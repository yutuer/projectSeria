package com.pureland.common.util;

import java.util.Comparator;
import java.util.ResourceBundle;

import com.pureland.common.enums.ResourceServerTypeEnum;
import com.pureland.common.enums.SubServerTypeEnum;

/**
 * Created by Administrator on 2015/2/3.
 */
public class GameUtil {

	public static final int LENGTH = 88;
	public static final int MAXCROWN = 35;
	public static final char EXIST = '1';
	public static final char NOEXIST = '0';

	public static final long SECOND = 1L * 1000;
	public static final long MINUTE = SECOND * 60;
	public static final long HOUR = MINUTE * 60;

	public static final long OFFLINETIME = MINUTE * 5;

	public static Comparator<String> COMPARATOR = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return Integer.parseInt(o1) - Integer.parseInt(o2);
		}
	};

	/**
	 * 战斗持续时间
	 */
	public static final long FIGHTLASTTIME = MINUTE * 5;

	public static int getConsumeByTime(int seconds) {
		int[] ranges = new int[] { 60, 3600, 86400, 604800 };
		int[] gems = new int[] { 1, 20, 260, 1000 };
		if (seconds <= 0)
			return (0);
		if (seconds <= ranges[0])
			return (gems[0]);
		int i;
		for (i = 1; i < ranges.length - 1; i++)
			if (seconds <= ranges[i])
				return (Math.round((seconds - ranges[i - 1]) / ((ranges[i] - ranges[i - 1]) / (gems[i] - gems[i - 1])) + gems[i - 1]));
		i = ranges.length - 1;
		return (Math.round((seconds - ranges[i - 1]) / ((ranges[i] - ranges[i - 1]) / (gems[i] - gems[i - 1])) + gems[i - 1]));
	}

	public static boolean IsTimeWrong(long time1, long time2) {
		return Math.abs(time1 - time2) > 10L * 1000;
	}

	public static int ResourceToGem(int resources) {
		int[] ranges = new int[] { 100, 1000, 10000, 100000, 1000000, 10000000 };
		int[] gems = new int[] { 1, 5, 25, 125, 600, 3000 };
		if (resources <= 0)
			return (0);
		if (resources <= ranges[0])
			return (gems[0]);
		int i;
		for (i = 1; i < ranges.length - 1; i++)
			if (resources <= ranges[i])
				return Math.round((resources - ranges[i - 1]) / ((ranges[i] - ranges[i - 1]) / (gems[i] - gems[i - 1])) + gems[i - 1]);
		i = ranges.length - 1;
		return Math.round((resources - ranges[i - 1]) / ((ranges[i] - ranges[i - 1]) / (gems[i] - gems[i - 1])) + gems[i - 1]);
	}

	public static SubServerTypeEnum getBuildingTypeByResourceType(ResourceServerTypeEnum resourceServerType) {
		SubServerTypeEnum buildingType = null;
		if (resourceServerType == ResourceServerTypeEnum.Oil) {
			buildingType = SubServerTypeEnum.Warehouse;
		} else if (resourceServerType == ResourceServerTypeEnum.Gold) {
			buildingType = SubServerTypeEnum.Vault;
		} else if (resourceServerType == ResourceServerTypeEnum.NewOil) {
			buildingType = SubServerTypeEnum.NewJar;
		}
		return buildingType;
	}

	public static boolean isBuildConflict(char[][] c, int x, int y, int size) {
		if (x + size > LENGTH) {
			return false;
		}
		if (y + size > LENGTH) {
			return false;
		}
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (c[i][j] == EXIST) {
					return true;
				}
			}
		}
		return false;
	}

	public static void removeBuildSpaceInfo(char[][] c, int beforeX, int beforeY, int size) {
		for (int i = beforeX; i < beforeX + size; i++) {
			for (int j = beforeY; j < beforeY + size; j++) {
				c[i][j] = NOEXIST;
			}
		}
	}

	public static void addBuildSpaceInfo(char[][] c, int afterX, int afterY, int size) {
		for (int i = afterX; i < afterX + size; i++) {
			for (int j = afterY; j < afterY + size; j++) {
				c[i][j] = EXIST;
			}
		}
	}

	public static void changeBuildSpaceInfo(char[][] c, int beforeX, int beforeY, int afterX, int afterY, int size) {
		removeBuildSpaceInfo(c, beforeX, beforeY, size);
		addBuildSpaceInfo(c, afterX, afterY, size);
	}

	public static char[][] transferString2Char(String buildingSpaceInfoValue) {
		char[][] c = new char[LENGTH][LENGTH];
		for (int i = 0; i < LENGTH; i++) {
			for (int j = 0; j < LENGTH; j++) {
				c[i][j] = buildingSpaceInfoValue.charAt(i * LENGTH + j);
			}
		}
		return c;
	}

	public static String transferChar2String(char[][] c) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < LENGTH; i++) {
			for (int j = 0; j < LENGTH; j++) {
				sb.append(c[i][j]);
			}
		}
		return sb.toString();
	}

	public static String getGameProp(String propName) {
		String ret = null;
		try {
			ResourceBundle rb = ResourceBundle.getBundle("game");
			ret = rb.getString(propName);
		} catch (Exception e) {
			
		}
		return ret;
	}
}
