package com.knucse.locker.domain.exception.locker.applyForm;

import com.knucse.common.exception.support.business.DuplicatedException;

public class ApplyFormDuplicatedException extends DuplicatedException {
	private static final String code = "APPLY_FORM_DUPLICATED";

	public ApplyFormDuplicatedException() {
		super(code);
	}
}
