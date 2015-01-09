<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Groups mega page</title>
</head>

<body>
<p>Зарезервоване місце для груп...</p>
	<c:forEach var="group" items="${groups}">
		<c:out value="${group.groupName}" />
	</c:forEach>
</body>

</html>