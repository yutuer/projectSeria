package com.pureland.common.service;

import com.pureland.common.db.data.ChatMsg;
import com.pureland.common.error.CoreException;

import java.util.Set;

/**
 * Created by Administrator on 2015/3/19.
 */
public interface ChatCommonService {
	Long getNewClanId() throws CoreException;

	void addChatMsg(ChatMsg chatMsg) throws CoreException;

	Set<ChatMsg> getAllClanChat(Long clanId) throws CoreException;

	Set<ChatMsg> getRangeClanChat(Long clanId, Long nowChatId) throws CoreException;

	ChatMsg getChatMsg(final Long clanId, final Long chatId) throws CoreException;

	void deleteClanChatMsgs(final Long clanId) throws CoreException;

	void updateChatMsg(final ChatMsg chatMsg) throws CoreException;

	void deleteClanChatMsg(Long clanId, final Long chatId) throws CoreException;

}
