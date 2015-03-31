package com.pureland.common.filters;

import java.util.Map;

import com.mongodb.DBObject;
import com.pureland.common.error.CommonException;
import com.pureland.common.util.DataObject;

/**
 * @author qinpr
 *
 */
public class Filters {

	private static Map<Class<? extends DataObject>, Filter<? extends DataObject>> filters;
	
	static {
	}
	
	
	/**
	 * 根据DataObject子类获取其对应的Filter，然后转为DataObject
	 * @param data
	 * @return
	 * @throws CommonException
	 */
	public <T extends DataObject> DBObject filterDataObject(T data) throws CommonException {
		if(data != null) {
			Filter<T> filter = (Filter<T>) filters.get(data.getClass());
			if(filter != null)
				return filter.filter(data);
			else
				throw new CommonException("Filter type match error");
		}
		return null;
	}
	

	public Map<Class<? extends DataObject>, Filter<? extends DataObject>> getFilters() {
		return filters;
	}

	public void setFilters(Map<Class<? extends DataObject>, Filter<? extends DataObject>> filters) {
		this.filters = filters;
	}
	
}
