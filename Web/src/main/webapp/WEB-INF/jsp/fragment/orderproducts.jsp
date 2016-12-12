<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="table-responsive">
	<table class ="table table-bordered table-striped ">
	<caption><b><s:message code="message.order.all.products"/></b></caption>
		<tr class="info">
			<th><s:message code="message.product.name"/></th>
			<th><s:message code="message.product.description"/></th>
			<th><s:message code="message.product.cost"/></th>
			<th><s:message code="message.order.all.products.action"/></th>
		</tr>
	<c:forEach var="product" items="${cartlistuser}" varStatus="status">
		<tr>
			<td><c:out value="${product.name}"/></td>
			<td><c:out value="${product.description}"/></td>
			<td><c:out value="${product.price}"/></td>
			<td>
				<form id="removeFromCartProduct" action="${pageContext.request.contextPath}/cart/delete/product" method="POST" >
					<input type="hidden" name="productId" value="${product.productID}" />
					<button class="btn btn-default btn-block btn-info" type="submit"><s:message code="message.order.all.products.action.delete"/></button>
				</form>
			</td>
		</tr>
	</c:forEach>
	</table>
	<form id="makeOrder" action="${pageContext.request.contextPath}/add/order" method="POST">
		<button class="btn btn-info" type="submit"><s:message code="message.order.all.products.action.save"/></button>
	</form>
</div>