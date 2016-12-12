<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="panel-heading">
	<c:if test="${(sessionScope.userType eq 'ADMIN')}">
		<b><i><s:message code="message.welcome"/> ${sessionScope.userLogin}!</i></b>
	</c:if>
</div>
<div class="panel panel-success">
	<div class="panel-heading">
		<i class="panel-title"><s:message code="message.admin.bar"/></i>
	</div>
	<div class="panel-body">
		<form id="showAllProductsButton" action="${pageContext.request.contextPath}/products/all" method="GET" >
			<input class="btn btn-default btn-block btn-info" type="submit" value="<s:message code="message.admin.bar.all.products"/>"/>
		</form><br/>	
		<form id="showAllUsersButton" action="${pageContext.request.contextPath}/users/all" method="GET" >
			<input class="btn btn-default btn-block btn-info" type="submit" value="<s:message code="message.admin.bar.all.users"/>" />
		</form><br/>	
		<form id="addNewProductButton" action="${pageContext.request.contextPath}/products/add" method="GET" >
			<input class="btn btn-default btn-block btn-info" type="submit" value="<s:message code="message.admin.bar.add.product"/>" />
		</form>	
	</div>
</div>
<form action="${pageContext.request.contextPath}/logout" method="GET">
	<button class="btn btn-info" type="submit"><s:message code="message.logout"/></button>
</form>