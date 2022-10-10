<html>
   <head>
      <meta charset="UTF-8">
      <title>Login</title>
      <link rel="stylesheet" href="main.css">
      <style>
		.center {
  		display: block;
  		margin-left: auto;
  		margin-right: auto;
		}
</style>
   </head>
   <body>
      <jsp:include page="_header.jsp"></jsp:include>
      <br>
      <br>
      <br>
      <jsp:include page="_menu_connection.jsp"></jsp:include>

      <h3>New Connection</h3>
      <p style="color: red;">${errorString}</p>


      <form method="POST" action="${pageContext.request.contextPath}/newConnection">
         <table border="0">
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "New Connection Invitation"/>
                  <a href="${pageContext.request.contextPath}/">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <table border="0">
            <tr>
               <td colspan ="2">
      				<input type="text" value="${connectionString}" id="myInput" readonly style="width: 1000px;">
      			</td>
      			<td>
      				<button onclick="myFunction()">Copy text</button>  
      			</td>		
      			<td>
      				<button value="${connectionString}" onclick="generateBarCode()">Generate Barcode</button>
      			</td>

            </tr>
         </table>
      
      	<br>
      	<br>
      	
	      				
      	<img id='barcode' 
            src="" 
            alt="" 
            title="Barcode" 
            width="250" 
            height=""
            class="center" />
      			

	<script>
		function myFunction() {
			// Get the text field
			var copyText = document.getElementById("myInput");

			// Select the text field
			copyText.select();
			copyText.setSelectionRange(0, 99999); // For mobile devices

			// Copy the text inside the text field
			navigator.clipboard.writeText(copyText.value);

			// Alert the copied text
			alert("Copied the text: " + copyText.value);
		}
	</script>
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script type="text/javascript">
            function generateBarCode()
            {
                var nric = $('#text').val();
                var url = 'https://api.qrserver.com/v1/create-qr-code/?data=' + nric + '&amp;size=50x50';
                $('#barcode').attr('src', url);
            }
        </script>
        
	      
	

	<jsp:include page="_footer.jsp"></jsp:include>
   </body>
</html>