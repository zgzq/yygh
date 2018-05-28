<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>年度目标</title>
<jsp:include page="../../inc/injs.jsp" />
<script type="text/javascript">
	var timerId;
	$(function() {
		//数据表部分初始化
		$('#dg').datagrid({
			url : '',
			columns : [ [  {
				field : 'project_name',
				title : '指标名称',
				width : 100,
				align : 'left',
				sortable : true
			}, {
				field : 'value1',
				title : '2018',
				width : 120,
				align : 'center',
				sortable : true
			}, {
				field : 'value2',
				title : '2017',
				width : 120,
				align : 'center',
				sortable : true
			}] ],
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


	});
	function refresh() {
		
		$("#dg").datagrid({
			url : 'gridData?month='+$("#month").val(),
		});
	};
	function reset() {
		$('#fmQuery').form('reset')
	};


	function exportExl(){
		
		var url = WEB_ROOT + '/kpi/rptcancer10/export?month='+$("#month").val();
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
						iconCls='icon-redo' plain='true' onclick='exportExl()'>导出</a></td>
					<td><div class='btn-separator'></div></td>
				</tr>
			</table>
		</div>
		<form id="fmQuery" method="post">
			<div
				style="padding-left: 10px; padding-top: 10px; border-top: 1px #DDDDDD solid">
				&nbsp;月份&nbsp;<select class="easyui-combobox" name="month"
					id="month" style="width: 10%;">
					<option value=""></option>
					<option value="01">1月</option>
					<option value="02">2月</option>
					<option value="03">3月</option>
					<option value="04">4月</option>
					<option value="05">5月</option>
					<option value="06">6月</option>
					<option value="07">7月</option>
					<option value="08">8月</option>
					<option value="09">9月</option>
					<option value="10">10月</option>
					<option value="11">11月</option>
					<option value="12">12月</option>
				</select>&nbsp;&nbsp; &nbsp;&nbsp;
			</div>
		</form>
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



