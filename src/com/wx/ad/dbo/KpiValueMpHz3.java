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



@Table("KPI_VALUE_MP_HZ3")
@View("KPI_VALUE_MP_HZ3_V")
@PK({  })
public class KpiValueMpHz3 extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueMpHz3 INSTANCE = new KpiValueMpHz3();
	
	@Column(value = "psdate", type = ColumnType.NUMBER)
	private BigDecimal psdate ;
	
	@Column(value = "pedate", type = ColumnType.NUMBER)
	private BigDecimal pedate ;
	
	@Column(value = "userid", type = ColumnType.STRING)
	private String userid ;
	
	@Column(value = "kpiname", type = ColumnType.STRING)
	private String kpiname ;
	
	@Column(value = "orgid1ref", type = ColumnType.NUMBER)
	private BigDecimal orgid1ref ;
	
	@Column(value = "orgid1val", type = ColumnType.NUMBER)
	private BigDecimal orgid1val ;
	
	@Column(value = "orgid2ref", type = ColumnType.NUMBER)
	private BigDecimal orgid2ref ;
	
	@Column(value = "orgid2val", type = ColumnType.NUMBER)
	private BigDecimal orgid2val ;
	
	@Column(value = "orgid3ref", type = ColumnType.NUMBER)
	private BigDecimal orgid3ref ;
	
	@Column(value = "orgid3val", type = ColumnType.NUMBER)
	private BigDecimal orgid3val ;
	
	@Column(value = "orgid5ref", type = ColumnType.NUMBER)
	private BigDecimal orgid5ref ;
	
	@Column(value = "orgid5val", type = ColumnType.NUMBER)
	private BigDecimal orgid5val ;
	
	@Column(value = "orgid7ref", type = ColumnType.NUMBER)
	private BigDecimal orgid7ref ;
	
	@Column(value = "orgid7val", type = ColumnType.NUMBER)
	private BigDecimal orgid7val ;
	
	@Column(value = "kpiname4", type = ColumnType.STRING)
	private String kpiname4 ;
	
	@Column(value = "orgid4ref", type = ColumnType.NUMBER)
	private BigDecimal orgid4ref ;
	
	@Column(value = "orgid4val", type = ColumnType.NUMBER)
	private BigDecimal orgid4val ;
	
	@Column(value = "seq", type = ColumnType.NUMBER)
	private BigDecimal seq ;
	
	@ReadOnly
	@Column(value = "orgid1perval", type = ColumnType.NUMBER)
	private BigDecimal orgid1perval ;
	
	@ReadOnly
	@Column(value = "orgid1per", type = ColumnType.STRING)
	private String orgid1per ;
	
	@ReadOnly
	@Column(value = "orgid2perval", type = ColumnType.NUMBER)
	private BigDecimal orgid2perval ;
	
	@ReadOnly
	@Column(value = "orgid2per", type = ColumnType.STRING)
	private String orgid2per ;
	
	@ReadOnly
	@Column(value = "orgid3perval", type = ColumnType.NUMBER)
	private BigDecimal orgid3perval ;
	
	@ReadOnly
	@Column(value = "orgid3per", type = ColumnType.STRING)
	private String orgid3per ;
	
	@ReadOnly
	@Column(value = "orgid5perval", type = ColumnType.NUMBER)
	private BigDecimal orgid5perval ;
	
	@ReadOnly
	@Column(value = "orgid5per", type = ColumnType.STRING)
	private String orgid5per ;
	
	@ReadOnly
	@Column(value = "orgid7perval", type = ColumnType.NUMBER)
	private BigDecimal orgid7perval ;
	
	@ReadOnly
	@Column(value = "orgid7per", type = ColumnType.STRING)
	private String orgid7per ;
	
	@ReadOnly
	@Column(value = "orgid4perval", type = ColumnType.NUMBER)
	private BigDecimal orgid4perval ;
	
	@ReadOnly
	@Column(value = "orgid4per", type = ColumnType.STRING)
	private String orgid4per ;
	


	public BigDecimal getPsdate (){
		return psdate;
	}
		 
	public void setPsdate (BigDecimal psdate){
		this.psdate=psdate;
	}

	public BigDecimal getPedate (){
		return pedate;
	}
		 
	public void setPedate (BigDecimal pedate){
		this.pedate=pedate;
	}

	public String getUserid (){
		return userid;
	}
		 
	public void setUserid (String userid){
		this.userid=userid;
	}

	public String getKpiname (){
		return kpiname;
	}
		 
	public void setKpiname (String kpiname){
		this.kpiname=kpiname;
	}

	public BigDecimal getOrgid1ref (){
		return orgid1ref;
	}
		 
	public void setOrgid1ref (BigDecimal orgid1ref){
		this.orgid1ref=orgid1ref;
	}

	public BigDecimal getOrgid1val (){
		return orgid1val;
	}
		 
	public void setOrgid1val (BigDecimal orgid1val){
		this.orgid1val=orgid1val;
	}

	public BigDecimal getOrgid2ref (){
		return orgid2ref;
	}
		 
	public void setOrgid2ref (BigDecimal orgid2ref){
		this.orgid2ref=orgid2ref;
	}

	public BigDecimal getOrgid2val (){
		return orgid2val;
	}
		 
	public void setOrgid2val (BigDecimal orgid2val){
		this.orgid2val=orgid2val;
	}

	public BigDecimal getOrgid3ref (){
		return orgid3ref;
	}
		 
	public void setOrgid3ref (BigDecimal orgid3ref){
		this.orgid3ref=orgid3ref;
	}

	public BigDecimal getOrgid3val (){
		return orgid3val;
	}
		 
	public void setOrgid3val (BigDecimal orgid3val){
		this.orgid3val=orgid3val;
	}

	public BigDecimal getOrgid5ref (){
		return orgid5ref;
	}
		 
	public void setOrgid5ref (BigDecimal orgid5ref){
		this.orgid5ref=orgid5ref;
	}

	public BigDecimal getOrgid5val (){
		return orgid5val;
	}
		 
	public void setOrgid5val (BigDecimal orgid5val){
		this.orgid5val=orgid5val;
	}

	public BigDecimal getOrgid7ref (){
		return orgid7ref;
	}
		 
	public void setOrgid7ref (BigDecimal orgid7ref){
		this.orgid7ref=orgid7ref;
	}

	public BigDecimal getOrgid7val (){
		return orgid7val;
	}
		 
	public void setOrgid7val (BigDecimal orgid7val){
		this.orgid7val=orgid7val;
	}

	public String getKpiname4 (){
		return kpiname4;
	}
		 
	public void setKpiname4 (String kpiname4){
		this.kpiname4=kpiname4;
	}

	public BigDecimal getOrgid4ref (){
		return orgid4ref;
	}
		 
	public void setOrgid4ref (BigDecimal orgid4ref){
		this.orgid4ref=orgid4ref;
	}

	public BigDecimal getOrgid4val (){
		return orgid4val;
	}
		 
	public void setOrgid4val (BigDecimal orgid4val){
		this.orgid4val=orgid4val;
	}

	public BigDecimal getSeq (){
		return seq;
	}
		 
	public void setSeq (BigDecimal seq){
		this.seq=seq;
	}

	public BigDecimal getOrgid1perval (){
		return orgid1perval;
	}
		 
	public void setOrgid1perval (BigDecimal orgid1perval){
		this.orgid1perval=orgid1perval;
	}

	public String getOrgid1per (){
		return orgid1per;
	}
		 
	public void setOrgid1per (String orgid1per){
		this.orgid1per=orgid1per;
	}

	public BigDecimal getOrgid2perval (){
		return orgid2perval;
	}
		 
	public void setOrgid2perval (BigDecimal orgid2perval){
		this.orgid2perval=orgid2perval;
	}

	public String getOrgid2per (){
		return orgid2per;
	}
		 
	public void setOrgid2per (String orgid2per){
		this.orgid2per=orgid2per;
	}

	public BigDecimal getOrgid3perval (){
		return orgid3perval;
	}
		 
	public void setOrgid3perval (BigDecimal orgid3perval){
		this.orgid3perval=orgid3perval;
	}

	public String getOrgid3per (){
		return orgid3per;
	}
		 
	public void setOrgid3per (String orgid3per){
		this.orgid3per=orgid3per;
	}

	public BigDecimal getOrgid5perval (){
		return orgid5perval;
	}
		 
	public void setOrgid5perval (BigDecimal orgid5perval){
		this.orgid5perval=orgid5perval;
	}

	public String getOrgid5per (){
		return orgid5per;
	}
		 
	public void setOrgid5per (String orgid5per){
		this.orgid5per=orgid5per;
	}

	public BigDecimal getOrgid7perval (){
		return orgid7perval;
	}
		 
	public void setOrgid7perval (BigDecimal orgid7perval){
		this.orgid7perval=orgid7perval;
	}

	public String getOrgid7per (){
		return orgid7per;
	}
		 
	public void setOrgid7per (String orgid7per){
		this.orgid7per=orgid7per;
	}

	public BigDecimal getOrgid4perval (){
		return orgid4perval;
	}
		 
	public void setOrgid4perval (BigDecimal orgid4perval){
		this.orgid4perval=orgid4perval;
	}

	public String getOrgid4per (){
		return orgid4per;
	}
		 
	public void setOrgid4per (String orgid4per){
		this.orgid4per=orgid4per;
	}

}
