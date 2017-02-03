<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<nav class="navbar navbar-default">
	<div class="container">
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="index.jsp"><fmt:message key="nav.home" /></a></li>
				<li><a href="#"><fmt:message key="nav.about" /></a></li>
				<c:if test="${sessionScope.role == 'User' or sessionScope.role == 'Admin'}">
					<li><a href="#"><fmt:message key="nav.account" /></a></li>
				</c:if>
				<c:if test="${sessionScope.role == 'Admin'}">
					<li><a href="#"><fmt:message key="nav.admin" /></a></li>
				</c:if>
			</ul>
			<form action="login.jsp" class="navbar-form navbar-right">
			<c:if test="${empty sessionScope.isAuthorized}" >
				<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;<fmt:message key="nav.button.login" /></button>
			</c:if>
			<c:if test="${sessionScope.isAuthorized == 'yes'}" >
				<input type="hidden" name="action" value="logout">
				<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;<fmt:message key="nav.button.logout" /></button>
			</c:if>
			</form>
			<form action="index.jsp" class="navbar-form navbar-right">
			<c:if test="${sessionScope.locale == 'locale_en_US' or empty sessionScope.locale}" >
				<button type="submit" name="lang" value="ru" class="btn btn-default"><span class="glyphicon glyphicon-globe" aria-hidden="true"></span></button>
			</c:if>
			<c:if test="${sessionScope.locale == 'locale_ru_RU'}">
				<button type="submit" name="lang" value="en" class="btn btn-default"><span class="glyphicon glyphicon-globe" aria-hidden="true"></span></button>
			</c:if>
			</form>
		</div>
	</div>
</nav>
