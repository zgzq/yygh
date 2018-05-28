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



@Table("ZM_SURGERY_COL")
@View("ZM_SURGERY_COL_V")
@PK({ "ID" })
public class ZmSurgeryCol extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final ZmSurgeryCol INSTANCE = new ZmSurgeryCol();
	
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id;
		@Column(value = "year", type = ColumnType.STRING)
	private String year;
		@Column(value = "month", type = ColumnType.STRING)
	private String month;
		@Column(value = "ordered_by", type = ColumnType.STRING)
	private String ordered_by;
		@Column(value = "performed_by", type = ColumnType.STRING)
	private String performed_by;
		@Column(value = "order_dept", type = ColumnType.STRING)
		private String order_dept;
		@Column(value = "performed_dept", type = ColumnType.STRING)
		private String performed_dept;
		@Column(value = "num_per", type = ColumnType.STRING)
	private String num_per;
		@Column(value = "bed_days", type = ColumnType.STRING)
	private String bed_days;
		@Column(value = "total_payments", type = ColumnType.STRING)
	private String total_payments;
		@Column(value = "avg_payments", type = ColumnType.STRING)
	private String avg_payments;
		@Column(value = "second_s", type = ColumnType.STRING)
	private String second_s;
		@Column(value = "third_s", type = ColumnType.STRING)
	private String third_s;
		@Column(value = "forth_s", type = ColumnType.STRING)
	private String forth_s;
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
		public String getOrdered_by() {
			return ordered_by;
		}
		public void setOrdered_by(String ordered_by) {
			this.ordered_by = ordered_by;
		}
		public String getOrder_dept() {
			return order_dept;
		}
		public void setOrder_dept(String order_dept) {
			this.order_dept = order_dept;
		}
		public String getPerformed_dept() {
			return performed_dept;
		}
		public void setPerformed_dept(String performed_dept) {
			this.performed_dept = performed_dept;
		}
		public String getPerformed_by() {
			return performed_by;
		}
		public void setPerformed_by(String performed_by) {
			this.performed_by = performed_by;
		}
		public String getNum_per() {
			return num_per;
		}
		public void setNum_per(String num_per) {
			this.num_per = num_per;
		}
		public String getBed_days() {
			return bed_days;
		}
		public void setBed_days(String bed_days) {
			this.bed_days = bed_days;
		}
		public String getTotal_payments() {
			return total_payments;
		}
		public void setTotal_payments(String total_payments) {
			this.total_payments = total_payments;
		}
		public String getAvg_payments() {
			return avg_payments;
		}
		public void setAvg_payments(String avg_payments) {
			this.avg_payments = avg_payments;
		}
		public String getSecond_s() {
			return second_s;
		}
		public void setSecond_s(String second_s) {
			this.second_s = second_s;
		}
		public String getThird_s() {
			return third_s;
		}
		public void setThird_s(String third_s) {
			this.third_s = third_s;
		}
		public String getForth_s() {
			return forth_s;
		}
		public void setForth_s(String forth_s) {
			this.forth_s = forth_s;
		}
		
		
		
}
