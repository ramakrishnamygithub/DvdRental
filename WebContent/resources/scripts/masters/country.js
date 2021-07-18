/**
 * 
 */

$(document).ready(function(){
 showCountries();
 

});

function showCountries(){
	countryService.getAllCountries(
			
	  function(countriesList){
		  $("#countriesListTable").empty();
		  if(countriesList.length>0){
			  var tableHeaderContent="<thead class='thead-light' ><tr><th>Country</th><th>Last Update</th><th>Action</th></tr></thead>";
			  var tableBodyContent="<tbody>";
			  for(var i=0;i<countriesList.length;i++){
				  tableBodyContent+="<tr id="+countriesList[i].countryId+" >" +
				  	                "<td>"+countriesList[i].country+"</td>" +
					  				   "<td>"+(new Date(countriesList[i].lastUpdate)).getDate()+"-"+(new Date(countriesList[i].lastUpdate)).getMonth()+1+"-"+ (new Date(countriesList[i].lastUpdate)).getFullYear()+" "+(new Date(countriesList[i].lastUpdate)).getHours()+":"+(new Date(countriesList[i].lastUpdate)).getMinutes()+":"+(new Date(countriesList[i].lastUpdate)).getSeconds()+"</td>" +

				  				     "<td><img data-toggle='modal' data-target='#myModal' onclick='showDetails(this);'  style='height: 20px;' src='"+strContextPath+"/resources/images/edit-solid.svg'>" +
				  				     "<img data-toggle='modal'  data-target='#myConfirmModal' onclick='confirmDelete(this);' style='height: 20px;margin-left:5px;'  src='"+strContextPath+"/resources/images/trash-alt-regular.svg'></td>" +
				  				 "</tr>";
			  }
			  tableBodyContent+="</tbody>";
			  var tableFootContent="<tfoot><tr><td></td><td></td><td></td></tr></tfoot>";
			  var tableContent=tableHeaderContent+tableBodyContent+tableFootContent;
			  $("#countriesListTable").append(tableContent);
				
		  }
		  
		  
	  }
	  );
}
function showDetails(obj){
	var countryId=$(obj).parents("tr").eq(0).attr("id");
	var country=$(obj).parents("tr").eq(0).children('td').eq(0).text();
	var lastUpdate=$(obj).parents("tr").eq(0).children('td').eq(1).text();	
	$("#modalbody").find("#countryId").val(countryId);
	$("#modalbody").find("#country").val(country);	
	$("#modalbody").find("#lastupdate").val(lastUpdate);	
}
function modifyCountry(obj){
	var countryId=$("#modalbody").find("#countryId").val();
	var country=$("#modalbody").find("#country").val();	
	var countrObj={countryId:countryId,country:country};
	countryService.updateCountry(countrObj,{callback:function(resultObj){
		if(resultObj!=null){
		   $("#countriesListTable").find("tr[id='"+resultObj.countryId+"']").children("td").eq(0).text(resultObj.country).children("td").eq(1).text(resultObj.lastUpdate);
		   $("#successDiv").find("#successMsg").text("Successfully modified.")
		   $("#successDiv").removeAttr('class').attr("class","alert alert-success");
		}else{
			$("#errorDiv").find("#errorMsg").text("Unable to modify this country.");
			$("#errorDiv").removeAttr('class').attr("class","alert alert-danger");
		}
		
	},async:true});
	
}
function deleteCountry(btnObj){
	var countryId=$(btnObj).attr("countryId");
	var obj=$("#countriesListTable").find("tr[id='"+countryId+"']");
	var country=$(obj).children('td').eq(0).text();
	var lastUpdate=$(obj).children('td').eq(1).text();	
	var countrObj={countryId:countryId,country:country};
	countryService.deleteCountry(countrObj,{callback:function(isDeleted){
		if(isDeleted==true){
			$("#countriesListTable").find("tr[id='"+resultObj.countryId+"']").remove();	
			$("#successDiv").find("#successMsg").text("Successfully deleted.")
			$("#successDiv").removeAttr('class').attr("class","alert alert-success");
		}else{
			$("#errorDiv").find("#errorMsg").text("Unable to delete this country.")
			$("#errorDiv").removeAttr('class').attr("class","alert alert-danger");
		}
		
		
	},async:true});
	
}
function confirmDelete(obj){
	var countryId=$(obj).parents("tr").eq(0).attr("id");
	$("#myConfirmModal").find("#myConfirmModalTitle").text("Are you sure?");
	$("#myConfirmModal").find("#myConfirmModalBody").text("Do you want to delete this Country.");
	$("#myConfirmModal").find("#myConfirmModalFooter").find("#confirmYesBtn").removeAttr("onclick").attr("onclick","deleteCountry(this);").removeAttr("countryId").attr("countryId",countryId);
}

	
