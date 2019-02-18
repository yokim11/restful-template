package com.demo.appname.api.controller.v1.exception;

public class MyException extends Exception {
	private static final long serialVersionUID = 1867540929893472401L;

	public MyException() {
		super("내가 만든 예외");
	}
}