<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

		<form:form name="f" method="POST" modelAttribute="formBean" enctype="multipart/form-data">
			<table align="center" width="700">
				<colgroup>
					<col span="1" width="150px">
					<col span="1" width="150px">
					<col span="1" width="350px">
				</colgroup>
				<tr>
					<td colspan="2"><h3>Register to access CiberComics</h3></td>
				</tr>
				<tr>
					<td>Avatar image (optional):</td>
					<td><form:input type="file" path="avatar" cssErrorClass="errorBox" /></td>
					<td><form:errors path="avatar" cssClass="error" /></td>
				</tr>				
				<tr>
					<td>User:</td>
					<td><form:input type="text" path="username" cssErrorClass="errorBox" /></td>
					<td><form:errors path="username" cssClass="error" /></td>
				</tr>				
				<tr>
					<td>Password:</td>
					<td><form:password path="password" cssErrorClass="errorBox" showPassword="true" /></td>
					<td><form:errors path="password" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Password (confirm):</td>
					<td><form:password path="passwordrepeat" cssErrorClass="errorBox" showPassword="true" /></td>
					<td><form:errors path="passwordrepeat" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><form:input type="text" path="email" cssErrorClass="errorBox" /></td>
					<td><form:errors path="email" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Email (confirm):</td>
					<td><form:input type="text" path="emailrepeat" cssErrorClass="errorBox" /></td>
					<td><form:errors path="emailrepeat" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Title (optional):</td>
					<td><form:input type="text" path="title" cssErrorClass="errorBox" /></td>
					<td><form:errors path="title" cssClass="error" /></td>
				</tr>				
				<tr>
					<td>Firstname:</td>
					<td><form:input type="text" path="firstname" cssErrorClass="errorBox" /></td>
					<td><form:errors path="firstname" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Lastname:</td>
					<td><form:input type="text" path="lastname" cssErrorClass="errorBox" /></td>
					<td><form:errors path="lastname" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Street:</td>
					<td><form:input type="text" path="street" cssErrorClass="errorBox" /></td>
					<td><form:errors path="street" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Housenumber:</td>
					<td><form:input type="text" path="housenumber" cssErrorClass="errorBox" /></td>
					<td><form:errors path="housenumber" cssClass="error" /></td>
				</tr>				
				<tr>
					<td>Postalcode:</td>
					<td><form:input type="text" path="postalcode" cssErrorClass="errorBox" /></td>
					<td><form:errors path="postalcode" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Town:</td>
					<td><form:input type="text" path="town" cssErrorClass="errorBox" /></td>
					<td><form:errors path="town" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Care of (optional):</td>
					<td><form:input type="text" path="careof" cssErrorClass="errorBox" /></td>
					<td><form:errors path="careof" cssClass="error" /></td>
				</tr>
				<tr>
					<td>company (optional):</td>
					<td><form:input type="text" path="company" cssErrorClass="errorBox" /></td>
					<td><form:errors path="company" cssClass="error" /></td>
				</tr>				
				<tr>
					<td colspan="2"><br><br>
						I have read and accept the terms and conditions <form:checkbox path="acceptterms" cssErrorClass="errorBox" />
						<br><br> 
					</td>
					<td><form:errors path="acceptterms" cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input name="submit" type="submit" value="Register" />
					</td>
				</tr>				
			</table>
			
			
		</form:form>