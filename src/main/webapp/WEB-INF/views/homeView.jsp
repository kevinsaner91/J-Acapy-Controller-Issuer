<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
     <meta charset="UTF-8">
     <title>Home Page</title>
     <link rel="stylesheet" href="main.css">
  </head>
  <body>

     <jsp:include page="_header.jsp"></jsp:include>
     <br>
     <br>
     <br>
     <jsp:include page="_menu.jsp"></jsp:include>
   
      <h3>Home Page</h3>
     
      This is demo Simple web application to control a ACAPY-Agent using jsp,servlet &amp; Jdbc. <br><br>
      <b>It includes the following functions:</b>
      <ul>
         <li>Login</li>
         <li>Connections</li>
         <li>Schema</li>
         <li>Credential Definition</li>
         <li>Issue Credential</li>
      </ul>

     <jsp:include page="_footer.jsp"></jsp:include>

  </body>
</html>