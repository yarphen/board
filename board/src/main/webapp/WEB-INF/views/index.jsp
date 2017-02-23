<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
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
<script type="text/javascript">
	var deletePost = function(id) {
		$.ajax({
			method : "DELETE",
			url : '${contextPath}/posts/' + id,
			success : function(result, status, xhr) {
				if (result.success) {
					location.href = '${contextPath}/deleted';
				} else {
					alert('Error occured!')
				}
			}
		});
	}
</script>
<style>
ul, div {
	margin-bottom: 10px;
	margin-top: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<h2>HELLO MVC PROJECT</h2>
		<form action="${contextPath}/posts" method="POST">
			<div class="form-group">
				<label for="text">Post text:</label> <input type="text"
					class="form-control" name="text" id="text">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
		<ul class="list-group">
			<c:forEach var="post" items="${posts}">
				<li class="list-group-item"><span>${post.text}</span>
					<button onclick="deletePost('${post.id}')"
						class="glyphicon glyphicon-remove" style="float: right;"></button>

				</li>
			</c:forEach>
		</ul>
	</div>
</body>
</html>
