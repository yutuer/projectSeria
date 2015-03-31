package com.pureland.common.service.bean.chat;

/**
 * Created by Administrator on 2015/3/19.
 */
public class ChatClanBase {
    private Long clanId;
    private String clanName;
    private String clanIcon;
    private Integer clanLevel;
    private Integer clanPostion;

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

    public Integer getClanLevel() {
        return clanLevel;
    }

    public void setClanLevel(Integer clanLevel) {
        this.clanLevel = clanLevel;
    }

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public Integer getClanPostion() {
        return clanPostion;
    }

    public void setClanPostion(Integer clanPostion) {
        this.clanPostion = clanPostion;
    }
}
