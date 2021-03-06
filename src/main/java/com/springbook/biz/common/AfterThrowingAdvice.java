package com.springbook.biz.common;

import org.aspectj.lang.JoinPoint;
public class AfterThrowingAdvice {
	public void exceptionLog(JoinPoint jp, Exception e) {
		String method = jp.getSignature().getName();

		System.out.println(method + "() 메소드 수행 중 예외 발생!");

		if (e instanceof IllegalArgumentException) {
			System.out.println("부적합한 값이 입력되었습니다.");
		} else if (e instanceof NumberFormatException) {
			System.out.println("숫자 형식의 값이 아닙니다.");
		} else if (e instanceof Exception) {
			System.out.println("문제가 발생했습니다.");

		}
	}
}
