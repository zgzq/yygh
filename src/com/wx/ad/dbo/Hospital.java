/**
** create by code gen .
**/
package com.wx.ad.dbo;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.Id;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("WX_HOSPITAL")
@View("WX_HOSPITAL")
@PK({ "ID" })
public class Hospital extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final Hospital INSTANCE = new Hospital();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private Long id ;
	
	@Column(value = "hosid", type = ColumnType.STRING)
	private String hosid ;
	
	@Column(value = "hosname", type = ColumnType.STRING)
	private String hosname ;
	
	@Column(value = "address", type = ColumnType.STRING)
	private String address ;
	
	@Column(value = "hlevel", type = ColumnType.STRING)
	private String hlevel ;
	
	@Column(value = "postcode", type = ColumnType.STRING)
	private String postcode ;
	
	@Column(value = "coordinatex", type = ColumnType.STRING)
	private String coordinatex ;
	
	@Column(value = "coordinatey", type = ColumnType.STRING)
	private String coordinatey ;
	
	@Column(value = "provincecode", type = ColumnType.STRING)
	private String provincecode ;
	
	@Column(value = "province", type = ColumnType.STRING)
	private String province ;
	
	@Column(value = "citycode", type = ColumnType.STRING)
	private String citycode ;
	
	@Column(value = "city", type = ColumnType.STRING)
	private String city ;
	
	@Column(value = "tel", type = ColumnType.STRING)
	private String tel ;
	
	@Column(value = "photourl", type = ColumnType.STRING)
	private String photourl ;
	
	@Column(value = "hosbrief", type = ColumnType.CLOB)
	private String hosbrief ;
	


	public Long getId (){
		return id;
	}
		 
	public void setId (Long id){
		this.id=id;
	}

	public String getHosid (){
		return hosid;
	}
		 
	public void setHosid (String hosid){
		this.hosid=hosid;
	}

	public String getHosname (){
		return hosname;
	}
		 
	public void setHosname (String hosname){
		this.hosname=hosname;
	}

	public String getAddress (){
		return address;
	}
	public String getHlevel() {
		return hlevel;
	}

	public void setHlevel(String hlevel) {
		this.hlevel = hlevel;
	}
		 
	public void setAddress (String address){
		this.address=address;
	}


	public String getPostcode (){
		return postcode;
	}
		 
	public void setPostcode (String postcode){
		this.postcode=postcode;
	}

	public String getCoordinatex (){
		return coordinatex;
	}
		 
	public void setCoordinatex (String coordinatex){
		this.coordinatex=coordinatex;
	}

	public String getCoordinatey (){
		return coordinatey;
	}
		 
	public void setCoordinatey (String coordinatey){
		this.coordinatey=coordinatey;
	}

	public String getProvincecode (){
		return provincecode;
	}
		 
	public void setProvincecode (String provincecode){
		this.provincecode=provincecode;
	}

	public String getProvince (){
		return province;
	}
		 
	public void setProvince (String province){
		this.province=province;
	}

	public String getCitycode (){
		return citycode;
	}
		 
	public void setCitycode (String citycode){
		this.citycode=citycode;
	}

	public String getCity (){
		return city;
	}
		 
	public void setCity (String city){
		this.city=city;
	}

	public String getTel (){
		return tel;
	}
		 
	public void setTel (String tel){
		this.tel=tel;
	}

	public String getPhotourl (){
		return photourl;
	}
		 
	public void setPhotourl (String photourl){
		this.photourl=photourl;
	}

	public String getHosbrief (){
		return hosbrief;
	}
		 
	public void setHosbrief (String hosbrief){
		this.hosbrief=hosbrief;
	}

}
