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



@Table("ZM_IND_SETTLEMENT")
@View("ZM_IND_SETTLEMENT")
@PK({ "ORG_ID","VALUE_DATE" })
public class IndSettlement extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final IndSettlement INSTANCE = new IndSettlement();
	
	@Column(value = "org_id", type = ColumnType.NUMBER)
	private String org_id ;
	
	@Column(value = "income", type = ColumnType.NUMBER)
	private String income ;
	
	@Column(value = "profit", type = ColumnType.NUMBER)
	private String profit ;
	
	@Column(value = "value_date", type = ColumnType.NUMBER)
	private String value_date ;
	
	@Column(value = "remark", type = ColumnType.STRING)
	private String remark ;
	
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
	


	public String getOrg_id (){
		return org_id;
	}
		 
	public void setOrg_id (String org_id){
		this.org_id=org_id;
	}

	public String getIncome (){
		return income;
	}
		 
	public void setIncome (String income){
		this.income=income;
	}

	public String getProfit (){
		return profit;
	}
		 
	public void setProfit (String profit){
		this.profit=profit;
	}

	public String getValue_date (){
		return value_date;
	}
		 
	public void setValue_date (String value_date){
		this.value_date=value_date;
	}

	public String getRemark (){
		return remark;
	}
		 
	public void setRemark (String remark){
		this.remark=remark;
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

}
