<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리스트</title>
<link rel="stylesheet" href="/resource/common.css" />
<script src="/resource/common.js" defer="defer"></script>   <!-- 원래 script태그 맨 아래에 쓰는건데 defer쓰면 헤더쪽에 써도 맨 아래 쓰는 것과 같은 효과 -->
</head>
<body>
	<h1>게시물 리스트 페이지</h1>
	<header>
		<a href="/">로고</a>
		<ul>
			<li><a href="/">홈</a></li>
			<li><a href="/usr/article/list">리스트</a></li>
		</ul>
	</header>
	<hr/>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성날짜</th>
				<th>수정날짜</th>
				<th>작성자</th>
				<th>제목</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles}">
			<tr>
				<td>${article.id}</td>
				<td>${article.regDate}</td>         <%-- 시간 서브스트링으로 문자열 자르기 : ${article.regDate.substring(2, 16) } => 2번째부터 16번째까지 자르겠다. --%>
				<td>${article.updateDate}</td>
				<td>${article.memberId}</td>
				<td>
					<a href="../article/detail?id=${article.id}">${article.title }</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>