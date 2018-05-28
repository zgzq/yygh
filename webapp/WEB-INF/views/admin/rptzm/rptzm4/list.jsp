<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@include file="../../common/header.jsp"%>
<%@include file="../../common/css.jsp"%>
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
		<form name=form1 action="${pageContext.request.contextPath}/kpi/rptzm4/download" method="post" target="newWin">  
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
							
							var month=document.getElementById("container").value;
							
							<tag:org editable="false"></tag:org>
							var gridh = $("#center-panel").height()
									- $("#buttonbar").outerHeight(true)
									- $("#search-panel").outerHeight(true) - 60;
							
							//动态获取列（初始化）
							$.post(WEB_ROOT + "/kpi/rptzm4/gridColumn",{month:month},
									function(jsonData){
										$('#grid').omGrid(
												{
													dataSource:"",
													height : gridh,
													width : 'fit',
													method : 'post',
													limit :0,
													colModel : jsonData
												});
									},'json');  
							
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
																left : WEB_ROOT + '/img/search.png'
															},
															onClick : function() {
																
																
																//动态获取列
																$.post(WEB_ROOT + "/kpi/rptzm4/gridColumn",{month:month},/* {sdate:sdate,edate:edate,}, */
																		function(jsonData){
																			$('#grid').omGrid(
																					{
																						dataSource:"",
																						height : gridh,
																						width : 'fit',
																						method : 'post',
																						limit :0,
																						colModel : jsonData
																					});
																		},'json');  

																var data = $('#queryform').serializeObject();
																
																$('#grid').omGrid('options').extraData = data;

																$('#grid').omGrid("setData",WEB_ROOT + "/kpi/rptzm4/gridData"); 
																
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