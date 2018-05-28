/**
** create by code gen .
**/
package com.wx.ad.dbo;
import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;




@Table("ZM_GOAL_INFO")
@View("ZM_GOAL_INFO")
@PK({ "GOALID" })
public class GoalInfo extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final GoalInfo INSTANCE = new GoalInfo();
	
	@Column(value = "goalid", type = ColumnType.NUMBER)
	private String goalid ;
	
	@Column(value = "year", type = ColumnType.STRING)
	private String year ;
	
	@Column(value = "departopcode", type = ColumnType.STRING)
	private String departopcode ;

	
	@Column(value = "yearvalue", type = ColumnType.NUMBER)
	private String yearvalue ;
	
	@Column(value = "department", type = ColumnType.STRING)
	private String department ;
	
	@Column(value = "goalname", type = ColumnType.STRING)
	private String goalname ;
	


	public String getGoalid (){
		return goalid;
	}
		 
	public void setGoalid (String goalid){
		this.goalid=goalid;
	}

	public String getYear (){
		return year;
	}
		 
	public void setYear (String year){
		this.year=year;
	}

	public String getDepartopcode (){
		return departopcode;
	}
		 
	public void setDepartopcode (String departopcode){
		this.departopcode=departopcode;
	}


	public String getYearvalue (){
		return yearvalue;
	}
		 
	public void setYearvalue (String yearvalue){
		this.yearvalue=yearvalue;
	}

	public String getDepartment (){
		return department;
	}
		 
	public void setDepartment (String department){
		this.department=department;
	}

	public String getGoalname (){
		return goalname;
	}
		 
	public void setGoalname (String goalname){
		this.goalname=goalname;
	}

}
