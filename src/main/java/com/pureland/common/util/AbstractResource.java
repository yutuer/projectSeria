package com.pureland.common.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.google.protobuf.GeneratedMessage;
import com.pureland.common.log.PurelandLog;
import com.pureland.common.protocal.BaseRespProtocal;
import com.pureland.common.protocal.BaseRespProtocal.BaseResp;
import com.pureland.common.protocal.BaseRespProtocal.BaseResp.ErrorType;
import com.pureland.common.protocal.RespWrapperProtocal.RespWrapper;

/**
 * 
 * @author qinpeirong
 *
 */
public abstract class AbstractResource extends AbstractController {
	private static String TAG = PurelandLog.getClassTag(AbstractResource.class);

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static InputStream parseFromRequest(HttpServletRequest request) {
		InputStream is = null;
		
		try {
			is = new BufferedInputStream(request.getInputStream());
			String contentLength   = request.getHeader("Content-Length");
			PurelandLog.debug("Request contentLength = " + contentLength);
			if(contentLength != null && contentLength.equals("0")) {
				return null;
			}
			
		} catch (IOException e) {
			PurelandLog.error(TAG, e.getMessage());
		} 
		return is;
	}
	
	
	public static BasicMessageEx<BaseResp> SUCCESS(HttpServletRequest request, HttpServletResponse response, RespWrapper respWrapper) {
		BaseResp baseResp = BaseRespProtocal.BaseResp.newBuilder().setErrorType(ErrorType.OK).setRespWrapper(respWrapper).build();
		return new BasicMessageEx<BaseResp>(request, response, baseResp);
		
	}
	
	public static BasicMessageEx<BaseResp> FAILED(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
		BaseResp baseResp = BaseRespProtocal.BaseResp.newBuilder().setErrorType(ErrorType.AuthFailed).setErrorMessage(errorMessage).build();
		return new BasicMessageEx<BaseResp>(request, response, baseResp);
	}
	public static BasicMessageEx<BaseResp> INFIGHT(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
		BaseResp baseResp = BaseRespProtocal.BaseResp.newBuilder().setErrorType(ErrorType.IsInFight).setErrorMessage(errorMessage).build();
		return new BasicMessageEx<BaseResp>(request, response, baseResp);
	}
	public static BasicMessageEx<BaseResp> INOFFLINE(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
		BaseResp baseResp = BaseRespProtocal.BaseResp.newBuilder().setErrorType(ErrorType.IsOffLine).setErrorMessage(errorMessage).build();
		return new BasicMessageEx<BaseResp>(request, response, baseResp);
	}

	public static class BasicMessageEx<T extends GeneratedMessage> {
		private HttpServletRequest request;
		private HttpServletResponse response;
		private T message;
		public BasicMessageEx(HttpServletRequest request,
				HttpServletResponse response, T message) {
			super();
			this.request = request;
			this.response = response;
			this.message = message;
		}
		
		public void respond() {
			OutputStream os = null;
			try {
				os = response.getOutputStream();
				message.writeTo(os);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				IOUtils.closeQuietly(os);
			}
		}
	}

}
