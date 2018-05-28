<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/tlds/my.tld" prefix="my"%>
<%@ attribute name="editable" type="java.lang.String" rtexprvalue="true"
	description="字典的key"%>
var DEPTOptions ={ dataSource :
${my:deptJson()}, editable:
${editable},multi : true    }; 

function DEPTRenderer(value){ 
var text=[];
var vs = value.split(",");
$(vs).each(function(data){
	 
	$(DEPTOptions.dataSource).each(function(){ 
if(this.value == vs[data]){
 text.push(this.text);
  } });
});
 
return text.join(","); }
