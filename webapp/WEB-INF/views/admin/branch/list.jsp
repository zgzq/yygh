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
	overflow: hidden;
}
</style>

</head>
<body>

	<div id="center-panel">
		<div id="buttonbar"></div>
		<div id="search-panel">
			<form id="queryform">
				<input type="hidden" name="pid" id="pid" value="0" />
			</form>
		</div>
		<table id="grid"></table>
	</div>
	<div id="west-panel">
		<div>
			<ul id="mytree2"></ul>
		</div>
	</div>

	<script>
		$(document)
				.ready(
						function() {
							var element = $('body').omBorderLayout({
								fit : true,
								panels : [ {
									id : "center-panel",
									header : false,
									region : "center"
								}, {
									id : "west-panel",
									resizable : true,
									collapsible : true,
									title : msg.sys_org_tree,
									region : "west",
									width : 150
								} ]
							});

							$("#mytree2")
									.omTree(
											{
												dataSource : WEB_ROOT
														+ "/sys/org/treeData",
												simpleDataModel : true,
												onSuccess : function(data) {
													var node = $('#mytree2')
															.omTree('findNode',
																	"id", '0',
																	"", true);
													$('#mytree2').omTree(
															'expand', node);
												},
												onSelect : function(nodedata) {

													$("#pid").val(nodedata.id);
													var data = $('#queryform')
															.serializeObject();

													console.log(data);

													$('#grid')
															.omGrid('options').extraData = data;

													$('#grid')
															.omGrid(
																	"setData",
																	WEB_ROOT
																			+ "/sys/org/gridData");

													// 													$('#grid')
													// 															.omGrid(
													// 																	"setData",
													// 																	WEB_ROOT
													// 																			+ "/sys/org/gridData?"+$.param(data));
												}
											});

							<tag:combooption dictcode="LANG" editable="false"></tag:combooption>;
							<tag:combooption dictcode="COMPTYPE" editable="false"></tag:combooption>
							<tag:combooption dictcode="KEYDEPT" editable="false"></tag:combooption>
							<tag:combooption dictcode="BRANCHTYPE" editable="false"></tag:combooption>

							var gridh = $("#center-panel").height()
									- $("#buttonbar").outerHeight(true) - 30;
							var gridw = $("#center-panel").width();
							$('#grid')
									.omGrid(
											{
												dataSource : WEB_ROOT
														+ "/sys/org/gridData",
												height : gridh,
												width : 'fit',
												method : 'post',
												singleSelect : false,
												colModel : [
														{
															header : '编码',
															name : 'code',
															width : 150,
															align : 'left',
															editor : {
																rules : [
																		"required",
																		true,
																		msg.required ],
																type : "text",
																editable : true,
																name : "code"
															}
														},
														{
															header : '名称',
															name : 'name',
															width : 200,
															align : 'left',
															editor : {
																rules : [
																		"required",
																		true,
																		msg.required ],
																type : "text",
																editable : true,
																name : "name"
															}
														},
														{
															header : '是否重点科室',
															name : 'iskey',
															width : 100,
															align : 'center',
															editor : {
																rules : [
																		"required",
																		true,
																		msg.required ],
																type : "omCombo",
																editable : true,
																options : KEYDEPTOptions,
																name : "iskey",
															},
															renderer : KEYDEPTRenderer
														},
														{
															header : '类型',
															name : 'type',
															width : 100,
															align : 'center',
															editor : {
																rules : [
																		"required",
																		true,
																		msg.required ],
																type : "omCombo",
																editable : true,
																options : BRANCHTYPEOptions,
																name : "type",
															},
															renderer : BRANCHTYPERenderer
														} ]
											});

							$(window).resize(
									function() {

										$("#center-panel").height(
												$('body').height());
										$("#center-panel").width(
												$('body').width()
														- $("#west-panel")
																.outerWidth()
														- 6);

										$('#grid').omGrid("resize");
									});

							// 							$("#search-panel").omPanel({
							// 								collapsible : true,
							// 								collapsed : false,
							// 								header : false
							// 							});

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
																				{
																					pid : $(
																							"#pid")
																							.val()
																				});
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
																						+ '/sys/org/saveGrid',
																				param,
																				function(
																						data) {
																					loadMask
																							.hide();
																					$(
																							'#grid')
																							.omGrid(
																									'reload');//刷新当前页数据

																					$(
																							"#mytree2")
																							.omTree(
																									'setData',
																									WEB_ROOT
																											+ "/sys/org/treeData");
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
																						+ "/sys/org/gridData");
																//有查询条件，显示查询数据
																// $('#grid').omGrid("setData", '../api/"+n21+"/query?'+$.param(data));
															}
														},
														{
															separtor : false
														},
														{
															label : '生成到微信',
															id : "button-save",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/save.png'
															},
															onClick : function() {
																var branchs = $(
																		'#grid')
																		.omGrid(
																				'getSelections',
																				true);
																var ids = [];
																$(branchs)
																		.each(
																				function() {
																					ids
																							.push(this.id);
																				});

																$
																		.post(
																				WEB_ROOT
																						+ '/sys/org/genWx',
																				{
																					branchs : ids
																							.join(",")
																				},
																				function(
																						data) {

																					if (data.ok) {
																						alert('生成到微信企业组织机构成功!')

																					} else {
																						alert('生成到微信企业组织机构失败!')
																					}

																				},
																				'json');

															}
														} ]
											});
						});
	</script>
</body>
</html>