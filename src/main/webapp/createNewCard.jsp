<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Card</title>
<script type="text/javascript">
function validateForm()
{
    var cardHolderName=document.getElementById("cardHolderName").value
    var cardNumber=document.getElementById("cardNumber").value
    var expiryDate = document.getElementById("expiryDate").value
    var pattern = /^\d{2}\/(0[1-9]|1[0-2])$/
    if(cardHolderName==null || cardHolderName=="" )
    {
        alert("Please enter the cardholder name");
        return false;
    }

    
    if(cardNumber==null || cardNumber=="")
    {
        alert("Please enter the card number");
        return false;
    }
    
    if(cardNumber.length != 16)
    {
        alert("Card Number should be 16 digits");
        return false;
    }
    
    if(expiryDate==null || expiryDate=="")
    {
        alert("Please enter the expiry date in (YY/MM) format");
        return false;
    }
   
    if(pattern.test(expiryDate)== false){
    	alert("Please enter the expiry date in (YY/MM) format")
    	return false;
    }
    
  }

</script>

</head>
<body>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<form:form method="post" modelAttribute="creditCard" action="${contextPath}/createNewCard" onsubmit="return validateForm()">
			<h2>Please enter the following details to create a new credit
				card</h2>
			<input type="hidden" name="username"
				value="${pageContext.request.userPrincipal.name}"></input>
			<table border="1">
				<tr>
					<td align="right">Cardholder Name:</td>
					<td align="left"><input type="text" name="cardHolderName" id="cardHolderName"
						size="50"></input></td>
				</tr>
				<tr>
					<td align="right">Card Number:</td>
					<td align="left"><input type="text" name="cardNumber" id="cardNumber"
						size="50"></input></td>
				</tr>
				<tr>
					<td align="right">Expiry Date (YY/MM)</td>
					<td align="left"><input type="text" name="expiryDate" id="expiryDate"
						size="50"></input></td>
				</tr>
				<tr>
					<td align="right"></td>
					<td align="left"><input type="submit"></input></td>
				</tr>
			</table>
		</form:form>

		<form method="post" action="${contextPath}/logout">
			<input type="submit" value="Logout"></input> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</c:if>
</body>
</html>