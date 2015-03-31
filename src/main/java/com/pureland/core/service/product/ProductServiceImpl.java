package com.pureland.core.service.product;

import com.pureland.common.db.data.Building;
import com.pureland.common.enums.BuildingServerStatus;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.*;
import com.pureland.common.service.impl.*;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.service.*;
import com.pureland.core.service.impl.*;
import com.pureland.common.service.bean.ProductBean;

/**
 * Created by Administrator on 2015/2/4.
 */
public abstract class ProductServiceImpl extends ProductCommonServiceImpl implements ProductService {

    protected UserRaceService userRaceService = (UserRaceService) SpringContextUtil.getBean(UserRaceServiceImpl.class.getSimpleName());
    protected UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());
    protected ArmyService armyService = (ArmyService) SpringContextUtil.getBean(ArmyServiceImpl.class.getSimpleName());
    protected ArmyCommonService armyCommonService = (ArmyCommonService) SpringContextUtil.getBean(ArmyCommonServiceImpl.class.getSimpleName());
    protected SkillService skillService = (SkillService) SpringContextUtil.getBean(SkillServiceImpl.class.getSimpleName());
    protected UserExtCommonService userExtCommonService = (UserExtCommonService) SpringContextUtil.getBean(UserExtCommonServiceImpl.class.getSimpleName());
    protected ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());
    protected SkillCommonService skillCommonService = (SkillCommonService) SpringContextUtil.getBean(SkillCommonServiceImpl.class.getSimpleName());
    protected BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());

    @Override
    public void product(ProductBean productBean) throws CoreException {
        Long userRaceId = productBean.getUserRaceId();
        Long buildingSid = productBean.getBuildingSid();
        String keyBuildingId = Building.getIdKeyString(userRaceId, buildingSid);
        Building building = buildingCommonService.getBuilding(keyBuildingId);
        if (building.getStatus() != BuildingServerStatus.ON) {
            throw new CoreException("建筑状态错误: %s ", building.getStatus().name());
        }
        product0(productBean, building);
    }

    abstract void product0(ProductBean productBean, Building building) throws CoreException;

}
