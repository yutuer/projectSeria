package com.pureland.core.service.clan;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.clan.ClanBean;
import com.pureland.common.service.bean.clan.ClanSearchBean;
import com.pureland.common.service.helper.ClanHelper;
import com.pureland.common.service.impl.ClanCommonServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2015/3/13.
 */
public class SearchClanServiceImpl extends ClanCommonServiceImpl implements ClanLogicService {

    private static final String SKIP = "SKIP";

    @Override
    public List<Clan> clanLogic(ClanBean clanBean) throws CoreException {
        ClanSearchBean clanSearchBean = clanBean.getClanSearchBean();

        String clanName = SKIP;
        if (StringUtils.isNotEmpty(clanSearchBean.getClanName())) {
            clanName = clanSearchBean.getClanName();
        }

        String country = SKIP;
        if (StringUtils.isNotEmpty(clanSearchBean.getCountry())) {
            country = clanSearchBean.getCountry();
        }

        String clanLevel = SKIP;
        if (clanSearchBean.getClanLevel() > 1) {
            clanLevel = String.valueOf(clanSearchBean.getClanLevel());
        }

        String fightRate = SKIP;
        if (clanSearchBean.getClanFightRateType().ordinal() > 0) {
            fightRate = String.valueOf(clanSearchBean.getClanFightRateType().ordinal());
        }

        String limitCrown = SKIP;
        if (clanSearchBean.getLimitCrown() > 0) {
            limitCrown = String.valueOf(clanSearchBean.getLimitCrown());
        }

        String numMinimum = SKIP;
        if (clanSearchBean.getNumMinimum() > 1) {
            numMinimum = String.valueOf(clanSearchBean.getNumMinimum());
        }

        String numMaxnium = SKIP;
        if (clanSearchBean.getNumMaximum() > 1 && clanSearchBean.getNumMaximum() < 50) {
            numMaxnium = String.valueOf(clanSearchBean.getNumMaximum());
        }

        String joinType = SKIP;

        List<Clan> ret = super.searchClan(joinType, clanName, country, clanLevel, fightRate, limitCrown, numMinimum, numMaxnium);

        return ret;
    }

}
