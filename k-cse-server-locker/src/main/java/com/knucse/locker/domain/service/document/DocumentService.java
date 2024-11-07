package com.knucse.locker.domain.service.document;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knucse.locker.api.locker.allocate.dto.AllocateReadDto;
import com.knucse.locker.domain.model.locker.allocate.Allocate;
import com.knucse.locker.domain.model.locker.applyForm.ApplyForm;
import com.knucse.student.student.model.Student;
import com.knucse.locker.domain.persistence.AllocateRepository;
import com.knucse.locker.domain.service.locker.applyForm.ApplyFormService;
import com.knucse.student.student.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentService {
	private final StudentService studentService;
	private final ApplyFormService applyFormService;
	private final AllocateRepository allocateRepository;

	public List<AllocateReadDto> getAllStudentAllocateForm() {
		// 모든 학생을 한 번에 조회
		List<Student> students = studentService.findAllStudents();

		// 활성화된 ApplyForm을 루프 외부에서 한 번만 조회
		ApplyForm applyForm = applyFormService.getActiveApplyForm();

		// 학생 리스트로부터 Allocate를 한 번에 조회
		List<Allocate> allocates = allocateRepository.findByApplyFormAndStudentIn(applyForm, students);

		// Allocate를 DTO로 변환하여 반환
		return allocates.stream()
			.map(allocate -> AllocateReadDto.fromEntity(
				allocate.getStudent(),
				allocate.getApply(),
				applyForm,
				allocate.getLocker()))
			.collect(Collectors.toList());
	}
}
