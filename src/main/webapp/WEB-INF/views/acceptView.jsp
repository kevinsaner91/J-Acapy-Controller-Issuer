<html>
   <head>
      <meta charset="UTF-8">
      <title>Connection</title>
      <link rel="stylesheet" href="main.css">
   </head>
   <body>
      <jsp:include page="_header.jsp"></jsp:include>
      <br>
      <br>
      <br>
      <jsp:include page="_menu_connection.jsp"></jsp:include>

      <h3>Accept Connection</h3>
      <p style="color: red;">${errorString}</p>


      <form method="POST" action="${pageContext.request.contextPath}/accept">
         <table border="0">
            <tr>
               <td>Connection String</td>
               <td><input type="text" name="connectionString" value= "${accept.connectionString}" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <a href="${pageContext.request.contextPath}/">Cancel</a>
               </td>
            </tr>
         </table>
      </form>

      <jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>
 