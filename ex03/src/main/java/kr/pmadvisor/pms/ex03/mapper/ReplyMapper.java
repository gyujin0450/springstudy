package kr.pmadvisor.pms.ex03.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.pmadvisor.pms.ex03.domain.Criteria;
import kr.pmadvisor.pms.ex03.domain.ReplyVO;

// 개정판 p378 참고 : CRUD
public interface ReplyMapper {

	public int createReply(ReplyVO vo);
	
	public ReplyVO readRno(Long rno);
	
	public int updateReplyText(ReplyVO replytext);
	
	public int deleteRno(Long rno);
	
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("bno") Long bno);
	
	public int getCountByBno(Long bno); // p432
	
}
