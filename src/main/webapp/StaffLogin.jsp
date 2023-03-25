<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>會員登入</title>
</head>
<body>
	<form method="get" action="<c:url value='/StaffLogin_controller' />">
		<div class="container w-50 border border-primary mt-5">
			<h1 class="text text-primary text-center">會員登入</h1>
			<label class="text offset-sm-2 mt-3">帳號<input type="text"
				name="account" id="account" placeholder="username"
				autocomplete="off"></label> 
				
			<label class="text offset-sm-2 mt-3">密碼<input
				type="password" name="password" id="password" placeholder="password"
				autocomplete="off"></label>
			<div class="text-center my-3">
				<input type="submit" value="登入">
				<input type="reset" value="清除">
			</div>
		</div>
	</form>


</body>
</html>