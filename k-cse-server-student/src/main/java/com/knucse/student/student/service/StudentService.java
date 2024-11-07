package com.knucse.student.student.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knucse.student._exception.StudentNotFoundException;
import com.knucse.student.student.model.Student;
import com.knucse.locker.domain.persistence.OAuth2UserInfoRepository;
import com.knucse.locker.domain.persistence.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentService {
    private final StudentRepository studentRepository;
    private final OAuth2UserInfoRepository oAuth2UserInfoRepository;

    /**
     * 학생 이름과 학번을 통해 학생을 조회합니다.
     *
     * @param studentName 학생 이름
     * @param studentNumber 학생 학번
     * @return 해당 이름과 학번을 가진 학생 엔티티
     * @throws StudentNotFoundException "STUDENT_NOT_FOUND"
     */
    public Student findStudentByNameAndNumber(String studentName, String studentNumber) {
        return studentRepository.findByStudentNameAndStudentNumber(studentName, studentNumber)
            .orElseThrow(StudentNotFoundException::new);
    }

    /**
     * 학번을 통해 학생을 조회합니다.
     *
     * @param studentNumber 학생 학번
     * @return 해당 학번을 가진 학생 엔티티
     * @throws StudentNotFoundException "STUDENT_NOT_FOUND"
     */
    public Student findStudentByStudentNumber(String studentNumber) {
        return studentRepository.findByStudentNumber(studentNumber)
            .orElseThrow(StudentNotFoundException::new);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }
}
