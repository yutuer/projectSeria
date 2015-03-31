package com.pureland.common.service.bean.chat;

/**
 * Created by Administrator on 2015/3/19.
 */
public class ChatPlayerBase {

	private Long chatId;
	private Long playerId;
	private String playerName;
	private Long sendTime;
	private int chatType;
	private Integer crown;

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public int getChatType() {
		return chatType;
	}

	public void setChatType(int chatType) {
		this.chatType = chatType;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Long getSendTime() {
		return sendTime;
	}

	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}

	public Integer getCrown() {
		return crown;
	}

	public void setCrown(Integer crown) {
		this.crown = crown;
	}
}
