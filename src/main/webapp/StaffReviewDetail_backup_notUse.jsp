<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import=" java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工審查</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<% request.setCharacterEncoding("utf-8"); %>

</head>
<body>
<div class="container border border-primary w-50 mt-5 py-5">
	<div class="offset-sm-2">
		
		<form method="post" action="<c:url value='/StaffReviewDetail2.jsp'/>">
			<p>姓名:    ${param.username}</p>
			<p>會員帳號:${param.account}</p>
			<p>會員密碼:${param.password}</p>
			<p>出生日期:${param.bornDate}</p>
			<p>身分證  :${param.identityCard}</p>
			<p>電話號碼:${param.phoneNumber}</p>
			<p>信箱    :${param.email}</p>
			<p>申請日期:${param.applicationDate}</p>
			<p>審核狀態:${param.applicationStatus}</p>
			<span>原因:${param.reason}</span>
			<input type="hidden" name="username" value="${param.username}">
			<input type="hidden" name="applicationStatus" value="${param.applicationStatus}">
			<input type="hidden" name="account" value="${param.account}">
			<input type="hidden" name="password" value="${param.password}">
			<input type="hidden" name="bornDate" value="${param.bornDate}">
			<input type="hidden" name="identityCard" value="${param.identityCard}">
			<input type="hidden" name="phoneNumber" value="${param.phoneNumber}">
			<input type="hidden" name="email" value="${param.email}">
			<input type="hidden" name="applicationDate" value="${param.applicationDate}">
			<input type="hidden" name="reason" value="${param.reason}">
			<input type="submit" value="編輯">
		</form>
		
	</div>
	
	<button class="btn btn-outline-info offset-sm-9 " type="button" name="returnHome" id="returnHome" onclick="location.href=`<c:url value='/Index.jsp'/>`">返回首頁</button>
	
</div>


</body>
</html>