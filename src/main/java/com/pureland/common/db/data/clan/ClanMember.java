package com.pureland.common.db.data.clan;

import com.pureland.common.enums.Entity;
import com.pureland.common.util.DataObject;

/**
 * Created by Administrator on 2015/3/12.
 */
public class ClanMember extends DataObject {

    private static final long serialVersionUID = 3890637549059866497L;

    private Long playerId; //玩家id
    private String playerName; //玩家名称
    private Integer playerLevel; //玩家等级
    private Long clanId; //公会id
    private Integer clanPosition; //职位
    private Integer donatedSoldier; //近期捐兵
    private Integer receivedSoldier; //近期收兵
    private Long joinTime; //加入时间
    private Integer lastRank;//上次排名
    private Integer crown;//积分

    public static enum Field {
        ClanId, ClanMemberInfo
    }

    //USERRACE:1:ClanMemberInfo
    public static String getClanMemberIdKeyString(Long userRaceId) {
        return generatorFieldKey(Entity.PLAYER, userRaceId, Field.ClanMemberInfo.name());
    }

    //USERRACE:1:ClanId
    public static String getClanIdKeyString(Long userRaceId) {
        return generatorFieldKey(Entity.PLAYER, userRaceId, Field.ClanId.name());
    }

    public Long getClanId() {
        return clanId;
    }

    public void setClanId(Long clanId) {
        this.clanId = clanId;
    }

    public Integer getClanPosition() {
        return clanPosition;
    }

    public void setClanPosition(Integer clanPosition) {
        this.clanPosition = clanPosition;
    }

    public Integer getCrown() {
        return crown;
    }

    public void setCrown(Integer crown) {
        this.crown = crown;
    }

    public Integer getDonatedSoldier() {
        return donatedSoldier;
    }

    public void setDonatedSoldier(Integer donatedSoldier) {
        this.donatedSoldier = donatedSoldier;
    }

    public Long getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Long joinTime) {
        this.joinTime = joinTime;
    }

    public Integer getLastRank() {
        return lastRank;
    }

    public void setLastRank(Integer lastRank) {
        this.lastRank = lastRank;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Integer getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(Integer playerLevel) {
        this.playerLevel = playerLevel;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getReceivedSoldier() {
        return receivedSoldier;
    }

    public void setReceivedSoldier(Integer receivedSoldier) {
        this.receivedSoldier = receivedSoldier;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
