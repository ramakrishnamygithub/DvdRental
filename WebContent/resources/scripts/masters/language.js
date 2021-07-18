/**
 * 
 */

$(document).ready(function(){
	showLanguages();
 

});

function showLanguages(){
	languageService.getAllLanguages(
			
	  function(languagesList){
		  $("#languagesListTable").empty();
		  if(languagesList.length>0){
			  var tableHeaderContent="<thead class='thead-light' ><tr><th>Language</th><th>Last Update</th><th>Action</th></tr></thead>";
			  var tableBodyContent="<tbody>";
			  for(var i=0;i<languagesList.length;i++){
				  tableBodyContent+="<tr id="+languagesList[i].languageId+" >" +
				  	                "<td>"+languagesList[i].name+"</td>" +
					  				   "<td>"+(new Date(languagesList[i].lastUpdate)).getDate()+"-"+(new Date(languagesList[i].lastUpdate)).getMonth()+1+"-"+ (new Date(languagesList[i].lastUpdate)).getFullYear()+" "+(new Date(languagesList[i].lastUpdate)).getHours()+":"+(new Date(languagesList[i].lastUpdate)).getMinutes()+":"+(new Date(languagesList[i].lastUpdate)).getSeconds()+"</td>" +

				  				     "<td><img data-toggle='modal' data-target='#myModal' onclick='showDetails(this);'  style='height: 20px;' src='"+strContextPath+"/resources/images/edit-solid.svg'>" +
				  				     "<img data-toggle='modal'  data-target='#myConfirmModal' onclick='confirmDelete(this);' style='height: 20px;margin-left:5px;'  src='"+strContextPath+"/resources/images/trash-alt-regular.svg'></td>" +
				  				 "</tr>";
			  }
			  tableBodyContent+="</tbody>";
			  var tableFootContent="<tfoot><tr><td></td><td></td><td></td></tr></tfoot>";
			  var tableContent=tableHeaderContent+tableBodyContent+tableFootContent;
			  $("#languagesListTable").append(tableContent);
				
		  }
		  
		  
	  }
	  );
}
function showDetails(obj){
	var languageId=$(obj).parents("tr").eq(0).attr("id");
	var language=$(obj).parents("tr").eq(0).children('td').eq(0).text();
	var lastUpdate=$(obj).parents("tr").eq(0).children('td').eq(1).text();	
	$("#modalbody").find("#languageId").val(languageId);
	$("#modalbody").find("#language").val(language);	
	$("#modalbody").find("#lastupdate").val(lastUpdate);	
}
function modifyLanguage(obj){
	var languageId=$("#modalbody").find("#languageId").val();
	var language=$("#modalbody").find("#language").val();	
	var countrObj={languageId:languageId,language:language};
	languageService.updateLanguage(countrObj,{callback:function(resultObj){
		if(resultObj!=null){
		   $("#languagesListTable").find("tr[id='"+resultObj.languageId+"']").children("td").eq(0).text(resultObj.language).children("td").eq(1).text(resultObj.lastUpdate);
		   $("#successDiv").find("#successMsg").text("Successfully modified.")
		   $("#successDiv").removeAttr('class').attr("class","alert alert-success");
		}else{
			$("#errorDiv").find("#errorMsg").text("Unable to modify this language.");
			$("#errorDiv").removeAttr('class').attr("class","alert alert-danger");
		}
		
	},async:true});
	
}
function deleteLanguage(btnObj){
	var languageId=$(btnObj).attr("languageId");
	var obj=$("#languagesListTable").find("tr[id='"+languageId+"']");
	var language=$(obj).children('td').eq(0).text();
	var lastUpdate=$(obj).children('td').eq(1).text();	
	var countrObj={languageId:languageId,language:language};
	languageService.deleteLanguage(countrObj,{callback:function(isDeleted){
		if(isDeleted==true){
			$("#languagesListTable").find("tr[id='"+resultObj.languageId+"']").remove();	
			$("#successDiv").find("#successMsg").text("Successfully deleted.")
			$("#successDiv").removeAttr('class').attr("class","alert alert-success");
		}else{
			$("#errorDiv").find("#errorMsg").text("Unable to delete this language.")
			$("#errorDiv").removeAttr('class').attr("class","alert alert-danger");
		}
		
		
	},async:true});
	
}
function confirmDelete(obj){
	var languageId=$(obj).parents("tr").eq(0).attr("id");
	$("#myConfirmModal").find("#myConfirmModalTitle").text("Are you sure?");
	$("#myConfirmModal").find("#myConfirmModalBody").text("Do you want to delete this Language.");
	$("#myConfirmModal").find("#myConfirmModalFooter").find("#confirmYesBtn").removeAttr("onclick").attr("onclick","deleteLanguage(this);").removeAttr("languageId").attr("languageId",languageId);
}

	
