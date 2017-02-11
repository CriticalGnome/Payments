<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="inc/uselocale.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/default.css" rel="stylesheet" type="text/css">
<title><fmt:message key="title.adminarea" /></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-12">
		<table class="table table-hover">
			<tr>
				<th><fmt:message key="adminarea.number" /></th>
				<th><fmt:message key="adminarea.id" /></th>
				<th><fmt:message key="adminarea.account" /></th>
				<th><fmt:message key="adminarea.amount" /></th>
				<th><fmt:message key="adminarea.username" /></th>
				<th><fmt:message key="adminarea.role" /></th>
				<th><fmt:message key="adminarea.function" /></th>
			</tr>
			<c:forEach var="i" begin="1" end="${blockedAccountsCount}">
				<tr>
					<td>${i}</td>
					<td>${blockedAccounts[i-1].id}</td>
					<td>${blockedAccounts[i-1].number}</td>
					<td>${blockedAccounts[i-1].amount}</td>
					<td>${blockedAccounts[i-1].firstName} ${blockedAccounts[i-1].lastName}</td>
					<td>${blockedAccounts[i-1].role}</td>
					<td>
						<form action="controller" method="POST">
						<input type="hidden" name="action" value="unBlockAccount">
						<input type="hidden" name="id" value="${blockedAccounts[i-1].id}">
						<button type="submit" class="btn btn-success btn-xs"><fmt:message key="adminarea.button.unblock" /></button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>
</div>
<%@ include file="inc/footer.jsp"%>
</body>
</html>