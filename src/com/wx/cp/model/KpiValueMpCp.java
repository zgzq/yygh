/**
** create by code gen .
**/
package com.wx.cp.model;

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



@Table("KPI_VALUE_MP_CP")
@View("KPI_VALUE_MP_CP_V")
@PK({  })
public class KpiValueMpCp extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueMpCp INSTANCE = new KpiValueMpCp();
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private Long orgid ;
	
	@Column(value = "org_name", type = ColumnType.STRING)
	private String org_name ;
	
	@Column(value = "kpiname", type = ColumnType.STRING)
	private String kpiname ;
	
	@Column(value = "refavg", type = ColumnType.NUMBER)
	private BigDecimal refavg ;
	
	@Column(value = "kpivalue", type = ColumnType.NUMBER)
	private BigDecimal kpivalue ;
	
	@Column(value = "pdate", type = ColumnType.NUMBER)
	private Long pdate ;
	
	@Column(value = "seq", type = ColumnType.NUMBER)
	private Long seq ;

	public Long getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (Long orgid){
		this.orgid=orgid;
	}

	public String getOrg_name (){
		return org_name;
	}
		 
	public void setOrg_name (String org_name){
		this.org_name=org_name;
	}

	public String getKpiname (){
		return kpiname;
	}
		 
	public void setKpiname (String kpiname){
		this.kpiname=kpiname;
	}

	public BigDecimal getRefavg (){
		return refavg;
	}
		 
	public void setRefavg (BigDecimal refavg){
		this.refavg=refavg;
	}

	public BigDecimal getKpivalue (){
		return kpivalue;
	}
		 
	public void setKpivalue (BigDecimal kpivalue){
		this.kpivalue=kpivalue;
	}

	public Long getPdate (){
		return pdate;
	}
		 
	public void setPdate (Long pdate){
		this.pdate=pdate;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

}
