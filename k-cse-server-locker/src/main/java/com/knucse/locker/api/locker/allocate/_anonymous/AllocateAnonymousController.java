package com.knucse.locker.api.locker.allocate._anonymous;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knucse.common.util.api.ApiResponseUtil;
import com.knucse.common.util.api.ApiSuccessResult;
import com.knucse.locker.api.locker.allocate.dto.AllocateReadDto;
import com.knucse.locker.domain.service.locker.allocate.AllocateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
@Tag(name = "사물함 할당 (사용자)", description = "사용자 사물함 배정 API")
public class AllocateAnonymousController {
	private final AllocateService allocateService;

	@GetMapping("/allocate/{studentNumber}")
	@Operation(summary = "특정 학생 배정 된 사물함 조회", description = "사용자는 특정 학번의 배정된 사물함 정보를 확인할 수 있습니다.")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "배정 사물함 조회 성공"),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사물함 배정 정보를 찾을 수 없음 (code: ALLOCATE_NOT_FOUND)")
	})
	public ResponseEntity<ApiSuccessResult<AllocateReadDto>> getAllocateForm(
		@PathVariable String studentNumber
	) {
		AllocateReadDto responseBody = allocateService.getAllocateForm(studentNumber);
		return ResponseEntity
			.status(HttpStatus.OK)
			.body(ApiResponseUtil.success(HttpStatus.OK, responseBody));
	}
}
