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



@Table("KPI_VALUE_MP_DAY")
@View("KPI_VALUE_MP_DAY_V")
@PK({ "USERID","KPINAME","ORGID","YEARMONTH" })
public class KpiValueMpDay extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueMpDay INSTANCE = new KpiValueMpDay();
	
	@Column(value = "userid", type = ColumnType.NUMBER)
	private BigDecimal userid ;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private BigDecimal orgid ;
	
	@Column(value = "yearmonth", type = ColumnType.NUMBER)
	private BigDecimal yearmonth ;
	
	@Column(value = "kpiname", type = ColumnType.STRING)
	private String kpiname ;
	
	@Column(value = "kpivalueref", type = ColumnType.NUMBER)
	private BigDecimal kpivalueref ;
	
	@Column(value = "kpivalueavg", type = ColumnType.NUMBER)
	private BigDecimal kpivalueavg ;
	
	@Column(value = "d1", type = ColumnType.NUMBER)
	private BigDecimal d1 ;
	
	@Column(value = "d2", type = ColumnType.NUMBER)
	private BigDecimal d2 ;
	
	@Column(value = "d3", type = ColumnType.NUMBER)
	private BigDecimal d3 ;
	
	@Column(value = "d4", type = ColumnType.NUMBER)
	private BigDecimal d4 ;
	
	@Column(value = "d5", type = ColumnType.NUMBER)
	private BigDecimal d5 ;
	
	@Column(value = "d6", type = ColumnType.NUMBER)
	private BigDecimal d6 ;
	
	@Column(value = "d7", type = ColumnType.NUMBER)
	private BigDecimal d7 ;
	
	@Column(value = "d8", type = ColumnType.NUMBER)
	private BigDecimal d8 ;
	
	@Column(value = "d9", type = ColumnType.NUMBER)
	private BigDecimal d9 ;
	
	@Column(value = "d10", type = ColumnType.NUMBER)
	private BigDecimal d10 ;
	
	@Column(value = "d11", type = ColumnType.NUMBER)
	private BigDecimal d11 ;
	
	@Column(value = "d12", type = ColumnType.NUMBER)
	private BigDecimal d12 ;
	
	@Column(value = "d13", type = ColumnType.NUMBER)
	private BigDecimal d13 ;
	
	@Column(value = "d14", type = ColumnType.NUMBER)
	private BigDecimal d14 ;
	
	@Column(value = "d15", type = ColumnType.NUMBER)
	private BigDecimal d15 ;
	
	@Column(value = "d16", type = ColumnType.NUMBER)
	private BigDecimal d16 ;
	
	@Column(value = "d17", type = ColumnType.NUMBER)
	private BigDecimal d17 ;
	
	@Column(value = "d18", type = ColumnType.NUMBER)
	private BigDecimal d18 ;
	
	@Column(value = "d19", type = ColumnType.NUMBER)
	private BigDecimal d19 ;
	
	@Column(value = "d20", type = ColumnType.NUMBER)
	private BigDecimal d20 ;
	
	@Column(value = "d21", type = ColumnType.NUMBER)
	private BigDecimal d21 ;
	
	@Column(value = "d22", type = ColumnType.NUMBER)
	private BigDecimal d22 ;
	
	@Column(value = "d23", type = ColumnType.NUMBER)
	private BigDecimal d23 ;
	
	@Column(value = "d24", type = ColumnType.NUMBER)
	private BigDecimal d24 ;
	
	@Column(value = "d25", type = ColumnType.NUMBER)
	private BigDecimal d25 ;
	
	@Column(value = "d26", type = ColumnType.NUMBER)
	private BigDecimal d26 ;
	
	@Column(value = "d27", type = ColumnType.NUMBER)
	private BigDecimal d27 ;
	
	@Column(value = "d28", type = ColumnType.NUMBER)
	private BigDecimal d28 ;
	
	@Column(value = "d29", type = ColumnType.NUMBER)
	private BigDecimal d29 ;
	
	@Column(value = "d30", type = ColumnType.NUMBER)
	private BigDecimal d30 ;
	
	@Column(value = "d31", type = ColumnType.NUMBER)
	private BigDecimal d31 ;
	
	@Column(value = "seq", type = ColumnType.NUMBER)
	private BigDecimal seq ;
	
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

	public BigDecimal getYearmonth (){
		return yearmonth;
	}
		 
	public void setYearmonth (BigDecimal yearmonth){
		this.yearmonth=yearmonth;
	}

	public String getKpiname (){
		return kpiname;
	}
		 
	public void setKpiname (String kpiname){
		this.kpiname=kpiname;
	}

	public BigDecimal getKpivalueref (){
		return kpivalueref;
	}
		 
	public void setKpivalueref (BigDecimal kpivalueref){
		this.kpivalueref=kpivalueref;
	}

	public BigDecimal getKpivalueavg (){
		return kpivalueavg;
	}
		 
	public void setKpivalueavg (BigDecimal kpivalueavg){
		this.kpivalueavg=kpivalueavg;
	}

	public BigDecimal getD1 (){
		return d1;
	}
		 
	public void setD1 (BigDecimal d1){
		this.d1=d1;
	}

	public BigDecimal getD2 (){
		return d2;
	}
		 
	public void setD2 (BigDecimal d2){
		this.d2=d2;
	}

	public BigDecimal getD3 (){
		return d3;
	}
		 
	public void setD3 (BigDecimal d3){
		this.d3=d3;
	}

	public BigDecimal getD4 (){
		return d4;
	}
		 
	public void setD4 (BigDecimal d4){
		this.d4=d4;
	}

	public BigDecimal getD5 (){
		return d5;
	}
		 
	public void setD5 (BigDecimal d5){
		this.d5=d5;
	}

	public BigDecimal getD6 (){
		return d6;
	}
		 
	public void setD6 (BigDecimal d6){
		this.d6=d6;
	}

	public BigDecimal getD7 (){
		return d7;
	}
		 
	public void setD7 (BigDecimal d7){
		this.d7=d7;
	}

	public BigDecimal getD8 (){
		return d8;
	}
		 
	public void setD8 (BigDecimal d8){
		this.d8=d8;
	}

	public BigDecimal getD9 (){
		return d9;
	}
		 
	public void setD9 (BigDecimal d9){
		this.d9=d9;
	}

	public BigDecimal getD10 (){
		return d10;
	}
		 
	public void setD10 (BigDecimal d10){
		this.d10=d10;
	}

	public BigDecimal getD11 (){
		return d11;
	}
		 
	public void setD11 (BigDecimal d11){
		this.d11=d11;
	}

	public BigDecimal getD12 (){
		return d12;
	}
		 
	public void setD12 (BigDecimal d12){
		this.d12=d12;
	}

	public BigDecimal getD13 (){
		return d13;
	}
		 
	public void setD13 (BigDecimal d13){
		this.d13=d13;
	}

	public BigDecimal getD14 (){
		return d14;
	}
		 
	public void setD14 (BigDecimal d14){
		this.d14=d14;
	}

	public BigDecimal getD15 (){
		return d15;
	}
		 
	public void setD15 (BigDecimal d15){
		this.d15=d15;
	}

	public BigDecimal getD16 (){
		return d16;
	}
		 
	public void setD16 (BigDecimal d16){
		this.d16=d16;
	}

	public BigDecimal getD17 (){
		return d17;
	}
		 
	public void setD17 (BigDecimal d17){
		this.d17=d17;
	}

	public BigDecimal getD18 (){
		return d18;
	}
		 
	public void setD18 (BigDecimal d18){
		this.d18=d18;
	}

	public BigDecimal getD19 (){
		return d19;
	}
		 
	public void setD19 (BigDecimal d19){
		this.d19=d19;
	}

	public BigDecimal getD20 (){
		return d20;
	}
		 
	public void setD20 (BigDecimal d20){
		this.d20=d20;
	}

	public BigDecimal getD21 (){
		return d21;
	}
		 
	public void setD21 (BigDecimal d21){
		this.d21=d21;
	}

	public BigDecimal getD22 (){
		return d22;
	}
		 
	public void setD22 (BigDecimal d22){
		this.d22=d22;
	}

	public BigDecimal getD23 (){
		return d23;
	}
		 
	public void setD23 (BigDecimal d23){
		this.d23=d23;
	}

	public BigDecimal getD24 (){
		return d24;
	}
		 
	public void setD24 (BigDecimal d24){
		this.d24=d24;
	}

	public BigDecimal getD25 (){
		return d25;
	}
		 
	public void setD25 (BigDecimal d25){
		this.d25=d25;
	}

	public BigDecimal getD26 (){
		return d26;
	}
		 
	public void setD26 (BigDecimal d26){
		this.d26=d26;
	}

	public BigDecimal getD27 (){
		return d27;
	}
		 
	public void setD27 (BigDecimal d27){
		this.d27=d27;
	}

	public BigDecimal getD28 (){
		return d28;
	}
		 
	public void setD28 (BigDecimal d28){
		this.d28=d28;
	}

	public BigDecimal getD29 (){
		return d29;
	}
		 
	public void setD29 (BigDecimal d29){
		this.d29=d29;
	}

	public BigDecimal getD30 (){
		return d30;
	}
		 
	public void setD30 (BigDecimal d30){
		this.d30=d30;
	}

	public BigDecimal getD31 (){
		return d31;
	}
		 
	public void setD31 (BigDecimal d31){
		this.d31=d31;
	}

	public String getOrg_name (){
		return org_name;
	}
		 
	public void setOrg_name (String org_name){
		this.org_name=org_name;
	}

	public BigDecimal getSeq() {
		return seq;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}

}
