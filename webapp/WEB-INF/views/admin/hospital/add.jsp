<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@include file="../common/tag.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@include file="../common/header.jsp"%>
<%@include file="../common/css.jsp"%>
<%@include file="../common/ke.jsp"%>
<style>
html, body {
	width: 100%;
	height: 100%;
	padding: 0;
	margin: 0;
	background: #f0f0f0;
}
</style>
</head>
<body>
	<div style="width: 800px; height: 650px; overflow: scroll;">
		<form id="myform" method="post"
			action="${pageContext.request.contextPath}/sys/hospital/save">
			<input type="hidden" name="id" value="${hos.id }" />
			<div id="buttonbar"></div>

			<table id="table">

				<tr>
					<th><div align="center">医院编码</div></th>
					<td><input name="hosid" class="text required" title="不能为空" style="width:250px"
						value="${hos.hosid}"/>
					</td>
					<th><div align="center">医院名称</div></th>
					<td><input name="hosname" class="text required" title="不能为空" style="width:250px"
						value="${hos.hosname}"/>
					</td>
				</tr>
				<tr>
					<th><div align="center">地址</div></th>
					<td><input name="address" class="text" title="" style="width:250px"
						value="${hos.address}" />
					</td>
					<th><div align="center">等级</div></th>
					<td><input name="hlevel" class="text" title="" style="width:250px"
						value="${hos.hlevel}"/>
					</td>
				</tr>
				<tr>
					<th><div align="center">邮编</div></th>
					<td><input name="postcode" class="text" title="" style="width:250px"
						value="${hos.postcode}"/>
					</td>
					<th><div align="center">电话</div></th>
					<td><input name="tel" class="text" title="" style="width:250px" value="${hos.tel}"
						/>
					</td>
				</tr>
				<tr>
					<th><div align="center">坐标X</div></th>
					<td><input name="coordinatex" class="text" title="" style="width:250px"
						value="${hos.coordinatex}"/>
					</td>
					<th><div align="center">坐标Y</div></th>
					<td><input name="coordinatey" class="text" title="" style="width:250px"
						value="${hos.coordinatey}"/>
					</td>
				</tr>
				<tr>
					<th><div align="center">省行政编码</div></th>
					<td><input name="provincecode" class="text" title="" style="width:250px"
						value="${hos.provincecode}"/>
					</td>
					<th><div align="center">省名称</div></th>
					<td><input name="province" class="text" title="" style="width:250px"
						value="${hos.province}"/>
					</td>
				</tr>
				<tr>
					<th><div align="center">地区编码</div></th>
					<td><input name="citycode" class="text" title="" style="width:250px"
						value="${hos.citycode}"/>
					</td>
					<th><div align="center">地区</div></th>
					<td><input name="city" class="text" title="" style="width:250px"
						value="${hos.city}"/>
					</td>
				</tr>
				<tr>
					<th><div align="center">医院照片</div></th>
					<td><input id="photourl" name="photourl" class="text" title="" style="width:250px"
						value="${hos.photourl}"/>
						<button type="button" id="upload"
							style="width: 60px; height: 25; font-size: 9px; text-align: center; vertical-align: middle">
							上传</button></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><textarea name="hosbrief" cols="100" rows="8"
							style="width: 770px; height: 400px; visibility: hidden;">${hos.hosbrief }</textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript">
		var msg = "${msg}";

		if (msg.length > 0) {
			art.dialog({
				time : 1,
				content : '${msg}',
				title : '提示'
			});
		}

		$(document)
				.ready(
						function() {

							$('#buttonbar').omButtonbar({
								btns : [ {
									label : "保存",
									id : "button-save",
									disabled : false,
									icons : {
										left : WEB_ROOT + '/img/save.png'
									},
									onClick : function() {
										editor.sync();
										$("#myform").submit();

									}
								}, {
									separtor : false
								}, {
									label : "返回",
									id : "button-close",
									disabled : false,
									icons : {
										left : WEB_ROOT + '/img/search.png'
									},
									onClick : function() {
										art.dialog.close();
									}
								} ]
							});

							KindEditor
									.ready(function(K) {
										window.editor = K
												.create(
														'textarea[name="hosbrief"]',
														{
															cssPath : '${pageContext.request.contextPath}/lib/ke/plugins/code/prettify.css',
															uploadJson : '${pageContext.request.contextPath}/main/ke/uploadjson',
															fileManagerJson : '../jsp/file_manager_json.jsp',
															allowFileManager : false,
															afterCreate : function() {
															}
														});
										prettyPrint();
									});
						});
		$("#upload")
				.click(
						function() {
							art.dialog
									.open(
											'${pageContext.request.contextPath}/sys/hospital/toupload',
											{
												width : 500,
												height : 400,
												lock : true,
												title : "",
												close : function() {
													var data = art.dialog
															.data("imgpath");
													$("#photourl").val(data);

												}
											});

							return false;
						});
	</script>
</body>
</html>