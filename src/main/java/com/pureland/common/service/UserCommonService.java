package com.pureland.common.service;

import com.pureland.common.db.data.User;
import com.pureland.common.error.CommonException;
import com.pureland.common.error.CoreException;

/**
 * 
 * @author qinpeirong
 *
 */
public interface UserCommonService {

    /**
     * 
     * @param account
     * @param passwd
     * @return
     * @throws CommonException
     */
    public User getUser(String account, String passwd) throws CoreException;
	
	/**
	 * 快速添加用户
	 * @param merchineId
	 * @throws CommonException
	 */
	public Long addQuickUser(String merchineId) throws CoreException;
	
	/**
	 * 
	 * @param user
	 * @throws CommonException
	 */
	public void updateUser(User user) throws CoreException;

}
