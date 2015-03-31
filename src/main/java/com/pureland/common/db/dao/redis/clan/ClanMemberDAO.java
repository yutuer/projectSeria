package com.pureland.common.db.dao.redis.clan;

import com.pureland.common.db.dao.redis.RedisDAO;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.db.error.DBException;
import com.pureland.common.service.helper.ClanHelper;
import com.pureland.common.util.SpringContextUtil;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;

import redis.clients.jedis.Jedis;

/**
 * Created by Administrator on 2015/3/13.
 */
public class ClanMemberDAO extends RedisDAO {

	private RedisTemplate valueJdkRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueJdkRedisTemplate");
	private RedisTemplate valueStrRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueStrRedisTemplate");

	public void addClanMember(final ClanMember clanMember) throws DBException {
		SessionCallback<ClanMember> sessionCallback = new SessionCallback<ClanMember>() {
			@Override
			public ClanMember execute(RedisOperations operations) throws DataAccessException {
				ValueOperations oper = operations.opsForValue();
				String keyClanMemberId = ClanMember.getClanMemberIdKeyString(clanMember.getPlayerId());
				oper.set(keyClanMemberId, clanMember);
				return clanMember;
			}
		};
		valueJdkRedisTemplate.execute(sessionCallback);

		SessionCallback<Long> t = new SessionCallback<Long>() {
			@Override
			public Long execute(RedisOperations operations) throws DataAccessException {
				operations.multi();
				ValueOperations oper = operations.opsForValue();
				String keyClanId = ClanMember.getClanIdKeyString(clanMember.getPlayerId());
				oper.set(keyClanId, String.valueOf(clanMember.getClanId()));

				ZSetOperations operZset = operations.opsForZSet();
				String keyMembersId = Clan.getClanMemberIdKeyString(clanMember.getClanId());
				operZset.add(keyMembersId, String.valueOf(clanMember.getPlayerId()), clanMember.getCrown());
				operations.exec();

				Long rankQuery = operZset.reverseRank(keyMembersId, String.valueOf(clanMember.getPlayerId()));

				int rank = (int) (rankQuery + 1);

				operations.multi();
				// 总积分
				String keyTotalCrown = Clan.getClanTotalCrownIdKeyString(clanMember.getClanId());
				oper.increment(keyTotalCrown, ClanHelper.getScoreByRank(rank, clanMember.getCrown()));
				// 人数
				String keyMemberNum = Clan.getClanMemberNumIdKeyString(clanMember.getClanId());
				oper.increment(keyMemberNum, 1);
				operations.exec();

				return rankQuery;
			}
		};
		valueStrRedisTemplate.execute(t);
	}

	public Long getClanIdByUserRaceId(final Long userRaceId) throws DBException {
		SessionCallback<String> sessionCallback = new SessionCallback<String>() {
			@Override
			public String execute(RedisOperations operations) throws DataAccessException {
				String keyClanId = ClanMember.getClanIdKeyString(userRaceId);
				ValueOperations<String, String> oper = operations.opsForValue();
				return oper.get(keyClanId);
			}
		};
		Object obj = valueStrRedisTemplate.execute(sessionCallback);
		if (obj == null) {
			return null;
		}
		return Long.valueOf(obj.toString());
	}

	public void removeClanMember(final ClanMember clanMember) throws DBException {
		final Long userRaceId = clanMember.getPlayerId();
		final Long clanId = clanMember.getClanId();

		SessionCallback<Long> t = new SessionCallback<Long>() {
			@Override
			public Long execute(RedisOperations operations) throws DataAccessException {
				ZSetOperations operZset = operations.opsForZSet();
				String keyMembersId = Clan.getClanMemberIdKeyString(clanMember.getClanId());
				Long rankQuery = operZset.reverseRank(keyMembersId, String.valueOf(clanMember.getPlayerId()));
				return rankQuery;
			}
		};
		final int rank = (int) ((Long) (valueStrRedisTemplate.execute(t)) + 1);

		SessionCallback sessionCallback = new SessionCallback() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				operations.multi();
				String keyClanMemberId = ClanMember.getClanMemberIdKeyString(userRaceId);
				operations.delete(keyClanMemberId);

				String keyClanId = ClanMember.getClanIdKeyString(userRaceId);
				operations.delete(keyClanId);

				ZSetOperations operZset = operations.opsForZSet();
				String keyMembersId = Clan.getClanMemberIdKeyString(clanId);
				operZset.remove(keyMembersId, String.valueOf(userRaceId));

				ValueOperations oper = operations.opsForValue();
				// 总积分
				String keyTotalCrown = Clan.getClanTotalCrownIdKeyString(clanId);
				oper.increment(keyTotalCrown, -1 * ClanHelper.getScoreByRank(rank, clanMember.getCrown()));
				// 人数
				String keyMemberNum = Clan.getClanMemberNumIdKeyString(clanId);
				oper.increment(keyMemberNum, -1);

				operations.exec();
				return null;
			}
		};
		valueStrRedisTemplate.execute(sessionCallback);
	}

	public ClanMember getClanMemberInfo(final Long userRaceId) throws DBException {
		SessionCallback<ClanMember> sessionCallback = new SessionCallback<ClanMember>() {
			@Override
			public ClanMember execute(RedisOperations operations) throws DataAccessException {
				String keyClanMember = ClanMember.getClanMemberIdKeyString(userRaceId);
				ValueOperations<String, ClanMember> valueOper = operations.opsForValue();
				return valueOper.get(keyClanMember);
			}
		};
		return (ClanMember) valueJdkRedisTemplate.execute(sessionCallback);
	}

	public void updateClanMember(final ClanMember clanMember) {
		final Long userRaceId = clanMember.getPlayerId();
		SessionCallback<ClanMember> sessionCallback = new SessionCallback<ClanMember>() {
			@Override
			public ClanMember execute(RedisOperations operations) throws DataAccessException {
				ValueOperations oper = operations.opsForValue();
				String keyClanMemberId = ClanMember.getClanMemberIdKeyString(userRaceId);
				oper.set(keyClanMemberId, clanMember);
				return clanMember;
			}
		};
		valueJdkRedisTemplate.execute(sessionCallback);

		SessionCallback s = new SessionCallback() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				ZSetOperations operZset = operations.opsForZSet();
				String keyMembersId = Clan.getClanMemberIdKeyString(clanMember.getClanId());
				operZset.add(keyMembersId, String.valueOf(clanMember.getPlayerId()), clanMember.getCrown());
				return null;
			}
		};
		valueStrRedisTemplate.execute(s);
	}
}
