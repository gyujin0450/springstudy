package kr.pmadvisor.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.pmadvisor.pms.domain.TodoDTO2;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample2/*")
@Log4j
public class SampleController2 {
	
	@GetMapping("/ex032")
	public String ex032(TodoDTO2 todo) {
		
		log.info("todo : " + todo);
		
		return "ex01";
	}
	
}
