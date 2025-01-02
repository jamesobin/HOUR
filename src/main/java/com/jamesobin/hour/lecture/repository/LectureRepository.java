package com.jamesobin.hour.lecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesobin.hour.lecture.domain.Lecture;

import jakarta.transaction.Transactional;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {

	public List<Lecture> findByTimetableId(int timetableId);
	
	@Transactional
	public void deleteByTimetableId(int timetableId);
	
}
