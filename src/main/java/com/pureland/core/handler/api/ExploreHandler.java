package com.pureland.core.handler.api;

import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.Ship;
import com.pureland.common.enums.EnumHelper;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.ExploreReqProtocal;
import com.pureland.common.protocal.ExploreRespProtocal;
import com.pureland.common.protocal.ReqWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.vo.ExploreShipVOProtocal;
import com.pureland.common.protocal.vo.ResourceVOProtocal;
import com.pureland.common.service.bean.ExploreBean;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.explore.ExploreLogicService;
import com.pureland.core.service.explore.ExploreMap;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Map;

public class ExploreHandler extends RequestAPIHandler {
	private ExploreMap exploreMap = (ExploreMap) SpringContextUtil.getBean(ExploreMap.class.getSimpleName());

	@Override
	public RespWrapperProtocal.RespWrapper handler(ReqWrapperProtocal.ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
		Long userId = authUser(authToken);
		Long userRaceId = getLastUserRaceId(userId);
		ExploreReqProtocal.ExploreReq exploreReq = reqWrapper.getExploreReq();
		long sid = exploreReq.getSid();
		int section = exploreReq.getSection();

		ResourceVOProtocal.ResourceVO resourceVO = exploreReq.getConsumeResVO();

		ResourceServerTypeEnum consumeResourceServerType = ResourceServerTypeEnum.getResourceServerTypeEnumByName(resourceVO.getResourceType().name());
		int consumeCount = resourceVO.getResourceCount();

		String exploreType = exploreReq.getRequestType().name();
		ExploreLogicService exploreLogicService = exploreMap.getExploreMaps().get(exploreType);

		List<Ship> shipList = exploreLogicService.exploreLogic(new ExploreBean(userRaceId, sid, section, consumeResourceServerType, consumeCount));
		RespWrapperProtocal.RespWrapper respWrapper = transferShipList(shipList);
		return respWrapper;
	}

	private RespWrapperProtocal.RespWrapper transferShipList(List<Ship> shipList) {
		RespWrapperProtocal.RespWrapper.Builder respWrapperOrBuilder = RespWrapperProtocal.RespWrapper.newBuilder();
		ExploreRespProtocal.ExploreResp.Builder exploreRespBuilder = ExploreRespProtocal.ExploreResp.newBuilder();
		if (CollectionUtils.isNotEmpty(shipList)) {
			ExploreShipVOProtocal.ExploreShipVO.Builder exploreShipVOBuilder = ExploreShipVOProtocal.ExploreShipVO.newBuilder();
			for (Ship ship : shipList) {
				exploreShipVOBuilder.setCid(ship.getCid());
				exploreShipVOBuilder.setSid(ship.getSid());
				exploreShipVOBuilder.setEndTime(ship.getEndTime());
				exploreShipVOBuilder.setSection(ship.getSection());
				exploreShipVOBuilder.setState(ExploreShipVOProtocal.ExploreShipVO.State.valueOf(ship.getState()));

				Resource resource = ship.getUpgradeCostResource();
				if (resource != null) {
					ResourceVOProtocal.ResourceVO.Builder resourceBuilder = ResourceVOProtocal.ResourceVO.newBuilder();
					resourceBuilder.setResourceType(EnumHelper.getProtocalResourceType(resource.getResourceType()));
					resourceBuilder.setResourceCount(resource.getCount());
					exploreShipVOBuilder.setConsumeResource(resourceBuilder.build());
				}

				Map<String, Resource> rewardMap = ship.getResourcesForMap();
				if (rewardMap != null && rewardMap.size() > 0) {
					for (Resource r : rewardMap.values()) {
						ResourceVOProtocal.ResourceVO.Builder resourceBuilder = ResourceVOProtocal.ResourceVO.newBuilder();
						resourceBuilder.setResourceType(EnumHelper.getProtocalResourceType(r.getResourceType()));
						resourceBuilder.setResourceCount(r.getCount());
						exploreShipVOBuilder.addResources(resourceBuilder.build());
					}
				}

				ExploreShipVOProtocal.ExploreShipVO exploreShipVO = exploreShipVOBuilder.build();
				exploreRespBuilder.addExploreShipList(exploreShipVO);
			}
		}
		ExploreRespProtocal.ExploreResp exploreResp = exploreRespBuilder.build();
		respWrapperOrBuilder.setExploreResp(exploreResp);
		RespWrapperProtocal.RespWrapper respWrapper = respWrapperOrBuilder.build();
		return respWrapper;
	}
}