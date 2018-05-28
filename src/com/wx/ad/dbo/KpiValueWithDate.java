/**
** create by code gen .
**/
package com.wx.ad.dbo;

import java.util.Date;
import java.util.List;
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



@Table("KPI_VALUE")
@View("KPI_VALUE_V")
@PK({  })
public class KpiValueWithDate extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueWithDate INSTANCE = new KpiValueWithDate();
	
	@Column(value = "kpiid", type = ColumnType.NUMBER)
	private Long kpiid ;
	
	@Column(value = "kpivalue", type = ColumnType.STRING)
	private String kpivalue ;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private Long orgid ;
	
	@Column(value = "pdate", type = ColumnType.NUMBER)
	private Long pdate ;
	
	@Column(value = "planid", type = ColumnType.NUMBER)
	private Long planid ;
	
	@Column(value = "deptcode", type = ColumnType.STRING)
	private String deptcode ;
	
	@ReadOnly
	@Column(value = "deptname", type = ColumnType.STRING)
	private String deptname ;
	
	@ReadOnly
	@Column(value = "dictname", type = ColumnType.STRING)
	private String dictname ;
	
	@ReadOnly
	@Column(value = "korder", type = ColumnType.NUMBER)
	private BigDecimal korder ;
	
	@ReadOnly
	@Column(value = "day", type = ColumnType.STRING)
	private String day ;
	
	@ReadOnly
	@Column(value = "week", type = ColumnType.NUMBER)
	private Long week ;
	
	@ReadOnly
	@Column(value = "week_name", type = ColumnType.STRING)
	private String week_name ;
	
	@ReadOnly
	@Column(value = "month", type = ColumnType.NUMBER)
	private Long month ;
	
	@ReadOnly
	@Column(value = "month_name", type = ColumnType.STRING)
	private String month_name ;
	
	@ReadOnly
	@Column(value = "season", type = ColumnType.NUMBER)
	private Long season ;
	
	@ReadOnly
	@Column(value = "season_name", type = ColumnType.STRING)
	private String season_name ;
	
	@ReadOnly
	@Column(value = "year", type = ColumnType.NUMBER)
	private Long year ;
	
	@ReadOnly
	@Column(value = "year_name", type = ColumnType.STRING)
	private String year_name ;
	


	public Long getKpiid (){
		return kpiid;
	}
		 
	public void setKpiid (Long kpiid){
		this.kpiid=kpiid;
	}

	public String getKpivalue (){
		return kpivalue;
	}
		 
	public void setKpivalue (String kpivalue){
		this.kpivalue=kpivalue;
	}

	public Long getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (Long orgid){
		this.orgid=orgid;
	}

	public Long getPdate (){
		return pdate;
	}
		 
	public void setPdate (Long pdate){
		this.pdate=pdate;
	}

	public Long getPlanid (){
		return planid;
	}
		 
	public void setPlanid (Long planid){
		this.planid=planid;
	}

	public String getDeptcode (){
		return deptcode;
	}
		 
	public void setDeptcode (String deptcode){
		this.deptcode=deptcode;
	}

	public String getDictname (){
		return dictname;
	}
		 
	public void setDictname (String dictname){
		this.dictname=dictname;
	}

	public BigDecimal getKorder (){
		return korder;
	}
		 
	public void setKorder (BigDecimal korder){
		this.korder=korder;
	}

	public String getDay (){
		return day;
	}
		 
	public void setDay (String day){
		this.day=day;
	}

	public Long getWeek (){
		return week;
	}
		 
	public void setWeek (Long week){
		this.week=week;
	}

	public String getWeek_name (){
		return week_name;
	}
		 
	public void setWeek_name (String week_name){
		this.week_name=week_name;
	}

	public Long getMonth (){
		return month;
	}
		 
	public void setMonth (Long month){
		this.month=month;
	}

	public String getMonth_name (){
		return month_name;
	}
		 
	public void setMonth_name (String month_name){
		this.month_name=month_name;
	}

	public Long getSeason (){
		return season;
	}
		 
	public void setSeason (Long season){
		this.season=season;
	}

	public String getSeason_name (){
		return season_name;
	}
		 
	public void setSeason_name (String season_name){
		this.season_name=season_name;
	}

	public Long getYear (){
		return year;
	}
		 
	public void setYear (Long year){
		this.year=year;
	}

	public String getYear_name (){
		return year_name;
	}
		 
	public void setYear_name (String year_name){
		this.year_name=year_name;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	
	public List<Object> querySumValue(Integer kpiid){
		List<Object> list=null;
		
		return list;
		
	}
	
}
