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



@Table("wx_user_plan")
@View("wx_user_plan")
@PK({ "ID" })
public class UserPlan extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final UserPlan INSTANCE = new UserPlan();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private Long id ;
	
	@Column(value = "userid", type = ColumnType.NUMBER)
	private Long userid ;
	
	@Column(value = "planid", type = ColumnType.NUMBER)
	private Long planid ;
	


	public Long getId (){
		return id;
	}
		 
	public void setId (Long id){
		this.id=id;
	}


	public Long getPlanid (){
		return planid;
	}
		 
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public void setPlanid (Long planid){
		this.planid=planid;
	}
	
	public KpiPlan plan() {
		return KpiPlan.INSTANCE.load(planid);
	}

	public void save(List<UserPlan> roleaps, String userid) {
		tableHelper().update("delete from wx_user_plan where userid = ? ", userid);

		String sql = "insert into wx_user_plan(id,userid,planid) values(?,?,?)";

		List<Object[]> paramss = new ArrayList<Object[]>();
		if (roleaps.size() > 0) {
			for (UserPlan ra : roleaps) {
				Object[] obj = new Object[3];
				obj[0] = ra.newId();
				obj[1] = userid;
				obj[2] = ra.getPlanid();
				paramss.add(obj);
			}
			if (paramss.size() > 0)
				tableHelper().batch(sql, paramss);
		}
	}
}
