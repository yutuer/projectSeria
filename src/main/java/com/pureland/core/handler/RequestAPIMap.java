package com.pureland.core.handler;

import java.util.Map;

public class RequestAPIMap {

	private Map<String, RequestAPIHandler> apiHandlerMap;

	/**
	 * @return the apiHandlerMap
	 */
	public Map<String, RequestAPIHandler> getApiHandlerMap() {
		return apiHandlerMap;
	}

	/**
	 * @param apiHandlerMap
	 *            the apiHandlerMap to set
	 */
	public void setApiHandlerMap(Map<String, RequestAPIHandler> apiHandlerMap) {
		this.apiHandlerMap = apiHandlerMap;
	}

}
