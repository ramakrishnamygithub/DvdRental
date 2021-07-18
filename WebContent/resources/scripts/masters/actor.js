/**
 * 
 */

$(document).ready(function(){
	showActors();
 

});

function showActors(){
	actorService.getAllActors(
			
	  function(actorsList){
		  $("#actorsListTable").empty();
		  if(actorsList.length>0){
			  var tableHeaderContent="<thead class='thead-light' ><tr><th>First Name</th><th>Last Name</th><th>Last Update</th><th>Action</th></tr></thead>";
			  var tableBodyContent="<tbody>";
			  for(var i=0;i<actorsList.length;i++){
				  tableBodyContent+="<tr id="+actorsList[i].actorId+" >" +
				  	                "<td>"+actorsList[i].firstName+"</td>" +
				  	              "<td>"+actorsList[i].lastName+"</td>" +
					  				   "<td>"+(new Date(actorsList[i].lastUpdate)).getDate()+"-"+(new Date(actorsList[i].lastUpdate)).getMonth()+1+"-"+ (new Date(actorsList[i].lastUpdate)).getFullYear()+" "+(new Date(actorsList[i].lastUpdate)).getHours()+":"+(new Date(actorsList[i].lastUpdate)).getMinutes()+":"+(new Date(actorsList[i].lastUpdate)).getSeconds()+"</td>" +

				  				     "<td><img data-toggle='modal' data-target='#myModal' onclick='showDetails(this);'  style='height: 20px;' src='"+strContextPath+"/resources/images/edit-solid.svg'>" +
				  				     "<img data-toggle='modal'  data-target='#myConfirmModal' onclick='confirmDelete(this);' style='height: 20px;margin-left:5px;'  src='"+strContextPath+"/resources/images/trash-alt-regular.svg'></td>" +
				  				 "</tr>";
			  }
			  tableBodyContent+="</tbody>";
			  var tableFootContent="<tfoot><tr><td></td><td></td><td></td><td></td></tr></tfoot>";
			  var tableContent=tableHeaderContent+tableBodyContent+tableFootContent;
			  $("#actorsListTable").append(tableContent);
				
		  }
		  
		  
	  }
	  );
}
function showDetails(obj){
	var actorId=$(obj).parents("tr").eq(0).attr("id");
	var actor=$(obj).parents("tr").eq(0).children('td').eq(0).text();
	var lastUpdate=$(obj).parents("tr").eq(0).children('td').eq(1).text();	
	$("#modalbody").find("#actorId").val(actorId);
	$("#modalbody").find("#actor").val(actor);	
	$("#modalbody").find("#lastupdate").val(lastUpdate);	
}
function modifyActor(obj){
	var actorId=$("#modalbody").find("#actorId").val();
	var actor=$("#modalbody").find("#actor").val();	
	var actorObj={actorId:actorId,actor:actor};
	actorService.updateActor(actorObj,{callback:function(resultObj){
		if(resultObj!=null){
		   $("#actorsListTable").find("tr[id='"+resultObj.actorId+"']").children("td").eq(0).text(resultObj.actor).children("td").eq(1).text(resultObj.lastUpdate);
		   $("#successDiv").find("#successMsg").text("Successfully modified.")
		   $("#successDiv").removeAttr('class').attr("class","alert alert-success");
		}else{
			$("#errorDiv").find("#errorMsg").text("Unable to modify this actor.");
			$("#errorDiv").removeAttr('class').attr("class","alert alert-danger");
		}
		
	},async:true});
	
}
function deleteActor(btnObj){
	var actorId=$(btnObj).attr("actorId");
	var obj=$("#actorsListTable").find("tr[id='"+actorId+"']");
	var actor=$(obj).children('td').eq(0).text();
	var lastUpdate=$(obj).children('td').eq(1).text();	
	var countrObj={actorId:actorId,actor:actor};
	actorService.deleteActor(countrObj,{callback:function(isDeleted){
		if(isDeleted==true){
			$("#actorsListTable").find("tr[id='"+resultObj.actorId+"']").remove();	
			$("#successDiv").find("#successMsg").text("Successfully deleted.")
			$("#successDiv").removeAttr('class').attr("class","alert alert-success");
		}else{
			$("#errorDiv").find("#errorMsg").text("Unable to delete this actor.")
			$("#errorDiv").removeAttr('class').attr("class","alert alert-danger");
		}
		
		
	},async:true});
	
}
function confirmDelete(obj){
	var actorId=$(obj).parents("tr").eq(0).attr("id");
	$("#myConfirmModal").find("#myConfirmModalTitle").text("Are you sure?");
	$("#myConfirmModal").find("#myConfirmModalBody").text("Do you want to delete this Actor.");
	$("#myConfirmModal").find("#myConfirmModalFooter").find("#confirmYesBtn").removeAttr("onclick").attr("onclick","deleteActor(this);").removeAttr("actorId").attr("actorId",actorId);
}

	
