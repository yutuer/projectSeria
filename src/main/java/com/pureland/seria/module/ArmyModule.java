package com.pureland.seria.module;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import com.google.common.collect.Maps;
import com.pureland.common.db.data.Army;

public class ArmyModule extends AbstractModule {
	private static final long serialVersionUID = -3745387932313979799L;

	private Map<Integer, Army> armyMap = Maps.newHashMap();

	public ArmyModule() {
		super();
	}

	public ArmyModule(Long playerId) {
		super(playerId);
	}

	@Override
	public String getName() {
		return ModuleEnum.Army.name();
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(armyMap);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		armyMap = (Map<Integer, Army>) in.readObject();
	}
}
