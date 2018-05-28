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



@Table("KPI_VALUE_MONTH_V")
@View("KPI_VALUE_MONTH_V")
@PK({  })
public class KpiValueMonthV extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueMonthV INSTANCE = new KpiValueMonthV();
	
	@Column(value = "month", type = ColumnType.STRING)
	private String month ;
	
	@Column(value = "planid", type = ColumnType.NUMBER)
	private Long planid ;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private Long orgid ;
	


	public String getMonth (){
		return month;
	}
		 
	public void setMonth (String month){
		this.month=month;
	}

	public Long getPlanid (){
		return planid;
	}
		 
	public void setPlanid (Long planid){
		this.planid=planid;
	}

	public Long getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (Long orgid){
		this.orgid=orgid;
	}

}
