package com.knucse.common.exception.handler;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.knucse.common.exception.BaseExceptionHandler;
import com.knucse.common.exception.ExceptionHandlerOrder;
import com.knucse.common.exception.support.business._ApplicationLogicException;
import com.knucse.common.exception.support.business.BadRequestException;
import com.knucse.common.exception.support.business.DuplicatedException;
import com.knucse.common.exception.support.business.NotFoundException;
import com.knucse.common.util.api.ApiErrorResult;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Order(ExceptionHandlerOrder.BUSINESS_EXCEPTION_HANDLER)
public class BusinessExceptionHandler extends BaseExceptionHandler<Exception> {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiErrorResult> handleBadRequestException(BadRequestException exception) {
		return handleException(exception, exception.getHttpStatus(), exception.getErrorCode());
	}

	@ExceptionHandler(DuplicatedException.class)
	public ResponseEntity<ApiErrorResult> handleDuplicatedException(DuplicatedException exception) {
		return handleException(exception, exception.getHttpStatus(), exception.getErrorCode());
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ApiErrorResult> handleNotFoundException(NotFoundException exception) {
		return handleException(exception, exception.getHttpStatus(), exception.getErrorCode());
	}

	@ExceptionHandler(_ApplicationLogicException.class)
	public ResponseEntity<ApiErrorResult> handleApplicationLogicException(_ApplicationLogicException exception) {
		return handleException(exception, exception.getHttpStatus(), exception.getErrorCode());
	}
}
