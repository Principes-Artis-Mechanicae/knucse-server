package com.knucse.locker.api.locker.applyForm.dto;

import com.knucse.common.util.datetime.DateTimeUtil;
import com.knucse.locker.domain.model.locker.applyForm.ApplyForm;
import com.knucse.locker.domain.model.locker.applyForm.ApplyFormStatus;

import lombok.Builder;

@Builder
public record ApplyFormReadDto(
	Long applyFormId,
	Integer year, Integer semester,
	String firstApplyStartDate, String firstApplyEndDate,
	String semesterEndDate, ApplyFormStatus status
) {
	public static ApplyFormReadDto fromEntity(ApplyForm applyForm) {
		return ApplyFormReadDto.builder()
			.applyFormId(applyForm.getId())
			.year(applyForm.getYear())
			.semester(applyForm.getSemester())
			.firstApplyStartDate(DateTimeUtil.localDateTimeToString(applyForm.getFirstApplyStartDate()))
			.firstApplyEndDate(DateTimeUtil.localDateTimeToString(applyForm.getFirstApplyEndDate()))
			.semesterEndDate(DateTimeUtil.localDateTimeToString(applyForm.getSemesterEndDate()))
			.status(applyForm.getStatus())
			.build();
	}
}
