<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form action="login" method="post">
		<div>아이디: <input type="text" name="uid"></div>
		<div>비밀번호: <input type="password" name="upw"></div>
		<div><input type="submit" value="로그인"></div>
	</form>
	<a href="join">회원가입</a>
</body>
</html>