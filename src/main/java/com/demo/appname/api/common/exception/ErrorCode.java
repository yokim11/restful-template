package com.demo.appname.api.common.exception;

import lombok.Getter;

public enum ErrorCode {
	
	DATA_NOT_FOUND("DATA_NOT_FOUND"),
	INTERNAL_ERROR("INTERNAL_ERROR"),
	CUSTOM_ERROR("CUSTOM_ERROR");
	
	@Getter
	private final String value;
	
	ErrorCode(String message) {
		this.value = message.toUpperCase();
	}
	
}
