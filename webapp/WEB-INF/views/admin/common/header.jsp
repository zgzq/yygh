<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
var WEB_ROOT="${pageContext.request.contextPath}";
var LANG="${pageContext.request.locale}";
</script>
<%
	boolean isDev = my.util.SysParam.isDevMode();
	if (isDev) {
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/operamasks-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.serialize-object.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/artDialog.source.js?skin=default"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/iframeTools.source.js"></script>
<%
	}else{
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/operamasks-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.serialize-object.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/iframeTools.js"></script>
<%
	}
%>
<c:if test="${LANG != 'zh_CN'  }">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/en-msg.js"></script>
</c:if>
<c:if test="${LANG == 'zh_CN'  }">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zh-msg.js"></script>
</c:if>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/apusic/om-apusic.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/crypto/core-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/crypto/enc-base64-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my.js"></script>