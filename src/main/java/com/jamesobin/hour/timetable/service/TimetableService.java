package com.jamesobin.hour.timetable.service;

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
			, String startTime
			, String endTime
			, String classRoom) {
		Timetable timetable = Timetable.builder()
				.userId(userId)
				.term(term)
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
