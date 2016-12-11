<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="table-responsive">
	<table class ="table table-bordered table-striped ">
	<caption align="top"><b>All products</b></caption>
		<tr class="info" align="center">
			<th>Name</th>
			<th>Description</th>
			<th>Category</th>
			<th>Price</th>
		</tr>
	<c:forEach var="product" items="${productsList}" varStatus="status">
		<tr>
			<td><c:out value="${product.name}"/></td>
			<td><c:out value="${product.description}"/></td>
			<td><c:out value="${product.category.name}"/></td>
			<td><c:out value="${product.price}"/></td>
		</tr>
	</c:forEach>
	</table>
</div>