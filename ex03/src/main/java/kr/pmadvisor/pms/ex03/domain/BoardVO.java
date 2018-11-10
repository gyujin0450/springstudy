package kr.pmadvisor.pms.ex03.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	
	private int bno;
	private String title;
	private String content; // MySQL TEXT 타입을  읽어오는 방법은?
	private String writer;
	private Date regdate;
	private Date updatedate; // 대소문자 구분함.. 교재에서는 updateDate로 표기함
//	private int viewcnt;
	
}

/* 기존 교재 : p163 참고
 
CREATE TABLE tbl_board(
	bno int not null AUTO_INCREMENT,
    title varchar(200) not null,
    content TEXT null,
    writer varchar(50) not null,
    regdate TIMESTAMP not null default now(),
    updatedate TIMESTAMP not null default now(),
    viewcnt int default 0,
    PRIMARY KEY(bno)
);

INSERT into tbl_board (title, content, writer)
values ("제목 1","내용 1","user1");

INSERT into tbl_board (title, content, writer)
values ("제목 2","내용 2","user1");

INSERT into tbl_board (title, content, writer)
values ("제목 3","내용 3","user1");

INSERT into tbl_board (title, content, writer)
values ("제목 4","내용 4","user1");

INSERT into tbl_board (title, content, writer)
values ("제목 5","내용 5","user1");

select * from tbl_board;

 */
