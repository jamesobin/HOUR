package com.jamesobin.hour.grade;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamesobin.hour.grade.domian.Grade;
import com.jamesobin.hour.grade.dto.GradeDTO;
import com.jamesobin.hour.grade.service.GradeService;
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
	private GradeService gradeService;
	
	public GradeController(TimetableService timetableService, LectureService lectureService, GradeService gradeService) {
		this.timetableService = timetableService;
		this.lectureService = lectureService;
		this.gradeService = gradeService;
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
		List<Grade> gradeList = gradeService.getGradeListByTimetableId(timetableId);
		List<GradeDTO> gradeDTOList = gradeService.getGradeDTOList();
		List<String> allLectureName = gradeService.getAllLectureName(timetableId);
		
		model.addAttribute("allTimetableList", allTimetableList);
		model.addAttribute("creditList", creditList);
		model.addAttribute("selectedId", timetableId);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("gradeDTOList", gradeDTOList);
		model.addAttribute("allLectureName" ,allLectureName);
		
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
	
	@ResponseBody
	@GetMapping("/list")
	public List<GradeDTO> getGradeDTOList() {
		List<GradeDTO> gradeDTOList = gradeService.getGradeDTOList();
		return gradeDTOList;
	}
	
}
