package com.knucse.common.exception.handler;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.knucse.common.exception.BaseExceptionHandler;
import com.knucse.common.exception.ExceptionHandlerOrder;
import com.knucse.common.util.api.ApiErrorResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Order(ExceptionHandlerOrder.GLOBAL_EXCEPTION_HANDLER)
public class GlobalExceptionHandler extends BaseExceptionHandler<Exception> {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiErrorResult> handleException(Exception exception) {
		log.error("Unhandled exception occurred", exception);
		return handleException(exception, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
	}
}
