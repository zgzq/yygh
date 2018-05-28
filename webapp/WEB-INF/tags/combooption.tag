<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/my.tld" prefix="my"%>
<%@ attribute name="dictcode" type="java.lang.String" rtexprvalue="true"
	description="字典的key"%>
<%@ attribute name="editable" type="java.lang.String" rtexprvalue="true"
	description="字典的key"%>
var ${dictcode}Options ={ dataSource :
${my:dictJson(dictcode)}, editable:
${editable} }; 

function ${dictcode}Renderer(value){ 
var text="";
$(${dictcode}Options.dataSource).each(function(){ 
if(this.value == value){
 text = this.text;
  } }); 
return text; }
