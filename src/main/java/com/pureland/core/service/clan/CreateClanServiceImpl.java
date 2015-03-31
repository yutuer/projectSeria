package com.pureland.core.service.clan;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.enums.clan.ClanPositionServerEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.UserRaceCommonService;
import com.pureland.common.service.bean.clan.ClanBean;
import com.pureland.common.service.helper.ChatHelper;
import com.pureland.common.service.helper.ClanHelper;
import com.pureland.common.service.impl.ClanCommonServiceImpl;
import com.pureland.common.service.impl.UserRaceCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.netty.websocket.ChannelGroupUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/3/12.
 */
public class CreateClanServiceImpl extends ClanServiceImpl implements ClanLogicService {

    private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());

    @Override
    public List<Clan> clanLogic(ClanBean clanBean) throws CoreException {
        Long userRaceId = clanBean.getClanBaseBean().getUserRaceId();
        Clan clan = ClanHelper.makeClanByClanBean(clanBean);
        Long clanId = super.addClan(clan);
        if (clanId == null) {
            throw new CoreException("名称已经存在,%s", clan.getClanBase().getClanName());
        }
        UserRace userRace = userRaceCommonService.getUserRace(userRaceId);

        ClanMember clanMember = ClanHelper.makeClanMember(clanId, userRace, ClanPositionServerEnum.ChairMan);
        super.addClanMember(clanMember);

        List<Clan> ret = Lists.newArrayList();
        ret.add(clan);
        return ret;
    }
}
