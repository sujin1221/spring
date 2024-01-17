<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<jsp:include page = "../layout/header.jsp"></jsp:include>

<div class="container">
	<form action="/member/modify" method="post">

		<div class="mb-3">
			<label for="e" class="form-label">E-mail</label> <input type="email"
				name="email" class="form-control" id="e" value="${mvo.email}">
		</div>

		<div class="mb-3">
			<label for="p" class="form-label">Password</label> <input
				type="password" name="pwd" class="form-control" id="p"
				value="${mvo.pwd}">
		</div>

		<div class="mb-3">
			<label for="n" class="form-label">Nick_name</label> <input
				type="text" name="nickName" class="form-control" id="n"
				value="${mvo.nickName}">
		</div>

		<!-- 해당 멤버의 권한을 출력(2개일 수도 있음) -->
		<c:forEach items="${mvo.authList}" var="auth">
			<li>${auth.auth}</li>
		</c:forEach>
		
		<br>
		<button type="submit" class="btn btn-primary">수정</button>
		<a href="/member/remove?email=${mvo.email}"><button type="button" class="btn btn-danger">탈퇴</button></a> <br>
	</form>
</div>


<jsp:include page = "../layout/footer.jsp"></jsp:include>