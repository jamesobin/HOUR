package com.jamesobin.hour.lecture;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamesobin.hour.grade.service.GradeService;
import com.jamesobin.hour.lecture.dto.CreditDTO;
import com.jamesobin.hour.lecture.service.LectureService;
import com.jamesobin.hour.timetable.domain.Timetable;
import com.jamesobin.hour.timetable.dto.TimetableDTO;
import com.jamesobin.hour.timetable.service.TimetableService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/timetable")
@Controller
public class LectureController {

	private TimetableService timetableService;
	private LectureService lectureService;
	private GradeService gradeService;
	
	public LectureController(TimetableService timetableService, LectureService lectureService, GradeService gradeService) {
		this.timetableService = timetableService;
		this.lectureService = lectureService;
		this.gradeService = gradeService;
	}

	@GetMapping("/lecture/input-view")
	public String addLecture(Model model, HttpSession session, @RequestParam("id") int timetableId) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<TimetableDTO> tableList = timetableService.getTimetable(userId, timetableId);
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		int credit = gradeService.getCreditSumByTimetableId(userId, timetableId);
		
		model.addAttribute("tableList", tableList);
		model.addAttribute("allTimetableList", allTimetableList);
		model.addAttribute("credit", credit);
		model.addAttribute("selectedId", timetableId);
		
		return "timetable/lectureinput";
	}
	
	@ResponseBody
	@GetMapping("/lecture/list")
	public List<CreditDTO> getLectureList(@RequestParam("id") int userId) {
		List<CreditDTO> lectureList = lectureService.getCreditListByUserId(userId);
		
		return lectureList;
	}

}
