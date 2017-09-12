<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DMS</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.0.4/popper.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main.css" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light"> <span
		class="h3" class="navbar-brand mb-0">DMS</span>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/home">My Patients</a></li>
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.request.contextPath}/patients">Patients<span
					class="sr-only">(current)</span></a></li>
			<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/critical">Critical</a></li>
			<li>
				<input class="form-control mr-sm-2" type="text" placeholder="Search patient"
					aria-label="Search" name="search" id="search">
			</li>
		</ul>
		<div class="my-2 my-lg-0">
			<small>Logged in as <strong class="color_blue margin_right10">${username}</strong></small>
			<a href="${pageContext.request.contextPath}/logout"
				class="btn btn-outline-danger btn-sm">Logout</a>
		</div>
	</div>
	</nav>
	<div class="container">
		<div class="row height80"></div>
		<div class="row justify-content-center">
			<div class="col col-10">
				<h3>List of other patients</h3>
				<br />
				<table class="table table-responsive">
					<thead class="bg-success">
						<tr>
							<th>#</th>
							<th>Name</th>
							<th>Username</th>
							<th>Born on</th>
							<th>City</th>
							<th>Country</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="patient" items="${patients}">
							<tr class="table-success">
								<td>${patient.id}</td>
								<td><a href="${pageContext.request.contextPath}/patient?id=${patient.id}">${patient.name}</a></td>
								<td>${patient.username}</td>
								<td><fmt:formatDate type="date" value="${patient.bornDate}" /></td>
								<td>${patient.city}</td>
								<td>${patient.country}</td>
								<td><button class="btn btn-danger btn-block"
										onclick="addPatient(${patient.id})">Add</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>

<script type="text/javascript">
	function addPatient(id){
		if(confirm("Are you sure you want to add this patient?") ){
			location.href="/dms/add/patient?id="+id;
		}
	}
	
	$("#search").on("keyup", function() {
	    var value = $(this).val();

	    $("table tr").each(function(index) {
	        if (index !== 0) {

	            $row = $(this);

	            var id = $row.find("td:eq(1)").text();

	            if (id.indexOf(value) !== 0) {
	                $row.hide();
	            }
	            else {
	                $row.show();
	            }
	        }
	    });
	});
</script>
</body>
</html>