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
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.mapping.MappingInfo;
import my.dao.mapping.MappingInfoHolder;



@Table("WX_KPI_CHARTS_DATA_TB")
@View("WX_KPI_CHARTS_DATA_TB_V")
@PK({ "CATEGORY","DT","ORGID","TYPE","YEAR" })
public class KpiChartsDataTb extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiChartsDataTb INSTANCE = new KpiChartsDataTb();
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private Long orgid ;
	
	@Column(value = "type", type = ColumnType.STRING)
	private String type ;
	
	@Column(value = "dt", type = ColumnType.STRING)
	private String dt ;
	
	@Column(value = "year", type = ColumnType.STRING)
	private String year ;
	
	@Column(value = "category", type = ColumnType.STRING)
	private String category ;
	
	@Column(value = "data", type = ColumnType.NUMBER)
	private BigDecimal data ;
	
	@ReadOnly
	@Column(value = "sdate", type = ColumnType.STRING)
	private String sdate ;
	
	@ReadOnly
	@Column(value = "edate", type = ColumnType.STRING)
	private String edate ;
	
	@ReadOnly
	@Column(value = "seq", type = ColumnType.NUMBER)
	private BigDecimal seq ;
	


	public Long getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (Long orgid){
		this.orgid=orgid;
	}

	public String getType (){
		return type;
	}
		 
	public void setType (String type){
		this.type=type;
	}

	public String getDt (){
		return dt;
	}
		 
	public void setDt (String dt){
		this.dt=dt;
	}

	public String getYear (){
		return year;
	}
		 
	public void setYear (String year){
		this.year=year;
	}

	public String getCategory (){
		return category;
	}
		 
	public void setCategory (String category){
		this.category=category;
	}

	public BigDecimal getData (){
		return data;
	}
		 
	public void setData (BigDecimal data){
		this.data=data;
	}

	public String getSdate (){
		return sdate;
	}
		 
	public void setSdate (String sdate){
		this.sdate=sdate;
	}

	public String getEdate (){
		return edate;
	}
		 
	public void setEdate (String edate){
		this.edate=edate;
	}

	public BigDecimal getSeq (){
		return seq;
	}
		 
	public void setSeq (BigDecimal seq){
		this.seq=seq;
	}

}
