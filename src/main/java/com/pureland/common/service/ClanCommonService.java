package com.pureland.common.service;

import java.util.List;

import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.clan.ClanBean;

/**
 * Created by Administrator on 2015/3/12.
 */
public interface ClanCommonService {

	Clan getClanInfoByClanId(Long clanId) throws CoreException;

	Long addClan(Clan clan) throws CoreException;

	void addClanMember(ClanMember clanMember) throws CoreException;

	Clan getClanInfoByUserRaceId(Long userRaceId) throws CoreException;

	boolean isHasClan(Long userRaceId) throws CoreException;

	long getClanMemberNum(Long clanId) throws CoreException;

	Long quitClan(Long userRaceId) throws CoreException;

	ClanMember getClanMemberInfo(Long userRaceId) throws CoreException;

	List<ClanMember> getClanMemberInfos(Long clanId) throws CoreException;

	void removeClan(Long clanId) throws CoreException;

	void updateClanMember(ClanMember clanMember1) throws CoreException;

	public int getNumsByPostion(Long clanId, int postion) throws CoreException;

	void updateClanBean(ClanBean clanBean) throws CoreException;

	List<Clan> searchClan(String joinType, String clanName, String country, String clanLevel, String fightRate, String limitCrown, String numMinimum,
			String numMaxnium) throws CoreException;

	DonateArmy getUserRaceDonateArmy(Long userRaceId) throws CoreException;

	void setUserRaceDonateArmy(final DonateArmy donateArmy) throws CoreException;

	void resetUserRaceDonateArmy(final Long userRaceId) throws CoreException;
}
