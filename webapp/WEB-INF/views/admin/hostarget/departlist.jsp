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
				<span class="label">名称：</span> <input type="text" class="input-text"
					name="deptname" />
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

							<tag:org editable="false"></tag:org>
							var gridh = $("#center-panel").height()
									- $("#buttonbar").outerHeight(true)
									- $("#search-panel").outerHeight(true) - 60;
							$('#grid').omGrid(
									{
										dataSource : WEB_ROOT
												+ "/hos/depart/gridData",
										height : gridh,
										width : 'fit',
										method : 'post',
										singleSelect : false,
										colModel : [ {
											header : '序列',
											name : 'departid',
											width : 100,
											align : 'center',
											editor : {
												type : "text",
												editable : false,
												name : "departid"
											}
										}, {
											header : '科室名称',
											name : 'department',
											width : 100,
											align : 'center',
											editor : {
												type : "text",
												editable : true,
												name : "department"
											},

										}, {
											header : '科室编码',
											name : 'departopcode',
											width : 100,
											align : 'center',
											editor : {
												type : "text",
												editable : true,
												name : "departopcode"
											}
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
															label : '新增',
															id : "button-new",
															icons : {
																left : WEB_ROOT
																		+ '/img/add.png'
															},
															onClick : function() {
																$('#grid')
																		.omGrid(
																				'insertRow',
																				0,
																				{});
															}
														},
														{
															separtor : false
														},
														{
															label : '删除',
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
																				'getSelections');
																if (selections.length == 0) {
																	alert(msg.pleaseselect);
																	return false;
																}
																//将选择的记录的id传递到后台去并执行delete操作

																var data = $(
																		'#grid')
																		.omGrid(
																				'getSelections',
																				true);

																if (data[0].id == 'admin') {
																	alert(msg.sysroledelinfo);
																	return false;
																}

																if (confirm(msg.befordel)) {
																	$('#grid')
																			.omGrid(
																					'deleteRow',
																					selections);
																}

																// $.post('../api/n21/delete', {
																//     fakeid: toDeleteRecordID
																// }, function(data){
																// $('#grid').omGrid('reload');//刷新当前页数据
																// if(data.ok){
																// 	 $.omMessageTip.show({
																//            title: "操作成功",
																//            content: "删除数据成功",
																//            timeout: 1500
																//       });
																// }else{
																//	$.omMessageTip.show({
																//        title: "操作失败",
																//        content:  data.msg,
																//        timeout: 1500
																//   });
																//}

																// },'json');
															}
														},
														{
															separtor : false
														},
														{
															label : '保存',
															id : "button-save",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/save.png'
															},
															onClick : function() {
																loadMask = $(
																		'.gBlock',
																		$('#grid'));
																var data = $(
																		'#grid')
																		.omGrid(
																				'getChanges');
																console
																		.log(data);
																loadMask.show();
																var param = {
																	json : JSON
																			.stringify(data),
																	_time_stamp_ : new Date()
																			.getTime()
																};

																$
																		.post(
																				WEB_ROOT
																						+ '/hos/depart/saveGrid',
																				param,
																				function(
																						data) {
																					loadMask
																							.hide();
																					$(
																							'#grid')
																							.omGrid(
																									'reload');//刷新当前页数据

																					if (data.ok) {
																						$.omMessageTip
																								.show({
																									title : msg.savesuccess,
																									content : msg.savesuccess,
																									timeout : 1500
																								});

																					} else {
																						$.omMessageTip
																								.show({
																									title : msg.savefailure,
																									content : data.msg,
																									timeout : 1500
																								});
																					}

																				},
																				'json');

															}
														},
														{
															separtor : false
														},
														{
															label : '查询',
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
																						+ "/hos/depart/gridData1");
																//有查询条件，显示查询数据
																// $('#grid').omGrid("setData", '../api/"+n21+"/query?'+$.param(data));
															}
														}, {
															separtor : false
														},
														{
															label : '导出',
															id : "button-export",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/save.png'
															},
															onClick : function() {
																window.open(WEB_ROOT + '/hos/depart/export');
															}
														}, {
															separtor : false
														} ]
											});
						});
	</script>
</body>
</html>