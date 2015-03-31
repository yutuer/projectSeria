package com.pureland.seria.db.seriaData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Army implements Serializable {

	private static final long serialVersionUID = -8562324763478859421L;

	private long armyId;
	private int amount;
	private ArmyExp armyExp;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeLong(armyId);
		out.writeInt(amount);
		out.writeObject(armyExp);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		armyId = in.readLong();
		amount = in.readInt();
		armyExp = (ArmyExp) in.readObject();
	}

	public long getArmyId() {
		return armyId;
	}

	public void setArmyId(long armyId) {
		this.armyId = armyId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ArmyExp getArmyExp() {
		return armyExp;
	}

	public void setArmyExp(ArmyExp armyExp) {
		this.armyExp = armyExp;
	}

}
