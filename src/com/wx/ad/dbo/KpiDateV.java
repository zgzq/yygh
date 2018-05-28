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



@Table("KPI_DATE_V")
@View("KPI_DATE_V")
@PK({  })
public class KpiDateV extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiDateV INSTANCE = new KpiDateV();
	
	@Column(value = "dt", type = ColumnType.NUMBER)
	private Long dt ;
	
	@Column(value = "day", type = ColumnType.NUMBER)
	private Long day ;
	
	@Column(value = "day2", type = ColumnType.STRING)
	private String day2 ;
	
	@Column(value = "day_name", type = ColumnType.STRING)
	private String day_name ;
	
	@Column(value = "week", type = ColumnType.NUMBER)
	private Long week ;
	
	@Column(value = "week_name", type = ColumnType.STRING)
	private String week_name ;
	
	@Column(value = "month", type = ColumnType.NUMBER)
	private Long month ;
	
	@Column(value = "month_name", type = ColumnType.STRING)
	private String month_name ;
	
	@Column(value = "season", type = ColumnType.NUMBER)
	private Long season ;
	
	@Column(value = "season_name", type = ColumnType.STRING)
	private String season_name ;
	
	@Column(value = "year", type = ColumnType.NUMBER)
	private Long year ;
	
	@Column(value = "year_name", type = ColumnType.STRING)
	private String year_name ;
	
	@Column(value = "yearmonth", type = ColumnType.STRING)
	private String yearmonth ;
	


	public Long getDt (){
		return dt;
	}
		 
	public void setDt (Long dt){
		this.dt=dt;
	}

	public Long getDay (){
		return day;
	}
		 
	public void setDay (Long day){
		this.day=day;
	}

	public String getDay2 (){
		return day2;
	}
		 
	public void setDay2 (String day2){
		this.day2=day2;
	}

	public String getDay_name (){
		return day_name;
	}
		 
	public void setDay_name (String day_name){
		this.day_name=day_name;
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

	public String getYearmonth (){
		return yearmonth;
	}
		 
	public void setYearmonth (String yearmonth){
		this.yearmonth=yearmonth;
	}

	
}
