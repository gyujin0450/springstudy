package kr.pmadvisor.pms.ex03.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pmadvisor.pms.ex03.domain.SampleVO;
import kr.pmadvisor.pms.ex03.domain.Ticket;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {

	// 개정판 p358
	@GetMapping(value = "/getText", produces="text/plan; charset=UTF-8")
	public String getText() {
		
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "Hi..규진";
	
	}
	
	// 개정판 p361 : SampleVO를 리턴하는 메서드
	@GetMapping(value = "/getSample", 
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
						 MediaType.APPLICATION_XML_VALUE})
	public SampleVO getSample() {
		
		return new SampleVO(112, "규진", "최");
	}
	
	
	// 개정판 p363 : produces 속성은 반드시 지정하는 것은 아님
	@GetMapping(value = "/getSample2") 
	public SampleVO getSample2() {
		
		return new SampleVO(113, "준호", "최");
	}
	
	// 개정판 p363 : 배열이나 리스크 객체를 전송하는 경우
	@GetMapping(value = "/getList")
	public List<SampleVO> getList(){
		
		return IntStream.range(1,10).mapToObj(i -> new SampleVO(i, i+ " First", i + " Last"))
				.collect(Collectors.toList());
		
	}
	
	// 개정판 p364 : 맵 타입의 객체를 전송하는 경우
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap(){
		
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First",new SampleVO(111,"현호", "최"));
		
		return map;
		
	}
	
	// 개정판 p365 : ResponseEntity는 데이터와 함께 HTTP 허더의 상태 메시지를 전달함 
	@GetMapping(value= "/check", params = {"height","weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight){
		
		SampleVO vo = new SampleVO(0, "" + height, ""+weight);
		
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
		
	}
	
	// 개정판 p366 : @PathVariable
	@GetMapping("/product/{cat}/{pid}")
	public String[] getPath( @PathVariable("cat") String cat,
							 @PathVariable("pid") String pid){
		
		return new String[] { 
				"categoty : " + cat,
				"productid: " + pid 
		};
	}
	
	// 개정판 p368 : @RequestBody
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		
		log.info("conver..........ticket " + ticket);
		
		return ticket;
		
	}
	
}
