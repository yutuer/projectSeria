package com.pureland.seria.service.building;

import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.common.service.UserExtCommonService;
import com.pureland.common.service.bean.BuildingCompleteBean;
import com.pureland.common.service.impl.ResourceCommonServiceImpl;
import com.pureland.common.service.impl.UserExtCommonServiceImpl;
import com.pureland.common.util.GameUtil;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.seria.db.seriaData.building.Building;

public class BuildCompleteLogicImmediate extends BuildCompleteLogicSeria {
	protected UserExtCommonService userExtCommonService = (UserExtCommonService) SpringContextUtil.getBean(UserExtCommonServiceImpl.class.getSimpleName());
	protected ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());

	@Override
	void completeBuilding0(BuildingCompleteBean buildingCompleteBean, Building building, EntityModel entityModel) throws CoreException {
		long now = System.currentTimeMillis();
		long buildEndTime = building.getEndTime();
		long sid = building.getSid();
		long cid = building.getCid();

		long clientTime = buildingCompleteBean.getNowTime();
		if (GameUtil.IsTimeWrong(clientTime, now)) {
			throw new CoreException("building has not been CompleteImmediately ,  sid = " + sid);
		}

		long leftime = buildEndTime - clientTime;
		int diffSeconds = (int) (leftime / 1000);
		int consume = GameUtil.getConsumeByTime(diffSeconds);

		// 判断消费足够不
		UserExt userExt = userExtCommonService.getUserExt(buildingCompleteBean.getUserRaceId());
		Resource resource = userExt.getResourcesForMap().get(ResourceServerTypeEnum.Diamond);
		int has = resource.getCount().intValue();
		if (consume > has) {
			throw new CoreException(String.format("玩家 %d立即完成升级技能消费不足,consume:%d ,当前拥有:%d\n", buildingCompleteBean.getUserRaceId(), consume, has));
		}
		// 扣钱
		int left = has - consume;
		resource.setCount(left);
		resourceCommonService.updateResource(resource);
	}

}
