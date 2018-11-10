package kr.pmadvisor.pms.ex03.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.pmadvisor.pms.ex03.domain.Criteria;
import kr.pmadvisor.pms.ex03.domain.ReplyVO;
import kr.pmadvisor.pms.ex03.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {
	
	private ReplyMapper mapper;
		
	@Override
	public int register(ReplyVO vo) {
		
		log.info("register..........."+ vo);
		
		return mapper.createReply(vo);
	}

	@Override
	public ReplyVO get(Long rno) {

		log.info("get.............." + rno);
		
		return mapper.readRno(rno);	
	}

	@Override
	public int modify(ReplyVO vo) {

		log.info("modify........."+ vo);
		
		return mapper.updateReplyText(vo);	 
	}

	@Override
	public int remove(Long rno) {

		log.info("remove........."+rno);
		
		return mapper.deleteRno(rno);
	}
	
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {

		log.info("get Reply List of board......"+ bno);
		
		return mapper.getListWithPaging(cri,bno);		
	}

}
