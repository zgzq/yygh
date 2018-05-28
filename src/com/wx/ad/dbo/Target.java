/**
** create by code gen .
**/
package com.wx.ad.dbo;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;

@Table("ZM_TARGET")
@View("ZM_TARGET_V")
@PK({ "ID" })
public class Target extends BasePO {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public static final Target INSTANCE = new Target();

	@Column(value = "id", type = ColumnType.NUMBER)
	private String id;

	@Column(value = "target", type = ColumnType.STRING)
	private String target;


	@Column(value = "year", type = ColumnType.NUMBER)
	private String year;

	@Column(value = "month", type = ColumnType.NUMBER)
	private String month;

	@Column(value = "monthvalue", type = ColumnType.NUMBER)
	private String monthvalue;

	@ReadOnly
	@Column(value = "department", type = ColumnType.STRING)
	private String department;


	@Column(value = "departopcode", type = ColumnType.STRING)
	private String departopcode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonthvalue() {
		return monthvalue;
	}

	public void setMonthvalue(String monthvalue) {
		this.monthvalue = monthvalue;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartopcode() {
		return departopcode;
	}

	public void setDepartopcode(String departopcode) {
		this.departopcode = departopcode;
	}

}
