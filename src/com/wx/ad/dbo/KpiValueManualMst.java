/**
** create by code gen .
**/
package com.wx.ad.dbo;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("WX_KPI_VALUE_MANUAL_MST")
@View("WX_KPI_VALUE_MANUAL_MST")
@PK({ "ID" })
public class KpiValueManualMst extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueManualMst INSTANCE = new KpiValueManualMst();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private Long id ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "valuedate", type = ColumnType.DATE)
	private String valuedate ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "filldate", type = ColumnType.DATE)
	private String filldate ;
	
	@Column(value = "userid", type = ColumnType.NUMBER)
	private String userid ;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private String orgid ;
	


	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public Long getId (){
		return id;
	}
		 
	public void setId (Long id){
		this.id=id;
	}
	public void setUserid (String userid){
		this.userid=userid;
	}
	
	public String getUserid (){
		return userid;
	}
	
	public void setValuedate (String valuedate){
		this.valuedate=valuedate;
	}
	
	public String getValuedate (){
		return valuedate;
	}
	public void setFilldate (String filldate){
		this.filldate=filldate;
	}
	
	public String getFilldate (){
		return filldate;
	}



	public String getHasRight() {
		return hasRight;
	}

	public void setHasRight(String hasRight) {
		this.hasRight = hasRight;
	}





	private String hasRight;
	

}
