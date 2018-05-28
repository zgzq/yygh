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



@Table("KPI_VALUE_DT")
@View("KPI_VALUE_DT_V")
@PK({  })
public class KpiValueDt extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KpiValueDt INSTANCE = new KpiValueDt();
	
	@Column(value = "ks", type = ColumnType.STRING)
	private String ks ;
	
	@Column(value = "qmyw", type = ColumnType.STRING)
	private String qmyw ;
	
	@Column(value = "qcys", type = ColumnType.STRING)
	private String qcys ;
	
	@Column(value = "qnys", type = ColumnType.STRING)
	private String qnys ;
	
	@Column(value = "tkry", type = ColumnType.STRING)
	private String tkry ;
	
	@Column(value = "z", type = ColumnType.STRING)
	private String z ;
	
	@Column(value = "hj", type = ColumnType.STRING)
	private String hj ;
	
	@Column(value = "zy", type = ColumnType.STRING)
	private String zy ;
	
	@Column(value = "hz", type = ColumnType.STRING)
	private String hz ;
	
	@Column(value = "wy", type = ColumnType.STRING)
	private String wy ;
	
	@Column(value = "sw", type = ColumnType.STRING)
	private String sw ;
	
	@Column(value = "q", type = ColumnType.STRING)
	private String q ;
	
	@Column(value = "zwks", type = ColumnType.STRING)
	private String zwks ;
	
	@Column(value = "qmys", type = ColumnType.STRING)
	private String qmys ;
	
	@Column(value = "cyzzr", type = ColumnType.STRING)
	private String cyzzr ;
	
	@Column(value = "sjfcs", type = ColumnType.STRING)
	private String sjfcs ;
	
	@Column(value = "pjfw", type = ColumnType.STRING)
	private String pjfw ;
	
	@Column(value = "pjcz", type = ColumnType.STRING)
	private String pjcz ;
	
	@Column(value = "sjycs", type = ColumnType.STRING)
	private String sjycs ;
	
	@Column(value = "cyzjys", type = ColumnType.STRING)
	private String cyzjys ;
	
	@Column(value = "zhi", type = ColumnType.STRING)
	private String zhi ;
	
	@Column(value = "hao", type = ColumnType.STRING)
	private String hao ;
	
	@Column(value = "bing", type = ColumnType.STRING)
	private String bing ;
	
	@Column(value = "bcy", type = ColumnType.STRING)
	private String bcy ;
	
	@Column(value = "bcz", type = ColumnType.STRING)
	private String bcz ;
	
	@Column(value = "kc", type = ColumnType.STRING)
	private String kc ;
	
	@Column(value = "jc", type = ColumnType.STRING)
	private String jc ;
	
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

	public String getKs (){
		return ks;
	}
		 
	public void setKs (String ks){
		this.ks=ks;
	}

	public String getQmyw (){
		return qmyw;
	}
		 
	public void setQmyw (String qmyw){
		this.qmyw=qmyw;
	}

	public String getQcys (){
		return qcys;
	}
		 
	public void setQcys (String qcys){
		this.qcys=qcys;
	}

	public String getQnys (){
		return qnys;
	}
		 
	public void setQnys (String qnys){
		this.qnys=qnys;
	}

	public String getTkry (){
		return tkry;
	}
		 
	public void setTkry (String tkry){
		this.tkry=tkry;
	}

	public String getZ (){
		return z;
	}
		 
	public void setZ (String z){
		this.z=z;
	}

	public String getHj (){
		return hj;
	}
		 
	public void setHj (String hj){
		this.hj=hj;
	}

	public String getZy (){
		return zy;
	}
		 
	public void setZy (String zy){
		this.zy=zy;
	}

	public String getHz (){
		return hz;
	}
		 
	public void setHz (String hz){
		this.hz=hz;
	}

	public String getWy (){
		return wy;
	}
		 
	public void setWy (String wy){
		this.wy=wy;
	}

	public String getSw (){
		return sw;
	}
		 
	public void setSw (String sw){
		this.sw=sw;
	}

	public String getQ (){
		return q;
	}
		 
	public void setQ (String q){
		this.q=q;
	}

	public String getZwks (){
		return zwks;
	}
		 
	public void setZwks (String zwks){
		this.zwks=zwks;
	}

	public String getQmys (){
		return qmys;
	}
		 
	public void setQmys (String qmys){
		this.qmys=qmys;
	}

	public String getCyzzr (){
		return cyzzr;
	}
		 
	public void setCyzzr (String cyzzr){
		this.cyzzr=cyzzr;
	}

	public String getSjfcs (){
		return sjfcs;
	}
		 
	public void setSjfcs (String sjfcs){
		this.sjfcs=sjfcs;
	}

	public String getPjfw (){
		return pjfw;
	}
		 
	public void setPjfw (String pjfw){
		this.pjfw=pjfw;
	}

	public String getPjcz (){
		return pjcz;
	}
		 
	public void setPjcz (String pjcz){
		this.pjcz=pjcz;
	}

	public String getSjycs (){
		return sjycs;
	}
		 
	public void setSjycs (String sjycs){
		this.sjycs=sjycs;
	}

	public String getCyzjys (){
		return cyzjys;
	}
		 
	public void setCyzjys (String cyzjys){
		this.cyzjys=cyzjys;
	}

	public String getZhi (){
		return zhi;
	}
		 
	public void setZhi (String zhi){
		this.zhi=zhi;
	}

	public String getHao (){
		return hao;
	}
		 
	public void setHao (String hao){
		this.hao=hao;
	}

	public String getBing (){
		return bing;
	}
		 
	public void setBing (String bing){
		this.bing=bing;
	}

	public String getBcy (){
		return bcy;
	}
		 
	public void setBcy (String bcy){
		this.bcy=bcy;
	}

	public String getBcz (){
		return bcz;
	}
		 
	public void setBcz (String bcz){
		this.bcz=bcz;
	}

	public String getKc (){
		return kc;
	}
		 
	public void setKc (String kc){
		this.kc=kc;
	}

	public String getJc (){
		return jc;
	}
		 
	public void setJc (String jc){
		this.jc=jc;
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
