<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="table-responsive">
	<table class ="table table-bordered table-striped ">
	<caption><b>All the products in your cart</b></caption>
		<tr class="info">
			<th>Name product</th>
			<th>Description</th>
			<th>Price</th>
			<th>Action</th>
		</tr>
	<c:forEach var="product" items="${cartlistuser}" varStatus="status">
		<tr>
			<td><c:out value="${product.name}"/></td>
			<td><c:out value="${product.description}"/></td>
			<td><c:out value="${product.price}"/></td>
			<td>
				<form id="removeFromCartProduct" action="${pageContext.request.contextPath}/cart/delete/product" method="POST" >
					<input type="hidden" name="productId" value="${product.productID}" />
					<button class="btn btn-default btn-block btn-info" type="submit">Remove</button>
				</form>
			</td>
		</tr>
	</c:forEach>
	</table>
	<form id="makeOrder" action="${pageContext.request.contextPath}/add/order" method="POST">
		<button class="btn btn-info" type="submit">Make order</button>
	</form>
</div>