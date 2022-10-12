<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Issue Credential</title>
     <link rel="stylesheet" href="main.css">
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
      <br>
      <br>
      <br>    
    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>Issue Credential</h3>

    
	<form method="POST" action="${pageContext.request.contextPath}/select">
    <table border="0" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Connection</th>
          <th>Schema</th>
          <th>Credential Definition</th>
       </tr>
          <tr>
			<td>
				<select name="connections">
					<c:forEach items="${connectionList}" var="connections">
						<option value="${connections.connection_id}">${connections.connection_id}: ${connections.their_label} </option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="schemas">
					<c:forEach items="${schemaList}" var="schemas">
						<option value="${schemas.schemaId}">${schemas.schemaId}: ${schemas.schemaName} </option>
					</c:forEach>
				</select>
			</td>
			<td>
             	<select name="creddef">
					<c:forEach items="${creddefList}" var="creddef">
						<option value="${creddef.id}">${creddef.id} </option>
					</c:forEach>
				</select>
             </td>
             <td>
             	<input type="submit" value= "Select"/>
             </td>
          </tr> 
          </table> 
    <br>

    <jsp:include page="_footer.jsp"></jsp:include>
    

 </body>
 
</html>	