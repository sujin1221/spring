<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/nav.jsp" />

<form action="/board/register" method="post">
	<div class="mb-3">
	  <label for="t" class="form-label">title</label>
	  <input type="text" class="form-control" name="title" id="t" placeholder="title">
	</div>
	<div class="mb-3">
	  <label for="w" class="form-label">writer</label>
	  <input type="text" class="form-control" name="writer" id="w" placeholder="writer">
	</div>
	<div class="mb-3">
	  <label for="c" class="form-label">Content</label>
	  <textarea class="form-control" name="content" id="c" rows="3"></textarea>
	</div>
<!-- 	<div class="mb-3">
	  <input type="file" class="form-control" name="files" multiple="multiple">
	  </div>
	<div class="mb-3">
	  <input type="file" class="form-control" name="files" id="files" style="display:none;" multiple="multiple">
	  input button trigger 용도의 button
	  <button type="button" id="trigger" class="btn btn-outline-primary">File Upload</button>
	</div>
	<div class="mb-3" id="fileZone">
		첨부파일 표시될 영역
	</div> -->
	<button type="submit" class="btn btn-dark" >등록</button>
</form>
<jsp:include page="../layout/footer.jsp" />
</body>
</html>