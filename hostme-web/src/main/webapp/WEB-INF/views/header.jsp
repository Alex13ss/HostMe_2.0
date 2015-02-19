<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<meta
    content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
    name='viewport'>

<script type="text/javascript" src="resources/js/header.js"></script>

<header class="header">

    <a href="index" class="logo"> <!-- Add the class icon to your logo image or logo icon to add the margining -->
        Host Me
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->
        <a href="#" class="navbar-btn sidebar-toggle"
            data-toggle="offcanvas" role="button"> <span
            class="sr-only">Toggle navigation</span> <span
            class="icon-bar"></span> <span class="icon-bar"></span> <span
            class="icon-bar"></span>
        </a>
        <div class="navbar-right">
            <ul class="nav navbar-nav">
                <!-- Messages: style can be found in dropdown.less-->

                <li class="dropdown"><a class="dropdown-toggle"
                    data-toggle="dropdown" href="#">
                    <spring:message code="label.title" />
                    <span class="caret"></span></a>

                    <ul class="dropdown-menu">
                        <li role="presentation"><a role="menuitem"
                            tabindex="-1" id="en"
                            onclick="setLanguage('en');" href="#">English</a></li>
                        <li role="presentation"><a role="menuitem"
                            tabindex="-1" id="ua"
                            onclick="setLanguage('ua');" href="#">Українська</a></li>
                    </ul>

                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu"><a
                    href="profile" class="glyphicon glyphicon-user">

                        ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}

                </a></li>
            </ul>
        </div>
    </nav>

</header>




