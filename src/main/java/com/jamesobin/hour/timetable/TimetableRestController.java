package com.jamesobin.hour.timetable;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
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
	public Map<String, Object> createTimetable(
			@RequestParam("term") int term
			, @RequestParam("timetableName") String timetableName
			, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		Object result = timetableService.addTimetable(userId, term, timetableName);
		
		Map<String, Object> resultMap = new HashMap<>();
		
		if(result == "false") {
			resultMap.put("result", "fail");
		} else {
			resultMap.put("result", "success");
			resultMap.put("id", result);
		}
		
		return resultMap;
	}
	
	@DeleteMapping("/delete")
	public Map<String, String> deleteTimetable(
			@RequestParam("id") int id
			, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		if(timetableService.deleteTimetable(id, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}

}
