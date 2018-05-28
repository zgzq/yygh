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
		<div id="buttonbar"></div>
		<table id="grid"></table>
	</div>

	<script>
		$(document)
				.ready(
						function() {

							$("#center-panel").height($('body').height());
							$("#center-panel").width($('body').width());

							var gridh = $("#center-panel").height()
									- $("#buttonbar").outerHeight(true) - 60;
							$('#grid').omGrid(
									{
										dataSource : WEB_ROOT
												+ "/sys/hospital/gridData",
										height : gridh,
										width : 'fit',
										method : 'post',
										colModel : [ {
											header : '医院编码',
											name : 'hosid',
											width : 100,
											align : 'left'
										},{
											header : '医院名称',
											name : 'hosname',
											width : 150,
											align : 'left'
										},{
											header : '地址',
											name : 'address',
											width : 150,
											align : 'left'
										} ,{
											header : '级别',
											name : 'hlevel',
											width : 100,
											align : 'left'
										},{
											header : '邮编',
											name : 'postcode',
											width : 100,
											align : 'left'
										},{
											header : '医院坐标 x轴',
											name : 'coordinatex',
											width : 100,
											align : 'left'
										},{
											header : '医院坐标 Y轴',
											name : 'coordinatey',
											width : 100,
											align : 'left'
										},{
											header : '省份行政编码',
											name : 'provincecode',
											width : 100,
											align : 'left'
										},{
											header : '省份名称',
											name : 'province',
											width : 150,
											align : 'left'
										},{
											header : '地区行政编码',
											name : 'citycode',
											width : 100,
											align : 'left'
										},{
											header : '地区',
											name : 'city',
											width : 150,
											align : 'left'
										},{
											header : '联系电话',
											name : 'tel',
											width : 100,
											align : 'left'
										},{
											header : '医院照片',
											name : 'photourl',
											width : 150,
											align : 'left'
										}  ]
									});

							$(window).resize(function() {
								$("#center-panel").height($('body').height());
								$("#center-panel").width($('body').width());
								$('#grid').omGrid("resize");
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
																						+ '/sys/hospital/toAdd',
																				{
																					width : 800,
																					height : 650,
																					lock : true,
																					title : "新增医院",
																					close : function() {
																						$(
																								'#grid')
																								.omGrid(
																										"setData",
																										WEB_ROOT
																												+ "/sys/hospital/gridData");
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
																						+ '/sys/hospital/toEdit?pkid='
																						+ pkid,
																				{
																					width : 800,
																					height : 650,
																					lock : true,
																					title : "修改医院",
																					close : function() {
																						$(
																								'#grid')
																								.omGrid(
																										"setData",
																										WEB_ROOT
																												+ "/sys/hospital/gridData");
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
																							+ '/sys/hospital/todelete',
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
														} ]
											});
						});
	</script>
</body>
</html>