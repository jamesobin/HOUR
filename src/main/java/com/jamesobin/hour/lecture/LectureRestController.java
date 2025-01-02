package com.jamesobin.hour.lecture;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamesobin.hour.lecture.service.LectureService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/lecture")
@RestController
public class LectureRestController {

	private LectureService lectureService;
	
	public LectureRestController(LectureService lectureService) {
		this.lectureService = lectureService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createLecture(
			@RequestParam("timetableId") int timetableId
			, @RequestParam("lectureName") String lectureName
			, @RequestParam("professorName") String professorName
			, @RequestParam("credit") int credit
			, @RequestParam("day") String day
			, @RequestParam("startTime") String startTime
			, @RequestParam("endTime") String endTime
			, @RequestParam("classRoom") String classRoom
			, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(lectureService.addLecture(userId, timetableId, lectureName, professorName, credit, day, startTime, endTime, classRoom)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
