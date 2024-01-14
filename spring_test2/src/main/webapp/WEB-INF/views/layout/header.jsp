<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<!-- bootstrap -->
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Spring</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">Home</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/board/register">글작성</a>
        </li>
                <li class="nav-item">
          <a class="nav-link" href="/board/list">글목록</a>
        </li>
                <li class="nav-item">
          <a class="nav-link" href="/member/register">회원가입</a>
        </li>
           <c:if test="${ses.id eq null}">
                <li class="nav-item">
          <a class="nav-link" href="/member/login">로그인</a>
          </li>
          </c:if>
            <c:if test="${ses.id ne null}">
            <li class="nav-item">
          <a class="nav-link" href="/member/logout">로그아웃</a>
          </li>
          </c:if>
          <!-- 내정보수정 만들거임 -->
          <c:if test="${ses.id ne null}">
            <li class="nav-item">
          <a class="nav-link" href="/member/modify">${ses.id }(${ses.email }) welcome~!</a>
          </li>
          </c:if>
      </ul>
    </div>
  </div>
</nav>
