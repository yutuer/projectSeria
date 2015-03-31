package com.pureland.common.service.helper;

public class BattleHelper {

	/**
	 * 获得战斗奖励分
	 * 
	 * @param attackerScore
	 * @param defenderScore
	 * @param star
	 * @return
	 */
	public static int GetBattleRewardScore(int attackerScore, int defenderScore, int star) {
		double deltaScore = defenderScore - attackerScore;
		if (deltaScore > 110) {
			deltaScore = 110;
		}
		if (deltaScore < -110) {
			deltaScore = -110;
		}
		double score = deltaScore * 0.1f + 35;
		switch (star) {
		case 1:
			score *= 0.3f;
			break;
		case 2:
			score *= 0.6f;
			break;
		}
		return (int) Math.round(score);
	}

}
