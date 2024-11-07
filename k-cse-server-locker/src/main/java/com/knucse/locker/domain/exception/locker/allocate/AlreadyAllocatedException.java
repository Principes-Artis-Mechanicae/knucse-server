package com.knucse.locker.domain.exception.locker.allocate;

import com.knucse.common.exception.support.business.BadRequestException;

public class AlreadyAllocatedException extends BadRequestException {
	private static final String code = "ALREADY_ALLOCATED";

	public AlreadyAllocatedException() {
		super(code);
	}
}
