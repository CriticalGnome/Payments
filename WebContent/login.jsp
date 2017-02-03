<%@page import="com.criticalgnome.payments.dao.UserDAO"%>
<%@page import="com.criticalgnome.payments.beans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${sessionScope.locale == 'locale_en_US' or empty sessionScope.locale}" >
    <fmt:setBundle basename="locale_en_US" />
</c:if>
<c:if test="${sessionScope.locale == 'locale_ru_RU'}">
    <fmt:setBundle basename="locale_ru_RU" />
</c:if>
<c:if test="${param.action == 'logout'}" >
	<c:remove var="isAuthorized"/>
	<c:remove var="userID"/>
	<c:remove var="role"/>
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/default.css" rel="stylesheet" type="text/css">
<title><fmt:message key="title.login" /></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>

<c:if test="${param.action == 'wronglogin'}" >
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="alert alert-danger alert-dismissible fade in" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<fmt:message key="login.wrong.pass"/>
			</div>
		</div>
	</div>
</div>
</c:if>


<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<form action="login" method="POST">
				<div class="form-group">
					<label for="email"><fmt:message key="login.email" /></label>
					<input type="email" autofocus class="form-control" name="email" placeholder="<fmt:message key="login.email.placeholder" />">
				</div>
				<div class="form-group">
					<label for="password"><fmt:message key="login.password" /></label>
					<input type="password" class="form-control" name="password" placeholder="<fmt:message key="login.password.placeholder" />">
				</div>
				<button type="submit" class="btn btn-primary"><fmt:message key="login.button" /></button>
			</form>
				<a href="register.jsp"><fmt:message key="register.button" /></a>
		</div>
	</div>
</div>
<%@ include file="inc/footer.jsp"%>
</body>
</html>