<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員首頁</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<style type="text/css">
</style>

</head>
<body>
	<div class="container border border-primary w-75 mt-5 py-3">
		<h1 class="offset-sm-3">歡迎光臨嵂昕銀行</h1>
		<p class="text-center fs-4">加入嵂昕讓你永保年薪</p>
		<c:if test="${sessionScope.username != null}">
			<div class="offset-sm-10">${sessionScope.username}您好
				<button class="btn btn-outline-info  " type="button"
					name="returnHome" id="returnHome"
					onclick="location.href=`<c:url value='/StaffLogout_controller'/>`">登出</button>
			</div>
		</c:if>
		<div class="offset-sm-2">
			<a href="<c:url value='/CreateMember.jsp'/>">會員登入/新建會員</a> <a
				class="offset-sm-2" href="<c:url value='/StaffArea.jsp'/>">員工專區</a>
		</div>

	</div>

</body>
</html>