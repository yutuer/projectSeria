package com.pureland.common.service.battle;

import java.util.List;

import com.pureland.common.db.data.battle.ResourceRecord;
import com.pureland.common.enums.BattleType;
import com.pureland.common.error.CoreException;

/**
 * 
 * @author qinpeirong
 *
 */
public interface ResourceRecordCommonService {

	public Long addResourceRecord(ResourceRecord record) throws CoreException;
	
	public void addBatchResourceRecord(List<ResourceRecord> resourceRecords) throws CoreException;
	
	public List<ResourceRecord> getResourceRecords(Long userRaceId, Long battleId, BattleType battleType) throws CoreException;
}
