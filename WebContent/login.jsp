<%@ page import="com.criticalgnome.payments.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${sessionScope.locale == 'locale_en_US' or empty sessionScope.locale}" >
    <fmt:setBundle basename="locale_en_US" />
</c:if>
<c:if test="${sessionScope.locale == 'locale_ru_RU'}">
    <fmt:setBundle basename="locale_ru_RU" />
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/default.css" rel="stylesheet" type="text/css">
<title><fmt:message key="title.index" /></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<%
	if (request.getParameter("email") != null) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String dbPassword = DBConnection.getPasswordFromDB(email);
		if (dbPassword == null) {
%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="alert alert-warning alert-dismissible fade in"
				role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<fmt:message key="login.no.user" />
			</div>
		</div>
	</div>
</div>
<%
	} else {
			if (dbPassword.equals(password)) {
				session.setAttribute("isAuthorized", "yes");
				response.sendRedirect("index.jsp?action=login");
			} else {
%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="alert alert-danger alert-dismissible fade in"
				role="alert">
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<fmt:message key="login.wrong.pass" />
			</div>
		</div>
	</div>
</div>
<%
	}
		}
	}
%>
<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<form action="login.jsp" method="POST">
				<div class="form-group">
					<label for="loginEmail1"><fmt:message key="login.email" /></label>
					<input type="email" class="form-control" name="email" placeholder="<fmt:message key="login.email.placeholder" />">
				</div>
				<div class="form-group">
					<label for="loginPassword1"><fmt:message key="login.password" /></label>
					<input type="password" class="form-control" name="password" placeholder="<fmt:message key="login.password.placeholder" />">
				</div>
				<button type="submit" class="btn btn-default"><fmt:message key="login.button" /></button>
			</form>
		</div>
	</div>
</div>
<%@ include file="inc/footer.jsp"%>
</body>
</html>