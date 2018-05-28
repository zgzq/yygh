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
		 <div id="buttonbar" ></div>
		<div id="search-panel">
				<form id="queryform">
					<span class="label">${my:i18n("syscompany")}：</span>
					<select   class="input-text" name="compcodequery" id="compcodequery" style="height:22px">
					<option></option>
					</select> <span
						class="label">${my:i18n("sysdept")}：</span> <input   class="input-text"
						name="deptcodequery" id="deptcodequery" /><span
						class="label">${my:i18n("sysuserno")}：</span> <input type="text" class="input-text"
						name="empno"   />
						<span
						class="label">${my:i18n("sysusername")}：</span> <input type="text" class="input-text"
						name="realname"  />
 				</form>
		</div>
		<table id="grid"></table>
	</div>

	<script>
		$(document)
				.ready(
						function() {
// 							var element = $('body').omBorderLayout({
// 								panels : [ {
// 									id : "center-panel",
// 									header : false,
// 									region : "center"
// 								}  ]
// 							});

							$("#center-panel").height($('body').height());
							$("#center-panel").width($('body').width());
							 
							
							<tag:company editable="false"></tag:company>
							
							<tag:dept editable="false"></tag:dept>
							
							<tag:combooption dictcode="USERTYPE" editable="false"></tag:combooption>;
							
							<tag:combooption dictcode="USERLEVEL" editable="false"></tag:combooption>;
							<tag:combooption dictcode="USERSTATUS" editable="false"></tag:combooption>;
							
							
// 							$('#compcodequery').omCombo({
// 				                dataSource :  COMPANYOptions.dataSource,
// 				                editable:false,
// 				                onValueChange : function(target, newValue, oldValue, event) {
// 				                    //得到第2个combo的当前值
// 				                    var compcode = $('#compcodequery').omCombo('value');
// 				                	alert(compcode);
// 				                    //用Ajax方式从后台取出城市(岛)的数据并赋值给第3个combo
// 				                    $('#deptcodequery').val('').omCombo('setData',
// 				                            WEB_ROOT+'/sys/org/deptjson?id=' + newValue);
				                    
// 				                }
// 				            });
							
							$(COMPANYOptions.dataSource).each(function(){
								$("#compcodequery").append("<option value='"+this.value+"'>"+this.text+"</option>");
							});
							
							$("#compcodequery").change(function(){
								$('#deptcodequery').val('').omCombo('setData',
 				                            WEB_ROOT+'/sys/org/deptjson?id=' + $("#compcodequery").val());
							});
							
							$('#deptcodequery').omCombo({
				                editable:false,
				            });
							
							var gridh = $("#center-panel").height()-$("#buttonbar").outerHeight(true)-$("#search-panel").outerHeight(true)-60;
							$('#grid').omGrid({
								dataSource : WEB_ROOT + "/sys/user/gridData",
								height : gridh,
								width : 'fit',
								method : 'post',
								colModel : [ {
									header : msg.username,
									name : 'username',
									width : 100,
									align : 'left'
								},{
									header : msg.userno,
									name : 'empno',
									width : 80,
									align : 'left'
								}, {
									header : msg.userrealname,
									name : 'realname',
									width : 80,
									align : 'left'
								}, {
									header : msg.syscompany,
									name : 'compcode',
									width : 200,
									align : 'left',
									renderer:COMPANYRenderer
								}, {
									header : msg.sysdept,
									name : 'deptcode',
									width : 200,
									align : 'left',
									renderer:DEPTRenderer
								}   , {
									header : msg.sysusestatus,
									name : 'status',
									width : 100,
									align : 'left',
									renderer:USERSTATUSRenderer
								}],
								onRowDblClick:function(rowIndex,rowData,event){
									window.parent.fillBackAndCloseDialog(rowData);
							     }
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
															label : msg.pubqry,
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
														}
														/* ,{
															separtor : false
														},
														{
															label : "确定",
															id : "button-save",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/import.png'
															},
															onClick : function() {
																
															}
														}  */
														]
											});
						});
	</script>
</body>
</html>