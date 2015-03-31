package com.pureland.common.service.impl;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.dao.redis.UserDAO;
import com.pureland.common.db.data.User;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;
import com.pureland.common.error.CoreException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.service.BaseService;
import com.pureland.common.service.UserCommonService;
import com.pureland.common.util.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author qinpeirong
 *
 */
public class UserCommonServiceImpl extends BaseService implements UserCommonService {
	
	private String TAG = PurelandLog.getClassTag(UserCommonServiceImpl.class);
	
	private UserDAO userDAO = (UserDAO) SpringContextUtil.getBean(UserDAO.class.getSimpleName());

	@Override
	public User getUser(String account, String passwd) throws CoreException {
        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(passwd)) {
            throw new CoreException("account is null or passwd is null");
        }
        String keyId = User.generatorIdKey(account);
        try {
            String id = RString.get(keyId);
            if(StringUtils.isEmpty(id))
                return null;

            Long userId = Long.parseLong(id);
            User user = userDAO.getUser(userId);

            if (!account.equals(user.getAccount()) || !passwd.equals(user.getPasswd())) {
                PurelandLog.error(TAG, "account or passwd is wrong");
                return null;
            }
            return user;
        } catch (DBException e) {
            throw new CoreException(e);
        } catch (RedisException e) {
            throw new CoreException(e);
        }
    }
	
	@Override
	public Long addQuickUser(String machineId) throws CoreException {
		Long userId = null;
		try {
			User user = new User();
			userId = userDAO.addUser(user);
		} catch (DBException e) {
			error(e);
		}
		return userId;

	}

	@Override
	public void updateUser(User user) throws CoreException {
        try {
            userDAO.updateUser(user);
        } catch (DBException e) {
            throw new CoreException(e);
        }
    }

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	

}
