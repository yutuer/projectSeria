package com.pureland.common.service.impl;

import java.util.List;
import java.util.Set;

import com.pureland.common.db.dao.redis.clan.ClanDAO;
import com.pureland.common.db.dao.redis.clan.ClanMemberDAO;
import com.pureland.common.db.dao.redis.clan.DonateArmyDAO;
import com.pureland.common.db.data.ChatMsg;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.chat.ChatChannelServerEnum;
import com.pureland.common.enums.chat.ChatTypeServerEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.BaseRespProtocal;
import com.pureland.common.service.ChatCommonService;
import com.pureland.common.service.ClanCommonService;
import com.pureland.common.service.bean.clan.ClanBean;
import com.pureland.common.service.helper.ChatHelper;
import com.pureland.common.service.helper.ClanHelper;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.netty.websocket.ChannelGroupUtil;

/**
 * Created by Administrator on 2015/3/12.
 */
public class ClanCommonServiceImpl implements ClanCommonService {

	private ClanDAO clanDAO = (ClanDAO) SpringContextUtil.getBean(ClanDAO.class.getSimpleName());
	private ClanMemberDAO clanMemberDAO = (ClanMemberDAO) SpringContextUtil.getBean(ClanMemberDAO.class.getSimpleName());
	private ChatCommonService chatCommonService = (ChatCommonService) SpringContextUtil.getBean(ChatCommonServiceImpl.class.getSimpleName());
	private DonateArmyDAO donateArmyDAO = (DonateArmyDAO) SpringContextUtil.getBean(DonateArmyDAO.class.getSimpleName());

	@Override
	public ClanMember getClanMemberInfo(Long userRaceId) throws CoreException {
		try {
			return clanMemberDAO.getClanMemberInfo(userRaceId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public List<ClanMember> getClanMemberInfos(Long clanId) throws CoreException {
		try {
			return clanDAO.getClanMembersByClanId(clanId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void removeClan(Long clanId) throws CoreException {
		try {
			clanDAO.removeClan(clanId);
			chatCommonService.deleteClanChatMsgs(clanId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void updateClanMember(ClanMember clanMember) throws CoreException {
		clanMemberDAO.updateClanMember(clanMember);
	}

	@Override
	public int getNumsByPostion(Long clanId, int postion) throws CoreException {
		try {
			return clanDAO.getNumsByPostion(clanId, postion);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void updateClanBean(ClanBean clanBean) throws CoreException {
		try {
			Clan oldClan = getClanInfoByClanId(clanBean.getClanBaseBean().getClanId());
			// 设置下clanName
			clanBean.getClanBaseBean().setClanName(oldClan.getClanBase().getClanName());
			Clan newClan = ClanHelper.makeClanByClanBean(clanBean);
			clanDAO.updateClanBaseBean(oldClan, newClan);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public List<Clan> searchClan(String joinType, String clanName, String country, String clanLevel, String fightRate, String limitCrown, String numMinimum,
			String numMaxnium) {
		List<String> clanIds = clanDAO.searchClanIds(joinType, clanName, country, clanLevel, fightRate, limitCrown, numMinimum, numMaxnium);
		return clanDAO.getAllClan(clanIds);
	}

	@Override
	public Clan getClanInfoByClanId(Long clanId) throws CoreException {
		try {
			Clan clan = clanDAO.getClanByClanId(clanId);
			List<ClanMember> list = clanDAO.getClanMembersByClanId(clanId);
			clan.setClanMemberList(list);
			return clan;
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public Clan getClanInfoByUserRaceId(Long userRaceId) throws CoreException {
		try {
			Long clanId = clanMemberDAO.getClanIdByUserRaceId(userRaceId);
			if (clanId == null) {
				return null;
			}
			return getClanInfoByClanId(clanId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public boolean isHasClan(Long userRaceId) throws CoreException {
		return getClanInfoByUserRaceId(userRaceId) != null;
	}

	@Override
	public long getClanMemberNum(Long clanId) throws CoreException {
		return clanDAO.getClanMemberNum(clanId);
	}

	@Override
	public Long quitClan(Long userRaceId) throws CoreException {
		try {
			ClanMember clanMember = getClanMemberInfo(userRaceId);
			if (clanMember == null) {
				throw new CoreException("玩家没有公会,不能退出");
			}
			clanMemberDAO.removeClanMember(clanMember);
			// 将玩家增援的信息从公会里删除掉
			Set<ChatMsg> chatMsgs = chatCommonService.getAllClanChat(clanMember.getClanId());
			for (ChatMsg chatMsg : chatMsgs) {
				if (chatMsg.getPlayerId().longValue() == userRaceId.longValue() && chatMsg.getChatType() == ChatTypeServerEnum.BackUp.ordinal()) {
					chatCommonService.deleteClanChatMsg(clanMember.getClanId(), chatMsg.getChatId());
					// 广播给公会玩家
					BaseRespProtocal.BaseResp.Builder respBuilder = ChatHelper.makeDeleteChatRespBuilder(chatMsg, ChatChannelServerEnum.Clan);
					ChannelGroupUtil.broadCastMessage2Group(ChatHelper.getClanChatChannel(chatMsg.getClanId()), respBuilder.build());
				}
			}
			return clanMember.getClanId();
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public Long addClan(Clan clan) throws CoreException {
		try {
			// 首先检查重名
			boolean isExist = clanDAO.isExistClanName(clan.getClanBase().getClanName());
			if (isExist) {
				return null;
			}
			Long clanId = clanDAO.addClan(clan);
			return clanId;
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void addClanMember(ClanMember clanMember) throws CoreException {
		try {
			clanMemberDAO.addClanMember(clanMember);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public DonateArmy getUserRaceDonateArmy(Long userRaceId) throws CoreException {
		try {
			DonateArmy donateArmy = donateArmyDAO.getUserRaceDonateArmy(userRaceId);
			if (donateArmy == null) {
				donateArmy = new DonateArmy();
				donateArmy.setUserRaceId(userRaceId);
			}
			return donateArmy;
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void setUserRaceDonateArmy(DonateArmy donateArmy) throws CoreException {
		try {
			donateArmyDAO.setUserRaceDonateArmy(donateArmy);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void resetUserRaceDonateArmy(final Long userRaceId) throws CoreException {
		try {
			donateArmyDAO.resetUserRaceDonateArmy(userRaceId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

}
