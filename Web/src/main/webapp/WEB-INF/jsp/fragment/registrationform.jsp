<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<jsp:useBean id="user" class="by.moiski.dao.entities.User" scope="request"></jsp:useBean>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/form_validation.js"></script>

<div class="alert alert-info" role="alert">
	<div id="registrForm">
		<c:if test="${regInfoMessage ne null}">
			<div class="alert alert-info" role="alert">
				<h4>
					<i>${regInfoMessage}</i>
				</h4>
			</div>
		</c:if>
		<sf:form class="form-horizontal" action="${pageContext.request.contextPath}/users/addnewuser" method="POST" 
				modelAttribute="user">
			<h3><s:message code="message.reg.form"/></h3>
			<br />
			<div id="regLogin" class="form-group">
				<sf:errors path="login" cssClass="error" />
				<div class="col-xs-2">
					<label for="loginData" class="control-label"><s:message code="message.reg.form.login"/></label>
				</div>
				<div class="col-xs-4">
					<sf:input path="login" 
						id="regLoginData" 
						required="required"
						placeholder="lkpo" 
						pattern="[a-zA-Z0-9]{3,10}"
						onselect="checkEnteredData('regLogin')"
						oninput="checkEnteredData('regLogin')" 
						class="form-control" />
					<span id="regLoginIcon"></span>
					<span class="help-block"><s:message code="message.reg.form.login.help"/></span>
				</div>
			</div>
			<div id="regPassword" class="form-group">
				<sf:errors path="password" cssClass="error" />
				<div class="col-xs-2">
					<label for="regPasswordData" class="control-label"><s:message code="message.reg.form.password"/></label>
				</div>
				<div class="col-xs-4">
					<sf:input path="password" 
					id="regPasswordData" 
					required="required"
					placeholder="123" 
					pattern="[a-zA-Z0-9]{3,10}"
					oninput="checkEnteredData('regPassword')" 
					class="form-control" />
					<span id="regPasswordIcon"></span> 
					<span class="help-block"><s:message code="message.reg.form.password.help"/></span>
				</div>
			</div>
			<div id="email" class="form-group">
				<sf:errors path="email" cssClass="error" />
				<div class="col-xs-2">
					<label for="emailData" class="control-label"><s:message code="message.reg.form.email"/></label>
				</div>
				<div class="col-xs-4">
					<sf:input path="email" 
						id="emailData" 
						required="required"
						placeholder="moiski@yandex.ru"
						pattern="[a-zA-Z0-9.]+@[a-zA-Z0-9]+\.[a-zA-Z]+"
						oninput="checkEnteredData('email')" 
						class="form-control" />
					<span id="emailIcon"></span>
					<span class="help-block"><s:message code="message.reg.form.email.help"/></span>
				</div>
			</div>
			<div id="firstName" class="form-group">
				<sf:errors path="firstname" cssClass="error" />
				<div class="col-xs-2">
					<label for="firstNameData" class="control-label"><s:message code="message.reg.form.user.name"/></label>
				</div>
				<div class="col-xs-4">
					<sf:input path="firstname" 
						id="firstNameData" 
						required="required"
						placeholder="Kostya" 
						pattern="[a-zA-Zа-яА-ЯёЁ ]+"
						oninput="checkEnteredData('firstName')" 
						class="form-control" />
					<span id="firstNameIcon"></span>
					<span class="help-block"><s:message code="message.reg.form.user.name.help"/></span>
				</div>
			</div>
			<div id="lastName" class="form-group">
				<sf:errors path="lastname" cssClass="error" />
				<div class="col-xs-2">
					<label for="lastNameData" class="control-label"><s:message code="message.reg.form.user.surname"/></label>
				</div>
				<div class="col-xs-4">
					<sf:input path="lastname" 
						id="lastNameData" 
						required="required"
						placeholder="Moiski" 
						pattern="[a-zA-Zа-яА-ЯёЁ ]+"
						oninput="checkEnteredData('lastName')" 
						class="form-control" />
					<span id="lastNameIcon"></span>
					<span class="help-block"><s:message code="message.reg.form.user.surname.help"/></span>
				</div>
			</div>
			<div id="shipAddress" class="form-group">
				<div class="col-xs-2">
					<label for="shipAddressData" class="control-label"><s:message code="message.reg.form.user.address"/></label>
				</div>
				<div class="col-xs-4">
					<sf:textarea path="shippingAddress" 
						id="shipAddressData"
						required="required" 
						placeholder="Minsk, st. Shpilevskogo"
						oninput="checkEnteredData('shipAddress')" 
						class="form-control"
						rows="5" />
					<span id="shipAddressIcon"></span>
				</div>
			</div>
			<span><s:message code="message.reg.form.help"/></span>
			<br />
			<br />
			<button class="btn btn-info" type="submit"><s:message code="message.reg.form.button.reg"/></button>
			<button class="btn btn-default" type="reset"><s:message code="message.reg.form.button.res"/></button>
		</sf:form>
	</div>
</div>