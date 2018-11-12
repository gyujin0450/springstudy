<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시판 등록</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">게시판 조회</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="form-group">
					<label>번호</label> <input class="form-control" name='bno'
						value='<c:out value="${board.bno }" />' readonly="readonly">
				</div>

				<div class="form-group">
					<label>제목</label> <input class="form-control" name='title'
						value='<c:out value="${board.title }" />' readonly="readonly">
				</div>

				<div class="form-group">
					<label>상세 내용</label>
					<textarea class="form-control" rows="3" name='content'
						readonly="readonly">
						<c:out value="${board.content }" /></textarea>
				</div>

				<div class="form-group">
					<label>작성자</label> <input class="form-control" name='writer'
						value='<c:out value="${board.writer }"/>' readonly="readonly">
				</div>

				<!-- 수정전 
				<button data-oper='modify' class="btn btn-default">
					<a href="/board/modify?bno=<c:out value="${board.bno }"/>">수정</a></button>
				<button data-oper='list' class="btn btn-info">
					<a href="/board/list" >목록</a></button>
				 -->
				<!-- 수정후 : p264 -->
				<button data-oper='modify' class="btn btn-default">수정</button>
				<button data-oper='list' class="btn btn-info">목록</button>

				<form id='operForm' action="/board/modify" method="get">
					<input type='hidden' id='bno' name='bno'
						value='<c:out value="${board.bno}"/>'> <input
						type='hidden' name='pageStart'
						value='<c:out value="${cri.pageStart }"/>'> <input
						type='hidden' name='perPageNum'
						value='<c:out value="${cri.perPageNum }"/>'> <input
						type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
					<input type='hidden' name='keyword'
						value='<c:out value="${cri.keyword}"/>'>
				</form>

			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
		<!-- p414 내용 추가 -->
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i>Reply
				<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New
					Reply</button>
			</div>

			<div class="panel-body">
				<ul class="chat">
					<!-- p439에서 삭제 처리
					<li class="left clearfix" data-rno='12'>
						<div>
							<div class="header">
								<strong class="primary-font">user00</strong> <small
									class="pull-right text-muted">2018-01-01 13:13</small>
							</div>
							<p>Good Job!!</p>
						</div>
					</li>
					 -->
				</ul>
			</div>
		</div>
		<!-- end of p414 내용 추가 -->
		<!-- p439 panel-footer 추가 -->
		<div class="panel-footer">
		
		</div>
		<!-- end of p439 panel-footer -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<!-- Modal : p420 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Modal title</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Reply Text</label>
					<input class="form-control" name='replytext' value='New Reply!!!'>
				</div>
				<div class="form-group">
					<label>Replyer</label>
					<input class="form-control" name='replyer' value='replyer'>
				</div>
				<div class="form-group">
					<label>Reply Date</label>
					<input class="form-control" name='regdate' value=''>
				</div>
			</div>
			<div class="modal-footer">
				<button id="modalModBtn" type="button" class="btn btn-warning">Modify</button>
				<button id="modalRemoveBtn" type="button" class="btn btn-danger">Remove</button>
				<button id="modalRegisterBtn" type="button" class="btn btn-primary">Register</button>
				<button id="modalCloseBtn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.Modal -->
<!-- p400 : 댓글관련 js 모듈화 -->
<script type="text/javascript" src="/resources/js/reply.js"></script>


<!-- p415 : 이벤트 처리  -->
<script>
$(document).ready(function(){
	
	var bnoValue = '<c:out value="${board.bno}"/>';
	var replyUL = $(".chat");
	
	showList(1);
	
	function showList(page){
		
		console.log("show list " + page);
		
// 		replyService.getList({bno:bnoValue, page: page||1}, function(list){
		replyService.getList({bno:bnoValue, page: page||1}, function(replyCnt, list){
			
			// p438 추가 부분 start
			console.log("replyCnt: " + replyCnt);
			console.log("list: " + list);
			console.log(list);
			
			if(page == -1){
				pageNum = Math.ceil(replyCnt/10.0);
				showList(pageNum);
				return;
			}
			// p438 추가 부분 end
			
			var str="";
			
			if(list == null || list.length == 0){
				return;
			}
			
			for(var i = 0, len = list.length || 0 ; i < len ; i++){
				
				str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
// 				str += "  <div><div class='header'><strong class='primary-font'>"+list[i].replyer+"</strong>";
				str += "  <div><div class='header'><strong class='primary-font'>["+ list[i].rno	+ "]"+list[i].replyer+"</strong>";
				str += "    <small class='pull-right text-muted'>" + replyService.displayTime(list[i].regdate) + "</small></div>";
				str += "    <p>"+list[i].replytext+"</p></div></li>";
				
			}
			
			replyUL.html(str);
			
			showReplyPage(replyCnt);
			
			}); // end function 
			
	} // end showList
	
	// p422
	var modal = $(".modal");
	var modalInputReplytext = modal.find("input[name='replytext']");
	var modalInputReplyer = modal.find("input[name='replyer']");
	var modalInputRegdate = modal.find("input[name='regdate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	
	$("#addReplyBtn").on("click", function(e){
	
		modal.find("input").val("");
		modalInputRegdate.closest("div").hide();
		modal.find("button[id !='modalCloseBtn']").hide();
		
		modalRegisterBtn.show();
		
		$(".modal").modal("show");
		
	});
	
	// p423 & p439
	modalRegisterBtn.on("click", function(e){
		
		var reply = {
			replytext : modalInputReplytext.val(),
			replyer : modalInputReplyer.val(),
			bno : bnoValue
		};
		
		replyService.add(reply, function(result){
		
			alert(result);
			
			modal.find("input").val("");
			modal.modal("hide");
			
// 			showList(1);
			showList(-1); // page를 -1로 전달하면, 전체 댓글의 숫자를 파악함 (p439)
			
		});
		
	});
	
	// p425 : UL의 클래스(chat)을 이용해서 이벤트 처리
	$(".chat").on("click", "li", function(e){
		
		var rno = $(this).data("rno");
		
// 		console.log(rno);
		
		replyService.get(rno, function(reply){
			
			modalInputReplytext.val(reply.replytext);
			modalInputReplyer.val(reply.replyer);
			modalInputRegdate.val(replyService.displayTime(reply.regdate))
			.attr("readonly","readonly");
			modal.data("rno", reply.rno);
			
			modal.find("button[id != 'modalCloseBtn']").hide(); 
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");
			
		});		
	});
	
	// p427 : 댓글수정
	modalModBtn.on("click", function(e){
	
		var reply = {rno:modal.data("rno"), replytext: modalInputReplytext.val()};
		
		replyService.update(reply, function(result){
		
			alert(result);
			modal.modal("hide");
			showList(1);
			
		});
		
	});
	
	// p427 : 댓글 삭제
	modalRemoveBtn.on("click", function(e){
	
		var rno = modal.data("rno");
		
		replyService.remove(rno, function(result){
			
			alert(result);
			modal.modal("hide");
			showList(1);
			
		});
		
	});
	
	// p440 댓글 페이지 조회
	var pageNum = 1;
	var replyPageFooter = $(".panel-footer");
	
	function showReplyPage(replyCnt){
		
		var endNum = Math.ceil(pageNum /10.0) * 10;
		var startNum = endNum - 9;
		
		var prev = startNum != 1;
		var next = false;
		
		
		if(endNum * 10 >= replyCnt){
			endNum = Math.ceil(replyCnt/10.0);
		}
	
		if(endNum * 10 < replyCnt){
			next = true;
		}
		
		var str = "<ul class='pagination pull-right'>";
		
		if(prev){
			str += "<li class='page-item'><a class='page-link' href='" + (startNum - 1) + "'>이전</a></li>";
		}
		
		for(var i = startNum ; i <= endNum ; i++){
			
			var active = pageNum == i ? "active" : "";
			
			str += "<li class='page-item " + active + " '><a class='page-link' href='" + i + "'>" + i + "</a></li>";
		}
		
		if(next){
			str += "<li class='page-item'><a class='page-link' href='" + (endNum + 1) + "'> 다음</a></li>";
			
		}
		
		str += "</ul></div>";
		
		console.log(str);
		
		replyPageFooter.html(str);
		
	} // end of showReplyPage
			
	// p441 : 새로운 댓글 가져오기
	replyPageFooter.on("click", "li a", function(e){
		
		e.preventDefault();
		console.log("page click");
			
		var targetPageNum = $(this).attr("href");
			
		console.log("targetPageNum: " + targetPageNum);
			
		pageNum = targetPageNum;
			
		showList(pageNum);
			
	});
		

	
});	
</script>


<!-- p400 : 댓글관련 js 모듈화 테스트
<script type="text/javascript">
$(document).ready(function(){

	console.log("==================");
	console.log("JS TEST");
	
	var bnoValue = '<c:out value="${board.bno}"/>';
	
	// for replyService add test
	replyService.add(
			{replytext:"JS Test", replyer:"tester", bno:bnoValue },
			function(result){
				alert("ReSULT:" + result);
			}
	);
	
	// p407 : 해당게시물의 모든 댓글을 가져오느지 확인
 	replyService.getList(
 			{bno:bnoValue, page:1},
 			function(list){
 				for(var i=0, len = list.length||0 ; i < len ; i++){
 					console.log(list[i]);
 				}
 			}
 	);
	
	// p409 : 15번 댓글 삭제
 	replyService.remove(15, function(count){
	
 		console.log(count);
		
 		if(count === "success"){
			alert("삭제됨");
 		}
 	}, function(err) {
 		alert('15번 삭제 오류....');
 	});
	
	// p411 : 25번 댓글 수정
	replyService.update({
		rno : 25,
		bno : bnoValue,
		replytext : "Modified Reply(25번)....."
	}, function(result){
		alert("수정완료...")
	});
	
	// p413 : 댓글 조회 처리
	replyService.get(25, function(data){
		console.log(data);
	});
	
});
</script>
-->


<!-- 추가적인 처리 : p265-->
<script type="text/javascript">
$(document).ready(function(){
	
	var operForm = $("#operForm");
	
	$("button[data-oper='modify']").on("click", function(e){
		
		operForm.attr("action","/board/modify").submit();
		
	});
	
	$("button[data-oper='list']").on("click", function(e){
		
		operForm.find("#bno").remove();
		operForm.attr("action", "/board/list");
		operForm.submit();
		
	});
});

</script>

<%@include file="../includes/footer.jsp"%>

