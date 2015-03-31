package com.pureland.core.handler.api;

import java.nio.ByteBuffer;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.google.protobuf.ByteString;
import com.pureland.common.db.data.ChatMsg;
import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.enums.chat.ChatChannelServerEnum;
import com.pureland.common.enums.chat.ChatTypeServerEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.ChatReqProtocal;
import com.pureland.common.protocal.ChatRespProtocal;
import com.pureland.common.protocal.ReqWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.enums.ChatEnumProtocal;
import com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO;
import com.pureland.common.service.ClanCommonService;
import com.pureland.common.service.bean.chat.ChatBean;
import com.pureland.common.service.helper.ChatHelper;
import com.pureland.common.service.impl.ClanCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.chat.ChatLogicService;
import com.pureland.core.service.chat.ChatMap;

/**
 * Created by Administrator on 2015/3/19.
 */
public class ChatHandler extends RequestAPIHandler {

	private ChatMap chatMap = (ChatMap) SpringContextUtil.getBean(ChatMap.class.getSimpleName());
	private ClanCommonService clanCommonService = (ClanCommonService) SpringContextUtil.getBean(ClanCommonServiceImpl.class.getSimpleName());

	@Override
	public RespWrapperProtocal.RespWrapper handler(ReqWrapperProtocal.ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
		Long userId = authUser(authToken);
		Long userRaceId = getLastUserRaceId(userId);

		ChatReqProtocal.ChatReq chatReq = reqWrapper.getChatReq();

		String requestType = chatReq.getRequestType().name();

		ByteString byteString = chatReq.getSound();
		ByteBuffer byteBuffer = ByteBuffer.allocate(byteString.size());
		byteString.copyTo(byteBuffer);

		String content = chatReq.getContent();
		int soundSecond = chatReq.getSoundSecond();
		Long clientNowChatId = chatReq.getClientNowChatId();

		ChatChannelServerEnum chatChannelServerEnum = ChatChannelServerEnum.values()[chatReq.getChatChannel().getNumber()];
		ChatTypeServerEnum chatTypeServerEnum = ChatTypeServerEnum.values()[chatReq.getChatType().getNumber()];

		ChatLogicService chatLogicService = chatMap.getChatMap().get(requestType);
		ChatBean chatBean = new ChatBean(userRaceId, byteBuffer, chatChannelServerEnum, chatTypeServerEnum, content, soundSecond, clientNowChatId);

		List<ChatMsg> chatMsgs = chatLogicService.chatLogic(chatBean);

		RespWrapperProtocal.RespWrapper respWrapper = transferChatList(chatMsgs);
		return respWrapper;
	}

	private RespWrapperProtocal.RespWrapper transferChatList(List<ChatMsg> chatMsgs) throws CoreException {
		RespWrapperProtocal.RespWrapper.Builder respWrapperBuilder = RespWrapperProtocal.RespWrapper.newBuilder();
		ChatRespProtocal.ChatResp.Builder chatRespBuilder = ChatRespProtocal.ChatResp.newBuilder();
		if (CollectionUtils.isNotEmpty(chatMsgs)) {
			chatRespBuilder.setChatChannel(ChatEnumProtocal.ChatChannel.valueOf(chatMsgs.get(0).getChatChannel()));
		}
		for (ChatMsg chatMsg : chatMsgs) {
			if (chatMsg.getChatType() == ChatTypeServerEnum.BackUp.ordinal()) {
				DonateArmy donateArmy = clanCommonService.getUserRaceDonateArmy(chatMsg.getPlayerId());
				chatMsg.setDonateArmy(donateArmy);
			}
			ChatVO chatVO = ChatHelper.makeChatVO(chatMsg);
			chatRespBuilder.addChatVO(chatVO);
		}
		respWrapperBuilder.setChatResp(chatRespBuilder.build());
		return respWrapperBuilder.build();
	}
}
