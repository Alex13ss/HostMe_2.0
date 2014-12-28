<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/WEB-INF/bootstrap.css">
</head>
<body>

    <section class="content-header">
    <h1>
        <spring:message code="label.feedbacksFor" />
        &thinsp;<a
            href="<c:url value='hoster?hosterId=${hosting.owner.userId}' />">${hosting.owner.firstName}&thinsp;${hosting.owner.lastName}</a>
        <spring:message code="label.stayedAtFin" />
        <spring:message code="label.place" />
        - <a
            href="<c:url value="hosting?hostingId=${hosting.hostingId}&&userId=${hosting.owner.userId}" />">${hosting.address}</a>
    </h1>
    </section>

    <section class="content"> <!-- general form elements -->

    <div class="box box-primary">

        <div class="box-body">
            <div class="row">
                <div class="col-md-12" style="margin-top: 1em;">
                    <c:forEach items="${hosting.feedbacks}" var="fback"
                        varStatus="loop">
                        <div class="callout callout-info">
                            <form:form method="POST"
                                action="feedbacks-delete">
                                <input type="hidden" name="hostingId"
                                    id="hostingId"
                                    value="${hosting.hostingId}" />
                                <input type="hidden" name="feedbackId"
                                    id="feedbackId"
                                    value="${fback.feedbackId}" />
                                <c:if
                                    test="${loggedUser.login eq fback.author.login}">
                                    <div class="box-tools pull-right">
                                        <button class="btn btn-primary btn-sm"
                                            type="submit"><i class="fa fa-times"></i></button>
                                    </div>
                                </c:if>
                                <h4>
                                    <a
                                        href="<c:url value='hoster?hosterId=${fback.author.userId}' />">${fback.author.firstName}&nbsp;${fback.author.lastName}</a>
                                </h4>
                                <q style="color: gray"><i><c:out
                                            value="${fback.description}"></c:out></i></q>
                            </form:form>
                        </div>

                    </c:forEach>
                </div>
            </div>
        </div>
    </div>


    </section>
</body>
</html>