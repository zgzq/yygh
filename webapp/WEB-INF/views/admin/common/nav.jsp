<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/my.tld" prefix="my" %>
<div class="hidden_menu">

	<ul>

		<li><a href="${pageContext.request.contextPath}/index"> <img
				src="${pageContext.request.contextPath}/image/index_img.png" /> <span>${my:i18n("msg.nav_sy")}</span>

		</a></li>
		<li><a href="${pageContext.request.contextPath}/yyjs"> <img
				src="${pageContext.request.contextPath}/image/li1_an.png" /> <span>${my:i18n("msg.nav_yyjs")}</span>
		</a></li>



		<li><a href="${pageContext.request.contextPath}/ksts"> <img
				src="${pageContext.request.contextPath}/image/li2_an.png" /> <span>${my:i18n("msg.nav_ksts")}</span>
		</a></li>


		<li><a href="${pageContext.request.contextPath}/zjjs"> <img
				src="${pageContext.request.contextPath}/image/li3_an.png" /> <span>${my:i18n("msg.nav_zjjs")}</span>
		</a></li>
		

		<li><a href="${pageContext.request.contextPath}/yygh"> <img
				src="${pageContext.request.contextPath}/image/li5_an.png" /> <span>${my:i18n("msg.nav_yygh")}</span>
		</a></li>


<!-- 		<li><a href="webpage/wcontent!tongzhiࠩd=27.html"> <img -->
<%-- 				src="${pageContext.request.contextPath}/image/li6_an.png" /> <span>通知公告</span> --%>
<!-- 		</a></li> -->



	</ul>
</div>