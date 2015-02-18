<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<title><spring:message code="routes.routeInfo"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCiYncr79qu9wVjrwaSBHHTKMb3Dbo3Eo"></script>
<script src="<c:url value="/resources/js/routes/maps.js"/>"></script>
<script src="<c:url value="/resources/js/routes/showRoute.js"/>"></script>

<link rel="stylesheet" type="text/css"
      href="<c:url value="/resources/css/maps/basicMap.css"/>"/>

<section class="content-header">
    <h1>
        <spring:message code="routes.routeInfo"/>
    </h1>
</section>

<section class="content">
    <div class="box box-primary">
        <div class="container-fluid">
            <div class="col-md-4">
                <h3>
                    <spring:message code="label.name"/>
                    :
                    ${route.name}
                </h3>
                <button id="likeRoute" class='btn btn-default btn-xs' style="margin-left: 5%">
                    <i class='fa fa-thumbs-o-up'></i>
                    ${route.rating}
                </button>
                <i class='fa fa-dollar' style="margin-left: 5%"></i>
                ${route.priceCategory.priceCategory}
                <i class='fa fa-suitcase' style="margin-left: 5%"></i>
                ${route.distance/1000} km
            </div>
            <div class="col-md-7" style="margin-top: 2%">
                <div class="panel box box-warning">
                    <a
                        data-toggle="collapse"
                        data-parent="#accordion"
                        href="#collapseDescription"
                        class="collapsed">
                        <div class="box-header">
                            <h4 class="box-title">
                                <spring:message code="label.description" />
                            </h4>
                        </div>
                    </a>
                    <div
                        id="collapseDescription"
                        class="panel-collapse collapse">
                        <div class="box-body">
                            <div class="form-group">
                                ${route.description}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${route.user == currentUser}">
                <a href="<c:url value='routeEdit?routeId=${route.id}'/>">
                    <button class="btn btn-default col-md-1 button" style="margin-top: 2%">
                        <i class="fa fa-fw fa-edit"></i>
                        <spring:message code="label.edit" />
                    </button>
                </a>
            </c:if>
            <div>
                <div class="col-md-5 callout callout-warning" style="margin-left:3%; height: 10%">
                    <div class="col-md-10">
                        <a href="
                            <c:url value="/place">
                                <c:param name="placeId" value="${origin.id}"/>
                            </c:url>">
                            ${origin.name}
                        </a>
                        <br>
                        <div id="originAddress">${origin.address}</div>
                        ${origin.priceCategory.priceCategory}
                        <br>
                    </div>
                    <div class="col-md-2">
                        <c:choose>
                            <c:when test="${!empty destination.image}">
                                <c:forEach var="image" begin="0" end="0" items="${destination.image}">
                                    <img src="${image_url}${image.link}" />
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value="resources/images/imgNotFound.jpg"/>" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="col-md-5 callout callout-warning" style="margin-left: 10%; height: 10%">
                    <div class="col-md-10">
                        <a href="
                            <c:url value="/place">
                                <c:param name="placeId" value="${destination.id}"/>
                            </c:url>">
                            ${destination.name}
                        </a>
                        <br>
                        <div id="originAddress">${destination.address}</div>
                        ${destination.priceCategory.priceCategory}
                        <br>
                    </div>
                    <div class="col-md-2">
                        <c:choose>
                            <c:when test="${!empty destination.image}">
                                <c:forEach var="image" begin="0" end="0" items="${destination.image}">
                                    <img src="${image_url}${image.link}" />
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value="resources/images/imgNotFound.jpg"/>" />
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <%--Waypoints--%>
            <c:if test="${!empty waypoints}">
                <div class="col-md-12">
                    <div class="panel box box-warning">
                        <a
                            data-toggle="collapse"
                            data-parent="#accordion"
                            href="#waypoints">
                            <div class="box-header">
                                <h4 class="box-title">
                                    <spring:message code="routes.routeWaypoint" />
                                </h4>
                            </div>
                        </a>
                        <div id="waypoints"
                            class="panel-collapse collapse">
                            <div class="box-body">
                                <table style="height: 10%">
                                    <tr style="position: relative;">
                                    <c:forEach var="waypoint"
                                               items="${waypoints}">
                                        <td class="box" style="padding: 1%; width: 5%; position: relative; ">
                                            <a href="
                                                <c:url value="/place">
                                                    <c:param name="placeId" value="${waypoint.id}"/>
                                                </c:url>"
                                                    style="position: absolute; top: 0">
                                                ${waypoint.name}
                                            </a>
                                            <br>
                                            <div class="waypointAddress" style="font-size: 0.85em">
                                                <i class="fa fa-building-o">
                                                    ${waypoint.city.city}
                                                </i>
                                                <br>
                                                <i class="fa fa-map-marker">
                                                    ${waypoint.address}
                                                </i>
                                            </div>
                                            <br>
                                            <div class="col-md-12" style="position: absolute; bottom: 0; left: 0;">
                                                <i class='fa fa-thumbs-o-up' style="height: 5%">
                                                    ${waypoint.rating}
                                                </i>
                                                <i class='fa fa-dollar' style="margin-left: 5%; height: 5%">
                                                    ${waypoint.priceCategory.priceCategory}
                                                </i>
                                            </div>
                                        </td>
                                    </c:forEach>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <%--Map--%>
            <div class="col-md-12">
                <div id="map-canvas"></div>
            </div>
        </div>
    </div>
</section>

<script>
    var route_rating = "${route.rating}";
    var route_id = "${route.id}"
</script>
