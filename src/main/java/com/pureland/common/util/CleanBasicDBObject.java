package com.pureland.common.util;

import com.mongodb.BasicDBObject;

/**
 * Created by qinpeirong on 14-10-16.
 */
public class CleanBasicDBObject extends BasicDBObject {

    @Override
    public BasicDBObject append(String key, Object val) {
        if(val == null)
            return null;
        return super.append(key, val);
    }

    @Override
    public Object put(String key, Object val) {
        if(val == null)
            return this;
        return super.put(key, val);
    }

}
