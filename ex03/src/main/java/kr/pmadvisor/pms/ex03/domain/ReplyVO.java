package kr.pmadvisor.pms.ex03.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	
	private Long	rno;
	private Long	bno;
	private String	replytext;
	private String	replyer; 
	private Date 	regdate;
	private Date 	updatedate;
	
}
