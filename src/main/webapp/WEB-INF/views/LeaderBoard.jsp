<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>LEADERBOARD!!</h1>
	<table border="1">
		<thead>
			<tr>
				<td>NAME</td>
				<td>WIN RATIO</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${leaderBoardData}" var='l'>
				<tr>
					<td>${l[0]}</td>
					<td>${l[2]}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>