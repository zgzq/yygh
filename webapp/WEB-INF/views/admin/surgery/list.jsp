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
				field : 'dept',
				title : '科室',
				width : 120,
				align : 'center',
				sortable : true
			}, {
				field : 'patient_id',
				title : '住院号',
				width : 80,
				align : 'center',
				sortable : true
			}, {
				field : 'name',
				title : '姓名',
				width : 100,
				align : 'center',
				sortable : true
			}, {
				field : 'sex',
				title : '性别',
				width : 50,
				align : 'center',
				sortable : true,
				
			}, {
				field : 'age',
				title : '年龄',
				width : 50,
				align : 'center',
				sortable : true,
				
			},{
				field : 'charge_type',
				title : '费别',
				width : 80,
				align : 'center',
				sortable : true
			}, {
				field : 'mailing_address',
				title : '现住址',
				width : 150,
				align : 'center',
				sortable : true
			} 
			, {
				field : 'phone',
				title : '电话',
				width : 100,
				align : 'center',
				sortable : true
			}
			, {
				field : 'admission_date_time',
				title : '入院日期',
				width : 120,
				align : 'center',
				sortable : true
			}
			, {
				field : 'operating_date',
				title : '手术日期',
				width : 120,
				align : 'center',
				sortable : true
			}
			, {
				field : 'discharge_date_time',
				title : '出院日期',
				width : 120,
				align : 'center',
				sortable : true
			}
			, {
				field : 'inhos_days',
				title : '住院天数',
				width : 70,
				align : 'center',
				sortable : true
			}
			, {
				field : 'diagnosis_desc',
				title : '诊断',
				width : 120,
				align : 'center',
				sortable : true
			}
			, {
				field : 'operation_desc',
				title : '手术名称',
				width : 120,
				align : 'center',
				sortable : true
			}
			, {
				field : 'operator',
				title : '手术医生',
				width : 80,
				align : 'center',
				sortable : true
			}
			, {
				field : 'assistant',
				title : '辅助医生',
				width : 80,
				align : 'center',
				sortable : true
			}
			, {
				field : 'operat_level',
				title : '手术级别',
				width : 70,
				align : 'center',
				sortable : true
			}
			, {
				field : 'total_payments',
				title : '消费金额',
				width : 80,
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
		var departid=$("#departid").val();
		var year=$("#year").val();
		var month=$("#month").val();
		if (departid=='') {
			alert('请选择科室');
			return;
		}
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
		var departid=$("#departid").val();
		var year=$("#year").val();
		var month=$("#month").val();
		if (departid=='') {
			alert('请选择科室');
			return;
		}
		if (year=='') {
			alert('请选择年度');
			return;
		}
		if (month=='') {
			alert('请选择月份');
			return;
		}
		$("#dg").datagrid({
			url : WEB_ROOT + '/hos/surgery/gridData1?'+$('#fmQuery').serialize()
		});

	}
	function reset(){
		$.ajax({
            //要用post方式   
            type: "Post",
            //方法所在页面和方法名   
            url: WEB_ROOT + '/hos/surgery/geneData?departid='+ $("#departid").val()
			+ "&year=" + $("#year").val()+"&month="+ $("#month").val(),
            data: '',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
            	$.messager.show({
					title : '提示',
					msg : data.msg
				});
                
            },
            error: function (err) {
            	$.messager.show({
					title : '提示',
					msg : data.msg
				});
            }
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
							+ "&year=" + $("#year").val()+"&month="+ $("#month").val(),
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
						iconCls='icon-reload' plain='true' onclick='reset()'>生成</a></td>
					<td><div class='btn-separator'></div></td>
					<td><div class='btn-separator'></div></td>
					<td><div class='btn-separator'></div></td>
					<!-- <td><a href='javascript:void(0);' class='easyui-linkbutton'
						iconCls='icon-redo' plain='true' onclick='imp()'>导入</a></td>
					<td><div class='btn-separator'></div></td>
					<td><div class='btn-separator'></div></td> -->
				</tr>
			</table>
		</div>

		<form id="fmQuery" method="post">
			<div
				style="padding-left: 10px; padding-top: 10px; border-top: 1px #DDDDDD solid">
				科室编码&nbsp;<input id="departid" name="departid"
					class="easyui-textbox"  class="easyui-validatebox" data-options="required:true"  style="width: 80px">&nbsp;&nbsp;
				选择科室&nbsp;<input disabled="disabled" id="department"
					name="department" class="easyui-textbox" style="width: 140px" /> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="openDlg()">...</a> 年度&nbsp;<select class="easyui-combobox"
					name="years" id="year" style="width: 10%;">
					<option value=""></option>
					<option value="2017">2017年</option>
					<option value="2018">2018年</option>
					<option value="2019">2019年</option>
					<option value="2020">2020年</option>
				</select>&nbsp;月度&nbsp;<select class="easyui-combobox" name="month" id="month"
					style="width: 10%;">
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