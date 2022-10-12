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
       
    </table>
    <br>
      <br> 
      <h3>Issue Credential</h3>
      
      
      <form method="POST" action="${pageContext.request.contextPath}/issueCredential">
      <table border="0" cellpadding="1" cellspacing="1" >
       <tr>
          <th>Connection ID</th>
          <th>Schema</th>
          <th>Credential Definition</th>
       </tr>
          <tr>
       		<c:forEach items="${selection}" var="selected" >
          <tr>
             <td><input type="text" value="${selected.connection}" name="connection" readonly style="width: 300px;"></td>
             <td><input type="text" value="${selected.schema}" name="schema" readonly style="width: 300px;"></td>
             <td><input type="text" value="${selected.creddef}" name="creddef" readonly style="width: 300px;"></td>           
          </tr>
       </c:forEach>
          </tr>

    </table>   

      <p style="color: red;">${errorString}</p>
      
     
     <table border="0" cellpadding="1" cellspacing="1" >
       <tr>
          <th>Attribute</th>
          <th>Value</th>
       </tr>
          <tr>
       		<c:forEach items="${attributes}" var="attribute" >
          <tr>
             <td><input type="text" value="${attribute.key}" name="key" readonly style="width: 300px;"></td>
             <td><input type="text" value="${value}" name="value"  style="width: 300px;"></td>
          </tr>
       </c:forEach>
          </tr>
    </table>

		<br>
		<table border="0" cellpadding="5" cellspacing="1">
			<tr>
				<td><input type="submit" value="Issue Credential" /></td>
				<td><a href="${pageContext.request.contextPath}/issueCredential">Cancel</a></td>
			</tr>
		</table>

		<jsp:include page="_footer.jsp"></jsp:include>
    </form>

 </body>
 
</html>	