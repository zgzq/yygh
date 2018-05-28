/**
** create by code gen .
**/
package com.wx.ad.dbo;

import java.util.Date;
import java.math.BigDecimal;

import my.base.BasePO;
import my.dao.annotation.Column;
import my.dao.annotation.DateFormat;
import my.dao.annotation.Id;
import my.dao.annotation.Name;
import my.dao.annotation.PK;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;
import my.dao.mapping.MappingInfo;
import my.dao.mapping.MappingInfoHolder;



@Table("KPI_VALUE_HB")
@View("KPI_VALUE_HB_V")
@PK({  })
public class KpiValueHb extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueHb INSTANCE = new KpiValueHb();
	
	@Column(value = "mzks", type = ColumnType.STRING)
	private String mzks ;
	
	@Column(value = "hj", type = ColumnType.STRING)
	private String hj ;
	
	@Column(value = "cz", type = ColumnType.STRING)
	private String cz ;
	
	@Column(value = "fz", type = ColumnType.STRING)
	private String fz ;
	
	@Column(value = "jz", type = ColumnType.STRING)
	private String jz ;
	
	@Column(value = "zj", type = ColumnType.STRING)
	private String zj ;
	
	@Column(value = "zyks", type = ColumnType.STRING)
	private String zyks ;
	
	@Column(value = "yys", type = ColumnType.STRING)
	private String yys ;
	
	@Column(value = "rys", type = ColumnType.STRING)
	private String rys ;
	
	@Column(value = "zrs", type = ColumnType.STRING)
	private String zrs ;
	
	@Column(value = "cys", type = ColumnType.STRING)
	private String cys ;
	
	@Column(value = "zcs", type = ColumnType.STRING)
	private String zcs ;
	
	@Column(value = "xys", type = ColumnType.STRING)
	private String xys ;
	
	@Column(value = "sjy", type = ColumnType.STRING)
	private String sjy ;
	
	@Column(value = "kfs", type = ColumnType.STRING)
	private String kfs ;
	
	@Column(value = "bcyl", type = ColumnType.STRING)
	private String bcyl ;
	
	@Column(value = "jc", type = ColumnType.STRING)
	private String jc ;
	
	@Column(value = "kc", type = ColumnType.STRING)
	private String kc ;
	
	@Column(value = "orgid", type = ColumnType.NUMBER)
	private Long orgid ;
	
	@Column(value = "userid", type = ColumnType.NUMBER)
	private Long userid ;
	
	@Column(value = "psdate", type = ColumnType.NUMBER)
	private Long psdate ;
	
	@Column(value = "pedate", type = ColumnType.NUMBER)
	private Long pedate ;
	
	@Column(value = "seq", type = ColumnType.NUMBER)
	private Long seq ;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getJz() {
		return jz;
	}

	public void setJz(String jz) {
		this.jz = jz;
	}

	public String getMzks (){
		return mzks;
	}
		 
	public void setMzks (String mzks){
		this.mzks=mzks;
	}

	public String getHj (){
		return hj;
	}
		 
	public void setHj (String hj){
		this.hj=hj;
	}

	public String getCz (){
		return cz;
	}
		 
	public void setCz (String cz){
		this.cz=cz;
	}

	public String getFz (){
		return fz;
	}
		 
	public void setFz (String fz){
		this.fz=fz;
	}

	public String getZj (){
		return zj;
	}
		 
	public void setZj (String zj){
		this.zj=zj;
	}

	public String getZyks (){
		return zyks;
	}
		 
	public void setZyks (String zyks){
		this.zyks=zyks;
	}

	public String getYys (){
		return yys;
	}
		 
	public void setYys (String yys){
		this.yys=yys;
	}

	public String getRys (){
		return rys;
	}
		 
	public void setRys (String rys){
		this.rys=rys;
	}

	public String getZrs (){
		return zrs;
	}
		 
	public void setZrs (String zrs){
		this.zrs=zrs;
	}

	public String getCys (){
		return cys;
	}
		 
	public void setCys (String cys){
		this.cys=cys;
	}

	public String getZcs (){
		return zcs;
	}
		 
	public void setZcs (String zcs){
		this.zcs=zcs;
	}

	public String getXys (){
		return xys;
	}
		 
	public void setXys (String xys){
		this.xys=xys;
	}

	public String getSjy (){
		return sjy;
	}
		 
	public void setSjy (String sjy){
		this.sjy=sjy;
	}

	public String getKfs (){
		return kfs;
	}
		 
	public void setKfs (String kfs){
		this.kfs=kfs;
	}

	public String getBcyl (){
		return bcyl;
	}
		 
	public void setBcyl (String bcyl){
		this.bcyl=bcyl;
	}

	public String getJc (){
		return jc;
	}
		 
	public void setJc (String jc){
		this.jc=jc;
	}

	public String getKc (){
		return kc;
	}
		 
	public void setKc (String kc){
		this.kc=kc;
	}

	public Long getOrgid (){
		return orgid;
	}
		 
	public void setOrgid (Long orgid){
		this.orgid=orgid;
	}

	public Long getUserid (){
		return userid;
	}
		 
	public void setUserid (Long userid){
		this.userid=userid;
	}

	public Long getPsdate (){
		return psdate;
	}
		 
	public void setPsdate (Long psdate){
		this.psdate=psdate;
	}

	public Long getPedate (){
		return pedate;
	}
		 
	public void setPedate (Long pedate){
		this.pedate=pedate;
	}

}
