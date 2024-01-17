<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../layout/header.jsp"></jsp:include>

<!-- email, pwd, nick_name -->
<!-- admin: 1111@naver.com으로 설정함 -->
<form action="/member/register" method="post">
			
		<div class="container-md">
		<br>
		<h2>ᘳ´• ᴥ •`ᘰ</h2>
		<br>
		<label for="e" class="form-label">email</label> 
		<input
			type="email" name="email" class="form-control" id="e"
			placeholder="example@OOO.com" required="required">
		<button type="button" id="eCheck" class="btn btn-success">중복체크</button>
		<div id="email-match-message"></div>
		</div>

		<div class="container-md">
			<label for="p" class="form-label">pwd</label> <input type="password"
				name="pwd" class="form-control" id="p" placeholder="passWord"
				required="required">
		</div>
		
		<div class="container-md">
			<label for="pcheck" class="form-label">pwd_check</label> 
			<input type="password"
				name="pwdcheck" class="form-control" id="pCheck" placeholder="passWord"
				required="required" onKeyUp="fn_compare_pwd();">
		<div id="password-match-message"></div>
		</div>

		<div class="container-md">
			<label for="n" class="form-label">nick_name</label> <input
				type="text" name="nickName" class="form-control" id="n"
				placeholder="nick_name" required="required">
		<br>

		<button type="submit" class="btn btn-primary" id="regBtn">join</button>
		</div>
		</form>
				
<script src="/resources/js/memberRegister.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>
