<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="header.jsp" %>
</head>
<body>
<c:if test="${msg!=null }">
<script>
$.omMessageTip
.show({
	title : '',
	content : "${msg}",
	timeout : 1500
});
</script>
</c:if>
<form action="${pageContext.request.contextPath}${action}" method="post" enctype="multipart/form-data" >
<input name="file" type="file"></input><input type="submit"/> 
</form>
</body>
</html>