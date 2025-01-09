package com.jamesobin.hour.lecture;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jamesobin.hour.timetable.domain.Timetable;
import com.jamesobin.hour.timetable.dto.TimetableDTO;
import com.jamesobin.hour.timetable.service.TimetableService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timetable")
@Controller
public class LectureController {

	private TimetableService timetableService;
	
	public LectureController(TimetableService timetableService) {
		this.timetableService = timetableService;
	}

	@GetMapping("/lecture/input-view")
	public String addLecture(Model model, HttpSession session, @RequestParam("id") int timetableId) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<TimetableDTO> tableList = timetableService.getTimetable(userId, timetableId);
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		
		model.addAttribute("tableList", tableList);
		model.addAttribute("allTimetableList", allTimetableList);
		model.addAttribute("selectedId", timetableId);
		
		return "timetable/lectureinput";
	}

}
