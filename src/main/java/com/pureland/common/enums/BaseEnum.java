package com.pureland.common.enums;

import java.io.Serializable;

public interface BaseEnum extends Serializable {

    public abstract Long getId();
	
	public abstract String getCode();
	
	public abstract String getName();
	
	public abstract String getLabel();
}
