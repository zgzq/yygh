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



@Table("ZM_ZBSJK_INFO1")
@View("ZM_ZBSJK_INFO1")
@PK({ "ID" })
public class ZbsjkInfo1 extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final ZbsjkInfo1 INSTANCE = new ZbsjkInfo1();
	
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id;
		@Column(value = "year", type = ColumnType.STRING)
	private String year;
		@Column(value = "month", type = ColumnType.STRING)
	private String month;
		@Column(value = "departopcode", type = ColumnType.STRING)
	private String departopcode;
		@Column(value = "project", type = ColumnType.STRING)
	private String project;
		@Column(value = "mbzb", type = ColumnType.NUMBER)
	private String mbzb;
		@Column(value = "ljwc", type = ColumnType.NUMBER)
	private String ljwc;
		@Column(value = "ljwcl", type = ColumnType.NUMBER)
	private String ljwcl;
		@Column(value = "ljtqdb", type = ColumnType.NUMBER)
	private String ljtqdb;
		@Column(value = "symb", type = ColumnType.NUMBER)
	private String symb;
		@Column(value = "yj", type = ColumnType.NUMBER)
	private String yj;
		@Column(value = "dqmb", type = ColumnType.NUMBER)
	private String dqmb;
		@Column(value = "dqz", type = ColumnType.NUMBER)
	private String dqz;
		@Column(value = "dqwcl", type = ColumnType.NUMBER)
	private String dqwcl;
		@Column(value = "tqdb", type = ColumnType.NUMBER)
	private String tqdb;
		@Column(value = "hb", type = ColumnType.NUMBER)
	private String hb;
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
		public String getDepartopcode() {
			return departopcode;
		}
		public void setDepartopcode(String departopcode) {
			this.departopcode = departopcode;
		}
		public String getProject() {
			return project;
		}
		public void setProject(String project) {
			this.project = project;
		}
		public String getMbzb() {
			return mbzb;
		}
		public void setMbzb(String mbzb) {
			this.mbzb = mbzb;
		}
		public String getLjwc() {
			return ljwc;
		}
		public void setLjwc(String ljwc) {
			this.ljwc = ljwc;
		}
		public String getLjwcl() {
			return ljwcl;
		}
		public void setLjwcl(String ljwcl) {
			this.ljwcl = ljwcl;
		}
		public String getLjtqdb() {
			return ljtqdb;
		}
		public void setLjtqdb(String ljtqdb) {
			this.ljtqdb = ljtqdb;
		}
		public String getSymb() {
			return symb;
		}
		public void setSymb(String symb) {
			this.symb = symb;
		}
		public String getYj() {
			return yj;
		}
		public void setYj(String yj) {
			this.yj = yj;
		}
		public String getDqmb() {
			return dqmb;
		}
		public void setDqmb(String dqmb) {
			this.dqmb = dqmb;
		}
		public String getDqz() {
			return dqz;
		}
		public void setDqz(String dqz) {
			this.dqz = dqz;
		}
		public String getDqwcl() {
			return dqwcl;
		}
		public void setDqwcl(String dqwcl) {
			this.dqwcl = dqwcl;
		}
		public String getTqdb() {
			return tqdb;
		}
		public void setTqdb(String tqdb) {
			this.tqdb = tqdb;
		}
		public String getHb() {
			return hb;
		}
		public void setHb(String hb) {
			this.hb = hb;
		}
		
}
