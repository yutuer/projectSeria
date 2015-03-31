package com.pureland.common.error;

public class CommonException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7344002560950342599L;

	private Integer code;

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public CommonException(int code, String message, Throwable throwable) {
		super(message, throwable);
		this.code = code;
	}

	public CommonException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public CommonException(int code, String message) {
		super(message);
		this.code = code;
	}

	public CommonException(String message) {
		super(message);
	}

	public CommonException(Throwable arg0) {
		super(arg0);
	}
}
