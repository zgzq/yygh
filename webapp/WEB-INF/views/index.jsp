<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>
      <c:if test="${ss==1}">
      	中美医疗集团
      </c:if>
      <c:if test="${ss==0}">
      	中美集团
      </c:if> 
    </title>
  	
	<!-- jquery mobile -->

    <jsp:include page="injs.jsp"/> 
    <meta content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" name="viewport">
    
	<style>
		table{
			border:1px solid #d6d6d6;
			margin:0;
			border-collapse:collapse;
			width:100%;
		} 
		thead th {
			border: 1px solid #d6d6d6;
		    height:30px;
		}
		tbody td{
		    border-left: 1px solid #d6d6d6;
		    border-right: 1px solid #d6d6d6;
			padding:0;
			
		    height:30px;
		}
		/* 隔行换色 */
		tbody tr:nth-child(even) {
		    background: #e9e9e9;
		}
		/* 标题第一行 */
		thead tr:first-child{
			background-color:3388CC;
			color:FFFFFF;
		}
		/* tbody除了第一列的其他列 */
		tbody tr td:not(:first-child){
			text-align:center;
		}
		
	</style>
		
  </head>
  
  <body>
  	 <!-- 页面1 -->
     <div data-role="page" id="page1" data-theme="a">
	  <div data-role="header"  data-position="fixed" data-tap-toggle="false">
		  	<div class="logo">
				<c:if test="${ss==1}">
					<img src="${pageContext.request.contextPath}/image/wxcp/logo.png" alt="">
				</c:if>
			    <c:if test="${ss==0}">
					<img src="${pageContext.request.contextPath}/image/wxcp/logo2.png" alt="">
				</c:if>
			</div> 
	 	</div>
	  <div data-role="content">
	   <!--  医院列表 -->
	   <div class="ui-grid-b">
		   <c:forEach items="${orgs}" var="org" varStatus="status">
				  <c:if test="${(status.index+1)%3==1}">
				   	<div class="ui-block-a">
					   	<a href="${pageContext.request.contextPath}/qyreq/zbinfo?id=${org.id}&orgname=${org.org_name}&userid=${userid}&pdate=${pdate}&ss=${ss}" target="_self" data-role="content">
							<div class="icon_img"><img src="${pageContext.request.contextPath}${org.org_pic}"  height="100%"/></div>
					    </a>
					    <div class="hosname">${org.org_name}</div> 
					</div>
				  </c:if>
		   		  <c:if test="${(status.index+1)%3==2}">
				   	<div class="ui-block-b">
					   	<a href="${pageContext.request.contextPath}/qyreq/zbinfo?id=${org.id}&orgname=${org.org_name}&userid=${userid}&pdate=${pdate}&ss=${ss}" target="_self" data-role="content">
							<div class="icon_img"><img src="${pageContext.request.contextPath}${org.org_pic}"  height="100%"/></div>
					    </a>
					    <div class="hosname">${org.org_name}</div>  
					</div>
				  </c:if> 
				  <c:if test="${(status.index+1)%3==0}">
				   	<div class="ui-block-c">
					   	<a href="${pageContext.request.contextPath}/qyreq/zbinfo?id=${org.id}&orgname=${org.org_name}&userid=${userid}&pdate=${pdate}&ss=${ss}" target="_self" data-role="content">
							<div class="icon_img"><img src="${pageContext.request.contextPath}${org.org_pic}"  height="100%"/></div>
					    </a>
					    <div class="hosname">${org.org_name}</div> 
					</div>
				  </c:if> 
			</c:forEach>
	   </div>
	  </div>
	  <div data-role="footer" data-position="fixed" data-tap-toggle="false">
	  	<!-- 内容中的导航栏 -->
	    <div data-role="navbar" >
	      <ul>
	        <li><a href="#" class="ui-btn-active ui-state-persist" data-icon="grid">列表</a></li>
	        <li><a href="#page2" data-icon="star">图表</a></li>
	        <li><a href="#page3" data-icon="gear">对比</a></li>
	      </ul>
	    </div>
	  	
	    <c:if test="${ss==1}">
	    	<h1>中美医疗集团信息部</h1>
	    </c:if>
	  	<c:if test="${ss==0}">
	    	<h1>中美集团信息部</h1>
	    </c:if>
	  </div>
	
	</div> 
	<!-- 页面2 -->
	<div data-role="page" id="page2" data-theme="a">
	  <div data-role="header"  data-position="fixed" data-tap-toggle="false">
	     	<div class="logo">
				<c:if test="${ss==1}">
					<img src="${pageContext.request.contextPath}/image/wxcp/logo.png" alt="">
				</c:if>
			    <c:if test="${ss==0}">
					<img src="${pageContext.request.contextPath}/image/wxcp/logo2.png" alt="">
				</c:if>
			</div>
	  </div>
	  <div data-role="content">
		   <!--图表列表 -->
		    <ul data-role="listview" data-inset="true">
			    <c:forEach items="${charts }" var="charts">
			      <li>
			      	<a href="/wxcp${charts.url}?orgname=${charts.chartname }&pdate=${pdate}&userid=${userid}&ss=${ss}" target="_self" data-transition="slide">
			  	     	${charts.chartname}
			  	     </a> 
			      </li>
			    </c:forEach>
		    </ul>
	  </div>
	  <div data-role="footer" data-position="fixed" data-tap-toggle="false">
	  	<!-- 内容中的导航栏 -->
	    <div data-role="navbar">
	      <ul>
	        <li><a href="#page1" data-icon="grid" >列表</a></li>
	        <li><a href="#" class="ui-btn-active ui-state-persist" data-icon="star">图表</a></li>
	        <li><a href="#page3" data-icon="gear">对比</a></li>
	      </ul>
	    </div>
	    <c:if test="${ss==1}">
	    	<h1>中美医疗集团信息部</h1>
	    </c:if>
	  	<c:if test="${ss==0}">
	    	<h1>中美集团信息部</h1>
	    </c:if>
	  </div>
	
	</div> 
	
	<!-- 页面3 -->
	<div data-role="page" id="page3" data-theme="a">
	  <div data-role="header"  data-position="fixed" data-tap-toggle="false">
	     	<div class="logo">
				<c:if test="${ss==1}">
					<img src="${pageContext.request.contextPath}/image/wxcp/logo.png" alt="">
				</c:if>
			    <c:if test="${ss==0}">
					<img src="${pageContext.request.contextPath}/image/wxcp/logo2.png" alt="">
				</c:if>
			</div>
	  </div>
	
     <div data-role="content" class="ui-content">
     	<div data-role="collapsible-set">
	     	<c:forEach items="${mapKvmpds }" var="mapKvmpd">
	     		<div data-role="collapsible">
	     			<h3>${mapKvmpd.key}</h3>
					    <table>
					      <thead>
					      	<tr>
					          <th colspan="3" >${mapKvmpd.key}(${pdate})</th>
					        </tr>
					        <tr>
					          <th>指标</th> <th>当日目标值</th> <th>当日实际值</th>
					        </tr>
					      </thead>
					      <tbody>
					      	<c:forEach items="${mapKvmpd.value}" var="kvmp">
						      	<tr>
						          <td>${kvmp.kpiname }</td>
						          <td>${kvmp.refavg }</td>
						          <td>${kvmp.kpivalue }</td>
						        </tr>
					      	</c:forEach>
					      </tbody>
					    </table>
			    		<br>
			    	</div>
			</c:forEach>
		</div>
	  </div>

	  
	  <div data-role="footer" data-position="fixed" data-tap-toggle="false">
	  	<!-- 内容中的导航栏 -->
	    <div data-role="navbar">
	      <ul>
	        <li><a href="#page1" data-icon="grid">列表</a></li>
	        <li><a href="#page2" data-icon="star">图表</a></li>
	        <li><a href="#" class="ui-btn-active ui-state-persist" data-icon="gear">对比</a></li>
	      </ul>
	    </div>
	  	
	    <c:if test="${ss==1}">
	    	<h1>中美医疗集团信息部</h1>
	    </c:if>
	  	<c:if test="${ss==0}">
	    	<h1>中美集团信息部</h1>
	    </c:if>
	  </div>
	
	</div> 
  </body>
</html>
