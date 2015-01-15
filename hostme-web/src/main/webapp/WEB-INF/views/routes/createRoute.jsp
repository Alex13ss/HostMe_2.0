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

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCiYncr79qu9wVjrwaSBHHTKMb3Dbo3Eo"></script>
<script src="<c:url value="/resources/js/routes/initMaps.js"/>"></script>
<script src="<c:url value="/resources/js/routes/calcRoute.js"/>"></script>

<div class="simple-map" id="map-canvas" ></div>

<section class="content">
    <div class="box box-primary">
        <div class="container-fluid " style="margin-left: 1.5em">
            <form:form method="post" action="createRoute"
                   modelAttribute="route" id="routeCreationForm"
                   enctype="multipart/form-data">
                <div class="row col-lg-4" style="margin-top:1.5em; margin-bottom: 1.5em" >
                    <div class="row">
                        <spring:message code="routes.createRouteName" />
                        <form:input path="name" type="text"
                            class="form-control" id="name"
                            name="name"
                            placeholder="What's your tour name?" />
                    </div>
                    <div class="row">
                        <spring:message code="routes.createRouteDescription" />
                        <form:input path="description" type="text"
                            class="form-control" id="description"
                            name="description"
                            placeholder="Share your thouts!" />
                    </div>
                    <div class="row">
                        <spring:message code="routes.createRoutePlaceFrom" />
                        <label>
                            <input id="from" type="text">
                        </label>
                    </div>
                    <div class="row">
                        <spring:message code="routes.createRoutePlaceTo" />
                        <label>
                            <input id="to" type="text">
                        </label>
                    </div>
                    <div class="row">
                        <spring:message code="routes.createRouteWaypoint" />
                        <label>
                            <input id="waypoint" type="text">
                        </label>
                    </div>
                    <button class="btn btn-primary">
                        <spring:message code="routes.createRoute" />
                    </button>
                </div>
            </form:form>
            <button id="googleCalculator" class="btn btn-primary">
                <spring:message code="routes.showRoute"/>
            </button>
        </div>
    </div>
</section>

