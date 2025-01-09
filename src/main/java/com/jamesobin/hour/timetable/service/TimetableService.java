package com.jamesobin.hour.timetable.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jamesobin.hour.lecture.dto.PeriodDTO;
import com.jamesobin.hour.lecture.service.LectureService;
import com.jamesobin.hour.lecture.service.PeriodDTOService;
import com.jamesobin.hour.timetable.domain.Timetable;
import com.jamesobin.hour.timetable.dto.TimetableDTO;
import com.jamesobin.hour.timetable.repository.TimetableRepository;

@Service
public class TimetableService {
	
	private TimetableRepository timetableRepository;
	private LectureService lectureService;
	private PeriodDTOService periodDTOService;
	
	public TimetableService(TimetableRepository timetableRepository
			, LectureService lectureService
			, PeriodDTOService periodDTOService) {
		this.timetableRepository = timetableRepository;
		this.lectureService = lectureService;
		this.periodDTOService = periodDTOService;
	}

	public Object addTimetable(
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
			return timetable.getId();
		} catch(Exception e) {
			return false;
		}
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
	
	public List<TimetableDTO> getTimetable(int userId, int timetableId) {
		List<Timetable> timetableList = timetableRepository.findByUserIdAndIdOrderByTermDesc(userId, timetableId);
		
		List<TimetableDTO> tableList = new ArrayList<>();
		for(Timetable timetable:timetableList) {
			List<PeriodDTO> periodDTOList = periodDTOService.getPeriodList(timetableId);
			
			TimetableDTO table = TimetableDTO.builder()
					.timetableId(timetable.getId())
					.userId(timetable.getUserId())
					.timetableName(timetable.getTimetableName())
					.periodDTOList(periodDTOList)
					.build();
			
			tableList.add(table);
		}
		
		return tableList;
	}
	
	public List<Timetable> getTimetableList(int userId) {
		List<Timetable> allTimetableList = timetableRepository.findByUserIdOrderByCreatedAtDesc(userId);
		
		return allTimetableList;
	}
	
}
