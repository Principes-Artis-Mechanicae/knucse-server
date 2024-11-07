package com.knucse.student.dues.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knucse.student.dues.model.Dues;

public interface DuesRepository extends JpaRepository<Dues, Long> {
	boolean existsDuesById(Long id);
	Optional<Dues> findDuesById(Long id);
}
