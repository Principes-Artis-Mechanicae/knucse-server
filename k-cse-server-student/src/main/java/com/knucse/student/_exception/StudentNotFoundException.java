package com.knucse.student._exception;

import com.knucse.common.exception.support.business.NotFoundException;

public class StudentNotFoundException extends NotFoundException {
	private static final String code = "STUDENT_NOT_FOUND";

	public StudentNotFoundException() {
		super(code);
	}
}
