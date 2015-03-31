package com.pureland.common.service.helper;

import java.io.File;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.UUID;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Multimap;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;
import com.pureland.common.db.data.ChatMsg;
import com.pureland.common.db.data.UserRace;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.db.data.clan.DonateArmy;
import com.pureland.common.db.data.clan.DonateInfo;
import com.pureland.common.enums.chat.ChatChannelServerEnum;
import com.pureland.common.enums.chat.ChatTypeServerEnum;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.BaseRespProtocal;
import com.pureland.common.protocal.ChatRespProtocal;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper.ResponseType;
import com.pureland.common.protocal.enums.ChatEnumProtocal;
import com.pureland.common.protocal.enums.ClanEnumProtocal;
import com.pureland.common.protocal.vo.ArmyVOProtocal.ArmyVO;
import com.pureland.common.protocal.vo.ChatVOProtocal;
import com.pureland.common.protocal.vo.ChatVOProtocal.ChatVO;
import com.pureland.common.protocal.vo.DonateArmyVOProtocal.DonateArmyVO;
import com.pureland.common.service.bean.chat.ChatClanBase;
import com.pureland.common.service.bean.chat.ChatPlayerBase;
import com.pureland.common.util.GameUtil;
import com.pureland.common.util.parseExcel.MyUtil;

/**
 * Created by Administrator on 2015/3/19.
 */
public class ChatHelper {

	public static String SoundURLPath;
	public static String SoundFilePath;

	static {

		if (isWindows()) {
			SoundURLPath = "192.168.1.197:8080/tools-1.0.0/";
			SoundFilePath = "D:/apache-tomcat-6.0.37/webapps/tools-1.0.0";
		} else {
			SoundURLPath = "192.168.1.99:8080/tools-1.0.0/";
			SoundFilePath = "/home/zhangfan/download/apache-tomcat-8.0.20/webapps/tools-1.0.0";
		}
		String urlPath = GameUtil.getGameProp("SoundURLPath");
		if (urlPath != null) {
			SoundURLPath = urlPath;
		}
		String soundFilePath = GameUtil.getGameProp("SoundFilePath");
		if (soundFilePath != null) {
			SoundFilePath = soundFilePath;
		}
	}

	private static final int CHANNELNUM = 1;

	private static ChatMsg MakeChatMsg(ChatPlayerBase chatPlayerBase) {
		ChatMsg chatMsg = new ChatMsg();
		chatMsg.setChatId(chatPlayerBase.getChatId());
		chatMsg.setChatType(chatPlayerBase.getChatType());
		chatMsg.setPlayerId(chatPlayerBase.getPlayerId());
		chatMsg.setPlayerName(chatPlayerBase.getPlayerName());
		chatMsg.setSendTime(chatPlayerBase.getSendTime());
		chatMsg.setCrown(chatPlayerBase.getCrown());
		return chatMsg;
	}

	public static ChatMsg MakeClanChatMsg(ChatPlayerBase chatPlayerBase, ChatClanBase chatClanBase) {
		ChatMsg chatMsg = MakeChatMsg(chatPlayerBase);
		if (chatClanBase != null) {
			chatMsg.setClanIcon(chatClanBase.getClanIcon());
			chatMsg.setClanId(chatClanBase.getClanId());
			chatMsg.setClanName(chatClanBase.getClanName());
			chatMsg.setClanPosition(chatClanBase.getClanPostion());
			chatMsg.setClanLevel(chatClanBase.getClanLevel());
		}
		return chatMsg;
	}

	public static ChatClanBase MakeChatClanBase(Clan clan, ClanMember clanMember) {
		if (clan == null) {
			return null;
		}
		ChatClanBase chatClanBase = new ChatClanBase();
		chatClanBase.setClanId(clan.getClanBase().getClanId());
		chatClanBase.setClanName(clan.getClanBase().getClanName());
		chatClanBase.setClanLevel(clan.getClanBase().getClanLevel());
		chatClanBase.setClanIcon(clan.getClanBase().getClanIcon());
		chatClanBase.setClanPostion(clanMember.getClanPosition());
		return chatClanBase;
	}

	public static ChatPlayerBase MakeChatPlayerBase(Long chatId, UserRace userRace, ChatTypeServerEnum chatTypeServerEnum) {
		ChatPlayerBase chatPlayerBase = new ChatPlayerBase();
		if (userRace != null) {
			chatPlayerBase.setPlayerId(userRace.getId());
			chatPlayerBase.setCrown(userRace.getUserExt().getCrown());
			chatPlayerBase.setPlayerName(userRace.getNickName());
		} else {
			chatPlayerBase.setPlayerId(-1L);
			chatPlayerBase.setCrown(0);
			chatPlayerBase.setPlayerName("系统");
		}
		chatPlayerBase.setChatId(chatId);
		chatPlayerBase.setChatType(chatTypeServerEnum.ordinal());
		chatPlayerBase.setSendTime(System.currentTimeMillis());
		return chatPlayerBase;
	}

	public static String getClanChatChannel(Long clanId) {
		return "ClanChannel:" + clanId;
	}

	public static String getWorldChatChannel(Long userRaceId) {
		return "WorldChannel:" + userRaceId % CHANNELNUM;
	}

	public static String writeSoundFile(Long playerId, byte[] array) {
		// 本地生成文件
		String dir = ChatHelper.SoundFilePath + "/" + playerId;
		File dirF = new File(dir);
		if (!dirF.exists()) {
			dirF.mkdir();
		}
		UUID uuid = UUID.randomUUID();
		String fileName = dir + "/" + uuid + ".y";
		File f = new File(fileName);
		MyUtil.writeFile(f, array);
		String url = SoundURLPath + playerId + "/" + uuid + ".y";
		return url;
	}

	private static boolean isWindows() {
		String osName = System.getProperty("os.name");
		if (osName.contains("Windows")) {
			return true;
		}
		return false;
	}

	private static BaseRespProtocal.BaseResp.Builder makeChatRespBuilder(ChatMsg chatMsg, ChatChannelServerEnum chatChannelServerEnum, ResponseType responseType) {
		ChatRespProtocal.ChatResp.Builder chatRespBuilder = ChatRespProtocal.ChatResp.newBuilder();
		chatRespBuilder.setChatChannel(ChatEnumProtocal.ChatChannel.valueOf(chatChannelServerEnum.ordinal()));
		ChatVO chatVO = makeChatVO(chatMsg);

		chatRespBuilder.addChatVO(chatVO);

		RespWrapperProtocal.RespWrapper.Builder respWrapperBuilder = RespWrapperProtocal.RespWrapper.newBuilder();
		respWrapperBuilder.setResponseType(responseType);
		respWrapperBuilder.setChatResp(chatRespBuilder.build());

		BaseRespProtocal.BaseResp.Builder respBuilder = BaseRespProtocal.BaseResp.newBuilder();
		respBuilder.setErrorType(BaseRespProtocal.BaseResp.ErrorType.OK).setRespWrapper(respWrapperBuilder.build());
		return respBuilder;
	}

	public static BaseRespProtocal.BaseResp.Builder makeUpdateChatRespBuilder(ChatMsg chatMsg, ChatChannelServerEnum chatChannelServerEnum) {
		return makeChatRespBuilder(chatMsg, chatChannelServerEnum, ResponseType.UpdateChatMsg);
	}

	public static BaseRespProtocal.BaseResp.Builder makeAddChatRespBuilder(ChatMsg chatMsg, ChatChannelServerEnum chatChannelServerEnum) {
		return makeChatRespBuilder(chatMsg, chatChannelServerEnum, ResponseType.AddChatMsg);
	}

	public static BaseRespProtocal.BaseResp.Builder makeDeleteChatRespBuilder(ChatMsg chatMsg, ChatChannelServerEnum chatChannelServerEnum) {
		return makeChatRespBuilder(chatMsg, chatChannelServerEnum, ResponseType.DeleteChatMsg);
	}

	public static ChatVOProtocal.ChatVO makeChatVO(ChatMsg chatMsg) {
		ChatVO.Builder chatVOBuilder = ChatVO.newBuilder();
		chatVOBuilder.setChatId(chatMsg.getChatId());
		chatVOBuilder.setChatType(ChatEnumProtocal.ChatType.valueOf(chatMsg.getChatType()));
		if (chatMsg.getCrown() != null) {
			chatVOBuilder.setCrown(chatMsg.getCrown());
		}
		if (chatMsg.getPlayerId() != null) {
			chatVOBuilder.setPlayerId(chatMsg.getPlayerId());
		}
		if (chatMsg.getPlayerName() != null) {
			chatVOBuilder.setPlayerName(chatMsg.getPlayerName());
		}
		if (chatMsg.getSendTime() != null) {
			chatVOBuilder.setSendTime(chatMsg.getSendTime());
		}

		if (chatMsg.getContent() != null) {
			chatVOBuilder.setContent(chatMsg.getContent());
		}

		if (chatMsg.getClanId() != null) {
			chatVOBuilder.setClanId(chatMsg.getClanId());
		}
		if (chatMsg.getClanName() != null) {
			chatVOBuilder.setClanName(chatMsg.getClanName());
		}
		if (chatMsg.getClanIcon() != null) {
			chatVOBuilder.setClanIcon(chatMsg.getClanIcon());
		}
		if (chatMsg.getClanLevel() != null) {
			chatVOBuilder.setClanLevel(chatMsg.getClanLevel());
		}
		if (chatMsg.getClanPosition() != null) {
			chatVOBuilder.setClanPosition(ClanEnumProtocal.ClanPosition.valueOf(chatMsg.getClanPosition()));
		}

		if (chatMsg.getBackUpUpper() != null) {
			chatVOBuilder.setDonateUpper(chatMsg.getBackUpUpper());
		}

		if (chatMsg.getIsSoundUse() != null) {
			chatVOBuilder.setIsSoundUse(chatMsg.getIsSoundUse());
		}
		if (chatMsg.getSoundAddress() != null) {
			chatVOBuilder.setSoundAddress(chatMsg.getSoundAddress());
		}
		if (chatMsg.getSoundSecond() != null) {
			chatVOBuilder.setSoundSecond(chatMsg.getSoundSecond());
		}

		DonateArmy donateArmy = chatMsg.getDonateArmy();
		if (donateArmy != null) {
			chatVOBuilder.setDonateNum(ClanHelper.getDonateArmyNowSpace(donateArmy));
			Table<Long, Integer, Integer> table = HashBasedTable.create();
			for (DonateInfo donateInfo : donateArmy.getDonateInfoMap()) {
				Integer num = table.get(donateInfo.getUserRaceId(), donateInfo.getCid());
				if (num == null) {
					num = 0;
				}
				table.put(donateInfo.getUserRaceId(), donateInfo.getCid(), donateInfo.getNum() + num);
			}

			Multimap<Long, A> maps = ArrayListMultimap.create();
			for (Cell<Long, Integer, Integer> cell : table.cellSet()) {
				maps.put(cell.getRowKey(), new A(cell.getColumnKey(), cell.getValue()));
			}

			for (Entry<Long, Collection<A>> entry : maps.asMap().entrySet()) {
				DonateArmyVO.Builder donateArmyVOBuilder = DonateArmyVO.newBuilder();
				donateArmyVOBuilder.setPlayerid(entry.getKey());
				for (A a : entry.getValue()) {
					ArmyVO.Builder armyBuilder = ArmyVO.newBuilder();
					armyBuilder.setCid(a.cid).setAmount(a.num);
					donateArmyVOBuilder.addArmyVOs(armyBuilder.build());
				}
				chatVOBuilder.addDonateArmyVOs(donateArmyVOBuilder.build());
			}
		} else {
			chatVOBuilder.setDonateNum(0);
		}
		return chatVOBuilder.build();
	}

	private static class A {
		public final Integer cid;
		public final Integer num;

		private A(Integer cid, Integer num) {
			super();
			this.cid = cid;
			this.num = num;
		}
	}

	public static void main(String[] args) {
		String urlPath = GameUtil.getGameProp("SoundURLPath");
		System.out.println(urlPath);
	}
}
