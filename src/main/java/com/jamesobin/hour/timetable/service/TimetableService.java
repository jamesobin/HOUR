package com.jamesobin.hour.timetable.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jamesobin.hour.lecture.dto.LectureDTO;
import com.jamesobin.hour.lecture.service.LectureService;
import com.jamesobin.hour.timetable.domain.Timetable;
import com.jamesobin.hour.timetable.dto.TimetableDTO;
import com.jamesobin.hour.timetable.repository.TimetableRepository;

@Service
public class TimetableService {
	
	private TimetableRepository timetableRepository;
	private LectureService lectureService;
	
	public TimetableService(TimetableRepository timetableRepository, LectureService lectureService) {
		this.timetableRepository = timetableRepository;
		this.lectureService = lectureService;
	}

	public boolean addTimetable(
			int userId
			, int term
			, String timetableName) {
		Timetable timetable = Timetable.builder()
				.userId(userId)
				.term(term)
				.timetableName(timetableName)
				.build();
		
		try {			
			timetableRepository.save(timetable);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public List<Timetable> getTimetableList(int userId) {
		return timetableRepository.findByUserIdOrderByTimetableNameDesc(userId);
	}
	
	public List<TimetableDTO> getTableList(int userId) {
		
		List<Timetable> timetableList = timetableRepository.findByUserIdOrderByTimetableNameDesc(userId);
		
		List<TimetableDTO> tableList = new ArrayList<>();
		for(Timetable timetable:timetableList) {
			List<LectureDTO> lectureList = lectureService.getLectureList(timetable.getId());
			
			TimetableDTO table = TimetableDTO.builder()
			.timetableId(timetable.getId())
			.userId(timetable.getUserId())
			.lectureList(lectureList)
			.build();
			
			tableList.add(table);
		}
		
		return tableList;
	}
	
	public boolean deleteTimetable(int id, int userId) {
		Optional<Timetable> optionalTimetable = timetableRepository.findById(id);
		
		if(optionalTimetable.isPresent()) {
			Timetable timetable = optionalTimetable.get();
			
			if(timetable.getUserId() == userId) {
				lectureService.deleteLectureByTimetableId(id);
				
				timetableRepository.delete(timetable);
				
				return true;				
			} else {
				return false;
			}
			
		} else {
			return false;
		}
	}
	
}
