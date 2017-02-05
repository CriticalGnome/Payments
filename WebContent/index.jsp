<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:if test="${param.lang == 'en'}" >
	<c:set var="locale" value="locale_en_US" scope="session" />
</c:if>
<c:if test="${param.lang == 'ru'}" >
	<c:set var="locale" value="locale_ru_RU" scope="session" />
</c:if>
<%@ include file="inc/uselocale.jsp" %>
<!DOCTYPE html>
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
<c:if test="${param.action == 'newuser'}" >
	<c:set var="alertMessage" value="messages.register.success" />
	<c:set var="alertColor" value="alert-success" />
</c:if>
<c:if test="${param.action == 'login'}" >
	<c:set var="alertMessage" value="messages.login.success" />
	<c:set var="alertColor" value="alert-success" />
</c:if>
<c:if test="${param.action == 'logout'}" >
	<c:set var="alertMessage" value="messages.logout.success" />
	<c:set var="alertColor" value="alert-warning" />
</c:if>
<c:if test="${not empty param.lang}" >
	<c:set var="alertMessage" value="messages.lang.change" />
	<c:set var="alertColor" value="alert-info" />
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
<div class="jumbotron" style="background: url(images/jumbo_bg.jpg) no-repeat center center fixed; -webkit-background-size: cover; -moz-background-size: cover; -o-background-size: cover; background-size: cover; color: white;">
	<div class="container">
		<h1 class="placeholder" style="text-shadow: black 0.1em 0.1em 0.1em;"><fmt:message key="jumbotron.header" /></h1>
		<p style="text-shadow: black 0.1em 0.1em 0.1em;"><fmt:message key="jumbotron.text" /></p>
		<p>
			<a class="btn btn-primary btn-lg" href="#" role="button"><fmt:message key="jumbotron.button" /></a>
		</p>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-4">
			<div class="thumbnail">
				<img src="images/placeholder_1280x800.jpg" alt="">
				<div class="caption">
					<h3><fmt:message key="tmb1.label" /></h3>
					<p><fmt:message key="tmb1.text" /></p>
					<p><a href="#" class="btn btn-primary" role="button"><fmt:message key="tmb1.button" /></a></p>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="thumbnail">
				<img src="images/placeholder_1280x800.jpg" alt="">
				<div class="caption">
					<h3><fmt:message key="tmb2.label" /></h3>
					<p><fmt:message key="tmb2.text" /></p>
					<p><a href="#" class="btn btn-primary" role="button"><fmt:message key="tmb2.button" /></a></p>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="thumbnail">
				<img src="images/placeholder_1280x800.jpg" alt="">
				<div class="caption">
					<h3><fmt:message key="tmb3.label" /></h3>
					<p><fmt:message key="tmb3.text" /></p>
					<p><a href="#" class="btn btn-primary" role="button"><fmt:message key="tmb3.button" /></a></p>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<p class="text-justify"><fmt:message key="index.main.text" /></p>
		</div>
	</div>
</div>
<%@ include file="inc/footer.jsp"%>
</body>
</html>