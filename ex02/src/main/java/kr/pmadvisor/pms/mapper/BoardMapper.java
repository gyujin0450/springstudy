package kr.pmadvisor.pms.mapper;

import java.util.List;

import kr.pmadvisor.pms.domain.BoardVO;
import kr.pmadvisor.pms.domain.Criteria;

// 기존 DAO를 Mapper가 대신함!!!
// 환경 셋팅을 통해 DAOImpl 없이 바로 xml과 연결됨!! (p95)

/*
 * 화면에 10개씩 데이터를 보여준다고 가정할 때, 
 * 사용자가 1페이지를 원한다면 limit 0,10의 구문이 완성되어야 하고, 
 * 2페이지를 원한다면 limit 10,10, 3페이지를 원한다면 limit 20,10과 같은 형태가 되어야 합니다.
 * 
 * 
 */

public interface BoardMapper {
	
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
//	public void insertSelectKey(BoardVO board); // MySQL에서는..?
	
	public BoardVO read(Long bno);	// DB에서 bno을  int로 했는...
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	// paging 처리...기존책 p249
	public List<BoardVO> listpage(int page);
	
	// 전체 데이터의 개수 처리 ... 개정판 p322
	public int getTotalCount(Criteria cri);
	

}
