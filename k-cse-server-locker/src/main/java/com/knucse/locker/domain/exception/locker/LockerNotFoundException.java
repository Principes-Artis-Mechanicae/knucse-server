package com.knucse.locker.domain.exception.locker;

import com.knucse.common.exception.support.business.NotFoundException;

public class LockerNotFoundException extends NotFoundException {
	private static final String code = "LOCKER_NOT_FOUND";

	public LockerNotFoundException() {
		super(code);
	}
}
