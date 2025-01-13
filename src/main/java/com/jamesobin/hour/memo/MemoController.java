package com.jamesobin.hour.memo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jamesobin.hour.lecture.dto.CreditDTO;
import com.jamesobin.hour.lecture.service.LectureService;
import com.jamesobin.hour.memo.domain.Memo;
import com.jamesobin.hour.memo.service.MemoService;
import com.jamesobin.hour.timetable.domain.Timetable;
import com.jamesobin.hour.timetable.service.TimetableService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/memo")
public class MemoController {
	
	private TimetableService timetableService;
	private MemoService memoService;
	private LectureService lectureService;
	
	public MemoController(TimetableService timetableService, MemoService memoService, LectureService lectureService) {
		this.timetableService = timetableService;
		this.memoService = memoService;
		this.lectureService = lectureService;
	}

	@GetMapping("/create-view")
	public String inputMemo(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");

		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		List<CreditDTO> creditDTOList = lectureService.getCreditListByUserId(userId);
		
		model.addAttribute("allTimetableList", allTimetableList);
		model.addAttribute("creditDTOList", creditDTOList);
		
		return "memo/input";
	}
	
	@GetMapping("/list-view")
	public String memoList(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		
		List<Memo> memoList = memoService.getMemoList(userId);
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		
		model.addAttribute("memoList", memoList);
		model.addAttribute("allTimetableList", allTimetableList);
		
		return "memo/list";
	}
	
	@GetMapping("/detail-view")
	public String memoDetail(Model model, HttpSession session, @RequestParam("id") int id) {
		int userId = (Integer)session.getAttribute("userId");
		
		Memo memo = memoService.getMemo(id);
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		
		model.addAttribute("memo", memo);
		model.addAttribute("allTimetableList", allTimetableList);
		
		return "memo/detail";
	}
	
	@GetMapping("/update-view")
	public String memoUpdate(Model model, HttpSession session, @RequestParam("id") int id) {
		int userId = (Integer)session.getAttribute("userId");
		
		Memo memo = memoService.getMemo(id);
		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		List<CreditDTO> creditDTOList = lectureService.getCreditListByUserId(userId);
		
		model.addAttribute("memo", memo);
		model.addAttribute("allTimetableList", allTimetableList);
		model.addAttribute("creditDTOList", creditDTOList);
		
		return "memo/update";
	}
	
}
