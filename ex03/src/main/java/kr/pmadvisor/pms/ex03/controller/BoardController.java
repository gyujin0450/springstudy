package kr.pmadvisor.pms.ex03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.pmadvisor.pms.ex03.domain.BoardVO;
import kr.pmadvisor.pms.ex03.domain.Criteria;
import kr.pmadvisor.pms.ex03.domain.PageDTO;
import kr.pmadvisor.pms.ex03.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		
//		log.info("list.........." + model);
//		model.addAttribute("list", service.getList());
//	}
	
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		
		log.info("list.........." + model);
		model.addAttribute("list", service.getList(cri));
		
//		model.addAttribute("pageMaker", new PageDTO(cri, 123));
		// 임의의 123 대신 전체 페이지 구하기 : 2949116
		int total = service.getTotal(cri);
		
		log.info("total : " + total);
		log.info("perPageNum : " + cri.getPerPageNum());
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		
	}
	
	
	// p239 게시물의 등록 작업은 Post 작업으로 처리하지만
	// 화면에 입력을 받아야 하므로 GET방식으로 입력 페이지를 볼 수 있도록 메소드 추가
	@GetMapping("/register")
	public void register() {
		// 입력페이지(register.jsp)를 보여 주는 역할만 하기 때문에 별도의 처리는 필요하지 않음!!		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		log.info("register: " + board);
		
		service.register(board);
		
		rttr.addFlashAttribute("result", board.getBno()); 
		// p246 일회성으로만 데이터를 result에 전달함 
		
		return "redirect:/board/list";		
	}
	
	@GetMapping({"/get", "/modify"})  // p259 수정 페이지 추가!!!
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri , Model model) {
		
		log.info("/get or /modify.......");
		model.addAttribute("board",service.get(bno));
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri , RedirectAttributes rttr) {
		
		log.info("modify:" + board);
		
		if(service.modify(board)) {
			rttr.addFlashAttribute("result","success");
		}
		
//		cri.getListLink() 를 이용한 파라미터 전달
		
//		rttr.addFlashAttribute("pageStart"	, cri.getPageStart());
//		rttr.addFlashAttribute("perPageNum"	, cri.getPerPageNum());
//		rttr.addFlashAttribute("type"		, cri.getType());
//		rttr.addFlashAttribute("keyword"	, cri.getKeyword());
		
		return "redirect:/board/list" + cri.getListLink();		
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno")Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		
		log.info("/remove............");
		
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result","success");
		}
		
//		cri.getListLink() 를 이용한 파라미터 전달
		
//		rttr.addFlashAttribute("pageStart"	, cri.getPageStart());
//		rttr.addFlashAttribute("perPageNum"	, cri.getPerPageNum());
//		rttr.addFlashAttribute("type"		, cri.getType());
//		rttr.addFlashAttribute("keyword"	, cri.getKeyword());

		return "redirect:/board/list" + cri.getListLink();
		
	}
	
}
