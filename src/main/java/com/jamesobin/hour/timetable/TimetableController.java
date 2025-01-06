package com.jamesobin.hour.timetable;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamesobin.hour.timetable.dto.TimetableDTO;
import com.jamesobin.hour.timetable.service.TimetableService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timetable")
@Controller
public class TimetableController {
	private TimetableService timetableService;
	
	public TimetableController(TimetableService timetableService) {
		this.timetableService = timetableService;
	}
	
	@GetMapping("/select-view")
	public String selectTimetable(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<TimetableDTO> tableList = timetableService.getTimetableList(userId);
		
		model.addAttribute("tableList", tableList);
		
		return "timetable/select";
	}
	
	@GetMapping("/detail-view/*")
	public String timetable(Model model, HttpSession session, @RequestParam("id") int timetableId) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<TimetableDTO> tableList = timetableService.getTimetableList(userId);
		
		model.addAttribute("tableList", tableList);
		
		return "timetable/detail";
	}
	
	@GetMapping("/input-view")
	public String addTimetable() {
		return "timetable/input";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public List<TimetableDTO> timetableList(Model model, HttpSession session, @RequestParam("timetableId") int timetableId) {
		int userId = (Integer)session.getAttribute("userId");
		List<TimetableDTO> tableList = timetableService.getTimetableList(userId);
		model.addAttribute("tableList", tableList);
		return tableList;
	}
	
}
