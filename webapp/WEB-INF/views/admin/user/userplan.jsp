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
		<table>
			<tr>
				<td>
					<table id="rgrid"></table>
				</td>
				<td>
					<table id="grid"></table>
				</td>
			</tr>
		</table>
	</div>

	<input type="hidden" id="authrity_all" />



	<script>
		function check(obj) {
			var oldv = $('#authrity_all').val();
			if (obj.checked) {
				$('#authrity_all').val(oldv + ',' + obj.id);
			} else {
				$('#authrity_all').val(oldv.replace(',' + obj.id, ''));
			}

		}

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

							function dosave() {
								var val = $('#authrity_all').val();
								var data = $('#rgrid').omGrid('getSelections',
										true)[0];
								if (!data) {
									return;
								}
								var userid = data.id;
								$.post(WEB_ROOT + "/sys/userplan/save", {
									id : userid,
									rights : val
								}, function(data) {
									if (data.ok) {
										$.omMessageTip.show({
											title : msg.savesuccess,
											content : msg.savesuccess,
											timeout : 1000
										});
									} else {
										$.omMessageTip.show({
											title : msg.savefailure,
											content : data.msg,
											timeout : 1500
										});
									}
								}, "json");

							}

							var gridh = $("#center-panel").height()
									- $("#buttonbar").outerHeight(true) - 55;
							var gridw = $("#center-panel").width();

							$('#grid')
									.omGrid(
											{
												title : '',
												limit : 0,
												height : gridh,
												width : gridw * 3.5 / 5 - 20,
												colModel : [
														{
															header : 'ID',
															name : 'id',
															width : 80,
															align : 'center'
														},
														{
															header : '方案名称',
															name : 'planname',
															align : 'left',
															width : 180
														},
														{
															header : '选择',
															name : 'hasRight',
															align : 'left',
															width : 'autoExpand',
															renderer : function(
																	colValue,
																	rowData,
																	rowIndex) {
																if (colValue == '1') {
																	var oldv = $(
																			'#authrity_all')
																			.val();
																	$(
																			'#authrity_all')
																			.val(
																					oldv
																							+ ","
																							+ rowData.id);
																	return "<input type='checkbox' id='"
																			+ rowData.id
																			+ "' value='1' onclick='check(this)' checked='checked'/>";
																} else {
																	return "<input type='checkbox' id='"
																			+ rowData.id
																			+ "' value='0' onclick='check(this)'/>";
																}
															}
														} ],
												onRefresh : function() {
													$('#grid')
															.find(
																	"input:checkbox")
															.click(
																	function(
																			event) {
																		event
																				.stopPropagation();
																	});
												}
											});

							$('#rgrid')
									.omGrid(
											{
												dataSource : WEB_ROOT
														+ "/sys/userplan/treeData",
												method : 'post',
												limit : 0,
												height : gridh,
												width : gridw * 1.5 / 5,
												colModel : [ {
													header : '用户名称',
													name : 'realname',
													width : 150,
													align : 'center'
												}, {
													header : '用户ID',
													name : 'id',
													width : 150,
													align : 'center'
												} ],

												onRefresh : function(nowpage,
														records) {
													var rows = [];
													rows.push(0);
													$('#rgrid').omGrid(
															"setSelections",
															rows);
												},
												onRowSelect : function(
														rowIndex, rowData,
														event) {

													if (rowData && rowData.id) {
														var userid = rowData.id;
														$('#authrity_all').val(
																'');
														$("#grid")
																.omGrid(
																		"setData",
																		WEB_ROOT
																				+ "/sys/userplan/gridData?userid="
																				+ userid);
													}

												}
											});

							$('#buttonbar').omButtonbar({
								btns : [ {
									label : '保存权限',
									id : "add",
									onClick : function(event) {
										dosave();
									}
								} ]

							}

							);

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

						});
	</script>
</body>
</html>