<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false" %>
<jsp:include page="./layout/header.jsp"></jsp:include>
<br>
<img src="" alt="이미지는 나중에 추가할거임">
<br>
<P>  The time on the server is ${serverTime}. </P>

<script> 
const isRg = `<c:out value = '${isRg}'/>`;
if(isRg == 1){
alert("게시글이 등록되었습니다!");
}
</script>

<jsp:include page="./layout/footer.jsp"></jsp:include>