<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${sessionScope.locale == 'locale_en_US' or empty sessionScope.locale}" >
    <fmt:setBundle basename="locale_en_US" />
</c:if>
<c:if test="${sessionScope.locale == 'locale_ru_RU'}">
    <fmt:setBundle basename="locale_ru_RU" />
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/default.css" rel="stylesheet" type="text/css">
<title><fmt:message key="title.register" /></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<c:if test="${param.action == 'emailalreadyexist'}" >
	<c:set var="alertMessage" value="messages.email.exist" />
	<c:set var="alertColor" value="alert-danger" />
</c:if>
<c:if test="${not empty alertMessage}" >
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="alert ${alertColor} alert-dismissible fade in" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<fmt:message key="${alertMessage}"/>
			</div>
		</div>
	</div>
</div>
</c:if>
<div class=container>
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<form action="register" method="POST">

				<%-- First Name input field --%>
				<c:choose>
					<%-- Empty First Name --%>
					<c:when test="${ param.fnError == 'empty' }">
					<div class="form-group has-error">
						<label for="firstName"><fmt:message key="register.first.name" /></label>
						<input type="text" autofocus class="form-control" name="firstName" placeholder="<fmt:message key="register.first.name.placeholder" />">
						<span class="help-block"><fmt:message key="register.first.name.empty"/></span>
					</div>
					</c:when>
					<%-- Wrong Characters in First Name --%>
					<c:when test="${ param.fnError == 'wrong' }">
					<div class="form-group has-error">
						<label for="firstName"><fmt:message key="register.first.name" /></label>
						<input type="text" autofocus class="form-control" name="firstName"  value="${ param.firstName }">
						<span class="help-block"><fmt:message key="register.invalid.characters"/></span>
					</div>
					</c:when>
					<%-- Correct First Name, but more Errors --%>
					<c:when test="${ param.fnError != 'empty' and not empty param.firstName }">
					<div class="form-group has-success">
						<label for="firstName"><fmt:message key="register.first.name" /></label>
						<input type="text" autofocus class="form-control" name="firstName" value="${ param.firstName }">
					</div>
					</c:when>
					<%-- First iterate --%>
					<c:otherwise>
					<div class="form-group">
						<label for="firstName"><fmt:message key="register.first.name" /></label>
						<input type="text" autofocus class="form-control" name="firstName" placeholder="<fmt:message key="register.first.name.placeholder" />">
					</div>
					</c:otherwise>
				</c:choose>
				
				<%-- Last Name input field --%>
				<c:choose>
					<%-- Empty Last Name --%>
					<c:when test="${ param.lnError == 'empty' }">
					<div class="form-group has-error">
						<label for="lastName"><fmt:message key="register.last.name" /></label>
						<input type="text" class="form-control" name="lastName" placeholder="<fmt:message key="register.last.name.placeholder" />">
						<span class="help-block"><fmt:message key="register.last.name.empty"/></span>
					</div>
					</c:when>
					<%-- Wrong Characters in Last Name --%>
					<c:when test="${ param.lnError == 'wrong' }">
					<div class="form-group has-error">
						<label for="lastName"><fmt:message key="register.last.name" /></label>
						<input type="text" class="form-control" name="lastName" value="${ param.lastName }">
						<span class="help-block"><fmt:message key="register.invalid.characters"/></span>
					</div>
					</c:when>
					<%-- Correct Last Name, but more Errors --%>
					<c:when test="${ param.lnError != 'empty' and not empty param.lastName }">
					<div class="form-group has-success">
						<label for="lastName"><fmt:message key="register.last.name" /></label>
						<input type="text" class="form-control" name="lastName" value="${ param.lastName }">
					</div>
					</c:when>
					<%-- First iterate --%>
					<c:otherwise>
					<div class="form-group">
						<label for="lastName"><fmt:message key="register.last.name" /></label>
						<input type="text" class="form-control" name="lastName" placeholder="<fmt:message key="register.last.name.placeholder" />">
					</div>
					</c:otherwise>
				</c:choose>

				<%-- Email input field --%>
				<c:choose>
					<%-- Empty Email --%>
					<c:when test="${ param.emError == 'empty' }">
					<div class="form-group has-error">
						<label for="email"><fmt:message key="register.email" /></label>
						<input type="email" class="form-control" name="email" placeholder="<fmt:message key="register.email.placeholder" />">
						<span class="help-block"><fmt:message key="register.email.empty"/></span>
					</div>
					</c:when>
					<%-- Correct Email, but more Errors --%>
					<c:when test="${ param.emError != 'empty' and not empty param.email }">
					<div class="form-group has-success">
						<label for="email"><fmt:message key="register.email" /></label>
						<input type="email" class="form-control" name="email" value="${ param.email }">
					</div>
					</c:when>
					<%-- First iterate --%>
					<c:otherwise>
					<div class="form-group">
						<label for="email"><fmt:message key="register.email" /></label>
						<input type="email" class="form-control" name="email" placeholder="<fmt:message key="register.email.placeholder" />">
					</div>
					</c:otherwise>
				</c:choose>

				<%-- Password input field --%>
				<c:choose>
					<%-- Empty Password --%>
					<c:when test="${ param.pwError == 'empty' }">
					<div class="form-group has-error">
						<label for="password"><fmt:message key="register.password" /></label>
						<input type="password" class="form-control" name="password" placeholder="<fmt:message key="register.password.placeholder" />">
						<span class="help-block"><fmt:message key="register.password.empty"/></span>
					</div>
					</c:when>
					<%-- Wrong Characters in Password --%>
					<c:when test="${ param.pwError == 'wrong' }">
					<div class="form-group has-error">
						<label for="password"><fmt:message key="register.password" /></label>
						<input type="password" class="form-control" name="password" value="${ param.password }">
						<span class="help-block"><fmt:message key="register.invalid.characters"/></span>
					</div>
					</c:when>
					<%-- Correct Password, but more Errors --%>
					<c:when test="${ param.pwError != 'empty' and not empty param.password }">
					<div class="form-group has-success">
						<label for="password"><fmt:message key="register.password" /></label>
						<input type="password" class="form-control" name="password" value="${ param.password }">
					</div>
					</c:when>
					<%-- First iterate --%>
					<c:otherwise>
					<div class="form-group">
						<label for="password"><fmt:message key="register.password" /></label>
						<input type="password" class="form-control" name="password" placeholder="<fmt:message key="register.email.placeholder" />">
					</div>
					</c:otherwise>
				</c:choose>
				
				<%-- Submit button --%>
				<button type="submit" class="btn btn-primary"><fmt:message key="register.button" /></button>
			</form>
		</div>
	</div>
</div>
<%@ include file="inc/footer.jsp"%>
</body>
</html>