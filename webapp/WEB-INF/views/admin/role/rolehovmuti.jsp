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
		<table id="grid"></table>
	</div>

	<script>
		$(document)
				.ready(
						function() {
							$("#center-panel").height($('body').height());
							$("#center-panel").width($('body').width());
							
							var gridh = $("#center-panel").height()-$("#buttonbar").outerHeight(true)-60;
							$('#grid').omGrid({
								dataSource : WEB_ROOT + "/sys/role/listData",
								height : 'fit',
								width : 'fit',
								limit:0,
								method : 'post',
								singleSelect : false,
								colModel : [ {
									header : 'ID',
									name : 'roleid',
									width : 80,
									align : 'left',
									editor : {
										rules : [ "roleid", true, msg.required ],
										type : "text",
										editable : true,
										name : "roleid"
									}
								}, {
									header : msg.rolename,
									name : 'rolename',
									width : 200,
									align : 'left',
									editor : {
										rules : [ "required", true, msg.required ],
										type : "text",
										editable : true,
										name : "rolename"
									}
								} ],
// 								onRowDblClick:function(rowIndex,rowData,event){
// 									window.parent.fillBackAndCloseDialog(rowData);
// 							     }
								onRefresh:function(nowpage, records){
									var role = '${roles}';
									var roles = role.split(',');
									var rows=[];
									for(var i=0;i<records.length;i++){
										for(var j=0;j<roles.length;j++){
											if(records[i].roleid==roles[j]){
												rows.push(i);break;
											}
									 	}
									}
									$('#grid').omGrid("setSelections", rows );
								}
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
															label : msg.ok,
															id : "button-save",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/import.png'
															},
															onClick : function() {
																var selections =  $(
																'#grid')
																.omGrid(
																		'getSelections',true);
																
																art.dialog.data('values', selections);
																art.dialog.close();
															}
														},
														{
															label : msg.pubclose,
															id : "button-close",
															disabled : false,
															icons : {
																left : WEB_ROOT
																		+ '/img/remove.png'
															},
															onClick : function() {
																art.dialog.data('values', 'close');
																art.dialog.close();
															}
														}  
														]
											});
						});
	</script>
</body>
</html>