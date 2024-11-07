package com.knucse.common.exception.support.security;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public abstract class AuthenticationFailedException extends _SecurityException {
	private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
	public AuthenticationFailedException(final String errorCode) {
		super(errorCode);
	}
}
