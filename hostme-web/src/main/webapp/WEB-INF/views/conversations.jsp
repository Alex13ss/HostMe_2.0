<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"-->
<title>Insert title here</title>
</head>
<body>
<p>Тут буде 5 останніх обговорень:</p>
	<table>
		<thead>
			<th>Name</th>
		</thead>
		<c:forEach var="conversation" items="${conversations}">
			<tr>
				<c:url var="conversationUrl" value="/conversation">
					<c:param name="id" value="${conversation.id}" />
				</c:url>
				<td><a href="<c:out value="${conversationUrl}"/>">${conversation.name}</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>