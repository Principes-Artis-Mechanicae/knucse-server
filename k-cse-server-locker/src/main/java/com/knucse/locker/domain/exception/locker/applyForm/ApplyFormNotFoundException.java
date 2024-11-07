package com.knucse.locker.domain.exception.locker.applyForm;

import com.knucse.common.exception.support.business.NotFoundException;

public class ApplyFormNotFoundException extends NotFoundException {
	private static final String code = "APPLY_FORM_NOT_FOUND";

	public ApplyFormNotFoundException() {
		super(code);
	}
}
