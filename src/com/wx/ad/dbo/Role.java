package com.wx.ad.dbo;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.po.BasePo;

@Table("SYS_ROLE")
@View("SYS_ROLE")
@PK({ "ROLEID" })
public class Role extends BasePo {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	public static final Role INSTANCE = new Role();
	
	@Name
	@Column(value = "roleid", type = ColumnType.STRING)
	private String roleid;

	@Column(value = "rolename", type = ColumnType.STRING)
	private String rolename;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "ct", type = ColumnType.DATE)
	private String ct;

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

 

	/**获得角色对应所有权限
	 * @return
	 */
	public List<RoleAp> roleAps() {
		return  RoleAp.INSTANCE.query("roleid = ? ",
				this.roleid);
	}
	
	/**角色对应的菜单id，以及在菜单上的权限
	 * @return
	 */
	public Map<String,Set<String>> roleApsMap(){
		List<RoleAp> roleaps = roleAps(); 
		Map<String,Set<String>> roleApMap=new HashMap<String,Set<String>>();
		
		for(RoleAp ap : roleaps){
			roleApMap.put(ap.getMenuid(), ap.actionSet());
		}
		return roleApMap;
	}
	
	

	/**
	 * 保存角色对应所有权限
	 * @param roleaps
	 */
	public void saveRoleAps(List<RoleAp> roleaps) {
		RoleAp.INSTANCE.save(roleaps, roleid);
	}
	

	
	public String getId(){
		return roleid;
	}
	
	public String getText(){
		return rolename;
	}
	
	public String getValue(){
		return roleid;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}
}