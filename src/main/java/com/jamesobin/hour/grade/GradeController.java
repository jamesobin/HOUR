package com.jamesobin.hour.grade;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamesobin.hour.grade.domian.Grade;
import com.jamesobin.hour.grade.dto.GradeDTO;
import com.jamesobin.hour.grade.service.AverageDTOService;
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
	private AverageDTOService averageDTOService;
	
	public GradeController(TimetableService timetableService
			, LectureService lectureService
			, GradeService gradeService
			, AverageDTOService averageDTOService) {
		this.timetableService = timetableService;
		this.lectureService = lectureService;
		this.gradeService = gradeService;
		this.averageDTOService = averageDTOService;
	}

	@GetMapping("/input-view")
	public String addGrade(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		double averageGrade = gradeService.getAverageGrade(userId);
		int creditSum = gradeService.getCreditSum(userId);
		
		model.addAttribute("allTimetableList", allTimetableList);
		model.addAttribute("averageGrade", averageGrade);
		model.addAttribute("creditSum", creditSum);
		
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
		double averageGrade = gradeService.getAverageGrade(userId);
		double averageGradeByTimetableId = gradeService.getAverageGradeByTimetableId(timetableId);
		int creditSum = gradeService.getCreditSum(userId);
		
		model.addAttribute("allTimetableList", allTimetableList);
		model.addAttribute("creditList", creditList);
		model.addAttribute("selectedId", timetableId);
		model.addAttribute("gradeList", gradeList);
		model.addAttribute("gradeDTOList", gradeDTOList);
		model.addAttribute("allLectureName" ,allLectureName);
		model.addAttribute("averageGrade", averageGrade);
		model.addAttribute("creditSum", creditSum);
		model.addAttribute("averageGradeByTimetableId", averageGradeByTimetableId);
		
		return "grade/input";
	}
	
	@GetMapping("/entire-view")
	public String getEntireGrade(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		double averageGrade = gradeService.getAverageGrade(userId);
		int creditSum = gradeService.getCreditSum(userId);
		
		model.addAttribute("allTimetableList", allTimetableList);
		model.addAttribute("averageGrade", averageGrade);
		model.addAttribute("creditSum", creditSum);
		
		return "grade/entire-grade";
	}
	
	@GetMapping("/specific-view")
	public String getSpecificGrade(Model model, HttpSession session, @RequestParam(value="id", required=false) Integer timetableId) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		double averageGrade = gradeService.getAverageGrade(userId);
		int creditSum = gradeService.getCreditSum(userId);
		List<Map<String, Object>> termList = gradeService.getTerm(userId);
		
		model.addAttribute("allTimetableList", allTimetableList);
		model.addAttribute("averageGrade", averageGrade);
		model.addAttribute("creditSum", creditSum);
		model.addAttribute("termList", termList);
		model.addAttribute("selectedId", timetableId);
		
		return "grade/specific-grade";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public List<Map<String, Object>> getAverageDTOList(@RequestParam("id") int timetableId) {
		List<Map<String, Object>> gradeList = averageDTOService.getGradeForChart(timetableId);
		return gradeList;
	}
	
	@ResponseBody
	@GetMapping("/term/list")
	public List<Map<String, Object>> termList(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		List<Map<String, Object>> termList = gradeService.getTerm(userId);
		return termList;
	}
	
}
