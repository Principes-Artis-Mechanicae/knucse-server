package com.knucse.locker.api.locker.apply.dto;

import com.knucse.locker.domain.model.locker.LockerFloor;
import com.knucse.locker.domain.model.locker.apply.Apply;
import com.knucse.locker.domain.model.locker.apply.ApplyHeight;
import com.knucse.locker.domain.model.locker.apply.ApplyPeriod;
import com.knucse.locker.domain.model.locker.apply.ApplyStatus;
import com.knucse.locker.domain.model.locker.applyForm.ApplyForm;
import com.knucse.student.student.model.Student;

import jakarta.validation.constraints.NotNull;

public record ApplyCreateDto(
	@NotNull String studentName, @NotNull String studentNumber,
	@NotNull LockerFloor firstFloor, @NotNull ApplyHeight firstHeight,
	@NotNull LockerFloor secondFloor, @NotNull ApplyHeight secondHeight
) {
	public Apply toEntity(Student student, ApplyPeriod period, ApplyForm applyForm) {
		return Apply.builder()
			.student(student)
			.applyForm(applyForm)
			.firstFloor(firstFloor)
			.firstHeight(firstHeight)
			.secondFloor(secondFloor)
			.secondHeight(secondHeight)
			.period(period)
			.status(
				period == ApplyPeriod.REPLACEMENT ? ApplyStatus.BROKEN_APPLY : ApplyStatus.APPLY
			).build();
	}
}
