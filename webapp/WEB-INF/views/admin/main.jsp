<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags"  prefix="tag"%>
<%@ taglib uri="/WEB-INF/tlds/my.tld" prefix="my" %>
<%@include file="common/tag.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>中美集团数据中心</title>
        <%@include file="common/header.jsp" %>
        <link href="${pageContext.request.contextPath}/css/portal.css" rel="stylesheet" type="text/css"/>
        <script>
        <tag:menu></tag:menu>
        </script>
		<script src="${pageContext.request.contextPath}/js/passwordstrength.js"></script>
		<script src="${pageContext.request.contextPath}/js/portal.js"></script>
		<script src="${pageContext.request.contextPath}/js/pngFix.js"></script>
        <script>
            $(document).ready(function(){
            	
            	//$("#rmdt-logo").pngFix();
            	
                	portal.layout();
					portal.loadtree('workSpace');
					
					$(window).resize(function(){
						//window.location.reload();
						//portal.layout();
					});
					
					 
					var dialog = $("#dialog-form").omDialog({
						width : 440,
						autoOpen : false,
						modal : true,
						resizable : true,
						buttons :[{
							text : msg.submit, 
							click:function() {
								submitDialog();
								return false; //阻止form的默认提交动作
							}
						},{
							text:msg.cancel,
							click: function() {
								$("#dialog-form").omDialog("close");//关闭dialog
							}
						}] 
					});
					
					$("#changepassword").click(function() {
						validator.resetForm();
						dialog.omDialog("option", "title", msg.changepassword);
						dialog.omDialog("open");//显示dialog
					});

					var validator = $('#myform').validate({
						focusInvalid: true, 
						 onkeyup: true,
						rules : {
							oldpassword : "required",
							newpassword : "required",
							newpassword2 : {
								required : true,
								equalTo : "#newpassword"
							}
						},
						messages : {
							oldpassword : {
								required : "请输入旧密码"
							},

							newpassword : {
								required : "请输入新密码"
							},

							newpassword2 : {
								required : "确认密码不能为空",
								equalTo : "兩次输入的新密码必須一致"
							}
						}
					});
					
					var submitDialog = function() {
						if (validator.form()) {
							var submitData = $("#myform").serializeObject();
							console.log(submitData);
							var url = "${pageContext.request.contextPath}/sys/modifypassword";
							$.post(url, submitData, function(data) {
								if(!checkPsw()){
									$("#info").html("建议密码采用字母和数字组合，并且长度大于6！");
									return;
								}
								if (!data.ok) {
									$.omMessageTip.show({
										title : "操作失败",
										content : data.msg,
										timeout : 1500
									});
									return;
								} else {
									$.omMessageTip.show({
										title : "操作成功",
										content : "密码修改成功",
										timeout : 1500
									});
								}
								$("#dialog-form").omDialog("close");
							}, 'json');
						}
					};
					
					/* 登录人密码有效性检验 */
					$.post('${pageContext.request.contextPath}/admin/validpassword',
						  	' ',
							function(data){
								if(!data.ok){
									//弹出修改密码框
									validator.resetForm();
									dialog.omDialog("option", "title", "您的密码过于简单，请修改！");
									dialog.omDialog("open");//显示dialog
									//
									
								}
							},
							'json');
				
					
            });
			
            function checkPsw(){
            	var middle=$("#middle").text();
            	var strength=$("#strength").text();
            	
            	if(middle=='' && strength==''){
            		return false;
            	}else{
            		return true;
            	}
            }
			function AuthPasswd() {
				var string=$("#newpassword").val();
				var modes = 0;
			    //正则表达式验证符合要求的
			    if (string.length < 6) { 
			    	noticeAssign(modes);
			    	return
			    };
			    if (/\d/.test(string)) {modes++}; //数字
			    if (/[a-z]/.test(string)) {modes++}; //小写
			    if (/[A-Z]/.test(string)) {modes++}; //大写  
			    if (/\W/.test(string)) {modes++}; //特殊字符
			    noticeAssign(modes);
			}
			 
			function noticeAssign(num) {
			    if(num == 3) {
			        $('#weak').css({backgroundColor:'#dd0000'});
			        $('#middle').css({backgroundColor:'#ffcc33'});
			        $('#strength').css({backgroundColor:'#009900'});
			        $('#weak').html('');
			        $('#middle').html('');
			        $('#strength').html('强');
			    }else if(num == 2){
			        $('#weak').css({backgroundColor:'#dd0000'});
			        $('#middle').css({backgroundColor:'#ffcc33'});
			        $('#strength').css({backgroundColor:''});
			        $('#weak').html('');
			        $('#middle').html('中');
			        $('#strength').html('');
			    }else if(num ==1) {
			        $('#weak').css({backgroundColor:'#dd0000'});
			        $('#middle').css({backgroundColor:''});
			        $('#strength').css({backgroundColor:''});
			        $('#weak').html('弱');
			        $('#middle').html('');
			        $('#strength').html('');
			    }else{
			        $('#weak').css({backgroundColor:''});
			        $('#middle').css({backgroundColor:''});
			        $('#strength').css({backgroundColor:''});
			        $('#weak').html('太短');
			        $('#middle').html('');
			        $('#strength').html('');
			    }
			}
</script>
    </head>
    <body >
    <div id="rmdt-header">
        <div id="rmdt-logo" style="margin-top: 10px" >
<%--               <a href="#"><img src="${pageContext.request.contextPath}/img/logo.jpg" alt="rmdt" width="125" height="35"/></a>  --%>
       <h1>中美集团数据中心</h1> 
        </div>
        <div id="rmdt-userStatus" style="margin-top: 10px">
         
            登录人： <strong>${user.userXm }</strong>
             - <a href="#" id="changepassword">${my:i18n("CHANGE_PASSWORD")}</a> 
              - <a href="${pageContext.request.contextPath}/logout">退出</a>
        </div>
    </div>
        <div id="Main">
            <div id="center-panel">
                <div id="center-tab">
                <ul>
                </ul>
                </div>
            </div>
            <div id="west-panel">
                <ul id="navTree">
                </ul>
            </div>
        </div>
        
        
        <div id="dialog-form">
		<form id="myform">
			<table id="table-psw" >
				<tr>
					<td>旧密码：</td>
					<td><input type="password" name="oldpassword" id="oldpassword"/> </td>
				</tr>
				<tr>
					<td>新密码：</td>
					<td><input type="password" id="newpassword" name="newpassword"  onKeyUp="javascript:AuthPasswd();"/></td> 
				</tr>
				<tr>
					<td><div><span id="strengthname">密码强度：</span></div></td>
					<td>
					  <div id="strengt-span">
				      	<div id="weak" ></div>
				      	<div id="middle"></div>
				      	<div id="strength"></div>
				      </div>
				    </td>
				</tr>
				<tr>
					<td>密码确认：</td>
					<td><input type="password"  name="newpassword2" id="newpassword2"  /></td>
				</tr>
			</table> 
		</form>
		<div id="info"></div>
	</div>
    </body>
</html>
