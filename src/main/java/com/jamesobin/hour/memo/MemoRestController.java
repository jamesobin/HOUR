package com.jamesobin.hour.memo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jamesobin.hour.memo.service.MemoService;

import jakarta.servlet.http.HttpSession;

@RestController
public class MemoRestController {
	
	private MemoService memoService;
	
	public MemoRestController(MemoService memoService) {
		this.memoService = memoService;
	}

	@PostMapping("/create")
	public Map<String, String> createMemo(
			@RequestParam(value="lectureId", required=false) int lectureId
			, @RequestParam("title") String title
			, @RequestParam("contents") String contents
			, @RequestParam(value="imageFile", required=false) MultipartFile file
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		
		if(memoService.addMemo(userId, lectureId, title, contents, file)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
}
