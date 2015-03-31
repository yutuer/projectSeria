package com.pureland.common.service;

import com.pureland.common.error.CoreException;

/**
 * Created by qinpeirong on 15-1-12.
 */
public interface MachineCommonService {

    public Long addMachine(String machineId, Long userId) throws CoreException;

    public void updateMachine(String machineId, Long userId) throws CoreException;

    public Long getUserIdByMachineId(String machineId) throws CoreException;
}
