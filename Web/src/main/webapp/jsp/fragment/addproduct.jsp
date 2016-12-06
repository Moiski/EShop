<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div id="addProductForm">
	<form class="form-horizontal" action="controller" method="POST" title="Add product form" >
		<input type="hidden" name="command" value="saveproducts" />
		<h3><i>Add product</i></h3>
		
		<div class="form-group">
			<label for="productName" class="col-sm-2 control-label">Product Name:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="productName" 
				name="name" placeholder="Kindle Paperwhite" required="required">
			</div>
		</div>
		<div class="form-group">
			<label for="categotyId" class="col-sm-2 control-label">Category ID:</label>
			<div class="col-sm-6">
				<select class="form-control" name="categotyId">
					<option>Select a category</option>
					<option value="1">E-readers</option>
					<option value="2">Tablets</option>
					<option value="3">Accessories</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="productPrice" class="col-sm-2 control-label">Product price:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="productPrice" 
				name="price" placeholder="99.99" required="required" 
				pattern="[1-9][0-9]+\.[0-9]{1,2}">
			</div>
		</div>
		<div class="form-group">
			<label for="productImage" class="col-sm-2 control-label">Image:</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="productImage" 
				name="image" placeholder="image" required="required">
			</div>
		</div>
		<div class="form-group">
			<label for="productDicription" class="col-sm-2 control-label">Product description:</label>
			<div class="col-sm-6">
				<textarea id="productDicription" rows="10" cols="58" name="description" contenteditable="true">${product.description}</textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-6">
				<button type="submit" class="btn btn-success">Save</button>
			</div>
		</div>
    </form> 	
</div>