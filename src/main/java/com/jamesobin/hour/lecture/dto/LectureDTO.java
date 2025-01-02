package com.jamesobin.hour.lecture.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LectureDTO {

	private int lectureId;
	private int userId;
	private String lectureName;
	private String professorName;
	private String classRoom;
	private int credit;
	private String startTime;
	private String endTime;
	
}
