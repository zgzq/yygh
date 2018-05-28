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
				  <span class="label">月份</span>
				  <select  name="month" id="container">
				  	<c:forEach items="${month }" var="m">
				  		<option value="${m }">${m }</option>
				  	</c:forEach>
				  </select>
			</form>
		</div>
	<form name=form1 action="${pageContext.request.contextPath}/kpi/rpthbzm2/download" method="post" target="newWin">  
    <input type="hidden" name="json" id="json" value=""/>
    <input type="hidden" name="month" id="month" value=""/>
    </form> 
		<table id="grid"></table>
	</div>


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
												colModel : 
													[
												     	//第一行
												     	[
												     	 {header:"门诊部分" ,colspan:6}, 
												     	 {header:"住院部分" ,colspan:11},
												     	] ,
												     	//第二行
												     	[{header:"门诊科室" , name:"mzks" , width:80},
												     	{header:"合计" , name:"hj" , width:40},
												     	{header:"初诊" , name:"cz" , width:40},
												     	{header:"复诊" , name:"fz" , width:40},
												     	{header:"急诊" , name:"jz" , width:40},
												     	{header:"专家" , name:"zj" , width:40},
												     	{header:"住院病区" , name:"zyks" , width:80},
												     	{header:"原有数" , name:"yys" , width:40},
												     	{header:"入院数" , name:"rys" , width:40},
												     	{header:"转入数" , name:"zrs" , width:40},
												     	{header:"出院数" , name:"cys" , width:40},
												     	{header:"转出数" , name:"zcs" , width:40},
												     	{header:"实际用" , name:"sjy" , width:40},
												     	{header:"开放数" , name:"kfs" , width:40},
												     	{header:"病床用率" , name:"bcyl" , width:45},
												     	{header:"加床" , name:"jc" , width:40},
												     	{header:"空床" , name:"kc" , width:40}
												     	
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
																var month=document.getElementById("container").value;
																var data = $('#queryform').serializeObject();
																
																$('#grid').omGrid('options').extraData = data;

																$('#grid').omGrid("setData",WEB_ROOT + "/kpi/rpthbzm2/gridData?month="+month); 
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
																var month=document.getElementById("container").value;
																var data = $('#grid').omGrid('getData');
							                                  	var json=JSON.stringify(data);
																$("#json").val(json);
																$("#month").val(month);
																window.open('','newWin');
																form1.submit();
															}
														} ]
											});
						});
	</script>
</body>
</html>