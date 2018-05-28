/**
** create by code gen .
**/
package com.wx.ad.dbo;

import java.util.List;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.util.SysParam;



@Table("WX_DICT")
@View("WX_DICT")
@PK({ "ID" })
public class Dict extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final Dict INSTANCE = new Dict();
	
	@Name
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id ;
	
	@Column(value = "pid", type = ColumnType.NUMBER)
	private String pid ;
	
	@Column(value = "text", type = ColumnType.STRING)
	private String text ;
	
	@Column(value = "texten", type = ColumnType.STRING)
	private String texten ;
	
	@Column(value = "value", type = ColumnType.STRING)
	private String value ;
	
	@Column(value = "stopflag", type = ColumnType.NUMBER)
	private String stopflag ;
	


	public String getId (){
		return id;
	}
		 
	public void setId (String id){
		this.id=id;
	}

	public String getPid (){
		return pid;
	}
		 
	public void setPid (String pid){
		this.pid=pid;
	}

	public String getText (){
		return text;
	}
		 
	public void setText (String text){
		this.text=text;
	}

	public String getTexten (){
		return texten;
	}
		 
	public void setTexten (String texten){
		this.texten=texten;
	}

	public String getValue (){
		return value;
	}
		 
	public void setValue (String value){
		this.value=value;
	}

	public String getStopflag (){
		return stopflag;
	}
		 
	public void setStopflag (String stopflag){
		this.stopflag=stopflag;
	}
	
	
	@Override
	public boolean cachedByName() {
		if(SysParam.isDevMode()){
			return false;
		}
		return true;
	}
	

	/**
	 * 查询下级字典
	 * 
	 * @param value
	 * @return
	 */
	public List<Dict> querySub(String value) {
		Dict dict = super.queryOne("value=?", value.toUpperCase());
		if (dict == null) {
			return null;
		}
		List<Dict> list=viewHelper()
				.query(Dict.class,
						"select * from wx_DICT where pid = ?",
						dict.getId());
			return list;
	}

}
