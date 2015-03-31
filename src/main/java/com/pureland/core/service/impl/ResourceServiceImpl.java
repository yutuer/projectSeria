package com.pureland.core.service.impl;

import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.UserExtCommonService;
import com.pureland.common.service.impl.BuildingCommonServiceImpl;
import com.pureland.common.service.impl.ResourceCommonServiceImpl;
import com.pureland.common.service.impl.UserExtCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.core.service.ResourceService;
import com.pureland.common.util.GameUtil;

/**
 * Created by Administrator on 2015/2/10.
 */
public class ResourceServiceImpl extends ResourceCommonServiceImpl implements ResourceService {

    private UserExtCommonService userExtCommonService = (UserExtCommonService) SpringContextUtil.getBean(UserExtCommonServiceImpl.class.getSimpleName());
    private BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());
    private ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());

    @Override
    public void buyResource(Long userRaceId, ResourceServerTypeEnum resourceServerType, int count) throws CoreException {
        //资源类型不能是钻石
        if (resourceServerType == ResourceServerTypeEnum.Diamond) {
            throw new CoreException("不能使用钻石换钻石");
        }

        UserExt userExt = userExtCommonService.getUserExt(userRaceId);
        Resource nowResource = userExt.getResourcesForMap().get(resourceServerType);

        SubServerTypeEnum buildingType = GameUtil.getBuildingTypeByResourceType(resourceServerType);
        int max = buildingCommonService.getSameTypeBuildingsMaxResourceStorage(userRaceId, buildingType);
        //判断容量
        if (nowResource.getCount() + count > max) {
            throw new CoreException("容量不足,nowResource.getCount():%d, count:%d, max:%d", nowResource.getCount(), count, max);
        }

        Resource diamondResource = userExt.getResourcesForMap().get(ResourceServerTypeEnum.Diamond);
        int hasDiamond = diamondResource.getCount();
        int consumeDiamond = GameUtil.ResourceToGem(count);
        //判断身上是否有足够钻石
        if (consumeDiamond > hasDiamond) {
            throw new CoreException("钻石不足,consumeDiamond:%d, hasDiamond:%d", consumeDiamond, hasDiamond);
        }
        //扣除掉钻石
        diamondResource.setCount(hasDiamond - consumeDiamond);
        resourceCommonService.updateResource(diamondResource);
        //增加相应资源
        nowResource.setCount(nowResource.getCount() + count);
        resourceCommonService.updateResource(nowResource);
    }
}
