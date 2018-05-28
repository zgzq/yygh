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



@Table("WX_KPI_CHARTS_V")
@View("WX_KPI_CHARTS_V")
@PK({  })
public class KpiCharts extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiCharts INSTANCE = new KpiCharts();
	
	@Column(value = "id", type = ColumnType.STRING)
	private String id ;
	
	@Column(value = "chartname", type = ColumnType.STRING)
	private String chartname ;
	
	@Column(value = "url", type = ColumnType.STRING)
	private String url ;
	
	@Column(value = "memo", type = ColumnType.STRING)
	private String memo ;
	


	public String getId (){
		return id;
	}
		 
	public void setId (String id){
		this.id=id;
	}

	public String getChartname (){
		return chartname;
	}
		 
	public void setChartname (String chartname){
		this.chartname=chartname;
	}

	public String getUrl (){
		return url;
	}
		 
	public void setUrl (String url){
		this.url=url;
	}

	public String getMemo (){
		return memo;
	}
		 
	public void setMemo (String memo){
		this.memo=memo;
	}

}
