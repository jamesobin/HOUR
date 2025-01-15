package com.jamesobin.hour.grade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamesobin.hour.grade.service.GradeService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/grade")
@RestController
public class GradeRestController {
	
	private GradeService gradeService;
	
	public GradeRestController(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	@PostMapping("/create")
	public Map<String, String> createGrade(
			@RequestParam("timetableId") int timetableId
			, @RequestParam("lectureName") String lectureName
			, @RequestParam("credit") int credit
			, @RequestParam("grade") double grade
			, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(gradeService.addGrade(userId, timetableId, lectureName, credit, grade)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@DeleteMapping("/delete")
	public Map<String, String> deleteGrade(@RequestParam("id") int id) {
		Map<String, String> resultMap = new HashMap<>();
		
		if(gradeService.deleteGrade(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
