<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ taglib uri="/WEB-INF/tlds/my.tld" prefix="my"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>
      <c:if test="${ss==1}">
      	中美医疗集团
      </c:if>
      <c:if test="${ss==0}">
      	中美集团
      </c:if>
    </title>
    <jsp:include page="injs.jsp"/>
    <meta content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    
  </head>
  
  <body>
    <div data-role="page" id="pageone" data-theme="a">
		  <div data-role="header" data-position="fixed" data-tap-toggle="false">
		    <a href="javascript:history.go(-1)" data-role="button" data-icon="back">返回</a>
		    <h1>${orgname}</h1>
		    <a href="${pageContext.request.contextPath}/qyreq/kpiday?pdate=${pdate}&userid=${userid}&ss=${ss}" data-role="button" data-icon="home" target="_self" >首页</a>
		  </div>
		
		  <div data-role="content">
		    <ul data-role="listview" data-inset="true">
		    <c:forEach items="${kvs}" var="kv">
		      <li>
				<span>${kv.dictname}:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>${kv.kpivalue}
			  </li>
		     </c:forEach>
		    </ul>
		  </div>
		
		  
		</div> 
		
  
  </body>
</html>
