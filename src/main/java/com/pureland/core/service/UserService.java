package com.pureland.core.service;

import com.pureland.common.error.CoreException;


/**
 * 
 * @author qinpeirong
 *
 */
public interface UserService {

	public Long login(String machineId, Integer raceId, String nickName) throws CoreException;
	
	public Long register(String machineId, Integer raceId, String nickName) throws CoreException;
	
	public Long authLogin(String machineId, String account, String passwd) throws CoreException;

    public void bindAcccount(Long userId, String account, String passwd, String telephone) throws CoreException;

    public Boolean existsAccount(Long userId) throws CoreException;
}
