package com.pureland.common.service.battle.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.redis.battle.ResourceRecordDAO;
import com.pureland.common.db.data.battle.ResourceRecord;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.BattleType;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.BaseService;
import com.pureland.common.service.battle.ResourceRecordCommonService;
import com.pureland.common.util.SpringContextUtil;

public class ResourceRecordCommonServiceImpl extends BaseService implements
		ResourceRecordCommonService {
	private ResourceRecordDAO resourceRecordDAO = (ResourceRecordDAO) SpringContextUtil.getBean(ResourceRecordDAO.class.getSimpleName());

	@Override
	public Long addResourceRecord(ResourceRecord record) throws CoreException {
		try {
			return resourceRecordDAO.addResourceRecord(record);
		} catch (DBException e) {
			throw new CoreException(e);
		}
	}

	@Override
	public void addBatchResourceRecord(List<ResourceRecord> resourceRecords)
			throws CoreException {
		for(ResourceRecord resourceRecord : resourceRecords) {
			addResourceRecord(resourceRecord);
		}
		
	}

	@Override
	public List<ResourceRecord> getResourceRecords(Long userRaceId,
			Long battleId, BattleType battleType) throws CoreException {
		List<ResourceRecord> resourceRecords = Lists.newArrayList();
		try {
			Set<String> ids = resourceRecordDAO.getSetCollection(userRaceId, battleId, battleType);
			Iterator<String> iterator = ids.iterator();
			while(iterator.hasNext()) {
				String next = iterator.next();
				long id = Long.parseLong(next);
				ResourceRecord resourceRecord = resourceRecordDAO.getResourceRecord(id);
				resourceRecords.add(resourceRecord);
			}
		} catch (DBException e) {
			throw new CoreException(e);
		}
		return resourceRecords;
	}

}
