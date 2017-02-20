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
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/default.css" rel="stylesheet" type="text/css">
	<title><fmt:message key="title.userarea" /></title>
</head>
<body>
<%@ include file="inc/navbar.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3 text-center">
			<h3><fmt:message key="userarea.account" /> ${account.number}</h3>
		</div>
	</div>

	<%-- User area --%>
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<form class="form-horizontal" action="controller" method="POST">
  				<div class="form-group">
				    <label for="firstName" class="control-label col-sm-4"><fmt:message key="userarea.first.name" /></label>
      				<div class="col-sm-8"><input type="text" class="form-control" name="firstName" value="${user.firstName}"></div>
  				</div>
  				<div class="form-group">
				    <label for="lastName" class="col-sm-4 control-label"><fmt:message key="userarea.last.name" /></label>
    				<div class="col-sm-8"><input type="text" class="form-control" name="lastName" value="${user.lastName}"></div>
  				</div>
  				<input type="hidden" name="action" value="updateNames">
  				<div class="text-right"><button type="submit" class="btn btn-default"><fmt:message key="userarea.confirm.changes" /></button></div>
  			</form>
  		</div>
  	</div>
  	<br>

  	<%-- Balance area --%>
  	<div class="row">
  		<div class="col-md-6 col-md-offset-3 text-center">
			<h3><fmt:message key="userarea.balance" />: $${account.amount}</h3>
		</div>
	</div>
	
  	<%-- Add balance area --%>
  	<div class="row">
  		<div class="col-md-6 col-md-offset-3">
			<c:if test="${!account.isBlocked}">
				<form class="form-horizontal" action="controller" method="POST">
					<input type="hidden" name="action" value="updateAmount">
					<input type="hidden" name="amount" value="${account.amount}">
  					<div class="form-group">
				    	<label for="amountAdd" class="col-sm-4 control-label"><fmt:message key="userarea.balance.add" /></label>
    					<div class="col-sm-5">
    						<div class="input-group">
    							<span class="input-group-addon">$</span>
    							<input type="text" class="form-control" name="amountAdd" value="0">
    						</div>
    					</div>
						<div class="col-sm-3 text-right"><button type="submit" class="btn btn-default"><fmt:message key="userarea.confirm.add" /></button></div>
    				</div>
	  			</form>
			</c:if>
			<c:if test="${account.isBlocked}">
				<div class="text-center">
					<p class="bg-danger"><fmt:message key="userarea.blocked" /></p>
				</div>
			</c:if>
		</div>
	</div>
	<br>

	<%-- Account area --%>
	<div class="row">
		<div class="col-md-6 col-md-offset-3 text-center">
				<c:if test="${!account.isBlocked}">
					<form class="form-horizontal" action="controller" method="POST">
						<input type="hidden" name="action" value="blockAccount">
						<button class="btn btn-success" type="button" data-toggle="modal" data-target=".modal-send"><fmt:message key="userarea.send" /></button>
						<button class="btn btn-danger" type="button" data-toggle="modal" data-target=".modal-warning"><fmt:message key="userarea.block" /></button>
		  			</form>
				</c:if>
  		</div>
  	</div>
	<br>

	<%-- Cards area --%>
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="text-center"><h3><fmt:message key="userarea.cards" /></h3></div>
			<table class="table table-condensed">
				<tr>
					<th>N</th>
					<th>NUMBER</th>
					<th>EXP</th>
				</tr>
				<c:forEach items="${cards}" var="item" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${item.number}</td>
						<td>${item.expMonth}/${item.expYear}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

</div>

<%-- Warning Modal Windows --%>
<div id="modalWarning" class="modal fade modal-warning" tabindex="1" role="dialog" aria-labelledby="modalWarning">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
				<h3 class="modal-title"><fmt:message key="userarea.modal.header" /></h3>
			</div>
			<div class="modal-body">
				<fmt:message key="userarea.modal.text" />
			</div>
			<div class="modal-footer">
				<form action="controller" method="POST">
					<input type="hidden" name="action" value="blockAccount">
					<button class="btn btn-danger" type="submit"><fmt:message key="userarea.block" /></button>
					<button class="btn btn-default" type="button" data-dismiss="modal"><fmt:message key="userarea.modal.cancel" /></button>
				</form>
			</div>
		</div>
	</div>
</div>

<%-- Send Funds Modal Windows --%>
<div id="modalSendFunds" class="modal fade modal-send" tabindex="2" role="dialog" aria-labelledby="modalSend">
	<div class="modal-dialog">
		<div class="modal-content">
			<form action="controller" method="POST">
				<div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
					<h3 class="modal-title"><fmt:message key="userarea.send" /></h3>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="amountSend"><fmt:message key="userarea.sum" /></label>
						<input name="amount" id="amountSend" class="form-control" type="text" value="0" />
					</div>
					<div class="form-group">
						<label for="commentSend"><fmt:message key="userarea.comment" /></label>
						<textarea name="comment" id="commentSend" class="form-control" rows="3" placeholder="<fmt:message key="userarea.comment.placeholder" />"></textarea>
					</div>
					<div class="form-group">
						<label for="acountId"><fmt:message key="userarea.dest" /></label>
						<select name="toAccount" name="accountId" class="form-control" size="5">
							<c:forEach items="${availableAccounts}" var="item">
								<c:if test="${item.id != account.id}">
									<option value="${item.id}">[${item.number}] ${item.firstName} ${item.lastName}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="modal-footer">
					<input name="action" id="actionSend" type="hidden" value="sendFunds">
					<input name="fromAccount" type="hidden" value="${account.id}">
					<button name="submit" name="sendSubmit" class="btn btn-success" type="submit"><fmt:message key="userarea.modal.send" /></button>
					<button name="button" name="sendCancel" class="btn btn-default" type="button" data-dismiss="modal"><fmt:message key="userarea.modal.cancel" /></button>
				</div>
			</form>
		</div>
	</div>
</div>

<%@ include file="inc/footer.jsp"%>
</body>
</html>