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



@Table("WX_YM_DEPT")
@View("WX_YM_DEPT")
@PK({ "ID" })
public class YmDept extends BasePO{

/**
* 
*/

	public static final YmDept INSTANCE = new YmDept();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id ;
	
	@Column(value = "totalincome", type = ColumnType.NUMBER)
	private String totalincome ;
	
	@Column(value = "clinicincome", type = ColumnType.NUMBER)
	private String clinicincome ;
	
	@Column(value = "hospitalincome", type = ColumnType.NUMBER)
	private String hospitalincome ;
	
	@Column(value = "totalclinicnum", type = ColumnType.NUMBER)
	private String totalclinicnum ;
	
	@Column(value = "clinicfirstnum", type = ColumnType.NUMBER)
	private String clinicfirstnum ;
	
	@Column(value = "clinicsecnum", type = ColumnType.NUMBER)
	private String clinicsecnum ;
	
	@Column(value = "totaltohospitalnum", type = ColumnType.NUMBER)
	private String totaltohospitalnum ;
	
	@Column(value = "tohospitalnumfirst", type = ColumnType.NUMBER)
	private String tohospitalnumfirst ;
	
	@Column(value = "tohospitalnumsec", type = ColumnType.NUMBER)
	private String tohospitalnumsec ;
	
	@Column(value = "totalouthospitalnum", type = ColumnType.NUMBER)
	private String totalouthospitalnum ;
	
	@Column(value = "outhospitalnumfirst", type = ColumnType.NUMBER)
	private String outhospitalnumfirst ;
	
	@Column(value = "outhospitalnumsec", type = ColumnType.NUMBER)
	private String outhospitalnumsec ;
	
	@Column(value = "inbeddays", type = ColumnType.NUMBER)
	private String inbeddays ;
	
	@Column(value = "timeclinicincome", type = ColumnType.NUMBER)
	private String timeclinicincome ;
	
	@Column(value = "personhospitalincome", type = ColumnType.NUMBER)
	private String personhospitalincome ;
	
	@Column(value = "personhospitalincomefirst", type = ColumnType.NUMBER)
	private String personhospitalincomefirst ;
	
	@Column(value = "personhospitalincomesec", type = ColumnType.NUMBER)
	private String personhospitalincomesec ;
	
	@Column(value = "averageinhospitaldays", type = ColumnType.NUMBER)
	private String averageinhospitaldays ;
	
	@Column(value = "averageinhospitaldaysfirst", type = ColumnType.NUMBER)
	private String averageinhospitaldaysfirst ;
	
	@Column(value = "averageinhospitaldayssec", type = ColumnType.NUMBER)
	private String averageinhospitaldayssec ;
	
	@Column(value = "hospitalrate", type = ColumnType.NUMBER)
	private String hospitalrate ;
	
	@Column(value = "hospitalratefirst", type = ColumnType.NUMBER)
	private String hospitalratefirst ;
	
	@Column(value = "hospitalratesec", type = ColumnType.NUMBER)
	private String hospitalratesec ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "uploaddate", type = ColumnType.DATE)
	private String uploaddate ;
	
	@Column(value = "opendays", type = ColumnType.NUMBER)
	private String opendays ;
	
	@Column(value = "bedrate", type = ColumnType.NUMBER)
	private String bedrate ;
	
	


	public String getOpendays() {
		return opendays;
	}

	public void setOpendays(String opendays) {
		this.opendays = opendays;
	}

	public String getBedrate() {
		return bedrate;
	}

	public void setBedrate(String bedrate) {
		this.bedrate = bedrate;
	}

	public String getId (){
		return id;
	}
		 
	public void setId (String id){
		this.id=id;
	}

	public String getTotalincome (){
		return totalincome;
	}
		 
	public void setTotalincome (String totalincome){
		this.totalincome=totalincome;
	}

	public String getClinicincome (){
		return clinicincome;
	}
		 
	public void setClinicincome (String clinicincome){
		this.clinicincome=clinicincome;
	}

	public String getHospitalincome (){
		return hospitalincome;
	}
		 
	public void setHospitalincome (String hospitalincome){
		this.hospitalincome=hospitalincome;
	}

	public String getTotalclinicnum (){
		return totalclinicnum;
	}
		 
	public void setTotalclinicnum (String totalclinicnum){
		this.totalclinicnum=totalclinicnum;
	}

	public String getClinicfirstnum (){
		return clinicfirstnum;
	}
		 
	public void setClinicfirstnum (String clinicfirstnum){
		this.clinicfirstnum=clinicfirstnum;
	}

	public String getClinicsecnum (){
		return clinicsecnum;
	}
		 
	public void setClinicsecnum (String clinicsecnum){
		this.clinicsecnum=clinicsecnum;
	}

	public String getTotaltohospitalnum (){
		return totaltohospitalnum;
	}
		 
	public void setTotaltohospitalnum (String totaltohospitalnum){
		this.totaltohospitalnum=totaltohospitalnum;
	}

	public String getTohospitalnumfirst (){
		return tohospitalnumfirst;
	}
		 
	public void setTohospitalnumfirst (String tohospitalnumfirst){
		this.tohospitalnumfirst=tohospitalnumfirst;
	}

	public String getTohospitalnumsec (){
		return tohospitalnumsec;
	}
		 
	public void setTohospitalnumsec (String tohospitalnumsec){
		this.tohospitalnumsec=tohospitalnumsec;
	}

	public String getTotalouthospitalnum (){
		return totalouthospitalnum;
	}
		 
	public void setTotalouthospitalnum (String totalouthospitalnum){
		this.totalouthospitalnum=totalouthospitalnum;
	}

	public String getOuthospitalnumfirst (){
		return outhospitalnumfirst;
	}
		 
	public void setOuthospitalnumfirst (String outhospitalnumfirst){
		this.outhospitalnumfirst=outhospitalnumfirst;
	}

	public String getOuthospitalnumsec (){
		return outhospitalnumsec;
	}
		 
	public void setOuthospitalnumsec (String outhospitalnumsec){
		this.outhospitalnumsec=outhospitalnumsec;
	}

	public String getInbeddays (){
		return inbeddays;
	}
		 
	public void setInbeddays (String inbeddays){
		this.inbeddays=inbeddays;
	}

	public String getTimeclinicincome (){
		return timeclinicincome;
	}
		 
	public void setTimeclinicincome (String timeclinicincome){
		this.timeclinicincome=timeclinicincome;
	}

	public String getPersonhospitalincome (){
		return personhospitalincome;
	}
		 
	public void setPersonhospitalincome (String personhospitalincome){
		this.personhospitalincome=personhospitalincome;
	}

	public String getPersonhospitalincomefirst (){
		return personhospitalincomefirst;
	}
		 
	public void setPersonhospitalincomefirst (String personhospitalincomefirst){
		this.personhospitalincomefirst=personhospitalincomefirst;
	}

	public String getPersonhospitalincomesec (){
		return personhospitalincomesec;
	}
		 
	public void setPersonhospitalincomesec (String personhospitalincomesec){
		this.personhospitalincomesec=personhospitalincomesec;
	}

	public String getAverageinhospitaldays (){
		return averageinhospitaldays;
	}
		 
	public void setAverageinhospitaldays (String averageinhospitaldays){
		this.averageinhospitaldays=averageinhospitaldays;
	}

	public String getAverageinhospitaldaysfirst (){
		return averageinhospitaldaysfirst;
	}
		 
	public void setAverageinhospitaldaysfirst (String averageinhospitaldaysfirst){
		this.averageinhospitaldaysfirst=averageinhospitaldaysfirst;
	}

	public String getAverageinhospitaldayssec (){
		return averageinhospitaldayssec;
	}
		 
	public void setAverageinhospitaldayssec (String averageinhospitaldayssec){
		this.averageinhospitaldayssec=averageinhospitaldayssec;
	}

	public String getHospitalrate (){
		return hospitalrate;
	}
		 
	public void setHospitalrate (String hospitalrate){
		this.hospitalrate=hospitalrate;
	}

	public String getHospitalratefirst (){
		return hospitalratefirst;
	}
		 
	public void setHospitalratefirst (String hospitalratefirst){
		this.hospitalratefirst=hospitalratefirst;
	}

	public String getHospitalratesec (){
		return hospitalratesec;
	}
		 
	public void setHospitalratesec (String hospitalratesec){
		this.hospitalratesec=hospitalratesec;
	}

	public String getUploaddate (){
		return uploaddate;
	}
		 
	public void setUploaddate (String uploaddate){
		this.uploaddate=uploaddate;
	}

}
