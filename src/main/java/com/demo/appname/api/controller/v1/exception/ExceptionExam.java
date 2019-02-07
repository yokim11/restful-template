package com.demo.appname.api.controller.v1.exception;

public class ExceptionExam {

	public static void main(String args[]) {
		x();
	}

	static void x() {
		y();
	}

	static void y() {
		int i = 0;
		int j = 10;
		int k = j / i;
	}

}
