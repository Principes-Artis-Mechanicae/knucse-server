package com.knucse.locker.domain.exception.locker.apply;

import com.knucse.common.exception.support.business.DuplicatedException;

public class ApplyDuplicatedException extends DuplicatedException {
	private static final String code = "APPLY_DUPLICATED";

	public ApplyDuplicatedException() {
		super(code);
	}
}
