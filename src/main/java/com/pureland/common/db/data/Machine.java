package com.pureland.common.db.data;

import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;

public class Machine extends DataObject {

	private static final long serialVersionUID = -6189124748984178727L;
	private Long id;
	private String machineId;
	private Long userId;

	public static String generatorIdKey(String singleMark) {
		return generatorIdKey(Entity.MACHINE, Entity.Machine.ID.getName(), singleMark);
	}

	public static String generatorFieldKey(Long id, String field) {
		return generatorFieldKey(Entity.MACHINE, id, field);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
