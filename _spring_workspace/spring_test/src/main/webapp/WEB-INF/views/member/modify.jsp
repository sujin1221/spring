<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
<h2>Member Modify Page</h2>
<form action="/member/modify" method="post">
 <table class="table">
 <tbody>
 
 <tr>
     <input type="hidden" name="id" value="${ses.id }"> 
   <th>ID</th>
   <td>${ses.id }</td>
    </tr>
    
   <tr>
   <th>Password</th>
   <td><input type="password" name="pw" placeholder="변경할 pwd"></td>
    </tr>
    
   <tr>
   <th>Name</th>
   <td><input type="text" name="name" value="${ses.name }"></td>
    </tr>
    
   <tr>
   <th>Email</th>
   <td><input type="email" name="email" value="${ses.email }"></td>
    </tr>
    
   <tr>
   <th>Home</th>
   <td><input type="text" name="home" value="${ses.home }"></td>
    </tr>
    
    <tr>
   <th>Age</th>
   <td><input type="text" name="age" value="${ses.age }"></td>
    </tr>
    
 </tbody>
 </table>
 <button type="submit" class="btn btn-primary">modify</button>
 <a href="/member/remove"><button type="button" class="btn btn-danger">delete</button></a>
 </form>
 </div>
<jsp:include page="../layout/footer.jsp"></jsp:include>