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
<body>
	<div id="west-panel">
		<div style="margin-top: 1px" id="buttonbar">
		</div>
		<!-- 		<div id="buttonbar"></div> -->
		<!-- 		<hr /> -->
		<ul id="navtree">
		</ul>
	</div>

	<div id="center-panel" style="float: left;">
		<table id="grid"></table>
	</div>

	<input type="hidden" id="authrity_all" />


	<div id="dialog-form">
		<form id="myform">
			<input type="hidden" name="roleid" id="roleid" /> <span class="label">角色名称：</span>
			<input type="text" class="input-text" name="rolename" id="rolename"
				style="width: 118px" />
		</form>
	</div>

	<script>
		function check(obj) {
			changed = true;
			var oldv = $('#authrity_all').val();
			if (obj.checked) {
				$('#authrity_all').val(oldv + ',' + obj.id);
			} else {
				$('#authrity_all').val(oldv.replace(',' + obj.id, ''));
			}
			
			//alert($('#authrity_all').val());
		}

		var changed = false;
		var roleid = -1;
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
									header : false,
									region : "west",
									width : 200
								} ]
							});

							function dosave() {
								var val = $('#authrity_all').val();
								$.post(WEB_ROOT + "/sys/role/save", {
									id : roleid,
									rights : val
								}, function(data) {
									if (data.ok) {
										$.omMessageTip.show({
											title : msg.savesuccess,
											content : msg.savesuccess,
											timeout : 1000
										});
										changed = false;
									} else {
										$.omMessageTip.show({
											title : msg.savefailure,
											content : data.msg,
											timeout : 1500
										});
									}
								}, "json");

							}

							var dialog = $("#dialog-form")
									.omDialog(
											{
												width : 300,
												autoOpen : false,
												modal : true,
												resizable : false,
												buttons : {
													"提交" : function() {

														$
																.post(
																		WEB_ROOT
																				+ '/sys/role/add',
																		{
																			"roleid" : $(
																					"#roleid")
																					.val(),
																			rolename : $(
																					"#rolename")
																					.val()
																		},
																		function(
																				data) {
																			if (data.ok) {
																				$.omMessageTip
																						.show({
																							title : msg.savesuccess,
																							content : msg.savesuccess,
																							timeout : 1000
																						});

																				$(
																						"#dialog-form")
																						.omDialog(
																								"close");

																				window.location
																						.reload();
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
														// return false; //阻止form的默认提交动作
													},
													"取消" : function() {
														$("#dialog-form")
																.omDialog(
																		"close");//关闭dialog
													}
												}
											});

							$('#buttonbar')
									.omButtonbar(
											{
												btns : [
														{
															label : '新增',
															id : "add",
															onClick : function(
																	event) {
																dialog
																		.omDialog(
																				"option",
																				"title",
																				'新增');
																dialog
																		.omDialog("open");//显示dialog
															}
														},
														{
															label : '修改',
															id : "rename",
															onClick : function(
																	event) {
																var data = $(
																		'#navtree')
																		.omTree(
																				'getSelected');
																console
																		.log(data);
																if (data.id) {
																	$("#roleid")
																			.val(
																					data.id);
																	$(
																			"#rolename")
																			.val(
																					data.text);
																	dialog
																			.omDialog(
																					"option",
																					"title",
																					'修改');
																	dialog
																			.omDialog("open");//显示dialog
																} else {
																	alert(msg.pleaseselect);
																}
															}
														},
														{
															label : '删除',
															id : "delete",
															onClick : function(
																	event) {
																var data = $(
																		'#navtree')
																		.omTree(
																				'getSelected');
																console
																		.log(data); 
																
																if (data.id!=-1) {
																	
																	artDialog.confirm('是否要删除选中记录',function(){
																		$
																		.post(
																				WEB_ROOT
																						+ "/sys/role/delete",
																				{
																					roleid : data.id
																				},
																				function(
																						data) {
																					if (data.ok) {
																						$.omMessageTip
																								.show({
																									title : msg.savesuccess,
																									content : msg.savesuccess,
																									timeout : 1000
																								});

																						window.location
																								.reload();
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
																	});
																	
																} else {
																	artDialog.alert(msg.pleaseselect);
																}
															}
														},
														{
															label : '保存权限',
															id : "add",
															onClick : function(
																	event) {
																dosave();
															}
														} ]

											}

									);

							var h = $(window).height() - 52;

							$("#leftpanel").omPanel({
								title : "",
								height : h,
								header : false,
								collapsible : true,
								collapsed : false
							});

							var gridh = $("#center-panel").height() - 2;
							$('#grid') .omGrid(
											{
												title : msg.authlist,
												limit : 0,
												height : gridh,
												width : 'fit',
												colModel : [
														{
															header : 'ID',
															name : 'id',
															width : 80,
															align : 'center'
														},
														{
															header : '菜单',
															name : 'text',
															align : 'left',
															width : 180
														} ,
														{
															header : '权限',
															name : 'rights',
															align : 'left',
															width : 'autoExpand',
															renderer : function(
																	colValue,
																	rowData,
																	rowIndex) {

																if (colValue == "") {
																	return "";
																}

																var ss = colValue
																		.split(',');

																console.log(ss);
																console
																		.log(rowData);

																var d = [];

																for (var val = 0; val < ss.length; val++) {
																	var hasr = false;

																	if (rowData.hasRight) {
																		$(
																				rowData.hasRight)
																				.each(
																						function() {
																							if (this == ss[val]) {
																								hasr = true;
																							}
																						});
																	}

																	if (hasr) {
																		d
																				.push("<input type='checkbox' id='"
																						+ rowData.id
																						+ "_"
																						+ ss[val]
																						+ "' value='"
																						+ ss[val]
																						+ "' onclick='check(this)' checked='checked'/>"
																						+ ss[val]);

																		var oldv = $(
																				'#authrity_all')
																				.val();

																		$(
																				'#authrity_all')
																				.val(
																						oldv
																								+ ","
																								+rowData.id
																								+ "_"
																								+ ss[val]);
																	} else {
																		d
																				.push("<input type='checkbox' id='"
																						+ rowData.id
																						+ "_"
																						+ ss[val]
																						+ "' value='"
																						+ ss[val]
																						+ "' onclick='check(this)'/>"
																						+ ss[val]);
																	}

																}
																return d
																		.join("");
															}
														}  ],
												onRefresh : function() {
													$('#grid').find("input:checkbox").click(
																	function(event) {
																		event.stopPropagation();
																	});
												}
											});

							$("#navtree").omTree(
											{
												dataSource : WEB_ROOT + "/sys/role/treeData",
												simpleDataModel : true,
												onBeforeSelect : function(
														nodeData) {
													if (changed) {
														if (confirm(msg.beforesave)) {
															dosave();
															return false;
														}
													}
													return true;
												},
												onSelect : function(nodedata) {
													changed = false;
													roleid = nodedata.id;
													$('#authrity_all').val('');
													$("#grid")
															.omGrid(
																	"setData",
																	WEB_ROOT
																			+ "/sys/role/gridData?roleid="
																			+ nodedata.id);
												},
												onSuccess : function(data) {
													console.log(data);
													if (data.length) {
														var node = $('#navtree')
																.omTree(
																		'findNode',
																		"id",
																		data[0].id,
																		"",
																		true);
														roleid = data[0].id;
														$('#authrity_all').val(
																'');
														$('#navtree').omTree(
																'select', node);

													} else {
														$("#grid")
																.omGrid(
																		"setData",
																		WEB_ROOT
																				+ "/sys/role/gridData?roleid=-1");
													}
												}
											});

							$(window).resize(function() {
								$('#grid').omGrid("resize");
							});

						});
	</script>
</body>
</html>