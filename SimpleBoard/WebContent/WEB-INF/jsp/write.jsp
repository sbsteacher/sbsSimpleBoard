<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String grp = request.getParameter("grp");
	String seq = request.getParameter("seq");
	String floor = request.getParameter("floor");
	
	if(grp == null) grp = "0";
	if(seq == null) seq = "0";
	if(floor == null) floor = "0";
%>    
	<h1>글쓰기 화면</h1>
	<a href="list?page=${p}">
		<button>리스트로 돌아가기</button>
	</a>
	<form action="write" method="post" onsubmit="return check()">
		<input type="hidden1" name="grp" value="<%=grp %>">
		<input type="hidden1" name="seq" value="<%=seq %>">
		<input type="hidden1" name="floor" value="<%=floor %>">
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







