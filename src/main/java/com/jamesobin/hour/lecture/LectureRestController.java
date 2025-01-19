package com.jamesobin.hour.lecture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamesobin.hour.lecture.domain.Lecture;
import com.jamesobin.hour.lecture.dto.CreditDTO;
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
	
	@DeleteMapping("/delete")
	public Map<String, String> deleteLecture(
			@RequestParam("id") int id
			, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		if(lectureService.deleteLecture(id, userId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@PutMapping("/update")
	public Map<String, String> updateLecture(
			@RequestParam("id") int id
			, @RequestParam("lectureName") String lectureName
			, @RequestParam("professorName") String professorName
			, @RequestParam("credit") int credit
			, @RequestParam("day") String day
			, @RequestParam("startTime") String startTime
			, @RequestParam("endTime") String endTime
			, @RequestParam("classRoom") String classRoom) {
		Map<String, String> resultMap = new HashMap<>();
		if(lectureService.updateLecture(id
				, lectureName
				, professorName
				, credit
				, day
				, startTime
				, endTime
				, classRoom)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@GetMapping("/detail")
	public Map<String, Object> getLecture(@RequestParam("id") int lectureId) {
		Optional<Lecture> lecture = lectureService.getLectureById(lectureId);
		
		Map<String, Object> resultMap = new HashMap<>();
		if(lecture != null) {
			resultMap.put("result", "success");
			resultMap.put("lecture", lecture);
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@GetMapping("/list")
	public Map<String, Object> getLecture(HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		List<CreditDTO> lectureList = lectureService.getCreditListByUserId(userId);
		
		Map<String, Object> resultMap = new HashMap<>();
		if(lectureList != null) {
			resultMap.put("result", "success");
			resultMap.put("lecture", lectureList);
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
