<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <jsp:include page="../layout/header.jsp" />
<jsp:include page="../layout/nav.jsp" />
<!-- 게시글 편집란 시작 -->

<div class="container my-3">
  <div class="col-md-7 col-lg-8">
    <h4 class="mb-3">Board information Modify</h4>
    <form action="/board/modify" method="post">
    <input type="hidden" name="bno" value="${bvo.bno }">
      <div class="row g-3">

        <div class="col-12">
          <label for="email" class="form-label">Writer</label>
          <div class="input-group has-validation">
            <span class="input-group-text">@</span>
            <input type="email" class="form-control" name="writer"
            id="writer" value="${bvo.writer }" readonly>              
          </div>
        </div>
        
        <div class="col-12">
          <label for="regAt" class="form-label">Reg At</label>
          <input type="text" class="form-control" value="${bvo.regAt }" readOnly>              
        </div>
        <div class="col-12">
          <label for="modAt" class="form-label">Mod At</label>
          <input type="text" class="form-control" value="${bvo.modAt }" readOnly>              
        </div>
        <div class="col-12">
          <label for="readCount" class="form-label">Read Count</label>
          <input type="text" class="form-control" value="${bvo.readCount }" readOnly>              
        </div>
        <div class="col-12">
          <label for="title" class="form-label">Title</label>
          <input type="text" class="form-control" name="title"
           id="title" placeholder="Title" value="${bvo.title }" >              
        </div>

        <div class="col-12">
          <label for="cont" class="form-label">Content</label>
          <textarea class="form-control" name="content" 
           id="cont" placeholder="Content">${bvo.content }</textarea>              
        </div>
            <button type="submit" class="btn btn-outline-warning" id="regBtn">Submit</button>
        </div>
        </form>
      </div>
</div>
<jsp:include page="../layout/footer.jsp" />