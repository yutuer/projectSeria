package com.pureland.core.apis.intercepters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;

import com.pureland.common.intercepters.CommonIntercepter;
import com.pureland.common.util.AbstractResource;

/**
 * 
 * @author qinpeirong
 *
 */
public class AuthenticationIntercepter extends CommonIntercepter {

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		super.preHandle(request, response, handler);
		
		Object resource;
		if(handler instanceof HandlerMethod) {
            resource = ((HandlerMethod) handler).getBean();
        } else {
            resource = handler;
        }
		
		if(resource instanceof AbstractResource) {
			
		} 
		
		return true;
	}

}
