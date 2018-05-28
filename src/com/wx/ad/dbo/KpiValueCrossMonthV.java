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



@Table("KPI_VALUE_CROSS_MONTH_V")
@View("KPI_VALUE_CROSS_MONTH_V")
@PK({  })
public class KpiValueCrossMonthV extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueCrossMonthV INSTANCE = new KpiValueCrossMonthV();
	
	@Column(value = "rptname", type = ColumnType.STRING)
	private String rptname ;
	
	@Column(value = "month", type = ColumnType.STRING)
	private String month ;
	
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

	public String getMonth (){
		return month;
	}
		 
	public void setMonth (String month){
		this.month=month;
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
