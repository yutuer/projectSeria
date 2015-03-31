package com.pureland.seria.db.seriaData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import com.pureland.seria.db.seriaData.core.Player;

public class Skill implements Serializable {

	private static final long serialVersionUID = -8311329803963886746L;

	private Integer cid;
	private Integer amount;
	private Integer totalAmount;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(cid);
		out.writeInt(amount);
		out.writeInt(totalAmount);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		cid = in.readInt();
		amount = in.readInt();
		totalAmount = in.readInt();
	}
}
