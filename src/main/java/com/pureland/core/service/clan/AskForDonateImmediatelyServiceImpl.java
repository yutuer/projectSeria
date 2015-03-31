package com.pureland.core.service.clan;

import java.util.List;

import com.google.common.collect.Lists;
import com.pureland.common.db.data.Resource;
import com.pureland.common.db.data.UserExt;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.ClanCommonService;
import com.pureland.common.service.ResourceCommonService;
import com.pureland.common.service.UserExtCommonService;
import com.pureland.common.service.bean.clan.ClanBean;
import com.pureland.common.service.helper.ClanHelper;
import com.pureland.common.service.impl.ClanCommonServiceImpl;
import com.pureland.common.service.impl.ResourceCommonServiceImpl;
import com.pureland.common.service.impl.UserExtCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;

public class AskForDonateImmediatelyServiceImpl extends ClanCommonServiceImpl implements ClanLogicService {

	private ClanCommonService clanCommonService = (ClanCommonService) SpringContextUtil.getBean(ClanCommonServiceImpl.class.getSimpleName());
	private UserExtCommonService userExtCommonService = (UserExtCommonService) SpringContextUtil.getBean(UserExtCommonServiceImpl.class.getSimpleName());
	private ResourceCommonService resourceCommonService = (ResourceCommonService) SpringContextUtil.getBean(ResourceCommonServiceImpl.class.getSimpleName());

	@Override
	public List<Clan> clanLogic(ClanBean clanBean) throws CoreException {
		Long userRaceId = clanBean.getClanBaseBean().getUserRaceId();
		// 判断是否在公会中
		Clan clan = clanCommonService.getClanInfoByUserRaceId(userRaceId);
		if (clan == null) {
			throw new CoreException("不在公会中!!!");
		}
		long now = System.currentTimeMillis();
		DonateArmy donateArmy = clanCommonService.getUserRaceDonateArmy(userRaceId);
		if (donateArmy == null || donateArmy.getNextCanDonateTime() < now) {
			throw new CoreException("您不在cd中,不需要清除!!!");
		}
		// 判断钱是否足够
		UserExt userExt = userExtCommonService.getUserExt(userRaceId);
		Resource diamondResource = userExt.getResourcesForMap().get(ResourceServerTypeEnum.Diamond);
		int need = ClanHelper.getClearBackupCd(donateArmy.getNextCanDonateTime());
		if (diamondResource.getCount() < need) {
			throw new CoreException("钻石不足");
		}

		// 扣钱
		diamondResource.setCount(diamondResource.getCount() - need);
		resourceCommonService.updateResource(diamondResource);

		// 设置时间
		donateArmy.setNextCanDonateTime(now);
		clanCommonService.setUserRaceDonateArmy(donateArmy);

		Clan c = new Clan();
		c.setClearBackUpCdDiamond(need);
		List<Clan> ret = Lists.newArrayList();
		ret.add(c);
		return ret;
	}

}
