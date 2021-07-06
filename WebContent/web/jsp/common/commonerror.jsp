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
<script type="text/javascript"
	src="<%=strContextPath%>/resources/scripts/library/jquery/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="<%=strContextPath%>/resources/scripts/library/bootstrap-4.0.0-dist/css/bootstrap.min.css">
<!--  <script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>-->
<script
	src="<%=strContextPath%>/resources/scripts/library/bootstrap-4.0.0-dist/js/bootstrap.bundle.min.js"></script>

<script>
$(document).ready(function(){

	  $("#loginbtn").click(function(){
		 window.top.location="<%=strContextPath%>/";
	  })

	});
</script>

<style>
body {
	background-color: hsl(200, 65%, 20%);
	font-family: Verdana, Arial, Helvetica, sans-serif;
}

#containerDiv {
	height: 600px;
}

#rowDiv1 {
	padding-top: 40px;
	height: 500px;
}

#errorcontentsubdiv1 {
	height: 30px;
}

#errorcontentsubdiv2 {
	height: 300px;
}

#errortextcontentdiv1 {
	padding-top: 15%;
}

#imagediv {
	background-image:
		url("<%=strContextPath%>/resources/images/questionmanwithbox.png");
	background-repeat: no-repeat;
	background-size: 100% 100%;
	height: 300px;
	width: 300px;
}
</style>
</head>
<body>
	<div id="containerDiv" class="container-fluid">

		<div id="rowDiv1" class="row">
			<div class="col-md-2"></div>
			<div id="errorcontentmaindiv" class="col-sm-8">
				<div id="errorcontentsubdiv1" class="row">
					<div class="col"></div>
				</div>
				<div id="errorcontentsubdiv2" class="row">
					<div id="errortextcontentdiv1" class="col-sm-9 text-white">
						<h1>Something's wrong here</h1>
						<span>We can't find the page you're looking for. Check out
							our Help Center or head back to Login Page</span></br> <br>
						<button type="button" class="btn btn-outline-light">Help</button>
						<button id="loginbtn" type="button" class="btn btn-outline-light">Login</button>
					</div>
					<div id="errorimagecontentdiv2" class="col-sm-3">
						<div id="imagediv"></div>
					</div>

				</div>

			</div>
			<div class="col-md-2 "></div>
		</div>
	</div>

</body>
</html>