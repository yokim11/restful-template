package com.demo.appname.api.common.exception;

public abstract class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 4385452207734760718L;

	public BaseRuntimeException() {
		super();
	}

	public BaseRuntimeException(String msg) {
		super(msg);
	}
	
	public BaseRuntimeException(Throwable t) {
		super(t);
	}

	public abstract ErrorCode errorCode();
	
	public abstract String userErrorMessage();

}
