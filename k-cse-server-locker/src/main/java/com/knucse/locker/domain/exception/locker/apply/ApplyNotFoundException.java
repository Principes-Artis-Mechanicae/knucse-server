package com.knucse.locker.domain.exception.locker.apply;

import com.knucse.common.exception.support.business.NotFoundException;

public class ApplyNotFoundException extends NotFoundException {
	private static final String code = "APPLY_NOT_FOUND";

	public ApplyNotFoundException() {
		super(code);
	}
}
