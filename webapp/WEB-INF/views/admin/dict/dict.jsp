<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../common/header.jsp"%>
<%@include file="../common/css.jsp"%>
<style>
html,body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
	overflow:hidden;
}
</style>

</head>
<body >
	<div id="center-panel">
		<div id="buttonbar" style=""></div>
		<div id="search-panel">
			<div style="width: 100%; ">
				<form id="queryform">
					<input type="hidden" name="pid" id="pid" value="0"/> <span class="label">字典描述：</span>
					<input type="text" class="input-text" name="text" id="text" /> <span
						class="label">字典编码：</span> <input type="text" class="input-text"
						name="value" id="value" />
				</form>
			</div>
		</div>
		<table id="grid"></table>
	</div>
	<div id="west-panel">
		<ul id="mytree2"></ul>
	</div>

	<script>
		$(document)
				.ready(
						function() {
							var element = $('body').omBorderLayout({
								panels : [ {
									id : "center-panel",
									header : false,
									region : "center"
								}, {
									id : "west-panel",
									resizable : true,
									collapsible : true,
									title : msg.sys_dict_tree,
									region : "west",
									width : 150
								} ]
							});

							$("#mytree2")
									.omTree(
											{
												dataSource : WEB_ROOT
														+ "/sys/dict/treeData",
												simpleDataModel : true,
												onSuccess: function(data){
													var node = $('#mytree2').omTree('findNode', "id", '0', "",true);
													$('#mytree2').omTree('expand',node);
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
																			+ "/sys/dict/gridData");

													// 													$('#grid')
													// 															.omGrid(
													// 																	"setData",
													// 																	WEB_ROOT
													// 																			+ "/sys/dict/gridData?"+$.param(data));
												}
											});
							var gridh = $("#center-panel").height()-$("#buttonbar").outerHeight(true)-$("#search-panel").outerHeight(true)-70;
							var gridw=  $("#buttonbar").width() ;
							$('#grid').omGrid({
								dataSource : WEB_ROOT + "/sys/dict/gridData",
								height : gridh,
								width : 'fit',
								method : 'post',
								colModel : [ 
								             {
									header : '编码',
									name : 'value',
									width : 80,
									align : 'left',
									editor : {
										rules : [ "required", true, msg.required ],
										type : "text",
										editable : true,
										name : "value"
									}
								}, 
								{
									header : '描述',
									name : 'text',
									width : 100,
									align : 'left',
									editor : {
										rules : [ "required", true, msg.required ],
										type : "text",
										editable : true,
										name : "text"
									}
								}
// 								, {
// 									header : '英文描述',
// 									name : 'texten',
// 									width : 100,
// 									align : 'left',
// 									editor : {
// 										rules : [ "required", true, msg.required ],
// 										type : "text",
// 										editable : true,
// 										name : "texten"
// 									}
// 								} 
								]
							});

							$(window).resize(function() {
								$('#grid').omGrid("resize");
							});

							$("#search-panel").omPanel({
								collapsible : true,
								collapsed : false,
								header : false
							});
							
							
							var btns=[];
							
							

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
															label : "保存",
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
																						+ '/sys/dict/saveGrid',
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
																											+ "/sys/dict/treeData");
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
																						+ "/sys/dict/gridData");
																//有查询条件，显示查询数据
																// $('#grid').omGrid("setData", '../api/"+n21+"/query?'+$.param(data));
															}
														} ]
											});
						});
	</script>
</body>
</html>