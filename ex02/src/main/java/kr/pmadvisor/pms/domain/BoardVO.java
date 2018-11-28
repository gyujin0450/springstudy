package kr.pmadvisor.pms.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	private Long 	bno;	// 게시물이 많아 질수 있으니 Long으로 잡음
	private String 	title;
	private String 	content; // MySQL TEXT 타입을  읽어오는 방법은?
	private String 	writer;
	private Date 	regdate;
	private Date 	updatedate; // 대소문자 구분함.. 교재에서는 updateDate로 표기함
//	private int viewcnt;
	
}


