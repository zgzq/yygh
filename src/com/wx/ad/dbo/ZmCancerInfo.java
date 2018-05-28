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



@Table("zm_cancer_info")
@View("zm_cancer_info")
@PK({ "ID" })
public class ZmCancerInfo extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final ZmCancerInfo INSTANCE = new ZmCancerInfo();
	
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id;
	@Column(value = "project_name", type = ColumnType.STRING)
	private String project_name;
	@Column(value = "value1", type = ColumnType.STRING)
	private String value1;
	@Column(value = "value2", type = ColumnType.STRING)
	private String value2;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
	
}
