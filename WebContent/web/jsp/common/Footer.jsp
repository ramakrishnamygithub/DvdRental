<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<%
	String strContextPath = request.getContextPath();
%>
<script type="text/javascript"
	src="<%=strContextPath%>/resources/scripts/library/jquery/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="<%=strContextPath%>/resources/css/bootstrap5/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="<%=strContextPath%>/resources/scripts/library/bootstrap5/bootstrap.min.js"></script>
<style>
body {
	margin: 0;
}

/* Style the header */
.footer {
	background-color: #f1f1f1;
	padding: 20px;
	text-align: center;
}
</style>
</head>
<body>
	<div class="footer">
		<h1>Footer</h1>
	</div>



</body>
</html>