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




@Table("zm_sjzx_imp")
@View("zm_sjzx_imp")
@PK({ "ID" })
public class ZmIMP extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final ZmIMP INSTANCE = new ZmIMP();
	
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id ;
	
	@Column(value = "year", type = ColumnType.STRING)
	private String year ;
	@Column(value = "month", type = ColumnType.STRING)
	private String month ;
	
	@Column(value = "dept_code", type = ColumnType.STRING)
	private String dept_code ;
	
	@Column(value = "xm", type = ColumnType.STRING)
	private String xm ;
	
	@Column(value = "dqz", type = ColumnType.STRING)
	private String dqz ;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDept_code() {
		return dept_code;
	}

	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getDqz() {
		return dqz;
	}

	public void setDqz(String dqz) {
		this.dqz = dqz;
	}
	
		
}
