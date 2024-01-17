<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../layout/header.jsp"></jsp:include>

<table class="table">
	<thead>
		<tr>
			<th scope="col">email</th>
			<th scope="col">nickName</th>
			<th scope="col">pwd</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="mvo">
			<tr>
				<td><a href="/member/detail?email=${mvo.email}">${mvo.email}</a></td>
				<td>${mvo.nickName}</td>
				<td>${mvo.pwd}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<jsp:include page="../layout/footer.jsp"></jsp:include>