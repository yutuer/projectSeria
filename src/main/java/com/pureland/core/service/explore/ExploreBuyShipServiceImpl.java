package com.pureland.core.service.explore;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.*;
import com.pureland.common.db.statics.BuildingLimitModel;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.enums.RaceServerTypeEnum;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.bean.ExploreBean;
import com.pureland.common.service.helper.ShipDbHelper;
import com.pureland.common.service.impl.ExploreCommonServiceImpl;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.common.service.impl.ResourceCommonServiceImpl;
import com.pureland.core.init.BuildingLimitModelHelper;

import java.util.List;

/**
 * Created by Administrator on 2015/3/6.
 */
public class ExploreBuyShipServiceImpl extends ExploreCommonServiceImpl implements ExploreLogicService {

	private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());
	private ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());

	@Override
	public List<Ship> exploreLogic(ExploreBean exploreBean) throws CoreException {
		List<Ship> shipList = Lists.newArrayList();
		Long userRaceId = exploreBean.getUserRaceId();

		// 根据玩家大北营等级得到当前可以够买的船的数量
		UserRace userRace = userRaceCommonService.getUserRace(userRaceId);
		Building base = userRace.getBuildingsForMap().get(SubServerTypeEnum.Base).get(0);
		BuildingLimitModel buildingLimitModel = BuildingLimitModelHelper.ENTITIES.get(base.getCid(),
				ShipDbHelper.GetShipIdByRace(RaceServerTypeEnum.getRaceServerTypeEnumById(userRace.getRaceId())));
		int canBuyCount = buildingLimitModel.getBuildingCount();

		// 得到当前船的数量
		int ownShipCount = super.getShipsCount(userRaceId).intValue();
		if (canBuyCount <= ownShipCount) {
			throw new CoreException("当前大北营不能购买船只");
		}

		// 获得玩家资源
		UserExt userExt = userRace.getUserExt();
		Resource resource = userExt.getResourcesForMap().get(ResourceServerTypeEnum.Gold);
		int need = (ownShipCount + 1) * 200;
		if (resource.getCount() < need) {
			throw new CoreException("购买船只钻石不够,需要%d,有%d", need, resource.getCount());
		}

		// 扣除玩家资源
		resource.setCount(resource.getCount() - need);
		resourceCommonService.updateResource(resource);
		// 添加船只
		Ship ship = ShipDbHelper.GetNewShip(userRaceId, RaceServerTypeEnum.getRaceServerTypeEnumById(userRace.getRaceId()));
		super.addShip(ship);

		shipList.add(ship);
		return shipList;
	}
}
