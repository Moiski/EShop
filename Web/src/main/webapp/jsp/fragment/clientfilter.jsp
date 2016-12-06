<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="clientFilterForm">
	<form id="clientProductForm" class="form-inline" action="controller" method="get">
		<input type="hidden" name="command" value="showproductsclientfilter">
		<input type="hidden" name="isFilterChanged" value="true">	
		<label>Choose category:&nbsp;
			<select name="categoryid" class="form-control">
				<option value="0">All product</option>
				<option value="1">Kindle E-readers</option>
				<option value="2">Tablets</option>
				<option value="3">Accessories</option>
			</select>
		<c:set var ="categoryid" value="${sessionScope.clientFilter.categoryId}" scope="page"/>
		</label>
		<label for="sort">Sort by:</label>
		<select name="sorting" class="form-control">
			<option value="0">No sort</option>
			<option value="1">Low to high</option>
			<option value="2">High to low</option>
		</select>
		<c:set var ="sorting" value="${sessionScope.clientFilter.sorting}" scope="page"/>
		<label for="itemsPerPage">Items per page:</label>
		<select name="itemsPerPage" class="form-control">
			<option value="3">3</option>
			<option value="5">5</option>
			<option value="7">7</option>
		</select>
		<c:set var ="itemsPerPage" value="${sessionScope.clientFilter.itemsPerPage}" scope="page"/>
		<button type="submit" class="btn btn-info">Accept</button>
	</form>
</div>


