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



@Table("WX_KPI_PLAN_DTL")
@View("WX_KPI_PLAN_DTL_V")
@PK({ "ID" })
public class KpiPlanDtl extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiPlanDtl INSTANCE = new KpiPlanDtl();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private Long id ;
	
	@Column(value = "planid", type = ColumnType.NUMBER)
	private Long planid ;
	
	@Column(value = "kpiid", type = ColumnType.NUMBER)
	private Long kpiid ;
	
	@Column(value = "op", type = ColumnType.NUMBER)
	private Long op ;
	
	@Column(value = "filter", type = ColumnType.STRING)
	private String filter ;
	
	@Column(value = "op1", type = ColumnType.NUMBER)
	private Long op1 ;
	
	@Column(value = "filter1", type = ColumnType.STRING)
	private String filter1 ;
	
	@ReadOnly
	@Column(value = "dictname", type = ColumnType.STRING)
	private String dictname ;
	
	@ReadOnly
	@Column(value = "dictcode", type = ColumnType.NUMBER)
	private Long dictcode ;
	
	@ReadOnly
	@Column(value = "dictinfo", type = ColumnType.STRING)
	private String dictinfo ;
	
	@Column(value = "korder", type = ColumnType.NUMBER)
	private Long korder ;
	


	public Long getKorder() {
		return korder;
	}

	public void setKorder(Long korder) {
		this.korder = korder;
	}

	public Long getId (){
		return id;
	}
		 
	public void setId (Long id){
		this.id=id;
	}

	public Long getPlanid (){
		return planid;
	}
		 
	public void setPlanid (Long planid){
		this.planid=planid;
	}

	public Long getKpiid (){
		return kpiid;
	}
		 
	public void setKpiid (Long kpiid){
		this.kpiid=kpiid;
	}

	public Long getOp (){
		return op;
	}
		 
	public void setOp (Long op){
		this.op=op;
	}

	public String getFilter (){
		return filter;
	}
		 
	public void setFilter (String filter){
		this.filter=filter;
	}

	public Long getOp1 (){
		return op1;
	}
		 
	public void setOp1 (Long op1){
		this.op1=op1;
	}

	public String getFilter1 (){
		return filter1;
	}
		 
	public void setFilter1 (String filter1){
		this.filter1=filter1;
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

	public String getDictinfo (){
		return dictinfo;
	}
		 
	public void setDictinfo (String dictinfo){
		this.dictinfo=dictinfo;
	}

}
