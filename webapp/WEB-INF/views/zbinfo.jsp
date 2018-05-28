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

  </head>
  
  <body>
	<c:forEach items="${kpis }" var="kpi" varStatus="vs" >
		<c:choose>
			<c:when test="${kpi.code==13||kpi.code==81||kpi.code==14||kpi.code==79}">
			  	<!-- 页面2 -->
				<div data-role="page" id="page2" data-theme="a">
				  <div data-role="header" id="header" data-position="fixed" data-tap-toggle="false">
				    <a href="javascript:history.go(-1)" data-role="button" data-icon="back">返回</a>
				    <h1>${orgname}<br><span style="font-size: small;">${pdate}</span></h1>
				    <a href="${pageContext.request.contextPath}/qyreq/kpiday?pdate=${pdate}&userid=${userid}&ss=${ss}" data-role="button" data-icon="home"  target="_self" >首页</a>
				  </div>
				
				  <div data-role="content">
				    <ul data-role="listview">
				       <c:if test="${kpi.code==13}"><!-- 中美重点科室 -->
					    <c:forEach items="${kpi.getKpivalue() }" var="kv">
					      <li><a href="${pageContext.request.contextPath}/qyreq/zmdept?ks=${kv.kpivalue}&pdate=${kv.pdate}&planid=${kv.planid}&userid=${userid}&ss=${ss}" data-transition="slide">${kv.dictname}</a></li>
					    </c:forEach>
					   </c:if>
					   <c:if test="${kpi.code==81}"><!-- 京美重点科室 -->
					    <c:forEach items="${kpi.getKpivalue() }" var="kv">
					      <li><a href="${pageContext.request.contextPath}/qyreq/jmdept?ks=${kv.kpivalue}&pdate=${kv.pdate}&planid=${kv.planid}&userid=${userid}&ss=${ss}" data-transition="slide">${kv.dictname}</a></li>
					    </c:forEach>
					   </c:if>
					   <c:if test="${kpi.code==14||kpi.code==79}"><!-- 京东誉美血透14 ,常德惠民(血透)79-->
					    <c:forEach items="${kpi.getKpivalue() }" var="kv">
				      		<li><span>${kv.dictname}:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>${kv.kpivalue}</li>
				      	</c:forEach>
					   </c:if>
				    </ul>
				  </div>
				
				  <div data-role="footer" data-position="fixed" data-tap-toggle="false">
				  	<div data-role="navbar" data-iconpos="left" id="footer2">
				      
				    </div>
				  </div>
				</div> 
			</c:when>	
			<c:otherwise>	
				<!-- 页面1 -->
		    	<div data-role="page" id="page1" data-theme="a">
				  <div data-role="header" id="header" data-position="fixed" data-tap-toggle="false">
				    <a href="javascript:history.go(-1)" data-role="button" data-icon="back">返回</a>
				    <h1>${orgname}<br><span style="font-size: small;">${pdate}</span></h1>
				    <a href="${pageContext.request.contextPath}/qyreq/kpiday?pdate=${pdate}&userid=${userid}&ss=${ss}" data-role="button" data-icon="home"  target="_self" >首页</a>
				  </div>
				
				  <div data-role="content">
				    <ul data-role="listview" data-inset="true">
				    	<c:forEach items="${kpi.getKpivalue() }" var="kv">
				      		<li><span>${kv.dictname}:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>${kv.kpivalue}</li>
				      	</c:forEach>
				    </ul>
				  </div>
				
				  <div data-role="footer" data-position="fixed" data-tap-toggle="false" >
				  	<div data-role="navbar" data-iconpos="left" id="footer1">
				      
				    </div>
				  </div>
				</div> 
			</c:otherwise>
		</c:choose>
	</c:forEach>	
	
	<script type="text/javascript">
		/* 创建footer */
	    var kpiscount = "${kpiscount}"; 
	    
    	
	
	    if(kpiscount==1){
	    	var planname10="${plannames[0]}";
	    	var ul = document.createElement('ul');
	    	var li = document.createElement('li');
	    	var a = document.createElement('a');
		    a.setAttribute('href',"#");
		    a.setAttribute('class',"ui-btn-active ui-state-persist");
		    a.setAttribute('data-icon',"grid");
		    a.id = "a1"
		    /* a.innerHTML = "data1"; */
		    a.innerHTML = planname10;
		    li.appendChild(a);
		    ul.appendChild(li);
		    document.getElementById('footer1').appendChild(ul);
		    
		}else if(kpiscount==2){
			/* footer1 */
		    var planname20="${plannames[0]}";
		    var planname21="${plannames[1]}";
			var ul1 = document.createElement('ul');
			var li11 = document.createElement('li');
			var li12 = document.createElement('li');
			var a11 = document.createElement('a');
			var a12 = document.createElement('a');
			
			a11.setAttribute('href',"#");
		    a11.setAttribute('class',"ui-btn-active ui-state-persist");
		    a11.setAttribute('data-icon',"grid");
		    a11.id = "a1"
			a11.innerHTML = planname20;
		    li11.appendChild(a11);
		    a12.setAttribute('href',"#page2");
		    a12.setAttribute('data-icon',"star");
		    a12.id = "a2"
			a12.innerHTML = planname21;
		    li12.appendChild(a12);
		    
		    ul1.appendChild(li11);
		    ul1.appendChild(li12);
			
		    document.getElementById('footer1').appendChild(ul1);
		    /* footer2 */
		    
		    var ul2 = document.createElement('ul');
			var li21 = document.createElement('li');
			var li22 = document.createElement('li');
			var a21 = document.createElement('a');
			var a22 = document.createElement('a');
			
			a21.setAttribute('href',"#page1");
		    a21.setAttribute('data-icon',"grid");
		    a21.id = "a1" 
			a21.innerHTML = planname20;
		    li21.appendChild(a21);
		    a22.setAttribute('href',"#");
		    a22.setAttribute('class',"ui-btn-active ui-state-persist");
		    a22.setAttribute('data-icon',"star");
		    a22.id = "a2"
			a22.innerHTML = planname21;
		    li22.appendChild(a22);
		    
		    ul2.appendChild(li21);
		    ul2.appendChild(li22);
		    document.getElementById('footer2').appendChild(ul2);
		} 
	
	 
	</script>	
  </body>
</html>
