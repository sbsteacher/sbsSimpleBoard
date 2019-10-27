<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.doheum.sb.*" %>
<%
	BoardVo vo = (BoardVo)request.getAttribute("vo");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>디테일</title>
</head>
<body>
<% if(vo == null) { %>
	<div>오류가 발생하였습니다.</div>
<% } else { %>
	<div>제목: <%=vo.getTitle() %></div>
	<div>날짜: <%=vo.getRegDateTime() %></div>
	<div>내용: <%=vo.getContent() %></div>
<% } %>
</body>
</html>







