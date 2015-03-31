package com.pureland.seria.db.seriaData.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Player implements Serializable {

	private static final long serialVersionUID = 4066118108283022329L;

	private long playerId;
	private Long userId;
	private Integer raceId; // 种族id
	private Long lastLoginTime;
	private Long lastOperateTime; // 最后一次发送消息的时间
	private String playerName;
	private Integer campCid;
	private Integer level;
	private Integer experience;
	private Integer crown;
	
	public Player() {
		super();
	}

	public Player(long playerId) {
		super();
		this.playerId = playerId;
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeLong(playerId);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		playerId = in.readLong();
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

}
