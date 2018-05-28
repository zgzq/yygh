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



@Table("ZM_CRC_INFO")
@View("ZM_CRC_INFO")
@PK({ "ID" })
public class ZmCRCInfo extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final ZmCRCInfo INSTANCE = new ZmCRCInfo();
	
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id;
	@Column(value = "year", type = ColumnType.STRING)
	private String year;
	@Column(value = "month", type = ColumnType.STRING)
	private String month;
	@Column(value = "patient_id", type = ColumnType.STRING)
	private String patient_id;
	@Column(value = "name", type = ColumnType.STRING)
	private String name;
	@Column(value = "sex", type = ColumnType.STRING)
	private String sex;
	@Column(value = "age", type = ColumnType.STRING)
	private String age;
	@Column(value = "dept_name", type = ColumnType.STRING)
	private String dept_name;
	@Column(value = "visit_date", type = ColumnType.STRING)
	private String visit_date;
	@Column(value = "mailing_address", type = ColumnType.STRING)
	private String mailing_address;
	@Column(value = "ordered_by_doctor", type = ColumnType.STRING)
	private String ordered_by_doctor;
	@Column(value = "registration_status", type = ColumnType.STRING)
	private String registration_status;
	@Column(value = "charge_type", type = ColumnType.STRING)
	private String charge_type;
	@Column(value = "first_visit_indicator", type = ColumnType.STRING)
	private String first_visit_indicator;
	public String getFirst_visit_indicator() {
		return first_visit_indicator;
	}
	public void setFirst_visit_indicator(String first_visit_indicator) {
		this.first_visit_indicator = first_visit_indicator;
	}
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
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	public String getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}
	public String getMailing_address() {
		return mailing_address;
	}
	public void setMailing_address(String mailing_address) {
		this.mailing_address = mailing_address;
	}
	public String getOrdered_by_doctor() {
		return ordered_by_doctor;
	}
	public void setOrdered_by_doctor(String ordered_by_doctor) {
		this.ordered_by_doctor = ordered_by_doctor;
	}
	public String getRegistration_status() {
		return registration_status;
	}
	public void setRegistration_status(String registration_status) {
		this.registration_status = registration_status;
	}
	public String getCharge_type() {
		return charge_type;
	}
	public void setCharge_type(String charge_type) {
		this.charge_type = charge_type;
	}
	
	


}
