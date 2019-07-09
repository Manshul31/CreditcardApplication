<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Card Details</title>
<script type="text/javascript">
function validateForm()
{
    var cardNumber=document.getElementById("cardNumber").value
    
    if(cardNumber==null || cardNumber=="" )
    {
        alert("Please enter the card number");
        return false;
    }
    
  }

</script>
</head>
<body>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<form method="post" action="${contextPath}/searchCards" onsubmit="return validateForm()">
			<h2>Please enter the credit card number</h2>
			<input type="hidden" name="username"
				value="${pageContext.request.userPrincipal.name}"></input>
				<input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
			<table border="1">
				<tr>
					<td align="right">Card Number:</td>
					<td align="left"><input type="text" name="cardNumber" id="cardNumber"
						size="50"></input></td>
				</tr>
				<tr>
					<td align="right"></td>
					<td align="left"><input type="submit" value="Search"></input></td>
				</tr>
			</table>
		</form>
		
		<form method="post" action="${contextPath}/logout">
			<input type="submit" value="Logout"></input> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</c:if>

</body>
</html>