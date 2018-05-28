<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/ke/themes/default/default.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/ke/plugins/code/prettify.css" />
<script charset="utf-8"
	src="${pageContext.request.contextPath}/lib/ke/kindeditor.js"></script>

<script charset="utf-8"
	src="${pageContext.request.contextPath}/lib/ke/plugins/code/prettify.js"></script>

<c:if test="${LANG != 'zh_CN'  }">
	<script charset="utf-8"
		src="${pageContext.request.contextPath}/lib/ke/lang/en.js"></script>
</c:if>
<c:if test="${LANG == 'zh_CN'  }">
	<script charset="utf-8"
		src="${pageContext.request.contextPath}/lib/ke/lang/zh_CN.js"></script>
</c:if>
