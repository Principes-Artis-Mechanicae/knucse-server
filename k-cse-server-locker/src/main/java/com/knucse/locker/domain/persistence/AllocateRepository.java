package com.knucse.locker.domain.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.knucse.locker.domain.model.locker.allocate.Allocate;
import com.knucse.locker.domain.model.locker.applyForm.ApplyForm;
import com.knucse.student.student.model.Student;

public interface AllocateRepository extends JpaRepository<Allocate, Long> {
	boolean existsByStudentAndApplyForm(Student student, ApplyForm applyForm);
	void deleteByStudentAndApplyForm(Student student, ApplyForm applyForm);
	List<Allocate> findAllByApplyForm(ApplyForm applyForm);

	@EntityGraph(attributePaths = {"student", "locker", "apply", "applyForm"})
	Optional<Allocate> findByStudentAndApplyForm(Student student, ApplyForm applyForm);
	List<Allocate> findByApplyFormAndStudentIn(ApplyForm applyForm, List<Student> students);
}
