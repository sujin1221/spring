<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

<form action="/member/login" method="post">
<div class="container-md">
<br>
<h2>Login Page</h2><br>
<br>
<div class="mb-3">
  <label for="e" class="form-label">E-mail</label>
  <input type="email" name="email" class="form-control" id="e" placeholder="example@eamil.com">
</div>
<div class="mb-3">
  <label for="p" class="form-label">PW</label>
  <input type="password" name="pwd" class="form-control" id="p" placeholder="password">
</div>
<c:if test="${not empty param.errMsg}">
<div class="mb-3 text-danger fw-bold">
<c:choose>
<c:when test="${param.errMsg eq 'Bad credentials'}">
	<c:set value="이메일 또는 비밀번호가 일치하지 않습니다." var="errText"></c:set>
</c:when>
<c:otherwise>
	<c:set value="관리자(1111@naver.com)에게 문의바랍니다." var="errText"></c:set>
</c:otherwise>
</c:choose>
${errText}
</div>
</c:if>
<button type="submit" class="btn btn-primary">Login</button>
</div>
</form>


<jsp:include page="../layout/footer.jsp"></jsp:include>