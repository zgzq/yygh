<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
</head>
<body style="">
	<form action="${pageContext.request.contextPath}/doupload"
		method="post" id="uploadimg" enctype="multipart/form-data">

		<p id="dis">
			<input id="file" type="file" name="file"></input></a>
		</p>
 
<br/>
		<p >
			<input type="button"  value="上传" onclick="clickcommit()"/>
		</p>

	</form>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.min.js">
		
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/artDialog.source.js?skin=blue"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/plugins/iframeTools.source.js"></script>


<script>

$(function(){
		
	var imgpath='${imgpath}';
	if(imgpath.length>0){
		art.dialog.data("imgpath",imgpath);
		art.dialog.close();
	}
	
	
	
});
function clickcommit(){
	var file = $("#file").val();
	if(file==''){
		alert("图片不能空!");
		$("#file").focus();	
		return;	 
	}
	$("#uploadimg").submit();
}


</script>

</body>

</html>
