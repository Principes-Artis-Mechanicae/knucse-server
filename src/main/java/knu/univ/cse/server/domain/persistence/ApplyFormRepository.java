package knu.univ.cse.server.domain.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import knu.univ.cse.server.domain.model.locker.applyForm.ApplyForm;

public interface ApplyFormRepository extends JpaRepository<ApplyForm, Long> {
	Optional<ApplyForm> findByYearAndSemester(Integer year, Integer semester);
	boolean existsByYearAndSemester(Integer year, Integer semester);
}
