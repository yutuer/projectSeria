package com.pureland.core.apis.rest;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pureland.common.error.InFightException;
import com.pureland.common.error.InOffLineException;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.BaseReqProtocal;
import com.pureland.common.protocal.BaseReqProtocal.BaseReq;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper;
import com.pureland.common.protocal.ReqWrapperProtocal.ReqWrapper.RequestType;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;
import com.pureland.common.util.AbstractResource;
import com.pureland.common.util.SpringContextUtil;
import com.pureland.core.handler.RequestAPIHandler;
import com.pureland.core.handler.RequestAPIMap;
import com.pureland.core.service.VerifyService;
import com.pureland.core.service.impl.VerifyServiceImpl;

@Controller
@RequestMapping("/apis")
public class RequestWrapper extends AbstractResource {
	private String TAG = PurelandLog.getClassTag(RequestWrapper.class);
	private RequestAPIMap requestAPIMap = (RequestAPIMap) SpringContextUtil.getBean(RequestAPIMap.class.getSimpleName());
	private VerifyService verifyService = (VerifyService) SpringContextUtil.getBean(VerifyServiceImpl.class.getSimpleName());

	@RequestMapping(value = "/reqWrapper", method = RequestMethod.POST)
	public void reqWrapper(@RequestParam(value = "cb", required = false) String callback, HttpServletRequest request, HttpServletResponse response) {
		try {
			InputStream is = parseFromRequest(request);
			BaseReq baseReq = BaseReqProtocal.BaseReq.parseFrom(is);
			PurelandLog.info(TAG, "请求:\n" + baseReq.toString());
			String authToken = baseReq.getAuthToken();
			Integer sequenceId = baseReq.getSequenceId();
			Long timestamp = baseReq.getTimestamp();
			PurelandLog.info(TAG, "之前--------------------------------------");
			verifyService.dealCompareResult(baseReq.getBefore());
			ReqWrapper reqWrapper = baseReq.getReqWrapper();
			RequestType requestType = reqWrapper.getRequestType();
			RequestAPIHandler handler = requestAPIMap.getApiHandlerMap().get(requestType.toString());
			RespWrapper respWrapper = handler.handler0(reqWrapper, authToken, timestamp);
			PurelandLog.info(TAG, "之后--------------------------------------");
			verifyService.dealCompareResult(baseReq.getAfter());
			PurelandLog.info(TAG, "响应:\n" + respWrapper.toString());
			SUCCESS(request, response, respWrapper).respond();
		} catch (InOffLineException e) {
			e.printStackTrace();
			PurelandLog.error(e.getMessage());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream s = new PrintStream(baos);
			e.printStackTrace(s);
			INOFFLINE(request, response, baos.toString()).respond();
		} catch (InFightException e) {
			e.printStackTrace();
			PurelandLog.error(e.getMessage());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream s = new PrintStream(baos);
			e.printStackTrace(s);
			INFIGHT(request, response, baos.toString()).respond();
		} catch (Exception e) {
			e.printStackTrace();
			PurelandLog.error(e.getMessage());
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream s = new PrintStream(baos);
			e.printStackTrace(s);
			FAILED(request, response, baos.toString()).respond();
		}
	}

}