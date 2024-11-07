package com.knucse.locker.domain.exception.locker.applyForm;

import com.knucse.common.exception.support.business.BadRequestException;

public class InvalidApplyFormDateException extends BadRequestException {
	private static final String code = "INVALID_APPLY_FORM_DATE";

	public InvalidApplyFormDateException() {
		super(code);
	}
}
