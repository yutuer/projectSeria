package com.pureland.common.service;

import com.pureland.common.db.data.UserExt;
import com.pureland.common.error.CoreException;

/**
 * 
 * @author qinpeirong
 *
 */
public interface UserExtCommonService {

	public void initUserExt(Long userRaceId) throws CoreException;
	
	public UserExt getUserExt(Long userRaceId) throws CoreException;
	
	public void updateUserExt(UserExt userExt) throws CoreException;

}
