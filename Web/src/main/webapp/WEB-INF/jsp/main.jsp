<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
	<div class="container">
		<div class="header">
			<c:import url="/WEB-INF/jsp/fragment/header.jsp" />
		</div>
		<hr />
		<div class="body">
			<div class="leftPart">
				<c:import url="/WEB-INF/jsp/fragment/navbar.jsp" />
				<c:import url="/WEB-INF/jsp/fragment/user.jsp" />
			</div>
			<div class="rigthPart">
				<c:if test="${mainInfoMessage ne null}">
					<div class="alert alert-info" role="alert">
						${mainInfoMessage}</div>
				</c:if>
				<c:if test="${orderInfoMessage ne null}">
					<div class="alert alert-info" role="alert">
						${orderInfoMessage}</div>
				</c:if>
				<c:if test="${regInfoMessage ne null}">
					<div class="alert alert-info" role="alert">
						${regInfoMessage}</div>
				</c:if>
				<c:if test="${sessionScope.userType eq 'GUEST'}">
					<h1 class="alert alert-info">
						<i>Hello! Welcome in our store! To make purchases from our
							store, sign in with your login or register!</i>
					</h1>
					<br />
				</c:if>
				<c:import url="/WEB-INF/jsp/fragment/productslist.jsp" />
			</div>
		</div>
	</div>
	<c:import url="/WEB-INF/jsp/fragment/footer.jsp" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.0.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
