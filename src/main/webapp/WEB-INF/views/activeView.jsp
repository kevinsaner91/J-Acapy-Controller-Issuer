<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Acitve Connections</title>
     <link rel="stylesheet" href="main.css">
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
      <br>
      <br>
      <br>    
    <jsp:include page="_menu_connection.jsp"></jsp:include>

    <h3>Active Connections</h3>

    <p style="color: red;">${errorString}</p>

    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Label</th>
          <th>My DID</th>
          <th>Their DID</th>
          <th>Created_At</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${connectionList}" var="result" >
          <tr>
             <td>${result.their_label}</td>
             <td>${result.my_did}</td>
             <td>${result.their_did}</td>
             <td>${result.created_at}</td>
             <td>
                <a href="delete?connection_id=${result.connection_id}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>

    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
</html>	