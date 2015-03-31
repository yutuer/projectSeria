package com.pureland.common.enums;

import com.pureland.common.protocal.vo.ResourceTypeProtocal;

/**
 * Created by Administrator on 2015/3/11.
 */
public class EnumHelper {

    public static ResourceTypeProtocal.ResourceType getProtocalResourceType(ResourceServerTypeEnum type) {
        for (ResourceTypeProtocal.ResourceType resourceType : ResourceTypeProtocal.ResourceType.values()) {
            if (resourceType.toString().equals(type.name())) {
                return resourceType;
            }
        }
        return null;
    }
}
