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
				<span class="label">日期:</span><input id="container"   name="pdate"  value="${sdate}"  />
			</form>
		</div>
		<table id="grid"></table>
	</div>
	<%-- <form name=form1 action="${pageContext.request.contextPath}/kpi/rptzm1/download" method="post" target="newWin">  
		<input type="hidden" name="json" id="json" value=""/>
	    <input type="hidden" name="month" id="month" value=""/>
    </form>  --%>
	<script type="text/javascript">
        $(document).ready(function() {
            $('#container').omCalendar({
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
															[
																{header:"" ,  colspan:2  }	,
																{header:"成都誉美医院" ,  colspan:2 }	,
																{header:"郑州京美医院" ,  colspan:2 }	,
																{header:"常德惠民医院" ,  colspan:2 }	
															],
															[
													            {header : '顺序', name : 'seq', width : 20, align : 'center'},	
													            {header : '指标名称', name : 'kpiname', width : 100},	
													            {header : '目标均值', name : 'orgid6ref', width : 50, align : 'center'},	
													            {header : '实际日值', name : 'orgid6val', width : 50, align : 'center',
													            	renderer : function(colValue, rowData, rowIndex) {
									                                     if (colValue < rowData['orgid6ref'] ) { 
									                                         return '<span style="color:red;">' + colValue + '</span>';
									                                     }else{
									                                    	 return '<span>' + colValue + '</span>';
									                                     }
									                                     return colValue;
									                                 }	
													            },	
													            {header : '目标均值', name : 'orgid8ref', width : 50, align : 'center'},	
													            {header : '实际日值', name : 'orgid8val', width : 50, align : 'center',
													            	renderer : function(colValue, rowData, rowIndex) {
									                                     if (colValue < rowData['orgid8ref'] ) { 
									                                         return '<span style="color:red;">' + colValue + '</span>';
									                                     }else{
									                                    	 return '<span>' + colValue + '</span>';
									                                     }
									                                     return colValue;
									                                 }	
													            },	
													            {header : '目标均值', name : 'orgid9ref', width : 50, align : 'center'},	
													            {header : '实际日值', name : 'orgid9val', width : 50, align : 'center',
													            	renderer : function(colValue, rowData, rowIndex) {
									                                     if (colValue < rowData['orgid9ref'] ) { 
									                                         return '<span style="color:red;">' + colValue + '</span>';
									                                     }else{
									                                    	 return '<span>' + colValue + '</span>';
									                                     }
									                                     return colValue;
									                                 }	
													            }
															]
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
														$('#grid').omGrid("setData",WEB_ROOT + "/kpimp/hz2/gridData");
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
														var pdate=document.getElementById("container").value;
														$(".table2excel").table2excel({
										    				exclude: ".noExl",
										    				name: "Excel Document Name",
										    				filename: "目标进度汇总("+pdate+")",
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