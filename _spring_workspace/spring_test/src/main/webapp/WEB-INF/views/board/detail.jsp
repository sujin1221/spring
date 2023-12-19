<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp"></jsp:include>
<h3>Board Register Page</h3>
<div class="container-md">
<div class="mb-3">
  <label for="bno" class="form-label">Bno</label>
  <input type="text" name="bno" class="form-control" id="bno" value="${bvo.bno}" readonly="readonly">
</div>
<div class="mb-3">
  <label for="title" class="form-label">Title</label>
  <input type="text" name = "title" class="form-control" id="title" value="${bvo.title}" placeholder="writer" readonly="readonly">
</div>
<div class="mb-3">
  <label for="writer" class="form-label">Writer</label>
  <input type="text" name = "writer" class="form-control" id="writer" value="${bvo.writer}" readonly="readonly">
</div>
<div class="mb-3">
  <label for="reg_date" class="form-label">reg_date</label>
  <span class="badge text-bg-primary">${bvo.read_count}</span>
  <input type="text" name = "reg_date" class="form-control" id="writer" value="${bvo.reg_date}" readonly="readonly">
</div>
<div class="mb-3">
  <label for="content" class="form-label">Content</label>
  <textarea class="form-control" name= "content" id="content" rows="3" readonly="readonly"></textarea>
</div>
</div>
<a href="/board/list"><button type="button" class="btn btn-primary">List</button></a>
<a href="/board/modify?bno=${bvo.bno}"><button type="button" class="btn btn-success">modify</button></a>
<a href="/board/remove?bno=${bvo.bno}"><button type="button" class="btn btn-danger">delete</button></a>
<jsp:include page="../layout/footer.jsp"></jsp:include>
