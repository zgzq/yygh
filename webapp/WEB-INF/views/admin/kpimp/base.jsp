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
				<span class="label">医院:</span> 
				<select name="orgid" id="orgid">
					<option value="1" selected="selected">京东中美医院</option>
					<option value="2">京东誉美医院</option>
					<option value="3">中美东城医院</option>
					<option value="4">河南整形医院</option>
					<!-- <option value="6">成都誉美医院</option> -->
					<option value="5">河南誉美医院</option>
					<option value="7">株洲同济医院</option>
					<!-- <option value="8">郑州京美医院</option>
					<option value="9">常德惠民医院</option> -->
				</select>
				<span class="label">年份:</span> 
				<select name="year" id="year">
					<option value="2017" selected="selected">2017年</option>
					<option value="2018">2018年</option>
				</select>
				
			</form>
		</div>
		<table id="grid"></table>
	</div>
	<script>
		$(document).ready(function() {
							$("#center-panel").height($('body').height());
							$("#center-panel").width($('body').width());


							var gridh = $("#center-panel").height() - $("#buttonbar").outerHeight(true) - $("#search-panel").outerHeight(true) - 60;
							$('#grid') .omGrid(
											{   dataSource :"",
												height : gridh,
												width : 'fit',
												method : 'post',
												singleSelect : true,
												showIndex : false,  
												colModel : [{
																header : '顺序', name : 'seq', width : 40, align : 'center', editor : { type : "text", editable : true, name : "seq" }
															},												        
															{
																header : '指标', name : 'kpiname', width : 100, align : 'center', editor : { type : "text", editable : true, name : "kpiname" }
															},												        
															{   
																header : '1月', name : 'm1', width : 50, align : 'center', editor : { type : "text", editable : true, name : "m1" }
															},												        
															{   
																header : '2月', name : 'm2', width : 50, align : 'center', editor : { type : "text", editable : true, name : "m2" }
															},												        
															{   
																header : '3月', name : 'm3', width : 50, align : 'center', editor : { type : "text", editable : true, name : "m3" }
															},												        
															{   
																header : '4月', name : 'm4', width : 50, align : 'center', editor : { type : "text", editable : true, name : "m4" }
															},												        
															{   
																header : '5月', name : 'm5', width : 50, align : 'center', editor : { type : "text", editable : true, name : "m5" }
															},												        
															{   
																header : '6月', name : 'm6', width : 50, align : 'center', editor : { type : "text", editable : true, name : "m6" }
															},												        
															{   
																header : '7月', name : 'm7', width : 50, align : 'center', editor : { type : "text", editable : true, name : "m7" }
															},												        
															{   
																header : '8月', name : 'm8', width : 50, align : 'center', editor : { type : "text", editable : true, name : "m8" }
															},												        
															{   
																header : '9月', name : 'm9', width : 50, align : 'center', editor : { type : "text", editable : true, name : "m9" }
															},												        
															{   
																header : '10月', name : 'm10', width : 50, align : 'center', editor : { type : "text", editable : true, name : "m10" }
															},												        
															{   
																header : '11月', name : 'm11', width : 50, align : 'center', editor : { type : "text", editable : true, name : "m12" }
															},												        
															{   
																header : '12月', name : 'm12', width : 50, align : 'center', editor : { type : "text", editable : true, name : "m13" }
															},												        
															{   
																header : '合计', name : 'totle', width : 50, align : 'center', editor : { type : "text", editable : true, name : "totle" }
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
							
							$('#buttonbar').omButtonbar(
									{
										btns : [
												{
													label : '新增',
													id : "button-new",
													icons : {
														left : WEB_ROOT + '/img/add.png'
													},
													onClick : function() {
														$('#grid') .omGrid( 'insertRow', 0, {});
													}
												},
												{
													separtor : true
												},
												{
													label : '删除',
													id : "button-remove",
													disabled : false,
													icons : {
														left : WEB_ROOT + '/img/remove.png'
													},
													onClick : function() {
														var selections = $( '#grid') .omGrid( 'getSelections');
														if (selections.length == 0) {
															alert(msg.pleaseselect);
															return false;
														}
														//将选择的记录的id传递到后台去并执行delete操作

														var data = $( '#grid') .omGrid( 'getSelections', true);

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
													separtor : true
												},
												{
													label : '保存',
													id : "button-save",
													disabled : false,
													icons : {
														left : WEB_ROOT + '/img/save.png'
													},
													onClick : function() {
														var year=$('#year').val();
														var orgid=$('#orgid').val();
														loadMask = $( '.gBlock', $('#grid'));
														var data = $( '#grid') .omGrid( 'getChanges');
														console .log(data);
														loadMask.show();
														var param = {
															json : JSON .stringify(data),
															_time_stamp_ : new Date() .getTime(),
															year :   year,
															orgid : orgid
														};

														$ .post(        WEB_ROOT + '/kpimp/base/saveGrid',
																		param,
																		function( data) {
																			loadMask .hide();
																			$('#grid') .omGrid( 'reload');//刷新当前页数据

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
													separtor : true
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
														var data = $('#queryform').serializeObject();

														$('#grid').omGrid('options').extraData = data;

														$('#grid').omGrid("setData",WEB_ROOT + "/kpimp/base/gridData");
														//有查询条件，显示查询数据
														// $('#grid').omGrid("setData", '../api/"+n21+"/query?'+$.param(data));
													}
												}]
									});
							
						});
	</script>
</body>
</html>