<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"> 
    <title>门诊量(同比)</title>   
	
    <script src="${pageContext.request.contextPath}/js/echarts.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<!-- 引入 macarons 主题 -->
	<script src="${pageContext.request.contextPath}/js/macarons.js"></script>
	    <link href="${pageContext.request.contextPath}/css/wxcp/mui.min.css"rel="stylesheet" />
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
	var v_date;
	var v_data1;
	var v_data2;
	var v_orgname;
	$(function(){
	    $("#button1").click(function() {
			$('#fm').form('submit',{  
		        url : 'getMzlTb', 
		        onSubmit: function(){  
		            //进行表单验证  
	      			//如果返回false阻止提交  
			    }, 
		        success : function(data) {  
			        var result = jQuery.parseJSON(data); 
		            v_date=result.date;
		            v_data1=result.data1; 
		            v_data2=result.data2; 
		            v_orgname=result.orgname; 
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
				backgroundColor: '#eee',
			    title : {
			        text: v_orgname ,
			        subtext: '门诊量(人次)'
			    },
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['2015年','2016年']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			            /* data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'] */
			            data : v_date
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'2015年',
			            type:'bar',
			            /* data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3], */
			            data : v_data1
			        },
			        {
			            name:'2016年',
			            type:'bar',
			            /* data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3], */
			            data : v_data2
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
		<form id="fm" method="post">
			<input type="hidden" name="orgname" id="orgname"/>
			<label>时间:</label>	
			<select name="dt" id="dt" >
				<!-- <option value="">请选择</option> -->
				<option value="年">年</option>
				<option value="季">季</option>
				<option value="月">月</option>
			</select>
			<label>机构:</label>	
			<select name="orgid" id="orgid" onchange="showMsg(this)">
				<!-- <option value="">请选择</option> -->
					<option value="3">中美东城医院</option>
					<option value="5">河南誉美医院</option>
					<!-- <option value="7">株洲同济医院</option> -->
					<!-- <option value="9">常德惠民医院</option> -->
				<%-- <c:forEach var="orgs" items="${orgs }" >
					<option value=${orgs.id }>${orgs.org_name }</option>
				</c:forEach> --%>
			</select>
			
			<input id="button1" type="button" value="查询" />					                   
		</form>
	</div>
			
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="chart" style="height:450px;border:1px solid #dddddd;margin:10px auto;top:30px;"/>
</body>
</html>