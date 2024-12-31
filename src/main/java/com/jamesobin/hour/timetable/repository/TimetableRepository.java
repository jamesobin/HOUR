package com.jamesobin.hour.timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesobin.hour.timetable.domain.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
	
}
