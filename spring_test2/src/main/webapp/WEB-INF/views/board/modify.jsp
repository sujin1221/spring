<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="../layout/header.jsp"></jsp:include>
<br>
<h3>Modify Page</h3>
<c:set value="${bdto.bvo }" var="bvo"></c:set>
<form action="/board/modify" method="post" enctype="multipart/form-data">
	<div class="container-md">
		<div class="mb-3">
			<label for="bno" class="form-label">Bno</label> <input type="text"
				name="bno" class="form-control" id="bno" value="${bvo.bno}"
				readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="title" class="form-label">Title</label> <input
				type="text" name="title" class="form-control" id="title"
				value="${bvo.title}" placeholder="title">
		</div>
		<div class="mb-3">
			<label for="writer" class="form-label">Writer</label> <input
				type="text" name="writer" class="form-control" id="writer"
				value="${bvo.writer}" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="reg_date" class="form-label">reg_date</label> <span
				class="badge text-bg-primary">${bvo.readCount}</span> <input
				type="text" name="reg_date" class="form-control" id="writer"
				value="${bvo.regDate}" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="content" class="form-label">Content</label>
			<textarea class="form-control" name="content" id="content" rows="3">${bvo.content} </textarea>
		</div>
	</div>

	<!-- file 표시라인 -->
	<c:set value="${bdto.flist }" var="flist"></c:set>
	<div class="mb-3">
		<label for="f" class="form-label"></label>
		<ul class="list-group list-group-flush">
			<c:forEach items="${flist }" var="fvo">
				<li class="list-group-item"><c:choose>
						<c:when test="${fvo.fileType == 1}">
							<div>
								<img alt=""
									src="/upload/${fvo.saveDir}/${fvo.uuid}_th_${fvo.fileName}">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<!-- 일반 파일 표시할 아이콘 -->
					<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-file-earmark-text" viewBox="0 0 16 16">
  <path d="M5.5 7a.5.5 0 0 0 0 1h5a.5.5 0 0 0 0-1h-5zM5 9.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0 2a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5z"/>
  <path d="M9.5 0H4a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V4.5L9.5 0zm0 1v2A1.5 1.5 0 0 0 11 4.5h2V14a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1h5.5z"/>
</svg>
					</div>
						</c:otherwise>
					</c:choose>
					<div class="ms-2 me-auto">
						<div class="fw-bold">${fvo.fileName}</div>
						<span class="badge text-bg-warning">${fvo.fileSize}Byte</span>
						<button type="button" data-uuid="${fvo.uuid}"
							class="btn btn-danger btn-sm file-x">x</button>
					</div></li>
			</c:forEach>
		</ul>
	</div>

	<!-- file 등록라인 -->
	<div class="mb-3">
  		<input type="file" name = "files" class="form-control" id="files" multiple="multiple" style="display: none">
	<!-- 트리거 사용을 위해.. -->
	<button type="button" class="btn btn-primary" id="trigger">File Upload</button>
		</div>
	<!-- 첨부파일 -->
	<div class="mb-3" id="fileZone">

	</div>

	<button type="submit" class="btn btn-primary" id="regBtn">등록</button>
	<a href="/board/list"><button type="button" class="btn btn-primary">목록</button></a>
</form>

<script src="/resources/js/boardRegister.js"></script>
<script src="/resources/js/boardModify.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>