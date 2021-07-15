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
	src="<%=strContextPath%>/dwr/interface/cityService.js"></script>
<script type="text/javascript"
	src="<%=strContextPath%>/resources/scripts/masters/city.js"></script>
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
  
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script> -->
<script>
var strContextPath ="<%=strContextPath%>";

	console.log("strContextPath===" + strContextPath);
	function init() {
		  dwr.util.useLoadingMessage();
		}

		if (window.addEventListener) {
		  window.addEventListener("load", init, false);
		}
		else if (window.attachEvent) {
		 window.attachEvent("onload", init);
		}
		else {
		  window.onload = init;
		}
		function useLoadingMessage(message) {
			console.log('useLoadingMessage');
			  var loadingMessage;
			  if (message) loadingMessage = "ramakrishna";
			  else loadingMessage = "ramakrishna";

			  dwr.engine.setPreHook(function() {
			    var disabledZone = $('disabledZone');
			    if (!disabledZone) {
			      disabledZone = document.createElement('div');
			      disabledZone.setAttribute('id', 'disabledZone');
			      disabledZone.style.position = "absolute";
			      disabledZone.style.zIndex = "1000";
			      disabledZone.style.left = "0px";
			      disabledZone.style.top = "0px";
			      disabledZone.style.width = "100%";
			      disabledZone.style.height = "100%";
			      document.body.appendChild(disabledZone);
			      var messageZone = document.createElement('div');
			      messageZone.setAttribute('id', 'messageZone');
			      messageZone.style.position = "absolute";
			      messageZone.style.top = "100px";
			      messageZone.style.right = "100px";
			      messageZone.style.background = "red";
			      messageZone.style.color = "white";
			      messageZone.style.fontFamily = "Arial,Helvetica,sans-serif";
			      messageZone.style.padding = "4px";
			      disabledZone.appendChild(messageZone);
			      var text = document.createTextNode(loadingMessage);
			      messageZone.appendChild(text);
			    }
			    else {
			      $('messageZone').innerHTML = loadingMessage;
			      disabledZone.style.visibility = 'visible';
			    }
			  });

			  dwr.engine.setPostHook(function() {
			    $('disabledZone').style.visibility = 'hidden';
			  });
			}
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
			<li class="breadcrumb-item active">City</li>
		</ul>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 ">
				<div class="table-responsive" style="height: 300px;">
					<table id="citiesListTable"
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
												<input id="cityId" type="hidden" value="" /> <label
													for="city">City:</label> <input type="text"
													class="form-control" id="city">
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
											data-dismiss="modal" onclick="modifyCity(this);">Save</button>
										<button type="button" class="btn btn-danger"
											data-dismiss="modal">Close</button>
									</div>

								</div>
							</div>
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