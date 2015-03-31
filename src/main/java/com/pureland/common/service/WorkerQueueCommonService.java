package com.pureland.common.service;

import java.util.List;

import com.pureland.common.error.CoreException;

/**
 * @author qinpeirong
 */
public interface WorkerQueueCommonService {
    public void addWorkerQuene(Long userRaceId, Long buildingSid) throws CoreException;

    public void removeWorkerQuene(Long userRaceId, Long buildingSid) throws CoreException;

    public WorkerQuene getWorkerQuenes(Long userRaceId) throws CoreException;

    public boolean isInWorkerQueues(Long userRaceId, Long buildingSid) throws CoreException;

    class WorkerQuene {
        private Long userRaceId;
        private List<Long> buildingSids;

        /**
         * @return the userRaceId
         */
        public Long getUserRaceId() {
            return userRaceId;
        }

        /**
         * @param userRaceId the userRaceId to set
         */
        public void setUserRaceId(Long userRaceId) {
            this.userRaceId = userRaceId;
        }

        /**
         * @return the buildingSids
         */
        public List<Long> getBuildingSids() {
            return buildingSids;
        }

        /**
         * @param buildingSids the buildingSids to set
         */
        public void setBuildingSids(List<Long> buildingSids) {
            this.buildingSids = buildingSids;
        }


    }
}
