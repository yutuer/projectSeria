package com.pureland.common.db.dao.redis;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.Building;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.BuildingServerStatus;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.enums.Entity;
import com.pureland.common.log.PurelandLog;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

public class BuildingDAO extends RedisDAO {

	private String TAG = PurelandLog.getClassTag(BuildingDAO.class);

	public String addBuilding(Building building) throws DBException {
		Long buildingSid = building.getSid();
		Long userRaceId = building.getUserRaceId();
		String buildingId = null;
		try {
			if (buildingSid == null) {
				buildingSid = Building.generatorIdWithUserRace(userRaceId);
			} else {
				// TODO 还需要校验buildingSid是否和表中自增的一样,如果一样,则自增主键
				Long aaa = Building.generatorIdWithUserRace(userRaceId);
				assert (buildingSid.longValue() == aaa.longValue());
			}
			buildingId = Building.getIdKeyString(userRaceId, buildingSid);

			String keySid = Building.generatorFieldKey(buildingId, Entity.Building.SID.getName());
			String keyCid = Building.generatorFieldKey(buildingId, Entity.Building.CID.getName());
			String keyUserRaceId = Building.generatorFieldKey(buildingId, Entity.Building.USERRACEID.getName());
			String keyStatus = Building.generatorFieldKey(buildingId, Entity.Building.STATUS.getName());
			String keyType = Building.generatorFieldKey(buildingId, Entity.Building.BUILDINGTYPE.getName());
			String keyEndTime = Building.generatorFieldKey(buildingId, Entity.Building.BUILDENDTIME.getName());
			String keyAbscissa = Building.generatorFieldKey(buildingId, Entity.Building.ABSCISSA.getName());
			String keyOrdinate = Building.generatorFieldKey(buildingId, Entity.Building.ORDINATE.getName());
			String keyGatherTime = Building.generatorFieldKey(buildingId, Entity.Building.GATHERTIME.getName());
			String keyStorageCount = Building.generatorFieldKey(buildingId, Entity.Building.STORAGECOUNTSTORAGECOUNT.getName());

			RString.set(keySid, String.valueOf(buildingSid));
			RString.set(keyCid, String.valueOf(building.getCid()));
			PurelandLog.info(TAG, "data persistence to redis { key=" + keyCid + ", value=" + building.getCid() + "}");
			RString.set(keyUserRaceId, String.valueOf(building.getUserRaceId()));
			PurelandLog.info(TAG, "data persistence to redis { key=" + keyUserRaceId + ", value=" + building.getUserRaceId() + "}");
			RString.set(keyStatus, String.valueOf(building.getStatus().getId()));
			PurelandLog.info(TAG, "data persistence to redis { key=" + keyStatus + ", value=" + building.getStatus().getId() + "}");
			RString.set(keyType, String.valueOf(building.getBuildingType().ordinal()));
			PurelandLog.info(TAG, "data persistence to redis { key=" + keyType + ", value=" + building.getBuildingType().ordinal() + "}");

			Long buildEndTime = building.getEndTime();
			if (buildEndTime != null) {
				RString.set(keyEndTime, String.valueOf(buildEndTime));
				PurelandLog.info(TAG, "data persistence to redis { key=" + keyEndTime + ", value=" + buildEndTime + "}");
			}
			Long gatherTime = building.getGatherTime();
			if (gatherTime != null) {
				RString.set(keyGatherTime, String.valueOf(gatherTime));
			}
			Integer storageCount = building.getStorageCount();
			if (storageCount != null) {
				RString.set(keyStorageCount, String.valueOf(storageCount));
			}
			RString.set(keyAbscissa, String.valueOf(building.getAbscissa()));
			PurelandLog.info(TAG, "data persistence to redis { key=" + keyAbscissa + ", value=" + building.getAbscissa() + "}");
			RString.set(keyOrdinate, String.valueOf(building.getOrdinate()));
			PurelandLog.info(TAG, "data persistence to redis { key=" + keyOrdinate + ", value=" + building.getOrdinate() + "}");

			addSetCollection(building.getUserRaceId(), String.valueOf(buildingId));

		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}

		return buildingId;
	}

	public Building getBuilding(String buildingId) throws DBException {
		Building building = new Building();
		try {
			String keySid = Building.generatorFieldKey(buildingId, Entity.Building.SID.getName());
			String keyCid = Building.generatorFieldKey(buildingId, Entity.Building.CID.getName());
			String keyUserRaceId = Building.generatorFieldKey(buildingId, Entity.Building.USERRACEID.getName());
			String keyStatus = Building.generatorFieldKey(buildingId, Entity.Building.STATUS.getName());
			String keyType = Building.generatorFieldKey(buildingId, Entity.Building.BUILDINGTYPE.getName());
			String keyAbscissa = Building.generatorFieldKey(buildingId, Entity.Building.ABSCISSA.getName());
			String keyOrdinate = Building.generatorFieldKey(buildingId, Entity.Building.ORDINATE.getName());
			String keyBuildEndTime = Building.generatorFieldKey(buildingId, Entity.Building.BUILDENDTIME.getName());
			String keyGatherTime = Building.generatorFieldKey(buildingId, Entity.Building.GATHERTIME.getName());
			String keyStorageCount = Building.generatorFieldKey(buildingId, Entity.Building.STORAGECOUNTSTORAGECOUNT.getName());

			building.setId(buildingId);
			String cid = RString.get(keyCid);
			// TODO update later
			if (StringUtils.isEmpty(cid)) {
				return building;
			}
			building.setSid(Long.parseLong(RString.get(keySid)));
			building.setCid(Integer.parseInt(RString.get(keyCid)));
			building.setUserRaceId(Long.parseLong(RString.get(keyUserRaceId)));
			building.setStatus(BuildingServerStatus.getBuildingServerStatusById(new Long(RString.get(keyStatus))));
			building.setBuildingType(SubServerTypeEnum.getSubServerTypeEnumById(Integer.parseInt(RString.get(keyType))));
			building.setAbscissa(Integer.parseInt(RString.get(keyAbscissa)));
			building.setOrdinate(Integer.parseInt(RString.get(keyOrdinate)));
			String buildEndTime = RString.get(keyBuildEndTime);
			if (StringUtils.isNotEmpty(buildEndTime)) {
				building.setEndTime(Long.parseLong(buildEndTime));
			}
			String gatherTime = RString.get(keyGatherTime);
			if (StringUtils.isNotEmpty(gatherTime)) {
				building.setGatherTime(Long.parseLong(gatherTime));
			}
			String storageCount = RString.get(keyStorageCount);
			if (StringUtils.isNotEmpty(storageCount)) {
				building.setStorageCount(Integer.parseInt(storageCount));
			}
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}

		return building;
	}

	public void updateBuilding(Building building) throws DBException {
		String id = building.getId();
		long sid = building.getSid();
		Long userRaceId = building.getUserRaceId();
		Integer cid = building.getCid();
		BuildingServerStatus status = building.getStatus();
		Integer abs = building.getAbscissa();
		Integer ord = building.getOrdinate();
		Long buildEndTime = building.getEndTime();
		Long gatherTime = building.getGatherTime();
		Integer storageCount = building.getStorageCount();

		if (id == null) {
			throw new DBException("can not update building because of id is null");
		}

		if (userRaceId == null) {
			throw new DBException("userRaceId can not be null where update");
		}

		if (!isExistBuilding(userRaceId, id)) {
			throw new DBException("not exist building for id=" + id);
		}

		try {
			if (cid != null) {
				String keyCid = Building.generatorFieldKey(id, Entity.Building.CID.getName());
				RString.set(keyCid, String.valueOf(cid));
			}
			if (status != null) {
				String keyStatus = Building.generatorFieldKey(id, Entity.Building.STATUS.getName());
				RString.set(keyStatus, String.valueOf(status.getId()));
			}
			if (abs != null) {
				String keyAbscissa = Building.generatorFieldKey(id, Entity.Building.ABSCISSA.getName());
				RString.set(keyAbscissa, String.valueOf(abs));
			}
			if (ord != null) {
				String keyOrdinate = Building.generatorFieldKey(id, Entity.Building.ORDINATE.getName());
				RString.set(keyOrdinate, String.valueOf(ord));
			}
			if (buildEndTime != null) {
				String keyBuildEndTime = Building.generatorFieldKey(id, Entity.Building.BUILDENDTIME.getName());
				RString.set(keyBuildEndTime, buildEndTime == null ? null : String.valueOf(buildEndTime));
			}
			if (gatherTime != null) {
				String keyGatherTime = Building.generatorFieldKey(id, Entity.Building.GATHERTIME.getName());
				RString.set(keyGatherTime, String.valueOf(gatherTime));
			}
			if (storageCount != null) {
				String keyStorageCount = Building.generatorFieldKey(id, Entity.Building.STORAGECOUNTSTORAGECOUNT.getName());
				RString.set(keyStorageCount, String.valueOf(storageCount));
			}

		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
	}

	public Boolean isExistBuilding(Long userRaceId, final String id) throws DBException {
		Set<String> buldingIds = getSetCollection(userRaceId);
		return CollectionUtils.exists(buldingIds, new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				// TODO Auto-generated method stub
				return id.toString().equals(object);
			}

		});
	}

	public void addSetCollection(Long userRaceId, String value) throws DBException {
		super.addSetCollection(Entity.BUILDING, String.valueOf(userRaceId), value);
	}

	public Set<String> getSetCollection(Long userRaceId) throws DBException {
		return super.getSetCollection(Entity.BUILDING, String.valueOf(userRaceId));
	}

	public Boolean sisSetMember(Long userRaceId, String value) throws DBException {
		return super.sisSetMember(Entity.BUILDING, String.valueOf(userRaceId), value);
	}

}
