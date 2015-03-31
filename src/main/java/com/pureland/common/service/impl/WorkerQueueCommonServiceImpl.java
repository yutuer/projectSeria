package com.pureland.common.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.pureland.common.component.cache.api.RSet;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.enums.Entity;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.WorkerQueueCommonService;
import com.pureland.common.util.DataObject;

public class WorkerQueueCommonServiceImpl implements WorkerQueueCommonService {

    @Override
    public void addWorkerQuene(Long userRaceId, Long buildingSid)
            throws CoreException {
        String keyWorker = DataObject.generatorFieldKey(Entity.USERWORKER, userRaceId, null);
        try {
            RSet.sadd(keyWorker, String.valueOf(buildingSid));
        } catch (RedisException e) {
            throw new CoreException(e);
        }
    }


    @Override
    public void removeWorkerQuene(Long userRaceId, Long buildingSid) throws CoreException {
        String keyWorker = DataObject.generatorFieldKey(Entity.USERWORKER, userRaceId, null);
        try {
            RSet.srem(keyWorker, String.valueOf(buildingSid));
        } catch (RedisException e) {
            throw new CoreException(e);
        }
    }

    @Override
    public WorkerQuene getWorkerQuenes(Long userRaceId)
            throws CoreException {
        WorkerQuene workerQuene = new WorkerQuene();
        workerQuene.setUserRaceId(userRaceId);
        String keyWorker = DataObject.generatorFieldKey(Entity.USERWORKER, userRaceId, null);
        try {

            List<Long> buildingSids = Lists.newArrayList();
            Set<String> smembers = RSet.smembers(keyWorker);
            Iterator<String> iterator = smembers.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                buildingSids.add(Long.parseLong(next));
            }
            workerQuene.setBuildingSids(buildingSids);

        } catch (RedisException e) {
            throw new CoreException(e);
        }
        return workerQuene;
    }

    @Override
    public boolean isInWorkerQueues(Long userRaceId, Long buildingSid) throws CoreException {
        try {
            String keyWorker = DataObject.generatorFieldKey(Entity.USERWORKER, userRaceId, null);
            Set<String> smembers = RSet.smembers(keyWorker);
            return smembers.contains(String.valueOf(buildingSid));
        } catch (RedisException e) {
            throw new CoreException(e);
        }
    }

}
