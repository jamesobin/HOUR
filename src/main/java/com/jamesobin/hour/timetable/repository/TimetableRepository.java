package com.jamesobin.hour.timetable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesobin.hour.timetable.domain.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
	
	public List<Timetable> findByUserIdAndIdOrderByTermDesc(int userId, int id);
	
	public List<Timetable> findByUserIdOrderByCreatedAtDesc(int userId);
	
}
