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
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/patients">Patients</a></li>
			<li class="nav-item active"><a class="nav-link"
				href="${pageContext.request.contextPath}/critical">Critical<span
					class="sr-only">(current)</span></a></li>
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
				<h3>List of patients with high glucose level</h3>
				<br />
				<table class="table table-responsive">
					<thead class="bg-danger">
						<tr>
							<th>#</th>
							<th>Level</th>
							<th>Added on</th>
							<th>Patient</th>
							<th>City</th>
							<th>Country</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="measure" items="${measures}">
							<tr class="table-danger">
								<td>${measure.id}</td>
								<td>${measure.level }</td>
								<td><fmt:formatDate type="date"
										value="${measure.dateAdded}" /></td>
								<td><a
									href="${pageContext.request.contextPath}/patient?id=${measure.patient.id}">${measure.patient.name}</a></td>
								<td>${measure.patient.city}</td>
								<td>${measure.patient.country}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
<script type="text/javascript">
$("#search").on("keyup", function() {
    var value = $(this).val();

    $("table tr").each(function(index) {
        if (index !== 0) {

            $row = $(this);

            var id = $row.find("td:eq(3)").text();

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