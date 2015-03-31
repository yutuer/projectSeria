package com.pureland.common.db.dao.redis;

import com.pureland.common.error.CoreException;
import org.apache.commons.lang3.StringUtils;

import com.pureland.common.component.cache.api.RString;
import com.pureland.common.component.cache.error.RedisException;
import com.pureland.common.db.data.User;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;

/**
 * 
 * @author qinpeirong
 *
 */
public class UserDAO extends RedisDAO {

	public Long addUser(User user) throws DBException {
		Long id = user.getId();
		String account = user.getAccount();
		String passwd = user.getPasswd();
		String telephone = user.getTelephone();
		String email = user.getEmail();

		try {
			if(id == null) {
				id = RString.generator(Entity.USER.getName());
			}
			
            String keyId = User.generatorFieldKey(id, Entity.User.ID.getName());
            String keyAccount = User.generatorFieldKey(id, Entity.User.ACCOUNT.getName());
			String keyPasswd = User.generatorFieldKey(id, Entity.User.PASSWD.getName());
			String keyTel = User.generatorFieldKey(id, Entity.User.TELEPHONE.getName());
			String keyEmail = User.generatorFieldKey(id, Entity.User.EMAIL.getName());
			
			RString.set(keyId, String.valueOf(id));
			if(StringUtils.isNotEmpty(account)) {
				RString.set(keyAccount, account);
			}
			if(StringUtils.isNotEmpty(passwd)) {
				RString.set(keyPasswd, passwd);
			}
			if(StringUtils.isNotEmpty(telephone)) {
				RString.set(keyTel, telephone);
			}
			if(StringUtils.isNotEmpty(email)) {
				RString.set(keyEmail, email);
			}
			
			addListCollection(String.valueOf(id));
			
		} catch (RedisException e) {
			throw new DBException(e.getMessage());
		}
		
		return id;
	}


    public User getUser(Long id) throws DBException {
        User user = new User();

        String keyAccount = User.generatorFieldKey(id, Entity.User.ACCOUNT.getName());
        String keyPasswd = User.generatorFieldKey(id, Entity.User.PASSWD.getName());
        String keyTel = User.generatorFieldKey(id, Entity.User.TELEPHONE.getName());

        try {
            user.setId(id);
            user.setAccount(RString.get(keyAccount));
            user.setPasswd(RString.get(keyPasswd));
            user.setTelephone(RString.get(keyTel));
        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }
        return user;
    }


    public void updateUser(User user) throws DBException {
        Long id = user.getId();
        String account = user.getAccount();
        String passwd = user.getPasswd();
        String telephone = user.getTelephone();
        if (id == null) {
            throw new DBException("id is null");
        }

        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(passwd)) {
            throw new DBException("account is null or passwd is null");
        }


        String keyId = User.generatorIdKey(account);
        String keyAccount = User.generatorFieldKey(id, Entity.User.ACCOUNT.getName());
        String keyPasswd = User.generatorFieldKey(id, Entity.User.PASSWD.getName());
        String keyTelephone = User.generatorFieldKey(id, Entity.User.TELEPHONE.getName());
        try {
            RString.set(keyId, String.valueOf(id));
            RString.set(keyAccount, account);
            RString.set(keyPasswd, passwd);
            if (StringUtils.isNotEmpty(telephone)) {
                RString.set(keyTelephone, telephone);
            }
        } catch (RedisException e) {
            throw new DBException(e.getMessage());
        }

    }
	
	public void addListCollection(String value) throws DBException {
		super.addListCollection(Entity.USER, value);
	}
}
