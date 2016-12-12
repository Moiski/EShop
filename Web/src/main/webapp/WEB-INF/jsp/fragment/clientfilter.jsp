<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div id="clientFilterForm">
	<form id="clientProductForm" class="form-inline" action="${pageContext.request.contextPath}/products/clientfilter" method="GET">
		<input type="hidden" name="isFilterChanged" value="true">
		<label><s:message code="message.category"/>&nbsp;
			<select name="categoryId" class="form-control">
				<option value="0"><s:message code="message.category.all"/></option>
				<option value="1"><s:message code="message.category.id.1"/></option>
				<option value="2"><s:message code="message.category.id.2"/></option>
				<option value="3"><s:message code="message.category.id.3"/></option>
			</select>
		<c:set var ="categoryId" value="${sessionScope.clientFilter.categoryId}" scope="page"/>
		</label>
		<label for="sort"><s:message code="message.sort"/></label>
		<select name="sorting" class="form-control">
			<option value="0"><s:message code="message.sort.no"/></option>
			<option value="1"><s:message code="message.sort.asc"/></option>
			<option value="2"><s:message code="message.sort.des"/></option>
		</select>
		<c:set var ="sorting" value="${sessionScope.clientFilter.sorting}" scope="page"/>
		<label for="itemsPerPage"><s:message code="message.per.page"/></label>
		<select name="itemsPerPage" class="form-control">
			<option value="3">3</option>
			<option value="5">5</option>
			<option value="7">7</option>
		</select>
		<c:set var ="itemsPerPage" value="${sessionScope.clientFilter.itemsPerPage}" scope="page"/>
		<button type="submit" class="btn btn-info"><s:message code="message.sort.filter"/></button>
	</form>
</div>