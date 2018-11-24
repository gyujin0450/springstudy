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
	@GetMapping("/ex02Test")
	public String ex02Test(String name, int age) {

		log.info("name : " + name);
		log.info("age  : " + age);

		return "sample/ex02Test";
	}
</pre>
<h5>파라메터로 사용되는 변수의 이름과 전달되는 파라미터 이름이 동일하면 @RequestParam 생략</h5>
</body>
</html>