package com.jamesobin.hour.timetable;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamesobin.hour.timetable.service.TimetableService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timetable")
@RestController
public class TimetableRestController {
	private TimetableService timetableService;
	
	public TimetableRestController(TimetableService timetableService) {
		this.timetableService = timetableService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createTimetable(
			@RequestParam("term") int term
			, @RequestParam("timetableName") String timetableName
			, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(timetableService.addTimetable(userId, term, timetableName)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}

}
