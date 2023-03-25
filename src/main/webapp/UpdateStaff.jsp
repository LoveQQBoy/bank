<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>創建會員</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<form method="post" action="<c:url value='/UpdateStaff' />">
	<div class="container border border-primary w-50 mt-5 py-5">
		<div class="mt-3 offset-sm-2">
			<label for="username">姓名</label>
			<input type="text" name="username" id="username" value="${mb.username}${rememberMessage.username}" placeholder="username" autocomplete="off"/>
			<span>${errorMessage.username }</span>
		</div>
		
		<div class="mt-3 offset-sm-2">
			<label for="account">帳號</label>
			<input type="text" name="account" id="account" value="${mb.account}${rememberMessage.account}" placeholder="account" autocomplete="off" />
			<span>${errorMessage.account }</span>
		</div>
		
		<div class="mt-3 offset-sm-2">
			<label for="password">密碼</label>
			<input type="password" name="password" id="password" value="${mb.password}${rememberMessage.password}" placeholder="password" autocomplete="off" />
			<span>${errorMessage.password }</span>
		</div>
		
		<div class="mt-3 offset-sm-2">
			<label for="bornDate">出生日期</label>
			<input type="date" name="bornDate" id="bornDate" value="${mb.bornDate}${rememberMessage.bornDate}" placeholder="bornDate" autocomplete="off"/>
			<span>${errorMessage.bornDate }</span>
		</div>
		
		<div class="mt-3 offset-sm-2">
			<label for="identityCard">身分證號碼</label>
			<input type="text" name="identityCard" id="identityCard" value="${mb.identityCard}${rememberMessage.identityCard}" placeholder="identityCard" autocomplete="off" />
			<span>${errorMessage.identityCard }</span>
		</div>
		
		<div class="mt-3 offset-sm-2">
			<label for="phoneNumber">電話號碼</label>
			<input type="text" name="phoneNumber" id="phoneNumber" value="${mb.phoneNumber}${rememberMessage.phoneNumber}" placeholder="phoneNumber" autocomplete="off" />
			<span>${errorMessage.phoneNumber }</span>
		</div>
		
		<div class="mt-3 offset-sm-2">
			<label for="email">信箱</label>
			<input type="email" name="email" id="email" value="${mb.email}${rememberMessage.email}" placeholder="email" autocomplete="off" />
			<span>${errorMessage.email }</span>
			
			<input type="hidden" name="id" id="id" value="${mb.p_ID}"/>
		</div>
		
		
		<div class="mt-3 offset-sm-4">
		<input type="submit" value="提交">
		<input type="reset" value="清除">
		</div>
	</div>
	
</form>
</body>
</html>