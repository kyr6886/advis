package com.noaa.base;

import org.springframework.http.HttpStatus;

public enum MessageStatus {
	
	KEY_DUPLICATED(9101, "이미 사용중인 값 입니다."),
	AUTH_REQUIRED(9201, "로그인후 이용 가능합니다."),
	AUTH_DENY(9202, "접근 권한이 없습니다."),
	AUTH_FAIL(9203, "입력 정보를 확인하세요."),
	AUTH_LOCK(9204, "계정이 잠겼습니다."),
	AUTH_PASSFAIL(9205, "비밀번호를 확인해주세요."),
	VALID_REQUIRED(9301,"필수 입력 항목을 확인하세요"),
	VALID_EXT_IMG(9501,"이미지만 업로드 가능합니다."),
	VALID_EXT_DOC(9601,"문서파일만 업로드 가능합니다."),
	VALID_NOT_FILE(9701,"필요한 파일을 업로드 하지않았습니다.");
	
	
	
	private final int value;
	private final String reasonPhrase;
	
	private MessageStatus(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public int value() {
		return this.value;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return reasonPhrase;
	}
	
	@Override
	public String toString() {
		return Integer.toString(value);
	}
	
	public static MessageStatus valueOf(int statusCode) {
		for (MessageStatus status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		return null;
		//throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}

}
