package com.knucse.locker.domain.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.knucse.locker.domain.model.locker.apply.Apply;
import com.knucse.locker.domain.model.locker.report.Report;

public interface ReportRepository extends JpaRepository<Report, Long> {
	Optional<Report> findByApply(Apply apply);
}
