package com.pureland.core.netty.websocket;

import com.pureland.common.error.InFightException;
import com.pureland.common.error.InOffLineException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.BaseReqProtocal;
import com.pureland.common.protocal.BaseRespProtocal;
import com.pureland.common.protocal.ReqWrapperProtocal;
import com.pureland.common.protocal.RespWrapperProtocal;
import com.pureland.common.service.helper.ChatHelper;
import com.pureland.common.util.parseExcel.MyUtil;
import com.pureland.core.handler.RequestAPIHandler;

import io.netty.channel.ChannelHandlerContext;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Administrator on 2015/3/19.
 */
public class WebSocketNoVerifyHandler extends WebSocketHandler {

	private static String TAG = PurelandLog.getClassTag(WebSocketNoVerifyHandler.class);

	@Override
	protected void messageReceived(final ChannelHandlerContext ctx, BaseReqProtocal.BaseReq baseReq) throws Exception {
		RespWrapperProtocal.RespWrapper respWrapper = null;
		try {
			PurelandLog.info(TAG, "请求:\n" + baseReq.toString());
			String authToken = baseReq.getAuthToken();
			Integer sequenceId = baseReq.getSequenceId();
			Long timestamp = baseReq.getTimestamp();
			// 这里先校验下时间戳
			// if (GameUtil.IsTimeWrong(System.currentTimeMillis(), timestamp))
			// {
			// throw new CoreException("时间戳验证错误, reqin:%d, now:%d", timestamp,
			// System.currentTimeMillis());
			// }
			ReqWrapperProtocal.ReqWrapper reqWrapper = baseReq.getReqWrapper();
			ReqWrapperProtocal.ReqWrapper.RequestType requestType = reqWrapper.getRequestType();

			RequestAPIHandler handler = requestAPIMap.getApiHandlerMap().get(requestType.toString());
			respWrapper = handler.handler0(reqWrapper, authToken, timestamp);

			if (respWrapper.hasLoginResp()) {
				Long userRaceId = respWrapper.getLoginResp().getUserId();
				ChannelGroupUtil.registerChannelId(userRaceId, ctx.channel());
				ChannelGroupUtil.addChannel2Group(ChatHelper.getWorldChatChannel(userRaceId), ctx.channel());
				if (respWrapper.getLoginResp().hasClanId()) {
					Long clanId = respWrapper.getLoginResp().getClanId();
					ChannelGroupUtil.addChannel2Group(ChatHelper.getClanChatChannel(clanId), ctx.channel());
				}
			}
			PurelandLog.info(TAG, "响应:\n" + respWrapper.toString());
			sendSuccess2Client(ctx, respWrapper);
		} catch (InOffLineException e) {
			PurelandLog.error(e.getMessage());
			PurelandLog.error(MyUtil.getStackMessage(e));
			sendError2Client(ctx, BaseRespProtocal.BaseResp.ErrorType.IsOffLine, e);
		} catch (InFightException e) {
			PurelandLog.error(e.getMessage());
			PurelandLog.error(MyUtil.getStackMessage(e));
			sendError2Client(ctx, BaseRespProtocal.BaseResp.ErrorType.IsInFight, e);
		} catch (Exception e) {
			PurelandLog.error(e.getMessage());
			PurelandLog.error(MyUtil.getStackMessage(e));
			sendError2Client(ctx, BaseRespProtocal.BaseResp.ErrorType.AuthFailed, e);
		}
	}

}
