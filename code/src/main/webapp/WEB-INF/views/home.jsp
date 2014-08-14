<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" %>
<html>
<head>
	<title>${title}</title>
</head>
<body>
<h1>
	${title}
</h1>

<P> Message from Server: ${message}</P>

<P>
 <i>Username is <c:out value="${pageContext.request.remoteUser}">[ unknown ]</c:out>, role is
	<sec:authorize ifAllGranted="ROLE_ADMIN">ADMIN</sec:authorize>
	<sec:authorize ifAllGranted="ROLE_USER">USER</sec:authorize>
	<sec:authorize ifNotGranted="ROLE_ADMIN,ROLE_USER">ANONYMOUS</sec:authorize>
</i>
</p>

</body>
</html>
