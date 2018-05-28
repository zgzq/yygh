package com.wx.ad.dbo;

import java.util.List;
import java.util.Set;

import my.dao.annotation.Column;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.po.BasePo;

@Table("SYS_MENU")
@View("SYS_MENU")
@PK({ "ID" })
public class SysMenu extends BasePo {

	/**
* 
*/
	private static final long serialVersionUID = 1L;

	public static final SysMenu INSTANCE = new SysMenu();

	
	@Name
	@Column(value = "id", type = ColumnType.STRING)
	private String id;

	@Column(value = "pid", type = ColumnType.STRING)
	private String pid;

	@Column(value = "text", type = ColumnType.STRING)
	private String text;
	
	@Column(value = "texten", type = ColumnType.STRING)
	private String texten;

	@Column(value = "url", type = ColumnType.STRING)
	private String url;
	
	public SysMenu() {

	}

	public SysMenu(SysMenu m) {
		id = m.id;
		pid = m.pid;
		text = m.text;
		url = m.url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	
	public List<MenuAction> actions(){
		 List<MenuAction> actions= MenuAction.INSTANCE.query("MENUID = ? ", id);
		 return actions;
	}
	
	public String getRights(){
		StringBuffer sb = new StringBuffer();
		for(MenuAction s:actions()){
			if(sb.length()>0)
				sb.append(",");
			sb.append(s.getAction());
		}
		return sb.toString();
	}
	
	private Set<String> hasRight;
	
	public Set<String> getHasRight() {
		return hasRight;
	}

	public void setHasRight(Set<String> hasRight) {
		this.hasRight = hasRight;
	}

	@SuppressWarnings("unchecked")
	public List<SysMenu> loadAll() {
		return (List<SysMenu>) viewDao().query(this.getClass(), "order by id asc");
	}

	public String getTexten() {
		return texten;
	}

	public void setTexten(String texten) {
		this.texten = texten;
	}
	
	
}
