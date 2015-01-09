<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<p>Conversation:</p>${conversation.name}
	<p>Created At:</p>${conversation.createdAt}
	<p>Created By User:</p>${conversation.ownerUser.login}
	<p>Moderating By Users:</p>
	<ul>
		<c:forEach var="moderator" items="${conversation.moderators}">
			<li>${moderator.login}</li>
		</c:forEach>
	</ul>
	<ol>
		<c:forEach var="post" items="${conversation.posts}">
			<div id="posts">
				<span>${post.author.login} says:</span>${post.content}
				<p>At: {post.postedAt}</p>
			</div>
		</c:forEach>
	</ol>
</body>
</html>