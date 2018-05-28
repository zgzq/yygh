/**
** create by code gen .
**/
package com.wx.cp.model;

import java.util.Date;
import java.math.BigDecimal;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.mapping.MappingInfo;
import my.dao.mapping.MappingInfoHolder;



@Table("JZ_JMYY_DEPT_V")
@View("JZ_JMYY_DEPT_V")
@PK({  })
public class JmyyDept extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final JmyyDept INSTANCE = new JmyyDept();
	
	@Column(value = "deptcode", type = ColumnType.NUMBER)
	private Long Deptcode ;
	
	@Column(value = "deptname", type = ColumnType.STRING)
	private String Deptname ;

	public Long getDeptcode() {
		return Deptcode;
	}

	public void setDeptcode(Long deptcode) {
		Deptcode = deptcode;
	}

	public String getDeptname() {
		return Deptname;
	}

	public void setDeptname(String deptname) {
		Deptname = deptname;
	}

	
	
	



}
