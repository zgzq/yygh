/**
** create by code gen .
**/
package com.wx.cp.model;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("KPI_VALUE")
@View("KPI_VALUE_V")
@PK({  })
public class KpidictValue extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpidictValue INSTANCE = new KpidictValue();
	
	@Column(value = "kpiid", type = ColumnType.NUMBER)
	private Long kpiid ;
	
	@Column(value = "kpivalue", type = ColumnType.STRING)
	private String kpivalue ;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private Long orgid ;
	
	@Column(value = "pdate", type = ColumnType.NUMBER)
	private String pdate ;
	
	@Column(value = "planid", type = ColumnType.NUMBER)
	private String planid ;
	
	@Column(value = "deptcode", type = ColumnType.STRING)
	private String deptcode ;
	
	@ReadOnly
	@Column(value = "dictname", type = ColumnType.STRING)
	private String dictname ;
	
	@ReadOnly
	@Column(value = "korder", type = ColumnType.NUMBER)
	private String korder ;
	
	


	public KpidictValue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KpidictValue(String kpivalue, String pdate, String planid, String dictname) {
		super();
		this.kpivalue = kpivalue;
		this.pdate = pdate;
		this.planid = planid;
		this.dictname = dictname;
	}

	public String getKorder() {
		return korder;
	}

	public void setKorder(String korder) {
		this.korder = korder;
	}

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

	public String getPdate (){
		return pdate;
	}
		 
	public void setPdate (String pdate){
		this.pdate=pdate;
	}

	public String getPlanid (){
		return planid;
	}
		 
	public void setPlanid (String planid){
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

}
