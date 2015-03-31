package com.pureland.core.service.clan;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.enums.clan.ClanPositionServerEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.clan.ClanBean;
import com.pureland.common.service.impl.ClanCommonServiceImpl;

import java.util.List;

/**
 * Created by Administrator on 2015/3/16.
 */
public class ModifyClanServiceImpl extends ClanCommonServiceImpl implements ClanLogicService {

    @Override
    public List<Clan> clanLogic(ClanBean clanBean) throws CoreException {
        Long userRaceId = clanBean.getClanBaseBean().getUserRaceId();
        ClanMember clanMember = super.getClanMemberInfo(userRaceId);
        if (clanMember == null) {
            throw new CoreException("没有公会");
        }
        //判断权限
        ClanPositionServerEnum clanPositionServerEnum = ClanPositionServerEnum.values()[clanMember.getClanPosition()];

        if (clanPositionServerEnum != ClanPositionServerEnum.ChairMan && clanPositionServerEnum != ClanPositionServerEnum.Vice_ChairMan) {
            throw new CoreException("权限不足");
        }

        clanBean.getClanBaseBean().setClanId(clanMember.getClanId());
        super.updateClanBean(clanBean);

        List<Clan> ret = Lists.newArrayList();
        return ret;
    }
}
