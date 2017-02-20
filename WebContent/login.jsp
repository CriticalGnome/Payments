<%@page import="com.criticalgnome.payments.dao.UserDAO"%>
<%@page import="com.criticalgnome.payments.beans.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="inc/uselocale.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<title><fmt:message key="title.login" /></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<form action="controller" method="POST">
				<div class="form-group">
					<label for="email"><fmt:message key="login.email" /></label>
					<input type="email" autofocus class="form-control" name="email" placeholder="<fmt:message key="login.email.placeholder" />">
				</div>
				<div class="form-group">
					<label for="password"><fmt:message key="login.password" /></label>
					<input type="password" class="form-control" name="password" placeholder="<fmt:message key="login.password.placeholder" />">
				</div>
				<button type="submit" class="btn btn-primary"><fmt:message key="login.button" /></button>
				<input type="hidden" name="action" value="login">
			</form>
				<a href="register.jsp"><fmt:message key="register.button" /></a>
		</div>
	</div>
</div>
<%@ include file="inc/footer.jsp"%>
</body>
</html>