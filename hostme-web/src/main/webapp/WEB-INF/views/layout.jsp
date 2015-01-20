<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="security"
    uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<link rel="stylesheet" type="text/css"
    href="resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
      href="resources/css/style.css">
<link rel="stylesheet" type="text/css"
    href="resources/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
      href="resources/css/ionicons.css">
<link rel="stylesheet" type="text/css" href="resources/css/minimal.css">
<script src="resources/js/jquery-2.1.1.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/app.js"></script>
<script src="resources/js/jquery-ui-1.10.3.min.js"></script>
</head>
<body class="skin-blue  pace-done">
    <security:authorize access="isAuthenticated()">
        <tiles:insertAttribute name="header" />

        <div class="wrapper row-offcanvas row-offcanvas-left"
            style="min-height: 593px;">
            <tiles:insertAttribute name="sidebar" />
            <aside class="right-side">
                <tiles:insertAttribute name="body" />
            </aside>
            
                <tiles:insertAttribute name="footer" />
            
        </div>
        
    </security:authorize>

           
    


    <security:authorize access="! isAuthenticated()">
        <aside>
            <tiles:insertAttribute name="body" />
        </aside>
    </security:authorize>


 
</body>
</html>