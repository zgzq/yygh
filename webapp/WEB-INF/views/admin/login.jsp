<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>登录 - 中美集团数据中心</TITLE>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>
<LINK rel=stylesheet type=text/css
	href="${pageContext.request.contextPath}/img/common.css" media=screen>
<META name=GENERATOR content="MSHTML 8.00.7600.16853">
</HEAD>
<BODY id=loginFrame>
	<DIV id=header>
		<div id="title">
			<h1>中美集团数据中心</h1>
		</div>

	</DIV>
	<DIV id=loginBox>
		<DIV id=loginBoxHeader></DIV>
		<DIV id=loginBoxBody>
			<UL class=floatLeft>
				<LI>
					<H4>请用您的账号登录</H4>
				</LI>
				<form id="login" method="post"
					action="${pageContext.request.contextPath}/dologin">
					<LI>
						<P>用户名:</P>
						<INPUT id=username class=textInput maxLength=150 size=30 type=text
						name=username>
					</LI>
					<LI>
						<P>密码:</P>
						<INPUT id=password class=textInput maxLength=80 size=30
						type=password name=password> <A class=highlight href="#"
						target=_blank></A>
					</LI>
					<LI class=highlight><INPUT id=loginBtn onclick=this.blur();
						value=登录 type=submit> <!--   <A id=regBtn href="#" target=_blank>忘记密码？</A> -->
					</LI>
					<LI></LI>
				</form>
			</UL>

			<DIV class=floatRight>
				系统管理后台登录</br>
				推荐使用360浏览器极速模式</br>
			</DIV>

			<BR clear=all>
		</DIV>
		<DIV id=loginBoxFooter></DIV>
	</DIV>
	<DIV id=footer>@CopyRight 2016  中美集团</DIV>

</BODY>
</HTML>
