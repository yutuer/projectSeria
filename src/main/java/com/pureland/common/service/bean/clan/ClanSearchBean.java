package com.pureland.common.service.bean.clan;

import com.pureland.common.enums.clan.ClanFightRateTypeServerEnum;

/**
 * Created by Administrator on 2015/3/12.
 */
public class ClanSearchBean {

    private String clanName;//公会名称
    private int clanLevel;//公会等级
    private int numMinimum; //人数下限
    private int numMaximum;//人数上限
    private String country;//国家
    private int limitCrown;//最低分数
    private ClanFightRateTypeServerEnum clanFightRateType;//战争频率

    public ClanFightRateTypeServerEnum getClanFightRateType() {
        return clanFightRateType;
    }

    public void setClanFightRateType(ClanFightRateTypeServerEnum clanFightRateType) {
        this.clanFightRateType = clanFightRateType;
    }

    public int getClanLevel() {
        return clanLevel;
    }

    public void setClanLevel(int clanLevel) {
        this.clanLevel = clanLevel;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLimitCrown() {
        return limitCrown;
    }

    public void setLimitCrown(int limitCrown) {
        this.limitCrown = limitCrown;
    }

    public int getNumMaximum() {
        return numMaximum;
    }

    public void setNumMaximum(int numMaximum) {
        this.numMaximum = numMaximum;
    }

    public int getNumMinimum() {
        return numMinimum;
    }

    public void setNumMinimum(int numMinimum) {
        this.numMinimum = numMinimum;
    }
}
