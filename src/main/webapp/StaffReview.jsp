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
select username,applicationStatus,account,password,bornDate,identityCard,phoneNumber,email,applicationDate,reason
from CustomerApplication
where applicationStatus ='待審查'
</sql:query>

<sql:query var="applicationFalse">
select username,applicationStatus,account,password,bornDate,identityCard,phoneNumber,email,applicationDate,reason
from CustomerApplication
where applicationStatus='審查未通過'
</sql:query>

<sql:query var="applicationSeccess">
select username,applicationStatus,account,password,bornDate,identityCard,phoneNumber,email,applicationDate,reason
from CustomerInformation
where applicationStatus ='審查通過'
</sql:query>

</head>
<body>
	<div class="container border border-primary w-50 mt-5 py-5">
		<form method="post" action="<c:url value='/StaffReview.jsp'/>"
			class="offset-sm-3">
			<label for="searchApplicationSuccess">審查通過 <input
				type="radio" id="searchApplicationSuccess"
				name="searchApplicationInformation" value="審查通過">
			</label> &nbsp; <label for="searchApplication">待審查 <input
				type="radio" id="searchApplication"
				name="searchApplicationInformation" value="待審查">
			</label> &nbsp; <label for="searchApplicationFail">審查未通過 <input
				type="radio" id="searchApplicationFail"
				name="searchApplicationInformation" value="審查未通過">
			</label> &nbsp; 
			<td><input type="submit" value="查詢"></td>
		</form>
		<br>
		<div class="offset-sm-2">
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>顧客名</th>
						<th>申請狀態</th>
						<th>詳細資訊</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${param.searchApplicationInformation =='待審查' }">
							<c:forEach var="applicationRow" items="${application.rows}">
								<tr>
									<td>${applicationRow.username}</td>
									<td>${applicationRow.applicationStatus}</td>
									<form method="post"
										action="<c:url value='/StaffReviewDetail.jsp'/>">
										<input type="hidden" name="identityCard"
											value="${applicationRow.identityCard}">
										<td><input type="submit" value="詳細資料"></td>
									</form>
								</tr>
							</c:forEach>
						</c:when>

						<c:when test="${param.searchApplicationInformation =='審查通過' }">
							<c:forEach var="applicationRow"
								items="${applicationSeccess.rows}">
								<tr>
									<td>${applicationRow.username}</td>
									<td>${applicationRow.applicationStatus}</td>
									<form method="post"
										action="<c:url value='/StaffReviewSuccessDetail.jsp'/>">
										<input type="hidden" name="identityCard"
											value="${applicationRow.identityCard}">
										<td><input type="submit" value="詳細資料"></td>
									</form>
								</tr>
							</c:forEach>
						</c:when>
						
						<c:when test="${param.searchApplicationInformation =='審查未通過' }">
							<c:forEach var="applicationRow"
								items="${applicationFalse.rows}">
								<tr>
									<td>${applicationRow.username}</td>
									<td>${applicationRow.applicationStatus}</td>
									<form method="post"
										action="<c:url value='/StaffReviewDetail.jsp'/>">
										<input type="hidden" name="identityCard"
											value="${applicationRow.identityCard}">
										<td><input type="submit" value="詳細資料"></td>
									</form>
								</tr>
							</c:forEach>
						</c:when>

						<c:otherwise>
							<td></td>
							<td></td>
							<td></td>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>

		<button class="btn btn-outline-info offset-sm-9 " type="button"
			name="returnHome" id="returnHome"
			onclick="location.href=`<c:url value='/Index.jsp'/>`">返回首頁</button>

	</div>


</body>
</html>