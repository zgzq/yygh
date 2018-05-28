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
	overflow: hidden;
}
</style>

</head>
<body>
	<div id="center-panel">
		<div id="buttonbar" style=""></div>
		<div id="search-panel">
			<div style="width: 100%;">
				<form id="queryform">
					<input type="hidden" name="roleid" id="roleid" value="0" /> 
					 <span
						class="label">姓名：</span> <input type="text" class="input-text"
						name="realname" />
				</form>
			</div>
		</div>
		<table id="grid"></table>
	</div>
	<div id="west-panel">
		<ul id="mytree2"></ul>
	</div>

	<div id="dialog-modal" title='选择用户'>
		<iframe frameborder="0"
			style="width: 100%; height: 100%; " src="about:blank"></iframe>
	</div>


	<script>
	
	 function fillBackAndCloseDialog(users){
		 
		 var ids=[];
		 $(users).each(function(){
			 ids.push(this.id);
		 });
		 
		 
	         
	         $
				.post(
						WEB_ROOT
								+ "/sys/userrole/add",
						{
							roleid :$("#roleid").val(),
							userid:ids.join(",")
						},
						function(
								result) {
							if(result.ok){
				        		 var data = $('#queryform')
									.serializeObject();
							$('#grid')
									.omGrid('options').extraData = data;

							$('#grid')
									.omGrid(
											"setData",
											WEB_ROOT
													+ "/sys/userrole/userGridData");
				        		 
							 $.omMessageTip
								.show({
									title : msg.savesuccess,
									content : msg.savesuccess,
									timeout : 1000
								});
				        	 }else{
				        		 $.omMessageTip
									.show({
										title : msg.savefailure,
										content : data.msg,
										timeout : 1500
									});
				        	 }
						},
						'json');
	         
	         
	         
	         $( "#dialog-modal").omDialog('close');

	     };
	     
	     
	     function deleteuserrole(userid){
	    	 $
				.post(
						WEB_ROOT
								+ "/sys/userrole/delete",
						{
							roleid :$("#roleid").val(),
							userid:userid
						},
						function(
								result) {
							if(result.ok){
				        		 var data = $('#queryform')
									.serializeObject();
							$('#grid')
									.omGrid('options').extraData = data;

							$('#grid')
									.omGrid(
											"setData",
											WEB_ROOT
													+ "/sys/userrole/userGridData");
				        		 
							 $.omMessageTip
								.show({
									title : msg.savesuccess,
									content : msg.savesuccess,
									timeout : 1000
								});
				        	 }else{
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
									title : msg.sys_role_tree,
									region : "west",
									width : 150
								} ]
							});
							
							
							
							 $( "#dialog-modal").omDialog({
						            autoOpen: false,
						            width:960,
						            height: 600,
						            modal: true
						        });

							$("#mytree2")
									.omTree(
											{
												dataSource : WEB_ROOT
														+ "/sys/userrole/treeData",
												simpleDataModel : true,
												onSuccess: function(data){
													if (data.length) {
														var node = $('#mytree2')
																.omTree(
																		'findNode',
																		"id",
																		data[0].id,
																		"",
																		true);
														$('#mytree2').omTree(
																'select', node);

													} 
												},
												onSelect : function(nodedata) {

													$("#roleid").val(nodedata.id);
													var data = $('#queryform')
															.serializeObject();


													$('#grid')
															.omGrid('options').extraData = data;

													$('#grid')
															.omGrid(
																	"setData",
																	WEB_ROOT
																			+ "/sys/userrole/userGridData");

												}
											});
							var gridh = $("#center-panel").height()-$("#buttonbar").outerHeight(true)-$("#search-panel").outerHeight(true)-70;
							$('#grid').omGrid({
								height : gridh,
								width : 'fit',
								method : 'post',
								colModel : [ {
									header : '用户名',
									name : 'username',
									width : 100,
									align : 'left'
								}, {
									header : '姓名',
									name : 'realname',
									width : 100,
									align : 'left'
								}]
							});

							$(window).resize(function() {
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
																$( "#dialog-modal").omDialog('open');
												                //下面是缓加载iframe页面（提高性能），如果不弹出dialog则iframe页面永不加载
												                var frameLoc=window.frames[0].location;
												                //if(frameLoc.href=='about:blank'){
												                    frameLoc.href=WEB_ROOT+'/hov/usermuti';
												                //}
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
																
																
																var selectedRecords = $('#grid').omGrid('getSelections',true);
																
															 
																if (selectedRecords.length == 0) {
																	alert(msg.pleaseselect);
																	return false;
																}
																//将选择的记录的id传递到后台去并执行delete操作

																if (confirm(msg.befordel)) {
																	 deleteuserrole(selectedRecords[0].id);
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
																						+ "/sys/userrole/userGridData");
																//有查询条件，显示查询数据
																// $('#grid').omGrid("setData", '../api/"+n21+"/query?'+$.param(data));
															}
														} ]
											});
						});
	</script>
</body>
</html>