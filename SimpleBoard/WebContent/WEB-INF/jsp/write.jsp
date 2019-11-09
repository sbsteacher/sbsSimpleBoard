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
	<form action="write" method="post" onsubmit="return check()">
		<div>
			제목 : <input type="text" name="title" id="title"> 
		</div>
		<div>
			내용
			<textarea name="content" id="cont"></textarea>	
		</div>
		<input type="submit" value="글등록">	
	</form>
	<script>
		function check() {
			var title = document.getElementById('title')			
			var content = document.getElementById('cont')
			
			if(title.value == '') {
				alert('제목을 입력해 주세요!!!')
				title.focus()
				return false
			} else if(content.value.length == 0) {
				alert('내용을 입력해 주세요!!!')
				content.focus()
				return false
			}
			
			return true
		}
	</script>
</body>
</html>






