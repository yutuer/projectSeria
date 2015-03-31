package com.pureland.core.service.conditions;

import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.BuildingBean;

/**
 * 
 * @author qinpeirong
 *
 */
public interface Conditions {
	public Boolean validation(BuildingBean validateModel) throws CoreException;
}
