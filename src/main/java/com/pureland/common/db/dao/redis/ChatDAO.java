package com.pureland.common.db.dao.redis;

import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import com.pureland.common.db.data.ChatMsg;
import com.pureland.common.db.error.DBException;
import com.pureland.common.enums.Entity;
import com.pureland.common.util.SpringContextUtil;

/**
 * Created by Administrator on 2015/3/19.
 */
public class ChatDAO extends RedisDAO {

	private RedisTemplate valueJdkRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueJdkRedisTemplate");
	private RedisTemplate valueStrRedisTemplate = (RedisTemplate) SpringContextUtil.getBean("valueStrRedisTemplate");

	public ChatMsg getChatMsg(final Long clanId, final Long chatId) throws DBException {
		SessionCallback<Set<ChatMsg>> sessionCallback = new SessionCallback<Set<ChatMsg>>() {
			@Override
			public Set<ChatMsg> execute(RedisOperations operations) throws DataAccessException {
				ZSetOperations zSetOperations = operations.opsForZSet();
				String keyId = ChatMsg.getIdKeyString(clanId);
				Set<ChatMsg> set = zSetOperations.rangeByScore(keyId, chatId, chatId);
				return set;
			}
		};
		Set<ChatMsg> set = (Set<ChatMsg>) valueJdkRedisTemplate.execute(sessionCallback);
		if (CollectionUtils.isEmpty(set)) {
			return null;
		}
		return set.iterator().next();
	}

	public void updateChatMsg(final ChatMsg chatMsg) throws DBException {
		deleteClanChatMsgs(chatMsg.getChatId());
		addChatMsg(chatMsg);
	}

	public void addChatMsg(final ChatMsg chatMsg) throws DBException {
		SessionCallback<ChatMsg> sessionCallback = new SessionCallback<ChatMsg>() {
			@Override
			public ChatMsg execute(RedisOperations operations) throws DataAccessException {
				ZSetOperations zSetOperations = operations.opsForZSet();
				String keyId = ChatMsg.getIdKeyString(chatMsg.getClanId());
				zSetOperations.add(keyId, chatMsg, Long.valueOf(chatMsg.getChatId()));

				Long num = zSetOperations.zCard(keyId);
				if (num > 50) {
					// 去掉第一个
					zSetOperations.removeRange(keyId, 0, 0);
				}
				return chatMsg;
			}
		};
		valueJdkRedisTemplate.execute(sessionCallback);
	}

	public Long getNewClanId() throws DBException {
		SessionCallback<Long> sessionCallback = new SessionCallback<Long>() {
			@Override
			public Long execute(RedisOperations operations) throws DataAccessException {
				ValueOperations valueOperations = operations.opsForValue();
				Long chatId = valueOperations.increment(Entity.CHAT.name(), 1);
				return chatId;
			}
		};
		return (Long) valueStrRedisTemplate.execute(sessionCallback);
	}

	public Set<ChatMsg> getClanChatMsgs(final Long clanId) throws DBException {
		SessionCallback<Set<ChatMsg>> sessionCallback = new SessionCallback<Set<ChatMsg>>() {
			@Override
			public Set<ChatMsg> execute(RedisOperations operations) throws DataAccessException {
				ZSetOperations zSetOperations = operations.opsForZSet();
				String keyId = ChatMsg.getIdKeyString(clanId);
				return zSetOperations.range(keyId, 0, -1);
			}
		};
		return (Set<ChatMsg>) valueJdkRedisTemplate.execute(sessionCallback);
	}

	public Set<ChatMsg> getRangeClanChat(final Long clanId, final Long nowChatId) throws DBException {
		SessionCallback<Set<ChatMsg>> sessionCallback = new SessionCallback<Set<ChatMsg>>() {
			@Override
			public Set<ChatMsg> execute(RedisOperations operations) throws DataAccessException {
				ZSetOperations zSetOperations = operations.opsForZSet();
				String keyId = ChatMsg.getIdKeyString(clanId);
				return zSetOperations.rangeByScore(keyId, nowChatId, Long.MAX_VALUE);
			}
		};
		return (Set<ChatMsg>) valueJdkRedisTemplate.execute(sessionCallback);
	}

	public void deleteClanChatMsg(final Long clanId, final Long chatId) throws DBException {
		SessionCallback sessionCallback = new SessionCallback() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				ZSetOperations zSetOperations = operations.opsForZSet();
				String keyId = ChatMsg.getIdKeyString(clanId);
				zSetOperations.removeRangeByScore(keyId, chatId, chatId);
				return null;
			}
		};
		valueJdkRedisTemplate.execute(sessionCallback);
	}

	public void deleteClanChatMsgs(final Long clanId) throws DBException {
		SessionCallback sessionCallback = new SessionCallback() {
			@Override
			public Object execute(RedisOperations operations) throws DataAccessException {
				String keyId = ChatMsg.getIdKeyString(clanId);
				operations.delete(keyId);
				return null;
			}
		};
		valueJdkRedisTemplate.execute(sessionCallback);
	}
}
