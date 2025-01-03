package com.jamesobin.hour.timetable;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@GetMapping("/detail-view")
	public String timetable(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
//		List<Timetable> timetableList = timetableService.getTimetableList(userId);
//		
//		model.addAttribute("timetableList", timetableList);
		
		List<TimetableDTO> tableList = timetableService.getTimetableList(userId);
		
		model.addAttribute("tableList", tableList);
		
		return "timetable/detail";
	}
	
	@GetMapping("/input-view")
	public String addTimetable() {
		return "timetable/input";
	}
	
}
