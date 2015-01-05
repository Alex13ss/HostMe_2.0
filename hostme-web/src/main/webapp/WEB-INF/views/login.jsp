<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
    uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery.validate.js"></script>
<script type="text/javascript" src="resources/js/validation.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<script
    src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<body>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog"
        aria-labelledby="myModalLabel" data-backdrop="static"
        aria-hidden="true">
        <div class="modal-dialog form-box ">
            <form role="form"
                action="<c:url value='j_spring_security_check'/>"
                method="POST" id="loginForm">
                <div class="modal-content">
                    <div class="header bg-blue">
                        <h4 class="modal-title" id="myModalLabel">Sign
                            In</h4>
                        <a href="<c:url value='/index' />" type="button"
                            class="close"> <span aria-hidden="true">&times;</span><span
                            class="sr-only">Close</span>
                        </a>
                    </div>
                    <div class="modal-body">
                        <c:if test="${param.success eq true }">
                            <div class="row">
                                <div class="callout callout-info">Your
                                    account has been activated
                                    successfully! Congrats!Now you must
                                    log in!</div>
                                <%--<h4 class="success_registration">Your account has been--%>
                                <%--activated successfully!--%>
                            </div>
                        </c:if>
                        <c:if test="${param.error eq true }">
                            <div class="row">
                                <div class="col-md-6 col-md-offset-2">
                                    <h4 class="error">Wrong login
                                        or password! Try again!</h4>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${param.registration eq true }">
                            <div class="callout callout-warning"
                                id=" alert">We send you the mail
                                with the activation link. Please, check
                                your mail and activate your account!</div>
                        </c:if>

                        <c:if test="${param.logout eq true }">
                            <div class="row">
                                <div class="col-md-6"
                                    style="margin-left: 1em; margin-bottom:1em;">
                                    <label class="error"><code>You
                                            have logged out. Please, log
                                            in!</code></label>
                                </div>
                            </div>
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
                        <a href="<c:url value='/index' />" type="button"
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
    <script type="text/javascript">
					$(function() {
						$('.modal').modal({
							show : true,
							keyboard : false,
							backdrop : 'static'
						});
					});
				</script>


</body>

</html>
