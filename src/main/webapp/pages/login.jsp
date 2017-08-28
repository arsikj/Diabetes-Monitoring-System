<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign in</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.0.4/popper.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css"/>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-5">
				<div class="card login-form-margin">
					<div class="card-body">
						<form class="form-signin" method="POST" id="form" action="${pageContext.request.contextPath}/login">
							<h2 class="form-signin-heading">Please sign in</h2>
							<div class="form-group">
								<label for="exampleInputUsername">Username</label>
    							<input type="text" class="form-control" id="exampleInputUsername" aria-describedby="emailHelp" placeholder="Username" name="username">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">Password</label>
    							<input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password" name="password">
							</div>
							<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
							<p class="text-center register-margin">
								<a class="card-link form-control-sm form-text" href="${pageContext.request.contextPath}/signup">New user? Register here.</a>
							</p>
							<p class="text-center margin_top5 color_green">${message}</p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>