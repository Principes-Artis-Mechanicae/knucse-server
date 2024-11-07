package com.knucse.locker.api.locker.apply.dto;

import jakarta.validation.constraints.NotNull;

public record ReportStatusUpdateDto(
	@NotNull Long reportId, @NotNull boolean isApproved
) {
}
