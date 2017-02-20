<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:if test="${empty sessionScope.locale}" >
    <fmt:setBundle basename="locale_en_US" />
</c:if>
<c:if test="${not empty sessionScope.locale}">
    <fmt:setBundle basename="${locale}" />
</c:if>