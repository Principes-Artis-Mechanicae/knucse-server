package com.knucse.locker.api.locker.apply.dto;

import com.knucse.locker.domain.model.locker.LockerFloor;
import com.knucse.locker.domain.model.locker.apply.ApplyHeight;
import com.knucse.locker.domain.model.locker.apply.ApplyPeriod;
import com.knucse.locker.domain.model.locker.apply.ApplyStatus;

public record ApplyUpdateDto(
	String studentName, String studentNumber,
	LockerFloor firstFloor, ApplyHeight firstHeight,
	LockerFloor secondFloor, ApplyHeight secondHeight,
	ApplyPeriod period, ApplyStatus status
) {
}
