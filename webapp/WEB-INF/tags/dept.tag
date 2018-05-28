<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/my.tld" prefix="my"%>
<%@ attribute name="editable" type="java.lang.String" rtexprvalue="true"
	description="字典的key"%>
var DEPTOptions ={ dataSource :
${my:deptJson()}, editable:
${editable} }; 

function DEPTRenderer(value){ 
var text="";
$(DEPTOptions.dataSource).each(function(){ 
if(this.value == value){
 text = this.text;
  } }); 
 if(text!="")return text;
  return value;  }
