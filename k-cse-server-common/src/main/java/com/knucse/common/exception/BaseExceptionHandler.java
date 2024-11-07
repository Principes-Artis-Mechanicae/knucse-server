package com.knucse.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.knucse.common.util.api.ApiErrorResult;
import com.knucse.common.util.api.ApiResponseUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BaseExceptionHandler<T extends Throwable> {

	protected ResponseEntity<ApiErrorResult> handleException(T exception, HttpStatus status, String errorCode) {
		log.debug(exception.getMessage(), exception);
		return ResponseEntity.status(status)
			.body(ApiResponseUtil.error(status, errorCode));
	}
}
