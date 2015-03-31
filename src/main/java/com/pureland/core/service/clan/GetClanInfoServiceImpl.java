package com.pureland.core.service.clan;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.clan.ClanBean;
import com.pureland.common.service.impl.ClanCommonServiceImpl;

import java.util.List;

/**
 * Created by Administrator on 2015/3/12.
 */
public class GetClanInfoServiceImpl extends ClanCommonServiceImpl implements ClanLogicService {

    @Override
    public List<Clan> clanLogic(ClanBean clanBean) throws CoreException {
        Long clanId = clanBean.getClanBaseBean().getClanId();
        //判断玩家有没有公会
        Clan clan = super.getClanInfoByClanId(clanId);
        if (clan == null) {
            throw new CoreException("根据%d没有找到clan", clanId);
        }
        List<Clan> ret = Lists.newArrayList();
        ret.add(clan);
        return ret;
    }
}
