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
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.mapping.MappingInfo;
import my.dao.mapping.MappingInfoHolder;



@Table("KPI_VALUE_MP_HZ2")
@View("KPI_VALUE_MP_HZ2_V")
@PK({  })
public class KpiValueMpHz2 extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueMpHz2 INSTANCE = new KpiValueMpHz2();
	
	@Column(value = "pdate", type = ColumnType.NUMBER)
	private BigDecimal pdate ;
	
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
	


	public BigDecimal getPdate (){
		return pdate;
	}
		 
	public void setPdate (BigDecimal pdate){
		this.pdate=pdate;
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

}
