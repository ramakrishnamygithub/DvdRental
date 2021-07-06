<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>

<%
	String strContextPath = request.getContextPath();
%>
  <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript"
	src="<%=strContextPath%>/resources/scripts/library/jquery/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="<%=strContextPath%>/resources/scripts/library/bootstrap-4.0.0-dist/css/bootstrap.min.css">
<!--  <script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>-->
<script
	src="<%=strContextPath%>/resources/scripts/library/bootstrap-4.0.0-dist/js/bootstrap.bundle.min.js"></script>
    <title>Hello, world!</title>
    <style>
   #header1{
    background-color: hsl(200, 65%, 20%);
	font-family: Verdana, Arial, Helvetica, sans-serif;
	height:70px;
	}
    </style>
</head>
<body>
<div id="header1" class="row">
 <div class="col-md-10"></div>
 <div class="col-md-2"></div>
</div>
<div id="header2" >
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#">Dvd Rental</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">

				<a class="nav-link  active" href="<%=strContextPath%>/web/jsp/common/DashBoard.jsp"><i class="fa fa-fw fa-home"></i>
					Home</a>
				</a>
				</li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Master </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="<%=strContextPath%>/web/jsp/masters/Country.jsp">Country</a> <a
							class="dropdown-item" href="#">City</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Something else here</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Dropdown </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">Action</a> <a
							class="dropdown-item" href="#">Another action</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Something else here</a>
					</div></li>
				<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
				</li>

			</ul>
			<form class="form-inline my-2 my-lg-0">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
		</div>
	</nav>
	</div>
</body>
</html>