package com.jamesobin.hour.timetable;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jamesobin.hour.grade.service.GradeService;
import com.jamesobin.hour.timetable.domain.Timetable;
import com.jamesobin.hour.timetable.dto.TimetableDTO;
import com.jamesobin.hour.timetable.service.TimetableService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timetable")
@Controller
public class TimetableController {
	
	private TimetableService timetableService;
	private GradeService gradeService;
	public TimetableController(TimetableService timetableService, GradeService gradeService) {
		this.timetableService = timetableService;
		this.gradeService = gradeService;
	}
	
	@GetMapping("")
	public String timetable(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		
		model.addAttribute("allTimetableList", allTimetableList);
		
		return "timetable/main";
	}
	
	@GetMapping("/detail-view")
	public String timetable(Model model, HttpSession session, @RequestParam("id") int id) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<TimetableDTO> tableList = timetableService.getTimetable(userId, id);
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		int credit = gradeService.getCreditSumByTimetableId(userId, id);
		
		model.addAttribute("tableList", tableList);
		model.addAttribute("allTimetableList", allTimetableList);
		model.addAttribute("credit", credit);
		model.addAttribute("selectedId", id);
		
		return "timetable/detail";
	}
	
	@GetMapping("/input-view")
	public String addTimetable() {
		return "timetable/input";
	}
	
}
