package com.knucse.locker.domain.exception.locker;

import com.knucse.common.exception.support.business.NotFoundException;

public class LockerFullNotFoundException extends NotFoundException {
	private static final String code = "LOCKER_FULL_NOT_FOUND";

	public LockerFullNotFoundException() {
		super(code);
	}
}
