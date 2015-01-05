<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="min-height: 682px;">
<head>





</head>
<body>

	<section class="content-header">
	<h1>
		Edite User Languages
	</h1>
	</section>
	<!-- Main content -->
	<section class="content">
	<div class="container">
		<div class="col-md-6">
			<div class="box box-primary">

				<div class="box-body">
				
					<form:form 
						role="form" 
						method="POST" 
						commandName="user"
						action="edited-languages"
						name ="form1">
				
				
						<div class="form-group">
							Here you can list the languages you can communicate with. You can list your most used languages.
						</div>
						
						<hr
							style="color: #c00; background-color: #c00; height: 1px; border: none;" />
						
						
						
						<c:forEach var="i" begin="0" end="8" step="1" varStatus="loop">
    

						
							<div class="form-group">
								<label for="language">Language ${1+i}</label>
								<form:select path="" name="newLanguages">								
							
									 <form:option value="${(user.languages[i] == null)?'': user.languages[i]}" />  
							
   									 <form:options items="${langs}" />
								</form:select>
							</div>
							
						</c:forEach>
						
						<div class="box-footer">
							<button type="submit" class="btn btn-primary">Submit</button>
						</div>
						
						
					</form:form>
				
				
				</div>
			</div>
		</div>

	</div>
	</section>


</body>
</html>