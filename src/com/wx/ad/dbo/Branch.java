/**
** create by code gen .
**/
package com.wx.ad.dbo;

import java.util.List;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.Id;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("WX_BRANCH")
@View("WX_BRANCH")
@PK({ "ID" })
public class Branch extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final Branch INSTANCE = new Branch();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id ;
	
	@Column(value = "code", type = ColumnType.STRING)
	private String code ;
	
	@Column(value = "name", type = ColumnType.STRING)
	private String name ;
	
	@Column(value = "pid", type = ColumnType.NUMBER)
	private String pid ;
	
	@Column(value = "iskey", type = ColumnType.NUMBER)
	private String iskey ;
	
	
	@Column(value = "type", type = ColumnType.NUMBER)
	private String type ;
	
	


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIskey() {
		return iskey;
	}

	public void setIskey(String iskey) {
		this.iskey = iskey;
	}

	public String getId (){
		return id;
	}
		 
	public void setId (String id){
		this.id=id;
	}

	public String getCode (){
		return code;
	}
		 
	public void setCode (String code){
		this.code=code;
	}

	public String getName (){
		return name;
	}
		 
	public void setName (String name){
		this.name=name;
	}

	public String getPid (){
		return pid;
	}
		 
	public void setPid (String pid){
		this.pid=pid;
	}

	
	public List<Branch> getSub(){
		return Branch.INSTANCE.query("pid = ? order by code", id);
	}
	
	
	public String getText() {
			return name;
	}
	
	
	public String getValue(){
		return id;
	}

}
