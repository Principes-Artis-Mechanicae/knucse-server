package com.knucse.student.student.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knucse.student.student.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findStudentById(Long id);
	Optional<Student> findByStudentNameAndStudentNumber(String studentName, String studentNumber);
	Optional<Student> findByStudentNumber(String studentNumber);
}
