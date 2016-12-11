<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel-heading">
	<c:if test="${(sessionScope.userType eq 'ADMIN')}">
		<b><i>Hello, ${sessionScope.userLogin}!</i></b>
	</c:if>
</div>
<div class="panel panel-success">
	<div class="panel-heading">
		<i class="panel-title">Only for Administrator</i>
	</div>
	<div class="panel-body">
		<form id="showAllProductsButton" action="${pageContext.request.contextPath}/products/all" method="GET" >
			<input class="btn btn-default btn-block btn-info" type="submit" value="Show all products" />
		</form><br/>	
		<form id="showAllUsersButton" action="${pageContext.request.contextPath}/users/all" method="GET" >
			<input class="btn btn-default btn-block btn-info" type="submit" value="Show all users" />
		</form><br/>	
		<form id="addNewProductButton" action="${pageContext.request.contextPath}/products/add" method="GET" >
			<input class="btn btn-default btn-block btn-info" type="submit" value="Add new product" />
		</form>	
	</div>
</div>
<form action="${pageContext.request.contextPath}/logout" method="GET">
	<button class="btn btn-info" type="submit">Log out</button>
</form>