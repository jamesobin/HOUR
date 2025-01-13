package com.jamesobin.hour.memo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jamesobin.hour.memo.service.MemoService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/memo")
@RestController
public class MemoRestController {
	
	private MemoService memoService;
	
	public MemoRestController(MemoService memoService) {
		this.memoService = memoService;
	}

	@PostMapping("/create")
	public Map<String, String> createMemo(
			@RequestParam(value="timetableId", required=false) int timetableId
			, @RequestParam(value="lectureName", required=false) String lectureName
			, @RequestParam("title") String title
			, @RequestParam("contents") String contents
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		
		if(memoService.addMemo(userId, timetableId, lectureName, title, contents)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@DeleteMapping("/delete")
	public Map<String, String> deleteMemo(@RequestParam("id") int id) {
		Map<String, String> resultMap = new HashMap<>();
		
		if(memoService.deleteMemo(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@PutMapping("/update")
	public Map<String, String> updateMemo(
			@RequestParam("id") int id
			, @RequestParam(value="timetableId", required=false) int timetableId
			, @RequestParam(value="lectureName", required=false) String lectureName
			, @RequestParam("title") String title
			, @RequestParam("contents") String contents) {
		Map<String, String> resultMap = new HashMap<>();
		if(memoService.updateMemo(id, timetableId, lectureName, title, contents)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
