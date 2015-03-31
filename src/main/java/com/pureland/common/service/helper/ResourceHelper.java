package com.pureland.common.service.helper;

import com.pureland.common.db.data.Building;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.core.init.EntityModelHelper;

/**
 * Created by Administrator on 2015/1/14.
 */
public class ResourceHelper {

	/**
	 * 得到当前能够获得的真正的资源
	 */
	public static int getBuildTrueResource(Building building, long gatherTime) {
		long diffTime = gatherTime - building.getGatherTime();
		// System.out.printf("gatherTime : %d, building.getGatherTime():%d , diffTime:%d\n",
		// gatherTime ,building.getGatherTime(), diffTime);

		EntityModel entityModel = EntityModelHelper.ENTITIES.get(building.getCid());
		// 到现在为止产出的数量
		int calCount = (int) (entityModel.getResourcePerSecond() * diffTime / 1000.0);
		// PurelandLog.debug(String.format("entityModel.getResourcePerSecond():%f, diffTime / 1000.0: %f",
		// entityModel.getResourcePerSecond(), diffTime / 1000.0));
		// 加上原来剩余的总量
		int trueCount = calCount + building.getStorageCount();
		// System.out.printf("calCount:%d,trueCount:%d\n",calCount,trueCount);
		// 不应该大于最大值
		if (trueCount > entityModel.getMaxResourceStorage()) {
			trueCount = entityModel.getMaxResourceStorage();
		}
		return trueCount;
	}

}
