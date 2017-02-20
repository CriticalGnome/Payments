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
<title><fmt:message key="title.about" /></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<h2><fmt:message key="about.header" /></h2>
			<blockquote><fmt:message key="about.quote" /></blockquote>
			
			<p><fmt:message key="about.line1" /></p>
			<p><fmt:message key="about.line2" /><p>
		</div>
		<div class="col-sm-4 col-sm-offset-1">
			<table class="table">
				<tr>
					<th><fmt:message key="about.table.header1" /></th>
					<th><fmt:message key="about.table.header2" /></th>
					<th><fmt:message key="about.table.header3" /></th>
				</tr>
				<tr>
					<td>admin@test.com</td>
					<td>123</td>
					<td><fmt:message key="about.table.role.admin" /></td>
				</tr>
				<tr>
					<td>user@test.com</td>
					<td>123</td>
					<td><fmt:message key="about.table.role.user" /></td>
				</tr>
			</table>
		</div>
		<div class="col-md-10 col-md-offset-1">
			<p><fmt:message key="about.line3" /></p>
			<p><fmt:message key="about.line4" /></p>
			<p><fmt:message key="about.line6" /><code>WebContent/WEB-INF/classes/config.properties</code>:</p>
			<small># <fmt:message key="about.comment1" /></small><br />
			<var>dbdriver</var> = <code>com.mysql.jdbc.Driver</code><br />
			<small># <fmt:message key="about.comment2" /></small><br />
			<var>dburl</var> = <code>jdbc:mysql://localhost:3306/payments?autoReconnect=true&amp;useSSL=false</code><br />
			<small># <fmt:message key="about.comment3" /></small><br />
			<var>dbuser</var> = <code>payments</code><br />
			<small># <fmt:message key="about.comment4" /></small><br />
			<var>dbpassword</var> = <code>payments</code><br />
			<small># <fmt:message key="about.comment5" /></small><br />
			<var>dbmaxconnections</var> = <code>100</code><br />
			<small># <fmt:message key="about.comment6" /></small><br />
			<var>paymentslistmaxrecords</var> = <code>100</code><br />
		</div>
		<div class="col-md-10 col-md-offset-1 text-right">
			<br>
			<address>
				<strong><fmt:message key="about.author" /></strong><br>
				<fmt:message key="about.name" /><br>
				<a href="mailto:lord.skiminok@gmail.com">lord.skiminok@gmail.com</a>
			</address>
		</div>
	</div>
</div>
<%@ include file="inc/footer.jsp"%>
</body>
</html>