package com.jamesobin.hour.lecture;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamesobin.hour.lecture.dto.LectureDTO;
import com.jamesobin.hour.lecture.service.LectureService;

@RequestMapping("/timetable")
@Controller
public class LectureController {
	
	private LectureService lectureService;
	
	public LectureController(LectureService lectureService) {
		this.lectureService = lectureService;
	}

	@GetMapping("/lecture/input-view")
	public String addLecture() {
		return "timetable/lectureinput";
	}
	
	@ResponseBody
	@GetMapping("/lecture/list")
	public List<LectureDTO> lectureDTOList(@RequestParam("id") int timetableId) {
		List<LectureDTO> lectureDTOList = lectureService.getLectureList(timetableId);
		
		return lectureDTOList;
	}

}
