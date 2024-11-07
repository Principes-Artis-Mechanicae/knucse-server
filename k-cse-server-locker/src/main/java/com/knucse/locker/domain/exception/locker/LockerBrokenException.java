package com.knucse.locker.domain.exception.locker;

import com.knucse.common.exception.support.business.BadRequestException;

public class LockerBrokenException extends BadRequestException {
	private static final String code = "LOCKER_BROKEN";

	public LockerBrokenException() {
		super(code);
	}
}
