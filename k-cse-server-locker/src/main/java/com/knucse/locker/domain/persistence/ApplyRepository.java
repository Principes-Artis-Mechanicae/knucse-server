package com.knucse.locker.domain.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.knucse.locker.domain.model.locker.apply.Apply;
import com.knucse.locker.domain.model.locker.apply.ApplyStatus;
import com.knucse.locker.domain.model.locker.applyForm.ApplyForm;
import com.knucse.student.student.model.Student;

public interface ApplyRepository extends JpaRepository<Apply, Long> {
	Optional<Apply> findByStudent(Student student);
	Optional<Apply> findByStudentAndApplyFormAndStatus(Student student, ApplyForm applyForm, ApplyStatus status);
	@EntityGraph(attributePaths = {"student"})
	List<Apply> findAllByApplyFormAndStatus(ApplyForm applyForm, ApplyStatus status);
	boolean existsByStudentAndApplyFormAndStatus(Student student, ApplyForm applyForm, ApplyStatus status);

	@EntityGraph(attributePaths = {"student"})
	List<Apply> findAllByApplyForm(ApplyForm applyForm);

	Optional<Apply> findByStudentAndApplyForm(Student student, ApplyForm applyForm);

	@Query("SELECT a FROM Apply a JOIN FETCH a.student WHERE a.id = :id")
	Optional<Apply> findByIdWithStudent(Long id);

}
