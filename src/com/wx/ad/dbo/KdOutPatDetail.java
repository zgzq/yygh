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



@Table("kd_outpatdetail")
@View("kd_outpatdetail")

public class KdOutPatDetail extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KdOutPatDetail INSTANCE = new KdOutPatDetail();
	
	
		@Column(value = "year", type = ColumnType.STRING)
	private String year;
		@Column(value = "month", type = ColumnType.STRING)
	private String month;
		@Column(value = "orgid", type = ColumnType.STRING)
	private String orgid;
		@Column(value = "name", type = ColumnType.STRING)
	private String name;
		@Column(value = "sex", type = ColumnType.STRING)
	private String sex;
		@Column(value = "age", type = ColumnType.STRING)
	private String age;
		@Column(value = "unit_in_contract", type = ColumnType.STRING)
	private String unit_in_contract;
		@Column(value = "identity", type = ColumnType.STRING)
	private String identity;
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
		public String getOrgid() {
			return orgid;
		}
		public void setOrgid(String orgid) {
			this.orgid = orgid;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getUnit_in_contract() {
			return unit_in_contract;
		}
		public void setUnit_in_contract(String unit_in_contract) {
			this.unit_in_contract = unit_in_contract;
		}
		public String getIdentity() {
			return identity;
		}
		public void setIdentity(String identity) {
			this.identity = identity;
		}
		
}
