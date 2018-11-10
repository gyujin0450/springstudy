/**
 *	p399 : 게시물의 조회 페이지에서 사용하기 위해 작성함
 */
console.log("Replay Module.............");

var replyService = (function(){

	// p403 : 댓글 추가
	function add(reply, callback, error){
	
		console.log("add reply...............");
		
		$.ajax({
			type : 'post',
			url : '/replies/new',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}				
			},
			error : function(xhr, status, er){
				if(error){
					error(er)
				}
			}		
		})
	}
	
	// p406 : 뎃글 조회
	function getList(param, callback, error){

		var bno  = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/replies/pages/" + bno + "/" + page + ".json",
			function(data){
				if(callback){
					callback(data);
				}
			}).fail(function(xhr, status, err){
				if(error){
					error();
				}
			});
	}
	
	
	// p408 : 댓글 삭제 
	function remove(rno, callback, error){
	
		$.ajax({
			type : 'delete',
			url : '/replies/'+ rno,
			success : function(deleteResult, status, xhr){
				if (callback){
					callback(deleteResult);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	
	// p410 : 댓글 수정
	function update(reply, callback, error){
		
		console.log("RNO : " + reply.rno);
		
		$.ajax({
			type : 'put',
			url : '/replies/' + reply.rno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
		
	}
	
	// p412 : 댓글 조회 처리
	function get(rno, callback, error){
		
		$.get("/replies/" + rno + ".json", function(result){
			if(callback){
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	// p417 : 시간에 대한 처리
	function displayTime(timeValue){
		
		var today = new Date();
		
		var gap = today.getTime() - timeValue;
		
		var dateObj = new Date(timeValue);
		var str = "";
		
		if(gap < (1000*60*60*24)){
			
			// 1일 이내이면 시:분:초
			var hh = dateObj.getHours();
			var mi = dateObj.getMinutes();
			var ss = dateObj.getSeconds();
			
			return [ (hh > 9 ? '' : '0') + hh,':',
					 (mi > 9 ? '' : '0') + mi,':',
					 (ss > 9 ? '' : '0') + ss ].join('');
						
		}else{
	
			// 1일 이상이면 년/월/일
			var yy = dateObj.getFullYear();
			var mm = dateObj.getMonth()+1;
			var dd = dateObj.getDate();
			
			return [ yy, '/' , 
					(mm > 9 ? '':'0') + mm, '/', 
					(dd > 9 ? '':'0') + dd].join('');
			
		}
	}
	
	return {
		add:add,
		get : get,
		getList : getList,
		remove : remove,
		update : update,
		displayTime : displayTime
	};
	
})();
