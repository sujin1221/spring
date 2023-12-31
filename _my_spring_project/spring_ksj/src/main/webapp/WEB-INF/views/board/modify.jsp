<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../layout/header.jsp"></jsp:include>

<h3>Modify Page</h3>
<form action="/board/modify" method="post">
<div class="container-md">
<div class="mb-3">
  <label for="bno" class="form-label">Bno</label>
  <input type="text" name="bno" class="form-control" id="bno" value="${bvo.bno}" readonly="readonly">
</div>
<div class="mb-3">
  <label for="title" class="form-label">Title</label>
  <input type="text" name ="title" class="form-control" id="title" value="${bvo.title}" placeholder="title">
</div>
<div class="mb-3">
  <label for="writer" class="form-label">Writer</label>
  <input type="text" name ="writer" class="form-control" id="writer" value="${bvo.writer}" readonly="readonly">
</div>
<div class="mb-3">
  <label for="reg_date" class="form-label">reg_date</label>
  <span class="badge text-bg-primary">${bvo.readCount}</span>
  <input type="text" name ="reg_date" class="form-control" id="writer" value="${bvo.regDate}" readonly="readonly">
</div>
<div class="mb-3">
  <label for="content" class="form-label">Content</label>
  <textarea class="form-control" name="content" id="content" rows="3">${bvo.content} </textarea>
</div>
</div>

<button type="submit" class="btn btn-primary" id="regBtn">등록</button>
<a href="/board/list"><button type="button" class="btn btn-primary">목록</button></a>
</form> 

<jsp:include page="../layout/footer.jsp"></jsp:include>