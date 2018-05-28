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
								height: 500,
								width: 900,
								modal: true
							});
							$( "#dialog-mzl").omDialog({
								autoOpen: false,
								height: 500,
								width: 900,
								modal: true
							});
							$( "#dialog-zyl").omDialog({
								autoOpen: false,
								height: 500,
								width: 900,
								modal: true
							});
							/* 初始化grid */
							$("#center-panel").height($('body').height());
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
																{header:"京东中美医院" ,  colspan:3 }	,
																{header:"京东誉美医院" ,  colspan:3 }	,
																{header:"中美东城医院" ,  colspan:3 }	,
																{header:"河南誉美医院" ,  colspan:3 }	,
																{header:"株洲同济医院" ,  colspan:3 }	,
																{header:"" ,  colspan:1 }	,
																{header:"河南整形医院" ,  colspan:3 }	,
															],
															[
															 {header : '顺序', name : 'seq', width : 20, align : 'center'},	
															 {header : '指标名称', name : 'kpiname', width : 100},	
															 {header : '目标值', name : 'orgid1ref', width : 45, align : 'center'},	
															 {header : '实际值', name : 'orgid1val', width : 45, align : 'center',
															 	renderer : function(colValue, rowData, rowIndex) {
															          if (colValue < rowData['orgid1ref'] ) { 
															              return '<span style="color:red;">' + colValue + '</span>';
															          }else{
															         	 return '<span>' + colValue + '</span>';
															          }
															          return colValue;
															      }	
															 },
															 {header : '进度', name : 'orgid1per', width : 45, align : 'center',
																 	renderer : function(colValue, rowData, rowIndex) {
																          if (rowData['orgid1val'] < rowData['orgid1ref'] ) { 
																              return '<span style="color:red;">' + colValue + '</span>';
																          }else{
																         	 return '<span>' + colValue + '</span>';
																          }
																          return colValue;
																      }	
																 },	
															 
															 {header : '目标值', name : 'orgid2ref', width : 45, align : 'center'},	
															 {header : '实际值', name : 'orgid2val', width : 45, align : 'center',
															 	renderer : function(colValue, rowData, rowIndex) {
															          if (colValue < rowData['orgid2ref'] ) { 
															              return '<span style="color:red;">' + colValue + '</span>';
															          }else{
															         	 return '<span>' + colValue + '</span>';
															          }
															          return colValue;
															      }	
															 },	
															 {header : '进度', name : 'orgid2per', width : 45, align : 'center',
																 	renderer : function(colValue, rowData, rowIndex) {
																          if (rowData['orgid2val'] < rowData['orgid2ref'] ) { 
																              return '<span style="color:red;">' + colValue + '</span>';
																          }else{
																         	 return '<span>' + colValue + '</span>';
																          }
																          return colValue;
																      }	
																 },	
															 
															 {header : '目标值', name : 'orgid3ref', width : 45, align : 'center'},	
															 {header : '实际值', name : 'orgid3val', width : 45, align : 'center',
															 	renderer : function(colValue, rowData, rowIndex) {
															          if (colValue < rowData['orgid3ref'] ) { 
															              return '<span style="color:red;">' + colValue + '</span>';
															          }else{
															         	 return '<span>' + colValue + '</span>';
															          }
															          return colValue;
															      }	
															 },	
															 {header : '进度', name : 'orgid3per', width : 45, align : 'center',
																 	renderer : function(colValue, rowData, rowIndex) {
																          if (rowData['orgid3val'] < rowData['orgid3ref'] ) { 
																              return '<span style="color:red;">' + colValue + '</span>';
																          }else{
																         	 return '<span>' + colValue + '</span>';
																          }
																          return colValue;
																      }	
																 },	
															 
															 {header : '目标值', name : 'orgid5ref', width : 45, align : 'center'},	
															 {header : '实际值', name : 'orgid5val', width : 45, align : 'center',
															 	renderer : function(colValue, rowData, rowIndex) {
															          if (colValue < rowData['orgid5ref'] ) { 
															              return '<span style="color:red;">' + colValue + '</span>';
															          }else{
															         	 return '<span>' + colValue + '</span>';
															          }
															          return colValue;
															      }	
															 },	
															 {header : '进度', name : 'orgid5per', width : 45, align : 'center',
																 	renderer : function(colValue, rowData, rowIndex) {
																          if (rowData['orgid5val'] < rowData['orgid5ref'] ) { 
																              return '<span style="color:red;">' + colValue + '</span>';
																          }else{
																         	 return '<span>' + colValue + '</span>';
																          }
																          return colValue;
																      }	
																 },	
															 
															 {header : '目标值', name : 'orgid7ref', width : 45, align : 'center'},	
															 {header : '实际值', name : 'orgid7val', width : 45, align : 'center',
															 	renderer : function(colValue, rowData, rowIndex) {
															          if (colValue < rowData['orgid7ref'] ) { 
															              return '<span style="color:red;">' + colValue + '</span>';
															          }else{
															         	 return '<span>' + colValue + '</span>';
															          }
															          return colValue;
															      }	
															 },	
															 {header : '进度', name : 'orgid7per', width : 45, align : 'center',
																 	renderer : function(colValue, rowData, rowIndex) {
																          if (rowData['orgid7val'] < rowData['orgid7ref'] ) { 
																              return '<span style="color:red;">' + colValue + '</span>';
																          }else{
																         	 return '<span>' + colValue + '</span>';
																          }
																          return colValue;
																      }	
																 },	
															 
															 {header : '指标名称', name : 'kpiname4', width : 100},	
															 {header : '目标值', name : 'orgid4ref', width : 45, align : 'center'},	
															 {header : '实际值', name : 'orgid4val', width : 45, align : 'center',
															 	renderer : function(colValue, rowData, rowIndex) {
															          if (colValue < rowData['orgid4ref'] ) { 
															              return '<span style="color:red;">' + colValue + '</span>';
															          }else{
															         	 return '<span>' + colValue + '</span>';
															          }
															          return colValue;
															      }	
															 },	
															 {header : '进度', name : 'orgid4per', width : 45, align : 'center',
																 	renderer : function(colValue, rowData, rowIndex) {
																          if (rowData['orgid4val'] < rowData['orgid4ref'] ) { 
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
														$('#grid').omGrid("setData",WEB_ROOT + "/kpimp/hz5/gridData");
														
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
														document.getElementById("yearmonthname").value = $("#yearmonth").find("option:selected").text(); 
														document.getElementById("kpiname").value = '收入(万元)'; 
														//以ajax方式刷新图表
									                    $("#queryform").omAjaxSubmit({
									                        url :'getHz5',
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
														document.getElementById("yearmonthname").value = $("#yearmonth").find("option:selected").text(); 
														document.getElementById("kpiname").value = '门诊量(人次)'; 
														//以ajax方式刷新图表
									                    $("#queryform").omAjaxSubmit({
									                        url :'getHz5',
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
														document.getElementById("yearmonthname").value = $("#yearmonth").find("option:selected").text(); 
														document.getElementById("kpiname").value = '住院量(人次)'; 
														//以ajax方式刷新图表
									                    $("#queryform").omAjaxSubmit({
									                        url :'getHz5',
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
					var option = 
							{
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
					}
					// 使用刚指定的配置项和数据显示图表。
					myChart.setOption(option);            
			  }
	</script>
</body>
</html>