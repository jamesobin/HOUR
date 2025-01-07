package com.jamesobin.hour.lecture;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timetable")
@Controller
public class LectureController {

	@GetMapping("/lecture/input-view")
	public String addLecture() {
		return "timetable/lectureinput";
	}

}
