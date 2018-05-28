/**
** create by code gen .
**/
package com.wx.ad.dbo;

import java.util.Date;
import java.math.BigDecimal;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;

@Table("ZM_DEPTART")
@View("ZM_DEPTART")
@PK({ "DEPARTID" })
public class Deptart extends BasePO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final Deptart INSTANCE = new Deptart();

	@Column(value = "departid", type = ColumnType.NUMBER)
	private String departid;

	@Column(value = "department", type = ColumnType.STRING)
	private String department;

	@Column(value = "departopcode", type = ColumnType.STRING)
	private String departopcode;

	public String getDepartopcode() {
		return departopcode;
	}

	public void setDepartopcode(String departopcode) {
		this.departopcode = departopcode;
	}

	public String getDepartid() {
		return departid;
	}

	public void setDepartid(String departid) {
		this.departid = departid;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getValue() {
		return departid;
	}

	public String getText() {
		return department;
	}

}
