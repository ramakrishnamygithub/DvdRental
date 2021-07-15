/**
 * 
 */

$(document).ready(function(){
 showCities();
 

});

function showCities(){
	cityService.getAllCities(
			
	  function(citiesList){
		  //useLoadingMessage("<div class='spinner-border text-success'></div>");
		  $("#citiesListTable").empty();
		  if(citiesList.length>0){
			  var tableHeaderContent="<thead class='thead-light' ><tr><th>City</th><th>Country</th><th>Last Update</th><th>Action</th></tr></thead>";
			  var tableBodyContent="<tbody>";
			  for(var i=0;i<citiesList.length;i++){
				  tableBodyContent+="<tr id="+citiesList[i].cityId+" >" +
				  	                "<td>"+citiesList[i].city+"</td>" +
				  	              "<td>"+citiesList[i].country.country+"</td>" +
				  				     "<td>"+citiesList[i].lastUpdate+"</td>" +
				  				     "<td><img data-toggle='modal' data-target='#myModal' onclick='showDetails(this);'  style='height: 20px;' src='"+strContextPath+"/resources/images/edit-solid.svg'>" +
				  				     "<img data-toggle='modal'  data-target='#myConfirmModal' onclick='confirmDelete(this);' style='height: 20px;margin-left:5px;'  src='"+strContextPath+"/resources/images/trash-alt-regular.svg'></td>" +
				  				 "</tr>";
			  }
			  tableBodyContent+="</tbody>";
			  var tableFootContent="<tfoot><tr><td></td><td></td><td></td><td></td></tr></tfoot>";
			  var tableContent=tableHeaderContent+tableBodyContent+tableFootContent;
			  $("#citiesListTable").append(tableContent);
				
		  }
		  
		  
	  }
	  );
}
function showDetails(obj){
	var cityId=$(obj).parents("tr").eq(0).attr("id");
	var city=$(obj).parents("tr").eq(0).children('td').eq(0).text();
	var lastUpdate=$(obj).parents("tr").eq(0).children('td').eq(1).text();	
	console.log(" cityId=="+cityId);
	$("#modalbody").find("#cityId").val(cityId);
	$("#modalbody").find("#city").val(city);	
	$("#modalbody").find("#lastupdate").val(lastUpdate);	
}
function modifyCity(obj){
	var cityId=$("#modalbody").find("#cityId").val();
	var city=$("#modalbody").find("#city").val();	
	var cityObj={cityId:cityId,city:city};
	cityService.updateCity(cityObj,{callback:function(resultObj){
		if(resultObj!=null){
		   $("#citiesListTable").find("tr[id='"+resultObj.cityId+"']").children("td").eq(0).text(resultObj.city).children("td").eq(1).text(resultObj.lastUpdate);
		   $("#successDiv").find("#successMsg").text("Successfully modified.")
		   $("#successDiv").removeAttr('class').attr("class","alert alert-success");
		}else{
			$("#errorDiv").find("#errorMsg").text("Unable to modify this City.");
			$("#errorDiv").removeAttr('class').attr("class","alert alert-danger");
		}
		
	},async:true});
	
}
function deleteCity(btnObj){
	var cityId=$(btnObj).attr("cityId");
	var obj=$("#citiesListTable").find("tr[id='"+cityId+"']");
	var city=$(obj).children('td').eq(0).text();
	var lastUpdate=$(obj).children('td').eq(1).text();	
	var cityObj={cityId:cityId,city:city};
	cityService.deleteCity(cityObj,{callback:function(isDeleted){
		if(isDeleted==true){
			$("#citiesListTable").find("tr[id='"+resultObj.cityId+"']").remove();	
			$("#successDiv").find("#successMsg").text("Successfully deleted.")
			$("#successDiv").removeAttr('class').attr("class","alert alert-success");
		}else{
			$("#errorDiv").find("#errorMsg").text("Unable to delete this City.")
			$("#errorDiv").removeAttr('class').attr("class","alert alert-danger");
		}
		
		
	},async:true});
	
}
function confirmDelete(obj){
	var cityId=$(obj).parents("tr").eq(0).attr("id");
	$("#myConfirmModal").find("#myConfirmModalTitle").text("Are you sure?");
	$("#myConfirmModal").find("#myConfirmModalBody").text("Do you want to delete this City.");
	$("#myConfirmModal").find("#myConfirmModalFooter").find("#confirmYesBtn").removeAttr("onclick").attr("onclick","deleteCity(this);").removeAttr("cityId").attr("cityId",cityId);
}

	
