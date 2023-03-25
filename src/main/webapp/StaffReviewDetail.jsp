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

<sql:setDataSource dataSource="jdbc/bank"/>
<sql:query var="application">
select username,applicationStatus,account,password,bornDate,identityCard,phoneNumber,email,applicationDate,reason
from CustomerApplication
where identityCard ='${param.identityCard}'
</sql:query>

</head>
<body>
<div class="container border border-primary w-50 mt-5 py-5">
	<div class="offset-sm-2">
	
		<form name="post1" method="post" action="<c:url value='/StaffReviewDetail2.jsp'/>"> 
			<c:forEach var="applicationRow" items="${application.rows}">
				<p>姓名:    ${applicationRow.username}</p>
				<p>會員帳號:${applicationRow.account}</p>
				<p>會員密碼:${applicationRow.password}</p>
				<p>出生日期:${applicationRow.bornDate}</p>
				<p>身分證  :${applicationRow.identityCard}</p>
				<p>電話號碼:${applicationRow.phoneNumber}</p>
				<p>信箱    :${applicationRow.email}</p>
				<p>申請日期:${applicationRow.applicationDate}</p>
				<p>審核狀態:${applicationRow.applicationStatus}</p>
				<p>原因:${applicationRow.reason}</p>
				<input type="submit" value="編輯審核狀態">
				<input type="hidden" name="identityCard" value="${applicationRow.identityCard}">
				<input type="hidden" name="username" value="${applicationRow.username}">
				<input type="hidden" name="applicationStatus" value="${applicationRow.applicationStatus}">
				<input type="hidden" name="account" value="${applicationRow.account}">
				<input type="hidden" name="password" value="${applicationRow.password}">
				<input type="hidden" name="bornDate" value="${applicationRow.bornDate}">
				<input type="hidden" name="phoneNumber" value="${applicationRow.phoneNumber}">
				<input type="hidden" name="email" value="${applicationRow.email}">
				<input type="hidden" name="applicationDate" value="${applicationRow.applicationDate}">
				<input type="hidden" name="reason" value="${applicationRow.reason}">
				
			</c:forEach>
	</form>
	<form name="post2" method="post" action="<c:url value='/CreateMember_controller'/>">
		<c:forEach var="applicationRow" items="${application.rows}">
			<input type="hidden" name="identityCard" value="${applicationRow.identityCard}">
			<input type="hidden" name="username" value="${applicationRow.username}">
			<input type="hidden" name="applicationStatus" value="${applicationRow.applicationStatus}">
			<input type="hidden" name="account" value="${applicationRow.account}">
			<input type="hidden" name="password" value="${applicationRow.password}">
			<input type="hidden" name="bornDate" value="${applicationRow.bornDate}">
			<input type="hidden" name="phoneNumber" value="${applicationRow.phoneNumber}">
			<input type="hidden" name="email" value="${applicationRow.email}">
			<input type="hidden" name="applicationDate" value="${applicationRow.applicationDate}">
			<input type="hidden" name="reason" value="${applicationRow.reason}">
			<div class="form-group">
				<input type="submit" value="送出審核結果" class="btn btn-outline-info offset-sm-1">	
				<button class="btn btn-outline-info offset-sm-1 " type="button" name="returnStaffReview" id="returnStaffReview" onclick="location.href=`<c:url value='/StaffReview.jsp'/>`">返回資料審查</button>
			</div>
		</c:forEach>
	</form>
	</div>
	
</div>


</body>
</html>