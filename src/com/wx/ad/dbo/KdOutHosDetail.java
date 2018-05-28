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



@Table("kd_outhosdetail")
@View("kd_outhosdetail")

public class KdOutHosDetail extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KdOutHosDetail INSTANCE = new KdOutHosDetail();
	
	
		@Column(value = "year", type = ColumnType.STRING)
	private String year;
		@Column(value = "month", type = ColumnType.STRING)
	private String month;
		@Column(value = "orgid", type = ColumnType.STRING)
	private String orgid;
		@Column(value = "inp_no", type = ColumnType.STRING)
	private String inp_no;
		@Column(value = "name", type = ColumnType.STRING)
	private String name;
		@Column(value = "sex", type = ColumnType.STRING)
	private String sex;
		@Column(value = "age", type = ColumnType.STRING)
	private String age;
		@Column(value = "diagnosis", type = ColumnType.STRING)
	private String diagnosis;
		@Column(value = "mail_address", type = ColumnType.STRING)
	private String mail_address;
		@Column(value = "identity", type = ColumnType.STRING)
	private String identity;
		@Column(value = "dept_admission_to", type = ColumnType.STRING)
	private String dept_admission_to;
		@Column(value = "attending_doctor", type = ColumnType.STRING)
	private String attending_doctor;
		@Column(value = "admission_date_time", type = ColumnType.STRING)
	private String admission_date_time;
		@Column(value = "discharge_date_time", type = ColumnType.STRING)
	private String discharge_date_time;
		@Column(value = "inhos_days", type = ColumnType.STRING)
	private String inhos_days;
		@Column(value = "total_payments", type = ColumnType.STRING)
	private String total_payments;
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
		public String getInp_no() {
			return inp_no;
		}
		public void setInp_no(String inp_no) {
			this.inp_no = inp_no;
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
		public String getDiagnosis() {
			return diagnosis;
		}
		public void setDiagnosis(String diagnosis) {
			this.diagnosis = diagnosis;
		}
		public String getMail_address() {
			return mail_address;
		}
		public void setMail_address(String mail_address) {
			this.mail_address = mail_address;
		}
		public String getDept_admission_to() {
			return dept_admission_to;
		}
		public void setDept_admission_to(String dept_admission_to) {
			this.dept_admission_to = dept_admission_to;
		}
		public String getAttending_doctor() {
			return attending_doctor;
		}
		public void setAttending_doctor(String attending_doctor) {
			this.attending_doctor = attending_doctor;
		}
		public String getAdmission_date_time() {
			return admission_date_time;
		}
		public void setAdmission_date_time(String admission_date_time) {
			this.admission_date_time = admission_date_time;
		}
		public String getDischarge_date_time() {
			return discharge_date_time;
		}
		public void setDischarge_date_time(String discharge_date_time) {
			this.discharge_date_time = discharge_date_time;
		}
		public String getInhos_days() {
			return inhos_days;
		}
		public void setInhos_days(String inhos_days) {
			this.inhos_days = inhos_days;
		}
		public String getTotal_payments() {
			return total_payments;
		}
		public void setTotal_payments(String total_payments) {
			this.total_payments = total_payments;
		}
		public String getIdentity() {
			return identity;
		}
		public void setIdentity(String identity) {
			this.identity = identity;
		}
		
}
