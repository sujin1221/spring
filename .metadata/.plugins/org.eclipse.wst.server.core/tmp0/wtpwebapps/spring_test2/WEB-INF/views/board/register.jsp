<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<jsp:include page = "../layout/header.jsp"></jsp:include>
<form action="/board/register" method="post" enctype="Multipart/form-data">
<br>
<h3>ദ്ദി=´∀｀) 글을 작성해봅시당~!</h3>
<br>
<div class="mb-3">
  <label for="title" class="form-label">Title</label>
  <input type="text" name="title" class="form-control" id="title" placeholder="title">
</div>

<sec:authorize access="isAuthenticated()">
<sec:authentication property="principal.mvo.email" var="authEmail"/>
<div class="mb-3">
  <label for="writer" class="form-label">Writer</label>
  <input type="text" name = "writer"  value="${authEmail}" class="form-control" id="writer" placeholder="writer">
</div>
</sec:authorize>

<div class="mb-3">
  <label for="content" class="form-label">Content</label>
  <textarea class="form-control" name= "content" id="content" rows="3"></textarea>
</div>

<!-- 파일등록 -->
<div class="mb-3">
  <input type="file" name = "files" class="form-control" id="files" multiple="multiple" style="display: none">
<!-- 트리거 사용을 위해.. -->
<button type="button" class="btn btn-primary" id="trigger">File Upload</button>
</div>
<!-- 첨부파일 -->
<div class="mb-3" id="fileZone">

</div>

<br>
<button type="submit" class="btn btn-primary" id="regBtn">등록</button>
</form>

<script src="/resources/js/boardRegister.js"></script>
</body>
</html>