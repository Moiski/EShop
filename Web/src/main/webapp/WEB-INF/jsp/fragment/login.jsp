<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<jsp:useBean id="user" class="by.moiski.dao.entities.User" scope="request"></jsp:useBean>

<div id="loginForm">
	<c:if test="${loginInfoMessage ne null}">
		<div class="alert alert-info" role="alert">
			<h4>
				<i>${loginInfoMessage}</i>
			</h4>
		</div>
	</c:if>
	<sf:form method="POST" action="${pageContext.request.contextPath}/users/login" modelAttribute="user">
		<div class="form-group">
			<label for="login"><s:message code="message.login.form.login"/></label>
			<sf:input path="login"
				id="login"
				placeholder="Login"
				class="form-control"/>
		</div>
		<div class="form-group">
			<label for="password"><s:message code="message.login.form.password"/></label>
			<sf:input path="password"
				id="password"
				placeholder="Password"
				class="form-control"/>
		</div>
		<button class="btn btn-info" type="submit"><s:message code="message.login.form.button.login"/></button>&nbsp;
		<a href="${pageContext.request.contextPath}/users/getregistrationform"><s:message code="message.login.form.button.reg"/></a>
	</sf:form>
</div>