package com.jamesobin.hour.grade;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jamesobin.hour.lecture.dto.CreditDTO;
import com.jamesobin.hour.lecture.service.LectureService;
import com.jamesobin.hour.timetable.domain.Timetable;
import com.jamesobin.hour.timetable.service.TimetableService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/grade")
@Controller
public class GradeController {
	
	private TimetableService timetableService;
	private LectureService lectureService;
	
	public GradeController(TimetableService timetableService, LectureService lectureService) {
		this.timetableService = timetableService;
		this.lectureService = lectureService;
	}

	@GetMapping("/input-view")
	public String addGrade(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		
		model.addAttribute("allTimetableList", allTimetableList);
		
		return "grade/input";
	}
	
	@GetMapping("/input/timetable-view")
	public String addGrade(Model model, HttpSession session, @RequestParam("id") int timetableId) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		List<CreditDTO> creditList = lectureService.getCreditListByUserIdAndTimetableId(userId, timetableId);
		
		model.addAttribute("allTimetableList", allTimetableList);
		model.addAttribute("creditList" ,creditList);
		model.addAttribute("selectedId", timetableId);
		
		return "grade/input";
	}
	
	@GetMapping("/entire-view")
	public String getEntireGrade(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		
		model.addAttribute("allTimetableList", allTimetableList);
		
		return "grade/entire-grade";
	}
	
	@GetMapping("/specific-view")
	public String getSpecificGrade(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		
		model.addAttribute("allTimetableList", allTimetableList);
		
		return "grade/specific-grade";
	}
	
}
