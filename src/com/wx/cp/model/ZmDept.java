/**
** create by code gen .
**/
package com.wx.cp.model;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("WX_ZM_DEPT")
@View("WX_ZM_DEPT")
@PK({ "ID" })
public class ZmDept extends BasePO{

/**
* 
*/

	public static final ZmDept INSTANCE = new ZmDept();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id ;
	
	@Column(value = "name", type = ColumnType.STRING)
	private String name ;
	
	@Column(value = "xhk", type = ColumnType.STRING)
	private String xhk ;
	
	@Column(value = "fk", type = ColumnType.STRING)
	private String fk ;
	
	@Column(value = "gk", type = ColumnType.STRING)
	private String gk ;
	
	@Column(value = "xnk", type = ColumnType.STRING)
	private String xnk ;
	
	@Column(value = "zlk", type = ColumnType.STRING)
	private String zlk ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "uploaddate", type = ColumnType.DATE)
	private String uploaddate ;
	


	public String getId (){
		return id;
	}
		 
	public void setId (String id){
		this.id=id;
	}

	public String getName (){
		return name;
	}
		 
	public void setName (String name){
		this.name=name;
	}

	public String getXhk (){
		return xhk;
	}
		 
	public void setXhk (String xhk){
		this.xhk=xhk;
	}

	public String getFk (){
		return fk;
	}
		 
	public void setFk (String fk){
		this.fk=fk;
	}

	public String getGk (){
		return gk;
	}
		 
	public void setGk (String gk){
		this.gk=gk;
	}

	public String getXnk (){
		return xnk;
	}
		 
	public void setXnk (String xnk){
		this.xnk=xnk;
	}

	public String getZlk (){
		return zlk;
	}
		 
	public void setZlk (String zlk){
		this.zlk=zlk;
	}

	public String getUploaddate (){
		return uploaddate;
	}
		 
	public void setUploaddate (String uploaddate){
		this.uploaddate=uploaddate;
	}

}
