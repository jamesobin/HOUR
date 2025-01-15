package com.jamesobin.hour.grade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesobin.hour.grade.domian.Grade;

public interface GradeRepository extends JpaRepository<Grade, Integer> {

	public List<Grade> findByTimetableId(int timetableId);
	
}
