<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<h3>Board Modify Page</h3>
<c:set value="${boardDTO.bvo }" var="bvo" />
<form action="/board/modify" method="post" enctype="multipart/form-data">

<div class="container-md">
<div class="mb-3">
  <label for="bno" class="form-label">Bno</label>
  <input type="text" name="bno" class="form-control" id="bno" value="${bvo.bno}" readonly="readonly">
</div>
<div class="mb-3">
  <label for="title" class="form-label">Title</label>
  <input type="text" name ="title" class="form-control" id="title" value="${bvo.title}" placeholder="writer">
</div>
<div class="mb-3">
  <label for="writer" class="form-label">Writer</label>
  <input type="text" name ="writer" class="form-control" id="writer" value="${bvo.writer}" readonly="readonly">
</div>
<div class="mb-3">
  <label for="reg_date" class="form-label">reg_date</label>
  <span class="badge text-bg-primary">${bvo.read_count}</span>
  <input type="text" name ="reg_date" class="form-control" id="writer" value="${bvo.reg_date}" readonly="readonly">
</div>
<div class="mb-3">
  <label for="content" class="form-label">Content</label>
  <textarea class="form-control" name="content" id="content" rows="3">${bvo.content} </textarea>
</div>
</div>

<!-- 파일 표시 라인 -->
<c:set value="${boardDTO.flist}" var="flist" />
<div class="mb-3">
<ul class="list-group">
<!-- 파일의 개수만큼 li를 추가하여 파일을 표시 -->
<!-- 타입이 1인 경우만 표시 -->

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
			<span class="badge text-bg-warning">${fvo.file_size }Byte</span>
		<button type="button" data-uuid ="${fvo.uuid }" class="file-x">X</button>
			</li>
		</c:forEach>
	</ul>
</div>
<!-- 수정 파일 등록 라인 -->
<!-- file 입력 라인 추가 -->

<div class="mb-3">
  <label for="file" class="form-label">files...</label>
  <input type="file" name ="files" class="form-control" id="file" multiple="multiple" style="display: none">
<button type="button" class="btn btn-primary" id="trigger">FileUpload</button>
</div>
<!-- 파일 목록 표시라인 -->
<div class="mb-3" id="fileZone">

</div>

<button type="submit" class="btn btn-primary" id="regBtn">등록</button>
<a href="/board/list"><button type="button" class="btn btn-primary">List</button></a>
</form> 

<script src="/resources/js/boardModify.js"></script>
<script src="/resources/js/boardRegister.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>
