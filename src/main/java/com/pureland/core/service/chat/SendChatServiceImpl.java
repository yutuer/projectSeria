package com.pureland.core.service.chat;

import java.nio.ByteBuffer;
import java.util.List;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.ChatMsg;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.enums.chat.ChatChannelServerEnum;
import com.pureland.common.enums.chat.ChatTypeServerEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.BaseRespProtocal;
import com.pureland.common.service.ChatCommonService;
import com.pureland.common.service.ClanCommonService;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.bean.chat.ChatBean;
import com.pureland.common.service.bean.chat.ChatClanBase;
import com.pureland.common.service.bean.chat.ChatPlayerBase;
import com.pureland.common.service.helper.ChatHelper;
import com.pureland.common.service.impl.ChatCommonServiceImpl;
import com.pureland.common.service.impl.ClanCommonServiceImpl;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.netty.websocket.ChannelGroupUtil;

/**
 * Created by Administrator on 2015/3/19.
 */
public class SendChatServiceImpl extends ChatCommonServiceImpl implements ChatLogicService {

	private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());
	private ClanCommonService clanCommonService = (ClanCommonService) SpringContextUtil.getBean(ClanCommonServiceImpl.class.getSimpleName());
	private ChatCommonService chatCommonService = (ChatCommonService) SpringContextUtil.getBean(ChatCommonServiceImpl.class.getSimpleName());

	@Override
	public List<ChatMsg> chatLogic(ChatBean chatBean) throws CoreException {
		Long playerId = chatBean.getPlayerId();
		ChatChannelServerEnum chatChannelServerEnum = chatBean.getChatChannelServerEnum();
		ChatTypeServerEnum chatTypeServerEnum = chatBean.getChatTypeServerEnum();

		Long chatId = chatCommonService.getNewClanId();

		UserRace userRace = userRaceCommonService.getUserRace(playerId);
		ChatPlayerBase chatPlayerBase = ChatHelper.MakeChatPlayerBase(chatId, userRace, ChatTypeServerEnum.Normal);

		Clan clan = clanCommonService.getClanInfoByUserRaceId(playerId);
		ClanMember clanMember = clanCommonService.getClanMemberInfo(playerId);
		ChatClanBase chatClanBase = ChatHelper.MakeChatClanBase(clan, clanMember);

		ChatMsg chatMsg = ChatHelper.MakeClanChatMsg(chatPlayerBase, chatClanBase);
		chatMsg.setChatChannel(chatChannelServerEnum.ordinal());

		if (chatChannelServerEnum == ChatChannelServerEnum.Clan) {
			if (clan == null) {
				throw new CoreException("玩家没有公会,不能发起公会聊天");
			}
			chatMsg.setContent(chatBean.getContent());

			// 需要存储
			if (chatTypeServerEnum == ChatTypeServerEnum.Sound) {
				ByteBuffer byteBuffer = chatBean.getByteBuffer();
				String path = ChatHelper.writeSoundFile(playerId, byteBuffer.array());
				chatMsg.setSound(path);
				chatMsg.setIsSoundUse(false);
				chatMsg.setSoundSecond(chatBean.getSoundSecond());
				chatMsg.setChatType(ChatTypeServerEnum.Sound.ordinal());
			}
			chatCommonService.addChatMsg(chatMsg);
		} else if (chatChannelServerEnum == ChatChannelServerEnum.Global) {
			// 只需要转播,只能是内容
			if (chatTypeServerEnum != ChatTypeServerEnum.Normal) {
				throw new CoreException("只能是正常消息");
			}
		}

		BaseRespProtocal.BaseResp.Builder respBuilder = ChatHelper.makeAddChatRespBuilder(chatMsg, chatChannelServerEnum);

		if (chatChannelServerEnum == ChatChannelServerEnum.Clan) {
			// 广播
			ChannelGroupUtil.broadCastMessage2Group(ChatHelper.getClanChatChannel(clan.getClanBase().getClanId()), respBuilder.build());
		} else if (chatChannelServerEnum == ChatChannelServerEnum.Global) {
			// 广播
			ChannelGroupUtil.broadCastMessage2Group(ChatHelper.getWorldChatChannel(playerId), respBuilder.build());
		}

		List<ChatMsg> ret = Lists.newArrayList();
		return ret;
	}
}
