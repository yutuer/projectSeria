package com.pureland.seria.db.seriaData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.pureland.common.enums.ResourceServerTypeEnum;

public class Resource implements Serializable {

	private static final long serialVersionUID = 7353185057110159162L;
	private ResourceServerTypeEnum resourceType;
	private Integer count;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(resourceType.name());
		out.writeInt(count);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		resourceType = ResourceServerTypeEnum.valueOf(in.readUTF());
		count = in.readInt();
	}
}
