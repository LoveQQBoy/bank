<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員審核</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<% request.setCharacterEncoding("UTF-8"); %>
</head>
<body>
<div class="container border w-50 mt-5">
	<div class="border border-primary mt-3">
		<h3 class="offset-sm-1 mt-3">親愛的客戶您好</h3>
		<p class="offset-sm-4 mt-3">您的資料正在審核中</p>
		
		<button class="btn btn-outline-info offset-sm-10 mb-3" type="button" name="returnHome" id="returnHome" onclick="location.href=`<c:url value='/Index.jsp'/>`">返回首頁</button>
	</div>


</div>
</body>
</html>