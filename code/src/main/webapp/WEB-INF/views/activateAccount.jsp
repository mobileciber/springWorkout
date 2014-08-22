<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
	<c:when test="${activationOkay}">
		Congratulations, your account is now activated!
		
		<script>
			setTimeout(function(){location.href='login.html';}, 2500);
		</script>
	</c:when>
	<c:otherwise>
		Something went wrong while we tried to activate your account.<br/>
		<br/>
		Did you use the correct activation key?
	</c:otherwise>
</c:choose>