<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.doheum.sb.*" %>
<%
	BoardVo vo = (BoardVo)request.getAttribute("vo");
	String msg = (String)request.getAttribute("msg");
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
	<div>조회수: <%=vo.getCnt() %></div>
	<div><a href="del?i_board=<%=vo.getI_board() %>">삭제</a></div>
	<div><a href="mod?i_board=<%=vo.getI_board() %>">수정</a></div>
	
	<% if(msg != null) { %>
	<div><%=msg %></div>
	<% } %>
<% } %>
</body>
</html>







