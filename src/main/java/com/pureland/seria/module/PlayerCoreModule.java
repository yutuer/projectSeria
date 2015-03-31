package com.pureland.seria.module;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PlayerCoreModule extends AbstractModule {

	private static final long serialVersionUID = -134560128545750268L;

	private Long userId;
	private Integer raceId; // 种族id
	private Long lastLoginTime;
	private Long lastOperateTime; // 最后一次发送消息的时间
	private String playerName;
	private Integer campCid;
	private Integer level;
	private Integer experience;
	private Integer crown;

	@Override
	public String getName() {
		return ModuleEnum.Core.name();
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeLong(userId);
		out.writeInt(raceId);
		out.writeLong(lastLoginTime);
		out.writeLong(lastOperateTime);
		out.writeUTF(playerName);
		out.writeInt(campCid);
		out.writeInt(level);
		out.writeInt(experience);
		out.writeInt(crown);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		userId = in.readLong();
		raceId = in.readInt();
		lastLoginTime = in.readLong();
		lastOperateTime = in.readLong();
		playerName = in.readUTF();
		campCid = in.readInt();
		level = in.readInt();
		experience = in.readInt();
		crown = in.readInt();
	}
}
