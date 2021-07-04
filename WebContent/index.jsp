<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
		<script type="text/javascript" src="/DvdRental/dwr/engine.js"></script>
		<script type="text/javascript" src="/DvdRental/dwr/util.js"></script>
		<script type="text/javascript" src="/DvdRental/dwr/interface/countryService.js"></script>
	
		<script>
			function getDataFromServer() {
				countryService.getCountryById(1,{
			  	callback: getDataFromServerCallBack
			  });
			}
			
			function getDataFromServerCallBack(dataFromServer) {
			  alert(dwr.util.toDescriptiveString(dataFromServer, 3));
			}
		</script>
</head>
<body>
	<h3> 3.x/Spring 3.x with Annotations and Spring MVC</h3>
		<a href="#" onclick="getDataFromServer(); return false;">Retrieve test data</a><br/>
</body>
</html>