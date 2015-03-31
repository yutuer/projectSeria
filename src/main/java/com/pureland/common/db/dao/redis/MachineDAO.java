package com.pureland.common.db.dao.redis;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.Machine;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by qinpeirong on 15-1-9.
 */
public class MachineDAO extends RedisDAO {

    public Long addMachine(Machine machine) throws DBException {
        Long id = machine.getId();
        String machineId = machine.getMachineId();
        Long userId = machine.getUserId();

        if (StringUtils.isEmpty(machineId)) {
            throw new DBException("machineId is null");
        }
        if (userId == null) {
            throw new DBException("userId is null");
        }

        try {
            if(id == null) {
                id = RString.generator(Entity.MACHINE.getName());
            }

            String keyId = Machine.generatorIdKey(String.valueOf(machineId));
            String keyMachineId = Machine.generatorFieldKey(id, Entity.Machine.MACHINEID.getName());
            String keyUserId = Machine.generatorFieldKey(id, Entity.Machine.USERID.getName());
            RString.set(keyId, String.valueOf(id));
            RString.set(keyMachineId, machineId);
            RString.set(keyUserId, String.valueOf(userId));

        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }
        return null;
    }

    public Machine getMachine(Long id) throws DBException {
        if (id == null) {
            throw new DBException("id is null");
        }
        Machine machine = new Machine();
        String keyMachineId = Machine.generatorFieldKey(id, Entity.Machine.MACHINEID.getName());
        String keyUserId = Machine.generatorFieldKey(id, Entity.Machine.USERID.getName());

        try {
            String machineId = RString.get(keyMachineId);
            String userId = RString.get(keyUserId);

            if (StringUtils.isNotEmpty(machineId) && StringUtils.isNotEmpty(userId)) {
                machine.setId(id);
                machine.setMachineId(machineId);
                machine.setUserId(Long.parseLong(userId));
            }

        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }
        return machine;
    }


    public void updateMachine(Machine machine) throws DBException {
        Long id = machine.getId();
        if (id == null) {
            throw new DBException("id is null");
        }

        String keyMachineId = Machine.generatorFieldKey(id, Entity.Machine.MACHINEID.getName());
        String keyUserId = Machine.generatorFieldKey(id, Entity.Machine.USERID.getName());

        String machineId = machine.getMachineId();
        Long userId = machine.getUserId();

        try {
            if (StringUtils.isNotEmpty(machineId)) {
                RString.set(keyMachineId, machineId);
            }
            if (userId != null) {
                RString.set(keyUserId, String.valueOf(userId));
            }
        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }
    }
}
