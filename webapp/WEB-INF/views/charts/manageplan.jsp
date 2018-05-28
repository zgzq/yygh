<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"> 
    <title>经营目标进度</title>   
	
    <script src="${pageContext.request.contextPath}/js/echarts.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<!-- 引入 macarons 主题 -->
	 <link href="${pageContext.request.contextPath}/css/wxcp/mui.min.css"rel="stylesheet" />

<script type="text/javascript">
	
	/* $(function(){ */
	$(document).ready(function(){
		/* 设置select默认值,默认当前月 */
		setDefualt();
		/* 页面加载刷新图表 */
		refresh()
		
	    $("#button1").click(function() {
	    	document.getElementById("yearmonthname").value = $("#yearmonth").find("option:selected").text(); 

	    	refresh();
			
	    }); 
   });
	function setDefualt(){
		var oDate = new Date(); //实例一个时间对象；
		var year=oDate.getFullYear();   //获取系统的年；
		var month=(oDate.getMonth()+1)<10?'0'+(oDate.getMonth()+1):(oDate.getMonth()+1);   //获取系统月份，由于月份是从0开始计算，所以要加1
		
		$("#yearmonth").val(year+''+month);
	}
	/* 刷新 */
	function refresh(){
		$('#fm').form('submit',{  
	        url :  "getdata?ss=${ss}", 
	        onSubmit: function(){  
	            //进行表单验证  
      			//如果返回false阻止提交  
		    },  
	        success : function(data) {  
	        	var v_percent_sr;
	            var v_percent_mzl;
	            var v_percent_zyl;
	            var v_orgname;
	            var v_yearmonthname;
	            var v_title;
		        var result=jQuery.parseJSON(data); 

	            v_percent_sr=result.percent_sr; 
	            v_percent_mzl=result.percent_mzl; 
	            v_percent_zyl=result.percent_zyl; 
	            v_orgname=result.orgname;  
	            v_yearmonthname=result.yearmonthname;  
 
	        	drewChart("chart_sr", "收入完成率",v_yearmonthname,v_orgname,v_percent_sr); //绘画
	        	drewChart("chart_mzl", "门诊量完成率",v_yearmonthname,v_orgname,v_percent_mzl); //绘画
	        	drewChart("chart_zyl", "住院量完成率",v_yearmonthname,v_orgname,v_percent_zyl); //绘画 
	        }
	    });
	}
	/* 绘画 */
	function drewChart(divId,v_title,v_yearmonthname,v_orgname,v_percent){
			// 第二个参数可以指定前面引入的主题
				var myChart = echarts.init(document.getElementById(divId), '');
				// 指定图表的配置项和数据
				var option = 
						{
						backgroundColor: '#eee',
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
					            dataView : {show: true, readOnly: true},
					            magicType: {show: true, type: ['bar']},
					            restore : {show: true}
					        }
					    },
					    calculable : true,
					    xAxis : [
						        {
						        	axisLabel:{
									clickable:true,
									show:true,
									formatter:function(val){
									    return val.split("").join("\n");
									}
								},
					            type : 'category',
					            data : v_orgname
					        }
					    ],
					    yAxis : [ 
					        {
					            type : 'value'
					        }
					    ],
					    series : [
					        {
					            name:v_yearmonthname,
					            type:'bar',
					            itemStyle: {
					            	normal: {
					    				label : {show:true,position:'top',formatter:'{c} %'}
					    			}
					        	},
					            barWidth: 20,//固定柱子宽度
					            data:v_percent,
					            markLine : {
					            	data: [
											{
										        name: '目标基准线',
										        yAxis: 100
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
</head>
<body>
<div id="top"><!--频道导航 开始-->
		<div style="margin-bottom:55px;">
		 <div id="toolbar">
		  <div class="fixed-pink">
		   <a class="ui-title" id="popmenus">${orgname}</a>
		   <a class="ui-btn-back" href="javascript:history.go(-1)"></a>
		   <a class="ui-btn-home" href="${pageContext.request.contextPath}/qyreq/kpiday?pdate=${pdate}&userid=${userid}&ss=${ss}"></a>
		  </div>
		 </div>
		 <div id="overlay"></div>
		</div>
	</div>
	<!-- 选择年份月份 -->
	<div>
		<form id="fm" method="post">
			<input type="hidden" name="yearmonthname" id="yearmonthname"/> 
			<input type="hidden" name="userid" value="${userid }"/> 
			<label>时间:</label>	
			<select name="yearmonth" id="yearmonth" >
				<option value="201701">2017年1月</option>
				<option value="201702">2017年2月</option>
				<option value="201703">2017年3月</option>
				<option value="201704">2017年4月</option>
				<option value="201705">2017年5月</option>
				<option value="201706">2017年6月</option>
				<option value="201707">2017年7月</option>
				<option value="201708">2017年8月</option>
				<option value="201709">2017年9月</option>
				<option value="201710">2017年10月</option>
				<option value="201711">2017年11月</option>
				<option value="201712">2017年12月</option>
			</select>
			<input id="button1" type="button" value="查询" />					                   
		</form>
	</div>
			
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<!-- <div id="chart" style="height:450px;border:1px solid #dddddd;margin:10px auto;top:30px;"/> -->
	<div id="chart_sr" style="height:200px;border:0px solid #999999;margin:10px auto;overflow;left;"></div>
	<div id="chart_mzl" style="height:200px;border:0px solid #000ddd;margin:10px auto;overflow;left;"></div>
	<div id="chart_zyl" style="height:200px;border:0px solid #ddd000;margin:10px auto;overflow;left;"></div>
</body>
</html>