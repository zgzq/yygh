/**
** create by code gen .
**/
package com.wx.ad.dbo;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.Id;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("WX_KPI_PLAN")
@View("WX_KPI_PLAN_v")
@PK({ "ID" })
public class KpiPlan extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiPlan INSTANCE = new KpiPlan();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private Long id ;
	
	@Column(value = "planname", type = ColumnType.STRING)
	private String planname ;
	
	@Column(value = "planinfo", type = ColumnType.STRING)
	private String planinfo ;
	
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private String orgid ;
	
	@Column(value = "org_pic", type = ColumnType.NUMBER)
	private String org_pic ;
	

	public String getOrg_pic() {
		return org_pic;
	}

	public void setOrg_pic(String org_pic) {
		this.org_pic = org_pic;
	}

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

	public String getPlanname (){
		return planname;
	}
		 
	public void setPlanname (String planname){
		this.planname=planname;
	}

	public String getPlaninfo (){
		return planinfo;
	}
		 
	public void setPlaninfo (String planinfo){
		this.planinfo=planinfo;
	}
	
	



	public String getHasRight() {
		return hasRight;
	}

	public void setHasRight(String hasRight) {
		this.hasRight = hasRight;
	}





	private String hasRight;
	

}
