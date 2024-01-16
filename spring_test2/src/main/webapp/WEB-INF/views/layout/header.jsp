<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
	integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
	integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
	crossorigin="anonymous"></script>
<!-- bootstrap -->
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Spring</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">

					<!-- 로그인하고 나면 없어져야함 -->
					<sec:authorize access="isAnonymous()">
						<li class="nav-item"><a class="nav-link"
							href="/member/register">회원가입</a></li>

						<li class="nav-item"><a class="nav-link" href="/member/login">로그인</a>
						</li>
					</sec:authorize>

					<!-- 현재 인증한 사용자의 정보를 가져와서 해당 권한이 있는지 확인 -->
					<!-- 현재 사용자의 정보는 principal -->
					<!-- anyMatch: stream에서 매칭이되는지 확인하는 메서드
        (최소한 1개의 요소가 주어진 요소에 맞는지 조사) -->
					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal.mvo.email" var="authEmail" />
						<sec:authentication property="principal.mvo.nickName"
							var="authNick" />
						<sec:authentication property="principal.mvo.authList" var="auths" />

						<c:choose>
							<c:when
								test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get()}">
								<li class="nav-item"><a class="nav-link"
									href="/member/list">회원목록 ${authNick}(${authEmail}/ADMIN)</a></li>
							</c:when>
							<c:otherwise>
								<li class="nav-item"><a class="nav-link"
									href="/member/modify">회원정보수정 ${authNick}(${authEmail})</a></li>
							</c:otherwise>
						</c:choose>

						<li class="nav-item"><a class="nav-link"
							href="/board/register">글작성</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/member/logout" id="logoutLink">로그아웃</a></li>

						<form action="/member/logout" method="post" id="logoutForm">
							<!-- 인증된 이메일(로그인한 계정의) -->
							<input type="hidden" name="email" value="${authEmail}">
						</form>
					</sec:authorize>

					<li class="nav-item"><a class="nav-link" href="/board/list">글목록</a>
					</li>

					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/">Main</a></li>

				</ul>
			</div>
		</div>
	</nav>
</body>
</html>

<script type="text/javascript">
document.getElementById('logoutLink').addEventListener('click',(e)=>{
    e.preventDefault(); //무력화
    document.getElementById('logoutForm').submit(); //전송
});
</script>