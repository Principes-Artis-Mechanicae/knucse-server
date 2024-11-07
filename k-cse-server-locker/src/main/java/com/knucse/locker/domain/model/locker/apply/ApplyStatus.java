package com.knucse.locker.domain.model.locker.apply;

public enum ApplyStatus {
	APPLY, APPROVE, REJECT, BROKEN_APPLY;

	public boolean isApply() {
		return this == APPLY;
	}
}
