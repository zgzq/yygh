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



@Table("WX_YM")
@View("WX_YM")
@PK({ "ID" })
public class Ym extends BasePO{

/**
* 
*/

	public static final Ym INSTANCE = new Ym();
	
	@Id
	@Column(value = "id", type = ColumnType.NUMBER)
	private String id ;
	
	@Column(value = "totalincome", type = ColumnType.NUMBER)
	private String totalincome ;
	
	@Column(value = "totalprofit", type = ColumnType.NUMBER)
	private String totalprofit ;
	
	@Column(value = "takedrugincome", type = ColumnType.NUMBER)
	private String takedrugincome ;
	
	@Column(value = "takedrugclinic", type = ColumnType.NUMBER)
	private String takedrugclinic ;
	
	@Column(value = "hemodialysisincome", type = ColumnType.NUMBER)
	private String hemodialysisincome ;
	
	@Column(value = "hemodialysisnum", type = ColumnType.NUMBER)
	private String hemodialysisnum ;
	
	@Column(value = "totalclinicnum", type = ColumnType.NUMBER)
	private String totalclinicnum ;
	
	@Column(value = "tohospitalnum", type = ColumnType.NUMBER)
	private String tohospitalnum ;
	
	@Column(value = "inbedays", type = ColumnType.NUMBER)
	private String inbedays ;
	
	@Column(value = "timeclinicincome", type = ColumnType.NUMBER)
	private String timeclinicincome ;
	
	@Column(value = "personhospitalincome", type = ColumnType.NUMBER)
	private String personhospitalincome ;
	
	@DateFormat("yyyy-MM-dd HH:mm:ss")
	@Column(value = "uploaddate", type = ColumnType.DATE)
	private String uploaddate ;
	
	
	@Column(value = "outhospitalnum", type = ColumnType.NUMBER)
	private String outhospitalnum ;
	
	@Column(value = "bedrate", type = ColumnType.NUMBER)
	private String bedrate ;
	
	@Column(value = "averageinhospitaldays", type = ColumnType.NUMBER)
	private String averageinhospitaldays ;

	
	@Column(value = "openbeddays", type = ColumnType.NUMBER)
	private String openbeddays ;

	public String getId (){
		return id;
	}
		 
	public String getOuthospitalnum() {
		return outhospitalnum;
	}

	public void setOuthospitalnum(String outhospitalnum) {
		this.outhospitalnum = outhospitalnum;
	}

	public String getBedrate() {
		return bedrate;
	}

	public void setBedrate(String bedrate) {
		this.bedrate = bedrate;
	}

	public String getAverageinhospitaldays() {
		return averageinhospitaldays;
	}

	public void setAverageinhospitaldays(String averageinhospitaldays) {
		this.averageinhospitaldays = averageinhospitaldays;
	}

	public String getOpenbeddays() {
		return openbeddays;
	}

	public void setOpenbeddays(String openbeddays) {
		this.openbeddays = openbeddays;
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

	public String getTotalprofit (){
		return totalprofit;
	}
		 
	public void setTotalprofit (String totalprofit){
		this.totalprofit=totalprofit;
	}

	public String getTakedrugincome (){
		return takedrugincome;
	}
		 
	public void setTakedrugincome (String takedrugincome){
		this.takedrugincome=takedrugincome;
	}

	public String getTakedrugclinic (){
		return takedrugclinic;
	}
		 
	public void setTakedrugclinic (String takedrugclinic){
		this.takedrugclinic=takedrugclinic;
	}

	public String getHemodialysisincome (){
		return hemodialysisincome;
	}
		 
	public void setHemodialysisincome (String hemodialysisincome){
		this.hemodialysisincome=hemodialysisincome;
	}

	public String getHemodialysisnum (){
		return hemodialysisnum;
	}
		 
	public void setHemodialysisnum (String hemodialysisnum){
		this.hemodialysisnum=hemodialysisnum;
	}

	public String getTotalclinicnum (){
		return totalclinicnum;
	}
		 
	public void setTotalclinicnum (String totalclinicnum){
		this.totalclinicnum=totalclinicnum;
	}

	public String getTohospitalnum (){
		return tohospitalnum;
	}
		 
	public void setTohospitalnum (String tohospitalnum){
		this.tohospitalnum=tohospitalnum;
	}

	public String getInbedays (){
		return inbedays;
	}
		 
	public void setInbedays (String inbedays){
		this.inbedays=inbedays;
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

	public String getUploaddate (){
		return uploaddate;
	}
		 
	public void setUploaddate (String uploaddate){
		this.uploaddate=uploaddate;
	}

}
