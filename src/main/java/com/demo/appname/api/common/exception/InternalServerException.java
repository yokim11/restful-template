package com.demo.appname.api.common.exception;

public class InternalServerException extends BaseRuntimeException {

	private static final long serialVersionUID = -8543825793659795871L;

	@Override
	public ErrorCode errorCode() {
		return ErrorCode.INTERNAL_ERROR;
	}

	@Override
	public String userErrorMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public InternalServerException(Throwable t) {
		super(t);
	}
}
