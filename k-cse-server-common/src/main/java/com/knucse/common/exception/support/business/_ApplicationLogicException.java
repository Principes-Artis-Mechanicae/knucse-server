package com.knucse.common.exception.support.business;

import org.springframework.http.HttpStatus;

import com.knucse.common.exception.support.custom._CustomException;

import lombok.Getter;

@Getter
public abstract class _ApplicationLogicException extends _CustomException {
	private final HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	private final String errorCode;

	public _ApplicationLogicException(final String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}
}
