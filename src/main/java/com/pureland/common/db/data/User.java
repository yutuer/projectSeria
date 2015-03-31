package com.pureland.common.db.data;

import java.util.List;

import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;

public class User extends DataObject {

	private static final long serialVersionUID = 8799515651436886728L;

	private Long id;
	private String account;
	private String passwd;
	private String telephone;
	private String email;

	private List<UserRace> userRaces;

	public static String generatorIdKey(String singleMark) {
		return generatorIdKey(Entity.USER, Entity.User.ID.getName(), singleMark);
	}

	public static String generatorFieldKey(Long id, String field) {
		return generatorFieldKey(Entity.USER, id, field);
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd
	 *            the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userRaces
	 */
	public List<UserRace> getUserRaces() {
		return userRaces;
	}

	/**
	 * @param userRaces
	 *            the userRaces to set
	 */
	public void setUserRaces(List<UserRace> userRaces) {
		this.userRaces = userRaces;
	}

}
