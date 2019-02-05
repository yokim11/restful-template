package com.demo.appname.api.common.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ErrorResponse {

	@ApiModelProperty(notes = "오류 코드", example = "INTERNAL_SERVER_ERROR", required = true)
	private ErrorCode errorCode;
	
	@ApiModelProperty(notes = "사용자 메시지", example = "내부 오류 입니다", required = true)
	private String message;

	@ApiModelProperty(notes = "에러 메시지", example = "에러 오류 메시지", required = true)
	private String error;
	
	@ApiModelProperty(notes = "시스템 메시지", example = "시스템 오류 메시지", required = true)
	private String debug;
	
	@ApiModelProperty(notes = "오류 발생시간", example = "2019-01-01 10:10:00", required = true)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;

	private ErrorResponse() {
		this.timestamp = LocalDateTime.now();
	}

	public ErrorResponse(BaseRuntimeException ex, String msg) {
		this();
		this.message = msg;
		this.error = ex.getLocalizedMessage();
		this.errorCode = ex.errorCode();
		this.debug = "";
	}

	public ErrorResponse(BaseRuntimeException ex, String msg, String req, String debug) {
		this();
		this.message = msg;
		this.debug = debug;
		this.error = ex.getCause() != null ? ex.getCause().toString() : ex.getMessage();
		this.errorCode = ex.errorCode();
	}
}
