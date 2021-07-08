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
<script type="text/javascript"
	src="<%=strContextPath%>/dwr/interface/countryService.js"></script>
<script type="text/javascript"
	src="<%=strContextPath%>/resources/scripts/masters/country.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script> -->
<script>
var strContextPath ="<%=strContextPath%>";

	console.log("strContextPath===" + strContextPath);
</script>
<style>
#modalheader {
	background-color: hsl(200, 65%, 20%);
	height: 70px;
}
</style>
</head>
<body>


	<jsp:include page="/web/jsp/common/Header.jsp" />
	<div style="height: 400px;">
		<ul class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">Masters</a></li>
			<li class="breadcrumb-item active">Country</li>
		</ul>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 ">
				<div class="table-responsive" style="height: 300px;">
					<table id="countriesListTable"
						class="table table-bordered  table-hover"></table>
					<div id="hiddencontent">
						<!-- The Modal -->
						<div class="modal fade" id="myModal">
							<div class="modal-dialog  modal-dialog-centered modal-lg"
								style="margin-left: 30%; width: 40%;">
								<div class="modal-content">

									<!-- Modal Header -->
									<div id="modalheader" class="modal-header">
										<h4 id="modaltitle" class="modal-title text-white">Modify</h4>
										<button type="button" class="close text-white"
											data-dismiss="modal">&times;</button>
									</div>

									<!-- Modal body -->
									<div class="modal-body">
										<div id="modalbody">
											<div class="form-group">
												<input id="countryId" type="hidden" value="" /> <label
													for="country">Country:</label> <input type="text"
													class="form-control" id="country">
											</div>
											<div class="form-group">
												<label for="lastupdate">Last update:</label> <input
													type="text" class="form-control" id="lastupdate"
													disabled="disabled">
											</div>


										</div>

									</div>

									<!-- Modal footer -->
									<div class="modal-footer">
										<button type="button" class="btn btn-primary"
											data-dismiss="modal" onclick="modifyCountry(this);">Save</button>
										<button type="button" class="btn btn-danger"
											data-dismiss="modal">Close</button>
									</div>

								</div>
							</div>
						</div>
						<div id="successDiv" style="display: none;">
							<strong>Success!</strong><span id="successMsg"> </span>
						</div>
						<div id="errorDiv">
							<strong>Oops!</strong><span id="errorMsg"> </span>
						</div>
						<!-- msg box -->
						<div id="confirmBox">
							<!-- The Modal -->
							<div class="modal fade" id="myConfirmModal">
								<div class="modal-dialog modal-sm">
									<div class="modal-content">

										<!-- Modal Header -->
										<div class="modal-header">
											<h4 id="myConfirmModalTitle" class="modal-title"></h4>
											<button type="button" class="close" data-dismiss="modal">&times;</button>
										</div>

										<!-- Modal body -->
										<div id="myConfirmModalBody" class="modal-body">
										
										
										</div>

										<!-- Modal footer -->
										<div id="myConfirmModalFooter" class="modal-footer">
											<button id="confirmYesBtn" type="button" class="btn btn-primary"
												data-dismiss="modal" >Yes</button>
											<button id="confirmNoBtn" type="button" class="btn btn-secondary"
												data-dismiss="modal">No</button>
										</div>

									</div>
								</div>
							</div>
						</div>
						<!-- end -->
					</div>
				</div>
			</div>
			<div class="col-md-2"></div>

		</div>
	</div>
	<jsp:include page="/web/jsp/common/Footer.jsp" />

</body>
</html>