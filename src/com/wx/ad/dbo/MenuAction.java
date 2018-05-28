package com.wx.ad.dbo;


import my.dao.annotation.Column;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.po.BasePo;

@Table("SYS_MENU_ACTION")
@View("SYS_MENU_ACTION")
@PK({ "ACTION", "MENUID" })
public class MenuAction extends BasePo {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	public static final MenuAction INSTANCE = new MenuAction();

	@Column(value = "menuid", type = ColumnType.STRING)
	private String menuid;

	@Column(value = "action", type = ColumnType.STRING)
	private String action;

	@Column(value = "sortno", type = ColumnType.STRING)
	private String sortno;

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSortno() {
		return sortno;
	}

	public void setSortno(String sortno) {
		this.sortno = sortno;
	}

}