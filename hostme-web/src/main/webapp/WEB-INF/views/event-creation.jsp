<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body class="skin-blue  pace-done" style="min-height: 1293px;">

	<h1>New Event</h1>
	<form:form method="post" action="eventAdd" commandName="event">

		<table id="example1"
			class="table table-bordered table-striped dataTable">
			<tr>
				<td><form:label path="address">
						<spring:message code="label.address" />
					</form:label></td>
				<td><form:input path="address" /></td>
			</tr>
			<tr>
				<td><form:label path="website">
						<spring:message code="label.website" />
					</form:label></td>
				<td><form:input path="website" /></td>
			</tr>
			<tr>
				<td><form:label path="description">
						<spring:message code="label.description" />
					</form:label></td>
				<td><form:input path="description" /></td>
			</tr>
			<tr>
				<td><form:label path="comment">
						<spring:message code="label.comment" />
					</form:label></td>
				<td><form:input path="comment" /></td>
			</tr>
			<tr>
				<td><form:label path="city.country.country">
						<spring:message code="label.country" />
					</form:label></td>
				<td><form:input path="city.country.country" /></td>
			</tr>
			<tr>
				<td><form:label path="city.city">
						<spring:message code="label.city" />
					</form:label></td>
				<td><form:input path="city.city" /></td>
			</tr>
			<tr>
				<td><form:label path="priceCategory.priceCategory">
						<spring:message code="label.priceCategory" />
					</form:label></td>
				<td><form:input path="priceCategory.priceCategory" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit"
					value="<spring:message code="label.createEvent"/>" />
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>