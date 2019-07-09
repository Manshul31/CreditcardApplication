<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Credit card options</title>
</head>
<body>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>Welcome ${pageContext.request.userPrincipal.name}</h2>

		<h2>Please select an option :</h2>
		<a href="${contextPath}/createNewCard">Create a new credit card</a>
		<br></br>
		<a href="${contextPath}/searchCards">Search for existing card</a>

		<form method="post" action="${contextPath}/logout">
			<input type="submit" value="Logout"></input>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</c:if>
</body>
</html>
