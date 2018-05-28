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



@Table("zm_crc_dept")
@View("zm_crc_dept")
@PK({ "ID" })
public class ZmCRCDept extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final ZmCRCDept INSTANCE = new ZmCRCDept();
	
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id;
	@Column(value = "year", type = ColumnType.STRING)
	private String year;
	@Column(value = "month", type = ColumnType.STRING)
	private String month;
	@Column(value = "patient_id", type = ColumnType.STRING)
	private String patient_id;
	@Column(value = "item_name", type = ColumnType.STRING)
	private String item_name;
	@Column(value = "dept_name", type = ColumnType.STRING)
	private String dept_name;
	@Column(value = "performed_by", type = ColumnType.STRING)
	private String performed_by;
	@Column(value = "charges", type = ColumnType.STRING)
	private String charges;
	@Column(value = "req_class", type = ColumnType.STRING)
	private String req_class;
	@Column(value = "ordered_doctor", type = ColumnType.STRING)
	private String ordered_doctor;
	@Column(value = "visit_time", type = ColumnType.STRING)
	private String visit_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getPerformed_by() {
		return performed_by;
	}
	public void setPerformed_by(String performed_by) {
		this.performed_by = performed_by;
	}
	public String getCharges() {
		return charges;
	}
	public void setCharges(String charges) {
		this.charges = charges;
	}
	public String getReq_class() {
		return req_class;
	}
	public void setReq_class(String req_class) {
		this.req_class = req_class;
	}
	public String getOrdered_doctor() {
		return ordered_doctor;
	}
	public void setOrdered_doctor(String ordered_doctor) {
		this.ordered_doctor = ordered_doctor;
	}
	public String getVisit_time() {
		return visit_time;
	}
	public void setVisit_time(String visit_time) {
		this.visit_time = visit_time;
	}
	
}
