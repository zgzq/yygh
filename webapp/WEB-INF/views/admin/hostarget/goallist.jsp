<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>年度目标</title>
<jsp:include page="../inc/injs.jsp" />
<script type="text/javascript">
	var timerId;
	$(function() {
		//数据表部分初始化
		$('#dg').datagrid({
			url : '',
			columns : [ [  {
				field : 'departopcode',
				title : '科室编码',
				width : 80,
				align : 'left',
				sortable : true
			}, {
				field : 'year',
				title : '年度',
				width : 80,
				align : 'center',
				sortable : true
			}, {
				field : 'department',
				title : '科室名称',
				width : 120,
				align : 'center',
				sortable : true
			}, {
				field : 'goalname',
				title : '项目',
				width : 180,
				align : 'left',
				sortable : true
			}, {
				field : 'yearvalue',
				title : '年度目标',
				width : 80,
				align : 'left',
				sortable : true
			} ] ],
			remoteSort : false,
			singleSelect : true,
			striped : true,
			fit : true,
			border : false,
			rownumbers : true,
			toolbar : "#tb",
			pagination : true,
			pageSize : 30,
			pageList : [ 30, 60, 90 ],
			pageNumber : 1,
			queryParams : {}
		});
		//数据表部分初始化
		$('#dg1').datagrid({
			url : '',
			columns : [ [ {
				field : 'departid',
				title : '科室ID',
				width : 100,
				align : 'center'
			}, {
				field : 'departopcode',
				title : '科室编码',
				width : 100,
				align : 'center'
			}, {
				field : 'department',
				title : '科室名称',
				width : 180,
				align : 'left'
			} ] ],
			singleSelect : true,
			striped : true,
			fit : true,
			border : false,
			rownumbers : true,
			toolbar : "#tb1",
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30 ],
			pageNumber : 1,
			onDblClickRow : function(rowIndex, rowData) {
				departSelect();
			}
		});
		//数据表部分初始化
		$('#dg2').datagrid({
			url : '',
			columns : [ [ {
				field : 'importuserid',
				title : 'userid',
				width : 100,
				align : 'center',
				hidden : true
			}, {
				field : 'importrealname',
				title : '导入用户',
				width : 100,
				align : 'center'
			}, {
				field : 'importdate',
				title : '导入时间',
				width : 180,
				align : 'left'
			} ] ],
			singleSelect : true,
			striped : true,
			fit : true,
			border : false,
			rownumbers : true,
			toolbar : "#tb2",
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 30 ],
			pageNumber : 1
		});
		/* 初始化dlg */
		$('#dlg').dialog({
			title : "选择科室",
			height : 480,
			width : 400,
			closed : true,
			cache : false,
			buttons : '#dlg-buttons',
			modal : true,
			inline : true,
			border : 'thin'
		/* cls:'c5' */
		});
		/* 初始化dlg_pic_edit */
		$('#dlg_excel').dialog({
			height : 150,
			width : 420,
			closed : true,
			cache : false,
			buttons : '#dlg-excel-buttons',
			modal : true,
			inline : true,
			border : 'thin'
		/* cls:'c5' */
		});
		/* 初始化dlg_execing */
		$('#dlg_execing').dialog({
			title : "提示",
			height : 80,
			width : 420,
			closed : true,
			closable : false,
			cache : false,
			modal : true,
			inline : true,
			border : 'thin'
		/* cls:'c5' */
		});
		/* 初始化dlg_impRcd */
		$('#dlg_impRcd').dialog({
			title : "导入历史记录",
			height : 500,
			width : 420,
			closed : true,
			cache : false,
			modal : true,
			inline : true,
			border : 'thin'
		/* cls:'c5' */
		});

	});
	function refresh() {

		$("#dg").datagrid({
			url : 'gridData?' + "year="+$("#year").val()+"&departid="+$("#departid").val(),
		});
	};
	function reset() {
		$('#fmQuery').form('reset')
	};
	function print() {
		$.post('buttonRole', {
			buttonName : "print"
		}, function(result) {
			if (result.success) {
				$("body").jqprint();
			} else {
				$.messager.show({
					title : '提示',
					msg : '没有打印权限!'
				});
			}
		}, 'json');
	};
	function exp() {
		$.post('buttonRole', {
			buttonName : "exp"
		}, function(result) {
			if (result.success) {
				var data = $('#dg').datagrid("getData"); // 获取所有数据
				var json = JSON.stringify(data);
				$("#json").val(json);
				window.open('', 'newWin');
				form1.action = "expToExcel?" + $('#fmQuery').serialize()
				form1.submit();
			} else {
				$.messager.show({
					title : '提示',
					msg : '没有导出权限!'
				});
			}
		}, 'json');
	};
	function imp() {
		var year=$("#year").val();
		if (year=='') {
			alert('请选择年度');
			return;
		}
		$('#dlg_excel').dialog('open').dialog('setTitle', "导入excel");
		$('#file_upload').val("");
	};
	function dwload() {
		$.post('buttonRole', {
			buttonName : "download"
		}, function(result) {
			if (result.success) {
				alert("download");
			} else {
				$.messager.show({
					title : '提示',
					msg : '没有下载权限!'
				});
			}
		}, 'json');
	};
	function openDlg() {
		$('#dlg').dialog('open');
		//$('#dg1').datagrid('load','getGoods?'+$('#fmQuery1').serialize()); 
	}
	function departSelect() {
		var row = $('#dg1').datagrid('getSelected');
		if (row) {
			//$('#fmQuery').form('clear');
			/* alert(row.goodsname); */
			$("#department").textbox("setValue", row.department);
			$("#departid").textbox("setValue", row.departopcode);
			$('#dlg').dialog('close'); // close the dialog
		}

	}
	function refresh1() {
		$("#dg1").datagrid(
				{
					url : WEB_ROOT + '/hos/depart/gridData?'
							+ $('#fmQuery1').serialize()
				});
	};
	/************************************************************************************
	上传EXCEL的方法
	 *************************************************************************************/
	function saveExcel() {
		/* 初始化进度条 */
		/* 	$("#p").dialog({
				modal : true,
				closed : false,
				title : '导入进度',
				height : 'auto'
			}); */
		//每隔0.5秒自动调用方法，实现进度条的实时更新
		//timerId = window.setInterval(getForm, 500);
		//loadfile(); 
		//$('#dlg_execing').dialog('open');
		//alert($("#departid").val());
		$('#fmDlgExcel').form(
				'submit',
				{
					url : 'importExcel?departid=' + $("#departid").val()
							+ "&year=" + $("#year").val(),
					onSubmit : function() {
						//表单验证信息
						return $(this).form('validate');
					},
					success : function(result) {
						var result = eval('(' + result + ')');
						if (result.success) {
							$('#dlg_excel').dialog('close'); // close the dialog

						}
						$.messager.show({
							title : '提示',
							msg : result.msg
						});
						/* window.clearInterval(timerId);
						$("#p").dialog("close"); */
						//$('#dlg_execing').dialog('close');
					}
				});

	}

	function getForm() {
		//使用JQuery从后台获取JSON格式的数据
		$.ajax({
			type : "post",//请求方式
			url : WEB_ROOT + "/hos/goal/getProgressValueByJson",//发送请求地址
			timeout : 30000,//超时时间：30秒
			dataType : "json",//设置返回数据的格式
			//请求成功后的回调函数 data为json格式
			success : function(data) {
				if (data.progressValue >= 100) {
					window.clearInterval(timerId);
					$("#p").dialog("close");
					$('#p').progressbar('setValue', 0);
				}
				$('#p').progressbar('setValue', data.progressValue);
			},
			//请求出错的处理
			error : function() {
				window.clearInterval(timerId);
				alert("请求出错");
			}
		});
	}

	function loadfile() {

		var value = $('#p').progressbar('getValue');
		if (value < 100) {
			value += Math.floor(Math.random() * 10);
			$('#p').progressbar('setValue', value);
			setTimeout(arguments.callee, 200);
		} else {
			$("#p").dialog("close");
			$('#p').progressbar('setValue', 0);
		}

	}
	/*****************************************************************
	导入历史记录
	 *****************************************************************/
	function impRcd() {
		$('#dg2').datagrid('load', 'impRcd');
		$('#dlg_impRcd').dialog('open').dialog('setTitle', "导入历史记录");
	}
	/***************************************************************** 
	删除所选导入数据
	 *****************************************************************/
	function delete2() {
		var row = $('#dg2').datagrid('getSelected');
		if (row) {
			$.messager.confirm('Confirm', '是删除所选导入数据?', function(r) {
				if (r) {
					$.post('delete2', {
						importuserid : row.importuserid,
						importrealname : row.importrealname,
						importdate : row.importdate
					}, function(result) {
						if (result.success) {
							$('#dg2').datagrid('reload'); // reload the user data
							$.messager.show({ // show error message
								title : '提示',
								msg : '删除成功!'
							});
						} else {
							$.messager.show({ // show error message
								title : '提示',
								msg : result.errorMsg
							});
						}
					}, 'json');
				}
			});
		}
	}
	function exportExl(){
		
		var url = WEB_ROOT + '/hos/goal/export?years='+ $("#year").val()+"&departid="+ $("#departid").val();
		window.open(url);
	}
</script>
</head>
<body>
	<!-- 数据列表 -->
	<table id="dg"></table>
	<!-- 表格的工具栏 -->
	<div id="tb" style="padding: 0px; height: auto">
		<div>
			<table cellpadding='1' cellspacing='1'>
				<tr>
					<td><a href='javascript:void(0);' class='easyui-linkbutton'
						iconCls='icon-search' plain='true' onclick='refresh()'>查询</a></td>
					<td><div class='btn-separator'></div></td>
					<td><a href='javascript:void(0);' class='easyui-linkbutton'
						iconCls='icon-reload' plain='true' onclick='reset()'>重置</a></td>
					<td><div class='btn-separator'></div></td>
					<td><div class='btn-separator'></div></td>
					<td><div class='btn-separator'></div></td>
					<td><a href='javascript:void(0);' class='easyui-linkbutton'
						iconCls='icon-redo' plain='true' onclick='imp()'>导入</a></td>
					<td><div class='btn-separator'></div></td>
					<td><a href='javascript:void(0);' class='easyui-linkbutton'
						iconCls='icon-redo' plain='true' onclick='exportExl()'>导出</a></td>
					<td><div class='btn-separator'></div></td>
				</tr>
			</table>
		</div>
		<form id="fmQuery" method="post">
			<div
				style="padding-left: 10px; padding-top: 10px; border-top: 1px #DDDDDD solid">
				科室编码&nbsp;<input id="departid" name="departid"
					class="easyui-textbox" disabled="disabled" style="width: 80px">&nbsp;&nbsp;
				选择科室&nbsp;<input disabled="disabled" id="department"
					name="department" class="easyui-textbox" style="width: 140px" /> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="openDlg()">...</a> 年度&nbsp;<select class="easyui-combobox"
					name="year"  id="year" style="width: 10%;">
					<option value=""></option>
					<option value="2017">2017年</option>
					<option value="2018">2018年</option>
					<option value="2019">2019年</option>
					<option value="2020">2020年</option>
					<option value="2021">2021年</option>
					<option value="2022">2022年</option>
					<option value="2023">2023年</option>
					<option value="2024">2024年</option>
					<option value="2025">2025年</option>
					<option value="2026">2026年</option>
				</select>&nbsp;&nbsp; &nbsp;&nbsp;
			</div>
		</form>
	</div>

	<!-- 表格的工具栏1 -->
	<div id="tb1" style="padding: 0px; height: auto">
		<!-- 表单基本信息对话框按钮 -->
		<div>
			<table cellpadding='1' cellspacing='1'>
				<tr>
					<td><a href='javascript:void(0);' class='easyui-linkbutton'
						iconCls='icon-search' plain='true' onclick='refresh1()'>查询</a></td>
					<td><div class='btn-separator'></div></td>
					<td><a href='javascript:void(0);' class='easyui-linkbutton'
						iconCls='icon-ok' plain='true' onclick='departSelect()'>选择</a></td>
					<td><div class='btn-separator'></div></td>
					<td><a href='javascript:void(0);' class='easyui-linkbutton'
						iconCls='icon-cancel' plain='true'
						onclick="javascript:$('#dlg').dialog('close')">取消</a></td>

				</tr>
			</table>
		</div>
		<form id="fmQuery1" method="post">
			<div
				style="padding-left: 10px; padding-top: 10px; border-top: 1px #DDDDDD solid">
				科室名称&nbsp;<input id="goodsid1" name="goodsid1"
					class="easyui-textbox" style="width: 80px" />&nbsp;&nbsp;
				科室编码&nbsp;<input id="goodsname1" name="goodsname1"
					class="easyui-textbox" style="width: 140px" />&nbsp;&nbsp;
			</div>
		</form>
	</div>
	<div id="dlg">
		<table id='dg1'></table>
	</div>
	<!-- 表单基本信息对话框按钮 -->
	<div id="dlg-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="departSelect()">选择</a> <a
			href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>

	<!-- 上传excel -->
	<div id="dlg_excel">
		<form:form id="fmDlgExcel" method="post" enctype="multipart/form-data"
			style="padding:20px">
			<input id="file_upload" name="file_upload" type="file"
				accept="application/vnd.ms-excel" />
		</form:form>
	</div>
	<div id="dlg-excel-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveExcel()">保存EXCEL</a> <a
			href="javascript:void(0);" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#dlg_excel').dialog('close')">取消</a>
	</div>
	<!-- //进度条div -->
	<div id="p" class="easyui-progressbar" style="width: 400px;"></div>
	<!-- 正在执行对话框 -->
	<div id="dlg_execing">
		<p>导入执行中。。。</p>
	</div>
	<!-- 导入历史 -->
	<div id="dlg_impRcd">
		<table id='dg2'></table>
	</div>
</body>
</html>



