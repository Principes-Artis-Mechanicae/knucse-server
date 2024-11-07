package com.knucse.locker.domain.exception.locker.allocate;

import com.knucse.common.exception.support.business.NotFoundException;

public class AllocateNotFoundException extends NotFoundException {
	private static final String code = "ALLOCATE_NOT_FOUND";

	public AllocateNotFoundException() {
		super(code);
	}
}
