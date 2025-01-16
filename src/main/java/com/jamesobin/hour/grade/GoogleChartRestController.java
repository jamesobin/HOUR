package com.jamesobin.hour.grade;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jamesobin.hour.grade.service.AverageDTOService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/api")
@RestController
public class GoogleChartRestController {

	private AverageDTOService averageDTOService;
	
	public GoogleChartRestController(AverageDTOService averageDTOService) {
		this.averageDTOService = averageDTOService;
	}
	
	@GetMapping("/average-data")
	public List<Map<String, Object>> getAverageDTOList(Model model, HttpSession session) {
		int userId = (Integer)session.getAttribute("userId");
		List<Map<String, Object>> averageList = averageDTOService.getAverageGradeForChart(userId);
		return averageList;
	}
	
}
