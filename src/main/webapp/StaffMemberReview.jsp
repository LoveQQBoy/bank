<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import=" java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>員工資料查詢</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<% request.setCharacterEncoding("utf-8"); %>
</head>
<body>
<div class="container border border-primary w-75 mt-5 py-5">
	<div class="">
	<table class="table table-bordered table-hover table-striped mr-5">
		<thead>
			<tr>
				<th>用戶名</th>
				<th>帳號</th>
				<th>密碼</th>
				<th>生日</th>
				<th>身分證</th>
				<th>手機號碼</th>
				<th>信箱</th>
				<th>操作</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="MemberInformation" items="${MemberInformation}">
			<tr>

				<td>${MemberInformation.username}</td>
				<td>${MemberInformation.account}</td>
				<td>${MemberInformation.password}</td>
				<td>${MemberInformation.bornDate}</td>
				<td>${MemberInformation.identityCard}</td>
				<input type="hidden" name="identityCard" value="${MemberInformation.identityCard}">
				<td>${MemberInformation.phoneNumber}</td>
				<td>${MemberInformation.email}</td>
				<td>
					<a href="<c:url value='/FindStaff?id=${MemberInformation.p_ID }'/>" class="badge bg-primary text-wrap fs-6 mv-5">修改</a>
				</td>			
			</tr>
			
			</c:forEach>
		</tbody>
	</table>
	</div>
	
	<button class="btn btn-outline-info offset-sm-9 " type="button" name="returnHome" id="returnHome" onclick="location.href=`<c:url value='/Index.jsp'/>`">返回首頁</button>
	
</div>


</body>
</html>