package com.wx.ad.dbo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import my.dao.annotation.Column;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.po.BasePo;

@Table("SYS_ROLE_AP")
@View("SYS_ROLE_AP")
@PK({ "MENUID", "ROLEID" })
public class RoleAp extends BasePo {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	public static final RoleAp INSTANCE = new RoleAp();

	@Column(value = "roleid", type = ColumnType.STRING)
	private String roleid;

	@Column(value = "menuid", type = ColumnType.STRING)
	private String menuid;

	@Column(value = "actionset", type = ColumnType.STRING)
	private String actionset;

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getActionset() {
		return actionset;
	}

	public void setActionset(String actionset) {
		this.actionset = actionset;
	}

	/**
	 * 获得对应菜单
	 * 
	 * @return
	 */
	public SysMenu menu() {
		return SysMenu.INSTANCE.loadByName(menuid);
	}

	public Set<String> actionSet() {
		Set<String> set = new HashSet<String>();
		if (actionset != null) {
			for (String action : actionset.split(",")) {
				set.add(action);
			}
		}

		return set;
	}

	public void save(List<RoleAp> roleaps, String roleid) {
		tableHelper().update("delete from sys_ROLE_AP where roleid = ? ", roleid);

		String sql = "insert into sys_ROLE_AP(roleid,menuid,actionset) values(?,?,?)";

		List<Object[]> paramss = new ArrayList<Object[]>();
		if (roleaps.size() > 0) {
			for (RoleAp ra : roleaps) {
				Object[] obj = new Object[3];
				obj[0] = roleid;
				obj[1] = ra.getMenuid();
				obj[2] = ra.getActionset();
				paramss.add(obj);
			}
			if (paramss.size() > 0)
				tableHelper().batch(sql, paramss);
		}
	}
}
