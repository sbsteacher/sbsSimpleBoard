<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.doheum.sb.*" %>
<%@ page import="java.util.*" %>
<%
	BoardVo vo = (BoardVo)request.getAttribute("vo");
	String msg = (String)request.getAttribute("msg");
	List<CommentVo> cmtList = (List<CommentVo>) request.getAttribute("cmtList");
	String p = request.getParameter("p");
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
	<a href="list?page=<%=p %>">
		<button>리스트로 돌아가기</button>
	</a>
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
	<div>
		<!-- action을 생략하면 현재 주소창에 적혀있는 주소로 post를 날립니다. -->
		<form method="post" id="frm" onsubmit="return check()">
			<input type="hidden" name="i_comment" value="0">
			<input type="hidden" name="i_board" value="<%=vo.getI_board() %>">
			<div>			
				댓글 : <input type="text" name="comment">
				<input type="submit" value="댓글달기">
			</div>
		</form>
	</div>
	
	<% if(cmtList != null && cmtList.size() > 0) { %>
	<div>
		<table>
			<tr>
				<th>번호</th>
				<th>댓글</th>
				<th>등록일시</th>
				<th>삭제</th>
			</tr>			
			<% for(CommentVo cmtVo : cmtList) { %>
			<tr>
				<td><%=cmtVo.getI_comment() %></td>
				<td><%=cmtVo.getCmt() %></td>
				<td><%=cmtVo.getR_datetime() %></td>
				<td><button onclick="delCmt(<%=cmtVo.getI_comment() %>)">삭제</button></td>
			</tr>
			<% } %>
		</table>
	</div>	
	<% } %>
	
<% } %>
<script>
	//댓글삭제
	function delCmt(i_cmt) {
		frm.i_comment.value = i_cmt
		frm.submit()
	}
	
	function check() {	
		if(frm.comment.value == '') {
			alert('댓글 내용이 없습니다')
			frm.comment.focus()
			return false
		}
	}
</script>
</body>
</html>







