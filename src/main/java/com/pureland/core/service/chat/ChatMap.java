package com.pureland.core.service.chat;


import java.util.Map;

/**
 * Created by Administrator on 2015/3/19.
 */
public class ChatMap {
    private Map<String, ChatLogicService> chatMap;

    public Map<String, ChatLogicService> getChatMap() {
        return chatMap;
    }

    public void setChatMap(Map<String, ChatLogicService> chatMap) {
        this.chatMap = chatMap;
    }
}
