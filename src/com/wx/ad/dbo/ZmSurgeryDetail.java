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



@Table("ZM_SURGERY")
@View("ZM_SURGERY")
@PK({ "ID" })
public class ZmSurgeryDetail extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final ZmSurgeryDetail INSTANCE = new ZmSurgeryDetail();
	
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id;
		@Column(value = "year", type = ColumnType.STRING)
	private String year;
		@Column(value = "month", type = ColumnType.STRING)
	private String month;
		@Column(value = "dept_code", type = ColumnType.STRING)
	private String dept_code;
		@Column(value = "dept", type = ColumnType.STRING)
	private String dept;
		@Column(value = "patient_id", type = ColumnType.STRING)
	private String patient_id;
		@Column(value = "name", type = ColumnType.STRING)
	private String name;
		@Column(value = "sex", type = ColumnType.STRING)
	private String sex;
		@Column(value = "age", type = ColumnType.STRING)
	private String age;
		@Column(value = "charge_type", type = ColumnType.STRING)
	private String charge_type;
		@Column(value = "mailing_address", type = ColumnType.STRING)
	private String mailing_address;
		@Column(value = "phone", type = ColumnType.STRING)
	private String phone;
		@Column(value = "admission_date_time", type = ColumnType.STRING)
	private String admission_date_time;
		@Column(value = "discharge_date_time", type = ColumnType.STRING)
	private String discharge_date_time;
		@Column(value = "operating_date", type = ColumnType.STRING)
	private String operating_date;
		@Column(value = "inhos_days", type = ColumnType.STRING)
	private String inhos_days;
		@Column(value = "diagnosis_desc", type = ColumnType.STRING)
	private String diagnosis_desc;
		@Column(value = "operation_desc", type = ColumnType.STRING)
	private String operation_desc;
		@Column(value = "operator", type = ColumnType.STRING)
	private String operator;
		@Column(value = "assistant", type = ColumnType.STRING)
	private String assistant;
		@Column(value = "operat_level", type = ColumnType.STRING)
	private String operat_level;
		@Column(value = "total_payments", type = ColumnType.STRING)
	private String total_payments;
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
		public String getDept_code() {
			return dept_code;
		}
		public void setDept_code(String dept_code) {
			this.dept_code = dept_code;
		}
		public String getDept() {
			return dept;
		}
		public void setDept(String dept) {
			this.dept = dept;
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
		public String getCharge_type() {
			return charge_type;
		}
		public void setCharge_type(String charge_type) {
			this.charge_type = charge_type;
		}
		public String getMailing_address() {
			return mailing_address;
		}
		public void setMailing_address(String mailing_address) {
			this.mailing_address = mailing_address;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
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
		public String getOperating_date() {
			return operating_date;
		}
		public void setOperating_date(String operating_date) {
			this.operating_date = operating_date;
		}
		public String getInhos_days() {
			return inhos_days;
		}
		public void setInhos_days(String inhos_days) {
			this.inhos_days = inhos_days;
		}
		public String getDiagnosis_desc() {
			return diagnosis_desc;
		}
		public void setDiagnosis_desc(String diagnosis_desc) {
			this.diagnosis_desc = diagnosis_desc;
		}
		public String getOperation_desc() {
			return operation_desc;
		}
		public void setOperation_desc(String operation_desc) {
			this.operation_desc = operation_desc;
		}
		public String getOperator() {
			return operator;
		}
		public void setOperator(String operator) {
			this.operator = operator;
		}
		public String getAssistant() {
			return assistant;
		}
		public void setAssistant(String assistant) {
			this.assistant = assistant;
		}
		public String getOperat_level() {
			return operat_level;
		}
		public void setOperat_level(String operat_level) {
			this.operat_level = operat_level;
		}
		public String getTotal_payments() {
			return total_payments;
		}
		public void setTotal_payments(String total_payments) {
			this.total_payments = total_payments;
		}
		
		


}
