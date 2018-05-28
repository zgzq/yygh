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



@Table("WX_KPI_VALUE_DC_DEPT_2")
@View("WX_KPI_VALUE_DC_DEPT_2_V")
@PK({  })
public class KpiValueDcDept2 extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueDcDept2 INSTANCE = new KpiValueDcDept2();
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private Long orgid ;
	
	@Column(value = "userid", type = ColumnType.STRING)
	private String userid ;
	
	@Column(value = "psdate", type = ColumnType.NUMBER)
	private Long psdate ;
	
	@Column(value = "pedate", type = ColumnType.NUMBER)
	private Long pedate ;
	
	@Column(value = "科室编号", type = ColumnType.NUMBER)
	private Long 科室编号 ;
	
	@Column(value = "科室名称", type = ColumnType.STRING)
	private String 科室名称 ;
	
	@Column(value = "门诊量", type = ColumnType.NUMBER)
	private BigDecimal 门诊量 ;
	
	@Column(value = "门诊收入", type = ColumnType.NUMBER)
	private BigDecimal 门诊收入 ;
	
	@Column(value = "入院量", type = ColumnType.NUMBER)
	private BigDecimal 入院量 ;
	
	@Column(value = "出院量", type = ColumnType.NUMBER)
	private BigDecimal 出院量 ;
	
	@Column(value = "在院量", type = ColumnType.NUMBER)
	private BigDecimal 在院量 ;
	
	@Column(value = "住院收入", type = ColumnType.NUMBER)
	private BigDecimal 住院收入 ;
	
	@Column(value = "总收入", type = ColumnType.NUMBER)
	private BigDecimal 总收入 ;
	


	public Long getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (Long orgid){
		this.orgid=orgid;
	}

	public String getUserid (){
		return userid;
	}
		 
	public void setUserid (String userid){
		this.userid=userid;
	}

	public Long getPsdate (){
		return psdate;
	}
		 
	public void setPsdate (Long psdate){
		this.psdate=psdate;
	}

	public Long getPedate (){
		return pedate;
	}
		 
	public void setPedate (Long pedate){
		this.pedate=pedate;
	}

	public Long get科室编号 (){
		return 科室编号;
	}
		 
	public void set科室编号 (Long 科室编号){
		this.科室编号=科室编号;
	}

	public String get科室名称 (){
		return 科室名称;
	}
		 
	public void set科室名称 (String 科室名称){
		this.科室名称=科室名称;
	}

	public BigDecimal get门诊量 (){
		return 门诊量;
	}
		 
	public void set门诊量 (BigDecimal 门诊量){
		this.门诊量=门诊量;
	}

	public BigDecimal get门诊收入 (){
		return 门诊收入;
	}
		 
	public void set门诊收入 (BigDecimal 门诊收入){
		this.门诊收入=门诊收入;
	}

	public BigDecimal get入院量 (){
		return 入院量;
	}
		 
	public void set入院量 (BigDecimal 入院量){
		this.入院量=入院量;
	}

	public BigDecimal get出院量 (){
		return 出院量;
	}
		 
	public void set出院量 (BigDecimal 出院量){
		this.出院量=出院量;
	}

	public BigDecimal get在院量 (){
		return 在院量;
	}
		 
	public void set在院量 (BigDecimal 在院量){
		this.在院量=在院量;
	}

	public BigDecimal get住院收入 (){
		return 住院收入;
	}
		 
	public void set住院收入 (BigDecimal 住院收入){
		this.住院收入=住院收入;
	}

	public BigDecimal get总收入 (){
		return 总收入;
	}
		 
	public void set总收入 (BigDecimal 总收入){
		this.总收入=总收入;
	}

}
