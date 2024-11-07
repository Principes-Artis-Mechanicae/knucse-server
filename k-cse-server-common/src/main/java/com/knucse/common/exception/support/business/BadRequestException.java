package com.knucse.common.exception.support.business;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public abstract class BadRequestException extends _ApplicationLogicException {
	private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
	public BadRequestException(final String errorCode) {
		super(errorCode);
	}
}
