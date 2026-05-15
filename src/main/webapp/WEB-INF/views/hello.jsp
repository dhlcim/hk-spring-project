<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>메시지: ${message}</h1>
	<div id="result"></div>

<script>
var preFix = "LED_";
document.getElementById("result").innerText= preFix+"${message}";
</script>

</body>
</html>