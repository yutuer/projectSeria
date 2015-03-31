package com.pureland.common.service.bean.clan;

import com.pureland.common.enums.clan.ClanFightRateTypeServerEnum;
import com.pureland.common.enums.clan.ClanJoinTypeServerEnum;

/**
 * Created by Administrator on 2015/3/12.
 */
public class ClanBaseBean {
    private Long userRaceId;//玩家id
    private Long clanId; //公会id
    private String clanName;//公会名字
    private String clanDes; //公会描述
    private String clanIcon; //公会Icon
    private ClanJoinTypeServerEnum clanJoinType;//公会进入条件
    private int joinNeedCrown; //加入需要的积分
    private ClanFightRateTypeServerEnum clanFightRateType;//战争频率
    private String clanCountry; //公会所属国家

    public Long getUserRaceId() {
        return userRaceId;
    }

    public void setUserRaceId(Long userRaceId) {
        this.userRaceId = userRaceId;
    }

    public String getClanCountry() {
        return clanCountry;
    }

    public void setClanCountry(String clanCountry) {
        this.clanCountry = clanCountry;
    }

    public String getClanDes() {
        return clanDes;
    }

    public void setClanDes(String clanDes) {
        this.clanDes = clanDes;
    }


    public String getClanIcon() {
        return clanIcon;
    }

    public void setClanIcon(String clanIcon) {
        this.clanIcon = clanIcon;
    }

    public Long getClanId() {
        return clanId;
    }

    public void setClanId(Long clanId) {
        this.clanId = clanId;
    }

    public ClanFightRateTypeServerEnum getClanFightRateType() {
        return clanFightRateType;
    }

    public void setClanFightRateType(ClanFightRateTypeServerEnum clanFightRateType) {
        this.clanFightRateType = clanFightRateType;
    }

    public ClanJoinTypeServerEnum getClanJoinType() {
        return clanJoinType;
    }

    public void setClanJoinType(ClanJoinTypeServerEnum clanJoinType) {
        this.clanJoinType = clanJoinType;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public int getJoinNeedCrown() {
        return joinNeedCrown;
    }

    public void setJoinNeedCrown(int joinNeedCrown) {
        this.joinNeedCrown = joinNeedCrown;
    }
}
