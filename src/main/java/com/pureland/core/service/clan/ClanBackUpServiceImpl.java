package com.pureland.core.service.clan;

import java.util.List;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.Army;
import com.pureland.common.db.data.ChatMsg;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.db.data.clan.DonateInfo;
import com.pureland.common.enums.chat.ChatChannelServerEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.BaseRespProtocal;
import com.pureland.common.service.ArmyCommonService;
import com.pureland.common.service.ChatCommonService;
import com.pureland.common.service.ClanCommonService;
import com.pureland.common.service.bean.clan.ClanBean;
import com.pureland.common.service.helper.ChatHelper;
import com.pureland.common.service.helper.ClanHelper;
import com.pureland.common.service.impl.ArmyCommonServiceImpl;
import com.pureland.common.service.impl.ChatCommonServiceImpl;
import com.pureland.common.service.impl.ClanCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.init.EntityModelHelper;
import com.pureland.core.netty.websocket.ChannelGroupUtil;

/**
 * Created by Administrator on 2015/3/20.
 */
public class ClanBackUpServiceImpl extends ClanCommonServiceImpl implements ClanLogicService {

	private ChatCommonService chatCommonService = (ChatCommonService) SpringContextUtil.getBean(ChatCommonServiceImpl.class.getSimpleName());
	private ArmyCommonService armyCommonService = (ArmyCommonService) SpringContextUtil.getBean(ArmyCommonServiceImpl.class.getSimpleName());
	private ClanCommonService clanCommonService = (ClanCommonService) SpringContextUtil.getBean(ClanCommonServiceImpl.class.getSimpleName());

	@Override
	public List<Clan> clanLogic(ClanBean clanBean) throws CoreException {
		// 捐兵发起者自己的id
		Long userRaceId = clanBean.getClanBaseBean().getUserRaceId();
		Clan clan = super.getClanInfoByUserRaceId(userRaceId);
		Long operaId = clanBean.getOperaId();
		ChatMsg chatMsg = chatCommonService.getChatMsg(clan.getClanBase().getClanId(), operaId);
		if (chatMsg == null) {
			throw new CoreException("no find backup record : %d", operaId);
		}
		// 不能自己给自己捐兵
		if (chatMsg.getPlayerId().longValue() == userRaceId.longValue()) {
			throw new CoreException("can't backup self");
		}

		Integer armySid = clanBean.getArmySid();
		Army army = armyCommonService.getArmyByCid(userRaceId, armySid);
		if (army == null) {
			throw new CoreException("没有找到%d的军队", armySid);
		}
		if (army.getAmount() == 0) {
			throw new CoreException("没有%的足够军队,现在有%d", armySid, army.getAmount());
		}

		// 被捐兵的玩家id
		Long beDonateUserRaceId = chatMsg.getPlayerId();
		DonateArmy donateArmy = clanCommonService.getUserRaceDonateArmy(beDonateUserRaceId);

		// 判断容量
		int needSpace = EntityModelHelper.ENTITIES.get(armySid).getSpaceUse();
		int nowSpace = ClanHelper.getDonateArmyNowSpace(donateArmy);
		int maxSpace = ClanHelper.getDonateArmyMaxSpace(beDonateUserRaceId);
		if (nowSpace + needSpace > maxSpace) {
			throw new CoreException("容量不够, need:%d, now:%d ,max:%d", needSpace, nowSpace, maxSpace);
		}

		// 扣除自己的兵
		army.setAmount(army.getAmount() - 1);
		armyCommonService.updateArmy(army);

		// 修改
		if (nowSpace + needSpace == maxSpace) {
			clanCommonService.resetUserRaceDonateArmy(beDonateUserRaceId);
			// 删除掉这个信息
			chatCommonService.deleteClanChatMsgs(operaId);
			// 广播给公会玩家
			ChatMsg tmp = new ChatMsg();
			tmp.setChatId(operaId);
			BaseRespProtocal.BaseResp.Builder respBuilder = ChatHelper.makeDeleteChatRespBuilder(tmp, ChatChannelServerEnum.Clan);
			ChannelGroupUtil.broadCastMessage2Group(ChatHelper.getClanChatChannel(clan.getClanBase().getClanId()), respBuilder.build());
		} else {
			// 添加对方的增援
			DonateInfo donateInfo = new DonateInfo();
			donateInfo.setUserRaceId(userRaceId);
			donateInfo.setCid(armySid);
			donateInfo.setNum(1);
			donateArmy.getDonateInfoMap().add(donateInfo);
			clanCommonService.setUserRaceDonateArmy(donateArmy);

			chatMsg.setDonateArmy(donateArmy);

			BaseRespProtocal.BaseResp.Builder respBuilder = ChatHelper.makeUpdateChatRespBuilder(chatMsg, ChatChannelServerEnum.Clan);
			ChannelGroupUtil.broadCastMessage2Group(ChatHelper.getClanChatChannel(clan.getClanBase().getClanId()), respBuilder.build());
		}
		List<Clan> ret = Lists.newArrayList();
		return ret;
	}
}
