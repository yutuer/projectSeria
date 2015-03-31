package com.pureland.common.service.bean.clan;

/**
 * Created by Administrator on 2015/3/12.
 */
public class ClanBean {

    private ClanBaseBean clanBaseBean;
    private ClanSearchBean clanSearchBean;
    private Long operaId;
    private String content;
    private Integer armySid;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ClanBaseBean getClanBaseBean() {
        return clanBaseBean;
    }

    public void setClanBaseBean(ClanBaseBean clanBaseBean) {
        this.clanBaseBean = clanBaseBean;
    }

    public ClanSearchBean getClanSearchBean() {
        return clanSearchBean;
    }

    public void setClanSearchBean(ClanSearchBean clanSearchBean) {
        this.clanSearchBean = clanSearchBean;
    }

    public Long getOperaId() {
        return operaId;
    }

    public void setOperaId(Long operaId) {
        this.operaId = operaId;
    }

    public Integer getArmySid() {
        return armySid;
    }

    public void setArmySid(Integer armySid) {
        this.armySid = armySid;
    }
}
