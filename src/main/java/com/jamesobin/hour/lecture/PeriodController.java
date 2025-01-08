package com.jamesobin.hour.lecture;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jamesobin.hour.lecture.dto.PeriodDTO;
import com.jamesobin.hour.lecture.service.PeriodDTOService;

@Controller
public class PeriodController {
	
	private PeriodDTOService periodDTOService;
	
	public PeriodController(PeriodDTOService periodDTOService) {
		this.periodDTOService = periodDTOService;
	}
	
	@ResponseBody
	@GetMapping("/period/list")
	public List<PeriodDTO> periodDTOList(@RequestParam("id") int timetableId) {
		List<PeriodDTO> periodDTOList = periodDTOService.getPeriodList(timetableId);
		
		return periodDTOList;
	}

}
