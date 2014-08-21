<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true" %>

<%@include file="/WEB-INF/views/header.jspf" %>
<c:set var="page" value="${pagename}" />
<!-- include specific pages -->
<c:choose>
	<c:when test="${page eq 'login'}">
		<jsp:include page="login.jsp" />
	</c:when>
	<c:when test="${page eq 'register'}">
		<jsp:include page="register.jsp" />
	</c:when>	
	<c:otherwise>
		<jsp:include page="anonym.jsp" />
	</c:otherwise>
</c:choose>
<%@include file="/WEB-INF/views/footer.jspf" %>