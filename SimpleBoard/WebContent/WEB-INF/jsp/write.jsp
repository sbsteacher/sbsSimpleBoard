<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<h1>글쓰기 화면</h1>
	<form action="write" method="post">
		<div>
			제목 : <input type="text" name="title"> 
		</div>
		<div>
			내용
			<textarea name="content"></textarea>	
		</div>
		<input type="submit" value="글등록">	
	</form>
</body>
</html>





