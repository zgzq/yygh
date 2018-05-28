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



@Table("KPI_VALUE_MP_HZ4")
@View("KPI_VALUE_MP_HZ4_V")
@PK({  })
public class KpiValueMpHz4 extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueMpHz4 INSTANCE = new KpiValueMpHz4();
	
	@Column(value = "psdate", type = ColumnType.NUMBER)
	private BigDecimal psdate ;
	
	@Column(value = "pedate", type = ColumnType.NUMBER)
	private BigDecimal pedate ;
	
	@Column(value = "userid", type = ColumnType.STRING)
	private String userid ;
	
	@Column(value = "kpiname", type = ColumnType.STRING)
	private String kpiname ;
	
	@Column(value = "orgid6ref", type = ColumnType.NUMBER)
	private BigDecimal orgid6ref ;
	
	@Column(value = "orgid6val", type = ColumnType.NUMBER)
	private BigDecimal orgid6val ;
	
	@Column(value = "orgid8ref", type = ColumnType.NUMBER)
	private BigDecimal orgid8ref ;
	
	@Column(value = "orgid8val", type = ColumnType.NUMBER)
	private BigDecimal orgid8val ;
	
	@Column(value = "orgid9ref", type = ColumnType.NUMBER)
	private BigDecimal orgid9ref ;
	
	@Column(value = "orgid9val", type = ColumnType.NUMBER)
	private BigDecimal orgid9val ;
	
	@Column(value = "seq", type = ColumnType.NUMBER)
	private BigDecimal seq ;
	
	@ReadOnly
	@Column(value = "orgid6perval", type = ColumnType.NUMBER)
	private BigDecimal orgid6perval ;
	
	@ReadOnly
	@Column(value = "orgid6per", type = ColumnType.STRING)
	private String orgid6per ;
	
	@ReadOnly
	@Column(value = "orgid8perval", type = ColumnType.NUMBER)
	private BigDecimal orgid8perval ;
	
	@ReadOnly
	@Column(value = "orgid8per", type = ColumnType.STRING)
	private String orgid8per ;
	
	@ReadOnly
	@Column(value = "orgid9perval", type = ColumnType.NUMBER)
	private BigDecimal orgid9perval ;
	
	@ReadOnly
	@Column(value = "orgid9per", type = ColumnType.STRING)
	private String orgid9per ;
	


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

	public BigDecimal getOrgid6ref (){
		return orgid6ref;
	}
		 
	public void setOrgid6ref (BigDecimal orgid6ref){
		this.orgid6ref=orgid6ref;
	}

	public BigDecimal getOrgid6val (){
		return orgid6val;
	}
		 
	public void setOrgid6val (BigDecimal orgid6val){
		this.orgid6val=orgid6val;
	}

	public BigDecimal getOrgid8ref (){
		return orgid8ref;
	}
		 
	public void setOrgid8ref (BigDecimal orgid8ref){
		this.orgid8ref=orgid8ref;
	}

	public BigDecimal getOrgid8val (){
		return orgid8val;
	}
		 
	public void setOrgid8val (BigDecimal orgid8val){
		this.orgid8val=orgid8val;
	}

	public BigDecimal getOrgid9ref (){
		return orgid9ref;
	}
		 
	public void setOrgid9ref (BigDecimal orgid9ref){
		this.orgid9ref=orgid9ref;
	}

	public BigDecimal getOrgid9val (){
		return orgid9val;
	}
		 
	public void setOrgid9val (BigDecimal orgid9val){
		this.orgid9val=orgid9val;
	}

	public BigDecimal getSeq (){
		return seq;
	}
		 
	public void setSeq (BigDecimal seq){
		this.seq=seq;
	}

	public BigDecimal getOrgid6perval (){
		return orgid6perval;
	}
		 
	public void setOrgid6perval (BigDecimal orgid6perval){
		this.orgid6perval=orgid6perval;
	}

	public String getOrgid6per (){
		return orgid6per;
	}
		 
	public void setOrgid6per (String orgid6per){
		this.orgid6per=orgid6per;
	}

	public BigDecimal getOrgid8perval (){
		return orgid8perval;
	}
		 
	public void setOrgid8perval (BigDecimal orgid8perval){
		this.orgid8perval=orgid8perval;
	}

	public String getOrgid8per (){
		return orgid8per;
	}
		 
	public void setOrgid8per (String orgid8per){
		this.orgid8per=orgid8per;
	}

	public BigDecimal getOrgid9perval (){
		return orgid9perval;
	}
		 
	public void setOrgid9perval (BigDecimal orgid9perval){
		this.orgid9perval=orgid9perval;
	}

	public String getOrgid9per (){
		return orgid9per;
	}
		 
	public void setOrgid9per (String orgid9per){
		this.orgid9per=orgid9per;
	}

}
