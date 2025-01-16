package com.jamesobin.hour.grade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jamesobin.hour.grade.domian.Grade;
import com.jamesobin.hour.grade.dto.AverageDTO;
import com.jamesobin.hour.grade.dto.GradeDTO;
import com.jamesobin.hour.grade.repository.GradeRepository;
import com.jamesobin.hour.lecture.dto.CreditDTO;
import com.jamesobin.hour.lecture.service.LectureService;
import com.jamesobin.hour.timetable.domain.Timetable;
import com.jamesobin.hour.timetable.repository.TimetableRepository;

@Service
public class GradeService {
	
	private GradeRepository gradeRepository;
	private LectureService lectureService;
	private TimetableRepository timetableRepository;
	
	public GradeService(GradeRepository gradeRepository, LectureService lectureService, TimetableRepository timetableRepository) {
		this.gradeRepository = gradeRepository;
		this.lectureService = lectureService;
		this.timetableRepository = timetableRepository;
	}
	
	public boolean addGrade(int userId, int timetableId, String lectureName, int credit, double grade) {
		Grade score = Grade.builder()
				.userId(userId)
				.timetableId(timetableId)
				.lectureName(lectureName)
				.credit(credit)
				.grade(grade)
				.build();
		
		try {			
			gradeRepository.save(score);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public List<Grade> getGradeListByTimetableId(int timetableId) {
		List<Grade> gradeList = gradeRepository.findByTimetableId(timetableId);
		
		for(Grade score:gradeList) {
			if(score.getGrade() == 4.3) {
				
			}
		}
		
		return gradeList;
	}
	
	public List<GradeDTO> getGradeDTOList() {
		List<GradeDTO> gradeDTOList = new ArrayList<>();
		
		GradeDTO gradeDTO = GradeDTO.builder().grade(4.3).gradeString("A+").build();
		gradeDTOList.add(gradeDTO);
		gradeDTO = GradeDTO.builder().grade(4.0).gradeString("A0").build();
		gradeDTOList.add(gradeDTO);
		gradeDTO = GradeDTO.builder().grade(3.7).gradeString("A-").build();
		gradeDTOList.add(gradeDTO);
		gradeDTO = GradeDTO.builder().grade(3.3).gradeString("B+").build();
		gradeDTOList.add(gradeDTO);
		gradeDTO = GradeDTO.builder().grade(3.0).gradeString("B0").build();
		gradeDTOList.add(gradeDTO);
		gradeDTO = GradeDTO.builder().grade(2.7).gradeString("B-").build();
		gradeDTOList.add(gradeDTO);
		gradeDTO = GradeDTO.builder().grade(2.3).gradeString("C+").build();
		gradeDTOList.add(gradeDTO);
		gradeDTO = GradeDTO.builder().grade(2.0).gradeString("C0").build();
		gradeDTOList.add(gradeDTO);
		gradeDTO = GradeDTO.builder().grade(1.7).gradeString("C-").build();
		gradeDTOList.add(gradeDTO);
		gradeDTO = GradeDTO.builder().grade(1.3).gradeString("D+").build();
		gradeDTOList.add(gradeDTO);
		gradeDTO = GradeDTO.builder().grade(1.0).gradeString("D0").build();
		gradeDTOList.add(gradeDTO);
		gradeDTO = GradeDTO.builder().grade(0.7).gradeString("D-").build();
		gradeDTOList.add(gradeDTO);
		gradeDTO = GradeDTO.builder().grade(0.0).gradeString("F").build();
		gradeDTOList.add(gradeDTO);
		
		return gradeDTOList;
	}
	
	public List<String> getAllLectureName(int timetableId) {
		ArrayList<String> allLectureList = new ArrayList<>();
		
		List<Grade> gradeList = gradeRepository.findByTimetableId(timetableId);
		
		for(Grade grade:gradeList) {
			String lectureName = grade.getLectureName();
			
			allLectureList.add(lectureName);
		}
		
		return allLectureList;
	}
	
	public double getAverageGrade(int userId) {
		List<Grade> gradeList = gradeRepository.findByUserId(userId);
		
		double result;
		double gradeSum = 0;
		int creditSum = 0;
		
		for(Grade grade:gradeList) {
			gradeSum += grade.getGrade() * grade.getCredit();
			creditSum += grade.getCredit();
		}
		
		result = gradeSum / creditSum;
		double averageGrade = Math.round(result * 100) / 100.0;
		
		return averageGrade;
	}
	
	public double getAverageGradeByTimetableId(int timetableId) {
		List<Grade> gradeList = gradeRepository.findByTimetableId(timetableId);
		
		double result;
		double gradeSum = 0;
		int creditSum = 0;
		
		for(Grade grade:gradeList) {
			gradeSum += grade.getGrade() * grade.getCredit();
			creditSum += grade.getCredit();
		}
		
		result = gradeSum / creditSum;
		double averageGradeByTimetableId = Math.round(result * 100) / 100.0;
		
		return averageGradeByTimetableId;
	}
	
	public List<AverageDTO> getAverageGradeDTO(int userId) {
		List<Grade> gradeList = gradeRepository.findByUserId(userId);
		List<Timetable> timetableList = timetableRepository.findByUserIdOrderByCreatedAtDesc(userId);
		
		List<AverageDTO> averageDTOList = new ArrayList<>();
		
		int term = 0;
		String termString = "";
		
		for(Timetable timetable:timetableList) {
			List<Grade> gradeListByTimetableId = gradeRepository.findByTimetableId(timetable.getId());
			
			double result;
			double gradeSum = 0;
			int creditSum = 0;
			
			for(Grade grade:gradeListByTimetableId) {
				gradeSum += grade.getGrade() * grade.getCredit();
				creditSum += grade.getCredit();
			}
			
			result = gradeSum / creditSum;
			double averageGradeByTimetableId = Math.round(result * 100) / 100.0;
			
			for(Grade grade:gradeList) {
				if(timetable.getId() == grade.getTimetableId()) {
					term = timetable.getTerm();
				}
			}
			
			if(term == 1) {
				termString = "1학년 1학기";
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
			
			AverageDTO averageDTO = AverageDTO.builder()
					.timetableId(timetable.getId())
					.term(termString)
					.average(averageGradeByTimetableId)
					.build();
			
			averageDTOList.add(averageDTO);
		}
		
		return averageDTOList;
	}
	
	public int getCreditSum(int userId) {
		List<CreditDTO> creditList = lectureService.getCreditListByUserId(userId);
		
		int creditSum = 0;
		
		for(CreditDTO credit:creditList) {
			creditSum += credit.getCredit();
		}
		
		return creditSum;
	}
	
	public boolean deleteGrade(int id) {
		Optional<Grade> optionalGrade = gradeRepository.findById(id);
		
		if(optionalGrade.isPresent()) {
			Grade grade = optionalGrade.get();
			
			gradeRepository.delete(grade);
			return true;
		} else {
			return false;
		}
	}

}
