<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {

		log.info("basic get only get............");
	}
</pre>
<h5>basicGet2(){...}의 return 타입이 void이기 때문에 "basicOnlyGet.jsp" 를 호출함</h5>
</body>
</html>