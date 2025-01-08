package com.jamesobin.hour.lecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jamesobin.hour.lecture.domain.Lecture;
import com.jamesobin.hour.lecture.dto.LectureDTO;
import com.jamesobin.hour.lecture.dto.PeriodDTO;
import com.jamesobin.hour.lecture.repository.LectureRepository;

@Service
public class PeriodDTOService {
	
	private LectureRepository lectureRepository;
	
	public PeriodDTOService(LectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}

	public List<PeriodDTO> getPeriodList(int timetableId) {
		List<Lecture> lectureList = lectureRepository.findByTimetableId(timetableId);
		
		List<LectureDTO> firstPeriodlectureDTOList = new ArrayList<>();
		List<LectureDTO> secondPeriodlectureDTOList = new ArrayList<>();
		List<LectureDTO> thirdPeriodlectureDTOList = new ArrayList<>();
		List<LectureDTO> fourthPeriodlectureDTOList = new ArrayList<>();
		List<LectureDTO> fifthPeriodlectureDTOList = new ArrayList<>();
		List<LectureDTO> sixthPeriodlectureDTOList = new ArrayList<>();
		List<LectureDTO> seventhPeriodlectureDTOList = new ArrayList<>();
		
		List<PeriodDTO> periodDTOList = new ArrayList<>();

		for(Lecture lecture:lectureList) {
			if(lecture.getStartTime().equals("09:00")) {
				LectureDTO firstLectureDTO = LectureDTO.builder()
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
				
				firstPeriodlectureDTOList.add(firstLectureDTO);
			} 			
			if(lecture.getStartTime().equals("10:30") || lecture.getEndTime().equals("12:00")) {
				LectureDTO secondLectureDTO = LectureDTO.builder()
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
				
				secondPeriodlectureDTOList.add(secondLectureDTO);
			}
			if(lecture.getStartTime().equals("12:00") || lecture.getEndTime().equals("13:30")) {
				LectureDTO thirdLectureDTO = LectureDTO.builder()
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
				
				thirdPeriodlectureDTOList.add(thirdLectureDTO);
			}
			if(lecture.getStartTime().equals("13:30") || lecture.getEndTime().equals("15:00")) {
				LectureDTO fourthLectureDTO = LectureDTO.builder()
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
				
				fourthPeriodlectureDTOList.add(fourthLectureDTO);
			}
			if(lecture.getStartTime().equals("15:00") || lecture.getEndTime().equals("16:30")) {
				LectureDTO fifthLectureDTO = LectureDTO.builder()
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
				
				fifthPeriodlectureDTOList.add(fifthLectureDTO);
			}
			if(lecture.getStartTime().equals("16:30") || lecture.getEndTime().equals("18:00")) {
				LectureDTO sixthLectureDTO = LectureDTO.builder()
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
				
				sixthPeriodlectureDTOList.add(sixthLectureDTO);
			}
			if(lecture.getStartTime().equals("18:00") || lecture.getEndTime().equals("19:30")) {
				LectureDTO seventhLectureDTO = LectureDTO.builder()
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
				
				seventhPeriodlectureDTOList.add(seventhLectureDTO);
			}
		}
		
		PeriodDTO periodDTO = PeriodDTO.builder().period("1교시").lectureList(firstPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		periodDTO = PeriodDTO.builder().period("2교시").lectureList(secondPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		periodDTO = PeriodDTO.builder().period("3교시").lectureList(thirdPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		periodDTO = PeriodDTO.builder().period("4교시").lectureList(fourthPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		periodDTO = PeriodDTO.builder().period("5교시").lectureList(fifthPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		periodDTO = PeriodDTO.builder().period("6교시").lectureList(sixthPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		periodDTO = PeriodDTO.builder().period("7교시").lectureList(seventhPeriodlectureDTOList).build();
		periodDTOList.add(periodDTO);
		
		return periodDTOList;
	}

}
