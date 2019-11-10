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
<style>
	body {
		padding: 20px;
	}
	table {
		border-collapse: collapse;
		border: 1px solid #000;
	}
	
	th, td {
		border: 1px solid #000;
	}
</style>
</head>
<body>
<% if(vo == null) { %>
	<div>오류가 발생하였습니다.</div>	
<% } else { %>
	<table>
		<tr>
			<th>제목</th>
			<td colspan="3"><%=vo.getTitle() %></td>
		</tr>
		<tr>
			<td>날짜</td>
			<td><%=vo.getRegDateTime() %></td>
			<td>조회수</td>
			<td><%=vo.getCnt() %></td>
		</tr>
		<tr>
			<td colspan="4"><%=vo.getContent() %></td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="del?i_board=<%=vo.getI_board() %>">삭제</a>
			</td>
			<td colspan="2">
				<a href="mod?i_board=<%=vo.getI_board() %>">수정</a>
			</td>
		</tr>
		<% if(msg != null) { %>
		<tr>
			<td>메시지</td>
			<td colspan="3"><%=msg %></td>
		</tr>
		<% } %>
	</table>	
<% } %>
</body>
</html>







