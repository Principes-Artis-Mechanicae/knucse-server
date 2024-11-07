package com.knucse.common.exception.support.business;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public abstract class DuplicatedException extends _ApplicationLogicException {
	private final HttpStatus httpStatus = HttpStatus.CONFLICT;
	public DuplicatedException(final String errorCode) {
		super(errorCode);
	}
}
