package kr.pmadvisor.pms.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.pmadvisor.pms.domain.BoardVO;
import kr.pmadvisor.pms.domain.Criteria;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}
	
	@Test
	public void testInsert() {
		
		BoardVO board = new BoardVO();
		
		board.setTitle("새글1");
		board.setContent("새내용1");
		board.setWriter("user2");
		
		mapper.create(board);
		
		log.info(board); 
		// Lombok이 만들오 주는 toString()을 이용해
		// bno 멤버 변수의 값을 알아보기 위함(p191)
							
		
	}
	
	@Test
	public void testRead() {
		
		// 존재하는 게시물 번호로 테스트
		BoardVO board = mapper.read(5L);
		
		log.info(board);
		
	}
	
	
	@Test
	public void testDelete() {
		
		log.info("DELETE COUNT:" + mapper.delete(3L));
		
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
		
		// 실행전에 존재하는 번호 인지 확인 할 것!!
		board.setBno(5L);
		board.setTitle("수정5");
		board.setContent("수정5");
		board.setWriter("user2");
		
		int count = mapper.update(board);
		
		log.info("UPDATE COUNT:" + count);
		
	}
	
	@Test
	public void testPaging() {
		
		Criteria cri = new Criteria();
		
		cri.setPageStart(3);
		cri.setPerPageNum(10);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		log.info("list :" + list);
		
		list.forEach(board -> log.info(board.getBno()));
		
	}
	
	@Test
	public void testsearch() {
		
		Criteria cri = new Criteria();
		cri.setKeyword("40");
		cri.setType("TC");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
		
	}
	
}
