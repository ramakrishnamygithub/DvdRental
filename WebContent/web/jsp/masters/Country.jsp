<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>DvdRental Dashboard</title>
<%
	String strContextPath = request.getContextPath();
%>
<script type="text/javascript"
	src="<%=strContextPath%>/resources/scripts/library/jquery/jquery-3.6.0.js"></script>

	<script type="text/javascript" src="<%=strContextPath%>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=strContextPath%>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=strContextPath%>/dwr/interface/countryService.js"></script>
	<script type="text/javascript" src="<%=strContextPath%>/resources/scripts/masters/country.js"></script>
	<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script> -->
</head>
<body>
<jsp:include page="/web/jsp/common/Header.jsp" />
<div style="height:400px;">
<ul class="breadcrumb">
    <li class="breadcrumb-item"><a href="#">Masters</a></li>
    <li class="breadcrumb-item active">Country</li>
  </ul>
<div class="row" >
  <div class="col-md-2"></div>
  <div  class="col-md-8 ">
    <div class="table-responsive" style="height:300px;">
     <table id="countriesListTable" class="table table-dark table-hover" ></table>
     </div>
  </div>
  <div  class="col-md-2"></div>
 
</div>
</div>
<jsp:include page="/web/jsp/common/Footer.jsp" />

</body>
</html>