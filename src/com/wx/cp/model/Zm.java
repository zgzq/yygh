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



@Table("WX_ZM")
@View("WX_ZM")
@PK({ "ID" })
public class Zm extends BasePO{

/**
* 
*/

	public static final Zm INSTANCE = new Zm();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id ;
	
	@Column(value = "totalincome", type = ColumnType.NUMBER)
	private String totalincome ;
	
	@Column(value = "totalclinicincome", type = ColumnType.NUMBER)
	private String totalclinicincome ;
	
	@Column(value = "generalclinicincome", type = ColumnType.NUMBER)
	private String generalclinicincome ;
	
	@Column(value = "emergencyincome", type = ColumnType.NUMBER)
	private String emergencyincome ;
	
	@Column(value = "physicalexamincome", type = ColumnType.NUMBER)
	private String physicalexamincome ;
	
	@Column(value = "inhospitalincome", type = ColumnType.NUMBER)
	private String inhospitalincome ;
	
	@Column(value = "totalprofit", type = ColumnType.NUMBER)
	private String totalprofit ;
	
	@Column(value = "totalclinicnum", type = ColumnType.NUMBER)
	private String totalclinicnum ;
	
	@Column(value = "generalclinicnum", type = ColumnType.NUMBER)
	private String generalclinicnum ;
	
	@Column(value = "physicalexamnum", type = ColumnType.NUMBER)
	private String physicalexamnum ;
	
	@Column(value = "emergencynum", type = ColumnType.NUMBER)
	private String emergencynum ;
	
	@Column(value = "activitynum", type = ColumnType.NUMBER)
	private String activitynum ;
	
	@Column(value = "tohospitalnum", type = ColumnType.NUMBER)
	private String tohospitalnum ;
	
	@Column(value = "outhospitalnum", type = ColumnType.NUMBER)
	private String outhospitalnum ;
	
	@Column(value = "inbeddays", type = ColumnType.NUMBER)
	private String inbeddays ;
	
	@Column(value = "openbeddays", type = ColumnType.NUMBER)
	private String openbeddays ;
	
	@Column(value = "inhospitalrate", type = ColumnType.NUMBER)
	private String inhospitalrate ;
	
	@Column(value = "averageinhospitaldays", type = ColumnType.NUMBER)
	private String averageinhospitaldays ;
	
	@Column(value = "timeclinicincome", type = ColumnType.NUMBER)
	private String timeclinicincome ;
	
	@Column(value = "personhospitalincome", type = ColumnType.NUMBER)
	private String personhospitalincome ;
	
	@Column(value = "beddayincome", type = ColumnType.NUMBER)
	private String beddayincome ;
	
	@Column(value = "bedrate", type = ColumnType.NUMBER)
	private String bedrate ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "uploaddate", type = ColumnType.DATE)
	private String uploaddate ;
	
	
	@Column(value = "otherfee", type = ColumnType.NUMBER)
	private String otherfee ;
	
	public String getOtherfee() {
		return otherfee;
	}

	public void setOtherfee(String otherfee) {
		this.otherfee = otherfee;
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

	public String getTotalclinicincome (){
		return totalclinicincome;
	}
		 
	public void setTotalclinicincome (String totalclinicincome){
		this.totalclinicincome=totalclinicincome;
	}

	public String getGeneralclinicincome (){
		return generalclinicincome;
	}
		 
	public void setGeneralclinicincome (String generalclinicincome){
		this.generalclinicincome=generalclinicincome;
	}

	public String getEmergencyincome (){
		return emergencyincome;
	}
		 
	public void setEmergencyincome (String emergencyincome){
		this.emergencyincome=emergencyincome;
	}

	public String getPhysicalexamincome (){
		return physicalexamincome;
	}
		 
	public void setPhysicalexamincome (String physicalexamincome){
		this.physicalexamincome=physicalexamincome;
	}

	public String getInhospitalincome (){
		return inhospitalincome;
	}
		 
	public void setInhospitalincome (String inhospitalincome){
		this.inhospitalincome=inhospitalincome;
	}

	public String getTotalprofit (){
		return totalprofit;
	}
		 
	public void setTotalprofit (String totalprofit){
		this.totalprofit=totalprofit;
	}

	public String getTotalclinicnum (){
		return totalclinicnum;
	}
		 
	public void setTotalclinicnum (String totalclinicnum){
		this.totalclinicnum=totalclinicnum;
	}

	public String getGeneralclinicnum (){
		return generalclinicnum;
	}
		 
	public void setGeneralclinicnum (String generalclinicnum){
		this.generalclinicnum=generalclinicnum;
	}

	public String getPhysicalexamnum (){
		return physicalexamnum;
	}
		 
	public void setPhysicalexamnum (String physicalexamnum){
		this.physicalexamnum=physicalexamnum;
	}

	public String getEmergencynum (){
		return emergencynum;
	}
		 
	public void setEmergencynum (String emergencynum){
		this.emergencynum=emergencynum;
	}

	public String getActivitynum (){
		return activitynum;
	}
		 
	public void setActivitynum (String activitynum){
		this.activitynum=activitynum;
	}

	public String getTohospitalnum (){
		return tohospitalnum;
	}
		 
	public void setTohospitalnum (String tohospitalnum){
		this.tohospitalnum=tohospitalnum;
	}

	public String getOuthospitalnum (){
		return outhospitalnum;
	}
		 
	public void setOuthospitalnum (String outhospitalnum){
		this.outhospitalnum=outhospitalnum;
	}

	public String getInbeddays (){
		return inbeddays;
	}
		 
	public void setInbeddays (String inbeddays){
		this.inbeddays=inbeddays;
	}

	public String getOpenbeddays (){
		return openbeddays;
	}
		 
	public void setOpenbeddays (String openbeddays){
		this.openbeddays=openbeddays;
	}

	public String getInhospitalrate (){
		return inhospitalrate;
	}
		 
	public void setInhospitalrate (String inhospitalrate){
		this.inhospitalrate=inhospitalrate;
	}

	public String getAverageinhospitaldays (){
		return averageinhospitaldays;
	}
		 
	public void setAverageinhospitaldays (String averageinhospitaldays){
		this.averageinhospitaldays=averageinhospitaldays;
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

	public String getBeddayincome (){
		return beddayincome;
	}
		 
	public void setBeddayincome (String beddayincome){
		this.beddayincome=beddayincome;
	}

	public String getBedrate (){
		return bedrate;
	}
		 
	public void setBedrate (String bedrate){
		this.bedrate=bedrate;
	}

	public String getUploaddate (){
		return uploaddate;
	}
		 
	public void setUploaddate (String uploaddate){
		this.uploaddate=uploaddate;
	}

}
