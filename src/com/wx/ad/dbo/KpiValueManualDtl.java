/**
** create by code gen .
**/
package com.wx.ad.dbo;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.Id;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("WX_KPI_VALUE_MANUAL_DTL")
@View("WX_KPI_VALUE_MANUAL_DTL_V")
@PK({ "ID" })
public class KpiValueManualDtl extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueManualDtl INSTANCE = new KpiValueManualDtl();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private Long id ;
	
	@Column(value = "mid", type = ColumnType.NUMBER)
	private Long mid ;
	
	@Column(value = "kpiid", type = ColumnType.NUMBER)
	private Long kpiid ;
	
	@Column(value = "kpivalue", type = ColumnType.NUMBER)
	
	private String kpivalue ;
	@ReadOnly
	@Column(value = "dictname", type = ColumnType.STRING)
	private String dictname ;
	
	@ReadOnly
	@Column(value = "dictcode", type = ColumnType.NUMBER)
	private Long dictcode ;
	


	public Long getId (){
		return id;
	}
		 
	 
	public void setId (Long id){
		this.id=id;
	}
	
	public void setMid (Long mid){
		this.mid=mid;
	}

	public Long getMid (){
		return mid;
	}
	
	
	public void setKpiid (Long kpiid){
		this.kpiid=kpiid;
	}

	public Long getKpiid (){
		return kpiid;
	}
	
	public String getKpivalue (){
		return kpivalue;
	}
		 
	public void setKpivalue (String kpivalue){
		this.kpivalue=kpivalue;
	}

	public String getDictname (){
		return dictname;
	}
		 
	public void setDictname (String dictname){
		this.dictname=dictname;
	}
	
	public Long getDictcode (){
		return dictcode;
	}
		 
	public void setDictcode (Long dictcode){
		this.dictcode=dictcode;
	}



	public String getHasRight() {
		return hasRight;
	}

	public void setHasRight(String hasRight) {
		this.hasRight = hasRight;
	}





	private String hasRight;
	

}
