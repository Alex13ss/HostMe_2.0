<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript" src="resources/js/countries3.js"></script>
<script type="text/javascript" src="resources/js/jquery.MultiFile.js"></script>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/validation.js"></script>

</head>
<body>

    <section class="content-header">
    <h1>
        <spring:message code="label.editHosting" />
    </h1>
    </section>

    <!-- Main content -->
    <section class="content">


    <div>

        <div class="box box-primary">

            <div class="box-body">

                <form:form method="post" action="hosting-editing"
                    onsubmit="validateForm(event)"
                    modelAttribute="hosting" id="hostingCreationForm"
                    enctype="multipart/form-data">
                    <div class="row"  style="margin-top:1.5em">
                        <div class="form-group">
                            <label for="country"
                                class="col-lg-2 control-label"><h4>Country</h4>
                            </label>
                            <div class="col-lg-5">
                                <form:select class="form-control"
                                    path="country" id="country"
                                    name="country"
                                    onchange="printState('region',this.selectedIndex);">
                                </form:select>
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
                                    path="region" id="region">
                                </form:select>
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
                    <div class="row">
                        <div class="form-group">
                            <label for="Address"
                                class="col-lg-2 control-label"><h4>Number
                                    of people (min and max)</h4> </label>
                            <div class="col-lg-2">
                                <form:select class="form-control"
                                    path="minNumberOfGuests" id="min"
                                    onchange="repopulateMax(this.selectedIndex)">
                                </form:select>
                            </div>
                            <div class="col-lg-2">
                                <form:select class="form-control"
                                    path="maxNumberOfGuests" id="max">
                                </form:select>
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
                    <input type="hidden" value="${hosting.hostingId}"
                        name="hostingId" />
                    <input type="hidden" value="${userId}" name="userId" />
                    <button type="submit" class="btn btn-primary"
                        style="margin-bottom: 30px; margin-top: 10px;">Save
                        changes</button>
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
					$(function() {
						$("#country").val("${hosting.country}");
						printState('region', $("#country option:selected")
								.index());
						$("#region").val("${hosting.region}");
					});
					$(function() {
						populateMinMax();
						$("#min").val("${hosting. minNumberOfGuests}");
						$("#max").val("${hosting. maxNumberOfGuests}");
					});
				</script>
    <script language="javascript">
					function populateMinMax() {
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
					}
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