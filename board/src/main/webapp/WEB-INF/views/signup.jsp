<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>HELLO MVC</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<style>
ul , div {
	margin-bottom: 10px;
	margin-top: 10px;
}
</style>
<body>

	<div class="container">
		<h2>SIGN UP PLEASE:</h2>
		<c:if test="${message!=null}">
			<div style="background-color: orange; border: black solid 0.5px;" >${message}</div>
		</c:if>
		<form action="users" method="POST">
			<div class="form-group">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<label for="username">User name:</label>
				<input type="text" class="form-control" name="username" id="username">
				<label for="password">Password:</label>
				<input type="password" class="form-control" name="password" id="password">
				<label for="passwordConfirm">Password confirm:</label>
				<input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
		<div>
			<a href="${pageContext.request.contextPath}">RETURN TO MAIN PAGE</a>
		</div>
	</div>
</body>
</html>
