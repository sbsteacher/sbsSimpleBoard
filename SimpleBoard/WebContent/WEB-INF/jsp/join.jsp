<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.doheum.sb.vo.*" %>    
<%
	String msg = (String)request.getAttribute("msg");
	UserVO vo = (UserVO)request.getAttribute("vo");
	
	if(vo == null) {
		vo = new UserVO();
		vo.setNm("");
		vo.setUid("");
		vo.setUpw("");
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script>
	function check() {
		if(frm.uid.value == '') {
			alert('아이디를 작성해 주세요')
			frm.uid.focus()
			return false
		} else if(frm.upw.value == '') {
			alert('비밀번호를 작성해 주세요')
			frm.upw.focus()
			return false
		} else if(frm.upw.value != frm.reupw.value) {
			alert('비밀번호를 확인해 주세요')
			frm.upw.focus()
			return false
		} else if(frm.nm.value == '') {
			alert('이름을 확인해 주세요')
			frm.nm.focus()
			return false
		}
	}
</script>
</head>
<body>
	<form id="frm" action="join" method="post" onsubmit="return check()">
		<div>아이디: <input type="text" name="uid" value="<%=vo.getUid()%>"></div>
		<div>비밀번호: <input type="password" name="upw" value="<%=vo.getUpw()%>"></div>
		<div>비밀번호 확인: <input type="password" name="reupw" value="<%=vo.getUpw()%>"></div>
		<div>이름: <input type="text" name="nm" value="<%=vo.getNm()%>"></div>
		<div><input type="submit" value="회원가입"></div>
	</form>	
	<% if(msg != null) { %>
		<div><%=msg %></div>
	<% } %>	
	<a href="login">로그인 돌아가기</a>
</body>
</html>





