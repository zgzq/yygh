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
				<table style="width: 100%">
					<tr>
						<td>医生姓名：</td>
						<td><input class="input-text" name="docname" id="docname" /></td>
						<td>科室代码：</td>
						<td><input class="input-text" name=branchcode id="branchcode" /></td>
					</tr>
				</table>
			</form>
		</div>
		<table id="grid"></table>
	</div>

	<script>
		$(document)
				.ready(
						function() {

							$("#center-panel").height($('body').height());
							$("#center-panel").width($('body').width());

							var gridh = $("#center-panel").height()
									- $("#buttonbar").outerHeight(true)
									- $("#search-panel").outerHeight(true) - 60;
							$('#grid').omGrid({
								dataSource : WEB_ROOT + "/sys/doctor/gridData",
								height : gridh,
								width : 'fit',
								method : 'post',
								colModel : [ {
									header : '医生代码',
									name : 'doccode',
									width : 100,
									align : 'left'
								}, {
									header : '医生名称',
									name : 'docname',
									width : 150,
									align : 'left'
								}, {
									header : '医生照片',
									name : 'url',
									width : 100,
									align : 'left'
								}, {
									header : '医生专长',
									name : 'spec',
									width : 150,
									align : 'left'
								}, {
									header : '备注',
									name : 'remark',
									width : 150,
									align : 'left'
								}, {
									header : '科室代码',
									name : 'deptcode',
									width : 100,
									align : 'left'
								}, {
									header : '医生级别',
									name : 'levelname',
									width : 100,
									align : 'left'
								}, {
									header : '性别',
									name : 'sex',
									width : 100,
									align : 'left'
								} ]
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
															label : "新增",
															id : "button-new",
															icons : {
																left : WEB_ROOT
																		+ '/img/add.png'
															},
															onClick : function() {
																art.dialog
																		.open(
																				WEB_ROOT
																						+ '/sys/doctor/toAdd',
																				{
																					width : 800,
																					height : 650,
																					lock : true,
																					title : "新增医生",
																					close : function() {
																						$(
																								'#grid')
																								.omGrid(
																										"setData",
																										WEB_ROOT
																												+ "/sys/doctor/gridData");
																					}
																				});

															}
														},
														{
															separtor : false
														},
														{
															label : "修改",
															id : "button-edit",
															icons : {
																left : WEB_ROOT
																		+ '/img/op-edit.png'
															},
															onClick : function() {
																var selections = $(
																		'#grid')
																		.omGrid(
																				'getSelections',
																				true)[0];
																if (!selections) {
																	art
																			.dialog({
																				content : '请选择一条记录',
																				ok : function() {
																					return true;
																				},
																				cancelVal : '关闭',
																				cancel : true
																			});
																	return;
																}
																;
																var pkid = selections.id;
																art.dialog
																		.open(
																				WEB_ROOT
																						+ '/sys/doctor/toEdit?pkid='
																						+ pkid,
																				{
																					width : 800,
																					height : 650,
																					lock : true,
																					title : "修改医生",
																					close : function() {
																						$(
																								'#grid')
																								.omGrid(
																										"setData",
																										WEB_ROOT
																												+ "/sys/doctor/gridData");
																					}
																				});

															}
														},
														{
															separtor : false
														},
														{
															label : "删除",
															id : "button-remove",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/remove.png'
															},
															onClick : function() {
																var selections = $(
																		'#grid')
																		.omGrid(
																				'getSelections');//行号
																if (selections.length == 0) {
																	alert(msg.pleaseselect);
																	return false;
																}
																//将选择的记录的id传递到后台去并执行delete操作
																var selectvalues = $(
																		'#grid')
																		.omGrid(
																				'getSelections',
																				true);//数据
																var pkid = selectvalues[0].id;
																if (confirm("是否要删除选中记录?")) {
																	console
																			.log(selections);
																	$('#grid')
																			.omGrid(
																					'deleteRow',
																					selections[0]);//不可编辑表格删除不了
																	$
																			.post(
																					WEB_ROOT
																							+ '/sys/doctor/todelete',
																					{
																						pkid : pkid
																					},
																					function(
																							data) {
																						$(
																								'#grid')
																								.omGrid(
																										'reload');//刷新当前页数据
																						if (data.ok) {
																							$.omMessageTip
																									.show({
																										title : "操作成功",
																										content : "操作成功",
																										timeout : 1500
																									});

																						} else {
																							$.omMessageTip
																									.show({
																										title : "操作失败",
																										content : data.msg,
																										timeout : 1500
																									});
																						}

																					},
																					'json');
																}

															}
														},
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
																						+ "/sys/doctor/gridData");
															}
														} ]
											});
						});
	</script>
</body>
</html>