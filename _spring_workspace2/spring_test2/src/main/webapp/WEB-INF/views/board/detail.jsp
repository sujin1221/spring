<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<br>
<h3>Board detail Page</h3>
<br>
<!-- c:set으로 앞에 붙여주기... -->
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

<div class="mb-3">
<ul class="list-group">
<!-- 파일의 개수만큼 li를 추가하여 파일을 표시 -->
<!-- 타입이 1인 경우만 표시 -->
<!-- 
li -> div -> img 표시 
div -> 파일명, 작성일, span size
-->
<!-- 파일 리스트 중 하나만 가져와서 fvo로 저장 -->
<c:forEach items="${flist }" var="fvo">
	<li class="list-group-item">
		<c:choose>
			<c:when test="${fvo.file_type > 0 }">
				<div>
					<!-- /upload/save_dir/uuid_file_name -->
					<img alt="" src="/upload/${fn:replace(fvo.save_dir, '\\', '/')}/${fvo.uuid}_th_${fvo.file_name}">
				</div>
			</c:when>
			<c:otherwise>
				<div>
				<!-- 아이콘 같은 모양 하나 가져와서 넣기 -->
				</div>
			</c:otherwise>
			</c:choose>
<div>
<!-- div => 파일명, 작성일, span size -->
<div>${fvo.file_name }</div>
		${fvo.reg_date }
		</div>
			<span>${fvo.file_size }Byte</span>
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
  
 <script> 
  const isMod = `<c:out value = '${isMod}'/>`;
  if(isMod == 1){
  alert("게시글이 수정되었습니다.");
  }
</script>

 <script type="text/javascript">
 let bnoVal = `<c:out value="${bvo.bno}" />`;
 console.log(bnoVal);
 </script>
  
<script type="text/javascript" src="/resources/js/boardComment.js"></script>  
<script type="text/javascript">
spreadCommentList(bnoVal);
</script>
<jsp:include page="../layout/footer.jsp"></jsp:include>