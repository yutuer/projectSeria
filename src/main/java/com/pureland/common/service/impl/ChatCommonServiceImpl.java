package com.pureland.common.service.impl;

import com.pureland.common.db.dao.redis.ChatDAO;
import com.pureland.common.db.data.ChatMsg;
import com.pureland.common.db.error.DBException;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.ChatCommonService;
import com.pureland.common.util.SpringContextUtil;

import java.util.Set;

/**
 * Created by Administrator on 2015/3/19.
 */
public class ChatCommonServiceImpl implements ChatCommonService {

	private ChatDAO chatDao = (ChatDAO) SpringContextUtil.getBean(ChatDAO.class.getSimpleName());

	@Override
	public Long getNewClanId() throws CoreException {
		try {
			return chatDao.getNewClanId();
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void addChatMsg(ChatMsg chatMsg) throws CoreException {
		try {
			chatDao.addChatMsg(chatMsg);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public Set<ChatMsg> getAllClanChat(Long clanId) throws CoreException {
		try {
			return chatDao.getClanChatMsgs(clanId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public Set<ChatMsg> getRangeClanChat(Long clanId, Long nowChatId) throws CoreException {
		try {
			return chatDao.getRangeClanChat(clanId, nowChatId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public ChatMsg getChatMsg(Long clanId, Long chatId) throws CoreException {
		try {
			return chatDao.getChatMsg(clanId, chatId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void deleteClanChatMsgs(Long clanId) throws CoreException {
		try {
			chatDao.deleteClanChatMsgs(clanId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void updateChatMsg(ChatMsg chatMsg) throws CoreException {
		try {
			chatDao.updateChatMsg(chatMsg);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

	@Override
	public void deleteClanChatMsg(Long clanId, Long chatId) throws CoreException {
		try {
			chatDao.deleteClanChatMsg(clanId, chatId);
		} catch (DBException e) {
			throw new CoreException(e.getMessage());
		}
	}

}
