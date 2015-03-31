package com.pureland.core.service.clan;

import java.util.List;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.ChatMsg;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.enums.chat.ChatChannelServerEnum;
import com.pureland.common.enums.chat.ChatTypeServerEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.BaseRespProtocal;
import com.pureland.common.service.ChatCommonService;
import com.pureland.common.service.ClanCommonService;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.bean.chat.ChatClanBase;
import com.pureland.common.service.bean.chat.ChatPlayerBase;
import com.pureland.common.service.bean.clan.ClanBean;
import com.pureland.common.service.helper.ChatHelper;
import com.pureland.common.service.helper.ClanHelper;
import com.pureland.common.service.impl.ChatCommonServiceImpl;
import com.pureland.common.service.impl.ClanCommonServiceImpl;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.netty.websocket.ChannelGroupUtil;

public class ClanAskForBackUpServiceImpl extends ClanCommonServiceImpl implements ClanLogicService {

	private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());
	private ChatCommonService chatCommonService = (ChatCommonService) SpringContextUtil.getBean(ChatCommonServiceImpl.class.getSimpleName());
	private ClanCommonService clanCommonService = (ClanCommonService) SpringContextUtil.getBean(ClanCommonServiceImpl.class.getSimpleName());

	@Override
	public List<Clan> clanLogic(ClanBean clanBean) throws CoreException {
		Long userRaceId = clanBean.getClanBaseBean().getUserRaceId();
		String content = clanBean.getContent();

		DonateArmy donateArmy = clanCommonService.getUserRaceDonateArmy(userRaceId);
		long now = System.currentTimeMillis();
		if (donateArmy.getNextCanDonateTime() > now) {
			throw new CoreException("请求增援冷却时间没到!!");
		}

		Long chatId = chatCommonService.getNewClanId();
		UserRace userRace = userRaceCommonService.getUserRace(userRaceId);
		ChatPlayerBase chatPlayerBase = ChatHelper.MakeChatPlayerBase(chatId, userRace, ChatTypeServerEnum.BackUp);

		Clan clan = clanCommonService.getClanInfoByUserRaceId(userRaceId);
		ClanMember clanMember = clanCommonService.getClanMemberInfo(userRaceId);
		ChatClanBase chatClanBase = ChatHelper.MakeChatClanBase(clan, clanMember);
		ChatMsg chatMsg = ChatHelper.MakeClanChatMsg(chatPlayerBase, chatClanBase);
		chatMsg.setChatChannel(ChatChannelServerEnum.Clan.ordinal());
		chatMsg.setContent(content);
		chatMsg.setDonateArmy(donateArmy);

		// 这个值从玩家里面拿
		chatMsg.setBackUpNum(ClanHelper.getDonateArmyNum(donateArmy));
		chatMsg.setBackUpUpper(ClanHelper.getDonateArmyMaxSpace(userRaceId));

		chatCommonService.addChatMsg(chatMsg);

		// 更新发布时间
		donateArmy.setNextCanDonateTime(now + ClanHelper.BackUpCoolDown);
		clanCommonService.setUserRaceDonateArmy(donateArmy);

		BaseRespProtocal.BaseResp.Builder respBuilder = ChatHelper.makeAddChatRespBuilder(chatMsg, ChatChannelServerEnum.Clan);
		// 广播
		ChannelGroupUtil.broadCastMessage2Group(ChatHelper.getClanChatChannel(clan.getClanBase().getClanId()), respBuilder.build());

		List<Clan> ret = Lists.newArrayList();
		return ret;
	}
}
