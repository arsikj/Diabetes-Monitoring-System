<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Patient - ${patient.name}</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.0.4/popper.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap-datepicker.min.css" />
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
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/critical">Critical</a></li>
		</ul>
		<div class="my-2 my-lg-0">
			<small>Logged in as <strong class="color_blue margin_right10">${username}</strong></small>
			<a href="${pageContext.request.contextPath}/logout"
				class="btn btn-outline-danger btn-sm">Logout</a>
		</div>
	</div>
	</nav>

	<div class="container">
		<div class="height80"></div>
		<div class="row justify-content-center">
			<div class="col-3">
				<div class="card">
					<div class="card-header card_header">
						<h5>Patient Details</h5>
					</div>
					<div class="card-body">
						<h4 class="card-title">${patient.name}</h4>
						<h6 class="card-subtitle mb-2 text-muted">${patient.username}</h6>

						<ul class="list-group list-group-flush">
							<li class="list-group-item"><strong>Born on:</strong> <fmt:formatDate
									type="date" value="${patient.bornDate}" /></li>
							<li class="list-group-item"><strong>City:</strong>
								${patient.city}</li>
							<li class="list-group-item"><strong>Country:</strong>
								${patient.country}</li>
							<li class="list-group-item"><strong>Height:</strong>
								${patient.height} cm</li>
							<li class="list-group-item"><strong>Weight:</strong>
								${patient.weight} kg</li>
							<li class="list-group-item"><strong>Total measures:</strong>
								${measures.size()}</li>
						</ul>
					</div>
				</div>
			</div>

			<div class="col-9 text-center">
				<canvas id="myChart" height="150"></canvas>
				<br />
				<button class="btn btn-primary" id="line">Line</button>
				<button class="btn btn-primary" id="bar">Bar</button>
				<button class="btn btn-primary" id="radar">Radar</button>
				<br />
				<div class="col-6 center margin_top20">
					<h3>Choose date ranges</h3>
					<div class="input-group input-daterange text-center">
						<form method="POST" class="form-inline" id="form" action="${pageContext.request.contextPath}/patient?id=${patient.id}">
							<input type="text" class="form-control" placeholder="08/14/2017" name="fromDate">
							<div class="input-group-addon">to</div>
							<input type="text" class="form-control margin_right10" placeholder="08/19/2017" name="toDate">
							<button class="btn btn-primary" type="submit">OK</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="height80"></div>
	</div>

	<script type="text/javascript">
	
	var measures = ${measures};
	var dates = ${measureDates};
	var config = {
		    type: 'line',
		    data: {
		        labels: dates,
		        datasets: [{
		            label: 'level of glucose (mg/dl)',
		            data: measures,
		            backgroundColor: 'rgba(249, 134, 145, 1)',
		            borderColor: 'rgba(220, 53, 69, 1)',
		            borderWidth: 1
		        }]
		    },
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        },
		        responsive: true
		    }
		}
	
	var myChart;
	$(document).ready(function() {
		$('.input-daterange input').each(function() {
		    $(this).datepicker('clearDates');
		});
		
		var ctx = $("#myChart");
		myChart = new Chart(ctx, config);
	});
	
	

	$("#line").click(function() {
	  	change('line');
	});

	$("#bar").click(function() {
	 	change('bar');
	});
	
	$("#radar").click(function() {
		change('radar');
	});
	
	function change(newType) {
		  var ctx = $("#myChart");

		  // Remove the old chart and all its event handles
		  if (myChart) {
		    myChart.destroy();
		  }

		  // Chart.js modifies the object you pass in. Pass a copy of the object so we can use the original object later
		  var temp = jQuery.extend(true, {}, config);
		  temp.type = newType;
		  myChart = new Chart(ctx, temp);
	};
</script>
</body>
</html>