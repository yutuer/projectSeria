package com.pureland.common.db.data;

import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;

public class ChatMsg extends DataObject {

	private static final long serialVersionUID = 4449756862830780035L;

	private int chatChannel;// 聊天频道
	private int chatType;// 聊天类型
	private Long chatId;// 聊天信息id
	private Long playerId;// 玩家id
	private String playerName; // 玩家姓名
	private Long sendTime;// 发送时间
	private Long clanId;// 公会id
	private String clanName;// 公会名称
	private Integer clanPosition;// 公会职位
	private Integer clanLevel;// 公会等级
	private String clanIcon;// 公会图标
	private Integer crown;// 积分
	private String content;// 聊天内容
	private Integer backUpNum;// 增援进度
	private Integer backUpUpper;// 增援上线
	private String sound;// 语音地址
	private Boolean isSoundUse;// 是否已经听过
	private Integer soundSecond;// 秒数
	private DonateArmy donateArmy; // 捐献信息

	// CHAT:clanId
	public static String getIdKeyString(Long clanId) {
		return generatorIdKey(Entity.CHAT, Entity.CLAN.name(), String.valueOf(clanId));
	}

	public DonateArmy getDonateArmy() {
		return donateArmy;
	}

	public void setDonateArmy(DonateArmy donateArmy) {
		this.donateArmy = donateArmy;
	}

	public String getSound() {
		return sound;
	}

	public Integer getSoundSecond() {
		return soundSecond;
	}

	public void setSoundSecond(Integer soundSecond) {
		this.soundSecond = soundSecond;
	}

	public Integer getBackUpNum() {
		return backUpNum;
	}

	public void setBackUpNum(Integer backUpNum) {
		this.backUpNum = backUpNum;
	}

	public Integer getBackUpUpper() {
		return backUpUpper;
	}

	public void setBackUpUpper(Integer backUpUpper) {
		this.backUpUpper = backUpUpper;
	}

	public int getChatChannel() {
		return chatChannel;
	}

	public void setChatChannel(int chatChannel) {
		this.chatChannel = chatChannel;
	}

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

	public String getClanIcon() {
		return clanIcon;
	}

	public void setClanIcon(String clanIcon) {
		this.clanIcon = clanIcon;
	}

	public Long getClanId() {
		return clanId;
	}

	public void setClanId(Long clanId) {
		this.clanId = clanId;
	}

	public Integer getClanLevel() {
		return clanLevel;
	}

	public void setClanLevel(Integer clanLevel) {
		this.clanLevel = clanLevel;
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	public Integer getClanPosition() {
		return clanPosition;
	}

	public void setClanPosition(Integer clanPosition) {
		this.clanPosition = clanPosition;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCrown() {
		return crown;
	}

	public void setCrown(Integer crown) {
		this.crown = crown;
	}

	public Boolean getIsSoundUse() {
		return isSoundUse;
	}

	public void setIsSoundUse(Boolean isSoundUse) {
		this.isSoundUse = isSoundUse;
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

	public String getSoundAddress() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}
}
