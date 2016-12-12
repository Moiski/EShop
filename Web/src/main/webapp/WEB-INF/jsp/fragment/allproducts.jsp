<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="table-responsive">
	<table class ="table table-bordered table-striped ">
	<caption align="top"><b><s:message code="message.admin.product.all"/></b></caption>
		<tr class="info" align="center">
			<th><s:message code="message.product.name"/></th>
			<th><s:message code="message.product.description"/></th>
			<th><s:message code="message.product.category.name"/></th>
			<th><s:message code="message.product.cost"/></th>
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