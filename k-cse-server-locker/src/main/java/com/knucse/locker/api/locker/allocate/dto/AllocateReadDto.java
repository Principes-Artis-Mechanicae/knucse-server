package com.knucse.locker.api.locker.allocate.dto;

import com.knucse.locker.api.locker.apply.dto.ApplyReadDto;
import com.knucse.locker.api.locker.applyForm.dto.ApplyFormReadDto;
import com.knucse.locker.domain.model.locker.Locker;
import com.knucse.locker.domain.model.locker.LockerFloor;
import com.knucse.locker.domain.model.locker.apply.Apply;
import com.knucse.locker.domain.model.locker.applyForm.ApplyForm;
import com.knucse.student.student.model.Student;

import lombok.Builder;

@Builder
public record AllocateReadDto(
	String studentName, String studentNumber,
	String lockerName, LockerFloor floor, Integer height, String pw, Boolean broken,
	ApplyReadDto apply, ApplyFormReadDto applyForm
) {
	public static AllocateReadDto fromEntity(Student student, Apply apply, ApplyForm applyForm, Locker locker) {
		return AllocateReadDto.builder()
			.studentName(student.getStudentName())
			.studentNumber(student.getStudentNumber())
			.lockerName(locker.getLockerName())
			.floor(locker.getFloor())
			.height(locker.getHeight())
			.pw(locker.getPw())
			.broken(locker.getBroken())
			.apply(ApplyReadDto.fromEntity(apply, student))
			.applyForm(ApplyFormReadDto.fromEntity(applyForm))
			.build();
	}
}
