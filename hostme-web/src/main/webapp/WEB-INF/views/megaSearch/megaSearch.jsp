<%@ page language="java" contentType="text/html; charset=Utf-8"
         pageEncoding="Utf-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<title><spring:message code="label.search"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=Utf-8">

<script src="<c:url value="/resources/js/search/search.js"/>"></script>

<section class="content-header">
  <h1>
    <spring:message code="label.search" />
  </h1>
</section>

<section class="content">
  <div class="box box-primary">
    <div class="container-fluid">
      <div class="row">
        <select id="searchType" class="col-lg-2"></select>
        <input id="search" class="col-lg-10">
        <p id="searchReq"></p>
      </div>
      <div id="searchResult">
        <div id="name"></div>
      </div>
    </div>
  </div>
</section>

