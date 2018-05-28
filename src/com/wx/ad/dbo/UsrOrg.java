/**
** create by code gen .
**/
package com.wx.ad.dbo;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("ZM_USR_ORG")
@View("zm_usr_org_v")
@PK({ "ID" })
public class UsrOrg extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final UsrOrg INSTANCE = new UsrOrg();
	
	@Name
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id ;
	
	@Column(value = "org_name", type = ColumnType.STRING)
	private String org_name ;
	
	@Column(value = "org_name_abbr", type = ColumnType.STRING)
	private String org_name_abbr ;
	
	@Column(value = "org_name_spell", type = ColumnType.STRING)
	private String org_name_spell ;
	
	@Column(value = "org_name_wb", type = ColumnType.STRING)
	private String org_name_wb ;
	
	@Column(value = "link_man", type = ColumnType.STRING)
	private String link_man ;
	
	@Column(value = "link_phone", type = ColumnType.STRING)
	private String link_phone ;
	
	@Column(value = "org_address", type = ColumnType.STRING)
	private String org_address ;
	
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
	
	@Column(value = "orig_org_id", type = ColumnType.NUMBER)
	private String orig_org_id ;
	
	@Column(value = "orig_org_name", type = ColumnType.STRING)
	private String orig_org_name ;
	
	@Column(value = "super_org_id", type = ColumnType.NUMBER)
	private String super_org_id ;
	
	@Column(value = "super_org_name", type = ColumnType.STRING)
	private String super_org_name ;
	
	@Column(value = "wxid", type = ColumnType.STRING)
	private String wxid ;
	
	@Column(value = "wxpid", type = ColumnType.STRING)
	private String wxpid ;

	@Column(value = "org_pic", type = ColumnType.STRING)
	private String org_pic ;
	
	@Column(value = "ss", type = ColumnType.NUMBER)
	private String ss ;
	
	
	public String getOrg_pic() {
		return org_pic;
	}


	public void setOrg_pic(String org_pic) {
		this.org_pic = org_pic;
	}


	public String getWxid() {
		return wxid;
	}


	public void setWxid(String wxid) {
		this.wxid = wxid;
	}


	public String getWxpid() {
		return wxpid;
	}


	public void setWxpid(String wxpid) {
		this.wxpid = wxpid;
	}


	@ReadOnly
	@Column(value = "url", type = ColumnType.STRING)
	private String url ;
	
	@Override
		public boolean cachedByName() {
			return true;
		}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId (){
		return id;
	}
		 
	public void setId (String id){
		this.id=id;
	}

	public String getOrg_name (){
		return org_name;
	}
		 
	public void setOrg_name (String org_name){
		this.org_name=org_name;
	}

	public String getOrg_name_abbr (){
		return org_name_abbr;
	}
		 
	public void setOrg_name_abbr (String org_name_abbr){
		this.org_name_abbr=org_name_abbr;
	}

	public String getOrg_name_spell (){
		return org_name_spell;
	}
		 
	public void setOrg_name_spell (String org_name_spell){
		this.org_name_spell=org_name_spell;
	}

	public String getOrg_name_wb (){
		return org_name_wb;
	}
		 
	public void setOrg_name_wb (String org_name_wb){
		this.org_name_wb=org_name_wb;
	}

	public String getLink_man (){
		return link_man;
	}
		 
	public void setLink_man (String link_man){
		this.link_man=link_man;
	}

	public String getLink_phone (){
		return link_phone;
	}
		 
	public void setLink_phone (String link_phone){
		this.link_phone=link_phone;
	}

	public String getOrg_address (){
		return org_address;
	}
		 
	public void setOrg_address (String org_address){
		this.org_address=org_address;
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

	public String getOrig_org_id (){
		return orig_org_id;
	}
		 
	public void setOrig_org_id (String orig_org_id){
		this.orig_org_id=orig_org_id;
	}

	public String getOrig_org_name (){
		return orig_org_name;
	}
		 
	public void setOrig_org_name (String orig_org_name){
		this.orig_org_name=orig_org_name;
	}

	public String getSuper_org_id (){
		return super_org_id;
	}
		 
	public void setSuper_org_id (String super_org_id){
		this.super_org_id=super_org_id;
	}

	public String getSuper_org_name (){
		return super_org_name;
	}
		 
	public void setSuper_org_name (String super_org_name){
		this.super_org_name=super_org_name;
	}
	public String getText() {
		return org_name;
		
		
	}


	public String getSs() {
		return ss;
	}


	public void setSs(String ss) {
		this.ss = ss;
	}


	public String getValue(){
		return id;
	}

}
