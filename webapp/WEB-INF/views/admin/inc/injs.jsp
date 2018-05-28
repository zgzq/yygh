<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script>
var WEB_ROOT="${pageContext.request.contextPath}";
var LANG="${pageContext.request.locale}";
</script>


<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/operamasks-ui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.serialize-object.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/apusic/om-apusic.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/crypto/core-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/crypto/enc-base64-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my.js"></script>




<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/jquery.easyui-1.5.1.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/easyui-lang-zh_CN.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/datagrid-filter.js"></script> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jquery-easyui-1.5.1/themes/color.css">


<!-- 打印 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>


<!-- 全部按钮-->
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/buttons.js"></script>  --%>



