package com.knucse.common.exception.support.custom;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class _CustomException extends RuntimeException {
	private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	private final String errorCode;

	public _CustomException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}
}
