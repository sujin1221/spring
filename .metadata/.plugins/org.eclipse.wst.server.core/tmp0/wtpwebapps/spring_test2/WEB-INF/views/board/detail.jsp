<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<br>
<h3>Detail Page</h3>
<br>
<!-- c:set으로 앞에 붙여주기... -->
<c:set value="${bdto.bvo}" var="bvo"></c:set>
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
  <span class="badge text-bg-primary">${bvo.readCount}</span>
  <input type="text" name = "reg_date" class="form-control" id="writer" value="${bvo.regDate}" readonly="readonly">
</div>
<div class="mb-3">
  <label for="content" class="form-label">Content</label>
  <textarea class="form-control" name= "content" id="content" rows="3" readonly="readonly">${bvo.content}</textarea>
</div>
</div>

<!-- file line -->
	<c:set value="${bdto.flist}" var="flist"></c:set>
	<div class="mb-3">
	<label for="f" class="form-label"></label>
	<ul class="list-group list-group-flush">
 <c:forEach items="${flist }" var="fvo">
 	<li class="list-group-item">
 		<c:choose>
 			<c:when test="${fvo.fileType == 1}">
 				<div>
 					<img alt="" src="/upload/${fvo.saveDir}/${fvo.uuid}_th_${fvo.fileName}">
 				</div>
 			</c:when>
 			<c:otherwise>
 			<div>
 			<!-- 일반 파일 표시할 아이콘, 다른이름으로 저장 -->
 			<a href="/upload/${fvo.saveDir}/${fvo.uuid}_${fvo.fileName}" download="${fvo.fileName}">
 			<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-file-earmark-text" viewBox="0 0 16 16">
  				<path d="M5.5 7a.5.5 0 0 0 0 1h5a.5.5 0 0 0 0-1h-5zM5 9.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5z"/>
  				<path d="M9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4.5L9.5 0zm0 1v2A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5z"/>
			</svg>
			</a>
 			</div>
 			</c:otherwise>
 		</c:choose>
 		<div class="ms-2 me-auto">
 			<div class="fw-bold">${fvo.fileName}</div>
 			<span class="badge text-bg-warning">${fvo.fileSize}Byte</span>
 		</div>
 	</li>
 </c:forEach>
	</ul>
</div>

	<br>
		<a href="/board/list"><button type="button" class="btn btn-primary">List</button></a>
		<a href="/board/modify?bno=${bvo.bno}"><button type="button" class="btn btn-success">modify</button></a>
		<a href="/board/remove?bno=${bvo.bno}"><button type="button" class="btn btn-danger">delete</button></a>
	<br>
<hr>

<!-- 댓글 등록 라인 -->
<div class="input-group mb-3">
<span id="cmtWriter" class="input-group-text">${bvo.writer}</span>
  <input type="text" id="cmtText" class="form-control" placeholder="Add Comment" aria-label="Recipient's username" aria-describedby="basic-addon2">
  <button type="button"  id="cmtPostBtn"  class="btn btn-success">등록</button>
</div>

<!-- 댓글 표시 라인 -->
<ul class="list-group list-group-flush" id="cmtListArea">
  <li class="list-group-item">
   <div class="mb-3">
      <div class="fw-bold">Writer</div>
      content
   </div>
   <span class="badge text-bg-primary">modAt</span>
  </li>
</ul>

<!-- 댓글 더보기 버튼 -->
  <div>
    <button type="button" id="moreBtn" data-page="1" class="btn btn-success" style="visibility: hiden">More+</button>
  </div>
  
  <!-- modal 창 -->
  <div class="modal" id="myModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Writer</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class="input-group mb-3">
        <input type="text" class="form-control" id="cmtTextMod">
        <button type="button" class="btn btn-primary" id="cmtModBtn">Post</button>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div> 
  </div>
 </div>
 </div>
  
 <script> 
  const isMod = `<c:out value = '${isMod}'/>`;
  if(isMod == 1){
  alert("게시글이 수정되었습니다.");
  }
 </script>

 <script type="text/javascript">
 let bnoVal = `<c:out value="${bdto.bvo.bno}" />`;
 console.log(bnoVal);
 </script>
  
<script type="text/javascript" src="/resources/js/boardComment.js"></script>  
<script type="text/javascript">
spreadCommentList(bnoVal);
</script>
<jsp:include page="../layout/footer.jsp"></jsp:include>