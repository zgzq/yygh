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
				<span class="label">姓名：</span> <input type="text" class="input-text"
					name="realname" />
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

							<tag:deptmuti editable="false"></tag:deptmuti>
							<tag:org editable="false"></tag:org>
							<tag:combooption dictcode="SEX" editable="false"></tag:combooption>;
							<tag:combooption dictcode="USERTYPE" editable="false"></tag:combooption>;
							<tag:combooption dictcode="SENDFLAG" editable="false"></tag:combooption>;
							<tag:plantypemuti editable="false"></tag:plantypemuti>;

							var gridh = $("#center-panel").height()
									- $("#buttonbar").outerHeight(true)
									- $("#search-panel").outerHeight(true) - 60;
							$('#grid')
									.omGrid(
											{
												dataSource : WEB_ROOT
														+ "/sys/user/gridData",
												height : gridh,
												width : 'fit',
												method : 'post',
												singleSelect : false,
												colModel : [
														{
															header : '姓名',
															name : 'realname',
															width : 100,
															align : 'center',
															editor : {
																type : "text",
																editable : true,
																name : "realname"
															}
														},
														{
															header : '编号',
															name : 'wxcount',
															width : 50,
															align : 'center',
															editor : {
																rules : [
																		"required",
																		true,
																		msg.required ],
																type : "text",
																editable : true,
																name : "wxcount"
															}
														},
														{
															header : '手机号',
															name : 'mobile',
															width : 100,
															align : 'center',
															editor : {
																type : "text",
																editable : true,
																name : "mobile"
															}
														},
														{
															header : '微信号',
															name : 'wexno',
															width : 100,
															align : 'center',
															editor : {
																type : "text",
																editable : true,
																name : "wexno"
															}
														},
														{
															header : '邮箱',
															name : 'mail',
															width : 100,
															align : 'center',
															editor : {
																type : "text",
																editable : true,
																name : "mail"
															}
														},
														{
															header : '登录名',
															name : 'username',
															width : 100,
															align : 'center',
															editor : {
																type : "text",
																editable : true,
																name : "username"
															}
														},
														{
															header : '密码',
															name : 'userpassword',
															width : 100,
															align : 'center',
															editor : {
																type : "text",
																editable : true,
																name : "userpassword"
															}
														},
														{
															header : '是否微信推送',
															name : 'issend',
															width : 100,
															align : 'center',
															editor : {
																type : "omCombo",
																editable : true,
																options : SENDFLAGOptions,
																name : "issend"
															},
															renderer : SENDFLAGRenderer
														},
														{
															header : '性别',
															name : 'sex',
															width : 60,
															align : 'center',
															editor : {
																type : "omCombo",
																editable : true,
																options : SEXOptions,
																name : "sex"
															},
															renderer : SEXRenderer
														},
														{
															header : '职务',
															name : 'postion',
															width : 100,
															align : 'left',
															editor : {
																type : "text",
																editable : true,
																name : "postion"
															}
														},
														{
															header : '所属机构',
															name : 'orgid',
															width : 100,
															align : 'center',
															editor : {
																type : "omCombo",
																editable : true,
																options : ORGOptions,
																name : "orgid"
															},
															renderer : ORGRenderer
														} ,
														{
															header : '用户类型',
															name : 'usertype',
															width : 80,
															align : 'center',
															editor : {
																type : "omCombo",
																editable : true,
																options : USERTYPEOptions,
																name : "usertype"
															},
															renderer : USERTYPERenderer
														},
														{
															header : '推送类型',
															name : 'plantypes',
															width : 150,
															align : 'center',
															editor : {
																type : "omCombo",
																editable : true,
																options : PLANTYPEOptions,
																name : "plantypes"
															},
															renderer : PLANTYPERenderer
														},
														{
															header : '所属科室',
															name : 'depts',
															width : 250,
															align : 'center',
															editor : {
																type : "omCombo",
																editable : true,
																options : DEPTOptions,
																name : "depts"
															},
															renderer : DEPTRenderer
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
																						+ '/sys/user/saveGrid',
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
																						+ "/sys/user/gridData");
																//有查询条件，显示查询数据
																// $('#grid').omGrid("setData", '../api/"+n21+"/query?'+$.param(data));
															}
														},
														{
															separtor : false
														},
														{
															label : '生成到微信(停用)',
															id : "button-save",
															disabled : true,//暂停使用,同步到微信会吧id号替换成微信的通讯录ＩＤ,会涉及对OA的影响.
															icons : {
																left : WEB_ROOT
																		+ '/img/save.png'
															},
															onClick : function() {
																var users = $(
																		'#grid')
																		.omGrid(
																				'getSelections',
																				true);
																var ids = [];
																$(users)
																		.each(
																				function() {
																					ids
																							.push(this.id);
																				});

																$
																		.post(
																				WEB_ROOT
																						+ '/sys/user/genWx',
																				{
																					userid : ids
																							.join(",")
																				},
																				function(
																						data) {

																					if (data.ok) {
																						alert('生成到微信企业通讯录成功!')

																					} else {
																						alert('生成到微信企业通讯录失败!')
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