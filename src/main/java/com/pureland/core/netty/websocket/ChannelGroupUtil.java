package com.pureland.core.netty.websocket;

import com.google.common.collect.Maps;
import com.pureland.common.protocal.BaseRespProtocal;
import com.pureland.common.util.parseExcel.MyUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;

/**
 * Created by Administrator on 2015/3/17.
 */
public class ChannelGroupUtil {

    public static final String GLOBAL = "Global";

    private static Map<String, ChannelGroup> Groups = Maps.newHashMap();

    private static Map<Long, Channel> ChannelPid2ChannelMaps = Maps.newHashMap();

    public static ChannelGroup GetChannalGroup(String name) {
        ChannelGroup channalGroup = Groups.get(name);
        if (channalGroup == null) {
            channalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
            Groups.put(name, channalGroup);
        }
        return channalGroup;
    }

    public static ChannelGroup GlabalChannalGroup = GetChannalGroup(GLOBAL);

    public static void registerChannelId(Long userRaceId, Channel channel) {
        ChannelPid2ChannelMaps.put(userRaceId, channel);
    }

    //注册给全局
    public static boolean addChannel2Global(Channel channel) {
        return GlabalChannalGroup.add(channel);
    }

    public static boolean addChannel2Group(String name, Channel channel) {
        return GetChannalGroup(name).add(channel);
    }

    public static boolean addChannel2Group(String name, Long userRaceId) {
        Channel channel = ChannelPid2ChannelMaps.get(userRaceId);
        return addChannel2Group(name, channel);
    }

    public static boolean removeChannelFromGroup(String name, Long userRaceId) {
        Channel channel = ChannelPid2ChannelMaps.get(userRaceId);
        return GetChannalGroup(name).remove(channel);
    }

    public static ChannelGroupFuture broadCastMessage2Group(String name, Object message) {
        return GetChannalGroup(name).writeAndFlush(message);
    }

    public static ChannelFuture sendMessage2Channel(Long userRaceId, BaseRespProtocal.BaseResp baseResp) {
        return ChannelPid2ChannelMaps.get(userRaceId).writeAndFlush(baseResp);
    }

    public static void removeFromPid2ChannelMaps(Long userRaceId) {
        Channel channel = ChannelPid2ChannelMaps.get(userRaceId);
        removeFromPid2ChannelMaps(channel);
    }

    public static void removeFromPid2ChannelMaps(Channel channel) {
        MyUtil.deleteMapOneEntryByValue(ChannelPid2ChannelMaps, channel);
    }
}
