package kr.pmadvisor.pms.ex03.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kr.pmadvisor.pms.ex03.domain.Criteria;
import kr.pmadvisor.pms.ex03.domain.ReplyVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplayMapperTests {

	// p383
	private Long[] bnoArr = {3112910L, 3112908L, 3112906L, 3112905L, 3112904L};
	
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	
	// p381
	@Test
	public void testMapper() {
		
		// JUnit으로 Test전에 web.xml, root-context.xml, servlet-context.xml 등의 환경설정 필수!!
		// 이 테스트가 성공하면, 기본적인 접속 환경이 완료된 것임!! (ex02 의 기존 설정을 복사함)
		log.info(mapper);
		
	}
	
	// p383
	@Test
	public void testCreate() {
		
		IntStream.rangeClosed(1,10).forEach(i -> {
		
			ReplyVO vo = new ReplyVO();
			
			// 게시물 번호
			vo.setBno(bnoArr[i%5]);
			vo.setReplytext("댓글 테스트 " + i);
			vo.setReplyer("replyer " + i);
			
			mapper.createReply(vo);
		});
	}

	// p385
	@Test
	public void testReadRno() {
		
		Long targetRno = 2L;
				
		ReplyVO vo = mapper.readRno(targetRno);
		
		log.info(vo);
		
	}
	
	// p386
	@Test
	public void testDeleteRno() {
		
		Long targetRno = 4L;
		
		mapper.deleteRno(targetRno);
		
	}
	
	// p387
	@Test
	public void testUpdate() {
		
		Long targetRno = 11L;
		
		ReplyVO vo = mapper.readRno(targetRno);
		
		vo.setReplytext(targetRno + " 번 댓글 수정");
		
		int count = mapper.updateReplyText(vo);
		
		log.info("Update Count : " + count);
		
		
	}
	
	// p388
	@Test
	public void testList() {
		
		Criteria cri = new Criteria();
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]); // 
		
		replies.forEach(reply -> log.info(reply));	
		
	}
	
	// p431
	@Test
	public void testListwithPage() {
		
		Criteria cri = new Criteria(3,10);
		
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 3112910L); // 
		
		replies.forEach(reply -> log.info(reply));	
		
	}
	
	
}


