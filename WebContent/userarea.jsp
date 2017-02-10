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
<title><fmt:message key="title.userarea" /></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<form class="form-horizontal">
  				<div class="form-group">
				    <label for="firstName" class="col-sm-4 control-label">First Name</label>
    				<div class="col-sm-8">
      					<input type="text" class="form-control" name="firstName" value="${user.firstName}">
    				</div>
  				</div>
  				<div class="form-group">
				    <label for="lastName" class="col-sm-4 control-label">Last Name</label>
    				<div class="col-sm-8">
      					<input type="text" class="form-control" name="lastName" value="${user.lastName}">
    				</div>
  				</div>
  				<div class="form-group">
				    <label for="email" class="col-sm-4 control-label">Email</label>
    				<div class="col-sm-8">
      					<input type="text" class="form-control" name="email" value="${user.email}" disabled>
    				</div>
  				</div>
  				<div class="form-group">
				    <label for="account" class="col-sm-4 control-label">Account</label>
    				<div class="col-sm-8">
      					<input type="text" class="form-control" name="account" value="${account.number}" disabled>
    				</div>
  				</div>
  				<div class="form-group">
				    <label for="amount" class="col-sm-4 control-label">Amount</label>
    				<div class="col-sm-8">
    					<div class="input-group">
        					<span class="input-group-addon">$</span>
      						<input type="text" class="form-control" name="amount" value="${account.amount}" disabled>
      					</div>
    				</div>
  				</div>
	  			<c:forEach var="i" begin="1" end="${cardsCount}">
	  				<div class="form-group">
					    <label for="card${i}" class="col-sm-4 control-label">Card <c:out value="${i}"/></label>
	    				<div class="col-sm-5">
    	  					<input type="text" class="form-control" name="card${i}" value="${cards[i-1].number}" disabled>
    					</div>
					    <label for="exp${i}" class="col-sm-1 control-label">Exp</label>
    					<div class="col-sm-2">
      						<input type="text" class="form-control" name="exp${i}" value="${cards[i-1].expMonth}/${cards[i-1].expYear}" disabled>
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