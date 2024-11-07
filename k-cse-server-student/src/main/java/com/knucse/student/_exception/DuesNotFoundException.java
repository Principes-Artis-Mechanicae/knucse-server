package com.knucse.student._exception;

import com.knucse.common.exception.support.business.NotFoundException;

public class DuesNotFoundException extends NotFoundException {
	private static final String code = "DUES_NOT_FOUND";

	public DuesNotFoundException() {
		super(code);
	}
}
