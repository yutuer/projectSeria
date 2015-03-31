package com.pureland.core.service.clan;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanBase;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.enums.clan.ClanJoinTypeServerEnum;
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
 * Created by Administrator on 2015/3/14.
 */
public class JoinClanServiceImpl extends ClanServiceImpl implements ClanLogicService {

    private UserRaceCommonService userRaceCommonService = (UserRaceCommonService) SpringContextUtil.getBean(UserRaceCommonServiceImpl.class.getSimpleName());

    @Override
    public List<Clan> clanLogic(ClanBean clanBean) throws CoreException {
        Long userRaceId = clanBean.getClanBaseBean().getUserRaceId();
        Long clanId = clanBean.getClanBaseBean().getClanId();

        Clan clan = joinClan(userRaceId, clanId);

        List<Clan> ret = Lists.newArrayList();
        ret.add(clan);
        return ret;
    }

    private Clan joinClan(Long userRaceId, Long clanId) throws CoreException {
        //先判断玩家有没有公会
        boolean isHasClan = isHasClan(userRaceId);
        if (isHasClan) {
            throw new CoreException("玩家已经有公会");
        }
        //判断人数满没满
        long memberNum = getClanMemberNum(clanId);
        if (memberNum >= 50) {
            throw new CoreException("人数已经满了");
        }

        UserRace userRace = userRaceCommonService.getUserRace(userRaceId);
        UserExt userExt = userRace.getUserExt();

        Clan clan = super.getClanInfoByClanId(clanId);
        //判断条件
        ClanBase clanBase = clan.getClanBase();
        if (clanBase.getJoinNeedCrown() != null) {
            //说明设置了条件
            if (userExt.getCrown() < clanBase.getJoinNeedCrown()) {
                throw new CoreException("您的crown不够, 您有%d, 需要%d", userExt.getCrown(), clanBase.getJoinNeedCrown());
            }
        }

        if (clanBase.getClanJoinType() != null) {
            ClanJoinTypeServerEnum clanJoinTypeServerEnum = ClanJoinTypeServerEnum.values()[clanBase.getClanJoinType()];
            if (clanJoinTypeServerEnum == ClanJoinTypeServerEnum.NoJoin) {
                throw new CoreException("公会不允许加入");
            }
        }

        //加入到公会id集合
        ClanMember clanMember = ClanHelper.makeClanMember(clanId, userRace, ClanPositionServerEnum.Normal_Member);
        super.addClanMember(clanMember);
        return getClanInfoByUserRaceId(userRaceId);
    }

}
