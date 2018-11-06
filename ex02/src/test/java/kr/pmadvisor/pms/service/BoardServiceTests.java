package kr.pmadvisor.pms.service;

import static org.junit.Assert.assertNotNull;

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
public class BoardServiceTests {
	
	
	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	
	@Test
	public void testExist() {
		
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		
		board.setTitle("신규1");
		board.setContent("신규1");
		board.setWriter("user2");
		
		service.register(board);
		
		log.info("생성된 게시물의 번호:" + board.getBno());
	}
	
	@Test
	public void testGetList() {
		
//		service.getList().forEach(board -> log.info(board));
		service.getList(new Criteria(2,10)).forEach(board -> log.info(board));
		
	}
	
	@Test
	public void testGet() {
		
		log.info(service.get(2L));
	}

	@Test
	public void testDelete() {
		
		log.info("Remove Result:" + service.remove(2L));
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO board = service.get(2L);
		
		if(board == null) {
			log.info("Not Find.."+board);
			return;
		}
		
		board.setTitle("수정..");
		log.info("Modify Result: "+ service.modify(board));
		
	}	
	
}
