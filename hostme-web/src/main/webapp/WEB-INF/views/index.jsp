<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
    uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>

<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<script type="text/javascript" src="../resources/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="../resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="../resources/js/validation.js"></script>
<script type="text/javascript" src="../resources/js/registration.js"></script>
<link rel="stylesheet" type="text/css" href="../resources/css/datepicker.css">
<title>Sight Visiting</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
    <section class="content">
        <div class="row">
            <security:authorize access="! isAuthenticated()">
                    <div class="col-lg-3 col-xs-6 col-md-offset-3">
                        <div class="btn small-box bg-aqua">
                            <div data-toggle="modal"
                  			     data-target="#myModal" 
                         		 class="inner" style="padding-top: 2em">
                                <h4>
                                    <strong>Log in</strong>
                                </h4>
                            </div>
                            <div class="icon">
                                <i class="ion ion-stats-bars"></i>
                            </div>
                        </div>
                    </div>
                    
                    
<!-- tour btn -->
                <div class="col-lg-3 col-xs-6">
                    <div data-toggle="modal"
                         data-target=""
                         class="btn small-box bg-green">
                        <div class="inner" style="padding-top: 2em">
                            <h4>
                                <strong>Take a tour</strong>
                            </h4>
                        </div>
                        <div class="icon">
                            <i class="ion ion-search"></i>
                        </div>
                    </div>
                </div>
<!-- tour btn -->
                
                
                <!-- ./col -->
                
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div data-toggle="modal"
                         data-target="#registrationModal"
                         class="btn small-box bg-yellow">
                        <div class="inner" style="padding-top: 2em">
                            <h4>
                                <strong>Registration</strong>
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

        <!-- Registration Modal -->

        <div class="modal fade" id="registrationModal" tabindex="-1"
            role="dialog" aria-labelledby="myModalLabel"
            data-backdrop="static"
            aria-hidden="true">
            <div class="modal-dialog form-box ">

                <form:form method="post" action="registration"
                    modelAttribute="user" id="registrationForm"
                    onsubmit="validateForm(event)">
                    <div class="modal-content">
                    
                        <div class="header bg-blue">
                            <a href="<c:url value='/index' />"
                                type="button" class="close"> 
                                <span aria-hidden="true">&times;</span>
                                <span class="sr-only">Close</span>
                            </a>
                            <h4 class="modal-title" id="myModalLabel">Join us</h4>
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
                                            <form:errors path="firstName" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <div>
                                        <form:input path="lastName"
                                            type="text"
                                            class="form-control"
                                            id="lastName"
                                            placeholder="Last Name" />
                                            <form:errors path="lastName" />
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
                                            <form:errors path="login" />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <div>
                                        <form:input path="password"
                                            type="password"
                                            class="form-control"
                                            id="password"
                                            placeholder="Password" />
                                            <form:errors path="password" />
                                    </div>
                                </div>
                                
                                <div>
                                    <div class="form-group">
                                        <div>
                                            <input name="repeatPassword"
                                                type="password"
                                                class="form-control"
                                                id="repeatPassword"
                                                placeholder="Password again"
                                                onblur="checkIdenticalPasswords()" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="row">
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
                                                placeholder="email.example@email.com"
                                                onblur="checkEmailIdentity()" />
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <div>
                                            <form:input path="birthday"
                                                type="text"
                                                class="datepicker form-control"
                                                id="dateOfBirth"
                                                placeholder="Enter yuor B-day date (dd/MM/yyyy)" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="modal-footer">
                                <button
                                    class="btn btn-primary btn-block"
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
        
        <div class="modal fade" id="myModal" tabindex="-1" 
    	role="dialog" aria-labelledby="myModalLabel" 
    	data-backdrop="static"
        aria-hidden="true">
        <div class="modal-dialog form-box ">
        
            <form role="form"
                action="<c:url value='j_spring_security_check'/>"
                method="POST" id="loginForm">
                <div class="modal-content">
                
                    <div class="header bg-blue">
                        <a href="<c:url value='/index' />" 
                        	type="button" class="close"> 
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </a>
                        <h4 class="modal-title" id="myModalLabel">Sign In</h4>
                    </div>
                    
                    <div class="modal-body">
                        <c:if test="${param.success eq true }">
                            <div class="row">
                                <div class="callout callout-info">Your
                                    account has been activated
                                    successfully! Congratulations! Now you must
                                    log in!</div>
                                <%--<h4 class="success_registration">Your account has been--%>
                                <%--activated successfully!--%>
                            </div>
                        </c:if>
                        
                        <c:if test="${param.error eq true }">
                            <div class="callout callout-danger"
                                id="alert">Wrong login or password! Try again!</div>
                        </c:if>
                        
                        <c:if test="${param.registration eq true }">
                            <div class="callout callout-warning"
                                id="alert">We send you the mail
                                with the activation link. Please, check
                                your mail and activate your account!</div>
                        </c:if>

                        <c:if test="${param.logout eq true }">
                      	    <div class="callout callout-danger"
                                id="alert">You have logged out. Please, log in!</div>
                        </c:if>

                        <div class="body bg-gray">
                            <div class="form-group">
                                <div>
                                    <input type="text" name="j_username"
                                        class="form-control"
                                        placeholder="Login" required
                                        autofocus id="login">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div>
                                    <input type="password"
                                        name="j_password"
                                        class="form-control"
                                        placeholder="Password" required
                                        id="password">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="text-blue" for="remember">Remember
                                    me?</label> <input id="remember"
                                    type="checkbox" class="minimal"
                                    name="_spring_security_remember_me" />
                            </div>
                            
                            <div class="row">
                                <div class="col-sm-offset-5 col-sm-2"></div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="modal-footer">
                        <button class="btn btn-primary btn-block "
                            type="submit">Sign in</button>
                        <a href="<c:url value='/index' />" 
                           type="button"
                           class="btn btn-primary btn-block">Close</a>
                    </div>
                </div>
                
            </form>
            <div class="margin text-center">
                <span style="color: white;">Sign in using social
                    networks</span> <br>
                    
                <button class="btn bg-light-blue btn-circle">
                    <i class="fa fa-facebook"></i>
                </button>
                
                <button class="btn bg-aqua btn-circle">
                    <i class="fa fa-twitter"></i>
                </button>
                
                <button class="btn bg-red btn-circle">
                    <i class="fa fa-google-plus"></i>
                </button>
            </div>
        </div>
    </div>
        
    </section>

    <script type="text/javascript">
					$('.datepicker').datepicker();
					(function($) {
						$('input[type="checkbox"].flat-red, input[type="radio"].flat-red')
								.iCheck({
									checkboxClass : 'icheckbox_flat-red',
									radioClass : 'iradio_flat-red'
								});
					});
				</script>

</body>
</html>
