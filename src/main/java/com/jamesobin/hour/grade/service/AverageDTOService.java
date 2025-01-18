package com.jamesobin.hour.grade.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jamesobin.hour.grade.domian.Grade;
import com.jamesobin.hour.grade.dto.AverageDTO;
import com.jamesobin.hour.grade.repository.GradeRepository;
import com.jamesobin.hour.timetable.domain.Timetable;
import com.jamesobin.hour.timetable.repository.TimetableRepository;

@Service
public class AverageDTOService {

	private GradeService gradeService;
	private TimetableRepository timetableRepository;
	private GradeRepository gradeRepository;
	
	public AverageDTOService(GradeService gradeService, TimetableRepository timetableRepository, GradeRepository gradeRepository) {
		this.gradeService = gradeService;
		this.timetableRepository = timetableRepository;
		this.gradeRepository = gradeRepository;
	}
	
	public List<Map<String, Object>> getAverageGradeForChart(int userId) {
		List<Timetable> timetableList = timetableRepository.findByUserIdOrderByCreatedAtDesc(userId);
		List<AverageDTO> averageGradeDTOList = gradeService.getAverageGradeDTO(userId);
		
		Object average1 = null;
		Object average2 = null;
		Object average3 = null;
		Object average4 = null;
		Object average5 = null;
		Object average6 = null;
		Object average7 = null;
		Object average8 = null;
		Object average9 = null;
		Object average10 = null;
		
		for(Timetable timetable:timetableList) {
			for(AverageDTO averageGradeDTO:averageGradeDTOList) {
				if(timetable.getId() == averageGradeDTO.getTimetableId()) {
					if(timetable.getTerm() == 1) {
						average1 = averageGradeDTO.getAverage();
					} else if(timetable.getTerm() == 2) {
						average2 = averageGradeDTO.getAverage();
					} else if(timetable.getTerm() == 3) {
						average3 = averageGradeDTO.getAverage();
					} else if(timetable.getTerm() == 4) {
						average4 = averageGradeDTO.getAverage();
					} else if(timetable.getTerm() == 5) {
						average5 = averageGradeDTO.getAverage();
					} else if(timetable.getTerm() == 6) {
						average6 = averageGradeDTO.getAverage();
					} else if(timetable.getTerm() == 7) {
						average7 = averageGradeDTO.getAverage();
					} else if(timetable.getTerm() == 8) {
						average8 = averageGradeDTO.getAverage();
					} else if(timetable.getTerm() == 9) {
						average9 = averageGradeDTO.getAverage();
					} else if(timetable.getTerm() == 10) {
						average10 = averageGradeDTO.getAverage();
					} 
				}
			}
		}

		List<Map<String, Object>> averageList = new ArrayList<>();

		Map<String, Object> map1 = new HashMap<>();
		map1.put("term", "1학년 1학기");
		map1.put("average", average1);
		averageList.add(map1);

		Map<String, Object> map2 = new HashMap<>();
		map2.put("term", "1학년 2학기");
		map2.put("average", average2);
		averageList.add(map2);

		Map<String, Object> map3 = new HashMap<>();
		map3.put("term", "2학년 1학기");
		map3.put("average", average3);
		averageList.add(map3);

		Map<String, Object> map4 = new HashMap<>();
		map4.put("term", "2학년 2학기");
		map4.put("average", average4);
		averageList.add(map4);
		
		Map<String, Object> map5 = new HashMap<>();
		map5.put("term", "3학년 1학기");
		map5.put("average", average5);
		averageList.add(map5);
		
		Map<String, Object> map6 = new HashMap<>();
		map6.put("term", "3학년 2학기");
		map6.put("average", average6);
		averageList.add(map6);
		
		Map<String, Object> map7 = new HashMap<>();
		map7.put("term", "4학년 1학기");
		map7.put("average", average7);
		averageList.add(map7);
		
		Map<String, Object> map8 = new HashMap<>();
		map8.put("term", "4학년 2학기");
		map8.put("average", average8);
		averageList.add(map8);
		
		Map<String, Object> map9 = new HashMap<>();
		map9.put("term", "5학년 1학기");
		map9.put("average", average9);
		averageList.add(map9);
		
		Map<String, Object> map10 = new HashMap<>();
		map10.put("term", "5학년 2학기");
		map10.put("average", average10);
		averageList.add(map10);
		
		return averageList;
	}
	
	public List<Map<String, Object>> getGradeForChart(int timetableId) {
		List<Grade> gradeList = gradeRepository.findByTimetableId(timetableId);
		List<Map<String, Object>> gradeListByLecture = new ArrayList<>();
		
		for(Grade grade:gradeList) {
			Map<String, Object> gradeMap = new HashMap<>();
			List<Timetable> timetableList = timetableRepository.findByUserIdOrderByCreatedAtDesc(grade.getUserId());
			
			int term = 0;
			
			for(Timetable timetable:timetableList) {
				if(timetable.getId() == grade.getTimetableId()) {
					term = timetable.getTerm();
				}
			}
			
			if(term == 1) {
				gradeMap.put("term", "1학년 1학기");
			} else if(term == 2) {
				gradeMap.put("term", "1학년 2학기");
			} else if(term == 3) {
				gradeMap.put("term", "2학년 1학기");
			} else if(term == 4) {
				gradeMap.put("term", "2학년 2학기");
			} else if(term == 5) {
				gradeMap.put("term", "3학년 1학기");
			} else if(term == 6) {
				gradeMap.put("term", "3학년 2학기");
			} else if(term == 7) {
				gradeMap.put("term", "4학년 1학기");
			} else if(term == 8) {
				gradeMap.put("term", "4학년 2학기");
			} else if(term == 9) {
				gradeMap.put("term", "5학년 1학기");
			} else if(term == 10) {
				gradeMap.put("term", "5학년 2학기");
			}
			
			gradeMap.put("lecture", grade.getLectureName());
			gradeMap.put("grade", grade.getGrade());
			gradeListByLecture.add(gradeMap);
		}
		
		return gradeListByLecture;
	}
	
}
