<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Credential Definition</title>
     <link rel="stylesheet" href="main.css">
 </head>
 <body>

    <jsp:include page="_header.jsp"></jsp:include>
      <br>
      <br>
      <br>    
    <jsp:include page="_menu.jsp"></jsp:include>

    <h3>Available Credential Definitions</h3>

    

    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Credential Definition ID</th>
          <th>Schema Version</th>
       </tr>
       <c:forEach items="${schemaList}" var="result" >
          <tr>
             <td>${result.creddefId}</td>
             <td>${result.creddefVersion}</td>
          </tr>
       </c:forEach>
    </table>
    <br>
      <br> 
      <h3>New Credential Definition</h3>

      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/schema">
     <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>
          	<div class="tooltip"> Schema ID
  				<span class="tooltiptext">Copy a the schema ID of an already created into the text field</span>
			</div>
		 </th>
       </tr>

          <tr>
             <td>
             	<input type="text" value="${schemaId}" name="schemaId"  style="width: 500px;">
             </td>
             <td>
             	<input type="submit" value= "Submit"/>
             </td>
          </tr>

    </table>   
    

    <jsp:include page="_footer.jsp"></jsp:include>

 </body>
 
 <style>
.tooltip {
  position: relative;
  display: inline-block;
  border-bottom: 1px dotted black;
}

.tooltip .tooltiptext {
  visibility: hidden;
  width: 220px;
  background-color: #555;
  color: #fff;
  text-align: center;
  border-radius: 6px;
  padding: 5px 0;
  position: absolute;
  z-index: 1;
  bottom: 125%;
  left: 50%;
  margin-left: -60px;
  opacity: 0;
  transition: opacity 0.3s;
}

.tooltip .tooltiptext::after {
  content: "";
  position: absolute;
  top: 100%;
  left: 50%;
  margin-left: -5px;
  border-width: 5px;
  border-style: solid;
  border-color: #555 transparent transparent transparent;
}

.tooltip:hover .tooltiptext {
  visibility: visible;
  opacity: 1;
}
</style>
</html>	