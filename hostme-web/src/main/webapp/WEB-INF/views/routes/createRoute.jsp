<%@ page language="java" contentType="text/html; charset=Utf-8"
         pageEncoding="Utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title>Create route</title>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">

<style type="text/css">
    #map-canvas { height: 40%; margin: 0; padding: 0}
</style>
<link href="<c:url value="/resources/css/routes/createRoute.css"/>" rel="stylesheet">

<script src="<c:url value="/resources/js/jquery.validate.js"/>"></script>
<script src="<c:url value="/resources/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="resources/js/fnAjaxReload.js"/>"></script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCiYncr79qu9wVjrwaSBHHTKMb3Dbo3Eo"></script>
<script src="<c:url value="/resources/js/routes/maps.js"/>"></script>
<script src="<c:url value="/resources/js/routes/createRoute.js"/>"></script>
<script src="<c:url value="/resources/js/routes/valCreateRoute.js"/>"></script>

<div class="simple-map" id="map-canvas" ></div>

<section class="content">
    <div class="box box-primary">
        <div class="container-fluid " style="margin-left: 1.5em">
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
                <div class="col-lg-4" id="userPlaces"></div>
                <div class="col-lg-4">
                    <div class="row dropArea" id="originPlaceDrop"></div>
                    <div class="row dropArea" id="waypointsPlacesDrop"></div>
                    <div class="row dropArea" id="destinationPlaceDrop"></div>
                </div>
                <div class="col-lg-4" id="allPlaces"></div>
                <button id="googleCalculator" class="row btn">
                    <spring:message code="routes.showRoute"/>
                </button>
            </form:form>
        </div>
    </div>
</section>

