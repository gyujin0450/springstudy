<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h5>컨트롤 처리 내용</h5>
<pre>

	// 컨트롤 처리 내용
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {

		log.info("dto: " + dto);
		log.info("page: " + page);

		return "/sample/ex04";
	}
	
	// 화면(jsp)에 컨트롤에서 선언된 파라미터 dto, page를 불러오기
	
	\${dto.name }       ( X ) ** Setter 클래스를 변수명으로 사용하기 때문인듯
	\${sampleDTO }
	\${sampleDTO.name }
	\${page }
	
</pre>

<hr>
<h4>dto : ${dto.name }<br/>
SampleDTO : ${sampleDTO}<br/>
name : ${sampleDTO.name }<br/>
Page : ${page }</h4>

</body>
</html>