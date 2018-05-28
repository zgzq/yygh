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
	<form name=form1 action="${pageContext.request.contextPath}/kpi/rptdtym2/download" method="post" target="newWin">  
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
																{header:"科室" , name:"ks" , rowspan:4 , width:80}, 
																{header:"期末<br/>有位" , name:"qmyw" , rowspan:4 , width:35},
																{header:"住院者动态" , colspan:12},
																{header:"出院<br/>占总日" , name:"cyzzr" ,rowspan:4 , width:35},
																{header:"实际<br/>放床数" , name:"sjfcs" ,rowspan:4 , width:35},
																{header:"平均<br/>放位" , name:"pjfw" ,rowspan:4 , width:35},
																{header:"平均<br/>床作" , name:"pjcz" ,rowspan:4 , width:35},
																{header:"实际<br/>用床数" , name:"sjycs" ,rowspan:4 , width:35},
																{header:"出院者<br/>均院数" , name:"cyzjys" ,rowspan:4 , width:35},
																{header:"治(%)" , name:"zhi" ,rowspan:4 , width:35},
																{header:"好(%)" , name:"hao" ,rowspan:4 , width:35},
																{header:"病(%)" , name:"bing" ,rowspan:4 , width:35},
																{header:"病床<br/>用(%)" , name:"bcy" ,rowspan:4 , width:35},
																{header:"病床<br/>转(次)" , name:"bcz" ,rowspan:4 , width:35},
																{header:"空床<br/>(日)" , name:"kc" ,rowspan:4 , width:35},
																{header:"加床<br/>(日)" , name:"jc" ,rowspan:4 , width:35}  
													     	] ,
													     	//第二行
													     	[
														     	{header:"期初<br/>院数" , name:"qcys" ,rowspan:3 , width:35},
														     	{header:"期内<br/>院数" , name:"qnys" ,rowspan:3 , width:35},
														     	{header:"他科<br/>入院" , name:"tkry" ,rowspan:3 , width:35},
																{header:"出院人数" ,  colspan:7},
														     	{header:"转往<br/>科数" , name:"zwks" ,rowspan:3 , width:35}, 
														     	{header:"期末<br/>院数" , name:"qmys" ,rowspan:3 , width:35}
													     	],
													     	//第三行
													     	 [
													     	{header:"总" , name:"z" ,rowspan:2 , width:35},
															{header:"出院病人数" , colspan:5},
													     	{header:"其" , name:"q" ,rowspan:2 , width:35}
													     	] ,
													     	 //第四行
													     	 [

														     	{header:"合计" , name:"hj" , width:35},
														     	{header:"治愈" , name:"zy" , width:35},
														     	{header:"好转" , name:"hz" , width:35},
														     	{header:"未愈" , name:"wy" , width:35},
														     	{header:"死亡" , name:"sw" , width:35}
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

																$('#grid').omGrid("setData",WEB_ROOT + "/kpi/rptdtym2/gridData?month="+month); 
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