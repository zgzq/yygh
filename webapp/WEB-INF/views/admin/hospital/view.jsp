<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/base.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/global.css" />
 <style>
.mid {
	border: solid 1px #e5e5e5;
	padding: 5px;
	margin: 5px auto;
}

.bt {
	min-height: 40px;
	line-height: 40px;
	color: #000;
	font-size: 22px;
	text-align: center;
}

.info {
	height: 20px;
	line-height: 20px;
	text-align: center;
	border-bottom: #ccc 1px solid;
	color: #999;
}

.content {
	line-height: 1.8em;
	font-size: 14px;
	color: #000;
	margin-top: 10px;
	min-height: 300px;
}
</style>
<title>公告</title>
</head>
<body >
		<div class="mid" style="width: 960px;">
			<h1 class="bt">${notice.bt }</h1>
			<div class="info">发布时间：${notice.fbrq } &nbsp; 作者：${notice.fbr }
				&nbsp;</div>
			<div class="content">${notice.nr}</div>
		</div>
</body>
</html>