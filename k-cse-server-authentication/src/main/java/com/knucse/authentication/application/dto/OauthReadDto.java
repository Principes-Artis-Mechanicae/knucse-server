package com.knucse.authentication.application.dto;

import com.knucse.authentication.application.entity.OAuthUserInfo;
import com.knucse.student.student.api.dto.StudentReadDto;
import com.knucse.student.student.model.Student;

import lombok.Builder;

@Builder
public record OauthReadDto(
	String email, String provider,
	String providerId, boolean isConnectedToStudent,
	StudentReadDto student
) {
	public static OauthReadDto fromEntity(Student student, OAuthUserInfo oAuthUserInfo) {
		boolean isConnectedToStudent = student != null;
		StudentReadDto studentDto = isConnectedToStudent ? StudentReadDto.fromEntity(student) : null;

		return OauthReadDto.builder()
			.email(oAuthUserInfo.getEmail())
			.provider(oAuthUserInfo.getProvider())
			.providerId(oAuthUserInfo.getProviderId())
			.isConnectedToStudent(isConnectedToStudent)
			.student(studentDto)
			.build();
	}
}
