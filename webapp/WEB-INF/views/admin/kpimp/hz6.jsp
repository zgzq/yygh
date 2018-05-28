<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<!-- 引入 macarons 主题 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/macarons.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/vintage.js"></script>
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
	<div id="center-panel" >
		<div id="buttonbar"></div>
		<div id="search-panel">
			<form id="queryform">
				<span class="label">年份:</span> 
						<select name="year" id="year">
							<option value="2017" selected="selected">2017年</option>
							<option value="2018" >2018年</option>
							<option value="2019" >2019年</option>
							<option value="2020" >2020年</option> 
							<option value="2021" >2021年</option>
							<option value="2022" >2022年</option>
							<option value="2023" >2023年</option>
							<option value="2024" >2024年</option>
						</select>
				<span class="label">月份:</span> 
						<select name="month" id="month">
							<option value="01" selected="selected">1月</option>
							<option value="02" >2月</option>
							<option value="03" >3月</option>
							<option value="04" >4月</option> 
							<option value="05" >5月</option>
							<option value="06" >6月</option>
							<option value="07" >7月</option>
							<option value="08" >8月</option>
							<option value="09" >9月</option>
							<option value="10" >10月</option>
							<option value="11" >11月</option>
							<option value="12" >12月</option>
						</select>
				<input type="hidden" name="yearmonthname" id="yearmonthname"/> 
				<input type="hidden" name="kpiname" id="kpiname"/> 
			</form>
		</div>
		<table id="grid"></table>
		
		<div id="dialog-sr" title="收入图表"></div>
		<div id="dialog-mzl" title="门诊量图表"></div>
		<div id="dialog-zyl" title="住院量图表"></div>
	</div>

	<script>
	/* 全局变量 */
	var v_title; 
	var v_yearmonthname; 
    var v_orgname; 
    var v_percent;  
    var v_currentPercent;
		$(document).ready(function() {
							/* 初始化弹窗 */
							$( "#dialog-sr").omDialog({
								autoOpen: false,
								height: 400,
								width: 900,
								modal: true
							});
							$( "#dialog-mzl").omDialog({
								autoOpen: false,
								height: 400,
								width: 900,
								modal: true
							});
							$( "#dialog-zyl").omDialog({
								autoOpen: false,
								height: 400,
								width: 900,
								modal: true
							});
							/* 初始化grid */
							$("#center-panel").height($('body').height() );
							$("#center-panel").width($('body').width());

							var gridh = $("#center-panel").height() - $("#buttonbar").outerHeight(true) - $("#search-panel").outerHeight(true) - 60;
							$('#grid') .omGrid(
											{   dataSource :"",
												height : gridh,
												width : 'fit',
												method : 'post',
												limit :0,
												singleSelect : true,
												showIndex : false,  
												colModel :[
															[
																{header:"" ,  colspan:2  }	,
																{header:"成都誉美医院" ,  colspan:3 }	,
																{header:"郑州京美医院" ,  colspan:3 }	,
																{header:"常德惠民医院" ,  colspan:3 }	
															],
															[
															 {header : '顺序', name : 'seq', width : 20, align : 'center'},	
															 {header : '指标名称', name : 'kpiname', width : 100},	
															 
															 {header : '目标值', name : 'orgid6ref', width : 50, align : 'center'},	
															 {header : '实际值', name : 'orgid6val', width : 50, align : 'center',
															 	renderer : function(colValue, rowData, rowIndex) {
															          if (colValue < rowData['orgid6ref'] ) { 
															              return '<span style="color:red;">' + colValue + '</span>';
															          }else{
															         	 return '<span>' + colValue + '</span>';
															          }
															          return colValue;
															      }	
															 },
															 {header : '进度', name : 'orgid6per', width : 50, align : 'center',
																 	renderer : function(colValue, rowData, rowIndex) {
																          if (rowData['orgid6val'] < rowData['orgid6ref'] ) { 
																              return '<span style="color:red;">' + colValue + '</span>';
																          }else{
																         	 return '<span>' + colValue + '</span>';
																          }
																          return colValue;
																      }	
																 },	
															 
															 {header : '目标值', name : 'orgid8ref', width : 50, align : 'center'},	
															 {header : '实际值', name : 'orgid8val', width : 50, align : 'center',
															 	renderer : function(colValue, rowData, rowIndex) {
															          if (colValue < rowData['orgid8ref'] ) { 
															              return '<span style="color:red;">' + colValue + '</span>';
															          }else{
															         	 return '<span>' + colValue + '</span>';
															          }
															          return colValue;
															      }	
															 },	
															 {header : '进度', name : 'orgid8per', width : 50, align : 'center',
																 	renderer : function(colValue, rowData, rowIndex) {
																          if (rowData['orgid8val'] < rowData['orgid8ref'] ) { 
																              return '<span style="color:red;">' + colValue + '</span>';
																          }else{
																         	 return '<span>' + colValue + '</span>';
																          }
																          return colValue;
																      }	
																 },	
															 
															 {header : '目标值', name : 'orgid9ref', width : 50, align : 'center'},	
															 {header : '实际值', name : 'orgid9val', width : 50, align : 'center',
															 	renderer : function(colValue, rowData, rowIndex) {
															          if (colValue < rowData['orgid9ref'] ) { 
															              return '<span style="color:red;">' + colValue + '</span>';
															          }else{
															         	 return '<span>' + colValue + '</span>';
															          }
															          return colValue;
															      }	
															 },	
															 {header : '进度', name : 'orgid9per', width : 50, align : 'center',
																 	renderer : function(colValue, rowData, rowIndex) {
																          if (rowData['orgid9val'] < rowData['orgid9ref'] ) { 
																              return '<span style="color:red;">' + colValue + '</span>';
																          }else{
																         	 return '<span>' + colValue + '</span>';
																          }
																          return colValue;
																      }	
																 }
															]
														  ]
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
													separtor : true
												},
												{
													label : '查询',
													id : "button-search1",
													disabled : false,
													icons : { left : WEB_ROOT + '/img/search.png' },
													onClick : function() {
														//刷新grid
														var data = $('#queryform').serializeObject();
														$('#grid').omGrid('options').extraData = data;
														$('#grid').omGrid("setData",WEB_ROOT + "/kpimp/hz6/gridData");
													}
												},{
													separtor : true
												},{
													label : '收入图表',
													id : "button-sr",
													disabled : false,
													icons : { left : WEB_ROOT + '/img/chart-bar.png' },
													onClick : function() {
														/* 生成chart */
														document.getElementById("yearmonthname").value = $("#year").find("option:selected").text()+$("#month").find("option:selected").text(); 
														document.getElementById("kpiname").value = '收入(万元)';
														//以ajax方式刷新图表
									                    $("#queryform").omAjaxSubmit({
									                        url :'getHz6',
									                        beforeSubmit:function(){
									                            return true;
									                        },
									                        success:function(data){
									        		            v_title=data.yearmonthname+'收入目标完成率'; 
									        		            v_yearmonthname=data.yearmonthname;
									        		            v_orgname=data.orgname; 
									        		            v_percent=data.percent;   
									        		            v_currentPercent=data.currentPercent;   
									        		        	drewChart('dialog-sr'); //绘画
									                        }
									                    });
														$( "#dialog-sr").omDialog('open');
													}
												},{
													separtor : true
												},{
													label : '门诊量图表',
													id : "button-mzl",
													disabled : false,
													icons : { left : WEB_ROOT + '/img/chart-line.png' },
													onClick : function() {
														/* 生成chart */
														document.getElementById("yearmonthname").value = $("#year").find("option:selected").text()+$("#month").find("option:selected").text(); 
														document.getElementById("kpiname").value = '门诊量(人次)'; 
														//以ajax方式刷新图表
									                    $("#queryform").omAjaxSubmit({
									                        url :'getHz6',
									                        beforeSubmit:function(){
									                            return true;
									                        },
									                        success:function(data){
									        		            v_title=data.yearmonthname+'门诊量目标完成率'; 
									        		            v_yearmonthname=data.yearmonthname;
									        		            v_orgname=data.orgname; 
									        		            v_percent=data.percent;   
									        		            v_currentPercent=data.currentPercent;   
									        		        	drewChart('dialog-mzl'); //绘画
									                        }
									                    });
														$( "#dialog-mzl").omDialog('open');
													}
												},{
													separtor : true
												},{
													label : '住院量图表',
													id : "button-zyl",
													disabled : false,
													icons : { left : WEB_ROOT + '/img/chart-pie.png' },
													onClick : function() {
														/* 生成chart */
														document.getElementById("yearmonthname").value = $("#year").find("option:selected").text()+$("#month").find("option:selected").text(); 
														document.getElementById("kpiname").value = '住院量(人次)'; 
														//以ajax方式刷新图表
									                    $("#queryform").omAjaxSubmit({
									                        url :'getHz6',
									                        beforeSubmit:function(){
									                            return true;
									                        },
									                        success:function(data){
									        		            v_title=data.yearmonthname+'住院量目标完成率'; 
									        		            v_yearmonthname=data.yearmonthname;
									        		            v_orgname=data.orgname; 
									        		            v_percent=data.percent;   
									        		            v_currentPercent=data.currentPercent;   
									        		        	drewChart('dialog-zyl'); //绘画
									                        }
									                    });
														$( "#dialog-zyl").omDialog('open');
													}
												},{
													separtor : true
												}
												]
									});
							
						});
	   /* 绘画 */
	   function drewChart(divId){
		// 第二个参数可以指定前面引入的主题
			var myChart = echarts.init(document.getElementById(divId), '');
				// 指定图表的配置项和数据
				var option = {
					    title : {
					        text: v_title,
					        subtext: '数据来自:中美集团信息部'
					    },
					    tooltip : {
					        trigger: 'axis'
					    },
					    legend: {
					        data:[v_yearmonthname]
					    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: true},
					            magicType: {show: true, type: ['bar']},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    xAxis : [
					        {
					            type : 'value',
					            boundaryGap : [0, 0.01],
					            name : '百分比%'
					        }
					    ],
					    yAxis : [ 
					        {
					            type : 'category',
					            data : v_orgname
					        }
					    ],
					    series : [
					        {
					            name:v_yearmonthname,
					            type:'bar',
					            itemStyle: {
					            	normal: {
					    				label : {show:true,position:'right',formatter:'{c} %'}
					    			}
					        	},
					            barWidth: 22,//固定柱子宽度
					            data:v_percent,
					            markLine : {
					            	data: [
											{
										        name: '日累计完成标准',
										        xAxis: v_currentPercent
										    }
					            	   ]
					            }
					        }
					    ]
					};
				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);            
		  }
	</script>
</body>
</html>