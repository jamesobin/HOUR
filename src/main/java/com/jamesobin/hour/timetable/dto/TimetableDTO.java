package com.jamesobin.hour.timetable.dto;

import java.util.List;

import com.jamesobin.hour.lecture.dto.LectureDTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TimetableDTO {
	
	private int timetableId;
	private int userId;
	
	List<LectureDTO> lectureList;
	
}