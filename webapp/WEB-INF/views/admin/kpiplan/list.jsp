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

	<div id="dialog-modal" title='选择指标项目'>
		<iframe frameborder="0" style="width: 100%; height: 100%;"
			src="about:blank"></iframe>
	</div>

	<script>
		function fillBackAndCloseDialog(kpis) {

			var ids = [];
			$(kpis).each(function() {
				ids.push(this.id);
			});
			var selections = $('#rgrid').omGrid('getSelections', true)[0];
			var planid = selections.id;
			$.post(WEB_ROOT + "/sys/kpiplan/adddtl", {
				planid : planid,
				kpiids : ids.join(",")
			}, function(result) {
				if (result.ok) {

					$('#grid').omGrid(
							"setData",
							WEB_ROOT + "/sys/kpiplan/GridDatadtl?planid="
									+ planid);

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
			}, 'json');

			$("#dialog-modal").omDialog('close');

		};

		function deletedtl(kpiid) {
			var selections = $('#grid').omGrid('getSelections', true)[0];
			var id = selections.id;
			var planid = selections.planid;
			$.post(WEB_ROOT + "/sys/kpiplan/deldtl", {
				id : id
			}, function(result) {
				if (result.ok) {

					$('#grid').omGrid(
							"setData",
							WEB_ROOT + "/sys/kpiplan/GridDatadtl?planid="
									+ planid);

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
			}, 'json');
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

							<tag:combooption dictcode="OPTYPE" editable="false"></tag:combooption>;
							var gridh = $("#center-panel").height()
									- $("#buttonbar").outerHeight(true) - 55;
							var gridw = $("#center-panel").width();

							$('#grid').omGrid({
								title : '',
								limit : 0,
								height : gridh,
								width : gridw * 2.5 / 5 + 180,
								colModel : [ {
									header : '指标编码',
									name : 'dictcode',
									width : 80,
									align : 'center'
								}, {
									header : '指标名称',
									name : 'dictname',
									align : 'center',
									width : 180
								}, {
									header : '操作(本)',
									name : 'op',
									align : 'center',
									width : 60,
									editor : {
										type : "omCombo",
										editable : true,
										options : OPTYPEOptions,
										name : "op"
									},
									renderer : OPTYPERenderer
								}, {
									header : '参数(本)',
									name : 'filter',
									align : 'center',
									width : 180,
									editor : {
										type : "text",
										editable : true,
										name : "filter"
									},
								}, {
									header : '操作(外)',
									name : 'op1',
									align : 'center',
									width : 60,
									editor : {
										type : "omCombo",
										editable : true,
										options : OPTYPEOptions,
										name : "op1"
									},
									renderer : OPTYPERenderer
								}, {
									header : '参数(外)',
									name : 'filter1',
									align : 'center',
									width : 180,
									editor : {
										type : "text",
										editable : true,
										name : "filter1"
									},
								}, {
									header : '排序',
									name : 'korder',
									align : 'center',
									width : 50,
									editor : {
										type : "text",
										editable : true,
										name : "korder"
									},
									
								},{
									header : '指标说明',
									name : 'dictinfo',
									align : 'center',
									width : 200
								} ]
							});

							$("#dialog-modal").omDialog({
								autoOpen : false,
								width : 960,
								height : 600,
								modal : true
							});

							<tag:org editable="false"></tag:org>

							$('#rgrid')
									.omGrid(
											{
												dataSource : WEB_ROOT
														+ "/sys/kpiplan/gridData1",
												method : 'post',
												limit : 0,
												height : gridh,
												width : gridw * 2.5 / 5 - 200,
												colModel : [ {
													header : '方案名称',
													name : 'planname',
													width : 130,
													align : 'center',
													editor : {
														type : "text",
														editable : true,
														name : "planname"
													}
												}, {
													header : '所属机构',
													name : 'orgid',
													width : 150,
													align : 'center',
													editor : {
														type : "omCombo",
														editable : true,
														options : ORGOptions,
														name : "orgid"
													},
													renderer : ORGRenderer
												}, {
													header : '方案说明',
													name : 'planinfo',
													width : 200,
													align : 'center',
													editor : {
														type : "text",
														editable : true,
														name : "planinfo"
													}
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
														var planid = rowData.id;
														$("#grid")
																.omGrid(
																		"setData",
																		WEB_ROOT
																				+ "/sys/kpiplan/GridDatadtl?planid="
																				+ planid);
													}

												}
											});

							$('#buttonbar')
									.omButtonbar(
											{
												btns : [
														{
															label : '新增方案',
															id : "button-new",
															icons : {
																left : WEB_ROOT
																		+ '/img/add.png'
															},
															onClick : function() {
																//WEB_ROOT + "/sys/kpiplan/GridDatadtl?planid="+planid
																$('#grid')
																		.omGrid(
																				"setData",
																				WEB_ROOT
																						+ "/sys/kpiplan/GridDatadtl?planid=-1");
																$('#rgrid')
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
															label : '删除方案',
															id : "button-remove",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/remove.png'
															},
															onClick : function() {
																var selections = $(
																		'#rgrid')
																		.omGrid(
																				'getSelections');
																if (selections.length == 0) {
																	alert(msg.pleaseselect);
																	return false;
																}
																//将选择的记录的id传递到后台去并执行delete操作

																var data = $(
																		'#rgrid')
																		.omGrid(
																				'getSelections',
																				true);

																if (confirm(msg.befordel)) {
																	$('#rgrid')
																			.omGrid(
																					'deleteRow',
																					selections);
																}
															}
														},
														{
															separtor : false
														},
														{
															label : '新增指标',
															id : "button-new",
															icons : {
																left : WEB_ROOT
																		+ '/img/add.png'
															},
															onClick : function() {
																var selections = $(
																		'#rgrid')
																		.omGrid(
																				'getSelections',
																				true);
																if (selections.length == 0) {
																	alert('请选择一条方案信息');
																	return false;
																}
																if (!selections[0].id) {
																	alert('请先保存方案信息后再新增指标!');
																	return false;
																}
																$(
																		"#dialog-modal")
																		.omDialog(
																				'open');
																//下面是缓加载iframe页面（提高性能），如果不弹出dialog则iframe页面永不加载
																var frameLoc = window.frames[0].location;
																//if(frameLoc.href=='about:blank'){
																frameLoc.href = WEB_ROOT
																		+ '/hov/kpidictmuti';
																//}
															}
														},
														{
															separtor : false
														},
														{
															label : '删除指标',
															id : "button-remove",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/remove.png'
															},
															onClick : function() {

																var selectedRecords = $(
																		'#grid')
																		.omGrid(
																				'getSelections',
																				true);

																if (selectedRecords.length == 0) {
																	alert(msg.pleaseselect);
																	return false;
																}
																//将选择的记录的id传递到后台去并执行delete操作

																if (confirm(msg.befordel)) {
																	deletedtl(selectedRecords[0].id);
																}
															}
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
																		$('#rgrid'));
																		loadMask1 = $(
																		'.gBlock',
																		$('#grid'));
																var data = $(
																		'#rgrid')
																		.omGrid(
																				'getChanges');
																var data1 = $(
																		'#grid')
																		.omGrid(
																				'getChanges');
																console
																		.log(data);
																loadMask.show();
																//loadMask1.show();
																var param = {
																	json : JSON
																			.stringify(data),
																			json1 : JSON
																			.stringify(data1),
																	_time_stamp_ : new Date()
																			.getTime()
																};

																$
																		.post(
																				WEB_ROOT
																						+ '/sys/kpiplan/saveGrid',
																				param,
																				function(
																						data) {
																					loadMask
																							.hide();
																					$(
																							'#rgrid')
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