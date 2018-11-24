<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5>ex02</h5>
<pre>
	http://localhost:8080/sample/ex02?irum=David&nai=50

	@GetMapping("/ex02")
	public String ex02(@RequestParam("irum") String name, @RequestParam("nai") int age) {

		log.info("name : " + name);
		log.info("age  : " + age);

		return "sample/ex02";
	}
</pre>
<h6>INFO : kr.pmadvisor.pms.controller.SampleController1 - name : David<br/>
INFO : kr.pmadvisor.pms.controller.SampleController1 - age  : 50</h6>
</body>
</html>