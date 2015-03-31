package com.pureland.seria.module;

import com.pureland.common.enums.Entity;

public abstract class AbstractModule implements IModule {

	private static final long serialVersionUID = -147644798726141482L;

	private Long playerId;

	public AbstractModule() {
		super();
	}

	public AbstractModule(Long playerId) {
		super();
		this.playerId = playerId;
	}

	@Override
	public Long getPlayerId() {
		return playerId;
	}

}
