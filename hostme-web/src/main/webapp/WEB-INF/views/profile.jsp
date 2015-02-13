<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html style="min-height: 682px;">
<head>
<link rel="stylesheet" type="text/css" href="resources/css/AdminLTE.css">
</head>
<body >

    <section class="content-header">
        <h1>
            <spring:message code="label.userProfile" />
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">

        <div>
            <div class="box box-primary">

                <div class="box-body">

                    <div class="row">
                        <div class="col-md-3">
                            <div class="row col-md-12"
                                style="padding-bottom: 5px">
                                <h4>
                                    <strong>${user.firstName}&nbsp;${user.lastName}</strong>
                                </h4>
										<c:forEach var="image" items="${user.images}">
											<a href="${image_url}${image.link}" data-lightbox="images">
											<img src="${image_url}${image.link}"/></a>
										</c:forEach>
                            </div>

                            <div class="row col-md-12">
                                <a href="<c:url value='edit-profile' />"
                                    class="btn btn-default btn-sm"
                                    style="width: 200px;"> <spring:message
                                        code="label.editProfile" />
                                </a>
                            </div>
                        </div>

                        <div class="col-md-4" style="padding-left: 0em;">
                            <div class="panel box box-warning col-md-12"
                                style="margin-top: 3em; padding-left: 0.3em;">
                                <div class="box-header">
                                    <h4 class="box-title"
                                        style="padding-top: 0.8em; padding-bottom: 0em;">
                                        <spring:message
                                            code="label.generalInformation" />
                                        :
                                    </h4>
                                </div>

                                <div id="collapseOne"
                                    class="panel-collapse in"
                                    style="height: auto;">
                                    <div class="box-body"
                                        style="padding-bottom: 0.5em; padding-left: 1.5em;">
                                        <div class="row"
                                            style="padding-top: 0.3em; padding-bottom: 0.3em">
                                            <div class="col-md-4" style="padding-right: 0em;">
                                                <spring:message
                                                    code="label.loginNoun" />
                                                :
                                            </div>
                                            <div
                                                class="col-md-8 selected">${user.login}</div>
                                        </div>

                                        <div class="row"
                                            style="padding-bottom: 0.3em">
                                            <div class="col-md-4" style="padding-right: 0em;">
                                                <spring:message
                                                    code="label.gender" />
                                                :
                                            </div>
                                            <div
                                                class="col-md-8 selected">
                                                <c:out
                                                    value="${user.gender == 'FEMALE' ? 'female': 'male'}" />
                                            </div>
                                        </div>

                                        <div class="row"
                                            style="padding-bottom: 0.3em">
                                            <div class="col-md-4" style="padding-right: 0em;">
                                                <spring:message
                                                    code="label.age" />
                                                :
                                            </div>
                                            <div
                                                class="col-md-8 selected">${age}</div>
                                        </div>

                                        <div class="row"
                                            style="padding-bottom: 0.3em">
                                            <div class="col-md-4" style="padding-right: 0em;">
                                                <spring:message
                                                    code="label.birthday" />
                                                :
                                            </div>
                                            <div
                                                class="col-md-8 selected">${birth}</div>
                                        </div>

                                        <div class="row"
                                            style="padding-bottom: 0.3em">
                                            <div class="col-md-4" style="padding-right: 0em;">
                                                <spring:message
                                                    code="label.languages" />
                                                :
                                            </div>
                                            <div
                                                class="col-md-8 selected">
                                                <c:forEach
                                                    items="${user.languages}"
                                                    var="languages"
                                                    varStatus="loop">

                                                    <c:out
                                                        value="${languages}">
                                                    </c:out>
                                                    <c:if
                                                        test="${!loop.last}">, </c:if>

                                                </c:forEach>
                                            </div>
                                        </div>

                                        <div class="row"
                                            style="padding-bottom: 0.3em">
                                            <div class="col-md-4" style="padding-right: 0em;">
                                                <spring:message
                                                    code="label.email" />
                                                :
                                            </div>
                                            <div
                                                class="col-md-8 selected"
                                                style="display: inline-block; width: 180px; white-space: nowrap; overflow: hidden !important; text-overflow: ellipsis;">${user.email}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="row col-md-12"
                                style="padding-top: 3em;">

                                <div class="callout callout-danger">
                                    <h4>
                                        <spring:message
                                            code="label.personalDescription" />
                                        :
                                    </h4>

                                    <p>${user.description}</p>
                                </div>
                                <div class="callout callout-warning">
                                    <h4>
                                        <spring:message
                                            code="label.hobby" />
                                        :
                                    </h4>
                                    <p>${user.hobby}</p>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="col-md-12">
                            <c:if test="${!empty user.hostings}">
                                <div class="row">
                                    <div class="col-md-12" style="margin-top: 1em;">
                                        <h4>
                                            <spring:message
                                                    code="label.hostingAddresses" />
                                            :
                                        </h4>
                                    </div>
                                </div>
                                <table class="table table-bordered">
                                    <tr>
                                        <th><spring:message
                                                code="label.country" /></th>
                                        <th><spring:message
                                                code="label.region" /></th>
                                        <th><spring:message
                                                code="label.city" /></th>
                                        <th><spring:message
                                                code="label.address" /></th>
                                        <th><spring:message
                                                code="label.manage" /></th>
                                    </tr>
                                    <c:forEach var="hosting"
                                        items="${user.hostings}">
                                        <tr>
                                            <td>${hosting.country}</td>
                                            <td>${hosting.region}</td>
                                            <td>${hosting.city}</td>
                                            <td><a
                                                href=<c:url value="hosting?hostingId=${hosting.hostingId}&&userId=${user.userId}"/>>${hosting.address}</a></td>
                                            <td width="200px"><div class="tools">
                                                    <a href=<c:url value="hosting-editing?hostingId=${hosting.hostingId}&&userId=${user.userId}"/>
                                                        class="text-green">
                                                        <spring:message code="label.edit"/>
                                                        &nbsp;&nbsp;
                                                        <span class="fa fa-edit" style="margin-right: 1em"></span></a>
                                                    <a href=<c:url value="hosting-delete?hostingId=${hosting.hostingId}"/>
                                                        class="text-red"><spring:message
                                                code="label.delete" />&nbsp;&nbsp;<span
                                                        class="fa fa-trash-o"></span></a>
                                                </div></td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                            <c:if test="${!empty user.routes}">
                                <div class="row">
                                    <div class="col-md-12" style="margin-top: 1em;">
                                        <h4>
                                            <spring:message code="routes.userRoutes" />
                                        </h4>
                                    </div>
                                </div>
                                <table class="table table-bordered">
                                    <tr>
                                        <th><spring:message code="label.name" /></th>
                                        <th><spring:message code="label.description" /></th>
                                        <th><spring:message code="label.manage" /></th>
                                    </tr>
                                    <c:forEach var="route" items="${user.routes}">
                                        <tr>
                                            <td>
                                                <a href=<c:url value="/route?routeId=${route.id}"/>>
                                                    ${route.name}
                                                </a>
                                            </td>
                                            <td>${route.description}</td>
                                            <td width="200px">
                                                <a class="text-green" href=<c:url value="/routeEdit?routeId=${route.id}"/>>
                                                    <spring:message code="label.edit"/>
                                                    &nbsp;&nbsp;
                                                    <span class="fa fa-edit" style="margin-right: 1em"></span>
                                                </a>
                                                <a class="text-red" href=<c:url value="/routeDelete?routeId=${route.id}"/>>
                                                    <spring:message code="label.delete"/>
                                                    &nbsp;&nbsp;
                                                    <span class="fa fa-trash-o"></span>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                            <div style="margin-top: 20px;">

                                <a href="<c:url value='hosting-creation' />"
                                    class="btn btn-primary button">
                                    <spring:message code="label.addHosting" /></a>

                                <a href="<c:url value='/createRoute' />"
                                    class="btn btn-primary button">
                                    <spring:message code="routes.createRoute" /></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>