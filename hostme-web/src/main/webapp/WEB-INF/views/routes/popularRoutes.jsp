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

    <script src="<c:url value="resources/js/jquery.dataTables.js"/>"></script>
    <script src="<c:url value="resources/js/fnAjaxReload.js"/>"></script>
    <script src="<c:url value="resources/js/dataTables.bootstrap.js"/>"></script>
    <script src="<c:url value="resources/js/countries3.js"/>"></script>
    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCiYncr79qu9wVjrwaSBHHTKMb3Dbo3Eo">
    </script>
    <script src="<c:url value="/resources/js/routes/maps.js"/>"></script>
</head>
<body>
    <section class="content-header">
        <h1>
            <spring:message code="routes.popularRoutes"/>
        </h1>
    </section>
    <section class="content">
        <div class="box box-primary">
            <div class="container-fluid ">
                <div class="col-lg-12" id="map-canvas" ></div>
            </div>
        </div>
    </section>
</body>
</html>
