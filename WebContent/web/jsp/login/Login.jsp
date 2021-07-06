<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Dvd Rental</title>
<link rel="icon" href="/DvdRental/resources/images/Favcon.png"
	type="image/png" sizes="16x16">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%
	String strContextPath = request.getContextPath();
%>
<%-- <script type="text/javascript"
	src="<%=strContextPath%>/resources/scripts/library/jquery/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="<%=strContextPath%>/resources/scripts/library/bootstrap-4.0.0-dist/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="<%=strContextPath%>/resources/scripts/library/bootstrap-4.0.0-dist/js/bootstrap.min.js"></script> --%>
	<script type="text/javascript"
	src="<%=strContextPath%>/resources/scripts/library/jquery/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="<%=strContextPath%>/resources/scripts/library/bootstrap-4.0.0-dist/css/bootstrap.min.css">
<!--  <script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>-->
<script
	src="<%=strContextPath%>/resources/scripts/library/bootstrap-4.0.0-dist/js/bootstrap.bundle.min.js"></script>

<%
	String loginStatus = (String) request.getAttribute("loginstatus");
	if (loginStatus == null) {
		loginStatus = "";
	}
%>
</head>
<body>
	<style>
body {
	background-color: hsl(200, 65%, 20%);
	font-family: Verdana, Arial, Helvetica, sans-serif;
}

#containerDiv {
	border-radius: 10px;
	height: 600px;
	background-image:
		url("/DvdRental/resources/images/album_art_compilation-wallpaper-1366x768.jpg");
	background-repeat: no-repeat;
	background-size: 100% 100%;
}

#rowDiv {
	padding-top: 40px;
	height: 400px;
}

#loginMainDiv {
	height: 100%;
}

#loginSubDiv1 {
	height: inherit;
}

#loginSideDiv1 {
	border-top-left-radius: 10px;
	border-bottom-left-radius: 10px;
	background-image: url("/DvdRental/resources/images/Favcon.png");
	background-repeat: no-repeat;
	background-size: 100% 100%;
}

#loginSideDiv2 {
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
	background-color: #ffffff;
}
</style>
	<div id="containerDiv" class="container p-3 my-3   border">

		<div id="rowDiv" class="row  ">
			<div class="col-2 "></div>
			<div id="loginMainDiv" class="col-8  ">
				<div id="loginSubDiv1" class="row">
					<div id="loginSideDiv1" class="col"></div>
					<div id="loginSideDiv2" class="col">
						<div class="row" style="height: 20%;">
							<div class="col-12">
								<h2 class="text-center text-primary">Dvd Rental</h2>
							</div>
						</div>
						<div class="row">
							<div class="col-1"></div>
							<div class="col-10">
								<form action="SSOLoginContoller" class="" method="post">
									<div class="form-group ">
										<label for="email">Email:</label> <input type="email"
											name="email" class="form-control" placeholder="Enter email"
											id="email">
									</div>
									<div class="form-group">
										<label for="pwd">Password:</label> <input type="password"
											name="password" class="form-control"
											placeholder="Enter password" id="pwd">
									</div>
									<div class="form-group form-check">
										<label class="form-check-label"> <input
											class="form-check-input" type="checkbox"> Remember me
										</label>
									</div>
									<button type="submit" class="btn btn-primary">Log In</button>
									<br> <span style="color: red;"><%=loginStatus%></span>
								</form>
							</div>
							<div class="col-1"></div>
						</div>
						<div class="row" style="height: 20%;">
							<div class="col-12"></div>
						</div>
					</div>

				</div>
			</div>
			<div class="col-2 "></div>
		</div>
	</div>
</body>
</html>