<%@ page language="java" contentType="text/html; charset=Utf-8"
         pageEncoding="Utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title>Manage minions</title>

<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">

<section class="content-header">
    <h1>
        <spring:message code="admin.usersManager" />
    </h1>
</section>

<section class="content">
  <div class="box box-primary">
    <div class="container-fluid">
        <table class="table table-bordered">
            <tr>
                <th><spring:message code="label.login" /></th>
                <th><spring:message code="label.firstname" /></th>
                <th><spring:message code="label.lastname" /></th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.login}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
  </div>
</section>

