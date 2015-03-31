package com.pureland.core.service.clan;

import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.helper.ChatHelper;
import com.pureland.common.service.impl.ClanCommonServiceImpl;
import com.pureland.core.netty.websocket.ChannelGroupUtil;

/**
 * Created by Administrator on 2015/3/21.
 */
public class ClanServiceImpl extends ClanCommonServiceImpl {

	public void addClanMember(ClanMember clanMember) throws CoreException {
		super.addClanMember(clanMember);
		// 将玩家添加到公会聊天频道
		ChannelGroupUtil.addChannel2Group(ChatHelper.getClanChatChannel(clanMember.getClanId()), clanMember.getPlayerId());
	}

	public Long quitClan(Long userRaceId) throws CoreException {
		Long clanId = super.quitClan(userRaceId);
		ChannelGroupUtil.removeChannelFromGroup(ChatHelper.getClanChatChannel(clanId), userRaceId);
		return clanId;
	}
}
