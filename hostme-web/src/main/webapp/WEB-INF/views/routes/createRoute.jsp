<%@ page language="java" contentType="text/html; charset=Utf-8"
         pageEncoding="Utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title>Create route</title>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">

<link rel="stylesheet" type="text/css"
      href="<c:url value="/resources/css/maps/basicMap.css"/>"/>

<link href="<c:url value="/resources/css/routes/createRoute.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/jquery-ui-themes-1.11.2/themes/smoothness/jquery-ui.css"/>" rel="stylesheet">

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCiYncr79qu9wVjrwaSBHHTKMb3Dbo3Eo"></script>
<script src="<c:url value="/resources/js/routes/maps.js"/>"></script>
<script src="<c:url value="/resources/js/routes/createRoute.js"/>"></script>
<script src="<c:url value="/resources/js/routes/valCreateRoute.js"/>"></script>
<script src="<c:url value="/resources/js/jquery-ui-1.11.2/jquery-ui.js"/>"></script>

<div class="simple-map" id="map-canvas"></div>

<section class="content">
    <div class="box box-primary">
        <div class="container-fluid">
            <div id="savingStatus"></div>
            <spring:message code="routes.createRouteName" />
            <input id="name" class="form-control">
            <spring:message code="routes.createRouteDescription" />
            <input id="description" class="form-control">
            <div class="container-fluid">
                <div id="userTabs" class="col-sm-4">
                    <spring:message code="places.yourPlaces"/>
                    <select id="userPlaceNumber"></select>
                    <ul>
                        <li><a href="#userBookedPlaces">Booked</a></li>
                        <li><a href="#userPlaces">Your</a></li>
                    </ul>
                    <div id="userBookedPlaces"></div>
                    <div id="userPlaces"></div>
                    <div class="col-sm-6 btn btn-primary"><<</div>
                    <div class="col-sm-6 btn btn-primary">>></div>
                </div>
                <div class="col-sm-4">
                    <div id="originPlaceDrop" class="dropArea">
                        <div class="hint">Add Origin</div>
                    </div>
                    <div id="waypointsPlacesDrop" class="dropArea">
                        <div class="hint">Add waypoints</div>
                    </div>
                    <div id="destinationPlaceDrop" class="dropArea">
                        <div class="hint">Add destination</div>
                    </div>
                    <button id="createRoute" class="btn btn-primary" style="width: 100%">
                        <spring:message code="routes.createRoute"/>
                    </button>
                </div>
                <div class="col-sm-4">
                    <spring:message code="places.popularPlaces"/>
                    <select id="popularPlaceNumber"></select>
                    <div id="allPlaces"></div>
                    <div class="col-sm-6 btn btn-primary"><<</div>
                    <div class="col-sm-6 btn btn-primary">>></div>
                </div>
            </div>
        </div>
    </div>
</section>

