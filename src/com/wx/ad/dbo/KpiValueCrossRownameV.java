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



@Table("KPI_VALUE_CROSS_ROWNAME_V")
@View("KPI_VALUE_CROSS_ROWNAME_V")
@PK({  })
public class KpiValueCrossRownameV extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueCrossRownameV INSTANCE = new KpiValueCrossRownameV();
	
	@Column(value = "rptname", type = ColumnType.STRING)
	private String rptname ;
	
	@Column(value = "rowname", type = ColumnType.STRING)
	private String rowname ;
	
	@Column(value = "psdate", type = ColumnType.NUMBER)
	private Long psdate ;
	
	@Column(value = "pedate", type = ColumnType.NUMBER)
	private Long pedate ;
	
	@Column(value = "userid", type = ColumnType.STRING)
	private String userid ;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private Long orgid ;
	


	public String getRptname (){
		return rptname;
	}
		 
	public void setRptname (String rptname){
		this.rptname=rptname;
	}

	public String getRowname (){
		return rowname;
	}
		 
	public void setRowname (String rowname){
		this.rowname=rowname;
	}

	public Long getPsdate (){
		return psdate;
	}
		 
	public void setPsdate (Long psdate){
		this.psdate=psdate;
	}

	public Long getPedate (){
		return pedate;
	}
		 
	public void setPedate (Long pedate){
		this.pedate=pedate;
	}

	public String getUserid (){
		return userid;
	}
		 
	public void setUserid (String userid){
		this.userid=userid;
	}

	public Long getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (Long orgid){
		this.orgid=orgid;
	}

}
