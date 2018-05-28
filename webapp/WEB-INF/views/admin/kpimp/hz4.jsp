<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@include file="../common/header.jsp"%>
<%@include file="../common/css.jsp"%>
<style>
html, body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0; 
}
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.table2excel.js"></script>
</head>
<body>
	<div id="center-panel" class="table-responsive table2excel" data-tableName="Test Table 1">
		<div id="buttonbar"></div>
		<div id="search-panel">
			<form id="queryform">
				<span class="label">开始日期:</span><input id="container1"   name="psdate"   />
				<span class="label">结束日期:</span><input id="container2"   name="pedate"   />
			</form>
		</div>
		<table id="grid"></table>
	</div>
	<script type="text/javascript">
        $(document).ready(function() {
            $('#container1').omCalendar({
                startDay : 1
            });
            $('#container2').omCalendar({
                startDay : 1
            });
        });
    </script>
	<script>
		$(document).ready(function() {
			
							$("#center-panel").height($('body').height());
							$("#center-panel").width($('body').width());

							var gridh = $("#center-panel").height() - $("#buttonbar").outerHeight(true) - $("#search-panel").outerHeight(true) - 60;
							$('#grid') .omGrid(
											{   dataSource :"",
												height : gridh,
												width : 'fit',
												method : 'post',
												singleSelect : true,
												showIndex : false,  
												colModel :[
													            {header : '顺序', name : 'seq', width : 20, align : 'center'},	
													            {header : '指标名称', name : 'kpiname', width : 100},	
													            {header : '成都誉美医院', name : 'orgid6val', width : 100, align : 'center'},	
													            {header : '郑州京美医院', name : 'orgid8val', width : 100, align : 'center'},	
													            {header : '常德惠民医院', name : 'orgid9val', width : 100, align : 'center'}
														  ]
											}); 
							
							$(window).resize(function() {
								$("#center-panel").height($('body').height());
								$("#center-panel").width($('body').width());
								$('#grid').omGrid("resize");
							});

							$("#search-panel").omPanel({
								collapsible : true,
								collapsed : false,
								header : false
							});
							
							$('#buttonbar').omButtonbar(
									{
										btns : [
												{
													separtor : true
												},
												{
													label : '查询',
													id : "button-search",
													disabled : false,
													icons : {
														left : WEB_ROOT + '/img/search.png'
													},
													onClick : function() {
														//查询数据
														var data = $('#queryform').serializeObject();
														$('#grid').omGrid('options').extraData = data;
														$('#grid').omGrid("setData",WEB_ROOT + "/kpimp/hz4/gridData");
													}
												},{
													separtor : true
												},
												{
													label : '导出',
													id : "button-export",
													disabled : false,
													icons : {
														left : WEB_ROOT + '/img/export.png'
													},
													onClick : function() {
														var psdate=document.getElementById("container1").value;
														var pedate=document.getElementById("container2").value;
														$(".table2excel").table2excel({
										    				exclude: ".noExl",
										    				name: "Excel Document Name",
										    				filename: "目标进度汇总("+psdate+"至"+pedate+")",
										    				exclude_img: true,
										    				exclude_links: true,
										    				exclude_inputs: true
										    			});
													}
												},
												{
													separtor : true
												}
												
												]
									});
							
						});
	</script>
</body>
</html>