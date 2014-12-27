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
    href="resources/css/datepicker.css">

<link rel="stylesheet" type="text/css"
    href="resources/css/bootstrap3-wysihtml5.css">

<link href="resources/css/lightbox.css" rel="stylesheet" />

<script type="text/javascript" src="resources/js/daterangepicker.js"></script>

<script type="text/javascript"
    src="resources/js/bootstrap-datepicker.js"></script>

<script type="text/javascript"
    src="resources/js/bootstrap3-wysihtml5.all.min.js"></script>

<script type="text/javascript" src="resources/js/hosting.js"></script>

<script src="resources/js/lightbox.min.js"></script>

</head>

<body class="wysihtml5-supported">

    <section class="content-header">
        <h1>
            <spring:message code="label.hostingInfo" />
        </h1>
    </section>

    <section class="content">
        <div>
            <!-- general form elements -->
            <div class="box box-primary">

                <div class="box-body">

                    <div class="row">
                        <div class="col-md-12">
                            <div class="col-md-8">
                                <c:forEach var="image"
                                    items="${hosting.images}">
                                    <a href="${image_url}${image.link}"
                                        data-lightbox="images"> <img
                                        src="${image_url}${image.link}"
                                        class="img-rounded"
                                        class="img-responsive"
                                        style="width: 200px; margin: 1em 0.5em 0em 0em;" /></a>
                                </c:forEach>
                                <div class="row">
                                    <div class="col-md-3">
                                        <h4>
                                            <spring:message
                                                code="label.address" />
                                            :
                                        </h4>
                                    </div>

                                    <div class="col-md-8">
                                        <h4>${hosting.country},
                                            ${hosting.city},
                                            ${hosting.address}</h4>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-3">
                                        <spring:message
                                            code="label.hoster" />
                                        :
                                    </div>

                                    <div class="col-md-8">
                                        <a
                                            href="<c:url value='hoster?hosterId=${hosting.owner.userId}' />">${hosting.owner.firstName}&thinsp;${hosting.owner.lastName}</a>
                                    </div>
                                </div>

                                <div id="UL" class="row">
                                    <div class="col-md-3">
                                        <spring:message
                                            code="label.languages" />
                                        :
                                    </div>
                                    <div class="col-md-9">
                                        <c:forEach
                                            items="${hosting.owner.languages}"
                                            var="languages"
                                            varStatus="loop">

                                            <c:out
                                                value="${languages.language}">
                                            </c:out>
                                            <c:if test="${!loop.last}">, </c:if>

                                        </c:forEach>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-3">
                                        <spring:message
                                            code="label.guestsNumber" />
                                        :
                                    </div>

                                    <div class="col-md-8">${hosting.minNumberOfGuests }-${hosting.maxNumberOfGuests }</div>
                                </div>

                                <div class="row">
                                    <div class="col-md-3">
                                        <spring:message
                                            code="label.guestsType" />
                                        :
                                    </div>
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
                                    <div class="col-md-3">
                                        <spring:message
                                            code="label.smoking" />
                                        :
                                    </div>
                                    <div class="col-md-8">
                                        <c:choose>
                                            <c:when
                                                test="${hosting.smoking == '0'}">Not allowed</c:when>
                                            <c:otherwise>Allowed</c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>

                                <div class="row"
                                    style="padding-bottom: 0.5em">
                                    <div class="col-md-3">
                                        <spring:message
                                            code="label.email" />
                                        :
                                    </div>
                                    <div class="col-md-9">
                                        <a
                                            href="mailto:${hosting.owner.email}">${hosting.owner.email}</a>
                                    </div>
                                </div>

                            </div>

                            <div class="col-md-4"
                                style="margin-top: 1em;">

                                <div style="padding-bottom: 0.5em" class="callout callout-warning">
                                    <input id="disabledDate"
                                        type="hidden"
                                        value="${disabledDate}" /> <input
                                        id="dateNew" type="hidden"
                                        value="${dateNew}" />
                                    <h4>
                                        <spring:message
                                            code="label.findGuestReviews" />
                                        <a
                                            href="<c:url value="feedbacks?hostingId=${hosting.hostingId}" />">
                                            <spring:message
                                                code="label.here" />
                                        </a>
                                    </h4>

                                </div>

                                <form:form action="feedbacks-send"
                                    method="post"
                                    modelAttribute="feedback"
                                    id="send_feedback_form">
                                    <div class="panel box box-warning">
                                        <div class="box-header">
                                            <h5 class="box-title">
                                                <a
                                                    data-toggle="collapse"
                                                    data-parent="#accordion"
                                                    href="#collapseFeedbacks"
                                                    class="collapsed">
                                                    <spring:message
                                                        code="label.stayedAt" />&thinsp;${hosting.owner.firstName}&thinsp;${hosting.owner.lastName}
                                                    <spring:message
                                                        code="label.stayedAtFin" />?
                                                </a>
                                            </h5>

                                        </div>
                                        <div style="margin-left: 0.7em">
                                            <spring:message
                                                code="label.sendUs" />
                                            <a data-toggle="collapse"
                                                data-parent="#accordion"
                                                href="#collapseFeedbacks">
                                                <spring:message
                                                    code="label.sendFeedback" />
                                            </a>!
                                        </div>
                                        <div class="box-body">
                                            <input type="hidden"
                                                name="hostingId"
                                                value="${hosting.hostingId}" />

                                            <div id="collapseFeedbacks"
                                                class="panel-collapse collapse"
                                                style="height: 0px;">
                                                <div class="form-group"
                                                    style="margin-bottom: 0">
                                                    <spring:message
                                                        code="label.stayOpinion"
                                                        var="varStayOption" />
                                                    <form:textarea
                                                        id="description"
                                                        path="description"
                                                        class=" form-control"
                                                        placeholder="${varStayOption}"
                                                        style="margin-bottom:1em"></form:textarea>
                                                    <button
                                                        class="btn btn-warning"                                                     <c:if test="${hosting.owner.userId eq loggedUser.userId}">
                                                    <c:out value="disabled='disabled'"/>
                                                    </c:if>>
                                                        <spring:message
                                                            code="label.sendFeedbackButton" />
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>

                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.box-body -->

                <div class="box-footer">

                    <div class="row">
                        <div class="col-md-12">
                            <form:form action="request" method="post"
                                modelAttribute="request"
                                id="send_request_form">
                                <div class="col-md-12">

                                    <!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->
                                    <div class="row">


                                        <div class="col-md-4">
                                            <div
                                                class="panel box box-info">
                                                <div class="box-header">
                                                    <h4
                                                        class="box-title">
                                                        <a
                                                            data-toggle="collapse"
                                                            data-parent="#accordion"
                                                            href="#collapseCalendar"
                                                            class="collapsed">
                                                            <spring:message
                                                                code="label.availability" />
                                                        </a>
                                                    </h4>
                                                </div>

                                                <div
                                                    id="collapseCalendar"
                                                    class="panel-collapse collapse"
                                                    style="height: 0px;">
                                                    <div
                                                        class="box-body">
                                                        <div
                                                            class="form-group">

                                                            <div
                                                                id="available">




                                                                <div></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>



                                        <div class="col-md-4">
                                            <div
                                                class="panel box box-warning">
                                                <div class="box-header">
                                                    <h4
                                                        class="box-title">
                                                        <a
                                                            data-toggle="collapse"
                                                            data-parent="#accordion"
                                                            href="#collapseOne"
                                                            class="collapsed">
                                                            <spring:message
                                                                code="label.selectTravelDates" />
                                                        </a>
                                                    </h4>
                                                </div>


                                                <div id="collapseOne"
                                                    class="panel-collapse collapse"
                                                    style="height: 0px;">
                                                    <div
                                                        class="box-body">
                                                        <div
                                                            class="form-group">
                                                            <label><spring:message
                                                                    code="label.selectDateTime" />:</label>
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
                                        </div>


                                        <div class="col-md-4">
                                            <div
                                                class="panel box box-success">
                                                <div class="box-header">
                                                    <h5
                                                        class="box-title">
                                                        <a
                                                            data-toggle="collapse"
                                                            data-parent="#accordion"
                                                            href="#collapseTwo"
                                                            class="collapsed">
                                                            <spring:message
                                                                code="label.writeNoteHoster" />
                                                        </a>
                                                    </h5>
                                                </div>


                                                <div id="collapseTwo"
                                                    class="panel-collapse collapse"
                                                    style="height: 0px;">
                                                    <div
                                                        class="form-group">
                                                        <form:textarea
                                                            path="notes"
                                                            class="textarea form-control"
                                                            placeholder="Place some text here" />
                                                    </div>
                                                </div>

                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-1">

                                            <button onfocus="formDate()"
                                                class="btn btn-primary">
                                                <spring:message
                                                    code="label.sendRequest" />
                                            </button>

                                        </div>

                                    </div>


                                </div>
                            </form:form>
                        </div>
                    </div>

                </div>


            </div>

        </div>
    </section>

    <script>
					$(".alert").alert();
					window.setTimeout(function() {
						$(".alert").alert('close');
					}, 5000);

					var dateDisabled = ${nonAvailableDatesJson};

					$(function() {
						$('#available div')
								.datepicker(
										{
											dateFormat : 'dd-mm-yyyy',
											beforeShowDay : function(date) {
												if ($.inArray(date
														.getFullYear()
														+ '-'
														+ (date.getMonth() + 1)
														+ '-' + date.getDate(),
														dateDisabled) !== -1) {
													return false;
												}
												return true;
											}
										});
					});
				</script>
</body>
</html>