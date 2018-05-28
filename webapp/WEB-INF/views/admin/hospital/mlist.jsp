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
				 <span
						class="label">发布日期：</span> <input   class="input-text"
						name="fbrq" id="fbrq" /><span
						class="label">标题：</span> <input type="text" class="input-text"
						name="bt"   />
						<span class="label">发布人：</span>
						<input type="text" class="input-text" name="fbr"   />
 				</form>
		</div>
		<table id="grid"></table>
	</div>

	<script>
		$(document)
				.ready(
						function() {
							
							$('#fbrq').omCalendar({
				                dateFormat : "yy-mm-dd"
				            });
						

							$("#center-panel").height($('body').height());
							$("#center-panel").width($('body').width());
							
							
							var gridh = $("#center-panel").height()-$("#buttonbar").outerHeight(true)-$("#search-panel").outerHeight(true)-60;
							$('#grid').omGrid({
								dataSource : WEB_ROOT + "/sys/notice/gridData",
								height : gridh,
								width : 'fit',
								method : 'post',
								title:'公共发布',
								colModel : [ {
									header : '发布人',
									name : 'fbr',
									width : 80,
									align : 'left'
								}, {
									header : '发布日期',
									name : 'fbrq',
									width : 100,
									align : 'left'
								},{
									header : '标题',
									name : 'bt',
									width : 250,
									align : 'left'
								}, {
									header : '状态',
									name : 'flagname',
									width : 100,
									align : 'left'
								}, {
									header : '备注',
									name : 'memo',
									width : 200,
									align : 'left'
								}, {
									header : '标识',
									name : 'pkdi',
									width : 10,
									align : 'left'
								}]
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
															label : "预览",
															id : "button-edit",
															icons : {
																left : WEB_ROOT
																		+ '/img/op-edit.png'
															},
															onClick : function() {
																var selections =  $('#grid').omGrid('getSelections',true)[0];
																if (!selections) {
																	art.dialog({
																		content : '请选择一条记录',
																		ok : function() {
																			return true;
																		},
																		cancelVal : '关闭',
																		cancel : true
																	});
																	return;
																};
																var pkid=selections.pkid;
																art.dialog.open(WEB_ROOT+'/sys/noticelist/toView?pkid='+pkid,{width:960,height:500,lock:true,title:"修改问卷设置",
																	close : function() {
																	}});
																
															}
														} ,
														{
															separtor : false
														},
														{
															label : "查询",
															id : "button-search",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/search.png'
															},
															onClick : function() {
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
																						+ "/sys/noticelist/gridData");
																//有查询条件，显示查询数据
																// $('#grid').omGrid("setData", '../api/"+n21+"/query?'+$.param(data));
															}
														} ]
											});
						});
	</script>
</body>
</html>