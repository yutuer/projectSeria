package com.pureland.seria.db.seriaData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ArmyExp implements Serializable {

	private static final long serialVersionUID = 9216861537096897395L;

	private long armyExpId;
	private int cid;
	private int exp;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeLong(armyExpId);
		out.writeInt(cid);
		out.writeInt(exp);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		armyExpId = in.readLong();
		cid = in.readInt();
		exp = in.readInt();
	}

	public long getArmyExpId() {
		return armyExpId;
	}

	public void setArmyExpId(long armyExpId) {
		this.armyExpId = armyExpId;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

}
