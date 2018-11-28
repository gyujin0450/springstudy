package kr.pmadvisor.pms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.pmadvisor.pms.domain.BoardVO;
import kr.pmadvisor.pms.domain.Criteria;
import kr.pmadvisor.pms.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	// spring 4.3 이상에서 자동 처리
	// @Setter(onMethod_ = @Atutowired)
	private BoardMapper mapper;
	
	@Override
	public void register(BoardVO board) {
		
		log.info("register..........."+ board);
		
		mapper.create(board);		// 등록 SQL 함수 호출
		
	}

	@Override
	public BoardVO get(Long bno) {

		log.info("get.............." + bno);
		
		return mapper.read(bno);	// 단건 조회 SQL 함수 호출
	}

	@Override
	public boolean modify(BoardVO board) {

		log.info("modify........."+ board);
		
		return mapper.update(board) == 1;	// 수성 SQL 함수를 호출하고, 정상 처리시 1이 리턴됨... 
	}

	@Override
	public boolean remove(Long bno) {

		log.info("remove........."+bno);
		
		return mapper.delete(bno) == 1;		// 수성 SQL 함수를 호출하고, 정상 처리시 1이 리턴됨...
	}

	@Override
	public List<BoardVO> getList() {

		log.info("getList................");
		
		return mapper.getList();	// 목록조회	
	}	
	
	@Override
	public List<BoardVO> getList(Criteria cri) {

		log.info("getList................"+ cri);
		
		return mapper.getListWithPaging(cri);	// 목록조회(Paging 적용)	
	}

	@Override
	public int getTotal(Criteria cri) {
		
		log.info("get total count ..............");
		
		return mapper.getTotalCount(cri);
	}

}
