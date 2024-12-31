package com.jamesobin.hour.timetable.service;

import java.sql.Time;

import org.springframework.stereotype.Service;

import com.jamesobin.hour.timetable.domain.Timetable;
import com.jamesobin.hour.timetable.repository.TimetableRepository;

@Service
public class TimetableService {
	
	private TimetableRepository timetableRepository;
	
	public TimetableService(TimetableRepository timetableRepository) {
		this.timetableRepository = timetableRepository;
	}

	public boolean addTimetable(
			int userId
			, int term
			, String lectureName
			, String professorName
			, int credit
			, String day
			, Time startTime
			, Time endTime
			, String classRoom) {
		Timetable timetable = Timetable.builder()
				.userId(userId)
				.lectureName(lectureName)
				.professorName(professorName)
				.credit(credit)
				.day(day)
				.startTime(startTime)
				.endTime(endTime)
				.classRoom(classRoom)
				.build();
		
		try {			
			timetableRepository.save(timetable);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
}
