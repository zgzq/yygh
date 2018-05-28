/**
** create by code gen .
**/
package com.wx.ad.dbo;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("ZM_USR_DEPT")
@View("ZM_USR_DEPT")
@PK({ "DEPT_CODE","ORG_ID" })
public class UsrDept extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final UsrDept INSTANCE = new UsrDept();
	
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id ;
	
	@Column(value = "dept_code", type = ColumnType.STRING)
	private String dept_code ;
	
	@Column(value = "dept_name", type = ColumnType.STRING)
	private String dept_name ;
	
	@Column(value = "dept_alias", type = ColumnType.STRING)
	private String dept_alias ;
	
	@Column(value = "dept_type", type = ColumnType.STRING)
	private String dept_type ;
	
	@Column(value = "important", type = ColumnType.STRING)
	private String important ;
	
	@Column(value = "org_id", type = ColumnType.NUMBER)
	private String org_id ;
	
	@Column(value = "remark", type = ColumnType.STRING)
	private String remark ;
	
	@Column(value = "enable_flag", type = ColumnType.STRING)
	private String enable_flag ;
	
	@Column(value = "create_user_id", type = ColumnType.NUMBER)
	private String create_user_id ;
	
	@Column(value = "create_user_name", type = ColumnType.STRING)
	private String create_user_name ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "create_date", type = ColumnType.DATE)
	private String create_date ;
	
	@Column(value = "modify_user_id", type = ColumnType.NUMBER)
	private String modify_user_id ;
	
	@Column(value = "modify_user_name", type = ColumnType.STRING)
	private String modify_user_name ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "modify_date", type = ColumnType.DATE)
	private String modify_date ;
	


	public String getId (){
		return id;
	}
		 
	public void setId (String id){
		this.id=id;
	}

	public String getDept_code (){
		return dept_code;
	}
		 
	public void setDept_code (String dept_code){
		this.dept_code=dept_code;
	}

	public String getDept_name (){
		return dept_name;
	}
		 
	public void setDept_name (String dept_name){
		this.dept_name=dept_name;
	}

	public String getDept_alias (){
		return dept_alias;
	}
		 
	public void setDept_alias (String dept_alias){
		this.dept_alias=dept_alias;
	}

	public String getDept_type (){
		return dept_type;
	}
		 
	public void setDept_type (String dept_type){
		this.dept_type=dept_type;
	}

	public String getImportant (){
		return important;
	}
		 
	public void setImportant (String important){
		this.important=important;
	}

	public String getOrg_id (){
		return org_id;
	}
		 
	public void setOrg_id (String org_id){
		this.org_id=org_id;
	}

	public String getRemark (){
		return remark;
	}
		 
	public void setRemark (String remark){
		this.remark=remark;
	}

	public String getEnable_flag (){
		return enable_flag;
	}
		 
	public void setEnable_flag (String enable_flag){
		this.enable_flag=enable_flag;
	}

	public String getCreate_user_id (){
		return create_user_id;
	}
		 
	public void setCreate_user_id (String create_user_id){
		this.create_user_id=create_user_id;
	}

	public String getCreate_user_name (){
		return create_user_name;
	}
		 
	public void setCreate_user_name (String create_user_name){
		this.create_user_name=create_user_name;
	}

	public String getCreate_date (){
		return create_date;
	}
		 
	public void setCreate_date (String create_date){
		this.create_date=create_date;
	}

	public String getModify_user_id (){
		return modify_user_id;
	}
		 
	public void setModify_user_id (String modify_user_id){
		this.modify_user_id=modify_user_id;
	}

	public String getModify_user_name (){
		return modify_user_name;
	}
		 
	public void setModify_user_name (String modify_user_name){
		this.modify_user_name=modify_user_name;
	}

	public String getModify_date (){
		return modify_date;
	}
		 
	public void setModify_date (String modify_date){
		this.modify_date=modify_date;
	}

	public String getText() {
		return dept_name;
}


public String getValue(){
	return id;
}
}
