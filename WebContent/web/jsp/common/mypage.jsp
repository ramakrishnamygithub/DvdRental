<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.servletsuite.com/servlets/waittag" prefix="w" %>
    
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Page</title>

<%
String strContextPath=request.getContextPath();
System.out.println("came to Mypage.jsp");

%>
 <link rel="icon" href="<%=strContextPath %>/resources/images/Favcon.png" type="image/png" sizes="16x16">

<style type="text/css">
#infoPage{position:absolute;top:50%;left:50%;height:50px;}
#realPage{position:relative !important}

</style>
</head>
<body>
  <w:wait>
   <w:infoPage>
     <div style="border:1pz solid #eOeOeO; width:300px;padding:10px;background-color:#f8f8f8;position:relative:top:-50%;left:-50%">
       <span style="color:#3399CC">Loading Dvd Rental</span>
     </div>
   </w:infoPage>
   <w:realPage>
     <jsp:include page="DashBoard.jsp"/>
   </w:realPage>
  </w:wait>
</body>
</html>