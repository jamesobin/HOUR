package com.jamesobin.hour.timetable.domain;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name="`timetable`")
@Entity
public class Timetable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int userId;
	
	private int term;
	
	@Column(name="lectureName")
	private String lectureName;
	
	@Column(name="professorName")
	private String professorName;
	
	private String day;
	
	@Column(name="startTime")
	private Time startTime;
	
	@Column(name="endTime")
	private Time endTime;
	
	@Column(name="classRoom")
	private String classRoom;
	
	private int credit;
	
	private double grade;
	
	@Column(name="createdAt")
	@CreationTimestamp	
	private LocalDateTime createdAt;
	
	@Column(name="updatedAt")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
}
