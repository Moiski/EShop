<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="table-responsive">
	<table class ="table table-bordered table-striped">
		<caption><b>All your Orders</b></caption>
			<tr class="info">
				<th>Order ID</th>
				<th>Common Price, BY</th>
				<th>State</th>
				<th>Products List</th>
			</tr>
		<c:forEach var="order" items="${orderlistuser}" varStatus="status">
			<tr>
				<td><c:out value="${order.orderId}" /></td>
				<td><c:out value="${order.amountPurchase}" /></td>
				<td><c:out value="${order.orderState}" /></td>
				<td>
					<c:forEach var="orderdetail" items="${order.orderDetails}" >
						<c:out value="[${orderdetail.product.name} : " />
						<c:out value="${orderdetail.product.description} - " />
						<c:out value="${orderdetail.product.price} RUB.] " />
					</c:forEach>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>