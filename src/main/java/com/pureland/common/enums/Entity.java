package com.pureland.common.enums;

/**
 * @author qinpeirong
 */
public enum Entity {
	MACHINE("MACHINE"), USER("USER"), USERRACE("USERRACE"), USERRACEMAPPING("USERRACEMAPPING"), BUILDING("BUILDING"), USERBUILDING("USERBUILDING"), CLANNAME(
			"CLANNAME"), CLANID("CLANID"), CHAT("CHAT"), USEREXT("USEREXT"), RESOURCE("RESOURCE"), USERWORKER("USERWORKER"), CLANFIGHTRATE("CLANFIGHTRATE"), ClANJOINTYPE(
			"CLANJOINTYPE"), ARMY("ARMY"), PRODUCT("PRODUCT"), PRODUCTQUENE("PRODUCTQUENE"), SHIP("SHIP"), CLAN("CLAN"), CLANLEVEL("CLANLEVEL"), CLANCOUNTRY(
			"CLANCOUNTRY"), ARMORY("ARMORY"), ATTACK("ATTACK"), DEFEND("DEFEND"), ARMYCONSUME("ARMYCONSUME"), CLANMEMBER("CLANMEMBER"), REPLAY("REPLAY"), RESOURCERECORD(
			"RESOURCERECORD"), SKILL("SKILL"), ARMYEXP("ARMYEXP"), SKILLCONSUME("SKILLCONSUME"), LASTOPERATETIME("LASTOPERATETIME"), DONATEARMY("DONATEARMY"), MODULE(
			"MODULE");

	public static final String SEPARATOR = ":";

	private String name;

	private Entity(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public static enum Machine {
		ID("ID"), MACHINEID("MACHINEID"), USERID("USERID");
		private String name;

		private Machine(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
	}

	public static enum User {
		ID("ID"), MACHINEID("MACHINEID"), ACCOUNT("ACCOUNT"), PASSWD("PASSWD"), TELEPHONE("TELEPHONE"), EMAIL("EMAIL");

		private String name;

		private User(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
	}

	public static enum UserRace {

		ID("ID"), USERID("USERID"), ISINFIGHT("ISINFIGHT"), FIGHTOPPONENT("FIGHTOPPONENT"), RACEID("RACEID"), EASYCODE("EASYCODE"), NICKNAME("NICKNAME"), LASTLOGIN(
				"LASTLOGIN"), CAMPCID("CAMPCID"), ARMYSHOP("ARMYSHOP"), SKILLSHOP("SKILLSHOP"), UPGRADESKILLCID("UPGRADESKILLCID"), UPGRADESKILLCOMPLETETIME(
				"UPGRADESKILLCOMPLETETIME"), BUILDINGSPACEINFO("BUILDINGSPACEINFO");
		private String name;

		private UserRace(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

	}

	public static enum Building {
		ID("ID"), CID("CID"), SID("SID"), USERRACEID("USERRACEID"), STATUS("STATUS"), BUILDINGTYPE("BUILDINGTYPE"), ABSCISSA("NICKNAME"), ORDINATE("ORDINATE"), BUILDENDTIME(
				"buildEndTime"), GATHERTIME("GATHERTIME"), STORAGECOUNTSTORAGECOUNT("storageCount");

		private String name;

		private Building(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
	}

	public static enum UserExt {
		ID("ID"), USERRACEID("USERRACEID"), LEVEL("LEVEL"), MILITARYRANK("MILITARYRANK"), EXPERIENCE("EXPERIENCE"), CROWN("CROWN");

		private String name;

		private UserExt(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
	}

	public static enum Resource {
		ID("ID"), USEREXTID("USEREXTID"), RESOURCETYPE("RESOURCETYPE"), COUNT("COUNT");

		private String name;

		private Resource(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
	}

	public static enum ArmyExp {
		ID("ID"), USERRACEID("USERRACEID"), CID("CID"), EXP("EXP");

		private String name;

		private ArmyExp(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
	}

	public static enum Army {
		ID("ID"), USERRACEID("USERRACEID"), ARMYEXPID("ARMYEXPID"), AMOUNT("AMOUNT");

		private String name;

		private Army(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

	}

	public static enum Skill {
		ID("ID"), USERRACEID("USERRACEID"), CID("CID"), AMOUNT("AMOUNT"), TOTALAMOUNT("TOTALAMOUNT");

		private String name;

		private Skill(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
	}

	public static enum Product {

		ID("ID"), USERRACEID("USERRACEID"), BUILDING("BUILDING"), CID("CID"), AMOUNT("AMOUNT"), SEQUENCE("SEQUENCE"), BEGINTIME("BEGINTIME"), NEXTENDTIME(
				"NEXTENDTIME");

		private String name;

		private Product(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

	}

	public static enum Attack {
		ID("ID"), USERRACEID("USERRACEID"), PERCENTAGE("PERCENTAGE"), STAR("STAR"), USEDONATEDARMY("USEDONATEDARMY"), REWARDMEDAL("REWARDMEDAL"), PEERNAME(
				"PEERNAME"), REWARDCROWN("REWARDCROWN"), REWARDGOLDBYCROWNLEVEL("REWARDGOLDBYCROWNLEVEL"), REWARDOILBYCROWNLEVEL("REWARDOILBYCROWNLEVEL"), TIMESTAMP(
				"timestamp"), PEERID("PEERID");

		private String name;

		private Attack(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
	}

	public static enum Defend {
		ID("ID"), USERRACEID("USERRACEID"), PERCENTAGE("PERCENTAGE"), STAR("STAR"), USEDONATEDARMY("USEDONATEDARMY"), REWARDMEDAL("REWARDMEDAL"), PEERNAME(
				"PEERNAME"), REWARDCROWN("REWARDCROWN"), REWARDGOLDBYCROWNLEVEL("REWARDGOLDBYCROWNLEVEL"), REWARDOILBYCROWNLEVEL("REWARDOILBYCROWNLEVEL"), TIMESTAMP(
				"timestamp"), PEERID("PEERID");

		private String name;

		private Defend(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
	}

	public static enum ArmyConsume {
		ID("ID"), USERRACEID("USERRACEID"), BATTLEID("BATTLEID"), CID("CID"), AMOUNT("AMOUNT");
		private String name;

		private ArmyConsume(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
	}

	public static enum SkillConsume {
		ID("ID"), USERRACEID("USERRACEID"), BATTLEID("BATTLEID"), CID("CID"), AMOUNT("AMOUNT");
		private String name;

		private SkillConsume(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
	}

	public static enum Replay {
		ID("ID"), USERRACEID("USERRACEID"), BATTLEID("BATTLEID"), BATTLETYPE("BATTLETYPE"), REPLAY("REPLAY");
		private String name;

		private Replay(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
	}

	public static enum ResourceRecord {
		ID("ID"), USERRACEID("USERRACEID"), BATTLEID("BATTLEID"), RESOURCESERVERTYPE("ResourceServcerType"), COUNT("COUNT");
		private String name;

		private ResourceRecord(String name) {
			this.name = name;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name
		 *            the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}
	}

}
