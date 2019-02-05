package com.demo.appname.api.common.exception;

import java.util.Iterator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandlingControllerAdvice {

	public static final String DEFAULT_ERROR_MESSAGE = "예상치 못한 오류 - 오류 코드 없음 ";
	
	public GlobalExceptionHandlingControllerAdvice() {
	}
	
	@ExceptionHandler({ DataNotFoundException.class })
	public ResponseEntity<Object> dataNotFoundHandler(RuntimeException ex, WebRequest request) {
		log.error("DataNotFoundException");
		
		BaseRuntimeException e = null;
		if (ex instanceof BaseRuntimeException) {
			e = (BaseRuntimeException) ex;
		} else {
			e = new InternalServerException(ex);
		}
		return buildResponse(e, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}


	@ResponseBody
	public ResponseEntity<Object> buildResponse(BaseRuntimeException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (ex != null && ex instanceof BaseRuntimeException) {
			String msg = ex.userErrorMessage();
			String req = generateRequestMessage(request, headers);
			StringBuffer debug = new StringBuffer();
			if (ex != null) {
				StackTraceElement ele = ex.getStackTrace()[0];
				if (ele != null) {
					debug.append(ele.getFileName() + "[" + ele.getLineNumber() + "] " + ele.getClassName() + " - ["
							+ ele.getMethodName() + "]\n");
				}
			}
			if (ex.getCause() != null) {
				StackTraceElement ele = ex.getCause().getStackTrace()[0];
				if (ele != null) {
					debug.append(ele.getFileName() + "[" + ele.getLineNumber() + "] " + ele.getClassName() + " - ["
							+ ele.getMethodName() + "]");
				}
			}
			ErrorResponse res = new ErrorResponse(ex, msg, req, debug.toString());
			if (!ErrorCode.DATA_NOT_FOUND.equals(ex.errorCode()))
				log.error(res.toString());
			return new ResponseEntity<>(res, status);
		}
		
		InternalServerException e = new InternalServerException(ex);
		return new ResponseEntity<>(new ErrorResponse(e, DEFAULT_ERROR_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	private static String generateRequestMessage(WebRequest request, HttpHeaders headers) {
		StringBuffer str = new StringBuffer();
		if (request != null) {
			str.append(request.getDescription(false));
			Iterator<String> names = request.getHeaderNames();
			str.append(" | headers : ");
			while (names.hasNext()) {
				String name = names.next();
				String value = request.getHeader(name);
				str.append(name + "=" + value + "&");
			}
			str.deleteCharAt(str.length() - 1);
		}
		if (request != null) {
			Iterator<String> names = request.getParameterNames();
			if (names.hasNext()) {
				str.append(" | parameters : ");
				String name = names.next();
				String value = request.getParameter(name);
				str.append(name + "=" + value + "&");
				while (names.hasNext()) {
					name = names.next();
					value = request.getParameter(name);
					str.append(name + "=" + value + "&");
				}
				str.deleteCharAt(str.length() - 1);
			}
		}
		return str.toString();
	}
}
