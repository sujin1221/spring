<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page = "../layout/header.jsp"></jsp:include>
<form action="/board/register" method="post" enctype="multipart/form-data">
<h3>Board Register Page</h3>
<div class="mb-3">
  <label for="title" class="form-label">Title</label>
  <input type="text" name="title" class="form-control" id="title" placeholder="title">
</div>
<div class="mb-3">
  <label for="writer" class="form-label">Writer</label>
  <input type="text" name = "writer" class="form-control" id="writer" placeholder="writer">
</div>
<div class="mb-3">
  <label for="content" class="form-label">Content</label>
  <textarea class="form-control" name= "content" id="content" rows="3"></textarea>
</div>

<!-- file 입력 라인 추가 -->
<!-- multiple="multiple": 여러개의 파일 업로드 가능하도록 -->
<div class="mb-3">
  <label for="file" class="form-label">files...</label>
  <input type="file" name = "files" class="form-control" id="file" multiple="multiple" style="display: none">
<button type="button" class="btn btn-primary" id="trigger">FileUpload</button>
</div>
<!-- 파일 목록 표시라인 -->
<div class="mb-3" id="fileZone">

</div>
<button type="submit" class="btn btn-primary" id="regBtn">등록</button>
</form>
<script src="/resources/js/boardRegister.js">
</script>
</body>
</html>