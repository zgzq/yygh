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
#tab1,#tab2 {
	padding:0px;
}
</style>


<script>	
	$(document).ready(function() {
		 $('#make-tab').omTabs({
		    	height:'fit'
		    });
	    
		  $("#center-panel1").height($('#tab1').height() - 80);
		 /* $("#center-panel1").width($('#tab1').width()); */
		 $("#center-panel2").height($('#tab2').height() - 80);
		 /* $("#center-panel2").width($('#tab2').width()); */ 

		
	    $('#grid1') .omGrid(
				{   dataSource :"",
					height : 'fit', 
					width : 'fit',
					method : 'post',
					singleSelect : true,
					autoScroll : true,
					showIndex : false,  
					colModel : [{header : '指标名称', name : 'kpiname', width : 100, align : 'center'},												        
								{header : '目标日均值', name : 'kpivalueref', width : 60, align : 'center'},												        
								{header : '实际日均值', name : 'kpivalueavg', width : 60, align : 'center'} ,												        
								{header : '1号', name : 'd1', width : 50, align : 'center'},
								{header : '2号', name : 'd2', width : 50, align : 'center'},
								{header : '3号', name : 'd3', width : 50, align : 'center'},
								{header : '4号', name : 'd4', width : 50, align : 'center'},
								{header : '5号', name : 'd5', width : 50, align : 'center'},
								{header : '6号', name : 'd6', width : 50, align : 'center'},
								{header : '7号', name : 'd7', width : 50, align : 'center'},
								{header : '8号', name : 'd8', width : 50, align : 'center'},
								{header : '9号', name : 'd9', width : 50, align : 'center'},
								{header : '10号', name : 'd10', width : 50, align : 'center'},
								{header : '11号', name : 'd11', width : 50, align : 'center'},
								{header : '12号', name : 'd12', width : 50, align : 'center'},
								{header : '13号', name : 'd13', width : 50, align : 'center'},
								{header : '14号', name : 'd14', width : 50, align : 'center'},
								{header : '15号', name : 'd15', width : 50, align : 'center'},
								{header : '16号', name : 'd16', width : 50, align : 'center'},
								{header : '17号', name : 'd17', width : 50, align : 'center'},
								{header : '18号', name : 'd18', width : 50, align : 'center'},
								{header : '19号', name : 'd19', width : 50, align : 'center'},
								{header : '20号', name : 'd20', width : 50, align : 'center'},
								{header : '21号', name : 'd21', width : 50, align : 'center'},
								{header : '22号', name : 'd22', width : 50, align : 'center'},
								{header : '23号', name : 'd23', width : 50, align : 'center'},
								{header : '24号', name : 'd24', width : 50, align : 'center'},
								{header : '25号', name : 'd25', width : 50, align : 'center'},
								{header : '26号', name : 'd26', width : 50, align : 'center'},
								{header : '27号', name : 'd27', width : 50, align : 'center'},
								{header : '28号', name : 'd28', width : 50, align : 'center'},
								{header : '29号', name : 'd29', width : 50, align : 'center'},
								{header : '30号', name : 'd30', width : 50, align : 'center'},
								{header : '31号', name : 'd31', width : 50, align : 'center'}
							   ]
				});
	    
	    $("#search-panel1").omPanel({
			collapsible : true,
			collapsed : false,
			header : false
		});
	    
		
		$('#buttonbar1').omButtonbar(
				{ btns : [{ separtor : true },
							{
								label : '查询',
								id : "button-search1",
								disabled : false,
								icons : { left : WEB_ROOT + '/img/search.png' },
								onClick : function() {
									var data = $('#queryform1').serializeObject();
									$('#grid1').omGrid('options').extraData = data;
									$('#grid1').omGrid("setData",WEB_ROOT + "/kpimp/cdhm/gridData1");
									//有查询条件，显示查询数据
									// $('#grid').omGrid("setData", '../api/"+n21+"/query?'+$.param(data));
								}
							},
							  { separtor : true }]
				});
		
		/******************************************/
		$('#grid2') .omGrid(
				{   dataSource :"",
					height : 'fit',
					width : 1180,
					method : 'post',
					singleSelect : true,
					autoScroll : true,
					showIndex : false,  
					colModel : [{header : '指标名称', name : 'kpiname', width : 100, align : 'center'},												        
								{header : '类型', name : 'kpitype', width : 60, align : 'center'},												        
								{header : '1月', name : 'm1', width : 50, align : 'center'},
								{header : '2月', name : 'm2', width : 50, align : 'center'},
								{header : '3月', name : 'm3', width : 50, align : 'center'},
								{header : '4月', name : 'm4', width : 50, align : 'center'},
								{header : '5月', name : 'm5', width : 50, align : 'center'},
								{header : '6月', name : 'm6', width : 50, align : 'center'},
								{header : '7月', name : 'm7', width : 50, align : 'center'},
								{header : '8月', name : 'm8', width : 50, align : 'center'},
								{header : '9月', name : 'm9', width : 50, align : 'center'},
								{header : '10月', name : 'm10', width : 50, align : 'center'},
								{header : '11月', name : 'm11', width : 50, align : 'center'},
								{header : '12月', name : 'm12', width : 50, align : 'center'}
							   ]
				});
		
		$("#search-panel2").omPanel({
			collapsible : true,
			collapsed : false,
			header : false
		});
		$('#buttonbar2').omButtonbar(
				{ btns : [{ separtor : true },
						  {
								label : '查询',
								id : "button-search2",
								disabled : false,
								icons : { left : WEB_ROOT + '/img/search.png' },
								onClick : function() {
									var data = $('#queryform2').serializeObject();
									$('#grid2').omGrid('options').extraData = data;
									$('#grid2').omGrid("setData",WEB_ROOT + "/kpimp/cdhm/gridData2");
									//有查询条件，显示查询数据
									// $('#grid').omGrid("setData", '../api/"+n21+"/query?'+$.param(data));
								}
						  },
						  { separtor : true }]
				});


	});
</script>
</head>
<body>
	<div id="make-tab">
	     <ul>
	         <li>
	             <a href="#tab1">按日对比</a>
	         </li>
	         <li>
	             <a href="#tab2">按月对比</a>
	         </li>
	     </ul>
	     <div id="tab1">
         	<div id="center-panel1"style="border:0px #F00 solid">
				<div id="buttonbar1"></div>
				<div id="search-panel1">
					<form id="queryform1">
						<span class="label">医院:</span> 
						<select name="orgid" id="orgid1">
							<option value="9" selected="selected">常德惠民医院</option>
						</select>
						<span class="label">月份:</span> 
						<select name="yearmonth" id="yearmonth">
							<option value="201701" selected="selected">2017年1月</option>
							<option value="201702" >2017年2月</option>
							<option value="201703" >2017年3月</option>
							<option value="201704" >2017年4月</option> 
							<option value="201705" >2017年5月</option>
							<option value="201706" >2017年6月</option>
							<option value="201707" >2017年7月</option>
							<option value="201708" >2017年8月</option>
							<option value="201709" >2017年9月</option>
							<option value="201710" >2017年10月</option>
							<option value="201711" >2017年11月</option>
							<option value="201712" >2017年12月</option>
						</select>
						
					</form>
				</div>
				<table id="grid1"></table>
			</div>
	     </div>
	     <div id="tab2">
	         <div id="center-panel2"style="border:0px #F00 solid">
				<div id="buttonbar2"></div>
				<div id="search-panel2">
					<form id="queryform2">
						<span class="label">医院:</span> 
						<select name="orgid" id="orgid2">
							<option value="9" selected="selected">常德惠民医院</option>
						</select>
						<span class="label">月份:</span> 
						<select name="year" id="year">
							<option value="2017" selected="selected">2017年</option>
						</select>
					</form>
				</div>
				<table id="grid2"></table> 
				
			
	     	</div>	
	     </div>
	 </div>	
</body>
</html>