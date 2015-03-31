package com.pureland.core.service.conditions;

import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.BuildingBean;
import com.pureland.core.init.EntityModelHelper;

/**
 * @author qinpeirong
 */
public class UpgradeResourceCondition extends BaseCondition implements Conditions {

    @Override
    public Boolean validation(BuildingBean validateModel) throws CoreException {
        Long userRaceId = validateModel.getUserRaceId();
        Integer cid = validateModel.getCid();
        ResourceServerTypeEnum costType = ResourceServerTypeEnum.getResourceServerTypeEnumByName(validateModel.getCostType());
        UserRace userRace = userRaceCommonService.getUserRace(userRaceId);
        EntityModel entityModel = EntityModelHelper.ENTITIES.get(cid);
        ResourceServerTypeEnum resourceNeededType = ResourceServerTypeEnum.getResourceServerTypeEnumByName(entityModel.getCostResourceType());
        Integer resourceNeededCount = entityModel.getCostResourceCount();
        if (resourceNeededType == ResourceServerTypeEnum.GoldOrOil) {
            if (costType != ResourceServerTypeEnum.Gold || costType != ResourceServerTypeEnum.Oil) {
                throw new CoreException(String.format("costType is not GOLD or OIL,  resourceNeededType:%s, costType: %s", resourceNeededType.name(), costType.name()));
            }
        } else {
            if (costType != resourceNeededType) {
                throw new CoreException(String.format("costType is diff,  resourceNeededType:%s, costType : %s", resourceNeededType.name(), costType.name()));
            }
        }
        Resource resource = userRace.getUserExt().getResourcesForMap().get(costType);
        Integer currentReourceCount = resource.getCount();
        if (currentReourceCount < resourceNeededCount) {
            throw new CoreException(String.format("资源不够 :currentReourceCount: %d, resourceNeededCount : %d", currentReourceCount, resourceNeededCount));
        }
        return true;
    }
}
