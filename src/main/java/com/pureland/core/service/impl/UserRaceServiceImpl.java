package com.pureland.core.service.impl;

import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.pureland.common.db.data.*;
import com.pureland.common.enums.ArmoryBuildTypeEnum;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.ProductCommonService;
import com.pureland.common.service.impl.BuildingCommonServiceImpl;
import com.pureland.common.service.impl.ProductCommonServiceImpl;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.init.ArmoryModelHelper;
import com.pureland.core.service.UserRaceService;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author qinpeirong
 */
public class UserRaceServiceImpl extends UserRaceCommonServiceImpl implements UserRaceService {

    private BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class.getSimpleName());
    private ProductCommonService productCommonService = (ProductCommonService) SpringContextUtil.getBean(ProductCommonServiceImpl.class.getSimpleName());

    @Override
    public Long addUserRace(Long userId, Integer raceId, String nickName) throws CoreException {
        if (userId == null || raceId == null) {
            throw new CoreException("userId and raceId is invalid, can't be null");
        }

        UserRace userRace = new UserRace();
        userRace.setUserId(userId);
        userRace.setRaceId(raceId);
        userRace.setNickName(nickName);
        userRace.setLastLoginTime(System.currentTimeMillis());

        Multimap<Integer, String> map = ArmoryModelHelper.ENTITIES.get(Integer.valueOf(raceId.toString()));
        Collection<String> armyExpCidsTmp = map.get(Integer.parseInt(ArmoryBuildTypeEnum.ARMYTYPE.getId().toString()));
        Set<String> armyExpCids = Sets.newHashSet(armyExpCidsTmp);
        userRace.setArmyShop(armyExpCids);

        Collection<String> skillShopTmp = map.get(Integer.parseInt(ArmoryBuildTypeEnum.SKILLTYPE.getId().toString()));
        Set<String> skillShopCids = Sets.newHashSet(skillShopTmp);
        userRace.setSkillShop(skillShopCids);

        Long userRaceId = addUserRace(userRace);
        return userRaceId;
    }

    @Override
    public List<UserRace> getUserRaces(Long userId, Boolean sort) throws CoreException {
        return super.getUserRaces(userId, sort);
    }


    @Override
    public UserRace getLastUserRace(Long userId) throws CoreException {
        List<UserRace> userRaces = getUserRaces(userId, Boolean.TRUE);
        if (CollectionUtils.isNotEmpty(userRaces)) {
            return userRaces.get(0);
        }
        return null;
    }

    @Override
    public void updateLastLoginTime(Long userRaceId) throws CoreException {
        UserRace userRace = new UserRace();
        userRace.setId(userRaceId);
        userRace.setLastLoginTime(System.currentTimeMillis());
        updateUserRace(userRace);
    }


    @Override
    public UserRace getUserRace(Long userRaceId) throws CoreException {
        UserRace userRace = super.getUserRace(userRaceId);
        return userRace;
    }

    @Override
    public void updateAllBuildingArmyCid(Long userRaceId, Integer beforeCid, Integer upgradeCid) throws CoreException {
        List<Building> buildings = buildingCommonService.getBuildings(userRaceId);
        for (Building building : buildings) {
            // 判断建筑类型
            if (building.getBuildingType() != SubServerTypeEnum.Army) {
                continue;
            }
            List<Product> products = productCommonService.getProducts(userRaceId, building.getSid());
            for (Product product : products) {
                if (product.getCid().intValue() == beforeCid.intValue()) {
                    product.setCid(upgradeCid);
                    productCommonService.updateProduct(product);
                    break;
                }
            }
        }
    }

    @Override
    public void updateAllBuildingSkillCid(Long userRaceId, Integer beforeCid, Integer upgradeCid) throws CoreException {
        List<Building> buildings = buildingCommonService.getBuildings(userRaceId);
        for (Building building : buildings) {
            // 判断建筑类型
            if (building.getBuildingType() != SubServerTypeEnum.Laboratory) {
                continue;
            }
            List<Product> products = productCommonService.getProducts(userRaceId, building.getSid());
            for (Product product : products) {
                if (product.getCid().intValue() == beforeCid.intValue()) {
                    product.setCid(upgradeCid);
                    productCommonService.updateProduct(product);
                    break;
                }
            }
        }
    }


}
