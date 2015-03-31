package com.pureland.core.service.chat;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.ChatMsg;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.ChatCommonService;
import com.pureland.common.service.ClanCommonService;
import com.pureland.common.service.bean.chat.ChatBean;
import com.pureland.common.service.impl.ChatCommonServiceImpl;
import com.pureland.common.service.impl.ClanCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/3/20.
 */
public class GetClanChatMsgsServiceImpl extends ChatCommonServiceImpl implements ChatLogicService {

	private ClanCommonService clanCommonService = (ClanCommonService) SpringContextUtil.getBean(ClanCommonServiceImpl.class.getSimpleName());
	private ChatCommonService chatCommonService = (ChatCommonService) SpringContextUtil.getBean(ChatCommonServiceImpl.class.getSimpleName());

	@Override
	public List<ChatMsg> chatLogic(ChatBean chatBean) throws CoreException {
		Long playerId = chatBean.getPlayerId();
		Long clientNowChatId = chatBean.getClientNowChatId();

		Clan clan = clanCommonService.getClanInfoByUserRaceId(playerId);
		if (clan == null) {
			throw new CoreException("玩家没有公会");
		}

		Long clanId = clan.getClanBase().getClanId();

		Set<ChatMsg> ret = null;
		if (clientNowChatId == 0) {
			ret = chatCommonService.getAllClanChat(clanId);
		} else {
			ret = chatCommonService.getRangeClanChat(clanId, clientNowChatId);
		}
		return Lists.newArrayList(ret);
	}
}
