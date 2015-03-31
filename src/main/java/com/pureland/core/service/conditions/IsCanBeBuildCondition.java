package com.pureland.core.service.conditions;

import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.BuildingBean;
import com.pureland.core.init.EntityModelHelper;

/**
 * Created by Administrator on 2015/1/27.
 */
public class IsCanBeBuildCondition extends BaseCondition implements Conditions {
    @Override
    public Boolean validation(BuildingBean validateModel) throws CoreException {
        Long userRaceId = validateModel.getUserRaceId();
        Integer cid = validateModel.getCid();
        Long sid = validateModel.getSid();
        UserRace userRace = userRaceCommonService.getUserRace(userRaceId);
        EntityModel entityModel = EntityModelHelper.ENTITIES.get(cid);

        //TODO 验证传入的cid是否能够建造,查询商城表,在里面的表示能被造
        entityModel.getSubType();

        //验证是否不在workqueue队列中
        if (queueService.isInWorkerQueues(userRaceId, sid)) {
            throw new CoreException(String.format("在工作队列中 ,sid:%d, cid:%d \n", sid, cid));
        }

//        if (BuildingType.BASE.getId().equals(super.entityModel.getSubType())) {
//            return false;
//        }
        return true;
    }
}
