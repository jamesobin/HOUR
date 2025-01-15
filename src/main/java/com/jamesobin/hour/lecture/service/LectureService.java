package com.jamesobin.hour.lecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jamesobin.hour.lecture.domain.Lecture;
import com.jamesobin.hour.lecture.dto.CreditDTO;
import com.jamesobin.hour.lecture.dto.LectureDTO;
import com.jamesobin.hour.lecture.repository.LectureRepository;

@Service
public class LectureService {

	private LectureRepository lectureRepository;
	
	public LectureService(LectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}

	public boolean addLecture(
			int userId
			, int timetableId
			, String lectureName
			, String professorName
			, int credit
			, String day
			, String startTime
			, String endTime
			, String classRoom) {
		Lecture lecture = Lecture.builder()
				.userId(userId)
				.timetableId(timetableId)
				.lectureName(lectureName)
				.professorName(professorName)
				.credit(credit)
				.day(day)
				.startTime(startTime)
				.endTime(endTime)
				.classRoom(classRoom)
				.build();
		
		try {			
			lectureRepository.save(lecture);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public void deleteLectureByTimetableId(int timetableId) {
		lectureRepository.deleteByTimetableId(timetableId);
	}

	
	public List<LectureDTO> getLectureList(int timetableId) {
		List<Lecture> lectureList = lectureRepository.findByTimetableId(timetableId);
		
		List<LectureDTO> lectureDTOList = new ArrayList<>();
		
		for(Lecture lecture:lectureList) {
			LectureDTO lectureDTO = LectureDTO.builder()
					.lectureId(lecture.getId())
					.userId(lecture.getUserId())
					.lectureName(lecture.getLectureName())
					.professorName(lecture.getProfessorName())
					.classRoom(lecture.getClassRoom())
					.credit(lecture.getCredit())
					.day(lecture.getDay())
					.startTime(lecture.getStartTime())
					.endTime(lecture.getEndTime())
					.build();
			
			lectureDTOList.add(lectureDTO);
		}
		
		return lectureDTOList;
	}
	
	public boolean deleteLecture(int id, int userId) {
		Optional<Lecture> optionalLecture = lectureRepository.findById(id);
		
		if(optionalLecture.isPresent()) {
			Lecture lecture = optionalLecture.get();
			
			if(lecture.getUserId() == userId) {
				lectureRepository.delete(lecture);
				
				return true;				
			} else {
				return false;
			}
			
		} else {
			return false;
		}
	}
	
	public boolean updateLecture(int id
			, String lectureName
			, String professorName
			, int credit
			, String day
			, String startTime
			, String endTime
			, String classRoom) {
		Optional<Lecture> optionalLecture = lectureRepository.findById(id);
		
		if(optionalLecture.isPresent()) {
			Lecture lecture = optionalLecture.get();
			
			lecture = lecture.toBuilder()
					.lectureName(lectureName)
					.professorName(professorName)
					.credit(credit)
					.day(day)
					.startTime(startTime)
					.endTime(endTime)
					.classRoom(classRoom)
					.build();
			
			try {
				lectureRepository.save(lecture);
				return true;
			} catch(Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public List<CreditDTO> getCreditListByUserId(int userId) {
		List<Lecture> lectureList = lectureRepository.findByUserId(userId);
		
		List<CreditDTO> allCreditDTOList = new ArrayList<>();
		List<CreditDTO> creditDTOList = new ArrayList<>();
		
		for(Lecture lecture:lectureList) {
			CreditDTO creditDTO = CreditDTO.builder()
					.userId(lecture.getUserId())
					.timetableId(lecture.getTimetableId())
					.lectureName(lecture.getLectureName())
					.professorName(lecture.getProfessorName())
					.credit(lecture.getCredit())
					.build();

			allCreditDTOList.add(creditDTO);
		}
		
		for(CreditDTO creditDTO:allCreditDTOList) {
			if(!creditDTOList.contains(creditDTO)) {
				creditDTOList.add(creditDTO);
			}
		}

		return creditDTOList;
	}
	
	public List<CreditDTO> getCreditListByUserIdAndTimetableId(int userId, int timetableId) {
		List<Lecture> lectureList = lectureRepository.findByUserIdAndTimetableId(userId, timetableId);
		
		List<CreditDTO> allCreditDTOList = new ArrayList<>();
		List<CreditDTO> creditList = new ArrayList<>();
		
		for(Lecture lecture:lectureList) {
			CreditDTO creditDTO = CreditDTO.builder()
					.userId(userId)
					.timetableId(timetableId)
					.lectureName(lecture.getLectureName())
					.professorName(lecture.getProfessorName())
					.credit(lecture.getCredit())
					.build();

			allCreditDTOList.add(creditDTO);
		}
		
		for(CreditDTO creditDTO:allCreditDTOList) {
			if(!creditList.contains(creditDTO)) {
				creditList.add(creditDTO);
			}
		}

		return creditList;
	}
	
}
