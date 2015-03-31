package com.pureland.core.service.explore;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.Ship;
import com.pureland.common.db.statics.ExploreModel;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.enums.ShipStateEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.ExploreBean;
import com.pureland.common.service.impl.ExploreCommonServiceImpl;
import com.pureland.common.util.GameUtil;
import com.pureland.core.init.ExploreModelHelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/3/9.
 */
public class CompleteExploreServiceImpl extends ExploreCommonServiceImpl implements ExploreLogicService {

    @Override
    public List<Ship> exploreLogic(ExploreBean exploreBean) throws CoreException {
        Long userRaceId = exploreBean.getUserRaceId();
        Long sid = exploreBean.getSid();

        Ship ship = super.getShip(userRaceId, sid);

        //判断状态
        if (ship.getState() != ShipStateEnum.EXPLORING.ordinal()) {
            throw new CoreException("状态不正确,需要:%s, 现在%s \n", ShipStateEnum.EXPLORING.name(), ShipStateEnum.values()[ship.getState()]);
        }

        Long endTime = ship.getEndTime();
        Long now = System.currentTimeMillis();
        if (GameUtil.IsTimeWrong(now, endTime)) {
            throw new CoreException("完成时间不正确!!");
        }

        //   查询表
        int section = ship.getSection();
        ExploreModel exploreModel = ExploreModelHelper.ENTITIES.get(section);
        //根据表来给船随机二个资源
        Map<String, Resource> getResources = randomGetReward(exploreModel);
        ship.setResourcesForMap(getResources);

        //更改状态
        ship.setState(ShipStateEnum.COMPLETE.ordinal());
        super.updateShip(ship);

        List<Ship> ret = Lists.newArrayList();
        ret.add(ship);
        return ret;
    }

    private Map<String, Resource> randomGetReward(ExploreModel exploreModel) {
        Map<String, Resource> ret = Maps.newHashMap();
        Map<ResourceServerTypeEnum, Integer> resourceMap = Maps.newHashMap();
        List<ResourceServerTypeEnum> list = Lists.newArrayList();
        if (exploreModel.getGold() > 0) {
            list.add(ResourceServerTypeEnum.Gold);
            resourceMap.put(ResourceServerTypeEnum.Gold, exploreModel.getGold());
        }
        if (exploreModel.getDiamond() > 0) {
            list.add(ResourceServerTypeEnum.Diamond);
            resourceMap.put(ResourceServerTypeEnum.Diamond, exploreModel.getDiamond());
        }
        if (exploreModel.getNewOil() > 0) {
            list.add(ResourceServerTypeEnum.NewOil);
            resourceMap.put(ResourceServerTypeEnum.NewOil, exploreModel.getNewOil());
        }
        if (exploreModel.getOil() > 0) {
            list.add(ResourceServerTypeEnum.Oil);
            resourceMap.put(ResourceServerTypeEnum.Oil, exploreModel.getOil());
        }
        if (list.size() > 2) {
            Collections.shuffle(list);
            list = list.subList(0, 2);
        }
        for (ResourceServerTypeEnum serverType : list) {
            Resource resource = new Resource();
            resource.setCount(resourceMap.get(serverType));
            resource.setResourceType(serverType);
            ret.put(serverType.name(), resource);
        }
        return ret;
    }
}
