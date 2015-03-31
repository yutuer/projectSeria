package com.pureland.core.service.clan;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.enums.clan.ClanPositionServerEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.clan.ClanBean;

/**
 * Created by Administrator on 2015/3/14.
 */
public class LeaveClanServiceImpl extends ClanServiceImpl implements ClanLogicService {

	@Override
	public List<Clan> clanLogic(ClanBean clanBean) throws CoreException {
		Long userRaceId = clanBean.getClanBaseBean().getUserRaceId();

		// 如果自己是公会会长,则需要提拔新公会会长
		ClanMember clanMember = super.getClanMemberInfo(userRaceId);
		Long clanId = super.quitClan(userRaceId);
		if (ClanPositionServerEnum.values()[clanMember.getClanPosition()] == ClanPositionServerEnum.ChairMan) {
			// 说明需要处理更换会长
			List<ClanMember> clanMembers = super.getClanMemberInfos(clanId);
			if (CollectionUtils.isNotEmpty(clanMembers)) {
				Collections.sort(clanMembers, new Comparator<ClanMember>() {
					@Override
					public int compare(ClanMember o1, ClanMember o2) {
						return o1.getClanPosition() - o2.getClanPosition();
					}
				});
				// 取出第一个
				ClanMember clanMember1 = clanMembers.get(0);
				clanMember1.setClanPosition(ClanPositionServerEnum.ChairMan.ordinal());
				super.updateClanMember(clanMember1);
			} else {
				// 清除掉
				removeClan(clanId);
			}
		}

		List<Clan> ret = Lists.newArrayList();
		return ret;
	}

}
