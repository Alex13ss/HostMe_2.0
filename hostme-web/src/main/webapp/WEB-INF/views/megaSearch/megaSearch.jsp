<%@ page language="java" contentType="text/html; charset=Utf-8"
         pageEncoding="Utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title><spring:message code="label.search"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">

<script src="<c:url value="/resources/js/search/search.js"/>"></script>
<link href="<c:url value="/resources/css/search/searchResults.css"/>" rel="stylesheet">

<section class="content-header">
  <h1>
    <spring:message code="label.search" />
  </h1>
</section>

<section class="content">
    <div class="box box-primary container-fluid">
        <select id="searchType" class="col-lg-2" style="height: 3%"></select>
        <input id="search" class="col-lg-10" style="height: 3%">
        <p id="searchReq"></p>
        <table id="searchResult" class="col-lg-12"></table>
    </div>
</section>


