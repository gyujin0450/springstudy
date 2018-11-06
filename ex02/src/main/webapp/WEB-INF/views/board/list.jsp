<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<!-- p250 list.jsp에 등록 버튼 추가 -->			
			<div class="panel-heading">
				Board List Page
				<button id='regBtn' type="button" class="btn btn-xs pull-right">신규등록</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<c:forEach items="${list }" var="board">
						<tr>
							<td><c:out value="${board.bno }" /></td>
							<!-- p314 : 목록조회후 첫페이지로 가는 문제 해결 
							     class 속성을 부여하고, 이벤트 처리 추가!!  
							-->
							<td><a class='move' href='<c:out value="${board.bno }"/>'>
								<c:out value="${board.title }"/></a></td>
							<td><c:out value="${board.writer}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.regdate }" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.updatedate }" /></td>
						</tr>
					</c:forEach>
				</table>
				
				<!-- 검색조건 추가 : p340 -->
				<div class='row'>
					<div class='col-lg-12'>
						<form id='searchForm' action="/board/list" method='get'>
							<select name='type'>
								<option value=""
									<c:out value="${pageMaker.cri.type == null ? 'selected' : ''}"/>>---</option>
								<option value="T"
									<c:out value="${pageMaker.cri.type eq 'T' ? 'selected' : ''}"/>>제목</option>
								<option value="C"
									<c:out value="${pageMaker.cri.type eq 'C' ? 'selected' : ''}"/>>내용</option>
								<option value="W"
									<c:out value="${pageMaker.cri.type eq 'W' ? 'selected' : ''}"/>>작성자</option>
								<option value="TC"
									<c:out value="${pageMaker.cri.type eq 'TC' ? 'selected' : ''}"/>>제목 or 내용</option>
								<option value="TW"
									<c:out value="${pageMaker.cri.type eq 'TW' ? 'selected' : ''}"/>>제목 or 작성자</option>
								<option value="TWC"
									<c:out value="${pageMaker.cri.type eq 'TWC' ? 'selected' : ''}"/>>제목 or 내용 or 작성자</option>
							</select>
							<input type='text' name='keyword' 
								value='<c:out value="${pageMaker.cri.keyword}"/>'/>
							<input type='hidden' name='pageStart' value = '${pageMaker.cri.pageStart }' />
							<input type='hidden' name='perPageNum' value = '${pageMaker.cri.perPageNum }' />
							<button class='btn btn-default'>Search</button>
						</form>
					</div>
				</div>

				<!-- jsp 페이지 번호 출력 -->
				<div class='pull-right'>

					<ul class="pagination">
						
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous">
								<a href="${pageMaker.startPage - 1 }">Previous</a>
							</li>
						</c:if>
						
						<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage}">
							<li class="paginate_button" ${pageMaker.cri.pageStart == num ? "active" : "" }>
								<a href="${num }">${num}</a>
							</li>
						</c:forEach>
						
						<c:if test="${pageMaker.next }">
							<li class="paginate_button next">
								<a href="${pageMaker.endPage+1 }">Next</a>
							</li>
						</c:if>
						
					</ul>
					
					<!-- a 태그가 원래의 동작을 못하도록 함 : p311 -->
					<form id='actionForm' action="/board/list" method='get'>
						<input type='hidden' name='pageStart' value = '${pageMaker.cri.pageStart }'>
						<input type='hidden' name='perPageNum' value = '${pageMaker.cri.perPageNum }'>
						<input type='hidden' name='type' value='<c:out value="${pageMaker.cri.type}"/>'>
						<input type='hidden' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>'>
					</form>
					
				</div>
				<!-- end paging -->
			</div>
			<!-- /.panel-body -->
			<!-- p247 모달창 보여주기 start-->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelleby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Modal title</h4>	
						</div>
						<div class="modal-body">처리가 완료되었습니다...</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-defaul" data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary">Save changes</button>
						</div>
					</div>
				</div>
			</div>
			<!-- p247 모달창 보여주기 end-->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>	
<!-- /.row -->

	<!-- p246 redirect 처리 부분-->
	<!-- p248  모달창 보여주기-->
	<!-- p250 list.jsp에 등록 버튼 추가 -->
	<script type="text/javascript">
		$(document).ready(function(){
			
			var result = '<c:out value="${result}" />';
			
			checkModal(result);
			
			// p257 window.history 객체 조작관련 추가
			history.replaceState({},null,null);
			
			function checkModal(result){
				
				// p257 window.history 객체 조작관련 추가
				if(result === '' || history.state){
				// if(result === ''){
					return;
				}
				
				if(parseInt(result) > 0){
					$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다 ")
				} 
				
				$("#myModal").modal("show");
			}
						
			$("#regBtn").on("click", function(){
			
				self.location = "/board/register";
			});
			
			// p312 : a 태그가 원래의 동작을 못하도록 함 
			var actionForm = $("#actionForm");
			
			$(".paginate_button a").on("click", function(e){
				
				e.preventDefault(); // a 태그를 클릭해도 페이지 이동이 없도록 함
				
				console.log('click');
				
				actionForm.find("input[name='pageStart']").val($(this).attr("href"));
				actionForm.submit();
				
			});
			
			// p315 : 목록조회후 첫페이지로 가는 문제 해결 이벤트
			$(".move").on("click", function(e){
			
				e.preventDefault();
				actionForm.append("<input type='hidden' name='bno' value='" +  $(this).attr("href") +"'>");
				actionForm.attr("action", "/board/get");
				actionForm.submit();
				
			});
			
			// p342
			var searchForm = $("#searchForm");
			
			$("#searchForm button").on("click", function(e){
			
				if(!searchForm.find("option:selected").val()){
					alert("검색 종류를 선택하세요")
					return false;
				}
				
				if(!searchForm.find("input[name='keyword']").val()){
					alert("키워드를 입력하세요")
					return false;
				}
				
				searchForm.find("input[name='pageStart']").val("1");
				e.preventDefault();
				
				searchForm.submit();
				
			});
			
			
			
			
		});
	</script>

	<%@include file="../includes/footer.jsp"%>