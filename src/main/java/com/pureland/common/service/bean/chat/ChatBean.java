package com.pureland.common.service.bean.chat;

import com.pureland.common.enums.chat.ChatChannelServerEnum;
import com.pureland.common.enums.chat.ChatTypeServerEnum;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2015/3/19.
 */
public class ChatBean {

	private final Long playerId;
	private final Integer soundSecond;
	private final String content;
	private final ByteBuffer byteBuffer;
	private final ChatChannelServerEnum chatChannelServerEnum;
	private final ChatTypeServerEnum chatTypeServerEnum;
	private final Long clientNowChatId;

	public ChatBean(Long playerId, ByteBuffer byteBuffer, ChatChannelServerEnum chatChannelServerEnum, ChatTypeServerEnum chatTypeServerEnum, String content,
			Integer soundSecond, Long clientNowChatId) {
		this.playerId = playerId;
		this.byteBuffer = byteBuffer;
		this.chatChannelServerEnum = chatChannelServerEnum;
		this.chatTypeServerEnum = chatTypeServerEnum;
		this.content = content;
		this.soundSecond = soundSecond;
		this.clientNowChatId = clientNowChatId;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public ByteBuffer getByteBuffer() {
		return byteBuffer;
	}

	public ChatChannelServerEnum getChatChannelServerEnum() {
		return chatChannelServerEnum;
	}

	public ChatTypeServerEnum getChatTypeServerEnum() {
		return chatTypeServerEnum;
	}

	public String getContent() {
		return content;
	}

	public Integer getSoundSecond() {
		return soundSecond;
	}

	public Long getClientNowChatId() {
		return clientNowChatId;
	}
}
