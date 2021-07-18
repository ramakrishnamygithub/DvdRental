/**
 * 
 */

$(document).ready(function(){
 showAddresses();
 

});

function showAddresses(){
	addressService.getAllAddresses(
			
	  function(addressesList){
		  debugger
		  //useLoadingMessage("<div class='spinner-border text-success'></div>");
		  $("#addressesListTable").empty();
		  if(addressesList.length>0){
			  var tableHeaderContent="<thead class='thead-light' ><tr><th>Address</th><th>District</th><th>City</th><th>Country</th><th>Phone</th><th>Last Update</th><th>Action</th></tr></thead>";
			  var tableBodyContent="<tbody>";
			  for(var i=0;i<addressesList.length;i++){
				  tableBodyContent+="<tr id="+addressesList[i].addressId+" >" +
				  	                "<td>"+addressesList[i].address+"</td>" +
				  	            
				  	            "<td>"+addressesList[i].district+"</td>" +
				  	              "<td>"+addressesList[i].city.city+"</td>" +
				  	            "<td>"+addressesList[i].city.country.country+"</td>" +
				  	          
				  	        "<td>"+addressesList[i].phone+"</td>" +
				  			"<td>"+(new Date(addressesList[i].lastUpdate)).getDate()+"-"+(new Date(addressesList[i].lastUpdate)).getMonth()+1+"-"+ (new Date(addressesList[i].lastUpdate)).getFullYear()+" "+(new Date(addressesList[i].lastUpdate)).getHours()+":"+(new Date(addressesList[i].lastUpdate)).getMinutes()+":"+(new Date(addressesList[i].lastUpdate)).getSeconds()+"</td>" +
				  				     "<td><img data-toggle='modal' data-target='#myModal' onclick='showDetails(this);'  style='height: 20px;' src='"+strContextPath+"/resources/images/edit-solid.svg'>" +
				  				     "<img data-toggle='modal'  data-target='#myConfirmModal' onclick='confirmDelete(this);' style='height: 20px;margin-left:5px;'  src='"+strContextPath+"/resources/images/trash-alt-regular.svg'></td>" +
				  				 "</tr>";
			  }
			  tableBodyContent+="</tbody>";
			  var tableFootContent="<tfoot><tr><td></td><td></td><td></td><td></td><td></td><td></td></tr><td></tr></tfoot>";
			  var tableContent=tableHeaderContent+tableBodyContent+tableFootContent;
			  $("#addressesListTable").append(tableContent);
				
		  }
		  
		  
	  }
	  );
}
function showDetails(obj){
	var addressId=$(obj).parents("tr").eq(0).attr("id");
	var address=$(obj).parents("tr").eq(0).children('td').eq(0).text();
	var lastUpdate=$(obj).parents("tr").eq(0).children('td').eq(1).text();	
	console.log(" addressId=="+addressId);
	$("#modalbody").find("#addressId").val(addressId);
	$("#modalbody").find("#address").val(address);	
	$("#modalbody").find("#lastupdate").val(lastUpdate);	
}
function modifyAddress(obj){
	var addressId=$("#modalbody").find("#addressId").val();
	var address=$("#modalbody").find("#address").val();	
	var addressObj={addressId:addressId,address:address};
	addressService.updateAddress(addressObj,{callback:function(resultObj){
		if(resultObj!=null){
		   $("#addressesListTable").find("tr[id='"+resultObj.addressId+"']").children("td").eq(0).text(resultObj.address).children("td").eq(1).text(resultObj.lastUpdate);
		   $("#successDiv").find("#successMsg").text("Successfully modified.")
		   $("#successDiv").removeAttr('class').attr("class","alert alert-success");
		}else{
			$("#errorDiv").find("#errorMsg").text("Unable to modify this Address.");
			$("#errorDiv").removeAttr('class').attr("class","alert alert-danger");
		}
		
	},async:true});
	
}
function deleteAddress(btnObj){
	var addressId=$(btnObj).attr("cityId");
	var obj=$("#citiesListTable").find("tr[id='"+cityId+"']");
	var address=$(obj).children('td').eq(0).text();
	var lastUpdate=$(obj).children('td').eq(1).text();	
	var addressObj={addressId:addressId,address:address};
	addressService.deleteAddress(addressObj,{callback:function(isDeleted){
		if(isDeleted==true){
			$("#addressesListTable").find("tr[id='"+resultObj.addressId+"']").remove();	
			$("#successDiv").find("#successMsg").text("Successfully deleted.")
			$("#successDiv").removeAttr('class').attr("class","alert alert-success");
		}else{
			$("#errorDiv").find("#errorMsg").text("Unable to delete this Address.")
			$("#errorDiv").removeAttr('class').attr("class","alert alert-danger");
		}
		
		
	},async:true});
	
}
function confirmDelete(obj){
	var addressId=$(obj).parents("tr").eq(0).attr("id");
	$("#myConfirmModal").find("#myConfirmModalTitle").text("Are you sure?");
	$("#myConfirmModal").find("#myConfirmModalBody").text("Do you want to delete this Address.");
	$("#myConfirmModal").find("#myConfirmModalFooter").find("#confirmYesBtn").removeAttr("onclick").attr("onclick","deleteAddress(this);").removeAttr("addressId").attr("addressId",addressId);
}

	
