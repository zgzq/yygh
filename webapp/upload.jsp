<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<form action="hs/upload" method="post" enctype="multipart/form-data" >
表名:<input name="tablename" value="ZY_HS_QJJL_MX"></input><br/>
主键列名:<input name="pkcolname" value="mxnm,jlnm"></input><br/>
主键值:<input name="pkvalue" value="21,3"></input><br/>
存路径的列名:<input name="pathcol" value="YYJL"></input><br/>
文件:<input name="file" type="file"></input><br/>
<input type="submit"/> 
</form>
</body>
</html>
