<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>CiberComics</title>
	</head>
	<body onload="document.f.j_username.focus();">
		<h3>Login to access CiberComics</h3>
		<form name="f" action="/comics/loginProcess"
			method="POST">
			<table>
				<tr>
					<td>User:</td>
					<td><input type="text" name="j_username" value=""></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="j_password" /></td>
				</tr>
				<c:if test="${loginError}">
					<tr>
						<td colspan="2">
							<font color="red">LOGIN FAILED</font>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="2"><input name="submit" type="submit" value="Login" /></td>
				</tr>
			</table>
		</form>
	</body>
</html>