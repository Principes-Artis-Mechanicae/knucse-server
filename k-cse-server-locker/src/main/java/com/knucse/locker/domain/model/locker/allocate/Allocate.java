package com.knucse.locker.domain.model.locker.allocate;

import com.knucse.locker.domain.model.locker.Locker;
import com.knucse.locker.domain.model.locker.apply.Apply;
import com.knucse.locker.domain.model.locker.applyForm.ApplyForm;
import com.knucse.student.student.model.Student;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 한 신청에 대해서 한 할당을 차지하고, 한 사물함을 차지함, 한 사람 당 여러 개의 사물함을 가질 수 있음.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "allocate")
public class Allocate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "apply_id")
	private Apply apply;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "locker_id")
	private Locker locker;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Student student;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "apply_form_id")
	private ApplyForm applyForm;

	@Builder
	public Allocate(Long id, Apply apply, Locker locker, Student student, ApplyForm applyForm) {
		this.id = id;
		this.apply = apply;
		this.locker = locker;
		this.student = student;
		this.applyForm = applyForm;
	}
}
