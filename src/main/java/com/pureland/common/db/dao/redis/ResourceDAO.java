package com.pureland.common.db.dao.redis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.log.PurelandLog;

/**
 * @author qinpeirong
 */
public class ResourceDAO extends RedisDAO {

    private String TAG = PurelandLog.getClassTag(ResourceDAO.class);

    public Long addResource(Resource resource) throws DBException {
        Long resourceId = null;
        try {
            resourceId = RString.generator(Entity.RESOURCE.getName());
            String keyUserExtId = Resource.generatorFieldKey(resourceId, Entity.Resource.USEREXTID.getName());
            String keyResourceType = Resource.generatorFieldKey(resourceId, Entity.Resource.RESOURCETYPE.getName());
            String keyCount = Resource.generatorFieldKey(resourceId, Entity.Resource.COUNT.getName());

            RString.set(keyUserExtId, String.valueOf(resource.getUserExtId()));
            PurelandLog.info(TAG, "data persistence to redis { key=" + keyUserExtId + ", value=" + resource.getUserExtId() + "}");
            RString.set(keyResourceType, String.valueOf(resource.getResourceType().ordinal()));
            PurelandLog.info(TAG, "data persistence to redis { key=" + keyResourceType + ", value=" + resource.getResourceType().ordinal() + "}");
            RString.set(keyCount, String.valueOf(resource.getCount()));
            PurelandLog.info(TAG, "data persistence to redis { key=" + keyCount + ", value=" + resource.getCount() + "}");

            addUserExt2ResourceSetCollection(resource.getUserExtId(), String.valueOf(resourceId));

        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }

        return resourceId;
    }

    public Resource getResource(Long resourceId) throws DBException {
        Resource resource = new Resource();

        try {
            String keyUserExtId = Resource.generatorFieldKey(resourceId, Entity.Resource.USEREXTID.getName());
            String keyResourceType = Resource.generatorFieldKey(resourceId, Entity.Resource.RESOURCETYPE.getName());
            String keyCount = Resource.generatorFieldKey(resourceId, Entity.Resource.COUNT.getName());

            resource.setId(resourceId);
            resource.setUserExtId(Long.parseLong(RString.get(keyUserExtId)));
            resource.setResourceType(ResourceServerTypeEnum.getResourceServerTypeEnumById(Integer.parseInt(RString.get(keyResourceType))));
            resource.setCount(Integer.parseInt(RString.get(keyCount)));

        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }

        return resource;
    }

    public List<Resource> getResources(Long userExtId) throws DBException {
        List<Resource> resources = Lists.newArrayList();
        Set<String> resourceIds = getUserExt2ResourceSetCollection(userExtId);
        Iterator<String> iterator = resourceIds.iterator();

        while (iterator.hasNext()) {
            String resourceId = iterator.next();
            Resource resource = getResource(Long.parseLong(resourceId));
            resources.add(resource);
        }

        return resources;
    }

    public void updateResource(Resource resource) throws DBException {
        Long id = resource.getId();
        ResourceServerTypeEnum resourceType = resource.getResourceType();
        Integer count = resource.getCount();

        if (id == null) {
            throw new DBException("can not update resource because of id is null");
        }

        try {
            if (resourceType != null) {
                String keyResourceType = Resource.generatorFieldKey(id, Entity.Resource.RESOURCETYPE.getName());
                RString.set(keyResourceType, String.valueOf(resourceType.ordinal()));
            }
            if (count != null) {
                String keyCount = Resource.generatorFieldKey(id, Entity.Resource.COUNT.getName());
                RString.set(keyCount, String.valueOf(count));
            }

        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }
    }

    public void addUserExt2ResourceSetCollection(Long userExtId, String value) throws DBException {
        super.addSetCollection(Entity.USEREXT, getUserExt2ResourceSetKey(userExtId), value);
    }

    public Set<String> getUserExt2ResourceSetCollection(Long userExtId) throws DBException {
        return super.getSetCollection(Entity.USEREXT, getUserExt2ResourceSetKey(userExtId));
    }

    private String getUserExt2ResourceSetKey(Long userExtId) {
        return String.valueOf(userExtId) + Entity.SEPARATOR + Entity.RESOURCE.name();
    }

    public void addShip2ResourceSetCollection(Long shipId, String value) throws DBException {
        super.addSetCollection(Entity.SHIP, getUserExt2ResourceSetKey(shipId), value);
    }

    public Set<String> getShip2ResourceSetCollection(Long shipId) throws DBException {
        return super.getSetCollection(Entity.SHIP, getUserExt2ResourceSetKey(shipId));
    }

    private String getShip2ResourceSetKey(Long shipId) {
        return String.valueOf(shipId) + Entity.SEPARATOR + Entity.RESOURCE.name();
    }
}
