<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:setBundle basename="pagelocale"/>
<script type="text/javascript" src="js/form_validation.js" ></script>
<div id="registrForm">
	<form class="form-horizontal" action="controller" method="POST" title="Registration form" >
		<input type="hidden" name="command" value="registration" />
	<h3>Registration form</h3><br/>
		
		<div id="regLogin" class="form-group">
			<div class="col-xs-2">
	   			<label for="loginData" class="control-label">Login:</label>
			</div>
			<div class="col-xs-4">
      			<input id="regLoginData" type="text" name="login" required="required" placeholder="login" pattern="[a-zA-Z0-9]{6,45}" onselect="checkEnteredData('regLogin')" oninput="checkEnteredData('regLogin')" class="form-control" />
      			<span id="regLoginIcon"></span>
      			<span class="help-block">Only latin letters and/or numbers (no less than 6 and no more than 45)</span>
			</div>
    	</div>
    	
    	<div id="regPassword" class="form-group">
			<div class="col-xs-2">
	   			<label for="regPasswordData" class="control-label">Password:</label>
			</div>
			<div class="col-xs-4">
      			<input id="regPasswordData" type="password" name="password" required="required" placeholder="password" pattern="[a-zA-Z0-9]{6,45}" oninput="checkEnteredData('regPassword')" class="form-control"/>
      			<span id="regPasswordIcon"></span>
      			<span class="help-block">Only latin letters and/or numbers (no less than 6 and no more than 45)</span>
			</div>
    	</div>
		
		<div id="email" class="form-group">
			<div class="col-xs-2">
	   			<label for="emailData" class="control-label">E-mail:</label>
			</div>
			<div class="col-xs-4">
      			<input id="emailData" type="email" name="email" required="required" placeholder="emailaddress@gmail.com" pattern="[a-zA-Z0-9.]+@[a-zA-Z0-9]+\.[a-zA-Z]+" oninput="checkEnteredData('email')" class="form-control"/>
      			<span id="emailIcon"></span>
      			<span class="help-block">For example: moiski@yandex.ru></span>
			</div>
    	</div>
    	
    	<div id="firstName" class="form-group">
			<div class="col-xs-2">
	   			<label for="firstNameData" class="control-label">First Name:</label>
			</div>
			<div class="col-xs-4">
      			<input id="firstNameData" type="text" name="firstName" required="required" placeholder="first name" pattern="[a-zA-Zа-яА-ЯёЁ ]+" oninput="checkEnteredData('firstName')" class="form-control"/>
      			<span id="firstNameIcon"></span>
      			<span class="help-block">Latin letters and/or Cyrillic, separated by spaces</span>
			</div>
    	</div>
    	
    	<div id="lastName" class="form-group">
			<div class="col-xs-2">
	   			<label for="lastNameData" class="control-label">Last Name:</label>
			</div>
			<div class="col-xs-4">
      			<input id="lastNameData" type="text" name="lastName" required="required" placeholder="last name" pattern="[a-zA-Zа-яА-ЯёЁ ]+" oninput="checkEnteredData('lastName')" class="form-control"/>
      			<span id="lastNameIcon"></span>
      			<span class="help-block">Latin letters and/or Cyrillic, separated by spaces</span>
			</div>
    	</div>
    	
    	<div id="shipAddress" class="form-group">
			<div class="col-xs-2">
	   			<label for="shipAddressData" class="control-label">Shipping Address:</label>
			</div>
			<div class="col-xs-4">
				<textarea id="shipAddressData" name="shipAddress" placeholder="Your Shipping Address" required="required" oninput="checkEnteredData('shipAddress')" class="form-control" rows="5"></textarea>
      			<span id="shipAddressIcon"></span>
			</div>
    	</div>
		
		<span>All fields are required</span><br/><br/>
		 <button class="btn btn-primary" type="submit">
		 	<span class="glyphicon glyphicon-send" ></span>Registration
		 </button>
		 <button class="btn btn-default" type="reset">Reset</button>
	</form>
</div> 