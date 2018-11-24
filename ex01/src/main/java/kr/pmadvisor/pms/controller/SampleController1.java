package kr.pmadvisor.pms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.pmadvisor.pms.domain.SampleDTO;
import kr.pmadvisor.pms.domain.SampleDTOList;
import kr.pmadvisor.pms.domain.TodoDTO1;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController1 {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public void basic() {

		log.info("basic.................");

	}

	@GetMapping("/basicOnlyGet")
	public void basicGet2() {

		log.info("basic get only get............");
	}

	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {

		log.info("" + dto);

		return "sample/ex01";
	}

	@GetMapping("/ex02")
	public String ex02(@RequestParam("irum") String name, @RequestParam("nai") int age) {

		log.info("name : " + name);
		log.info("age  : " + age);

		return "sample/ex02";
	}
	
	@GetMapping("/ex02Test")
	public String ex02Test(String name, int age) {

		log.info("name : " + name);
		log.info("age  : " + age);

		return "sample/ex02Test";
	}
	

	@GetMapping("/ex02List")
	public String ex02(@RequestParam("ids") ArrayList<String> ids) {

		log.info("ids : " + ids);

		return "info_page";
	}
	
	@GetMapping("/ex02Array")
	public String ex02(@RequestParam("ids") String[] ids) {

		log.info("array ids : " + Arrays.toString(ids));

		return "info_page";
	}
	
	

	@GetMapping("/ex02Bean")
	public String ex02(SampleDTOList list) {

		log.info("list dtos : " + list);

		return "info_page";
	}

	@GetMapping("/ex031")
	public String ex031(TodoDTO1 todo) {

		log.info("todo : " + todo);

		return "info_page";
	}

	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		// int 타입으로 선언된 page는 jsp로 전달되지 않아
		// @ModelAttribute 를 통해 Model에 담아서 전달함!! (p142)

		log.info("dto: " + dto);
		log.info("page: " + page);

		return "/sample/ex04";
	}

	@GetMapping("/ex05")
	public void ex05() {

		log.info("/ex05..............");

	}

	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {

		log.info("/ex06...............");

		SampleDTO dto = new SampleDTO();

		dto.setAge(10);
		dto.setName("홍길동");

		return dto;
	}
	
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07(){
		
		log.info("/ex07......................");
		
		// {"name":"홍길동"}
		String msg = "{\"name\" : 홍길동 \"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8"); 
		
		return new ResponseEntity<>(msg,header,HttpStatus.OK);		
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload.................");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		
		files.forEach(file -> {
			log.info("----------------------");
			log.info("name:" + file.getOriginalFilename());
			log.info("size:"+ file.getSize());			
		});
	}
}
