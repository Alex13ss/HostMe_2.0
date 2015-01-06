<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
    uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta
    content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
    name='viewport'>
<script type="text/javascript"
        src="/resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript"
        src="/resources/js/jquery.validate.js"></script>
<script type="text/javascript"
        src="/resources/js/validation.js"></script>
<script type="text/javascript"
        src="/resources/js/registration.js"></script>
<link rel="stylesheet" type="text/css"
        href="/resources/css/datepicker.css">
<title>Sight Visiting</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
    <section class="content">
        <div class="row">
            <security:authorize access="! isAuthenticated()">
                <a href="<c:url value='login' />">
                    <div class="col-lg-3 col-xs-6 col-md-offset-3">
                        <div class="btn small-box bg-aqua">
                            <div class="inner" style="padding-top: 2em">
                                <h4>
                                    <strong>Log in</strong>
                                </h4>
                            </div>
                            <div class="icon">
                                <i class="ion ion-stats-bars"></i>
                            </div>
                        </div>
                    </div>
                </a>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div data-toggle="modal"
                         data-target="#registrationModal"
                         class="btn small-box bg-yellow">
                        <div class="inner" style="padding-top: 2em">
                            <h4>
                                <strong>Sign in</strong>
                            </h4>
                        </div>
                        <div class="icon">
                            <i class="ion ion-person-add"></i>
                        </div>
                    </div>
                </div>
            </security:authorize>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="box box-solid">
                    <div class="box-body">
                        <div id="carousel-example-generic"
                            class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li
                                    data-target="#carousel-example-generic"
                                    data-slide-to="0" class=""></li>
                                <li
                                    data-target="#carousel-example-generic"
                                    data-slide-to="1" class="active"></li>
                                <li
                                    data-target="#carousel-example-generic"
                                    data-slide-to="2" class=""></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="item">
                                    <img
                                        src="resources/images/index-hosting1.jpg"
                                        alt="First slide">
                                    <div class="carousel-caption">First
                                        Slide</div>
                                </div>
                                <div class="item active">
                                    <img
                                        src="resources/images/index-hosting2.jpg"
                                        alt="Second slide">
                                    <div class="carousel-caption">Second
                                        Slide</div>
                                </div>
                                <div class="item">
                                    <img
                                        src="resources/images/index-hosting3.jpg"
                                        alt="Third slide">
                                    <div class="carousel-caption">Third
                                        Slide</div>
                                </div>
                            </div>
                            <a class="left carousel-control"
                                href="#carousel-example-generic"
                                data-slide="prev"> <span
                                class="glyphicon glyphicon-chevron-left"></span>
                            </a> <a class="right carousel-control"
                                href="#carousel-example-generic"
                                data-slide="next"> <span
                                class="glyphicon glyphicon-chevron-right"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Login Modal -->

        <div class="modal fade" id="registrationModal" tabindex="-1"
            role="dialog" aria-labelledby="myModalLabel"
            aria-hidden="true">
            <div class="modal-dialog form-box ">

                <form:form method="post" action="registration"
                    modelAttribute="user" id="registrationForm"
                    onsubmit="validateForm(event)">
                    <div class="modal-content">
                        <div class="header bg-blue">

                            <a href="<c:url value='/index' />"
                                type="button" class="close"> <span
                                aria-hidden="true">&times;</span><span
                                class="sr-only">Close</span>
                            </a>
                            <h4 class="modal-title" id="myModalLabel">Join
                                us</h4>
                        </div>
                        <div class="modal-body">
                            <div class="body bg-gray">
                                <div class="form-group">
                                    <div>
                                        <form:input path="firstName"
                                            type="text"
                                            class="form-control"
                                            id="firstName"
                                            placeholder="First Name" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div>
                                        <form:input path="lastName"
                                            type="text"
                                            class="form-control"
                                            id="lastName"
                                            placeholder="Last Name" />
                                    </div>

                                </div>
                                <div class="form-group">
                                    <div>
                                        <form:input path="login"
                                            type="text"
                                            class="form-control"
                                            id="login"
                                            placeholder="Login"
                                            onblur="checkLoginIdentity()" />
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div>
                                        <form:input path="password"
                                            type="password"
                                            class="form-control"
                                            id="password"
                                            placeholder="Password" />
                                    </div>
                                </div>
                                <div>
                                    <div class="form-group">
                                        <div>
                                            <input name="repeatPassword"
                                                type="password"
                                                class="form-control"
                                                id="repeatPassword"
                                                placeholder="Password"
                                                onblur="checkIdenticalPasswords()" />
                                        </div>
                                    </div>


                                    <div class="form-group">
                                        <div class="row">
                                            <form:radiobutton
                                                class="radio inline"
                                                path="gender"
                                                value="FEMALE" />
                                            Female
                                            <form:radiobutton
                                                path="gender"
                                                value="MALE"
                                                class="radio inline" />
                                            Male
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div>
                                            <form:input path="email"
                                                type="text"
                                                class="form-control"
                                                id="email"
                                                placeholder="some.email@gmail.com"
                                                onblur="checkEmailIdentity()" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div>
                                            <form:input path="birthday"
                                                type="text"
                                                class="datepicker form-control"
                                                id="dateOfBirth"
                                                placeholder="MM/dd/yyyy"
                                                pattern="MM/dd/yyyy" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button
                                    class="btn btn-primary btn-block "
                                    type="submit">Sumbit</button>
                                <a href="<c:url value='/index' />"
                                    type="button"
                                    class="btn btn-primary btn-block">Close</a>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>


        <!-- Login Modal -->
    </section>

    <script type="text/javascript">
					$('.datepicker').datepicker();
					(function($) {
						$(
								'input[type="checkbox"].flat-red, input[type="radio"].flat-red')
								.iCheck({
									checkboxClass : 'icheckbox_flat-red',
									radioClass : 'iradio_flat-red'
								});
					});
				</script>

</body>
</html>
