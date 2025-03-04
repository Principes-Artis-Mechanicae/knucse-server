package com.knucse.locker.api.locker.apply.dto;

import com.knucse.locker.domain.model.locker.LockerFloor;
import com.knucse.locker.domain.model.locker.apply.Apply;
import com.knucse.locker.domain.model.locker.apply.ApplyHeight;
import com.knucse.locker.domain.model.locker.apply.ApplyPeriod;
import com.knucse.locker.domain.model.locker.apply.ApplyStatus;
import com.knucse.student.student.model.Student;

import lombok.Builder;

@Builder
public record ApplyReadDto(
	Long applyId,
	String studentName, String studentNumber,
	LockerFloor firstFloor, ApplyHeight firstHeight,
	LockerFloor secondFloor, ApplyHeight secondHeight,
	ApplyPeriod period, ApplyStatus status
) {
	public static ApplyReadDto fromEntity(Apply apply, Student student) {
		return ApplyReadDto.builder()
			.applyId(apply.getId())
			.studentName(student.getStudentName())
			.studentNumber(student.getStudentNumber())
			.firstFloor(apply.getFirstFloor())
			.firstHeight(apply.getFirstHeight())
			.secondFloor(apply.getSecondFloor())
			.secondHeight(apply.getSecondHeight())
			.period(apply.getPeriod())
			.status(apply.getStatus())
			.build();
	}
}
