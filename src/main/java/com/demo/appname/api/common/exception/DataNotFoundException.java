package com.demo.appname.api.common.exception;

public class DataNotFoundException extends BaseRuntimeException {

	private static final long serialVersionUID = 3678378493919631684L;

	@Override
	public ErrorCode errorCode() {
		return ErrorCode.DATA_NOT_FOUND;
	}

	public DataNotFoundException() {
		super();
	}


	public DataNotFoundException(String msg) {
		super(msg);
	}
	
	@Override
	public String userErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
