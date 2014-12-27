<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="min-height: 682px;">
<head>

<link rel="stylesheet" type="text/css" href="resources/css/datepicker.css">
<link rel="stylesheet" type="text/css" href="resources/css/AdminLTE.css">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrapValidator.css">

<script type="text/javascript" src="resources/js/jquery.MultiFile.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.12.0/jquery.validate.min.js"></script>

<script type="text/javascript" src="resources/js/bootstrap-datepicker.js"></script>


</head>
<body>
	<div class="wrapper row-offcanvas row-offcanvas-left"
		style="min-height: 682px;">

		<aside class="right-side"> <!-- Content Header (Page header) -->

		<section class="content-header">
		<h1>
			Dashboard <small>Control panel</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"> <i class="fa fa-dashboard"></i> Home </a></li>
			<li class="active">Dashboard</li>
		</ol>
		</section> <!-- Main content --> <section class="content">

		<div class="container">

			<div class="col-md-6">
				<!-- general form elements -->
				<div class="box box-primary">
					<div class="box-header">
						<h3 class="box-title">Edit your profile</h3>
					</div>
					<!-- form start -->
					<form:form 
						role="form" 
						method="POST" 
						commandName="user"
						action="edited-profile"
						name ="form1"
						enctype="multipart/form-data"
						id="edit-profile-form">

						<div class="box-body">
							<div class="form-group">
							
								<label for="inputLogin">Login</label>
								
								<form:input path="login" 
											class="form-control" 
											id="inputLogin"
											disabled="true" />
								<small> You can not change your login </small>
							</div>
							
							<div class="form-group">
								<label for="firstName">First name</label>
								
								<form:input path="firstName" 
											class="form-control" 
											id="firstName"
											value="${user.firstName}" />
							</div>
							
							<div class="form-group">
								<label for="lastName">Last name</label>
								<form:input path="lastName" 
											class="form-control" 
											id="lastName" />
							</div>

							<div class="form-group">
								<label for="gender" style="display: block; float: none">Gender</label>
								<label class="radio-inline"> 
									<form:radiobutton   path="gender" 
														id="male" 
														value="MALE" /> Male
								</label> 
								
								<label class="radio-inline"> 
									<form:radiobutton	path="gender" 
														id="female" 
														value="FEMALE" /> Female
								</label>
								
							</div>	

							<div class="form-group">
								<label for="email">Password</label> 
								<input 	class="form-control"
										id="password" value="****" 
										disabled /> 
								<small> You can change your <a class="btn-small" href="#myModal" data-toggle="modal">password </a> here	</small>
							</div>




								<div class="form-group">
									<label for="email">Email</label>
									
									<form:input path="email" 
												class="form-control" 
												id="email"
												name="email" />
								</div>

								<div class="form-group">
									<label for="profilePicture"><h4>Profile picture</h4></label> <br>
									<input type="file" name="file" id="profilePicture" maxamount="1" class="multi"
											data-maxfile="10000" data-maxsize="50000" />
								</div>

								<div class="form-group">
									<label for="language">Languages</label> 
									</br> 
									<small> You can change your <a class="btn-small" href="<c:url value='edit-languages' />">languages </a> here	</small>
								</div>


								<div class="form-group">
                	                <div>
                	                <label>Date of Birth</label> 
            	                        <input 
                 	                          type="text" 
                 	                          value="${bd}"
                     	                      class="datepicker form-control" 
                         	                  id="dateOfBirth" 
                                              name="birth"/> 
                    	            </div>
                                </div>


								<div class="form-group">
									<label>Personal Description</label>
									<form:textarea class="form-control" path="description" rows="3"></form:textarea>

								</div>

								<div class="form-group">
									<label>Interests</label>
									<form:textarea class="form-control" path="hobby" rows="3"></form:textarea>
								</div>

								<!-- /.box-body -->

								<div class="box-footer">
									<button type="submit" class="btn btn-primary">Submit</button>
								</div>
							</div>
						
					</form:form>
				</div>
			</div>
		</div>
		</section> <!-- /.content --> </aside>
	</div>

</body>


								<!-- Modal form for Change PW-->

									<form:form	cssClass="form-horizontal changePasswordForm"
												action="change-password"
												method="POST"
												id="change-pasword-mf">
												
										<div 	class="modal fade" 
												id="myModal" 
												tabindex="-1" 
												role="dialog"
												aria-labelledby="myModalLabel" 
												aria-hidden="true">
												
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">&times;</button>
														<h4 class="modal-title" id="myModalLabel">
															Change password form
														</h4>
													</div>
													<div class="modal-body">
									
														<div class="form-group">
															<label for="oldPassword" class="col-sm-2 control-label">Password</label>
															<div class="col-sm-10">
																<input type="password" name="oldPassword" id="oldPassword"
																	class="form-control" />
															</div>
														</div>
									
														<div class="form-group">
															<label for="newPassword" class="col-sm-2 control-label">New password</label>
															<div class="col-sm-10">
																<input type="password" id="newPassword" name="newPassword"
																	id="newPassword" class="form-control" />
															</div>
														</div>
									
														<div class="form-group">
															<label for="newPassword" class="col-sm-2 control-label">Password again</label>
															<div class="col-sm-10">
																<input type="password" name="passwordAgain" id="passwordAgain"
																	class="form-control" />
															</div>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default" data-dismiss="modal">
															Back
														</button>
														<input class="btn btn-large btn-primary" type="submit"
															value="Submit">
													</div>
													</div>
									
												</div>
											</div>
									</form:form>

								<!-- Modal form-->

<script type="text/javascript">
$(document).ready(function() {
	
					$("#change-pasword-mf").validate(
							{
									rules : {
											oldPassword : {
												required : true,
												remote : {
													url : "<c:url value='/change-password/correctOldPassword'/>",
													type : "POST",
													data : {
														oldPassword : function() {
															return $("#oldPassword").val();
														}
													}
												}
											},
											newPassword : {
												minlength : 4,
												required : true
											},
											passwordAgain : {
												required : true,
												equalTo : "#newPassword"
											}
									},
									messages : {
										oldPassword : {
											remote : "Wrong password!",
											required : "This field is required!"
										},
										newPassword : {
											minlength : "Min length 4",
											required : "This field is required!"
										},
										passwordAgain : {
											equalTo : "Incorrectly filled!",
											required : "This field is required!"
										}
									}
							});
					
					$("#edit-profile-form").validate(
							{
									rules : {
										email : {
											required : true,
											email : true
										}
									},
									messages : {
										email : {
											required: "This field is required!",
											email : "Not correct email!"
											
										}
									}
							});
});
</script>


<script type="text/javascript">
					$('.datepicker').datepicker();
</script>



</html>