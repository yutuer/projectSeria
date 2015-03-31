package com.pureland.common.protocal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.pureland.common.protocal.BaseReqProtocal.BaseReq;
import com.pureland.common.protocal.BattleHistoryReqProtocal.BattleHistoryReq;
import com.pureland.common.protocal.BattleReplayReqProtocal.BattleReplayReq;
import com.pureland.common.protocal.BattleResultReqProtocal.BattleResultReq;
import com.pureland.common.protocal.BuildingCompleteReqProtocal.BuildingCompleteReq;
import com.pureland.common.protocal.BuildingConsReqProtocal.BuildingConsReq;
import com.pureland.common.protocal.BuildingUpgradeReqProtocal.BuildingUpgradeReq;
import com.pureland.common.protocal.CampReqProtocal.CampReq;
import com.pureland.common.protocal.GatherReqProtocal.GatherReq;
import com.pureland.common.protocal.NoAuthLoginReqProtocal.NoAuthLoginReq;
import com.pureland.common.protocal.ProductionReqProtocal.ProductionReq;
import com.pureland.common.protocal.ProductionReqProtocal.ProductionReq.ProductionRequestType;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper.RequestType;
import com.pureland.common.protocal.vo.*;
import com.pureland.common.protocal.vo.ArmyVOProtocal.ArmyVO;
import com.pureland.common.protocal.vo.BattleResultVOProtocal.BattleResultVO;
import com.pureland.common.protocal.vo.BattleResultVOProtocal.BattleResultVO.Builder;
import com.pureland.common.protocal.vo.BuildingVOProtocal.BuildingVO;
import com.pureland.common.protocal.vo.ProductionItemVOProtocal.ProductionItemVO;
import com.pureland.common.protocal.vo.ResourceTypeProtocal.ResourceType;
import com.pureland.common.protocal.vo.ResourceVOProtocal.ResourceVO;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		output();
		input();
		output1();
		input1();
//		output2();
//        input2();
//		output3();
//        input3();
//        output4();
//        input4();
		output5();
		input5();
//		output6();
//		input6();
		output7();
		input7();
		output8();
		input8();
		output9();
		input9();
//        output10();
//        input10();
//        output11();
//        input11();
//        output12();
//        input12();
        output13();
        input13();
	}
	
	public static void output() throws IOException {
		NoAuthLoginReq noAuthLoginReq = NoAuthLoginReqProtocal.NoAuthLoginReq.newBuilder()
				                                                             .setMachineId("1000000001")
				                                                             .setRaceType(2)
                                                                             .setUserName("mmmm2")
				                                                             .build();
		ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.NoAuthLogin)
		                                          .setNoAuthLoginReq(noAuthLoginReq)
		                                          .build();
		BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
                								 .setReqWrapper(reqWrapper)
                								 .build();
		FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/noAuthLoginReq.txt");
		baseReq.writeTo(output);
		output.close();
		
	}
	
	
	public static void input() throws IOException {
		FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/noAuthLoginReq.txt");
		BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
		ReqWrapper reqWrapper = baseReq.getReqWrapper();
		RequestType requestType = reqWrapper.getRequestType();
		NoAuthLoginReq noAuthLoginReq = reqWrapper.getNoAuthLoginReq();
		String machineId = noAuthLoginReq.getMachineId();
		int raceType = noAuthLoginReq.getRaceType();
		
		System.out.println(requestType.toString());
		System.out.println(machineId);
		System.out.println(raceType);
	}
	
	public static void output1() throws IOException {
		CampReq campReq = CampReqProtocal.CampReq.newBuilder().setUserId(1).build();
		ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.Camp)
		                                          .setCampReq(campReq)
		                                          .build();
		BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
//                .setAuthToken("r96xz4hbpS0bMBrT7VtU4g==")
							   .setAuthToken("r96xz4hbpS1WkT1OSKtUlQ==")
		                       .setReqWrapper(reqWrapper)
		                       .build();
		
		FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/campResp.txt");
		baseReq.writeTo(output);
		output.close();
	}
	
	public static void input1() throws IOException {
		FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/campResp.txt");
		BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
		String authToken = baseReq.getAuthToken();
		ReqWrapper reqWrapper = baseReq.getReqWrapper();
		RequestType requestType = reqWrapper.getRequestType();
		CampReq campReq = reqWrapper.getCampReq();
		
		System.out.println(authToken);
		System.out.println(requestType.toString());
		System.out.println(campReq.getUserId());
	}
	
	
	public static void output2() throws IOException {
		BuildingVO buildingVO = BuildingVOProtocal.BuildingVO.newBuilder()
				                                             .setSid(3)
		                                                     .setCid(11201)
		                                                     .setX(80)
		                                                     .setY(80)
		                                                     .build();
		BuildingConsReq buildingConsReq = BuildingConsReqProtocal.BuildingConsReq.newBuilder().setBuildingVO(buildingVO).build();
		ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.BuildingCons)
				                                                          .setBuildingConsReq(buildingConsReq)
				                                                          .build();
		BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
                								 .setAuthToken("r96xz4hbpS0bMBrT7VtU4g==")
                								 .setReqWrapper(reqWrapper)
                								 .build();
		FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/mine.txt");
		baseReq.writeTo(output);
		output.close();

	}
	
	
	public static void input2() throws IOException {
		FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/mine.txt");
		BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
		String authToken = baseReq.getAuthToken();
		ReqWrapper reqWrapper = baseReq.getReqWrapper();
		RequestType requestType = reqWrapper.getRequestType();
		BuildingConsReq buildingConsReq = reqWrapper.getBuildingConsReq();
		BuildingVO buildingVO = buildingConsReq.getBuildingVO();
		
		System.out.println(authToken);
		System.out.println(requestType.toString());
		System.out.println(buildingVO.getSid());
		System.out.println(buildingVO.getCid());
		System.out.println(buildingVO.getX());
		System.out.println(buildingVO.getY());
	}
	
	
	public static void output3() throws IOException {
		BuildingCompleteReq buildingCompleteReq = BuildingCompleteReqProtocal.BuildingCompleteReq.newBuilder().setSid(3).build();
		ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.BuildingUpgrade)
                														  .setBuildingCompleteReq(buildingCompleteReq)
                														  .build();		
		BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
												 .setAuthToken("r96xz4hbpS0bMBrT7VtU4g==")
												 .setReqWrapper(reqWrapper)
												 .build();
		FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/buildComplete.txt");
		baseReq.writeTo(output);
		output.close();
	}
	
	public static void input3() throws IOException {
		FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/buildComplete.txt");
		BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
		String authToken = baseReq.getAuthToken();
		ReqWrapper reqWrapper = baseReq.getReqWrapper();
		RequestType requestType = reqWrapper.getRequestType();
		BuildingCompleteReq buildingCompleteReq = reqWrapper.getBuildingCompleteReq();
		long sid = buildingCompleteReq.getSid();
		
		System.out.println(authToken);
		System.out.println(requestType.toString());
		System.out.println(sid);
	}
	
	
	public static void output4() throws IOException {
		BuildingUpgradeReq buildingUpgradeReq = BuildingUpgradeReqProtocal.BuildingUpgradeReq.newBuilder().setSid(3).build();
		ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.BuildingUpgrade)
				  														  .setBuildingUpReq(buildingUpgradeReq)
				  														  .build();
		BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
				 .setAuthToken("r96xz4hbpS0bMBrT7VtU4g==")
				 .setReqWrapper(reqWrapper)
				 .build();
		FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/buildUpgrade.txt");
		baseReq.writeTo(output);
		output.close();
	}
	
	public static void input4() throws IOException {
		FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/buildUpgrade.txt");
		BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
		String authToken = baseReq.getAuthToken();
		ReqWrapper reqWrapper = baseReq.getReqWrapper();
		RequestType requestType = reqWrapper.getRequestType();
		BuildingUpgradeReq buildingUpReq = reqWrapper.getBuildingUpReq();
		long sid = buildingUpReq.getSid();
		
		System.out.println(authToken);
		System.out.println(requestType.toString());
		System.out.println(sid);
	}
	
	public static void output5() throws IOException {//12701
		ProductionItemVO productionItemVO = ProductionItemVOProtocal.ProductionItemVO.newBuilder().setCid(12702).setCount(10).build();
		ProductionReq productionReq = ProductionReqProtocal.ProductionReq.newBuilder().setProductionRequestType(ProductionRequestType.Complete)
		                                                .setSid(3)
		                                                .setProductionItemVO(productionItemVO)
		                                                .setTime(System.currentTimeMillis())
		                                                .build();
		ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.Production)
				                                                          .setProductionReq(productionReq)
				                                                          .build();
		BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
				 .setAuthToken("r96xz4hbpS0bMBrT7VtU4g==")
//                 .setAuthToken("r96xz4hbpS1WkT1OSKtUlQ==")
				 .setSequenceId(1)
				 .setReqWrapper(reqWrapper)
				 .build();
		FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/product.txt");
		baseReq.writeTo(output);
		output.close();
		
	}
	
	public static void input5() throws IOException {
		FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/product.txt");
		BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
		String authToken = baseReq.getAuthToken();
		int sequenceId = baseReq.getSequenceId();
		ReqWrapper reqWrapper = baseReq.getReqWrapper();
		RequestType requestType = reqWrapper.getRequestType();
		ProductionReq productionReq = reqWrapper.getProductionReq();
		ProductionRequestType productionRequestType = productionReq.getProductionRequestType();
		long sid = productionReq.getSid();
		ProductionItemVO productionItemVO = productionReq.getProductionItemVO();
		long time = productionReq.getTime();
		int cid = productionItemVO.getCid();
		int count = productionItemVO.getCount();
		
		System.out.println(authToken);
		System.out.println(sequenceId);
		System.out.println(requestType.toString());
		System.out.println(time);
		System.out.println(productionRequestType.toString());
		System.out.println(sid);
		System.out.println(cid);
		System.out.println(count);
	}
	
	public static void output6() throws IOException {
		ResourceVO resourceVO = ResourceVOProtocal.ResourceVO.newBuilder().setResourceType(ResourceType.Gold)
		                                          						  .setResourceCount(20)
		                                          						  .build();
		GatherReq gatherReq = GatherReqProtocal.GatherReq.newBuilder().setSid(2)
		                                        .setResourceVO(resourceVO)
		                                        .setGatherTime(System.currentTimeMillis())
		                                        .build();
		ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.Gather)
                                                                          .setGatherReq(gatherReq)
                                                                          .build();
		BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
				 .setAuthToken("r96xz4hbpS0bMBrT7VtU4g==")
				 .setReqWrapper(reqWrapper)
				 .build();
		FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/gather.txt");
		baseReq.writeTo(output);
		output.close();
	}
	
	public static void input6() throws IOException {
		FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/gather.txt");
		BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
		String authToken = baseReq.getAuthToken();
		ReqWrapper reqWrapper = baseReq.getReqWrapper();
		RequestType requestType = reqWrapper.getRequestType();
		GatherReq gatherReq = reqWrapper.getGatherReq();
		long sid = gatherReq.getSid();
		long gatherTime = gatherReq.getGatherTime();
		ResourceVO resourceVO = gatherReq.getResourceVO();
		ResourceType resourceType = resourceVO.getResourceType();
		int resourceCount = resourceVO.getResourceCount();
		
		System.out.println(authToken);
		System.out.println(requestType.toString());
		System.out.println(sid);
		System.out.println(gatherTime);
		System.out.println(resourceType.toString());
		System.out.println(resourceCount);
	}
	
	public static void output7() throws IOException {
		ArmyVO usedArmyVO1 = ArmyVOProtocal.ArmyVO.newBuilder().setCid(12701).setAmount(10).build();
		ArmyVO usedArmyVO2 = ArmyVOProtocal.ArmyVO.newBuilder().setCid(12702).setAmount(10).build();
//		ArmyVO usedArmyVO3 = ArmyVOProtocal.ArmyVO.newBuilder().setCid(3).setAmount(20).build();
		
		ArmyVO killedDefendArmyVO1 = ArmyVOProtocal.ArmyVO.newBuilder().setCid(12701).setAmount(1).build();
		ArmyVO killedDefendArmyVO2 = ArmyVOProtocal.ArmyVO.newBuilder().setCid(12702).setAmount(2).build();
		
		ResourceVO goldResource = ResourceVOProtocal.ResourceVO.newBuilder().setResourceType(ResourceType.Gold).setResourceCount(12).build();
		ResourceVO oilResource = ResourceVOProtocal.ResourceVO.newBuilder().setResourceType(ResourceType.Oil).setResourceCount(35).build();
		
		Builder battleResultBuilder = BattleResultVOProtocal.BattleResultVO.newBuilder().setPercentage(80)
		                                                  .setStar(10)
		                                                  .setUseDonatedArmy(true)
		                                                  .setRewardCrown(50)
		                                                  .setRewardGoldByCrownLevel(30)
		                                                  .setRewardOilByCrownLevel(20)
		                                                  .setTimestamp(System.currentTimeMillis())
		                                                  .setPeerId(2);
		battleResultBuilder.addUsedArmies(usedArmyVO1);
		battleResultBuilder.addUsedArmies(usedArmyVO2);
//		battleResultBuilder.addUsedArmies(usedArmyVO3);
		battleResultBuilder.addKilledDefenderDonatedArmies(killedDefendArmyVO1);
		battleResultBuilder.addKilledDefenderDonatedArmies(killedDefendArmyVO2);
		battleResultBuilder.addStolenResources(goldResource);
		battleResultBuilder.addStolenResources(oilResource);
		BattleResultVO battleResultVO = battleResultBuilder.build();


        BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO battleOperationPlaceSoldierVO = BattleOperationPlaceSoldierVOProtocal.BattleOperationPlaceSoldierVO.newBuilder().setCid(100)
                .setX(50)
                .setY(60)
                .build();

        BattleOperationVOProtocal.BattleOperationVO battleOperationVO = BattleOperationVOProtocal.BattleOperationVO.newBuilder().setFrame(100)
                .setBattleOperationType(BattleOperationVOProtocal.BattleOperationVO.BattleOperationType.PlaceSoldier)
                .setBattleOperationPlaceSoldierVO(battleOperationPlaceSoldierVO)
                .build();

        PlayerVOProtocal.PlayerVO playerVO = PlayerVOProtocal.PlayerVO.newBuilder().setUserId(new Long("10")).build();
        CampVOProtocal.CampVO attacker = CampVOProtocal.CampVO.newBuilder().setPlayer(playerVO).build();
        CampVOProtocal.CampVO defender = CampVOProtocal.CampVO.newBuilder().setPlayer(playerVO).build();

        BattleReplayVOProtocal.BattleReplayVO.Builder battleReplayBuilder = BattleReplayVOProtocal.BattleReplayVO.newBuilder();
        battleReplayBuilder.setSeed(new Long("1"));
        battleReplayBuilder.setBattleDuration(1);
        battleReplayBuilder.setBattleStartTime(System.currentTimeMillis());
        battleReplayBuilder.addBattleInputs(battleOperationVO);
        battleReplayBuilder.setAttacker(attacker);
        battleReplayBuilder.setDefender(defender);
        BattleReplayVOProtocal.BattleReplayVO battleReplayVO = battleReplayBuilder.build();


        BattleResultReq battleResultReq = BattleResultReqProtocal.BattleResultReq.newBuilder().setBattleResultVO(battleResultVO)
                                                                                              .setBattleReplayVO(battleReplayVO)
                                                                                              .build();
		
		ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.BattleResult)
                														  .setBattleResultReq(battleResultReq)
                														  .build();
		BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
                .setAuthToken("r96xz4hbpS0bMBrT7VtU4g==")
//                                                 .setAuthToken("r96xz4hbpS1WkT1OSKtUlQ==")
												 .setReqWrapper(reqWrapper)
												 .build();
		FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/battleResult.txt");
		baseReq.writeTo(output);
		output.close();
		
	}
	
	public static void input7() throws IOException {
		FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/battleResult.txt");
		BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
		String authToken = baseReq.getAuthToken();
		ReqWrapper reqWrapper = baseReq.getReqWrapper();
		RequestType requestType = reqWrapper.getRequestType();
		BattleResultReq battleResultReq = reqWrapper.getBattleResultReq();
		BattleResultVO battleResultVO = battleResultReq.getBattleResultVO();
		
		System.out.println(battleResultVO.toString());
	}
	
	public static void output8() throws IOException {
		BattleHistoryReq battleHistoryReq = BattleHistoryReqProtocal.BattleHistoryReq.newBuilder().build();
		ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.BattleHistory)
				  														  .setBattleHistoryReq(battleHistoryReq)
				  														  .build();
		BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
//                .setAuthToken("r96xz4hbpS0bMBrT7VtU4g==")
                .setAuthToken("r96xz4hbpS1WkT1OSKtUlQ==")
				  								 .setReqWrapper(reqWrapper)
				  								 .build();
		FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/battleHistory.txt");
		baseReq.writeTo(output);
		output.close();
	}
	
	public static void input8() throws IOException {
		FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/battleHistory.txt");
		BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
		String authToken = baseReq.getAuthToken();
		ReqWrapper reqWrapper = baseReq.getReqWrapper();
		RequestType requestType = reqWrapper.getRequestType();
		BattleHistoryReq battleHistoryReq = reqWrapper.getBattleHistoryReq();
		System.out.println(authToken);
		System.out.println(requestType.toString());
	}
	
	public static void output9() throws IOException {
		BattleReplayReq battleReplayReq = BattleReplayReqProtocal.BattleReplayReq.newBuilder().setReplayId(1).build();
		ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.BattleReplay)
		                                          .setBattleReplayReq(battleReplayReq)
		                                          .build();
		BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
					 							 .setAuthToken("r96xz4hbpS0bMBrT7VtU4g==")
					 							 .setReqWrapper(reqWrapper)
					 							 .build();
		FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/battleReplay.txt");
		baseReq.writeTo(output);
		output.close();
	}
	
	public static void input9() throws IOException {
		FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/battleReplay.txt");
		BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
		String authToken = baseReq.getAuthToken();
		ReqWrapper reqWrapper = baseReq.getReqWrapper();
		RequestType requestType = reqWrapper.getRequestType();
		BattleReplayReq battleReplayReq = reqWrapper.getBattleReplayReq();
		long replayId = battleReplayReq.getReplayId();
		System.out.println(replayId);
		
	}

    public static void output10() throws IOException {
        CompleteInfoReqProtocal.CompleteInfoReq completeInfoReq = CompleteInfoReqProtocal.CompleteInfoReq.newBuilder().setAccount("abc")
                .setPasswd("123")
                .setPhoneNum("15811187605")
                .build();
        ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.CompleteInfo)
                .setCompleteInfoReq(completeInfoReq)
                .build();
        BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
                .setAuthToken("r96xz4hbpS0bMBrT7VtU4g==")
                .setReqWrapper(reqWrapper)
                .build();
        FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/completeInfo.txt");
        baseReq.writeTo(output);
        output.close();
    }

    public static void input10() throws IOException {
        FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/completeInfo.txt");
        BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
        String authToken = baseReq.getAuthToken();
        ReqWrapper reqWrapper = baseReq.getReqWrapper();
        RequestType requestType = reqWrapper.getRequestType();
        CompleteInfoReqProtocal.CompleteInfoReq completeInfoReq = reqWrapper.getCompleteInfoReq();
        System.out.println(completeInfoReq.getAccount());
        System.out.println(completeInfoReq.getPasswd());
        System.out.println(completeInfoReq.getPhoneNum());
    }

    public static void output11() throws IOException {
        ChangeAccountReqProtocal.ChangeAccountReq changeAccountReq = ChangeAccountReqProtocal.ChangeAccountReq.newBuilder().build();
        ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.ChangeAccount)
                .setChangeAccountReq(changeAccountReq)
                .build();
        BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
                .setAuthToken("r96xz4hbpS0bMBrT7VtU4g==")
                .setReqWrapper(reqWrapper)
                .build();
        FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/checkAccount.txt");
        baseReq.writeTo(output);
        output.close();
    }

    public static void input11() throws IOException {
        FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/checkAccount.txt");
        BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
        String authToken = baseReq.getAuthToken();
        ReqWrapper reqWrapper = baseReq.getReqWrapper();
        RequestType requestType = reqWrapper.getRequestType();
        System.out.println(authToken);
        System.out.println(requestType.toString());
    }

    public static void output12() throws IOException {
        AuthLoginReqProtocal.AuthLoginReq authLoginReq = AuthLoginReqProtocal.AuthLoginReq.newBuilder().setMachineId("1000000000")
                .setAccount("abc")
                .setPasswd("123")
                .build();
        ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.AuthLogin)
                .setAuthLoginReq(authLoginReq)
                .build();
        BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
                .setAuthToken("r96xz4hbpS0bMBrT7VtU4g==")
                .setReqWrapper(reqWrapper)
                .build();
        FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/authLogin.txt");
        baseReq.writeTo(output);
        output.close();
    }

    public static void input12() throws IOException {
        FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/authLogin.txt");
        BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
        String authToken = baseReq.getAuthToken();
        ReqWrapper reqWrapper = baseReq.getReqWrapper();
        RequestType requestType = reqWrapper.getRequestType();
        AuthLoginReqProtocal.AuthLoginReq authLoginReq = reqWrapper.getAuthLoginReq();
        System.out.println(authLoginReq.getMachineId());
        System.out.println(authLoginReq.getAccount());
        System.out.println(authLoginReq.getPasswd());
    }

    public static void output13() throws IOException {
        PlayerListReqProtocal.PlayerListReq playerListReq = PlayerListReqProtocal.PlayerListReq.newBuilder().build();
        ReqWrapper reqWrapper = ReqWrapperProtocal.ReqWrapper.newBuilder().setRequestType(RequestType.PlayerList)
                .setPlayerListReq(playerListReq)
                .build();
        BaseReq baseReq = BaseReqProtocal.BaseReq.newBuilder()
                .setAuthToken("r96xz4hbpS1WkT1OSKtUlQ==")
                .setReqWrapper(reqWrapper)
                .build();
        FileOutputStream output = new FileOutputStream("/Users/qinpeirong/Downloads/message/playerRoleList.txt");
        baseReq.writeTo(output);
        output.close();
    }

    public static void input13() throws IOException {
        FileInputStream input = new FileInputStream("/Users/qinpeirong/Downloads/message/playerRoleList.txt");
        BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(input);
        String authToken = baseReq.getAuthToken();
        ReqWrapper reqWrapper = baseReq.getReqWrapper();
        RequestType requestType = reqWrapper.getRequestType();
        System.out.println(authToken);
        System.out.println(requestType.toString());
    }



}
