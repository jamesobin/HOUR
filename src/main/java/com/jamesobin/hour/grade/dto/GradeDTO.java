package com.jamesobin.hour.grade.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GradeDTO {

	private double grade;
	private String gradeString;
	
}
