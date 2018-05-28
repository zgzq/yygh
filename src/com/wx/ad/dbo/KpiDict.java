/**
** create by code gen .
**/
package com.wx.ad.dbo;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.Id;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("WX_KPI_DICT")
@View("WX_KPI_DICT")
@PK({ "ID" })
public class KpiDict extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiDict INSTANCE = new KpiDict();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private Long id ;
	
	@Column(value = "dictcode", type = ColumnType.NUMBER)
	private String dictcode ;
	
	@Column(value = "dictname", type = ColumnType.STRING)
	private String dictname ;
	
	@Column(value = "dictinfo", type = ColumnType.STRING)
	private String dictinfo ;
	


	public Long getId (){
		return id;
	}
		 
	public void setId (Long id){
		this.id=id;
	}

	public String getDictcode (){
		return dictcode;
	}
		 
	public void setDictcode (String dictcode){
		this.dictcode=dictcode;
	}

	public String getDictname (){
		return dictname;
	}
		 
	public void setDictname (String dictname){
		this.dictname=dictname;
	}

	public String getDictinfo (){
		return dictinfo;
	}
		 
	public void setDictinfo (String dictinfo){
		this.dictinfo=dictinfo;
	}

}
