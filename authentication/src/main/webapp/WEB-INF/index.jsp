<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <form:form action="/register" method="post" modelAttribute="newUser">
    <div>
		<form:label path="userName"> User Name: </form:label><br />
		<form:input path="userName"/>
		<form:errors path="userName"/>
	</div>
	<div>
		<form:label path="email">Email: </form:label><br />
		<form:input path="email"/>
		<form:errors path="email"/>
	</div>
	<div>
		<form:label path="password">Password: </form:label><br />
		<form:input path="password"/>
		<form:errors path="password"/>
	</div>
<div>
		<form:label path="confirm">Confirm Password: </form:label><br />
		<form:input path="confirm"/>
		<form:errors path="confirm"/>
	</div>
	
    
    <input type="submit" value="Register"/>
</form:form> 


 <form:form action="/login" method="post" modelAttribute="newLogin">
   
	<div>
		<form:label path="email">Email: </form:label><br />
		<form:input path="email"/>
		<form:errors path="email"/>
	</div>
	<div>
		<form:label path="password">Password: </form:label><br />
		<form:input path="password"/>
		<form:errors path="password"/>
	</div>
	
    
    <input type="submit" value="Login"/>
</form:form> 

</body>
</html>