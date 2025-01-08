package com.jamesobin.hour.lecture.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PeriodDTO {

	private String period;
	
	List<LectureDTO> lectureList;
	
}
