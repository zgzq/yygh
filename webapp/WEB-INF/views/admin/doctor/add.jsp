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
			action="${pageContext.request.contextPath}/sys/doctor/save">
			<input type="hidden" name="id" value="${doc.id }" />
			<div id="buttonbar"></div>

			<table id="table">

				<tr>
					<th><div align="center">医生编码</div></th>
					<td><input name=doccode class="text required" title="不能为空"
						style="width: 250px" value="${doc.doccode}" /></td>
					<th><div align="center">医生名称</div></th>
					<td><input name="docname" class="text required" title="不能为空"
						style="width: 250px" value="${doc.docname}" /></td>
				</tr>
				<tr>
					<th><div align="center">医生照片</div></th>
					<td><input id="url" name="url" class="text" title=""
						style="width: 250px" value="${doc.url}" />
						<button type="button" id="upload"
							style="width: 60px; height: 25; font-size: 9px; text-align: center; vertical-align: middle">
							上传</button></td>
					<th><div align="center">所属科室</div></th>
					<td><input id="branchid" name="branchid"
						value="${doc.branchid}" style="width: 250px"></input>
				</tr>
				<tr>
					<th><div align="center">医生专长</div></th>
					<td><input name="spec" class="text" title=""
						style="width: 250px" value="${doc.spec}" /></td>
					<th><div align="center">备注</div></th>
					<td><input name="remark" class="text" title=""
						style="width: 250px" value="${doc.remark}" /></td>
				</tr>
				<tr>
					<th><div align="center">医生级别</div></th>
					<td><input id="levelid" name="levelid" class="text" title=""
						style="width: 250px" value="${doc.levelid}" /></td>
					<th><div align="center">医生性别</div></th>
					<td><input id="sexid" name="sexid" class="text" title=""
						style="width: 250px" value="${doc.sexid}" /></td>
				</tr>
			</table>
			<table id=table2>
				<tr>
					<td><textarea name="intro" cols="100" rows="8"
							style="width: 770px; height: 480px; visibility: hidden;">${doc.intro }</textarea>
					</td>
				</tr>
			</table>

			<script>
				$(document)
						.ready(
								function() {
									<tag:branch editable="false"></tag:branch>
									<tag:combooption dictcode="SEX" editable="false"></tag:combooption>;
									<tag:combooption dictcode="DOCLEVEL" editable="false"></tag:combooption>;
									$('#branchid').omCombo({
										dataSource : BRANCHOptions.dataSource,
										editable : true,
									});

									$('#sexid').omCombo({
										dataSource : SEXOptions.dataSource,
										editable : true,
									});
									$('#levelid')
											.omCombo(
													{
														dataSource : DOCLEVELOptions.dataSource,
														editable : true,
													});
								});
			</script>
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

							var branchid = '${doc.branchid}';
							$('#branchid').omCombo("value", branchid);
							var levelid = '${doc.levelid}';
							$('#levelid').omCombo("value", levelid);
							var sex = '${doc.sexid}';
							$('#sexid').omCombo("value", sex);

							KindEditor
									.ready(function(K) {
										window.editor = K
												.create(
														'textarea[name="intro"]',
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
											'${pageContext.request.contextPath}/sys/doctor/toupload',
											{
												width : 500,
												height : 400,
												lock : true,
												title : "",
												close : function() {
													var data = art.dialog
															.data("imgpath");
													$("#url").val(data);

												}
											});

							return false;
						});
	</script>
</body>
</html>