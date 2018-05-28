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



@Table("kd_type_value")
@View("kd_typeinfo1_v")

public class KdTypeInfo1 extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KdTypeInfo1 INSTANCE = new KdTypeInfo1();
	
	
		@Column(value = "year", type = ColumnType.STRING)
	private String year;
		@Column(value = "month", type = ColumnType.STRING)
		private String month;
		@Column(value = "orgid", type = ColumnType.STRING)
		private String orgid;
		@Column(value = "iga", type = ColumnType.STRING)
	private String iga;
		@Column(value = "hspn", type = ColumnType.STRING)
	private String hspn;
		@Column(value = "ln", type = ColumnType.STRING)
	private String ln;
		@Column(value = "crf", type = ColumnType.STRING)
		private String crf;
		@Column(value = "cgn", type = ColumnType.STRING)
		private String cgn;
		@Column(value = "ns", type = ColumnType.STRING)
		private String ns;
		@Column(value = "dm", type = ColumnType.STRING)
		private String dm;
		@Column(value = "dn", type = ColumnType.STRING)
		private String dn;
		@Column(value = "hp", type = ColumnType.STRING)
		private String hp;
		@Column(value = "hpk", type = ColumnType.STRING)
		private String hpk;
		@Column(value = "gk", type = ColumnType.STRING)
		private String gk;
		@Column(value = "md", type = ColumnType.STRING)
		private String md;
		@Column(value = "pk", type = ColumnType.STRING)
		private String pk;
		@Column(value = "qt", type = ColumnType.STRING)
		private String qt;
		@Column(value = "total", type = ColumnType.STRING)
		private String total;
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
		public String getIga() {
			return iga;
		}
		public void setIga(String iga) {
			this.iga = iga;
		}
		public String getHspn() {
			return hspn;
		}
		public void setHspn(String hspn) {
			this.hspn = hspn;
		}
		public String getLn() {
			return ln;
		}
		public void setLn(String ln) {
			this.ln = ln;
		}
		public String getCrf() {
			return crf;
		}
		public void setCrf(String crf) {
			this.crf = crf;
		}
		public String getCgn() {
			return cgn;
		}
		public void setCgn(String cgn) {
			this.cgn = cgn;
		}
		public String getNs() {
			return ns;
		}
		public void setNs(String ns) {
			this.ns = ns;
		}
		public String getDm() {
			return dm;
		}
		public void setDm(String dm) {
			this.dm = dm;
		}
		public String getDn() {
			return dn;
		}
		public void setDn(String dn) {
			this.dn = dn;
		}
		public String getHp() {
			return hp;
		}
		public void setHp(String hp) {
			this.hp = hp;
		}
		public String getHpk() {
			return hpk;
		}
		public void setHpk(String hpk) {
			this.hpk = hpk;
		}
		public String getGk() {
			return gk;
		}
		public void setGk(String gk) {
			this.gk = gk;
		}
		public String getMd() {
			return md;
		}
		public void setMd(String md) {
			this.md = md;
		}
		public String getPk() {
			return pk;
		}
		public void setPk(String pk) {
			this.pk = pk;
		}
		public String getQt() {
			return qt;
		}
		public void setQt(String qt) {
			this.qt = qt;
		}
		public String getTotal() {
			return total;
		}
		public void setTotal(String total) {
			this.total = total;
		}
		
}
