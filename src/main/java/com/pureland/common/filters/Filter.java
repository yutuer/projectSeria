package com.pureland.common.filters;

import com.mongodb.DBObject;
import com.pureland.common.error.CommonException;
import com.pureland.common.util.CleanBasicDBObject;
import com.pureland.common.util.DataObject;



public abstract class Filter<T extends DataObject> {
	public static final String OUTPUT_ID = "outPutId";

	public abstract DBObject filter(T data) throws CommonException;
	
    protected DBObject newDBObject(T data) {
    	DBObject dbObj = new CleanBasicDBObject();
		return dbObj;
    }
    
    protected DBObject newDBObject() {
		return new CleanBasicDBObject();
	}
    
}
