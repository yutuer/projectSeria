package com.pureland.common.db.data.clan;

import java.util.List;

import com.google.common.collect.Lists;
import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;

/**
 * Created by Administrator on 2015/3/12.
 */
public class Clan extends DataObject {

	private static final long serialVersionUID = -1630204836236216761L;

	private ClanBase clanBase;
	private List<ClanMember> clanMemberList = Lists.newArrayList();

	private int clearBackUpCdDiamond;

	public int getClearBackUpCdDiamond() {
		return clearBackUpCdDiamond;
	}

	public void setClearBackUpCdDiamond(int clearBackUpCdDiamond) {
		this.clearBackUpCdDiamond = clearBackUpCdDiamond;
	}

	public static String getClanName2IdKeyString() {
		return Entity.CLANNAME.name() + Entity.SEPARATOR + Entity.CLANID.name();
	}

	public static enum Field {
		ClanBaseInfo, ClanMembers, TotalCrown, MemberNum, ClanLevel;
	}

	public static String getClanIdKeyString(Long clanId) {
		return generatorIdKey(Entity.CLAN, Clan.Field.ClanBaseInfo.name(), String.valueOf(clanId));
	}

	public static String getClanMemberIdKeyString(Long clanId) {
		return generatorFieldKey(Entity.CLAN, clanId, Clan.Field.ClanMembers.name());
	}

	public static String getClanTotalCrownIdKeyString(Long clanId) {
		return generatorFieldKey(Entity.CLAN, clanId, Clan.Field.TotalCrown.name());
	}

	public static String getClanMemberNumIdKeyString(Long clanId) {
		return generatorFieldKey(Entity.CLAN, clanId, Field.MemberNum.name());
	}

	public static String getClanLevelIdKeyString(Long clanId) {
		return generatorFieldKey(Entity.CLAN, clanId, Field.ClanLevel.name());
	}

	/**
	 * @return 返回所有clanId
	 */
	public static String getTotalClanIdIdKeyString() {
		return Entity.CLANID.name();
	}

	public static String getTotalClanNameIdKeyString() {
		return Entity.CLANNAME.name();
	}

	public static String getCountryIdKeyString(String clanCountry) {
		return generatorFieldKey(Entity.CLANCOUNTRY, clanCountry, "");
	}

	public static String getFightRateIdKeyString(int battleFrequency) {
		return generatorFieldKey(Entity.CLANFIGHTRATE, String.valueOf(battleFrequency), "");
	}

	public static String getJoinTypeIdKeyString(int joinType) {
		return generatorFieldKey(Entity.ClANJOINTYPE, String.valueOf(joinType), "");
	}

	public static String getDonateArmyKeyIdString(Long userRaceId) {
		return generatorFieldKey(Entity.PLAYER, userRaceId, Entity.DONATEARMY.name());
	}

	public ClanBase getClanBase() {
		return clanBase;
	}

	public void setClanBase(ClanBase clanBase) {
		this.clanBase = clanBase;
	}

	public List<ClanMember> getClanMemberList() {
		return clanMemberList;
	}

	public void setClanMemberList(List<ClanMember> clanMemberList) {
		this.clanMemberList = clanMemberList;
	}

}
