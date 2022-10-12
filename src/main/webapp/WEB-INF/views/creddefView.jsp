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
          <th>Version</th>
          <th>Tag</th>
       </tr>
       <c:forEach items="${creddefList}" var="result" >
          <tr>
             <td>${result.id}</td>
             <td>${result.version}</td>
             <td>${result.tag}</td>
          </tr>
       </c:forEach>
    </table>
    <br>
      <br> 
      <h3>New Credential Definition</h3>

      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/creddef">
     <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>
          	<div class="tooltip"> Schema ID
  				<span class="tooltiptext">Copy a the schema ID of an already created schema into the text field</span>
			</div>
		 </th>
		 <th>
          	<div class="tooltip"> Tag
  				<span class="tooltiptext">Use the tag "default"</span>
			</div>
		 </th>
       </tr>

          <tr>
             <td>
             	<input type="text" value="${schemaId}" name="schemaId"  style="width: 500px;">
             </td>
             <td>
             	<input type="text" value="${tag}" name="tag"  style="width: 500px;">
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