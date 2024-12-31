package com.jamesobin.hour.timetable;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.jamesobin.hour.timetable.service.TimetableService;

import jakarta.servlet.http.HttpSession;

@Controller
public class TimetableRestController {
	private TimetableService timetableService;
	
	public TimetableRestController(TimetableService timetableService) {
		this.timetableService = timetableService;
	}
	
	public Map<String, String> createTimetable(
			@RequestParam("term") int term
			, @RequestParam("lectureName") String lectureName
			, @RequestParam("professorName") String professorName
			, @RequestParam("credit") int credit
			, @RequestParam("day") String day
			, @RequestParam("startTime") Time startTime
			, @RequestParam("endTime") Time endTime
			, @RequestParam("classRoom") String classRoom
			, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(timetableService.addTimetable(userId, term, lectureName, professorName, credit, day, startTime, endTime, classRoom)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
