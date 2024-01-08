<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="d-flex justify-content-center">
 <div class="container-md">
 <br>
 <h3>글 목록</h3>
 <br>
 
 <!-- search line -->
  <div class=".container-md">
  <form action="/board/list" method="get">
  <div class="input-group mb-3">
  <c:set value="${ph.pgvo.type}" var="typed"></c:set>
  <select name="type" class="form-select" id="search">
  <option ${typed eq null ? 'selected' : ''}>Choose</option>
  <option value="t" ${typed eq 't' ? 'selected' : ''}>Title</option>
  <option value="w" ${typed eq 'w' ? 'selected' : ''}>Writer</option>
  <option value="c" ${typed eq 'c' ? 'selected' : ''}>Content</option>
  <option value="tc" ${typed eq 'tc' ? 'selected' : ''}>Title&Content</option>
  <option value="tw" ${typed eq 'tw' ? 'selected' : ''}>Title&Writer</option>
  <option value="wc" ${typed eq 'wc' ? 'selected' : ''}>Writer&Content</option>
  <option value="twc" ${typed eq 'twc' ? 'selected' : ''}>All</option>
  </select>
  <input type="hidden" name="pageNo" value="1">
  <input type="hidden" name="qty" value="${ph.pgvo.qty}">  
  <input type="search" class="form-control me-2" value="${ph.pgvo.keyword}" name="keyword" placeholder="search">
  
  <button type="submit" class="btn btn-primary position-relative">
  search
  <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    ${ph.totalCount}
    <span class="visually-hidden">unread messages</span>
  </span>
</button> 
  </div>
  </form>
 </div>

 <table class="table">
 <thead>
 <tr>
      <th scope="col">#</th>
      <th scope="col">title</th>
      <th scope="col">writer</th>
      <th scope="col">content</th>
      <th scope="col">regDate</th>
      <th scope="col">readCount</th>
      <th scope="col">cmtQty</th>
      <th scope="col">hasFile</th>
    </tr>
 </thead>
  <tbody>
  <c:forEach items="${list}" var="bvo">
  <tr>
  <th scope="row">${bvo.bno}</th>
  <td><a href="/board/detail?bno=${bvo.bno}">${bvo.title}</a></td>
  <td>${bvo.writer}</td>
  <td>${bvo.content}</td>
  <td>${bvo.regDate}</td>
  <td>${bvo.readCount}</td>
  <td>${bvo.cmtQty}</td>
  <td>${bvo.hasFile}</td>
  </tr>
  </c:forEach>
  </tbody>
  </table>
   
   <nav aria-label="Page navigation example">
  <ul class="pagination">
  
  <c:if test="${ph.prev }">
    <li class="page-item">
      <a class="page-link" href="/board/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
     
    <c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
    <li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword }">${i}</a></li>
    </c:forEach>
          
    <c:if test="${ph.next }">
    <li class="page-item">
      <a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword }" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>   
    </c:if>
    <li class="page-item"><a class="page-link" href="/board/list">전체보기</a></li>
     </ul>
     </nav> 
       
 </div>
 </div>
 
 <script> 
const isRe = `<c:out value = '${isRe}'/>`;
if(isRe == 1){
alert("게시글이 삭제되었습니다.");
}
</script>

<jsp:include page="../layout/footer.jsp"></jsp:include>