<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page = "../layout/header.jsp"></jsp:include>
<div class="container">
<form action="/board/register" method="post">
<br>
<h3 style="background: linear-gradient(to right, violet, indigo, blue, green, yellow, orange, red); display: inline-block; -webkit-background-clip: text; color: transparent;">૮⍝•ᴥ •⍝ა</h3>
<br>
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
  <textarea class="form-control" name= "content" id="content" rows="3" placeholder="content"></textarea>
</div>
<br>
<button type="submit" class="btn btn-outline-success">Ok</button>
<a href="/board/list"><button type="button" class="btn btn-outline-danger">Nope!</button></a>
<button type="button" class="btn btn-outline-warning">❤️‍🔥</button>
</form>
</div>
</body>
</html>