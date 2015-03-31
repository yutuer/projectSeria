package com.pureland.common.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.dao.redis.ResourceDAO;
import com.pureland.common.db.dao.redis.UserExtDAO;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.service.BaseService;
import com.pureland.common.service.UserExtCommonService;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.init.EntityModelHelper;

public class UserExtCommonServiceImpl extends BaseService implements UserExtCommonService {
	private String TAG = PurelandLog.getClassTag(UserExtCommonServiceImpl.class);

	private UserExtDAO userExtDAO = (UserExtDAO) SpringContextUtil.getBean(UserExtDAO.class.getSimpleName());
	private ResourceDAO resourceDAO = (ResourceDAO) SpringContextUtil.getBean(ResourceDAO.class.getSimpleName());

	@Override
	public void initUserExt(Long userRaceId) throws CoreException {
		UserExt userExt = new UserExt();
		userExt.setUserRaceId(userRaceId);
		userExt.setLevel(EntityModelHelper.level);
		userExt.setExperience(EntityModelHelper.experience);
		userExt.setCrown(EntityModelHelper.crown);

		try {
			Long extId = userExtDAO.addUserExt(userExt);
			if (extId == null) {
				throw new CoreException("userExtId is null");
			}

			Map<ResourceServerTypeEnum, Integer> resource = EntityModelHelper.resource;
			Set<ResourceServerTypeEnum> keySet = resource.keySet();
			Iterator<ResourceServerTypeEnum> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				ResourceServerTypeEnum next = iterator.next();
				Integer count = resource.get(next);

				Resource r = new Resource();
				r.setUserExtId(extId);
				r.setResourceType(next);
				r.setCount(count);
				resourceDAO.addResource(r);
			}

		} catch (DBException e) {
			error(e);
		}

	}

	@Override
	public UserExt getUserExt(Long userRaceId) throws CoreException {
		UserExt userExt = new UserExt();
		try {
			String keyId = UserExt.generatorIdKey(String.valueOf(userRaceId));
			String extId = RString.get(keyId);

			if (StringUtils.isEmpty(extId)) {
				throw new CoreException("userExtId is null, keyId:" + keyId);
			}

			Long extId2Long = Long.parseLong(extId);
			userExt = userExtDAO.getUserExt(extId2Long);
			List<Resource> resources = resourceDAO.getResources(extId2Long);
			if (CollectionUtils.isNotEmpty(resources)) {
				userExt.setResources(resources);
			}

		} catch (RedisException e) {
			error(e);
		} catch (DBException e) {
			error(e);
		}

		return userExt;
	}

	@Override
	public void updateUserExt(UserExt userExt) throws CoreException {
		try {
			userExtDAO.updateUserExt(userExt);
		} catch (DBException e) {
			throw new CoreException(e);
		}

	}

}
