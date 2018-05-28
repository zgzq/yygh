<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>desktop</title>

<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
<!-- <link href="css/default/om-default.css" rel="stylesheet" type="text/css" /> -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/apusic/om-apusic.css" />

<style>

table,tr,td,br,select {
	font-family: "宋体";
	line-height: 170%;
	font-size: 12px;
}

.ListColumnClass1 {
	font-family: 宋体;
	font-size: 12px;
	line-height: 220%;
	text-align: left;
}

.ListColumnClass1 a {
	color: #3a3a3a;
	text-decoration: none;
	padding-left: 13px;
}

.ListColumnClass1 A:visited {
	color: #5d5d5d;
	text-decoration: none
}

.ListColumnClass1 a:hover {
	color: #CC0000;
	text-decoration: underline
}

.ListColumnClass1 A:active {
	COLOR: #ff9900;
	TEXT-DECORATION: none
}

.ListColumnClass1 a:link {
	color: #3a3a3a;
	text-decoration: none
}
.om-panel-body{
background-color: white;
}
</style>
<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript">
	
</script>
<script src="${pageContext.request.contextPath}/js/operamasks-ui.min.js" type="text/javascript">
	
</script>


<script>
	$(function() {
		
// 		var h = $(window).height()-10;
	
		
		
// 		if(h<200){
// 			h=460;
// 		}
		
// 		$("#pl").omPanel({
// 			width : 'fit',
// 			height:h,
// 			iconCls : "",
// 			header : true,
// 			title : '待办任务',
// 			collapsed : false,
// 			collapsible : true,
// 			closable : false,
// 			onOpen : function() {
// 			},
// 			onClose : function() {
// 			},
// 			onExpand : function() {
// 			},
// 			onCollapse : function() {
// 			}
// 		});

		 

	});
</script>
</head>
<body style="height:100%">
	<div class="g_m">
		<!--主面板-->
		<div class="row-fluid">
			<div class="span12">
				<div id="pl">
				<img src="${pageContext.request.contextPath}/img/bg.jpg"></img>
					<div class="clearfix"></div>
<!-- 					<a class="pull-right "> </a> -->
					</div>
			</div>
		</div>
	</div>
</body>
</html>
