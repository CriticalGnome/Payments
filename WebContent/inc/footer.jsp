<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<br>
<footer class="navbar-bottom panel-footer">
<div class="container">
	<div class="row">
		<div class="col-md-2">

		</div>
		<div class="col-md-8 text-center">
			site by <a href="mailto:web3oha@gmail.com">Web3OHA</a>
		</div>
		<div class="col-md-2">
			<form action="controller" method="POST">
				<input type="hidden" name="action" value="changelocale"> 
				<select name="locale" class="form-control input-sm" onchange="if (this.selectedIndex) this.form.submit ()">
					<option><fmt:message key="footer.language" /></option>
					<option value="default">English</option>
					<option value="ru">Русский</option>
				</select>
			</form>
		</div>
	</div>
</div>
</footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
