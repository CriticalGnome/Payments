<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="inc/uselocale.jsp" %>
<c:if test="${empty payments}">
<c:redirect url="controller?action=userarea" />
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/default.css" rel="stylesheet" type="text/css">
<title><fmt:message key="title.userarea" /></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<h3 class="text-center"><fmt:message key="userarea.payments.header" /></h3>
			<table class="table table-striped table-condensed">
				<tr>
					<th><fmt:message key="userarea.payments.no" /></th>
					<th><fmt:message key="userarea.payments.datetime" /></th>
					<th><fmt:message key="userarea.payments.destination" /></th>
					<th><fmt:message key="userarea.payments.account" /></th>
					<th><fmt:message key="userarea.payments.user" /></th>
					<th><fmt:message key="userarea.payments.amount" /></th>
					<th><fmt:message key="userarea.payments.comment" /></th>
				</tr>
				<c:forEach var="i" begin="1" end="${paymentsCount}">
				<tr>
					<td>${i}</td>
					<td>${payments[i-1].dateTime}</td>
					<c:if test="${payments[i-1].accountID == sessionScope.userID}">
						<td><span class="label label-danger"><fmt:message key="userarea.payments.to" /></span></td>
					</c:if>
					<c:if test="${payments[i-1].accountID != sessionScope.userID}">
						<td><span class="label label-success"><fmt:message key="userarea.payments.from" /></span></td>
					</c:if>
					<td>${payments[i-1].number}</td>
					<td>${payments[i-1].firstName} ${payments[i-1].lastName}</td>
					<td>${payments[i-1].amount}</td>
					<td>${payments[i-1].comment}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

<%@ include file="inc/footer.jsp"%>
</body>
</html>