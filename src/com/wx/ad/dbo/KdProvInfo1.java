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



@Table("kd_prov_value")
@View("kd_provinfo1_v")

public class KdProvInfo1 extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KdProvInfo1 INSTANCE = new KdProvInfo1();
	
	
		@Column(value = "year", type = ColumnType.STRING)
	private String year;
		@Column(value = "orgid", type = ColumnType.STRING)
		private String orgid;
		@Column(value = "province_id", type = ColumnType.STRING)
	private String province_id;
		@Column(value = "province_name", type = ColumnType.STRING)
	private String province_name;
		@Column(value = "m01", type = ColumnType.STRING)
	private String m01;
		@Column(value = "m02", type = ColumnType.STRING)
		private String m02;
		@Column(value = "m03", type = ColumnType.STRING)
		private String m03;
		@Column(value = "m04", type = ColumnType.STRING)
		private String m04;
		@Column(value = "m05", type = ColumnType.STRING)
		private String m05;
		@Column(value = "m06", type = ColumnType.STRING)
		private String m06;
		@Column(value = "m07", type = ColumnType.STRING)
		private String m07;
		@Column(value = "m08", type = ColumnType.STRING)
		private String m08;
		@Column(value = "m09", type = ColumnType.STRING)
		private String m09;
		@Column(value = "m10", type = ColumnType.STRING)
		private String m10;
		@Column(value = "m11", type = ColumnType.STRING)
		private String m11;
		@Column(value = "m12", type = ColumnType.STRING)
		private String m12;
		@Column(value = "total", type = ColumnType.STRING)
		private String total;
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public String getProvince_id() {
			return province_id;
		}
		public void setProvince_id(String province_id) {
			this.province_id = province_id;
		}
		public String getProvince_name() {
			return province_name;
		}
		public void setProvince_name(String province_name) {
			this.province_name = province_name;
		}
		public String getM01() {
			return m01;
		}
		public void setM01(String m01) {
			this.m01 = m01;
		}
		public String getM02() {
			return m02;
		}
		public void setM02(String m02) {
			this.m02 = m02;
		}
		public String getM03() {
			return m03;
		}
		public void setM03(String m03) {
			this.m03 = m03;
		}
		public String getM04() {
			return m04;
		}
		public void setM04(String m04) {
			this.m04 = m04;
		}
		public String getM05() {
			return m05;
		}
		public void setM05(String m05) {
			this.m05 = m05;
		}
		public String getM06() {
			return m06;
		}
		public void setM06(String m06) {
			this.m06 = m06;
		}
		public String getM07() {
			return m07;
		}
		public void setM07(String m07) {
			this.m07 = m07;
		}
		public String getM08() {
			return m08;
		}
		public void setM08(String m08) {
			this.m08 = m08;
		}
		public String getM09() {
			return m09;
		}
		public void setM09(String m09) {
			this.m09 = m09;
		}
		public String getM10() {
			return m10;
		}
		public void setM10(String m10) {
			this.m10 = m10;
		}
		public String getM11() {
			return m11;
		}
		public void setM11(String m11) {
			this.m11 = m11;
		}
		public String getM12() {
			return m12;
		}
		public void setM12(String m12) {
			this.m12 = m12;
		}
		public String getOrgid() {
			return orgid;
		}
		public void setOrgid(String orgid) {
			this.orgid = orgid;
		}
		public String getTotal() {
			return total;
		}
		public void setTotal(String total) {
			this.total = total;
		}
		
		
}
