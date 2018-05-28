/**
** create by code gen .
**/
package com.wx.ad.dbo;

import java.util.Date;
import java.math.BigDecimal;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.mapping.MappingInfo;
import my.dao.mapping.MappingInfoHolder;



@Table("KPI_VALUE_MP_MONTH")
@View("KPI_VALUE_MP_MONTH_V")
@PK({ "KPINAME","KPITYPE","ORGID","USERID","YEAR" })
public class KpiValueMpMonth extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueMpMonth INSTANCE = new KpiValueMpMonth();
	
	@Column(value = "userid", type = ColumnType.NUMBER)
	private BigDecimal userid ;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private BigDecimal orgid ;
	
	@Column(value = "year", type = ColumnType.NUMBER)
	private BigDecimal year ;
	
	@Column(value = "kpiname", type = ColumnType.STRING)
	private String kpiname ;
	
	@Column(value = "kpitype", type = ColumnType.STRING)
	private String kpitype ;
	
	@Column(value = "seq", type = ColumnType.NUMBER)
	private BigDecimal seq ;
	
	@Column(value = "m1", type = ColumnType.NUMBER)
	private BigDecimal m1 ;
	
	@Column(value = "m2", type = ColumnType.NUMBER)
	private BigDecimal m2 ;
	
	@Column(value = "m3", type = ColumnType.NUMBER)
	private BigDecimal m3 ;
	
	@Column(value = "m4", type = ColumnType.NUMBER)
	private BigDecimal m4 ;
	
	@Column(value = "m5", type = ColumnType.NUMBER)
	private BigDecimal m5 ;
	
	@Column(value = "m6", type = ColumnType.NUMBER)
	private BigDecimal m6 ;
	
	@Column(value = "m7", type = ColumnType.NUMBER)
	private BigDecimal m7 ;
	
	@Column(value = "m8", type = ColumnType.NUMBER)
	private BigDecimal m8 ;
	
	@Column(value = "m9", type = ColumnType.NUMBER)
	private BigDecimal m9 ;
	
	@Column(value = "m10", type = ColumnType.NUMBER)
	private BigDecimal m10 ;
	
	@Column(value = "m11", type = ColumnType.NUMBER)
	private BigDecimal m11 ;
	
	@Column(value = "m12", type = ColumnType.NUMBER)
	private BigDecimal m12 ;
	
	@ReadOnly
	@Column(value = "org_name", type = ColumnType.STRING)
	private String org_name ;
	


	public BigDecimal getUserid (){
		return userid;
	}
		 
	public void setUserid (BigDecimal userid){
		this.userid=userid;
	}

	public BigDecimal getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (BigDecimal orgid){
		this.orgid=orgid;
	}

	public BigDecimal getYear (){
		return year;
	}
		 
	public void setYear (BigDecimal year){
		this.year=year;
	}

	public String getKpiname (){
		return kpiname;
	}
		 
	public void setKpiname (String kpiname){
		this.kpiname=kpiname;
	}

	public String getKpitype (){
		return kpitype;
	}
		 
	public void setKpitype (String kpitype){
		this.kpitype=kpitype;
	}

	public BigDecimal getSeq (){
		return seq;
	}
		 
	public void setSeq (BigDecimal seq){
		this.seq=seq;
	}

	public BigDecimal getM1 (){
		return m1;
	}
		 
	public void setM1 (BigDecimal m1){
		this.m1=m1;
	}

	public BigDecimal getM2 (){
		return m2;
	}
		 
	public void setM2 (BigDecimal m2){
		this.m2=m2;
	}

	public BigDecimal getM3 (){
		return m3;
	}
		 
	public void setM3 (BigDecimal m3){
		this.m3=m3;
	}

	public BigDecimal getM4 (){
		return m4;
	}
		 
	public void setM4 (BigDecimal m4){
		this.m4=m4;
	}

	public BigDecimal getM5 (){
		return m5;
	}
		 
	public void setM5 (BigDecimal m5){
		this.m5=m5;
	}

	public BigDecimal getM6 (){
		return m6;
	}
		 
	public void setM6 (BigDecimal m6){
		this.m6=m6;
	}

	public BigDecimal getM7 (){
		return m7;
	}
		 
	public void setM7 (BigDecimal m7){
		this.m7=m7;
	}

	public BigDecimal getM8 (){
		return m8;
	}
		 
	public void setM8 (BigDecimal m8){
		this.m8=m8;
	}

	public BigDecimal getM9 (){
		return m9;
	}
		 
	public void setM9 (BigDecimal m9){
		this.m9=m9;
	}

	public BigDecimal getM10 (){
		return m10;
	}
		 
	public void setM10 (BigDecimal m10){
		this.m10=m10;
	}

	public BigDecimal getM11 (){
		return m11;
	}
		 
	public void setM11 (BigDecimal m11){
		this.m11=m11;
	}

	public BigDecimal getM12 (){
		return m12;
	}
		 
	public void setM12 (BigDecimal m12){
		this.m12=m12;
	}

	public String getOrg_name (){
		return org_name;
	}
		 
	public void setOrg_name (String org_name){
		this.org_name=org_name;
	}

}
