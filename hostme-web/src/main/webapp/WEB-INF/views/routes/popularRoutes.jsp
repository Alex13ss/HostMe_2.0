<%@ page language="java" contentType="text/html; charset=Utf-8"
         pageEncoding="Utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Routes</title>
    <meta http-equiv="Content-Type" content="text/html; charset=Utf-8">

    <style type="text/css">
        #map-canvas { height: 40%; margin: 0; padding: 0;}
    </style>

    <link href="${pageContext.request.contextPath}/WEB-INF/resources/css/bootstrap.css"
          rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/WEB-INF/resources/css/dataTables.bootstrap.css"
            rel="stylesheet" type="text/css" />
    <%--<link href="${pageContext.request.contextPath}/WEB-INF/resources/css/maps/basicMap.css"--%>
            <%--rel="stylesheet" type="text/css" />--%>

    <script src="${pageContext.request.contextPath}/WEB-INF/resources/js/jquery.dataTables.js"></script>
    <script src="${pageContext.request.contextPath}/WEB-INF/resources/js/fnAjaxReload.js"></script>
    <script src="${pageContext.request.contextPath}/WEB-INF/resources/js/dataTables.bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/WEB-INF/resources/js/countries3.js"></script>
    <script src="${pageContext.request.contextPath}/WEB-INF/resources/js/routes/createRoute.js"></script>
    <script src="${pageContext.request.contextPath}/WEB-INF/resources/js/routes/initMaps.js"></script>
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

        function calcRoute() {
            var start = "Lviv";
            var end = "Kiev";
            var request = {
                origin: start,
                destination: end,
                travelMode: google.maps.TravelMode.DRIVING
            };
            directionsService.route(request, function (result, status) {
                if (status == google.maps.DirectionsStatus.OK) {
                    directionsDisplay.setDirections(result);
                }
            });
        }

        google.maps.event.addDomListener(window, 'load', initialize);
    </script>

</head>
<body>
    <section class="content-header">
        <h1>
            Routes
        </h1>
    </section>
    <section class="content">
        <div class="box box-primary">
            <div class="container-fluid " style="margin-left: 1.5em">
                <div class="row " style="margin-top:1.5em; margin-bottom: 1.5em" >
                    <div class="form-group col-lg-4">
                        <div class="row">
                            <h4>Country</h4>
                        </div>
                        <div class="row">
                            <h4>City</h4>
                        </div>
                    </div>
                    <div class="col-lg-8" id="map-canvas" style=""></div>
                    <button onclick="calcRoute()">Click</button>
                </div>
                <div class="row">
                    <div class="col-lg-4">
                        <div class="row">
                            <h4>Popular routes</h4>
                        </div>
                        <div class="row">
                            <h4>Table with routes</h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
