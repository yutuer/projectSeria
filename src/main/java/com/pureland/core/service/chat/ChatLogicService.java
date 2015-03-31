package com.pureland.core.service.chat;

import com.pureland.common.db.data.ChatMsg;
import com.pureland.common.error.CoreException;
import com.pureland.common.service.bean.chat.ChatBean;

import java.util.List;

/**
 * Created by Administrator on 2015/3/19.
 */
public interface ChatLogicService {
    List<ChatMsg> chatLogic(ChatBean chatBean) throws CoreException;
}
