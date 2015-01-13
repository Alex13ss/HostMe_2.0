<%@ page language="java" contentType="text/html; charset=Utf-8"
         pageEncoding="Utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Create route</title>
    <meta http-equiv="Content-Type" content="text/html; charset=Utf-8">

    <style type="text/css">
        #map-canvas { height: 40%; margin: 0; padding: 0;}
    </style>

    <link href="<c:url value="resources/css/bootstrap.css"/>"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="resources/css/dataTables.bootstrap.css"/>"
          rel="stylesheet" type="text/css" />
    <link href="<c:url value="resources/css/basicMap.css"/>"
          rel="stylesheet" type="text/css" />

    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCiYncr79qu9wVjrwaSBHHTKMb3Dbo3Eo">
    </script>

    <script type="text/javascript">
        var directionsDisplay;
        var directionsService = new google.maps.DirectionsService();
        var map;

        function initialize() {
            directionsDisplay = new google.maps.DirectionsRenderer();
            var chicago = new google.maps.LatLng(41.850033, -87.6500523);
            var mapOptions = {
                center: chicago,
                zoom: 8
            };
            var map = new google.maps.Map(document.getElementById('map-canvas'),
                    mapOptions);
            directionsDisplay.setMap(map);
        }

        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>

    <div id="map-canvas" ></div>

    <section class="content">
        <div class="box box-primary">
            <div class="container-fluid " style="margin-left: 1.5em">
                <form:form method="post" action="createRoute"
                       modelAttribute="route" id="routeCreationForm"
                       enctype="multipart/form-data">
                    <div class="row col-lg-4" style="margin-top:1.5em; margin-bottom: 1.5em" >
                        <div class="row">
                        <spring:message code="routes.createRouteName"/>
                        <form:input path="name" type="text"
                            class="form-control" id="name"
                            name="name"
                            placeholder="What's your tour name?" />
                        </div>
                        <div class="row">
                        <spring:message code="routes.createRouteDescription"/>
                        <form:input path="description" type="text"
                            class="form-control" id="description"
                            name="description"
                            placeholder="Share your thouts!" />
                        </div>
                        <div class="row">
                            <spring:message code="routes.createRoutePlaceFrom"/>
                            <label>
                                <input class="form-control" type="text" name="from">
                            </label>
                        </div>
                        <div class="row">
                            <spring:message code="routes.createRoutePlaceTo"/>
                            <label>
                                <input class="form-control" type="text" name="to">
                            </label>
                        </div>
                        <div class="row">
                            <spring:message code="routes.createRouteWaypoint"/>
                            <label>
                                <input class="form-control" type="text" name="waypoint">
                            </label>
                        </div>
                        <button type="submit" class="btn btn-primary">
                            Create route
                        </button>
                    </div>
                </form:form>
            </div>
        </div>
    </section>
</body>
</html>
