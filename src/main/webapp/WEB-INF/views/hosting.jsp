<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
    href="resources/css/daterangepicker-bs3.css">

<link rel="stylesheet" type="text/css"
    href="resources/css/bootstrap3-wysihtml5.css">

<script type="text/javascript" src="resources/js/daterangepicker.js"></script>

<script type="text/javascript"
    src="resources/js/bootstrap3-wysihtml5.all.min.js"></script>

<script type="text/javascript" src="resources/js/hosting.js"></script>
</head>

<body class="wysihtml5-supported">

    <section class="content-header">
        <h1>Hostel information</h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i>
                    Home</a></li>
            <li class="active">Dashboard</li>
        </ol>
    </section>

    <section class="content">
        <div>
            <!-- general form elements -->
            <div class="box box-primary">

                <div class="box-body">

                    <div class="row">
                        <div class="col-md-12">
                            <img src="resources/images/hostel1sm.jpg"
                                style="margin-top: 1em; margin-bottom: 1em;"></img>
                            <img src="resources/images/hostel2sm.jpg"
                                style="margin: 1em 0em 1em 2em;"></img>
                        </div>

                        <div class="col-md-8">

                            <div class="row">
                                <div class="col-md-3">
                                    <h4>Address:</h4>
                                </div>

                                <div class="col-md-8">
                                    <h4>${hosting.country},
                                        ${hosting.city},
                                        ${hosting.address}</h4>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-3">Hoster:</div>

                                <div class="col-md-8">
                                    <a
                                        href="<c:url value='hoster?hosterId=${user.userId}' />"><c:out
                                            value="${user.firstName}"></c:out>
                                        ${user.lastName}</a>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-3">Languages:</div>
                                <div class="col-md-9">
                                    <c:forEach items="${user.languages}"
                                        var="languages" varStatus="loop">


                                        <c:out
                                            value="${languages.language}">
                                        </c:out>
                                        <c:if test="${!loop.last}">, </c:if>

                                    </c:forEach>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-3">Number of
                                    guests:</div>

                                <div class="col-md-8">${hosting.minNumberOfGuests }-${hosting.maxNumberOfGuests }</div>
                            </div>

                            <div class="row">
                                <div class="col-md-3">Guests
                                    allowed:</div>
                                <div class="col-md-8">
                                    <c:choose>
                                        <c:when
                                            test="${hosting.gender == 'MALE'}">Men</c:when>
                                        <c:when
                                            test="${hosting.gender == 'FEMALE'}">Women</c:when>
                                        <c:when
                                            test="${hosting.gender == 'UNSPECIFIED'}">Men, women</c:when>
                                    </c:choose>
                                    <c:if test="${hosting.children}">, children</c:if>
                                    <c:if test="${hosting.family}">, family</c:if>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-3">Smoking:</div>
                                <div class="col-md-8">
                                    <c:choose>
                                        <c:when
                                            test="${hosting.smoking == '0'}">Not allowed</c:when>
                                        <c:otherwise>Allowed</c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="row" style="padding-bottom: 5px">
                                <div class="col-md-3">E-mail:</div>
                                <div class="col-md-9">
                                    <a href="mailto:${user.email}">${user.email}</a>
                                </div>
                            </div>

                        </div>

                    </div>

                </div>
                <!-- /.box-body -->

                <div class="box-footer">





                    <div class="row">
                        <form:form action="request" method="post"
                            modelAttribute="request"
                            id="send_request_form">
                            <div class="col-md-12">

                                <!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="panel box box-info">
                                            <div class="box-header">
                                                <h4 class="box-title">
                                                    <a
                                                        data-toggle="collapse"
                                                        data-parent="#accordion"
                                                        href="#collapseOne"
                                                        class="collapsed">
                                                        Select your
                                                        travel dates</a>
                                                </h4>
                                            </div>
                                        </div>

                                        <div id="collapseOne"
                                            class="panel-collapse collapse"
                                            style="height: 0px;">
                                            <div class="box-body">
                                                <div class="form-group">
                                                    <label>Date
                                                        and time range:</label>
                                                    <div
                                                        class="input-group">
                                                        <div
                                                            class="input-group-addon">
                                                            <i
                                                                class="fa fa-clock-o"></i>
                                                        </div>

                                                        <input
                                                            name="timeRange"
                                                            type="text"
                                                            class="form-control pull-right"
                                                            id="reservationtime" />
                                                        <form:input
                                                            path="endDate"
                                                            type="hidden"
                                                            id="endDate" />
                                                        <form:input
                                                            path="beginDate"
                                                            type="hidden"
                                                            id="beginDate" />
                                                        <form:input
                                                            path="hosting"
                                                            value="${hosting.hostingId}"
                                                            type="hidden"
                                                            id="hosting" />
                                                    </div>
                                                    <!-- /.input group -->
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="col-md-6">
                                        <div
                                            class="panel box box-warning">
                                            <div class="box-header">
                                                <h4 class="box-title">
                                                    <a
                                                        data-toggle="collapse"
                                                        data-parent="#accordion"
                                                        href="#collapseTwo"
                                                        class="collapsed">
                                                        A note to the
                                                        hoster</a>
                                                </h4>
                                            </div>


                                            <div id="collapseTwo"
                                                class="panel-collapse collapse"
                                                style="height: 0px;">
                                                <div class="form-group">
                                                    <form:textarea
                                                        path="notes"
                                                        class="textarea form-control"
                                                        placeholder="Place some text here" />
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                </div>


                                <div>
                                    <button onfocus="formDate()"
                                        class="btn btn-primary">Send
                                        request</button>
                                </div>
                            </div>

                        </form:form>
                    </div>


                </div>

            </div>
        </div>


        <div class="container">

            <div class="middle-side col-md-9">



                <div class="row">
                    <div class="col-md-3">
                        <h4 style="margin-top: 18px;">
                            <b>Check availability</b>
                        </h4>
                    </div>
                    <div class="col-md-9">
                        <h4>
                            From <input type="date" name="availability">
                            To <input type="date" name="availability">
                            <button id="avail" type="submit"
                                class="btn btn-default"
                                style="margin-left: 10px; margin-bottom: 3px;">Check</button>
                        </h4>
                    </div>
                </div>
            </div>

        </div>
    </section>
</body>
</html>