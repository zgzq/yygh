/**
** create by code gen .
**/
package com.wx.cp.model;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("USER_COL_COMMENTS")
@View("USER_COL_COMMENTS")
@PK({  })
public class ColComments extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final ColComments INSTANCE = new ColComments();
	
	@Column(value = "table_name", type = ColumnType.STRING)
	private String table_name ;
	
	@Column(value = "column_name", type = ColumnType.STRING)
	private String column_name ;
	
	@Column(value = "comments", type = ColumnType.STRING)
	private String comments ;
	


	public String getTable_name (){
		return table_name;
	}
		 
	public void setTable_name (String table_name){
		this.table_name=table_name;
	}

	public String getColumn_name (){
		return column_name;
	}
		 
	public void setColumn_name (String column_name){
		this.column_name=column_name;
	}

	public String getComments (){
		return comments;
	}
		 
	public void setComments (String comments){
		this.comments=comments;
	}

}
