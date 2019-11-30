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
	<div id="contentTitle">보드 리스트</div>	
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>좋아요</th>
		</tr>
		<%
			if(data != null) {
		%>
			<%
				for(BoardVO vo : data) {
			%>
				<tr class="content" onclick="goDetail(<%=vo.getI_board()%>, <%=p %>)">
					<td><%=vo.getI_board() %></td>
					<td>
						<% if(vo.getFloor() > 0) { %>
							<% for(int i=0; i<vo.getFloor(); i++)  {%>
								&nbsp;&nbsp;
							<% } %>
							┖
						<% } %>
						<%=vo.getTitle() %>
					</td>
					<td><%=vo.getNm() %></td>
					<td><%=vo.getRegDateTime() %></td>
					<td class="center"><%=vo.getCnt() %></td>
					<td class="center"><%=vo.getFavorite() %></td>
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