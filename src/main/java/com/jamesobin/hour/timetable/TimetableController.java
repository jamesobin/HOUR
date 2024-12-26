package com.jamesobin.hour.timetable;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timetable")
@Controller
public class TimetableController {
	
	@GetMapping("/detail-view")
	public String timetable() {
		return "timetable/detail";
	}
	
}
