<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="table-responsive">
	<table class ="table table-bordered table-striped ">
	<caption align="top"><b>All users</b></caption>
		<tr class="info" align="left">
			<th>Registration date</th>
			<th>Login</th>
			<th>Email</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Shipping Address</th>
			<th>User Type</th>
			<th>Black List</th>
			<th>Action</th>
		</tr>
	<c:forEach var="user" items="${usersList}" varStatus="status">
		<tr>
			<td><c:out value="${user.registrDate}"/></td>
			<td><c:out value="${user.login}"/></td>
			<td><c:out value="${user.email}"/></td>
			<td><c:out value="${user.firstname}"/></td>
			<td><c:out value="${user.lastname}"/></td>
			<td><c:out value="${user.shippingAddress}"/></td>
			<td><c:out value="${user.role}"/></td>
			<td><c:out value="${user.blackList}"/></td>
			<td>
				<form id="addAndRemoveInBlackList" action="controller" method="POST">
					<input type="hidden" name="command" value="blackList"/>
					<input type="hidden" name="userid" value="${user.userId}"/>
					<button class="btn btn-default btn-block btn-info" type="submit">Black List</button>
				</form>
			</td>
		</tr>
	</c:forEach>
	</table>
</div>