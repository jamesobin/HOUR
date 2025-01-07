package com.jamesobin.hour.lecture.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jamesobin.hour.lecture.domain.Lecture;
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
	
}
