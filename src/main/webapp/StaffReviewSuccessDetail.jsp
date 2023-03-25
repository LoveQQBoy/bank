<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import=" java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工審查</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<%
request.setCharacterEncoding("utf-8");
%>

<sql:setDataSource dataSource="jdbc/bank" />
<sql:query var="application">
select username,applicationStatus,account,bankAccount,password,bornDate,identityCard,phoneNumber,email,applicationDate,reason
from CustomerInformation
where identityCard ='${param.identityCard}'
</sql:query>
</head>
<body>
	<div class="container border border-primary w-50 mt-5 py-5">
		<div class="offset-sm-2">
			<c:forEach var="applicationRow" items="${application.rows}">
				<p>戶號: ${applicationRow.bankAccount}</p>
				<p>姓名: ${applicationRow.username}</p>
				<p>會員帳號:${applicationRow.account}</p>
				<p>會員密碼:${applicationRow.password}</p>
				<p>出生日期:${applicationRow.bornDate}</p>
				<p>身分證 :${applicationRow.identityCard}</p>
				<p>電話號碼:${applicationRow.phoneNumber}</p>
				<p>信箱 :${applicationRow.email}</p>
				<p>申請通過日期:${applicationRow.applicationDate}</p>
				<p>審核狀態:${applicationRow.applicationStatus}</p>

			</c:forEach>
			<button class="btn btn-outline-info offset-sm-1 " type="button"
				name="returnStaffReview" id="returnStaffReview"
				onclick="location.href=`<c:url value='/StaffReview.jsp'/>`">返回資料審查</button>
		</div>

	</div>
</body>
</html>