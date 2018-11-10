<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시판 수정</h1>
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
				<form role="form" action="/board/modify" method="post">
				
					<!-- p318 : 수정 삭제 처리 추가 시작 -->
					<input type='hidden' name='pageStart'  	value ='<c:out value="${cri.pageStart }"/>'>
					<input type='hidden' name='perPageNum' 	value ='<c:out value="${cri.perPageNum }"/>'>
					<input type='hidden' name='type' 		value ='<c:out value="${cri.type}"/>'>
					<input type='hidden' name='keyword' 	value ='<c:out value="${cri.keyword}"/>'>
					<!-- 추가 끝 -->
				
					<div class="form-group">
						<label>번호</label> <input class="form-control" name='bno'
							value='<c:out value="${board.bno }" />' readonly="readonly">
					</div>

					<div class="form-group">
						<label>제목</label> <input class="form-control" name='title'
							value='<c:out value="${board.title }" />'>
					</div>

					<div class="form-group">
						<label>상세 내용</label>
						<textarea class="form-control" rows="3" name='content'>
						<c:out value="${board.content }" /></textarea>
					</div>

					<div class="form-group">
						<label>작성자</label> <input class="form-control" name='writer'
							value='<c:out value="${board.writer }"/>' readonly="readonly">
					</div>

					<button type="submit" data-oper='modify' class="btn btn-default">수정</button>
					<button type="submit" data-oper='remove' class="btn btn-danger">삭제</button>
					<button type="submit" data-oper='list' class="btn btn-info">목록</button>
				</form>
			</div>

			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<!-- p262 버튼에 따라서 다른 동작을 하도록 함 -->
<script>
	$(document).ready(function(){
		
		var formObj = $("form");
		
		$('button').on("click", function(e){
		
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation);
			
			if(operation === 'remove'){
				formObj.attr("action", "/board/remove");
			}else if(operation === 'list'){
				// 수정전...
				// self.location="/board/list";
				// return;
				// 수정후...
				formObj.attr("action", "/board/list").attr("method","get");
				// p321 변경부분
				var pageStartTag 	= $("input[name='pageStart']").clone();
				var perPageNumTag 	= $("input[name='perPageNum']").clone();
				
				var keywordTag 		= $("input[name='keyword']").clone();
				var typeTag 		= $("input[name='type']").clone();
				
				formObj.empty();
				// p320 변경부분
				formObj.append(pageStartTag);				
				formObj.append(perPageNumTag);
				
				formObj.append(keywordTag);
				formObj.append(typeTag);
				
				
			}
			formObj.submit();
		});
	});
</script>

<%@include file="../includes/footer.jsp"%>

