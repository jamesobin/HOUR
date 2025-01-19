package com.jamesobin.hour.timetable.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	public String getTerm(int id) {
		Optional<Timetable> optionalTimetable = timetableRepository.findById(id);
		
		int term = 0;
		String termString = null;
		
		if(optionalTimetable.isPresent()) {
			Timetable timetable = optionalTimetable.get();
			term = timetable.getTerm();
		}
		
		if(term == 1) {
			termString ="1학년 1학기";
		} else if(term == 2) {
			termString = "1학년 2학기";
		} else if(term == 3) {
			termString = "2학년 1학기";
		} else if(term == 4) {
			termString = "2학년 2학기";
		} else if(term == 5) {
			termString = "3학년 1학기";
		} else if(term == 6) {
			termString = "3학년 2학기";
		} else if(term == 7) {
			termString = "4학년 1학기";
		} else if(term == 8) {
			termString = "4학년 2학기";
		} else if(term == 9) {
			termString = "5학년 1학기";
		} else if(term == 10) {
			termString = "5학년 2학기";
		}
		
		return termString;
	}
	
	public List<Map<String, Object>> getTermString() {
		List<Map<String, Object>> termStringList = new ArrayList<>();
		Map<String, Object> termMap1 = new HashMap<>();
		Map<String, Object> termMap2 = new HashMap<>();
		Map<String, Object> termMap3 = new HashMap<>();
		Map<String, Object> termMap4 = new HashMap<>();
		Map<String, Object> termMap5 = new HashMap<>();
		Map<String, Object> termMap6 = new HashMap<>();
		Map<String, Object> termMap7 = new HashMap<>();
		Map<String, Object> termMap8 = new HashMap<>();
		Map<String, Object> termMap9 = new HashMap<>();
		Map<String, Object> termMap10 = new HashMap<>();

		
		String termString1 ="1학년 1학기";
		termMap1.put("term", 1);
		termMap1.put("termString", termString1);
		termStringList.add(termMap1);

		String termString2 = "1학년 2학기";
		termMap2.put("term", 2);
		termMap2.put("termString", termString2);
		termStringList.add(termMap2);

		String termString3 = "2학년 1학기";
		termMap3.put("term", 3);
		termMap3.put("termString", termString3);
		termStringList.add(termMap3);

		String termString4 = "2학년 2학기";
		termMap4.put("term", 4);
		termMap4.put("termString", termString4);
		termStringList.add(termMap4);

		String termString5 = "3학년 1학기";
		termMap5.put("term", 5);
		termMap5.put("termString", termString5);
		termStringList.add(termMap5);

		String termString6 = "3학년 2학기";
		termMap6.put("term", 6);
		termMap6.put("termString", termString6);
		termStringList.add(termMap6);

		String termString7 = "4학년 1학기";
		termMap7.put("term", 7);
		termMap7.put("termString", termString7);
		termStringList.add(termMap7);

		String termString8 = "4학년 2학기";
		termMap8.put("term", 8);
		termMap8.put("termString", termString8);
		termStringList.add(termMap8);

		String termString9 = "5학년 1학기";
		termMap9.put("term", 9);
		termMap9.put("termString", termString9);
		termStringList.add(termMap9);

		String termString10 = "5학년 2학기";
		termMap10.put("term", 10);
		termMap10.put("termString", termString10);
		termStringList.add(termMap10);

		
		return termStringList;
	}
	
}
