<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
	<!--  not used, only for documentation purposes -->
	<form method="POST" action="upload"
		enctype="multipart/form-data">
 
		Please select a file to upload : 
		<input type="file" name="file" id="file"/>
		
		<input type="submit" value="upload" />
 
	</form>
 
</body>
</html>
