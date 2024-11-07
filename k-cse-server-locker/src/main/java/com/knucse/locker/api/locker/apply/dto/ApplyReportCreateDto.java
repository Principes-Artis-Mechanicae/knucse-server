package com.knucse.locker.api.locker.apply.dto;

import jakarta.validation.constraints.NotNull;

public record ApplyReportCreateDto(
	@NotNull ApplyCreateDto apply, @NotNull String content
) {
}
