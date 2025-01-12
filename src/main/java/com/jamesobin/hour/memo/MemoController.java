package com.jamesobin.hour.memo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	public MemoController(TimetableService timetableService, MemoService memoService) {
		this.timetableService = timetableService;
		this.memoService = memoService;
	}

	@GetMapping("/create-view")
	public String inputMemo(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");

		List<Timetable> allTimetableList = timetableService.getTimetableList(userId);
		
		model.addAttribute("allTimetableList", allTimetableList);
		
		return "memo/input";
	}
	
	@GetMapping("/list-view")
	public String memoList(
			Model model
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<Memo> memoList = memoService.getPostList(userId);
		
		model.addAttribute("memoList", memoList);
		
		return "memo/list";
	}
	
	@GetMapping("/detail-view")
	public String memoDetail(
			@RequestParam("id") int id
			, Model model) {
		Memo memo = memoService.getMemo(id);
		
		model.addAttribute("memo", memo);
		
		return "memo/detail";
	}
	
	@GetMapping("/update-view")
	public String memoUpdate(
			@RequestParam("id") int id
			, Model model) {
		Memo memo = memoService.getMemo(id);
		
		model.addAttribute("memo", memo);
		
		return "memo/update";
	}
	
}
