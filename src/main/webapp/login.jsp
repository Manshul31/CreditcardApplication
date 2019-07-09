<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<script type="text/javascript">
function validateForm()
{
    var userName=document.getElementById("username").value
    var password=document.getElementById("password").value
    if(userName==null || userName=="" )
    {
        alert("Please enter the user name");
        return false;
    }

    
    if(password==null || password=="")
    {
        alert("Please enter the password");
        return false;
    }
   
    
  }

</script>
</head>
<body>
	<form method="post" action="${contextPath}/login" onsubmit="return validateForm()">
		<h2>Mifinity Login</h2>
		<table border="1">
			<tr>
				<td align="right">User Name:</td>
				<td align="left"><input type="text" name="username" id="username"
					placeholder="Username" size="30"></input></td>
			</tr>
			<tr>
				<td align="right">Password:</td>
				<td align="left"><input type="password" name="password" id="password"
					placeholder="Password" size="20"></input></td>
			</tr>
			<tr>
				<td align="right"></td>
				<td align="left"><input type="submit" value="Login"></input></td>
			</tr>
		</table> 
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>
	<br></br>
	<br></br>
	<a href="${contextPath}/registration">New User? Click Here !!!</a>

</body>
</html>