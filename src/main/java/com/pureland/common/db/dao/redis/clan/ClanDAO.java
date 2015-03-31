package com.pureland.common.db.dao.redis.clan;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.script.RedisScript;

import com.google.common.collect.Lists;
import com.pureland.common.db.dao.redis.RedisDAO;
import com.pureland.common.db.data.clan.Clan;
import com.pureland.common.db.data.clan.ClanMember;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;
import com.pureland.common.service.helper.ClanHelper;
import com.pureland.common.util.SpringContextUtil;

public class ClanDAO extends RedisDAO {

	private RedisTemplate valueJdkRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueJdkRedisTemplate");
	private RedisTemplate valueStrRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueStrRedisTemplate");

	public Clan getClanByClanId(final Long clanId) throws DBException {
		SessionCallback<Clan> sessionCallback = new SessionCallback<Clan>() {
			@Override
			public Clan execute(RedisOperations operations) throws DataAccessException {
				ValueOperations<String, Clan> valueOperations = operations.opsForValue();
				String clanIdKey = Clan.getClanIdKeyString(clanId);
				Clan clan = valueOperations.get(clanIdKey);
				return clan;
			}
		};
		final Clan ret = (Clan) valueJdkRedisTemplate.execute(sessionCallback);
		if (ret == null) {
			throw new DBException("不存在ClanId:%d的公会", clanId);
		}

		RedisCallback redisCallback = new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
				// 等级
				String keyLevel = Clan.getClanLevelIdKeyString(clanId);
				String level = stringRedisConnection.get(keyLevel);
				ret.getClanBase().setClanLevel(Integer.parseInt(level));

				// 总积分
				String keyTotalCrown = Clan.getClanTotalCrownIdKeyString(clanId);
				String crown = stringRedisConnection.get(keyTotalCrown);
				ret.getClanBase().setClanTotalCrown(Integer.parseInt(crown));

				// 人数
				String keyMemberNum = Clan.getClanMemberNumIdKeyString(clanId);
				String memberNum = stringRedisConnection.get(keyMemberNum);
				ret.getClanBase().setClanMemberNum(Integer.parseInt(memberNum));
				return null;
			}
		};
		valueStrRedisTemplate.execute(redisCallback);
		return ret;
	}

	public int getNumsByPostion(Long clanId, int postion) throws DBException {
		int ret = 0;
		List<ClanMember> clanMembers = getClanMembersByClanId(clanId);
		for (ClanMember clanMember : clanMembers) {
			if (clanMember.getClanPosition() == postion) {
				ret++;
			}
		}
		return ret;
	}

	public List<ClanMember> getClanMembersByClanId(final Long clanId) throws DBException {
		SessionCallback<Set<String>> setSessionCallback = new SessionCallback<Set<String>>() {
			@Override
			public Set<String> execute(RedisOperations operations) throws DataAccessException {
				String keyMembersId = Clan.getClanMemberIdKeyString(clanId);
				ZSetOperations operZset = operations.opsForZSet();
				return operZset.reverseRange(keyMembersId, 0, -1);
			}
		};
		final Set<String> clanMemberIds = (Set<String>) valueStrRedisTemplate.execute(setSessionCallback);

		RedisCallback<ClanMember> pipelineCallback = new RedisCallback<ClanMember>() {
			@Override
			public ClanMember doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
				for (String pid : clanMemberIds) {
					String key = ClanMember.getClanMemberIdKeyString(Long.valueOf(pid));
					stringRedisConn.get(key);
				}
				return null;
			}
		};
		List<ClanMember> results = Lists.newArrayList(valueJdkRedisTemplate.executePipelined(pipelineCallback));
		return results;
	}

	public Long addClan(final Clan clan) throws DBException {
		SessionCallback<Clan> sessionCallback = new SessionCallback<Clan>() {
			@Override
			public Clan execute(RedisOperations operations) throws DataAccessException {
				ValueOperations valueOperations = operations.opsForValue();
				Long clanId = valueOperations.increment(Entity.CLAN.name(), 1);
				clan.getClanBase().setClanId(clanId);

				// CLAN:1:ClanBaseInfo
				String clanIdKey = Clan.getClanIdKeyString(clanId);
				valueOperations.set(clanIdKey, clan);
				return clan;
			}
		};

		final Clan retClan = (Clan) valueJdkRedisTemplate.execute(sessionCallback);

		SessionCallback sessionCallback1 = new SessionCallback() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				String clanIdStr = String.valueOf(clan.getClanBase().getClanId());

				operations.multi();

				SetOperations<String, String> setOperations = operations.opsForSet();
				// CLANID
				String totalClanIdIdKey = Clan.getTotalClanIdIdKeyString();
				setOperations.add(totalClanIdIdKey, clanIdStr);
				// CLANNAME
				String totalClanNameIdKey = Clan.getTotalClanNameIdKeyString();
				setOperations.add(totalClanNameIdKey, clan.getClanBase().getClanName());

				// 建立搜索缓存
				// CLANCOUNTRY:china
				String countryIdKey = Clan.getCountryIdKeyString(clan.getClanBase().getClanCountry());
				setOperations.add(countryIdKey, clanIdStr);
				// CLANFIGHTRATE:1
				String fightRateIdKey = Clan.getFightRateIdKeyString(clan.getClanBase().getClanFightRateType());
				setOperations.add(fightRateIdKey, clanIdStr);
				// ClANJOINTYPE:1
				String joinTypeIdKey = Clan.getJoinTypeIdKeyString(clan.getClanBase().getClanJoinType());
				setOperations.add(joinTypeIdKey, clanIdStr);

				ValueOperations<String, String> valueOperations = operations.opsForValue();
				// 等级
				String keyLevel = Clan.getClanLevelIdKeyString(retClan.getClanBase().getClanId());
				valueOperations.set(keyLevel, "1");

				// 公会名称和id的对应
				HashOperations<String, String, String> hashOperations = operations.opsForHash();
				String clanName2IdKey = Clan.getClanName2IdKeyString();
				hashOperations.put(clanName2IdKey, clan.getClanBase().getClanName(), clanIdStr);

				operations.exec();
				return null;
			}
		};
		valueStrRedisTemplate.execute(sessionCallback1);

		return retClan.getClanBase().getClanId();
	}

	public boolean isExistClanName(final String clanName) {
		SessionCallback<Boolean> sessionCallback = new SessionCallback<Boolean>() {
			@Override
			public Boolean execute(RedisOperations operations) throws DataAccessException {
				SetOperations setOperations = operations.opsForSet();
				String totalClanNameIdKey = Clan.getTotalClanNameIdKeyString();
				boolean ret = setOperations.isMember(totalClanNameIdKey, clanName);
				return ret;
			}
		};
		boolean ret = (Boolean) valueStrRedisTemplate.execute(sessionCallback);
		return ret;
	}

	public long getClanMemberNum(final Long clanId) {
		SessionCallback<Long> setSessionCallback = new SessionCallback<Long>() {
			@Override
			public Long execute(RedisOperations operations) throws DataAccessException {
				String keyMembersId = Clan.getClanMemberIdKeyString(clanId);
				ZSetOperations operZset = operations.opsForZSet();
				return operZset.zCard(keyMembersId);
			}
		};
		return (Long) valueStrRedisTemplate.execute(setSessionCallback);
	}

	private List<Object> getClanOtherInfo(final Long clanId) {
		RedisCallback setSessionCallback = new RedisCallback() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				// 人数
				StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
				String keyMembersId = Clan.getClanMemberIdKeyString(clanId);
				stringRedisConnection.zCard(keyMembersId);

				// 总积分
				String keyTotalCrownId = Clan.getClanTotalCrownIdKeyString(clanId);
				stringRedisConnection.get(keyTotalCrownId);

				// 等级
				String keyLevel = Clan.getClanLevelIdKeyString(clanId);
				stringRedisConnection.get(keyLevel);

				return null;
			}
		};
		return valueStrRedisTemplate.executePipelined(setSessionCallback);
	}

	public List<Clan> getAllClan(final List<String> clanIds) {
		RedisCallback<Clan> pipelineCallback = new RedisCallback<Clan>() {
			@Override
			public Clan doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection stringRedisConn = (StringRedisConnection) connection;
				for (String clanId : clanIds) {
					String key = Clan.getClanIdKeyString(Long.valueOf(clanId));
					stringRedisConn.get(key);
				}
				return null;
			}
		};

		List<Clan> results = Lists.newArrayList(valueJdkRedisTemplate.executePipelined(pipelineCallback));

		List<Clan> ret = Lists.newArrayList();
		for (Clan c : results) {
			long cid = c.getClanBase().getClanId();
			List<Object> infos = getClanOtherInfo(cid);
			c.getClanBase().setClanMemberNum(Integer.parseInt(infos.get(0).toString()));
			c.getClanBase().setClanTotalCrown(Integer.parseInt(infos.get(1).toString()));
			c.getClanBase().setClanLevel(Integer.parseInt(infos.get(2).toString()));

			ret.add(c);
		}
		return ret;
	}

	public void removeClan(final Long clanId) throws DBException {
		final Clan clan = getClanByClanId(clanId);
		SessionCallback sessionCallback = new SessionCallback() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				operations.multi();

				String clanIdKey = Clan.getClanIdKeyString(clanId);
				operations.delete(clanIdKey);

				String clanIdStr = String.valueOf(clanId);

				SetOperations setOperations = operations.opsForSet();
				// CLANID
				String totalClanIdIdKey = Clan.getTotalClanIdIdKeyString();
				setOperations.remove(totalClanIdIdKey, clanIdStr);
				// CLANNAME
				String totalClanNameIdKey = Clan.getTotalClanNameIdKeyString();
				setOperations.remove(totalClanNameIdKey, clan.getClanBase().getClanName());

				// 移除搜索缓存
				// CLANCOUNTRY:china
				String countryIdKey = Clan.getCountryIdKeyString(clan.getClanBase().getClanCountry());
				setOperations.remove(countryIdKey, clanIdStr);
				// CLANFIGHTRATE:1
				String fightRateIdKey = Clan.getFightRateIdKeyString(clan.getClanBase().getClanFightRateType());
				setOperations.remove(fightRateIdKey, clanIdStr);
				// ClANJOINTYPE:1
				String joinTypeIdKey = Clan.getJoinTypeIdKeyString(clan.getClanBase().getClanJoinType());
				setOperations.remove(joinTypeIdKey, clanIdStr);

				// 等级
				String keyLevel = Clan.getClanLevelIdKeyString(clanId);
				operations.delete(keyLevel);

				// 公会名称和id的对应
				String clanName2IdKey = Clan.getClanName2IdKeyString();
				HashOperations<String, String, String> hashOperations = operations.opsForHash();
				hashOperations.delete(clanName2IdKey, clan.getClanBase().getClanName());

				operations.exec();
				return null;
			}
		};
		valueStrRedisTemplate.execute(sessionCallback);

		// 因为清除成员是在quitClan里面做的,所以这里可以不用处理了

	}

	public void updateClanBaseBean(final Clan oldClan, final Clan newClan) throws DBException {
		final Long clanId = oldClan.getClanBase().getClanId();
		SessionCallback sessionCallback = new SessionCallback() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				ValueOperations valueOperations = operations.opsForValue();
				// CLAN:1:ClanBaseInfo
				String clanIdKey = Clan.getClanIdKeyString(clanId);
				valueOperations.set(clanIdKey, newClan);
				return null;
			}
		};
		valueJdkRedisTemplate.execute(sessionCallback);

		SessionCallback sessionCallback1 = new SessionCallback() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				operations.multi();

				String clanIdStr = String.valueOf(clanId);

				SetOperations setOperations = operations.opsForSet();

				// 删除旧的搜索缓存
				// CLANCOUNTRY:china
				String countryIdKey = Clan.getCountryIdKeyString(oldClan.getClanBase().getClanCountry());
				setOperations.remove(countryIdKey, clanIdStr);
				// CLANFIGHTRATE:1
				String fightRateIdKey = Clan.getFightRateIdKeyString(oldClan.getClanBase().getClanFightRateType());
				setOperations.remove(fightRateIdKey, clanIdStr);
				// ClANJOINTYPE:1
				String joinTypeIdKey = Clan.getJoinTypeIdKeyString(oldClan.getClanBase().getClanJoinType());
				setOperations.remove(joinTypeIdKey, clanIdStr);

				// 建立新的搜索缓存
				// CLANCOUNTRY:china
				String countryIdKey1 = Clan.getCountryIdKeyString(newClan.getClanBase().getClanCountry());
				setOperations.add(countryIdKey1, clanIdStr);
				// CLANFIGHTRATE:1
				String fightRateIdKey1 = Clan.getFightRateIdKeyString(newClan.getClanBase().getClanFightRateType());
				setOperations.add(fightRateIdKey1, clanIdStr);
				// ClANJOINTYPE:1
				String joinTypeIdKey1 = Clan.getJoinTypeIdKeyString(newClan.getClanBase().getClanJoinType());
				setOperations.add(joinTypeIdKey1, clanIdStr);

				operations.exec();
				return null;
			}
		};
		valueStrRedisTemplate.execute(sessionCallback1);
	}

	public List<String> searchClanIds(String joinType, String clanName, String country, String clanLevel, String fightRate, String limitCrown,
			String numMinimum, String numMaxnium) {
		RedisScript<List> redisScript = ClanHelper.script();
		List<String> clanIds = (List<String>) valueStrRedisTemplate.execute(redisScript,
				Lists.newArrayList(country, fightRate, joinType, numMinimum, numMaxnium, clanLevel, clanName, limitCrown));
		return clanIds;
	}
}
