<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@include file="../common/tag.jsp"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%-- <%@include file="../common/header.jsp"%>
<%@include file="../common/css.jsp"%> --%>
<jsp:include page="../inc/injs.jsp" />
<style>
html, body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
}

</style>

<script type="text/javascript">	
$(function() {

		 $("#grid1").datagrid({
				url : '',
				columns : [ [ {
					field : 'patient_id',
					title : 'ID',
					width : 80,
					align : 'center',
					sortable : true
				},{
					field : 'name',
					title : '姓名',
					width : 100,
					align : 'center',
					sortable : true
				}, {
					field : 'sex',
					title : '性别',
					width : 80,
					align : 'center',
					sortable : true
				},  {
					field : 'age',
					title : '年龄',
					width : 120,
					align : 'center',
					sortable : true
				}, {
					field : 'dept_name',
					title : '门诊科室',
					width : 160,
					align : 'center',
					sortable : true
				}, {
					field : 'visit_date',
					title : '就诊时间',
					width : 200,
					align : 'center',
					sortable : true
				}, {
					field : 'mailing_address',
					title : '地址',
					width : 200,
					align : 'center',
					sortable : true
				}, {
					field : 'ordered_by_doctor',
					title : '医生',
					width : 120,
					align : 'center',
					sortable : true
				}, {
					field : 'registration_status',
					title : '状态',
					width : 120,
					align : 'center',
					sortable : true,
					formatter:function(value,row,index){
						if(value == 1 ){
							return "未就诊";
						}else if(value == 2){
							return "就诊";
						}else if(value == 3){
							return "就诊";
						}else if(value == 4){
							return "未就诊";
						}else{}
					}
				}, {
					field : 'charge_type',
					title : '费别',
					width : 120,
					align : 'center',
					sortable : true
				}, {
					field : 'first_visit_indicator',
					title : '初诊/复诊',
					width : 120,
					align : 'center',
					sortable : true,
					formatter:function(value,row,index){
						if(value == 1 ){
							return "初诊";
						}else if(value == 0){
							return "复诊";
						}else{}
					}
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
		 

	});
	function refresh() {
		var year = $("#year").val();
		var month = $("#month").val();
		if(year==''){
			alert("请选择年份");
			return;
		}
		if(month==''){
			alert("请选择月份");
			return;
		}

		$("#grid1").datagrid(
				{
					url : WEB_ROOT + '/zm/crcInfo/gridData1?'
							+ $("#fmQuery").serialize()
				});
	}
	function reset(){
		var year = $("#year").val();
		var month = $("#month").val();
		if(year == ''){
			alert("请选择年份");
			return;
		}
		if(month == ''){
			alert("请选择月份");
			return;
		}

		$.ajax({
            //要用post方式   
            type: "Post",
            //方法所在页面和方法名   
            url: WEB_ROOT + '/zm/crcInfo/geneData1?years='+ $("#year").val()+"&month="+ $("#month").val(),
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
	
	function exportExl(){
		
		var url = WEB_ROOT + '/zm/crcInfo/export1?years='+ $("#year").val()+"&month="+ $("#month").val();
		window.open(url);
	} 
</script>
</head>
<body>

	 <table id="grid1" ></table>
	 
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
				<td><a href='javascript:void(0);' class='easyui-linkbutton'
						iconCls='icon-redo' plain='true' onclick='exportExl()'>导出</a></td>
				<td><div class='btn-separator'></div></td>
			</tr>
		</table>
		</div>
		<form id="fmQuery" method="post">
		<div
			style="padding-left: 10px; padding-top: 10px; border-top: 1px #DDDDDD solid">
			年度&nbsp;<select class="easyui-combobox"
				name="years" id="year" style="width: 10%;">
				<option value=""></option>
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
	
</body>
</html>