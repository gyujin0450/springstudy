package kr.pmadvisor.pms.ex03.service;

import java.util.List;

import kr.pmadvisor.pms.ex03.domain.BoardVO;
import kr.pmadvisor.pms.ex03.domain.Criteria;

public interface BoardService {
	
	public void register(BoardVO board); 	// 등록
	
	public BoardVO get(Long bno);		 	// 단건조회	
	
	public boolean modify(BoardVO board); 	// 수정
	
	public boolean remove(Long bno);		// 삭제	
	
//	public List<BoardVO> getList();			// 전체조회
	
	public List<BoardVO> getList(Criteria cri);	// 전체조회(Paging 반영)
	
	public int getTotal(Criteria cri); // 전체 데이터 개수 : p323
	

	
}
