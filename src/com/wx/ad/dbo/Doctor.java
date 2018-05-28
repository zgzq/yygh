/**
** create by code gen .
**/
package com.wx.ad.dbo;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.Id;
import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("WX_DOCTOR")
@View("WX_DOCTOR_V")
@PK({ "ID" })
public class Doctor extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final Doctor INSTANCE = new Doctor();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private Long id ;
	
	@Column(value = "doccode", type = ColumnType.STRING)
	private String doccode ;
	
	@Column(value = "docname", type = ColumnType.STRING)
	private String docname ;
	
	@Column(value = "url", type = ColumnType.STRING)
	private String url ;
	
	@Column(value = "intro", type = ColumnType.CLOB)
	private String intro ;
	
	@Column(value = "spec", type = ColumnType.STRING)
	private String spec ;
	
	@Column(value = "remark", type = ColumnType.STRING)
	private String remark ;
	
	@Column(value = "branchid", type = ColumnType.NUMBER)
	private Long branchid ;
	
	@Column(value = "levelid", type = ColumnType.NUMBER)
	private Long levelid ;
	
	@Column(value = "sexid", type = ColumnType.NUMBER)
	private Long sexid ;
	
	@ReadOnly
	@Column(value = "deptcode", type = ColumnType.STRING)
	private String deptcode ;
	
	@ReadOnly
	@Column(value = "levelname", type = ColumnType.STRING)
	private String levelname ;
	
	@ReadOnly
	@Column(value = "sex", type = ColumnType.STRING)
	private String sex ;
	


	public Long getId (){
		return id;
	}
		 
	public void setId (Long id){
		this.id=id;
	}

	public String getDoccode (){
		return doccode;
	}
		 
	public void setDoccode (String doccode){
		this.doccode=doccode;
	}

	public String getDocname (){
		return docname;
	}
		 
	public void setDocname (String docname){
		this.docname=docname;
	}

	public String getUrl (){
		return url;
	}
		 
	public void setUrl (String url){
		this.url=url;
	}

	public String getIntro (){
		return intro;
	}
		 
	public void setIntro (String intro){
		this.intro=intro;
	}

	public String getSpec (){
		return spec;
	}
		 
	public void setSpec (String spec){
		this.spec=spec;
	}

	public String getRemark (){
		return remark;
	}
		 
	public void setRemark (String remark){
		this.remark=remark;
	}

	public Long getBranchid (){
		return branchid;
	}
		 
	public void setBranchid (Long branchid){
		this.branchid=branchid;
	}

	public Long getLevelid (){
		return levelid;
	}
		 
	public void setLevelid (Long levelid){
		this.levelid=levelid;
	}


	public String getDeptcode (){
		return deptcode;
	}
		 
	public void setDeptcode (String deptcode){
		this.deptcode=deptcode;
	}

	public String getLevelname (){
		return levelname;
	}
		 
	public void setLevelname (String levelname){
		this.levelname=levelname;
	}

	public Long getSexid() {
		return sexid;
	}

	public void setSexid(Long sexid) {
		this.sexid = sexid;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}



}
