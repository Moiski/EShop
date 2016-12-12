<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="js/navbar_animation.js"></script>
<div id="navbar">
	<ul class="nav nav-pills nav-stacked">
		<li id="mainItem" role="presentation" onclick="setActiveStyleAttribute('mainItem')">
			<a href="${pageContext.request.contextPath}/main"><s:message code="message.main"/></a></li>
	</ul>
</div>
<div id="administratorbar">
	<c:if test="${sessionScope.userType eq 'ADMIN'}">
		<c:import url="/WEB-INF/jsp/fragment/administratorbar.jsp" />
	</c:if>
</div>
<br/>
