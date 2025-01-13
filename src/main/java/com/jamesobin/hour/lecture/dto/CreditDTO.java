package com.jamesobin.hour.lecture.dto;

import java.util.Objects;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreditDTO {

	private int userId;
	private int timetableId;
	private String lectureName;
	private String professorName;
	private int credit;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditDTO creditDTO = (CreditDTO) o;
        return userId == creditDTO.userId &&
               timetableId == creditDTO.timetableId &&
               credit == creditDTO.credit &&
               Objects.equals(lectureName, creditDTO.lectureName) &&
               Objects.equals(professorName, creditDTO.professorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, timetableId, lectureName, professorName, credit);
    }
	
}
