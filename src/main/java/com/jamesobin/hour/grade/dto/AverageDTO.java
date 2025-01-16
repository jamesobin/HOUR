package com.jamesobin.hour.grade.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AverageDTO {

	private int timetableId;
	private double average;
	private String term;
	
}
