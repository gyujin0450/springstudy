package kr.pmadvisor.pms.ex03.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	// p304 ~ 305 페이징 관련 로직	
	public PageDTO(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int)(Math.ceil(cri.getPageStart()/10.0)) * 10;
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)(Math.ceil((total * 1.0)/ cri.getPerPageNum()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1 ;
		this.next = this.endPage < realEnd;
		
		System.out.println("--------------------");
		System.out.println("this.cri : " 		+ this.cri);
		System.out.println("this.total : " 		+ this.total);
		System.out.println("this.endPage : " 	+ this.endPage);
		System.out.println("this.startPage : " 	+ this.startPage);
		System.out.println("realEnd : " 		+ realEnd);
		System.out.println("this.prev  : " 		+ this.prev );
		System.out.println("this.next  : " 		+ this.next );
		System.out.println("--------------------");			
	}
	
}
