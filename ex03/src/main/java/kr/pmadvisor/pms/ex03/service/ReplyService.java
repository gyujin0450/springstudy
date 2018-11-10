package kr.pmadvisor.pms.ex03.service;

import java.util.List;

import kr.pmadvisor.pms.ex03.domain.Criteria;
import kr.pmadvisor.pms.ex03.domain.ReplyVO;

public interface ReplyService {
	
	public int register(ReplyVO vo);	 		// 등록
		
	public ReplyVO get(Long bno);			 	// 단건조회	
	
	public int modify(ReplyVO board);		 	// 수정
	
	public int remove(Long bno);				// 삭제	
	
	public List<ReplyVO> getList(Criteria cri, Long bno);	// 전체조회(Paging 반영)
	
}
