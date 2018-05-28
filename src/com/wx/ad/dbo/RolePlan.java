/**
** create by code gen .
**/
package com.wx.ad.dbo;

import java.util.ArrayList;
import java.util.List;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.Id;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("WX_ROLE_PLAN")
@View("WX_ROLE_PLAN")
@PK({ "ID" })
public class RolePlan extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final RolePlan INSTANCE = new RolePlan();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private Long id ;
	
	@Column(value = "roleid", type = ColumnType.NUMBER)
	private Long roleid ;
	
	@Column(value = "planid", type = ColumnType.NUMBER)
	private Long planid ;
	


	public Long getId (){
		return id;
	}
		 
	public void setId (Long id){
		this.id=id;
	}

	public Long getRoleid (){
		return roleid;
	}
		 
	public void setRoleid (Long roleid){
		this.roleid=roleid;
	}

	public Long getPlanid (){
		return planid;
	}
		 
	public void setPlanid (Long planid){
		this.planid=planid;
	}
	
	public KpiPlan plan() {
		return KpiPlan.INSTANCE.load(planid);
	}

	public void save(List<RolePlan> roleaps, String roleid) {
		tableHelper().update("delete from wx_role_plan where roleid = ? ", roleid);

		String sql = "insert into wx_role_plan(id,roleid,planid) values(?,?,?)";

		List<Object[]> paramss = new ArrayList<Object[]>();
		if (roleaps.size() > 0) {
			for (RolePlan ra : roleaps) {
				Object[] obj = new Object[3];
				obj[0] = ra.newId();
				obj[1] = roleid;
				obj[2] = ra.getPlanid();
				paramss.add(obj);
			}
			if (paramss.size() > 0)
				tableHelper().batch(sql, paramss);
		}
	}
}
