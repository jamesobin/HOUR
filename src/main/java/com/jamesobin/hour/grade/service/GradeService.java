package com.jamesobin.hour.grade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jamesobin.hour.grade.domian.Grade;
import com.jamesobin.hour.grade.dto.GradeDTO;
import com.jamesobin.hour.grade.repository.GradeRepository;

@Service
public class GradeService {
	
	private GradeRepository gradeRepository;
	
	public GradeService(GradeRepository gradeRepository) {
		this.gradeRepository = gradeRepository;
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
