<%@ page language="java" contentType="text/html; charset=Utf-8"
         pageEncoding="Utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Create route</title>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">

<style type="text/css">
    #map-canvas { height: 40%; margin: 0; padding: 0}
</style>
<link href="<c:url value="/resources/css/routes/dragndrop.css"/>" rel="stylesheet">

<script src="<c:url value="/resources/js/jquery.validate.js"/>"></script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCiYncr79qu9wVjrwaSBHHTKMb3Dbo3Eo"></script>
<script src="<c:url value="/resources/js/routes/maps.js"/>"></script>
<script src="<c:url value="/resources/js/routes/createRoute.js"/>"></script>
<script src="<c:url value="/resources/js/routes/valCreateRoute.js"/>"></script>

<div class="simple-map" id="map-canvas" ></div>

<section class="content">
    <div class="box box-primary">
        <div class="container-fluid " style="margin-left: 1.5em">
            <div class="row col-lg-4" style="margin-top:1.5em; margin-bottom: 1.5em" >
                <form:form method="post" action="createRoute"
                   modelAttribute="route" id="routeCreationForm"
                   enctype="multipart/form-data">
                    <div class="row">
                        <spring:message code="routes.createRouteName" />
                        <form:input path="name" id="name"
                            class="form-control" type="text"
                            name="name"
                            placeholder="What's your tour name?" />
                    </div>
                    <div class="row">
                        <spring:message code="routes.createRouteDescription" />
                        <form:input path="description" id="description"
                            class="form-control" type="text"
                            name="description"
                            placeholder="Share your thouts!" />
                    </div>
                    <div class="row">
                        <spring:message code="routes.createRoutePlaceFrom" />
                        <form:input path="beginPoint" id="beginPoint"
                            class="form-control" type="text"
                            name="beginPoint"
                            placeholder="Where do you wanna start?" />
                    </div>
                    <div class="row">
                        <spring:message code="routes.createRoutePlaceTo" />
                        <form:input path="endPoint" id="endPoint"
                            class="form-control" type="text"
                            name="endPoint"
                            placeholder="Place to end your trip!" />
                    </div>
                    <button class="btn btn-primary">
                        <spring:message code="routes.createRoute" />
                    </button>
                </form:form>
                <button id="googleCalculator" class="btn">
                    <spring:message code="routes.showRoute"/>
                </button>
            </div>
            <div class="col-lg-4" id="droppablePlace">
                <p>Drop routes here!</p>
            </div>
            <div class="col-lg-4">
                <table class="table table-bordered">
                    <c:forEach var="place" items="${places}">
                        <td>
                            <div class="drag" id="droppable">${place.address}</div>
                        </td>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</section>

