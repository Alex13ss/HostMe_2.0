<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/conversations.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Here Will be group info
<p>Begin Conversations block</p>
<c:url var="conversationsUrl" value="/conversations">
		<c:param name="group_id" value="${group.id}" />
</c:url>
<%@ include file="conversations/latest.jsp" %>
<p>End Conversations block</p>
</body>
</html>