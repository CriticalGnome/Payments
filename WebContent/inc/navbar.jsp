<%@page import="org.apache.catalina.startup.HomesUserDatabase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:choose>
	<c:when test="${ pageContext.request.servletPath == '/index.jsp' }">
		<c:set var="homeClass" value="active" />
	</c:when>
	<c:when test="${ pageContext.request.servletPath == '/about.jsp' }">
		<c:set var="aboutClass" value="active" />
	</c:when>
	<c:when test="${ pageContext.request.servletPath == '/userarea.jsp' }">
		<c:set var="userareaClass" value="active" />
	</c:when>
	<c:when test="${ pageContext.request.servletPath == '/payments.jsp' }">
		<c:set var="paymentsClass" value="active" />
	</c:when>
	<c:when test="${ pageContext.request.servletPath == '/adminarea.jsp' }">
		<c:set var="adminareaClass" value="active" />
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>
<nav class="navbar navbar-default">
	<div class="container">
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="${ homeClass }"><a href="index.jsp"><fmt:message key="nav.home" /></a></li>
				<li class="${ aboutClass }"><a href="about.jsp"><fmt:message key="nav.about" /></a></li>
				<c:if test="${sessionScope.role == 'User' or sessionScope.role == 'Admin'}">
					<li class="${ userareaClass }"><a href="controller?action=userarea"><fmt:message key="nav.account" /></a></li>
				</c:if>
				<c:if test="${sessionScope.role == 'User' or sessionScope.role == 'Admin'}">
					<li class="${ paymentsClass }"><a href="controller?action=payments"><fmt:message key="nav.payments" /></a></li>
				</c:if>
				<c:if test="${sessionScope.role == 'Admin'}">
					<li class="${ adminareaClass }"><a href="controller?action=adminarea"><fmt:message key="nav.admin" /></a></li>
				</c:if>
			</ul>
			<c:if test="${empty sessionScope.isAuthorized}" >
				<form action="login.jsp" class="navbar-form navbar-right">
					<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;<fmt:message key="nav.button.login" /></button>
				</form>
			</c:if>
			<c:if test="${sessionScope.isAuthorized == 'yes'}" >
				<form action="controller" class="navbar-form navbar-right">
					<input type="hidden" name="action" value="logout">
					<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;<fmt:message key="nav.button.logout" /></button>
				</form>
			</c:if>
			<form action="controller" method="POST" class="navbar-form navbar-right">
				<input type="hidden" name="action" value="changelocale">
				<button type="submit" name="lang" value="en" class="btn btn-default"><span class="glyphicon glyphicon-globe" aria-hidden="true"></span></button>
			</form>
		</div>
	</div>
</nav>
<%-- Alert area --%>
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
<c:if test="${param.action == 'wronglogin'}" >
	<c:set var="alertMessage" value="login.wrong.pass" />
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