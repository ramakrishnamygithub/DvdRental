<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DvdRental</title>
 <% 
 String strContexPath=request.getContextPath();
 System.out.println("came to dvdrentalindex");
 System.out.println("strContexPath== "+strContexPath);
 %>
 <link rel="icon" href="<%=strContexPath %>/resources/images/Favcon.png" type="image/png" sizes="16x16">
 
</head>
 <frameset row="99.9%,0.1%" frameborder="no" border="0" framespacing="0">
 
  <frame src="<%=strContexPath %>/web/jsp/common/mypage.jsp" name="contentFrame" border="0" scrolling="auto"
  noresize="noresize" id="contentFrame"/>
  <frame src="<%=strContexPath %>/web/jsp/common/sessionhandler.jsp" name="sessionFrame" border="0" scrolling="No" 
  noresize="noresize" id="sessionFrame"/>
 </frameset>
 <noframes></noframes>

<body>

</body>
</html>