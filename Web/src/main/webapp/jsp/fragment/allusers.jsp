<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="table-responsive">
	<table class ="table table-bordered table-striped ">
	<caption><b>All users</b></caption>
		<tr class="info">
			<th>User ID</th>
			<th>Registration date</th>
			<th>Login</th>
			<th>Email</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Shipping Address</th>
			<th>User Type</th>
			<th>Black List</th>
		</tr>
	<c:forEach var="user" items="${usersList}" varStatus="status">
		<tr>
			<td><c:out value="${user.userId}"/></td>
			<td><c:out value="${user.registrDate}"/></td>
			<td><c:out value="${user.login}"/></td>
			<td><c:out value="${user.email}"/></td>
			<td><c:out value="${user.firstname}"/></td>
			<td><c:out value="${user.lastname}"/></td>
			<td><c:out value="${user.shippingAddress}"/></td>
			<td><c:out value="${user.role}"/></td>
			<td><c:out value="${user.blackList}"/></td>
		</tr>
	</c:forEach>
	</table>
</div>