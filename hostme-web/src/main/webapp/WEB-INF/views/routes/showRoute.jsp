<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title><spring:message code="routes.routeInfo"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCiYncr79qu9wVjrwaSBHHTKMb3Dbo3Eo"></script>

<section class="content-header">
    <h1>
        <spring:message code="routes.routeInfo"/>
    </h1>
</section>

<section class="content">
    <div class="box box-primary">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-4">
                    <h4>
                        <spring:message code="label.name"/>
                        :
                        ${route.name}
                    </h4>
                    <spring:message code="label.rating"/>
                    :
                    ROUTE RATING
                    <spring:message code="label.priceCategory"/>
                    :
                    PRICE
                </div>
                <div class="col-lg-8">
                    <div class="panel box box-warning">
                        <div class="box-header">
                            <h4 class="box-title">
                                <a
                                    data-toggle="collapse"
                                    data-parent="#accordion"
                                    href="#collapseDescription"
                                    class="collapsed">
                                    <spring:message code="label.description" />
                                </a>
                            </h4>
                        </div>
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
            </div>
            <div class="row">
                <div class="col-lg-6 callout callout-warning">
                    <a href="
                        <c:url value="/place">
                            <c:param name="placeId" value="${origin.id}"/>
                        </c:url>">
                    ${origin.name}
                    </a>
                    <br>
                    ${origin.description}
                    <br>
                    PRICE
                </div>
                <div class="col-lg-6 callout callout-warning">
                    <a href="
                        <c:url value="/place">
                            <c:param name="placeId" value="${destination.id}"/>
                        </c:url>">
                    ${destination.name}
                    </a>
                    <br>
                    ${destination.description}
                    <br>
                    PRICE
                </div>
                <div class="row">
                    <div class="container-fluid">
                        <div class="row">
                            <c:if test="${!empty waypoints}">
                                <div class="col-lg-12">
                                    <div class="panel box box-warning">
                                        <div class="box-header">
                                            <h4 class="box-title">
                                                <a
                                                    data-toggle="collapse"
                                                    data-parent="#accordion"
                                                    href="#waypoints"
                                                    class="collapsed">
                                                    <spring:message code="routes.routeWaypoint" />
                                                </a>
                                            </h4>
                                        </div>
                                        <div
                                            id="waypoints"
                                            class="panel-collapse collapse">
                                            <div class="box-body">
                                                <div class="form-group">
                                                    <c:forEach var="waypoint"
                                                               items="${waypoints}">
                                                        ${waypoint.name}
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="panel box box-warning">
                                    <div class="box-header">
                                        <h4 class="box-title">
                                            <a
                                                data-toggle="collapse"
                                                data-parent="#accordion"
                                                href="#map"
                                                class="collapsed">
                                                <spring:message code="routes.map" />
                                            </a>
                                        </h4>
                                    </div>
                                    <div
                                        id="map"
                                        class="panel-collapse collapse">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>