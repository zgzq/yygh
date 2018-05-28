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



@Table("KPI_VALUE_MP_CP_ROLE_V")
@View("KPI_VALUE_MP_CP_ROLE_V")
@PK({  })
public class KpiValueMpCpRoleV extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueMpCpRoleV INSTANCE = new KpiValueMpCpRoleV();
	
	@Column(value = "id", type = ColumnType.STRING)
	private String id ;
	
	@Column(value = "realname", type = ColumnType.STRING)
	private String realname ;
	
	@Column(value = "rolename", type = ColumnType.STRING)
	private String rolename ;
	
	@Column(value = "menuid", type = ColumnType.STRING)
	private String menuid ;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private Long orgid ;
	
	@Column(value = "ss", type = ColumnType.NUMBER)
	private Long ss ;

	public String getId (){
		return id;
	}
		 
	public void setId (String id){
		this.id=id;
	}

	public String getRealname (){
		return realname;
	}
		 
	public void setRealname (String realname){
		this.realname=realname;
	}

	public String getRolename (){
		return rolename;
	}
		 
	public void setRolename (String rolename){
		this.rolename=rolename;
	}

	public String getMenuid (){
		return menuid;
	}
		 
	public void setMenuid (String menuid){
		this.menuid=menuid;
	}

	public Long getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (Long orgid){
		this.orgid=orgid;
	}

	public Long getSs() {
		return ss;
	}

	public void setSs(Long ss) {
		this.ss = ss;
	}

}
