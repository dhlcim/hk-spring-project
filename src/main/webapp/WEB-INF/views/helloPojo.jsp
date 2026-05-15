<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>POJO 연동 테스트</title>
</head>
<body>
    <h1>스프링 POJO 연동 테스트</h1>
    <p>결과: ${message}</p>
    <p>코드 수정 없이 beans.xml만 바꾸면 아래 결과가 바뀝니다!</p>
    
    <div id="result"></div>

<script>
var preFix = "LED_";
document.getElementById("result").innerText= preFix+"${message}";
</script>
</body>
</html>