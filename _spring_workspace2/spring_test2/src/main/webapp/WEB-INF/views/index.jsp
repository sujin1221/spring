<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<jsp:include page="./layout/header.jsp"></jsp:include>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<a href="/board/register"><button>register Board</button></a>
</body>
</html>
<jsp:include page="./layout/footer.jsp"></jsp:include>
