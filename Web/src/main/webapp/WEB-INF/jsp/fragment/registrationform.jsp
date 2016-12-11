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
			<h3>Registration form</h3>
			<br />
			<div id="regLogin" class="form-group">
				<sf:errors path="login" cssClass="error" />
				<div class="col-xs-2">
					<label for="loginData" class="control-label">Login:</label>
				</div>
				<div class="col-xs-4">
					<sf:input path="login" 
						id="regLoginData" 
						required="required"
						placeholder="login" 
						pattern="[a-zA-Z0-9]{3,10}"
						onselect="checkEnteredData('regLogin')"
						oninput="checkEnteredData('regLogin')" 
						class="form-control" />
					<span id="regLoginIcon"></span>
					<span class="help-block">Only latin letters and/or numbers (no less than 3 and no more than 10)</span>
				</div>
			</div>
			<div id="regPassword" class="form-group">
				<sf:errors path="password" cssClass="error" />
				<div class="col-xs-2">
					<label for="regPasswordData" class="control-label">Password:</label>
				</div>
				<div class="col-xs-4">
					<sf:input path="password" 
					id="regPasswordData" 
					required="required"
					placeholder="password" 
					pattern="[a-zA-Z0-9]{3,10}"
					oninput="checkEnteredData('regPassword')" 
					class="form-control" />
					<span id="regPasswordIcon"></span> 
					<span class="help-block">Only latin letters and/or numbers (no less than 3 and no more than 10)</span>
				</div>
			</div>
			<div id="email" class="form-group">
				<sf:errors path="email" cssClass="error" />
				<div class="col-xs-2">
					<label for="emailData" class="control-label">E-mail:</label>
				</div>
				<div class="col-xs-4">
					<sf:input path="email" 
						id="emailData" 
						required="required"
						placeholder="emailaddress@gmail.com"
						pattern="[a-zA-Z0-9.]+@[a-zA-Z0-9]+\.[a-zA-Z]+"
						oninput="checkEnteredData('email')" 
						class="form-control" />
					<span id="emailIcon"></span>
					<span class="help-block">For example: moiski@yandex.ru></span>
				</div>
			</div>
			<div id="firstName" class="form-group">
				<sf:errors path="firstname" cssClass="error" />
				<div class="col-xs-2">
					<label for="firstNameData" class="control-label">First Name:</label>
				</div>
				<div class="col-xs-4">
					<sf:input path="firstname" 
						id="firstNameData" 
						required="required"
						placeholder="first name" 
						pattern="[a-zA-Zа-яА-ЯёЁ ]+"
						oninput="checkEnteredData('firstName')" 
						class="form-control" />
					<span id="firstNameIcon"></span>
					<span class="help-block">Latin letters and/or Cyrillic, separated by spaces</span>
				</div>
			</div>
			<div id="lastName" class="form-group">
				<sf:errors path="lastname" cssClass="error" />
				<div class="col-xs-2">
					<label for="lastNameData" class="control-label">Last Name:</label>
				</div>
				<div class="col-xs-4">
					<sf:input path="lastname" 
						id="lastNameData" 
						required="required"
						placeholder="last name" 
						pattern="[a-zA-Zа-яА-ЯёЁ ]+"
						oninput="checkEnteredData('lastName')" 
						class="form-control" />
					<span id="lastNameIcon"></span>
					<span class="help-block">Latin letters and/or Cyrillic, separated by spaces</span>
				</div>
			</div>
			<div id="shipAddress" class="form-group">
				<div class="col-xs-2">
					<label for="shipAddressData" class="control-label">Shipping address:</label>
				</div>
				<div class="col-xs-4">
					<sf:textarea path="shippingAddress" 
						id="shipAddressData"
						required="required" 
						placeholder="Your Shipping Address"
						oninput="checkEnteredData('shipAddress')" 
						class="form-control"
						rows="5" />
					<span id="shipAddressIcon"></span>
				</div>
			</div>
			<span>All fields are required</span>
			<br />
			<br />
			<button class="btn btn-info" type="submit">Registration</button>
			<button class="btn btn-default" type="reset">Reset</button>
		</sf:form>
	</div>
</div>