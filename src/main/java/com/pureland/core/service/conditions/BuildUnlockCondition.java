package com.pureland.core.service.conditions;

import java.util.List;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.service.bean.BuildingBean;
import com.pureland.common.db.data.Building;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.core.init.EntityModelHelper;

/**
 * @author qinpeirong
 */
public class BuildUnlockCondition extends BaseCondition implements Conditions {

    @Override
    public Boolean validation(BuildingBean validateModel) throws CoreException {
        Long userRaceId = validateModel.getUserRaceId();
        Integer cid = validateModel.getCid();
        //如果是大本营升级依赖玩家等级、军衔、经验值等等,待完善
        UserRace userRace = userRaceCommonService.getUserRace(userRaceId);
        EntityModel entityModel = EntityModelHelper.ENTITIES.get(cid);
        //判断建造解锁条件;
        SubServerTypeEnum buildNeedType = SubServerTypeEnum.getSubServerTypeEnumByName(entityModel.getBuildNeedType());
        if (buildNeedType == SubServerTypeEnum.Crown) {
            //TODO crown的逻辑


        } else if (buildNeedType != SubServerTypeEnum.None) { //BASE,Study
            //比较玩家的该建筑level是否>=表里的id
            SubServerTypeEnum need_subType = SubServerTypeEnum.getSubServerTypeEnumByName(entityModel.getBuildNeedType());
            List<Building> hasBuilds = userRace.getBuildingsForMap().get(need_subType);
            assert (hasBuilds.size() == 1);
            EntityModel hasBuild = EntityModelHelper.ENTITIES.get(hasBuilds.get(0).getCid());
            if (hasBuild.getLevel() < entityModel.getBuildNeedLevel()) {
                throw new CoreException(String.format("level condition is not enough nowLevel : %d, needLevel : %d", hasBuild.getLevel(), entityModel.getBuildNeedLevel()));
            }
        }
        return true;
    }

}
