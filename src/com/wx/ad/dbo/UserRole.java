package com.wx.ad.dbo;

import java.util.ArrayList;
import java.util.List;

import my.dao.annotation.Column;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.po.BasePo;

@Table("SYS_USER_ROLE")
@View("SYS_USER_ROLE")
@PK({ "ROLEID", "USERID" })
public class UserRole extends BasePo {

	/**
* 
*/

	private static final long serialVersionUID = 1L;

	public static final UserRole INSTANCE = new UserRole();

	@Column(value = "roleid", type = ColumnType.STRING)
	private String roleid;

	@Column(value = "userid", type = ColumnType.STRING)
	private String userid;

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	private void deleteByUserid(String userid){
		tableHelper().update("delete from sys_USER_ROLE where userid = ? ", userid);
	}
	
	public void save(List<String> roleids,String userid){
		deleteByUserid(userid);
		
		if(roleids==null||roleids.size()==0){
			return ;
		}
		 
		String sql = "insert into sys_USER_ROLE(roleid,userid) values(?,?)";
		
		List<Object[]> paramss=new ArrayList<Object[]>(roleids.size());
		
		for(String roleid:roleids){
			Object[] obj = new Object[2];
			obj[0]=roleid;
			obj[1]=userid;
			paramss.add(obj);
		}
		
		tableHelper().batch(sql, paramss);
	}
}