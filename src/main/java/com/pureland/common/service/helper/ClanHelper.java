package com.pureland.common.service.helper;

import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import com.pureland.common.db.data.Building;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanBase;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.db.data.clan.DonateInfo;
import com.pureland.common.db.statics.EntityModel;
import com.pureland.common.enums.SubServerTypeEnum;
import com.pureland.common.enums.clan.ClanFightRateTypeServerEnum;
import com.pureland.common.enums.clan.ClanJoinTypeServerEnum;
import com.pureland.common.enums.clan.ClanPositionServerEnum;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.vo.ClanBaseVOProtocal;
import com.pureland.common.protocal.vo.ClanSearchConditionVOProtocal;
import com.pureland.common.service.BuildingCommonService;
import com.pureland.common.service.bean.clan.ClanBaseBean;
import com.pureland.common.service.bean.clan.ClanBean;
import com.pureland.common.service.bean.clan.ClanSearchBean;
import com.pureland.common.service.impl.BuildingCommonServiceImpl;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.common.util.parseExcel.MyUtil;
import com.pureland.core.init.EntityModelHelper;

/**
 * Created by Administrator on 2015/3/12.
 */
public class ClanHelper {

	public static final int Vicechairmannum = 4;
	public static final int OfficerNum = 10;
	public static final long BackUpCoolDown = 1 * MyUtil.MINUTE;

	private static BuildingCommonService buildingCommonService = (BuildingCommonService) SpringContextUtil.getBean(BuildingCommonServiceImpl.class
			.getSimpleName());

	public static RedisScript<List> script() {
		DefaultRedisScript<List> redisScript = new DefaultRedisScript<List>();
		redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("scripts/searchClan.lua")));
		redisScript.setResultType(List.class);
		return redisScript;
	}

	public static int getScoreByRank(int rank, int score) {
		double ret = score;
		if (rank > 40 && rank <= 50) {
			ret = score * 0.03;
		} else if (rank > 30 && rank <= 40) {
			ret = score * 0.10;
		} else if (rank > 20 && rank <= 30) {
			ret = score * 0.12;
		} else if (rank > 10 && rank <= 20) {
			ret = score * 0.25;
		} else if (rank > 0 && rank <= 10) {
			ret = score * 0.5;
		}
		return (int) ret;
	}

	public static Clan makeClanByClanBean(ClanBean clanBean) {
		Clan clan = new Clan();
		ClanBaseBean clanBaseBean = clanBean.getClanBaseBean();
		ClanBase clanBase = makeClanBaseByClanBaseBean(clanBaseBean);
		clan.setClanBase(clanBase);
		return clan;
	}

	public static ClanMember makeClanMember(Long clanId, UserRace userRace, ClanPositionServerEnum positionServerEnum) {
		ClanMember clanMember = new ClanMember();
		clanMember.setPlayerId(userRace.getId());
		clanMember.setPlayerName(userRace.getNickName());
		clanMember.setPlayerLevel(userRace.getUserExt().getLevel());
		clanMember.setClanId(clanId);
		clanMember.setCrown(userRace.getUserExt().getCrown());
		clanMember.setClanPosition(positionServerEnum.ordinal());
		clanMember.setJoinTime(System.currentTimeMillis());
		clanMember.setDonatedSoldier(0);
		clanMember.setReceivedSoldier(0);
		return clanMember;
	}

	private static ClanBase makeClanBaseByClanBaseBean(ClanBaseBean clanBaseBean) {
		ClanBase clanBase = new ClanBase();
		clanBase.setClanName(clanBaseBean.getClanName());
		clanBase.setClanLevel(1);
		clanBase.setClanCountry(clanBaseBean.getClanCountry());
		clanBase.setClanDes(clanBaseBean.getClanDes());
		clanBase.setClanFightRateType(clanBaseBean.getClanFightRateType().ordinal());
		clanBase.setClanJoinType(clanBaseBean.getClanJoinType().ordinal());
		clanBase.setClanIcon(clanBaseBean.getClanIcon());
		clanBase.setClanId(clanBaseBean.getClanId());
		clanBase.setJoinNeedCrown(clanBaseBean.getJoinNeedCrown());
		return clanBase;
	}

	public static ClanBean transferClanBase2ClanBean(Long userRaceId, ClanBaseVOProtocal.ClanBaseVO clanBase,
			ClanSearchConditionVOProtocal.ClanSearchConditionVO clanSearchConditionVO, Long operaId, String content, Integer armySid) {
		ClanBean clanBean = new ClanBean();

		ClanBaseBean clanBaseBean = new ClanBaseBean();
		clanBaseBean.setUserRaceId(userRaceId);
		clanBaseBean.setClanId(clanBase.getClanId());
		clanBaseBean.setClanDes(clanBase.getClanDes());
		clanBaseBean.setClanName(clanBase.getClanName());
		clanBaseBean.setClanIcon(clanBase.getClanIcon());
		clanBaseBean.setJoinNeedCrown(clanBase.getJoinNeedCrown());
		clanBaseBean.setClanCountry(clanBase.getClanCountry());

		clanBaseBean.setClanJoinType(ClanJoinTypeServerEnum.valueOf(clanBase.getClanJoinType().name()));
		clanBaseBean.setClanFightRateType(ClanFightRateTypeServerEnum.valueOf(clanBase.getClanFightRateType().name()));

		ClanSearchBean clanSearchBean = null;
		if (clanSearchConditionVO != null) {
			clanSearchBean = new ClanSearchBean();
			clanSearchBean.setClanName(clanSearchConditionVO.getClanName());
			clanSearchBean.setClanLevel(clanSearchConditionVO.getClanLevel());
			clanSearchBean.setCountry(clanSearchConditionVO.getCountry());
			clanSearchBean.setNumMinimum(clanSearchConditionVO.getNumMinimum());
			clanSearchBean.setNumMaximum(clanSearchConditionVO.getNumMaximum());
			clanSearchBean.setLimitCrown(clanSearchConditionVO.getLimitCrown());
			clanSearchBean.setClanFightRateType(ClanFightRateTypeServerEnum.valueOf(clanSearchConditionVO.getClanFightRateType().name()));
		}

		clanBean.setClanBaseBean(clanBaseBean);
		clanBean.setClanSearchBean(clanSearchBean);
		clanBean.setOperaId(operaId);
		clanBean.setContent(content);
		clanBean.setArmySid(armySid);
		return clanBean;
	}

	public static int getDonateArmyNum(DonateArmy donateArmy) {
		int ret = 0;
		for (DonateInfo donateInfo : donateArmy.getDonateInfoMap()) {
			ret += donateInfo.getNum();
		}
		return ret;
	}

	public static int getDonateArmyNowSpace(DonateArmy donateArmy) {
		int ret = 0;
		for (DonateInfo donateInfo : donateArmy.getDonateInfoMap()) {
			EntityModel em = EntityModelHelper.ENTITIES.get(donateInfo.getCid());
			ret += donateInfo.getNum() * em.getSpaceUse();
		}
		return ret;
	}

	public static Integer getDonateArmyMaxSpace(Long userRaceId) {
		int tmp = 0;
		try {
			Building building = buildingCommonService.getBuildingBySubType(userRaceId, SubServerTypeEnum.Federal).get(0);
			EntityModel entityModel = EntityModelHelper.ENTITIES.get(building.getCid());
			tmp = entityModel.getSpaceProvide();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return tmp;
	}

	public static Integer getClearBackupCd(long nextCanDonateTime) {
		long now = System.currentTimeMillis();

		return 20;
	}
}
