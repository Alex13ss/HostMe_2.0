<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/conversations.css">
<!--title>Insert title here</title-->
</head>
<body>
	<%--page for viewing all conversations on group --%>
	<div id="group_conversations_block">
	
		<c:url var="conversationsUrl" value="/conversations" />

		<a class="conversations_header_link" href="<c:out value="${conversationsUrl}"/>">
			<div class="conversations_header">
				<spring:message code="conversations.title" />
				<span class="badge conversation_badge">${conversationsSize}</span> <span
					class="badge conversation_badge  conversation_create_label">
					<spring:message code="conversations.create" />
				</span>
			</div>
		</a>

		<div id="conversations_body">
			<c:forEach var="conversation" items="${conversations}">
				
				<c:url var="conversationUrl" value="/conversation">
					<c:param name="id" value="${conversation.id}" />
				</c:url>
				
				<a href="<c:out value="${conversationUrl}"/>">
					<div class="conversation">
						<div class="conversation_title">${conversation.name}</div>
						<div class="last_post">Останнє повідомлення від (юзер). (дата) о (час)</div>
					</div>
				</a>
			</c:forEach>
		</div>
		
	</div>
</body>
</html>