<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"> 
    <title>中美重点科室</title>   
	
    <script src="${pageContext.request.contextPath}/js/echarts.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<!-- 引入 macarons 主题 -->
	<script src="${pageContext.request.contextPath}/js/macarons.js"></script>
	<link href="${pageContext.request.contextPath}/css/wxcp/mui.min.css"rel="stylesheet" />
<script language="JavaScript">   
function yearmonthdaystart(){   
	MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];   
	
	//先给年下拉框赋内容   
	var y  = new Date().getFullYear();  
	for (var i = (y-3); i < (y+1); i++) //以今年为准，前30年，后30年   
		   document.reg_testdate.year.options.add(new Option(" "+ i +" 年", i));   
	
	//赋月份的下拉框   
	for (var i = 1; i < 13; i++)   
		   document.reg_testdate.month.options.add(new Option(" " + i + " 月", i));   
	
	document.reg_testdate.year.value = y;   
	document.reg_testdate.month.value = new Date().getMonth() + 1;   
	var n = MonHead[new Date().getMonth()];   
	if (new Date().getMonth() ==1 && IsPinYear(yearvalue)) n++;   
		writeDay(n); //赋日期下拉框Author:meizz   
	document.reg_testdate.day.value = new Date().getDate();   
}   
//-------
if(document.attachEvent)   
	window.attachEvent("onload", yearmonthdaystart);   
else   
	window.addEventListener('load', yearmonthdaystart, false);   
//-------
function yearday(str) //年发生变化时日期发生变化(主要是判断闰平年)   
{   
	var monthvalue = document.reg_testdate.month.options[document.reg_testdate.month.selectedIndex].value;   
	if (monthvalue == ""){ var e = document.reg_testdate.day; optionsClear(e); return;}   
	var n = MonHead[monthvalue - 1];   
	if (monthvalue ==2 && IsPinYear(str)) n++;   
	writeDay(n)   
}   
function monthday(str)   //月发生变化时日期联动   
{   
	var yearvalue = document.reg_testdate.year.options[document.reg_testdate.year.selectedIndex].value;   
	if (yearvalue == ""){ var e = document.reg_testdate.day; optionsClear(e); return;}   
	var n = MonHead[str - 1];   
	if (str ==2 && IsPinYear(yearvalue)) n++;   
	writeDay(n)   
}   
function writeDay(n)   //据条件写日期的下拉框   
{   
	var e = document.reg_testdate.day; 
	optionsClear(e);   
	for (var i=1; i<(n+1); i++)   
	e.options.add(new Option(" "+ i + " 日", i));   
}   
function IsPinYear(year)//判断是否闰平年   
{
	return(0 == year%4 && (year%100 !=0 || year%400 == 0));
}   
function optionsClear(e)   
{   
	e.options.length = 0;   
}   
</script>	
<script type="text/javascript">	
function getSelectedText(id){  
	var obj=$(id);  
	for(i=0;i<obj.length;i++){  
	   if(obj[i].selected==true){  
	    return obj[i].innerText;      //关键是通过option对象的innerText属性获取到选项文本  
	   }  
	}  
}  
//获取下拉列表选中项的值  
function getSelectedValue(id){  
	var obj=$(id);  
	return obj.value;      //如此简单，直接用其对象的value属性便可获取到  
} 	
function showMsg(obj) {
    var opt = obj.options[obj.selectedIndex]
    //alert("The option you select is:"+opt.text+"("+opt.value+")");
    //$("#orgname").val=opt.text;
    document.getElementById("orgname").value = opt.text;
 }
</script>
<script type="text/javascript">
	var v_data1;
	var v_data2;
	var v_data3;
	$(function(){
	    $("#button1").click(function() {
	    	
			$('#fm').form('submit',{  
		        url : 'getZmdept', 
		        onSubmit: function(){  
		            //进行表单验证  
	      			//如果返回false阻止提交  
			    }, 
		        success : function(data) {  
		        	
		            var result = jQuery.parseJSON(data); 
		            v_data1=result.data1; 
		            v_data2=result.data2; 
		            v_data3=result.data3;  
		        	drewChart(); //绘画
		        }
		    });
	    });
   });
   function drewChart(){
		// 第二个参数可以指定前面引入的主题
		var myChart = echarts.init(document.getElementById('chart'), 'macarons');
		// 指定图表的配置项和数据
		var option = {
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        /* data:['直达','营销广告','搜索引擎','邮件营销','联盟广告','视频广告','百度','谷歌','必应','其他'] */
		    	data : v_data1
		    },
		    series: [
		        {
		            name:'收入',
		            type:'pie',
		            selectedMode: 'single',
		            radius: [0, '30%'],

		            label: {
		                normal: {
		                    position: 'inner'
		                }
		            },
		            labelLine: {
		                normal: {
		                    show: false
		                }
		            },
		            /* data:[
		                {value:335, name:'直达'},
		                {value:679, name:'营销广告'},
		                {value:1548, name:'搜索引擎'}
		            ] */
			    	data : v_data2
		        },
		        {
		            name:'门诊量',
		            type:'pie',
		            radius: ['40%', '55%'],

		            /* data:[
		                {value:335, name:'直达11'},
		                {value:310, name:'邮件营销'},
		                {value:234, name:'联盟广告'},
		                {value:135, name:'视频广告'},
		                {value:1048, name:'百度'},
		                {value:251, name:'谷歌'},
		                {value:147, name:'必应'},
		                {value:102, name:'其他'}
		            ] */
		    		data : v_data3
		        }
		    ]
		};
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
<form name="reg_testdate" id="fm" method="post">
	<select name="year" onChange="yearday(this.value)"></select>
	<select name="month" onChange="monthday(this.value)"></select>
	<select name="day"></select>
    <input id="button1" type="button" value="查询" />			
</form>

	</div>
			
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="chart" style="height:450px;border:1px solid #dddddd;margin:10px auto;top:30px;"/>
</body>
</html>