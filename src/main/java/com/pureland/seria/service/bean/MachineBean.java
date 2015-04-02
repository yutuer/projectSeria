package com.pureland.seria.service.bean;

import org.apache.commons.lang3.StringUtils;

import com.pureland.common.enums.Entity;

public class MachineBean {

	public static String generatorIdKey(String machineId) {
		return StringUtils.join(Entity.MACHINE, Entity.SEPARATOR, machineId, Entity.SEPARATOR, Entity.USER.name());
	}

}
