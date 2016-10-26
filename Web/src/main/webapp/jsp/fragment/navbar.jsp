<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="pagelocale"/>

<script type="text/javascript" src="js/navbar_animation.js"></script>

<div id="navbar">
	<ul class="nav nav-pills nav-stacked">
		<li id="mainItem" role="presentation" onclick="setActiveStyleAttribute('mainItem')"><a href="controller?command=getmainpage">Main</a></li>
		<li id="readersItem" role="presentation" onclick="setActiveStyleAttribute('readersItem')"><a href="controller?command=showproducts&categoryid=1">Kindle E-readers</a></li>
		<li id="tabletsItem" role="presentation" onclick="setActiveStyleAttribute('tabletsItem')"><a href="controller?command=showproducts&categoryid=2">Tablets</a></li>
		<li id="accessoriesItem" role="presentation" onclick="setActiveStyleAttribute('accessoriesItem')"><a href="controller?command=showproducts&categoryid=3">Accessories</a></li>
	</ul>
</div>
<br/>
<div id="administratorbar">
	<c:if test="${sessionScope.userType eq 'ADMIN'}">
		<c:import url="/jsp/fragment/administratorbar.jsp" />
	</c:if>
</div>
