/**
 * 
 */

$(document).ready(function(){
 showCountries();
});

function showCountries(){
	countryService.getAllCountries(
			
	  function(countriesList){
		  debugger
		  $("#countriesListTable").empty();
		  if(countriesList.length>0){
			  var tableHeaderContent="<thead ><tr><th>Country</th><th>Last Update</th><th>Action</th></tr></thead>";
			  var tableBodyContent="<tbody>";
			  for(var i=0;i<countriesList.length;i++){
				  tableBodyContent+="<tr id="+countriesList[i].countryId+" ><td>"+countriesList[i].country+"</td><td>"+countriesList[i].lastUpdate+"</td><td></td></tr>";
			  }
			  tableBodyContent+="</tbody>";
			  var tableFootContent="<tfoot><tr><td></td><td></td><td></td></tr></tfoot>";
			  var tableContent=tableHeaderContent+tableBodyContent+tableFootContent;
			  $("#countriesListTable").append(tableContent);
				
		  }
		  
		  
	  }
	  );
}