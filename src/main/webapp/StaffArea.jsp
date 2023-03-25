<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工登入</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container border border-primary w-50 mt-5 py-5">
	<div class="offset-sm-2">
	<a href="<c:url value='/CreateStaffPermissions.jsp'/>">員工帳號創建</a>
	<a class="offset-sm-2" href="<c:url value='/StaffLogin_controller'/>">員工帳號登入</a>
	<br><br>
	<a  href="<c:url value='/CheckStaffLogin_controller'/>">用戶資料審查</a>
	<a class="offset-sm-2" href="<c:url value='/QueryStaff'/>">員工資料確認</a>
	</div>
	
	<button class="btn btn-outline-info offset-sm-9 " type="button" name="returnHome" id="returnHome" onclick="location.href=`<c:url value='/Index.jsp'/>`">返回首頁</button>
	
</div>


</body>
</html>