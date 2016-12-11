<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<jsp:useBean id="product" class="by.moiski.dao.entities.Product" scope="request"></jsp:useBean>

<div id="addProductForm">

	<sf:form class="form-horizontal" action="${pageContext.request.contextPath}/products/add/new/product" method="POST" modelAttribute="product">
		<h3><i>Add product</i></h3>
		<div id="productName" class="form-group">
			<label for="nameData" class="col-sm-2 control-label">Product Name:</label>
			<div class="col-sm-6">
				<sf:input path="name"
					id="productNameData"
					class="form-control"
					placeholder="Kindle Paperwhite"
					required="required"
					name="name"/>
			</div>
		</div>	
		<div class="form-group" id="categoryId">
			<label for="categotyData" class="col-sm-2 control-label">Category ID:</label>
			<div class="col-sm-6">
				<select class="form-control" id="productCategoryData" name="categoryId">
					<option value="">Select a category</option>
					<option value="1">E-readers</option>
					<option value="2">Tablets</option>
					<option value="3">Accessories</option>
				</select>
			</div>
		</div>
		<div class="form-group" id="idPrice">
			<label for="productPrice" class="col-sm-2 control-label">Product price:</label>
			<div class="col-sm-6">
				<sf:input path="price"
					class="form-control"
					id="productPrice"
					placeholder="99.99"
					required="required" 
					pattern="[1-9][0-9]+\.[0-9]{1,2}"
					name="price"/>
			</div>
		</div>
		<div class="form-group" id="idImage">
			<label for="productImage" class="col-sm-2 control-label">Image:</label>
			<div class="col-sm-6">
				<sf:input path="image"
					id="productImage"
					class="form-control"
					placeholder="image" 
					required="required"
					name="image"/>
			</div>
		</div>
		<div class="form-group" id="idDescription">
			<label for="productDicription" class="col-sm-2 control-label">Product description:</label>
			<div class="col-sm-6">
				<sf:textarea path="description"
					 id="productDicription"
					 rows="10" cols="58"
					 name="description"
					 contenteditable="true"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-6">
				<button type="submit" class="btn btn-success">Save</button>
			</div>
		</div>	
	</sf:form>	
</div>