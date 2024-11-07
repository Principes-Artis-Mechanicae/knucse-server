package com.knucse.locker.domain.exception.locker.allocate;

import com.knucse.common.exception.support.business.DuplicatedException;

public class AllocateDuplicatedException extends DuplicatedException {
	private static final String code = "ALLOCATE_DUPLICATED";

	public AllocateDuplicatedException() {
		super(code);
	}
}
