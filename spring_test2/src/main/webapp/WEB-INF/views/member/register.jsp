<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "../layout/header.jsp"></jsp:include>

	<!-- email, pwd, nick_name -->
	<!-- admin: 1111@naver.com으로 설정함 -->
<form action="/member/register" method="post">
<div class="container-md">
<br>
<h2>ᘳ´• ᴥ •`ᘰ</h2>
<br>
<label for="e" class="form-label">email</label>
  <input type="email" name="email" class="form-control" id="e" placeholder="example@OOO.com">
</div>

<div class="container-md">
<label for="p" class="form-label">pwd</label>
  <input type="password" name="pwd" class="form-control" id="p" placeholder="passWord">
</div>

<div class="container-md">
<label for="n" class="form-label">nick_name</label>
  <input type="text" name="nickName" class="form-control" id="n" placeholder="nick_name">
</div>

<br>
<button type="submit" class="btn btn-primary" id="regBtn">join</button>
</form>


<jsp:include page = "../layout/footer.jsp"></jsp:include>
