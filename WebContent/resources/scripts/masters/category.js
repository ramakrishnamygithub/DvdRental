/**
 * 
 */

$(document).ready(function(){
 showCategories();
 

});

function showCategories(){
	categoryService.getAllCategories(
			
	  function(categoriesList){
		  $("#categoriesListTable").empty();
		  if(categoriesList.length>0){
			  var tableHeaderContent="<thead class='thead-light' ><tr><th>Category</th><th>Last Update</th><th>Action</th></tr></thead>";
			  var tableBodyContent="<tbody>";
			  for(var i=0;i<categoriesList.length;i++){
				  tableBodyContent+="<tr id="+categoriesList[i].categoryId+" >" +
				  	                "<td>"+categoriesList[i].name+"</td>" +
					  				   "<td>"+(new Date(categoriesList[i].lastUpdate)).getDate()+"-"+(new Date(categoriesList[i].lastUpdate)).getMonth()+1+"-"+ (new Date(categoriesList[i].lastUpdate)).getFullYear()+" "+(new Date(categoriesList[i].lastUpdate)).getHours()+":"+(new Date(categoriesList[i].lastUpdate)).getMinutes()+":"+(new Date(categoriesList[i].lastUpdate)).getSeconds()+"</td>" +

				  				     "<td><img data-toggle='modal' data-target='#myModal' onclick='showDetails(this);'  style='height: 20px;' src='"+strContextPath+"/resources/images/edit-solid.svg'>" +
				  				     "<img data-toggle='modal'  data-target='#myConfirmModal' onclick='confirmDelete(this);' style='height: 20px;margin-left:5px;'  src='"+strContextPath+"/resources/images/trash-alt-regular.svg'></td>" +
				  				 "</tr>";
			  }
			  tableBodyContent+="</tbody>";
			  var tableFootContent="<tfoot><tr><td></td><td></td><td></td></tr></tfoot>";
			  var tableContent=tableHeaderContent+tableBodyContent+tableFootContent;
			  $("#categoriesListTable").append(tableContent);
				
		  }
		  
		  
	  }
	  );
}
function showDetails(obj){
	var categooryId=$(obj).parents("tr").eq(0).attr("id");
	var category=$(obj).parents("tr").eq(0).children('td').eq(0).text();
	var lastUpdate=$(obj).parents("tr").eq(0).children('td').eq(1).text();	
	$("#modalbody").find("#countryId").val(categooryId);
	$("#modalbody").find("#country").val(category);	
	$("#modalbody").find("#lastupdate").val(lastUpdate);	
}
function modifyCountry(obj){
	var countryId=$("#modalbody").find("#categoryId").val();
	var country=$("#modalbody").find("#category").val();	
	var countrObj={countryId:countryId,country:country};
	countryService.updateCountry(countrObj,{callback:function(resultObj){
		if(resultObj!=null){
		   $("#categoriesListTable").find("tr[id='"+resultObj.categoryId+"']").children("td").eq(0).text(resultObj.name).children("td").eq(1).text(resultObj.lastUpdate);
		   $("#successDiv").find("#successMsg").text("Successfully modified.")
		   $("#successDiv").removeAttr('class').attr("class","alert alert-success");
		}else{
			$("#errorDiv").find("#errorMsg").text("Unable to modify this Category.");
			$("#errorDiv").removeAttr('class').attr("class","alert alert-danger");
		}
		
	},async:true});
	
}
function deleteCategory(btnObj){
	var countryId=$(btnObj).attr("categoryId");
	var obj=$("#categoriesListTable").find("tr[id='"+categoryId+"']");
	var category=$(obj).children('td').eq(0).text();
	var lastUpdate=$(obj).children('td').eq(1).text();	
	var categoryObj={categoryId:categoryId,category:category};
	countryService.deleteCountry(countrObj,{callback:function(isDeleted){
		if(isDeleted==true){
			$("#categoriesListTable").find("tr[id='"+resultObj.categoryId+"']").remove();	
			$("#successDiv").find("#successMsg").text("Successfully deleted.")
			$("#successDiv").removeAttr('class').attr("class","alert alert-success");
		}else{
			$("#errorDiv").find("#errorMsg").text("Unable to delete this Category.");
			$("#errorDiv").removeAttr('class').attr("class","alert alert-danger");
		}
		
		
	},async:true});
	
}
function confirmDelete(obj){
	var categoryId=$(obj).parents("tr").eq(0).attr("id");
	$("#myConfirmModal").find("#myConfirmModalTitle").text("Are you sure?");
	$("#myConfirmModal").find("#myConfirmModalBody").text("Do you want to delete this Category.");
	$("#myConfirmModal").find("#myConfirmModalFooter").find("#confirmYesBtn").removeAttr("onclick").attr("onclick","deleteCategory(this);").removeAttr("categoryId").attr("categoryId",categoryId);
}

	
