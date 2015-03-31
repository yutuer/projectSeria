package com.pureland.common.db.data.clan;

import com.pureland.common.util.DataObject;

/**
 * Created by Administrator on 2015/3/12.
 */
public class ClanBase extends DataObject {
    private static final long serialVersionUID = -5925579928610279403L;

    private Long ClanId; //公会id
    private String ClanName;//公会名字
    private Integer ClanLevel; //公会等级
    private String ClanDes; //公会描述
    private String ClanIcon; //公会Icon
    private Integer clanJoinType;//公会进入条件
    private Integer joinNeedCrown; //加入需要的积分
    private Integer clanFightRateType;//战争频率
    private String ClanCountry; //公会所属国家
    private Integer ClanTotalCrown; //公会积分
    private Integer ClanMemberNum;//公会现有人数

    public String getClanCountry() {
        return ClanCountry;
    }

    public void setClanCountry(String clanCountry) {
        ClanCountry = clanCountry;
    }

    public String getClanDes() {
        return ClanDes;
    }

    public void setClanDes(String clanDes) {
        ClanDes = clanDes;
    }

    public Integer getClanFightRateType() {
        return clanFightRateType;
    }

    public void setClanFightRateType(Integer clanFightRateType) {
        this.clanFightRateType = clanFightRateType;
    }

    public String getClanIcon() {
        return ClanIcon;
    }

    public void setClanIcon(String clanIcon) {
        ClanIcon = clanIcon;
    }

    public Long getClanId() {
        return ClanId;
    }

    public void setClanId(Long clanId) {
        ClanId = clanId;
    }

    public Integer getClanJoinType() {
        return clanJoinType;
    }

    public void setClanJoinType(Integer clanJoinType) {
        this.clanJoinType = clanJoinType;
    }

    public Integer getClanLevel() {
        return ClanLevel;
    }

    public void setClanLevel(Integer clanLevel) {
        ClanLevel = clanLevel;
    }

    public Integer getClanMemberNum() {
        return ClanMemberNum;
    }

    public void setClanMemberNum(Integer clanMemberNum) {
        ClanMemberNum = clanMemberNum;
    }

    public String getClanName() {
        return ClanName;
    }

    public void setClanName(String clanName) {
        ClanName = clanName;
    }

    public Integer getClanTotalCrown() {
        return ClanTotalCrown;
    }

    public void setClanTotalCrown(Integer clanTotalCrown) {
        ClanTotalCrown = clanTotalCrown;
    }

    public Integer getJoinNeedCrown() {
        return joinNeedCrown;
    }

    public void setJoinNeedCrown(Integer joinNeedCrown) {
        this.joinNeedCrown = joinNeedCrown;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
