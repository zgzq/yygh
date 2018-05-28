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
html,body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
}
 
</style>

</head>
<body>
	<div id="center-panel">
		 <div id="buttonbar" ></div>
		<div id="search-panel">
				<form id="queryform">
						<!-- <span
						class="label">指标名称：</span> <input type="text" class="input-text"
						name="dictname"  /> -->
 				</form>
		</div>
		<table id="grid"></table>
	</div>

	<script>
		$(document)
				.ready(
						function() {
// 							var element = $('body').omBorderLayout({
// 								panels : [ {
// 									id : "center-panel",
// 									header : false,
// 									region : "center"
// 								}  ]
// 							});

							$("#center-panel").height($('body').height());
							$("#center-panel").width($('body').width());
							 
							var orgid=${orgid};
							var gridh = $("#center-panel").height()-$("#buttonbar").outerHeight(true)-$("#search-panel").outerHeight(true)-60;
							$('#grid').omGrid({
								dataSource : WEB_ROOT + "/sgwh/manual/gridData2?orgid="+orgid,
								height : gridh,
								width : 'fit',
								method : 'post',
								singleSelect : true,
								colModel : [ {
									header : '方案ID',
									name : 'id',
									width : 100,
									align : 'center'
								},
								{
									header : '方案名称',
									name : 'planname',
									width : 300,
									align : 'center'
								}],
								onRowDblClick:function(rowIndex,rowData,event){
									window.parent.fillBackAndCloseDialog(rowData);
							     } 
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
															label : '确定',
															id : "button-save",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/import.png'
															},
															onClick : function() {
																var selections =  $(
																'#grid')
																.omGrid(
																		'getSelections',true);
																
																window.parent.fillBackAndCloseDialog(selections);
															}
														}  
														]
											});
						});
	</script>
</body>
</html>