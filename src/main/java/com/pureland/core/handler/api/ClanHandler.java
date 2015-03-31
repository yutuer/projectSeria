package com.pureland.core.handler.api;

import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanBase;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.error.CoreException;
import com.pureland.common.protocal.ClanReqProtocal;
import com.pureland.common.protocal.ClanRespProtocal;
import com.pureland.common.protocal.ReqWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.enums.ClanEnumProtocal;
import com.pureland.common.protocal.vo.ClanBaseVOProtocal;
import com.pureland.common.protocal.vo.ClanMemberVOProtocal;
import com.pureland.common.protocal.vo.ClanSearchConditionVOProtocal;
import com.pureland.common.protocal.vo.ClanVOProtocal;
import com.pureland.common.service.bean.clan.ClanBean;
import com.pureland.common.service.helper.ClanHelper;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.service.clan.ClanLogicService;
import com.pureland.core.service.clan.ClanMap;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/3/12.
 */
public class ClanHandler extends RequestAPIHandler {

	private ClanMap clanMap = (ClanMap) SpringContextUtil.getBean(ClanMap.class.getSimpleName());

	@Override
	public RespWrapperProtocal.RespWrapper handler(ReqWrapperProtocal.ReqWrapper reqWrapper, String authToken, Long timestamp) throws CoreException {
		Long userId = authUser(authToken);
		Long userRaceId = getLastUserRaceId(userId);
		ClanReqProtocal.ClanReq clanReq = reqWrapper.getClanReq();

		ClanBaseVOProtocal.ClanBaseVO clanBaseVO = clanReq.getClanBaseVO();
		ClanSearchConditionVOProtocal.ClanSearchConditionVO clanSearchConditionVO = clanReq.getClanSearchConditionVO();
		Long operId = clanReq.getOperaId();
		String content = clanReq.getContent();
		Integer armySid = clanReq.getArmyId();

		ClanBean clanBean = ClanHelper.transferClanBase2ClanBean(userRaceId, clanBaseVO, clanSearchConditionVO, operId, content, armySid);

		String clanType = clanReq.getRequestType().name();
		ClanLogicService clanLogicService = clanMap.getClanMap().get(clanType);

		List<Clan> clans = clanLogicService.clanLogic(clanBean);

		RespWrapperProtocal.RespWrapper respWrapper = transferClanList(clans);
		return respWrapper;
	}

	private RespWrapperProtocal.RespWrapper transferClanList(List<Clan> clans) {
		RespWrapperProtocal.RespWrapper.Builder builder = RespWrapperProtocal.RespWrapper.newBuilder();

		ClanRespProtocal.ClanResp.Builder clanRespBuilder = ClanRespProtocal.ClanResp.newBuilder();
		if (CollectionUtils.isNotEmpty(clans)) {
			for (Clan clan : clans) {
				ClanVOProtocal.ClanVO.Builder clanVOBuilder = ClanVOProtocal.ClanVO.newBuilder();

				// clanBaseVOBuild
				ClanBaseVOProtocal.ClanBaseVO.Builder clanBaseVOBuilder = ClanBaseVOProtocal.ClanBaseVO.newBuilder();
				ClanBase clanBase = clan.getClanBase();
				if (clanBase != null) {
					if (clanBase.getClanId() != null) {
						clanBaseVOBuilder.setClanId(clanBase.getClanId());
					}
					if (clanBase.getClanName() != null) {
						clanBaseVOBuilder.setClanName(clanBase.getClanName());
					}
					if (clanBase.getClanLevel() != null) {
						clanBaseVOBuilder.setClanLevel(clanBase.getClanLevel());
					}
					if (clanBase.getClanDes() != null) {
						clanBaseVOBuilder.setClanDes(clanBase.getClanDes());
					}
					if (clanBase.getClanIcon() != null) {
						clanBaseVOBuilder.setClanIcon(clanBase.getClanIcon());
					}
					if (clanBase.getClanJoinType() != null) {
						clanBaseVOBuilder.setClanJoinType(ClanEnumProtocal.ClanJoinType.valueOf(clanBase.getClanJoinType()));
					}
					if (clanBase.getJoinNeedCrown() != null) {
						clanBaseVOBuilder.setJoinNeedCrown(clanBase.getJoinNeedCrown());
					}
					if (clanBase.getClanFightRateType() != null) {
						clanBaseVOBuilder.setClanFightRateType(ClanEnumProtocal.ClanFightRateType.valueOf(clanBase.getClanFightRateType()));
					}
					if (clanBase.getClanCountry() != null) {
						clanBaseVOBuilder.setClanCountry(clanBase.getClanCountry());
					}
					if (clanBase.getClanTotalCrown() != null) {
						clanBaseVOBuilder.setClanTotalCrown(clanBase.getClanTotalCrown());
					}
					if (clanBase.getClanMemberNum() != null) {
						clanBaseVOBuilder.setClanMemberNum(clanBase.getClanMemberNum());
					}
				}

				clanVOBuilder.setClanBaseVO(clanBaseVOBuilder.build());

				// clanMemberVOBuilder
				List<ClanMember> clanMembers = clan.getClanMemberList();
				if (CollectionUtils.isNotEmpty(clanMembers)) {
					ClanMemberVOProtocal.ClanMemberVO.Builder clanMemberBuilder = ClanMemberVOProtocal.ClanMemberVO.newBuilder();
					for (ClanMember clanMember : clanMembers) {
						if (clanMember.getPlayerId() != null) {
							clanMemberBuilder.setPlayerId(clanMember.getPlayerId());
						}
						if (clanMember.getPlayerName() != null) {
							clanMemberBuilder.setPlayerName(clanMember.getPlayerName());
						}
						if (clanMember.getPlayerLevel() != null) {
							clanMemberBuilder.setPlayerLevel(clanMember.getPlayerLevel());
						}
						if (clanMember.getClanPosition() != null) {
							clanMemberBuilder.setClanPosition(ClanEnumProtocal.ClanPosition.valueOf(clanMember.getClanPosition()));
						}
						if (clanMember.getDonatedSoldier() != null) {
							clanMemberBuilder.setDonatedSoldier(clanMember.getDonatedSoldier());
						}
						if (clanMember.getReceivedSoldier() != null) {
							clanMemberBuilder.setReceivedSoldier(clanMember.getReceivedSoldier());
						}
						if (clanMember.getCrown() != null) {
							clanMemberBuilder.setCrown(clanMember.getCrown());
						}
						if (clanMember.getJoinTime() != null) {
							clanMemberBuilder.setJoinTime(clanMember.getJoinTime());
						}
						if (clanMember.getLastRank() != null) {
							clanMemberBuilder.setLastRank(clanMember.getLastRank());
						}
						clanVOBuilder.addClanMembers(clanMemberBuilder.build());
					}
				}
				clanRespBuilder.setClearDonateTimeDiamond(clan.getClearBackUpCdDiamond());
				clanRespBuilder.addClanVO(clanVOBuilder.build());
			}
		}
		builder.setClanResp(clanRespBuilder.build());
		return builder.build();
	}
}
