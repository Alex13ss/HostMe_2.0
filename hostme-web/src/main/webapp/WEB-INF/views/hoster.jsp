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
<body>

    <section class="content-header">
        <h1>
            <spring:message code="label.hosterInformation" />
        </h1>
    </section>

    <section class="content">
        <div>
            <!-- general form elements -->
            <div class="box box-primary">

                <div class="box-body">

                    <div class="row">
                        <div class="col-md-3">
                            <div class="row col-md-12"
                                style="padding-bottom: 5px">
                                <h4>
                                    <strong>${user.firstName}&nbsp;${user.lastName}</strong>
                                </h4>
                                <c:forEach var="image"
                                    items="${user.images}">
                                    <img src="${image_url}${image.link}"
                                        style="width: 200px; height: 200px" />
                                </c:forEach>
                            </div>
                        </div>

                        <div class="col-md-4" style="padding-left: 0em;">
                            <div class="panel box box-info col-md-12"
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
                                            <div class="col-md-4"
                                                style="padding-right: 0em;">
                                                <spring:message
                                                    code="label.loginNoun" />
                                                :
                                            </div>
                                            <div
                                                class="col-md-8 selected">${user.login}</div>
                                        </div>

                                        <div class="row"
                                            style="padding-bottom: 0.3em">
                                            <div class="col-md-4"
                                                style="padding-right: 0em;">
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
                                            <div class="col-md-4"
                                                style="padding-right: 0em;">
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
                                            <div class="col-md-4"
                                                style="padding-right: 0em;">
                                                <spring:message
                                                    code="label.email" />
                                                :
                                            </div>
                                            <div
                                                class="col-md-8 selected">${user.email}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-5">
                            <div class="row col-md-12"
                                style="padding-top: 3em;">

                                <div class="callout callout-info">
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

                    <div class="row" style="margin-top: 1.5em">
                        <h4 class="col-md-8">
                            <spring:message
                                code="label.hostingAddresses" />
                            :
                        </h4>
                    </div>


                    <div>
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
                            </tr>
                            <c:forEach var="hosting"
                                items="${user.hostings}">
                                <tr>
                                    <td>${hosting.country}</td>
                                    <td>${hosting.region}</td>
                                    <td>${hosting.city}</td>
                                    <td><a
                                        href=<c:url value="hosting?hostingId=${hosting.hostingId}"/>>${hosting.address}</a></td>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>

                </div>
                <!-- /.box-body -->

            </div>
        </div>
    </section>

</body>


<script src="../WEB-INF/resources/js/jquery-2.1.1.js"></script>
<script src="../WEB-INF/resources/js/bootstrap.js"></script>
<script type="text/javascript">
	$('#myModal').modal('show');
</script>

<script type="text/javascript">
	$("#createHosting").on("click", function() {
		window.location = "profile";
	});
</script>

<script src="../WEB-INF/resources/js/jquery-2.1.1.js"></script>
<script src="../WEB-INF/resources/js/bootstrap.js"></script>

</html>