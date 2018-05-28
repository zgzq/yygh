<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<head>
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"> 
    <title>日收入</title>   
	
    <script src="${pageContext.request.contextPath}/js/echarts.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<!-- 引入 macarons 主题 -->
	<script src="${pageContext.request.contextPath}/js/macarons.js"></script>
	
	<!--       -->
    <script src="${pageContext.request.contextPath}/js/wxcp/mobiscroll.2.13.2.js"></script>
    
    <link href="${pageContext.request.contextPath}/css/wxcp/mobiscroll.2.13.2.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/wxcp/mui.min.css"rel="stylesheet" />


    <script type="text/javascript">
        $(function () {
        	var obj=$('#yearmonth')
        	setYMD(obj);
        });
        

        
        //日期不能空
        function checkNull(){
    	    var keyVal= $("#yearmonth").val();  
    		if(keyVal.length==0){  
    		    alert("请输入日期"); 
    		    return false;
    		}   else{
    			return true;
    		}
    	}  
    </script>
<!-- 	 -->


<script type="text/javascript">
	var v_day;
	var v_data1;
	var v_orgname;
	$(function(){
	    $("#button1").click(function() {
	    	
	        document.getElementById("orgname").value = $("#orgid").find("option:selected").text();
	    	$('#fm').form('submit',{ 
		        url : 'getSr', 
		        onSubmit: function(){  
		            //进行表单验证  
	      			//如果返回false阻止提交  
	      			return checkNull();
			    }, 
		        success : function(data) {  
			        var result = jQuery.parseJSON(data); 
		            v_day=result.day;
		            v_data1=result.data1; 
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
			        text: v_orgname+'当月',
			        subtext: '日收入(万元)'
			    }, 
			    tooltip : {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['日收入对比']
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,
			    xAxis : [
			        {
			            type : 'category',
			             //data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'] 
			            data : v_day
			        }
			    ],
			    yAxis : [
			        {
			            type : 'value'
			        }
			    ],
			    series : [
			        {
			            name:'日收入(万元)',
			            type:'line',
			            areaStyle: {normal: {}},
			            //data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3], 
			            data:v_data1,
			            markPoint : {
			                data : [
			                    {type : 'max',name: '最大值' },
			                    {type : 'min', name: '最小值'}
			                ]
			            },
			            markLine : {
			                data : [
			                    {type : 'average', name: '平均值'}
			                ]
			            }
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
	
	<!-- 表单 -->
	<div>
		<form id="fm" method="post">
		       <div class="settings" style="width:42%; float:left;">
					<label for="yearmonth" style="width:35px;; float:left; height:30px; line-height:24px;">日期:</label>
					<input id="yearmonth" name="yearmonth" placeholder="请选择年月" style=" width:70%;float:left;"/>  
		        </div>
		        
		        <div data-role="fieldcontain" class="demo-select" style="width:42%;float:left;">
	            <label for="orgid" style="width:35px;; float:left;height:30px; line-height:24px;">机构:</label>
	            <select name="orgid"  id="orgid" class="demo-test-select" data-role="none" >
					<!-- <option value="1">京东中美医院</option>
					<option value="2">京东誉美医院</option>
					<option value="3">中美东城医院</option>
					<option value="4">河南整形医院</option>
					<option value="5">河南誉美医院</option>
					<option value="6">成都誉美医院</option>
					<option value="7">株洲同济医院</option>
					<option value="8">郑州京美医院</option>
					<option value="9">常德惠民医院</option> -->
					<c:forEach var="orgs" items="${orgs }" >
						<option value=${orgs.id }>${orgs.org_name }</option>
					</c:forEach> 
	            </select>
	            <input type="hidden" name="orgname" id="orgname"/> 
	        </div>
	        <div>
	        <input type="button" id="button1" value="查询" data-inline="true" class="ui-btn-right" style=" width:16%; box-shadow: 0 1px 3px rgba(0,0,0,.2);border-radius:10% ; height:28px; line-height:15px;cursor: pointer; float:left;"/>
	       </div> 
	    </form>
    </div>
			
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="chart" style="height:450px;border:1px solid #dddddd;margin:10px auto;top:30px;"></div>
</body>
</html>