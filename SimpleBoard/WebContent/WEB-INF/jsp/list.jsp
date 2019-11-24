<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.doheum.sb.vo.*" %>    
<%
    	List<BoardVO> data = (List<BoardVO>)request.getAttribute("data");
    	int totalPagingCnt = (int)request.getAttribute("totalPagingCnt");
    	
    	String p = request.getParameter("page");
    	if(p == null) {
    		p = "1";
    	}
    %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style>	
	#contentTitle {
		background-color: black;
		color: red;
	}	
	
	.center {
		text-align: center;
	}
	
	a {
		text-decoration: none;
		color: black;
	}
	
	.aSelected {
		color: red !important;
		font-weight: bold;		
	}
	
	table {
		border-collapse: collapse;
		width: 100%;
		border: 1px solid black;
	}
	
	table td, th {
		border: 1px solid black;
	}
	
	.content {
		cursor: pointer;
	}
	
	table tr.content:hover {
		background-color: #CAD3C8;
	}
	
</style>
</head>
<body>
	<div>
		${loginUser.nm}님 환영합니다.
		<a href="logout">로그아웃</a>
	</div>
	<div id="contentTitle">보드 리스트</div>
	<div>
		<a href="write?p=<%=p%>">
			<button>글쓰기</button>
		</a>
	</div>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<%
			if(data != null) {
		%>
			<%
				for(BoardVO vo : data) {
			%>
				<tr class="content" onclick="goDetail(<%=vo.getI_board()%>, <%=p %>)">
					<td><%=vo.getI_board() %></td>
					<td><%=vo.getTitle() %></td>
					<td><%=vo.getNm() %></td>
					<td><%=vo.getRegDateTime() %></td>
					<td class="center"><%=vo.getCnt() %></td>
				</tr>				
			<% } %>
		<% } %>
	</table>
	<div>
		<% 		
			for(int i=1; i<=totalPagingCnt; i++) { 
				String cls = "";
				
				if(i == Integer.parseInt(p)) {
					cls = "aSelected";
				}
		%>
			<span >
				<a class="<%=cls %>" href="list?page=<%=i %>"><%=i %></a>
			</span>&nbsp;&nbsp;
		<% } %>
	</div>
	<script>
		function goDetail(i_board, page) {
			location.href='detail?i_board=' + i_board + '&p=' + page 
		}
	</script>
</body>
</html>












