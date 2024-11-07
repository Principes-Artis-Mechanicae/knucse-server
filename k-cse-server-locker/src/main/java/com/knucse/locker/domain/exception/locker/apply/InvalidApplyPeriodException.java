package com.knucse.locker.domain.exception.locker.apply;

import com.knucse.common.exception.support.business.BadRequestException;

public class InvalidApplyPeriodException extends BadRequestException {
	private static final String code = "INVALID_APPLY_PERIOD";

	public InvalidApplyPeriodException() {
		super(code);
	}
}
