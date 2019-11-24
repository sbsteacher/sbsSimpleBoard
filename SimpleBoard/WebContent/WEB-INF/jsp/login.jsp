<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.doheum.sb.*" %>    
<%
	String msg = (String)request.getAttribute("msg");
	UserVO vo = (UserVO)request.getAttribute("vo");
	

	if(vo == null) {
		vo = new UserVO();		
		vo.setUid("mic");
		vo.setUpw("1212");
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form action="login" method="post">
		<div>아이디: <input type="text" name="uid" value="<%=vo.getUid()%>"></div>
		<div>비밀번호: <input type="password" name="upw" value="<%=vo.getUpw()%>"></div>
		<div><input type="submit" value="로그인"></div>
	</form>
	<a href="join">회원가입</a>
	<% if(msg != null) { %>
		<div><%=msg %></div>
	<% } %>	
</body>
</html>