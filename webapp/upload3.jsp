<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<form action="site/file/upload" method="post" enctype="multipart/form-data" >
json:<input name="json" value='{"cclj":"0","cjid":"1","mxid":"","pxm":1,"scbz":"0","scsj":"2014-11-06 21:31:16","wjdx":1,"wjfl":"0","wjlj":"createfolder","wjlx":"11","wjmc":"test.jpg","wzms":"0","zhxgrq":"2014-11-06 21:31:16"}'></input><br/>
文件:<input name="file" type="file"></input><br/>
<input type="submit"/> 

<br/>

<p></p>

<img src="site/file/download?mxid=2" width="100px" height="100px"></img>  


</form>
</body>
</html>
