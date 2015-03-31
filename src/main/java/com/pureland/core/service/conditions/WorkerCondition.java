package com.pureland.core.service.conditions;

import java.util.List;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.service.bean.BuildingBean;
import com.pureland.common.db.data.Building;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.core.init.BuildingLimitModelHelper;
import com.pureland.core.init.EntityModelHelper;

/**
 * @author qinpeirong
 */
public class WorkerCondition extends BaseCondition implements Conditions {
    private String TAG = PurelandLog.getClassTag(WorkerCondition.class);

    @Override
    public Boolean validation(BuildingBean validateModel) throws CoreException {
        Long userRaceId = validateModel.getUserRaceId();
        Integer cid = validateModel.getCid();
        UserRace userRace = userRaceCommonService.getUserRace(userRaceId);
        EntityModel entityModel = EntityModelHelper.ENTITIES.get(cid);
        SubServerTypeEnum subType = SubServerTypeEnum.getSubServerTypeEnumByName(entityModel.getSubType());
        //获取大北营id
        Building base = userRace.getBuildingsForMap().get(SubServerTypeEnum.Base).get(0);

        List<Building> subTypeBuildings = userRace.getBuildingsForMap().get(subType);
        Integer buildingHomeCount = subTypeBuildings == null ? 0 : subTypeBuildings.size();
        int limitSize = BuildingLimitModelHelper.ENTITIES.get(base.getCid(), cid).getBuildingCount();

        if (buildingHomeCount >= limitSize) {
            throw new CoreException("workingCount is more than buildingHomeCount for userRaceId = " + userRaceId);
        }
        return true;
    }

}
