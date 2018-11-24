<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5>ex01</h5>
<pre>
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {

		log.info("" + dto);

		return "sample/ex01";
	}
</pre>
<h6>위와 같이 SampleDTO를 파라미터로 사용할 경우,자동으로 setter 메소드가 동작함 </h6>
<pre>
INFO : kr.pmadvisor.pms.controller.SampleController1 - SampleDTO(name=David, age=50)
</pre>
</body>
</html>