package kr.pmadvisor.pms.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class Criteria {

	private int pageStart;  // 개정판 : pageNum
	private int perPageNum; // 개정판 : amount
	
	// MySQL문의 0번째 row 누락 방지
	private int rowStart; 	
	
	// 다중 항목 검색 처리(변수 선언)
	private String type;
	private String keyword;
	
	// 생성자
	public Criteria() {
		this.pageStart = 1;
		this.perPageNum = 10;
	}

	public Criteria(int pageStart, int perPageNum) {

		this.pageStart = pageStart;
		this.perPageNum = perPageNum;
	}

	// setter/getter, ToString() 로직 반영 필요!!

	public int getPageStart() {
		return pageStart;
	}

	/* pageStart --> rowStart 매핑 반영!!!
	 *  
	 * 1페이지 : 0 행부터 10개행 (  0 ~  9)
	 * 2페이지 : 1 행부터 10개행 ( 10 ~ 19)
	 */
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
		this.rowStart = (this.pageStart - 1)*this.perPageNum;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [pageStart=" + pageStart + ", perPageNum=" + perPageNum + ", rowStart=" + rowStart
				+ ", getPageStart()=" + getPageStart() + ", getPerPageNum()=" + getPerPageNum() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	// 다중 항목 검색 처리(Setter/Getter)
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	// 검색조간을 배열로 만들어 한번에 처리
	// 검색조건이 각 글자(T,W,C)로 구성됨...
	public String[] getTypeArr() {
		
		return type == null ? new String[]{} : type.split("");
		
	}
	
	// 파라미터 일괄 처리
	public String getListLink() {
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageStart"	, this.pageStart)
				.queryParam("perPageNum", this.getPerPageNum())
				.queryParam("type"		, this.getType())
				.queryParam("keyword"	, this.getKeyword());
		
		return builder.toUriString();
		
	}
	
}
