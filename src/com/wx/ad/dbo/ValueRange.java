/**
** create by code gen .
**/
package com.wx.ad.dbo;

import java.math.BigDecimal;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("KPI_VALUE_RANGE")
@View("KPI_VALUE_RANGE_V")
@PK({  })
public class ValueRange extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final ValueRange INSTANCE = new ValueRange();
	
	@Column(value = "kpiid", type = ColumnType.NUMBER)
	private Long kpiid ;
	
	@Column(value = "kpivalue", type = ColumnType.STRING)
	private String kpivalue ;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private Long orgid ;
	
	@Column(value = "psdate", type = ColumnType.NUMBER)
	private Long psdate ;
	
	@Column(value = "planid", type = ColumnType.NUMBER)
	private Long planid ;
	
	@Column(value = "deptcode", type = ColumnType.STRING)
	private String deptcode ;
	
	@Column(value = "userid", type = ColumnType.STRING)
	private String userid ;
	
	@Column(value = "qrykey", type = ColumnType.STRING)
	private String qrykey ;
	
	@Column(value = "pedate", type = ColumnType.NUMBER)
	private Long pedate ;
	
	@ReadOnly
	@Column(value = "dictname", type = ColumnType.STRING)
	private String dictname ;
	
	@ReadOnly
	@Column(value = "korder", type = ColumnType.NUMBER)
	private BigDecimal korder ;
	


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

	public Long getPsdate (){
		return psdate;
	}
		 
	public void setPsdate (Long psdate){
		this.psdate=psdate;
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

	public String getUserid (){
		return userid;
	}
		 
	public void setUserid (String userid){
		this.userid=userid;
	}

	public String getQrykey (){
		return qrykey;
	}
		 
	public void setQrykey (String qrykey){
		this.qrykey=qrykey;
	}

	public Long getPedate (){
		return pedate;
	}
		 
	public void setPedate (Long pedate){
		this.pedate=pedate;
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

}
