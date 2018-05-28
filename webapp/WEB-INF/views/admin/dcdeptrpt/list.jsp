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

</head>
<body>
	<div id="center-panel">
		<div id="buttonbar"></div>
		<div id="search-panel">
			<form id="queryform">
				<span
				  class="label">起始日期:</span><input id="container"   name="sdate"  value="${sdate}"  />
				  <span
				  class="label">终止日期:</span><input id="container2"   name="edate"  value="${edate}"   />
			</form>
		</div>
		<table id="grid"></table>
	</div>
	<form name=form1 action="${pageContext.request.contextPath}/kpi/dcdept/download" method="post" target="newWin">  
		<input type="hidden" name="json" id="json" value=""/>
		<input type="hidden" name="sdate" id="sdate" value=""/>
		<input type="hidden" name="edate" id="edate" value=""/>
    </form> 
	
	 <script type="text/javascript">
        $(document).ready(function() {
            $('#container').omCalendar({
                startDay : 1
            });
        });
    </script>
     <script type="text/javascript">
        $(document).ready(function() {
            $('#container2').omCalendar({
                startDay : 1
            });
        });
    </script>

	<script>
		$(document)
				.ready(
						function() {
							$("#center-panel").height($('body').height());
							$("#center-panel").width($('body').width());

							<tag:org editable="false"></tag:org>
							var gridh = $("#center-panel").height()
									- $("#buttonbar").outerHeight(true)
									- $("#search-panel").outerHeight(true) - 60;
							$('#grid')
									.omGrid(
											{
												dataSource:"",
												height : gridh,
												width : 'fit',
												method : 'post',
												limit :0,
												colModel : [
												        [
															{
																header : '科室名称',rowspan:2,name : '科室名称',width : 120
															},
															{
																header : '门诊量',rowspan:2,name : '门诊量',width : 80
															},
															{
																header : '门诊收入',rowspan:2,name : '门诊收入',width : 80
															},
															{
																header : '住院量',colspan:3
															},
															{
																header : '住院收入',rowspan:2,name : '住院收入',width : 80
															},
															{
																header : '总收入',rowspan:2,name : '总收入',width : 80
															}
												        
												        ] ,  
												        [
															{
																header : '入院量',
																name : '入院量',
																width : 80,
																align : 'center'
															},
															{
																header : '出院量',
																name : '出院量',
																width : 80,
																align : 'center'
															},
															{
																header : '在院量',
																name : '在院量',
																width : 80,
																align : 'center'
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

							$('#buttonbar')
									.omButtonbar(
											{
												btns : [
														{
															label : '查询',
															id : "button-search",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/search.png'
															},
															onClick : function() {
															var sdate=document.getElementById("container").value;
																var edate=document.getElementById("container2").value;
																if(!sdate||!edate){
																	alert("开始日期和结束日期必须选择!");
																	return;
																}
																var data = $(
																		'#queryform')
																		.serializeObject();

																$('#grid')
																		.omGrid(
																				'options').extraData = data;

																$('#grid')
																		.omGrid(
																				"setData",
																				WEB_ROOT
																						+ "/kpi/dcdept/gridData");
																//有查询条件，显示查询数据
																// $('#grid').omGrid("setData", '../api/"+n21+"/query?'+$.param(data));
															}
														},
														{
															separtor : false
														},
														{
															label : '导出',
															id : "button-imp",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/export.png'
															},
															onClick : function() {
															    var data = $('#grid').omGrid('getData');
															    var json=JSON.stringify(data);
																var sdate=document.getElementById("container").value;
																var edate=document.getElementById("container2").value;
																$("#json").val(json);
																$("#sdate").val(sdate);
																$("#edate").val(edate);
																window.open('','newWin');
																form1.submit();
															}
														} ]
											});
						});
	</script>
</body>
</html>