package com.pureland.common.service.impl;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.dao.redis.MachineDAO;
import com.pureland.common.db.data.Machine;
import com.pureland.common.db.error.DBException;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.MachineCommonService;
import com.pureland.common.util.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by qinpeirong on 15-1-12.
 */
public class MachineCommonServiceImpl implements MachineCommonService {
    private MachineDAO machineDAO = (MachineDAO) SpringContextUtil.getBean(MachineDAO.class.getSimpleName());

    @Override
    public Long addMachine(String machineId, Long userId) throws CoreException {
        Machine machine = new Machine();
        machine.setMachineId(machineId);
        machine.setUserId(userId);
        try {
            return machineDAO.addMachine(machine);
        } catch (DBException e) {
            throw new CoreException(e.getMessage());
        }
    }

    @Override
    public void updateMachine(String machineId, Long userId) throws CoreException {
        String keyId = Machine.generatorIdKey(machineId);
        String id = null;
        try {
            id = RString.get(keyId);
        } catch (RedisException e) {
            throw new CoreException(e);
        }

        if (StringUtils.isEmpty(id)) {
            throw new CoreException("id is null");
        }

        long objId = Long.parseLong(id);
        Machine machine = new Machine();
        machine.setId(objId);
        machine.setUserId(userId);

        try {
            machineDAO.updateMachine(machine);
        } catch (DBException e) {
            throw new CoreException(e);
        }
    }

    @Override
    public Long getUserIdByMachineId(String machineId) throws CoreException {
        if (StringUtils.isEmpty(machineId))
            throw new CoreException("machineId is null");

        String keyId = Machine.generatorIdKey(machineId);
        String id = null;
        try {
            id = RString.get(keyId);
        } catch (RedisException e) {
           throw new CoreException(e);
        }

        Long userId = null;
        if (StringUtils.isNotEmpty(id)) {
            try {
                Machine machine = machineDAO.getMachine(Long.parseLong(id));
                userId = machine.getUserId();
            } catch (DBException e) {
                throw new CoreException(e);
            }
        }
        return userId;
    }
}
