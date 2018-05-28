<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="../inc/injs.jsp" />
<script type="text/javascript">
	$(function() {
		/* 初始化dg*/
		$("#dg").datagrid({
			url : '',
			columns : [ [ {
				field : 'year',
				title : '年份',
				width : 100,
				align : 'center',
				sortable : true
			}, {
				field : 'month',
				title : '月份',
				width : 80,
				align : 'center',
				sortable : true
			},  {
				field : 'departopcode',
				title : '科室编码',
				width : 120,
				align : 'center',
				sortable : true
			}, {
				field : 'department',
				title : '科室名称',
				width : 160,
				align : 'center',
				sortable : true
			}, {
				field : 'target',
				title : '项目',
				width : 200,
				align : 'center',
				sortable : true
			}, {
				field : 'monthvalue',
				title : '目标值',
				width : 120,
				align : 'center',
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
		/*初始化dg1l*/
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
		/*初始化impExcel*/
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
	});
	function imp() {
		var year=$("#year").val();
		var month=$("#month").val();
		
		if (year=='') {
			alert('请选择年度');
			return;
		}
		if (month=='') {
			alert('请选择月份');
			return;
		}
		$('#dlg_excel').dialog('open').dialog('setTitle', "导入excel");
		//$('#file_upload').val("");
	};
	function refresh() {
		var year=$("#year").val();
		var month=$("#month").val();
		
		if (year!='' && month=='') {
			alert('请选择月份');
			return;
		}
		if (month !='' && year == '') {
			alert('请选择年');
			return;
		}
		$("#dg").datagrid(
				{
					url : WEB_ROOT + '/hos/target/gridData?'
							+ $("#fmQuery").serialize()
				});

	}

	function openDlg() {
		$('#dlg').dialog('open');
	}

	function refresh1() {
		$("#dg1").datagrid({
			url : WEB_ROOT + '/hos/depart/gridData'
		});
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
	function saveExcel() {

		$('#fmDlgExcel').form(
				'submit',
				{

					url : 'importExcel?departid=' + $("#departid").val()
							+ "&year=" + $("#year").val() + "&month="
							+ $("#month").val(),
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
	function exportExl(){
		
		var url = WEB_ROOT + '/hos/target/export?years='+ $("#year").val()+"&month="+ $("#month").val()+"&departid="+ $("#departid").val();
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
					class="easyui-textbox"  style="width: 80px">&nbsp;&nbsp;
				选择科室&nbsp;<input id="department"
					name="department" class="easyui-textbox" style="width: 140px" /> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="openDlg()">...</a> 年度&nbsp;<select class="easyui-combobox"
					name="years" id="year" style="width: 10%;">
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
				</select>&nbsp;月度&nbsp;<select class="easyui-combobox" name="month"
					id="month" style="width: 10%;">
					<option value=""></option>
					<option value="1">1月</option>
					<option value="2">2月</option>
					<option value="3">3月</option>
					<option value="4">4月</option>
					<option value="5">5月</option>
					<option value="6">6月</option>
					<option value="7">7月</option>
					<option value="8">8月</option>
					<option value="9">9月</option>
					<option value="10">10月</option>
					<option value="11">11月</option>
					<option value="12">12月</option>
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

</body>
</html>