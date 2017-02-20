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
	<link href="css/default.css" rel="stylesheet" type="text/css">
	<title><fmt:message key="title.index" /></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<%-- Main page area --%>
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
				<img src="images/tn1.jpg" alt="">
				<div class="caption">
					<h3><fmt:message key="tmb1.label" /></h3>
					<p><fmt:message key="tmb1.text" /></p>
					<p><a href="#" class="btn btn-primary" role="button"><fmt:message key="tmb1.button" /></a></p>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="thumbnail">
				<img src="images/tn2.jpg" alt="">
				<div class="caption">
					<h3><fmt:message key="tmb2.label" /></h3>
					<p><fmt:message key="tmb2.text" /></p>
					<p><a href="#" class="btn btn-primary" role="button"><fmt:message key="tmb2.button" /></a></p>
				</div>
			</div>
		</div>
		<div class="col-md-4">
			<div class="thumbnail">
				<img src="images/tn3.jpg" alt="">
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