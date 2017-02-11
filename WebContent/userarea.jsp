<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="inc/uselocale.jsp" %>
<c:if test="${empty user}">
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
		<div class="col-md-6 col-md-offset-3">
			<form class="form-horizontal" action="controller" method="POST">
  				<div class="form-group">
				    <label for="firstName" class="col-sm-4 control-label"><fmt:message key="userarea.first.name" /></label>
    				<div class="col-sm-8">
      					<input type="text" class="form-control" name="firstName" value="${user.firstName}">
    				</div>
  				</div>
  				<div class="form-group">
				    <label for="lastName" class="col-sm-4 control-label"><fmt:message key="userarea.last.name" /></label>
    				<div class="col-sm-8">
      					<input type="text" class="form-control" name="lastName" value="${user.lastName}">
    				</div>
  				</div>
  				<div class="form-group">
				    <label for="email" class="col-sm-4 control-label"><fmt:message key="userarea.email" /></label>
    				<div class="col-sm-8">
      					<input type="text" class="form-control" name="email" value="${user.email}" readonly>
    				</div>
  				</div>
  				<input type="hidden" name="action" value="updateNames">
  				<div class="text-right"><button type="submit" class="btn btn-default"><fmt:message key="userarea.confirm.changes" /></button></div>
  			</form>
  			<br>
			<form class="form-horizontal" action="controller" method="POST">
  				<div class="form-group">
				    <label for="account" class="col-sm-4 control-label"><fmt:message key="userarea.account" /></label>
    				<div class="col-sm-8">
      					<input type="text" class="form-control" name="account" value="${account.number}" readonly>
    				</div>
  				</div>
				<input type="hidden" name="action" value="blockAccount">
				<c:if test="${account.isBlocked}">
				<div class="text-right"><button type="button" class="btn btn-default disabled"><fmt:message key="userarea.blocked" /></button></div>
				</c:if>
				<c:if test="${!account.isBlocked}">
				<div class="text-right"><button type="submit" class="btn btn-danger"><fmt:message key="userarea.block" /></button></div>
				</c:if>
  			</form>
  			<br>
			<form class="form-horizontal" action="controller" method="POST">
  				<div class="form-group">
				    <label for="amount" class="col-sm-4 control-label"><fmt:message key="userarea.balance" /></label>
    				<div class="col-sm-8">
    					<div class="input-group">
        					<span class="input-group-addon">$</span>
      						<input type="text" class="form-control" name="amount" value="${account.amount}" readonly>
      					</div>
    				</div>
  				</div>
				<c:if test="${!account.isBlocked}">
  				<div class="form-group">
				    <label for="amountAdd" class="col-sm-4 control-label"><fmt:message key="userarea.balance.add" /></label>
    				<div class="col-sm-8">
    					<div class="input-group">
        					<span class="input-group-addon">$</span>
      						<input type="text" class="form-control" name="amountAdd" value="0">
      					</div>
    				</div>
  				</div>
				<input type="hidden" name="action" value="updateAmount">
				<div class="text-right"><button type="submit" class="btn btn-default"><fmt:message key="userarea.confirm.add" /></button></div>
				</c:if>
  			</form>
  			<br>
			<form class="form-horizontal">
	  			<c:forEach var="i" begin="1" end="${cardsCount}">
	  				<div class="form-group">
					    <label for="card${i}" class="col-sm-4 control-label"><fmt:message key="userarea.card" /> <c:out value="${i}"/></label>
	    				<div class="col-sm-5">
    	  					<input type="text" class="form-control" name="card${i}" value="${cards[i-1].number}" readonly>
    					</div>
					    <label for="exp${i}" class="col-sm-1 control-label"><fmt:message key="userarea.exp" /></label>
    					<div class="col-sm-2">
      						<input type="text" class="form-control" name="exp${i}" value="${cards[i-1].expMonth}/${cards[i-1].expYear}" readonly>
    					</div>
  					</div>
  				</c:forEach>
  			</form>
		</div>
	</div>
</div>
<%@ include file="inc/footer.jsp"%>
</body>
</html>