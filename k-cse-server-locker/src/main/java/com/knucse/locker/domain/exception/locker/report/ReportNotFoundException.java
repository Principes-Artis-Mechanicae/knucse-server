package com.knucse.locker.domain.exception.locker.report;

import com.knucse.common.exception.support.business.NotFoundException;

public class ReportNotFoundException extends NotFoundException {
	private static final String code = "REPORT_NOT_FOUND";

	public ReportNotFoundException() {
		super(code);
	}
}
