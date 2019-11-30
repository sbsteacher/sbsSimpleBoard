<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<style>
html, body {
	padding:0;
	margin:0;
	width: 100%;
	height: 100%;
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    background-color: #333;
}
ul:after{
    content:'';
    display: block;
    clear:both;
}
li {
    float: left;
}
li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}
li a:hover:not(.active) {
    background-color: #111;
}
.active {
    background-color: #4CAF50;
}

#container {
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: column;
}

section {
	flex-grow: 1;
	padding: 20px;
}

footer {
	background-color: #333;
	color: white;
	text-align: center;
	height: 30px;
}

.mt-30 {
	margin-top: 30px;
}

table {
	width: 70%;
	border-collapse: collapse;
	border: 1px solid #000;
}

th, td {
	border: 1px solid #000;
	padding: 10px;
}

</style>
</head>
<body>
	<div id="container">
		<header>
			<nav>				
				  <ul>
				      <li><a class="active" href="list?page=${p}">Home</a></li>
				      <li><a href="write?grp=0&seq=0&floor=0">글쓰기</a></li>
				      <li><a href="#contact">Contact</a></li>
				      <li><a href="logout">Logout</a></li>
				  </ul>
				  <div>${loginUser.nm}님 환영합니다.</div>
			</nav>
		</header>
		<section>
			<jsp:include page="${target}.jsp"></jsp:include>
		</section>
		<footer>
			CopyRight 대구시 중구 코리아IT아카데미
		</footer>
	</div>
</body>
</html>