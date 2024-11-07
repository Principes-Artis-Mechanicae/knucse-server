package com.knucse.locker.api.locker.applyForm.dto;

import com.knucse.common.util.datetime.DateTimeUtil;
import com.knucse.locker.domain.model.locker.applyForm.ApplyForm;
import com.knucse.locker.domain.model.locker.applyForm.ApplyFormStatus;

import jakarta.validation.constraints.NotNull;


public record ApplyFormCreateDto(
	@NotNull Integer year,
	@NotNull Integer semester,
	@NotNull String firstApplyStartDate,
	@NotNull String firstApplyEndDate,
	@NotNull String semesterEndDate
) {
	public ApplyForm toEntity() {
		return ApplyForm.builder()
			.year(year)
			.semester(semester)
			.firstApplyStartDate(DateTimeUtil.stringToLocalDateTime(firstApplyStartDate))
			.firstApplyEndDate(DateTimeUtil.stringToLocalDateTime(firstApplyEndDate))
			.semesterEndDate(DateTimeUtil.stringToLocalDateTime(semesterEndDate))
			.status(ApplyFormStatus.INACTIVE)
			.build();
	}
}
