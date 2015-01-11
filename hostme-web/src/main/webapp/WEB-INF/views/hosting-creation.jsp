<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" type="text/css"
            href="resources/css/maps/basicMap.css">

    <script type="text/javascript"
            src="resources/js/countries3.js"></script>
    <script type="text/javascript"
            src="resources/js/jquery.MultiFile.js"></script>
    <script type="text/javascript"
            src="resources/js/jquery.validate.js"></script>
    <script type="text/javascript"
            src="resources/js/validation.js"></script>
    <script type="text/javascript"
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCCiYncr79qu9wVjrwaSBHHTKMb3Dbo3Eo">
    </script>

    <%--<style type="text/css">--%>
        <%--#map-canvas { height: 30%; width: 40%; margin: 0; padding: 0;}--%>
    <%--</style>--%>

</head>
<body>
    <section class="content-header">
    <h1>
        <spring:message code="label.addHosting" />
    </h1>
    </section>

    <section class="content">

    <div>
        <div class="box box-primary">
            <div class="box-body">
                <form:form method="post" action="hosting-creation"
                    onsubmit="validateForm(event)"
                    modelAttribute="hosting" id="hostingCreationForm"
                    enctype="multipart/form-data">
                    <div class="row" style="margin-top:1.5em">
                        <div class="form-group">
                            <label for="country"
                                class="col-lg-2 control-label"><h4>Country</h4>
                            </label>
                            <div class="col-lg-5">
                                <form:select class="form-control"
                                    path="country" id="country"
                                    name="country"
                                    onchange="printState('region',this.selectedIndex);" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="region"
                                class="col-lg-2 control-label"><h4>Region</h4>
                            </label>
                            <div class="col-lg-5">
                                <form:select class="form-control"
                                    path="region" id="region" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="city"
                                class="col-lg-2 control-label"><h4>City</h4>
                            </label>
                            <div class="col-lg-5">
                                <form:input path="city" type="text"
                                    class="form-control" id="city"
                                    name="city"
                                    placeholder="City (e.g. Lviv)" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="address"
                                class="col-lg-2 control-label"><h4>Address</h4>
                            </label>
                            <div class="col-lg-5">
                                <form:input path="address" type="text"
                                    class="form-control" name="address"
                                    id="address"
                                    placeholder="Address (e.g. Pasternaka Str., 5)" />
                            </div>
                        </div>
                    </div>
                    <div id="map-canvas"></div>
                    <div class="row">
                        <div class="form-group">
                            <label for="Address"
                                class="col-lg-2 control-label"><h4>Number
                                    of people (min and max)</h4> </label>
                            <div class="col-lg-2">
                                <form:select class="form-control"
                                    path="minNumberOfGuests" id="min"
                                    onchange="repopulateMax(this.selectedIndex)" />
                            </div>
                            <div class="col-lg-2">
                                <form:select class="form-control"
                                    path="maxNumberOfGuests" id="max" />
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="gender allowed"
                                class="col-lg-2 control-label"><h4>Gender
                                    allowed</h4> </label>
                            <div class="col-lg-5 input-sm">
                                <label class="radio-inline"> <form:radiobutton
                                        path="gender" id="male"
                                        value="MALE" />Male
                                </label> <label class="radio-inline"> <form:radiobutton
                                        path="gender" id="female"
                                        value="FEMALE" />Female
                                </label> <label class="radio-inline"> <form:radiobutton
                                        path="gender" id="both"
                                        value="UNSPECIFIED" />Both
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-lg-2 control-label"><h4>Smoking
                                    allowed</h4> </label>
                            <div class="col-lg-4">
                                <label class="checkbox"> <form:checkbox
                                        path="smoking" id="smoking" />
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-lg-2 control-label"><h4>Family
                                    allowed</h4> </label>
                            <div class="col-lg-4">
                                <label class="checkbox"> <form:checkbox
                                        path="family" id="family" />
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-lg-2 control-label"><h4>Children
                                    allowed</h4> </label>
                            <div class="col-lg-4">
                                <label class="checkbox"> <form:checkbox
                                        path="children" id="children" />
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label class="col-lg-2 control-label"><h4>Pets
                                    allowed</h4> </label>
                            <div class="col-lg-4">
                                <label class="checkbox"> <form:checkbox
                                        path="pets" id="pets" />
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputFile"><h4>
                                Add photos of your hosting
                                <h5>(use ctrl+click to select
                                    multiple images)</h5>
                            </h4></label> <br> <input type="file" name="file"
                            class="multi" multiple accept="gif|jpg|png"
                            data-maxfile="10000" data-maxsize="50000" />
                    </div>
                    <div class="row">
                        <div class="form-group">
                            <label for="Notes"
                                class="col-lg-2 control-label"><h4>Add
                                    some notes</h4> </label>
                            <div class="col-lg-5">
                                <form:textarea path="notes"
                                    class="form-control" id="notes"
                                    rows="3" />
                            </div>
                        </div>
                    </div>
                    <br>
                    <button type="submit" class="btn btn-primary"
                        style="margin-bottom: 30px; margin-top: 10px;">Create
                        hosting</button>
                    <a href="${pageContext.request.contextPath}/profile"><button
                            type="button" class="btn btn-default"
                            style="margin-bottom: 30px; margin-top: 10px;">Cancel</button>
                    </a>
                </form:form>
            </div>
        </div>
    </div>
    </section>

    <script language="javascript">
					printCountry("country");
					printState('region', 0);
    </script>
    <script language="javascript">
        function initialize() {
            var mapOptions = {
                center: { lat: -34.397, lng: 150.644},
                zoom: 8
            };
            var map = new google.maps.Map(document.getElementById('map-canvas'),
                    mapOptions);
        }
        google.maps.event.addDomListener(window, 'load', initialize);

        window.onload = function() {
            maxAmount = 20;
            minselect = document.getElementById("min"),
                    maxselect = document.getElementById("max")
            for (var i = 1; i <= maxAmount; i++) {
                var minoption = null, maxoption = null;
                minoption = document.createElement("option");
                maxoption = document.createElement("option");
                minoption.innerHTML = i;
                minselect.appendChild(minoption);
                maxoption.innerHTML = maxAmount - i + 1;
                maxselect.appendChild(maxoption);
            }
        };
        function removeOptions(selectbox) {
            var i;
            for (i = selectbox.options.length - 1; i >= 0; i--) {
                selectbox.remove(i);
            }
        }
        function repopulateMax(selVal) {
            removeOptions(maxselect);
            for (var i = maxAmount; i > selVal; i--) {
                var option = null;
                option = document.createElement("option");
                option.innerHTML = i;
                maxselect.appendChild(option);
            }
        }
    </script>
</body>
</html>