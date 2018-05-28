/**
** create by code gen .
**/
package com.wx.ad.dbo;
import my.base.BasePO;
import my.dao.annotation.Column;

import my.dao.annotation.PK;
import my.dao.annotation.ReadOnly;
import my.dao.annotation.Table;
import my.dao.annotation.View;
import my.dao.mapping.ColumnType;



@Table("kd_outhospaydetail")
@View("kd_outhospay_v")

public class KdOutHosPayDetail extends BasePO{

/**
* 
*/
private static final long serialVersionUID = 1L;

	public static final KdOutHosPayDetail INSTANCE = new KdOutHosPayDetail();
	
	
		@Column(value = "year", type = ColumnType.STRING)
	private String year;
		@Column(value = "month", type = ColumnType.STRING)
	private String month;
		@Column(value = "orgid", type = ColumnType.STRING)
	private String orgid;
		@Column(value = "ct", type = ColumnType.STRING)
	private String ct;
		@Column(value = "rpom", type = ColumnType.STRING)
	private String rpom;
		@Column(value = "病理收入", type = ColumnType.STRING)
	private String blsr;
		@Column(value = "材料收入", type = ColumnType.STRING)
	private String clsr;
		@Column(value = "彩超", type = ColumnType.STRING)
	private String cc;
		@Column(value = "放射", type = ColumnType.STRING)
	private String fs;
		@Column(value = "放射可优惠", type = ColumnType.STRING)
	private String fskyh;
		@Column(value = "挂号", type = ColumnType.STRING)
	private String gh;
		@Column(value = "核磁", type = ColumnType.STRING)
	private String hc;
		@Column(value = "化验", type = ColumnType.STRING)
	private String hy;
		@Column(value = "化验可优惠", type = ColumnType.STRING)
	private String hykyh;
		@Column(value = "监护", type = ColumnType.STRING)
	private String jh;
		@Column(value = "检查", type = ColumnType.STRING)
	private String jc;
		@Column(value = "结肠透析", type = ColumnType.STRING)
		private String jctx;
		@Column(value = "理疗", type = ColumnType.STRING)
		private String ll;
		@Column(value = "麻醉", type = ColumnType.STRING)
		private String mz;
		@Column(value = "美容", type = ColumnType.STRING)
		private String mr;
		@Column(value = "其他", type = ColumnType.STRING)
		private String qt;
		@Column(value = "肾图", type = ColumnType.STRING)
		private String st;
		@Column(value = "手术费", type = ColumnType.STRING)
		private String ssf;
		@Column(value = "体检", type = ColumnType.STRING)
		private String tj;
		@Column(value = "胃肠镜", type = ColumnType.STRING)
		private String wcj;
		@Column(value = "西药", type = ColumnType.STRING)
		private String xy;
		@Column(value = "心电", type = ColumnType.STRING)
		private String xd;
		@Column(value = "血透", type = ColumnType.STRING)
		private String xt;
		@Column(value = "氧气", type = ColumnType.STRING)
		private String yq;
		@Column(value = "医疗", type = ColumnType.STRING)
		private String yl;
		@Column(value = "治疗", type = ColumnType.STRING)
		private String zl;
		@Column(value = "中草药", type = ColumnType.STRING)
		private String zcy;
		@Column(value = "中成药", type = ColumnType.STRING)
		private String zchy;
		@Column(value = "住院", type = ColumnType.STRING)
		private String zy;
		@Column(value = "自制成药", type = ColumnType.STRING)
		private String zzcy;
		@Column(value = "床位费", type = ColumnType.STRING)
		private String cwf;
		@Column(value = "放射费", type = ColumnType.STRING)
		private String fsf;
		@Column(value = "化验费", type = ColumnType.STRING)
		private String hyf;
		@Column(value = "检查费", type = ColumnType.STRING)
		private String jcf;
		@Column(value = "治疗费", type = ColumnType.STRING)
		private String zlf;
		@Column(value = "西药费", type = ColumnType.STRING)
		private String xyf;
		@Column(value = "中成药费", type = ColumnType.STRING)
		private String zchyf;
		@Column(value = "中草药费", type = ColumnType.STRING)
		private String zcyf;
		@Column(value = "余欠款", type = ColumnType.STRING)
		private String yqk;
		@Column(value = "其他费", type = ColumnType.STRING)
		private String qtf;
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
		public String getOrgid() {
			return orgid;
		}
		public void setOrgid(String orgid) {
			this.orgid = orgid;
		}
		public String getCt() {
			return ct;
		}
		public void setCt(String ct) {
			this.ct = ct;
		}
		public String getRpom() {
			return rpom;
		}
		public void setRpom(String rpom) {
			this.rpom = rpom;
		}
		public String getBlsr() {
			return blsr;
		}
		public void setBlsr(String blsr) {
			this.blsr = blsr;
		}
		public String getClsr() {
			return clsr;
		}
		public void setClsr(String clsr) {
			this.clsr = clsr;
		}
		public String getCc() {
			return cc;
		}
		public void setCc(String cc) {
			this.cc = cc;
		}
		public String getFs() {
			return fs;
		}
		public void setFs(String fs) {
			this.fs = fs;
		}
		public String getFskyh() {
			return fskyh;
		}
		public void setFskyh(String fskyh) {
			this.fskyh = fskyh;
		}
		public String getGh() {
			return gh;
		}
		public void setGh(String gh) {
			this.gh = gh;
		}
		public String getHc() {
			return hc;
		}
		public void setHc(String hc) {
			this.hc = hc;
		}
		public String getHy() {
			return hy;
		}
		public void setHy(String hy) {
			this.hy = hy;
		}
		public String getHykyh() {
			return hykyh;
		}
		public void setHykyh(String hykyh) {
			this.hykyh = hykyh;
		}
		public String getJh() {
			return jh;
		}
		public void setJh(String jh) {
			this.jh = jh;
		}
		public String getJc() {
			return jc;
		}
		public void setJc(String jc) {
			this.jc = jc;
		}
		public String getJctx() {
			return jctx;
		}
		public void setJctx(String jctx) {
			this.jctx = jctx;
		}
		public String getLl() {
			return ll;
		}
		public void setLl(String ll) {
			this.ll = ll;
		}
		public String getMz() {
			return mz;
		}
		public void setMz(String mz) {
			this.mz = mz;
		}
		public String getMr() {
			return mr;
		}
		public void setMr(String mr) {
			this.mr = mr;
		}
		public String getQt() {
			return qt;
		}
		public void setQt(String qt) {
			this.qt = qt;
		}
		public String getSt() {
			return st;
		}
		public void setSt(String st) {
			this.st = st;
		}
		public String getSsf() {
			return ssf;
		}
		public void setSsf(String ssf) {
			this.ssf = ssf;
		}
		public String getTj() {
			return tj;
		}
		public void setTj(String tj) {
			this.tj = tj;
		}
		public String getWcj() {
			return wcj;
		}
		public void setWcj(String wcj) {
			this.wcj = wcj;
		}
		public String getXy() {
			return xy;
		}
		public void setXy(String xy) {
			this.xy = xy;
		}
		public String getXd() {
			return xd;
		}
		public void setXd(String xd) {
			this.xd = xd;
		}
		public String getXt() {
			return xt;
		}
		public void setXt(String xt) {
			this.xt = xt;
		}
		public String getYq() {
			return yq;
		}
		public void setYq(String yq) {
			this.yq = yq;
		}
		public String getYl() {
			return yl;
		}
		public void setYl(String yl) {
			this.yl = yl;
		}
		public String getZl() {
			return zl;
		}
		public void setZl(String zl) {
			this.zl = zl;
		}
		public String getZcy() {
			return zcy;
		}
		public void setZcy(String zcy) {
			this.zcy = zcy;
		}
		public String getZchy() {
			return zchy;
		}
		public void setZchy(String zchy) {
			this.zchy = zchy;
		}
		public String getZy() {
			return zy;
		}
		public void setZy(String zy) {
			this.zy = zy;
		}
		public String getZzcy() {
			return zzcy;
		}
		public void setZzcy(String zzcy) {
			this.zzcy = zzcy;
		}
		public String getCwf() {
			return cwf;
		}
		public void setCwf(String cwf) {
			this.cwf = cwf;
		}
		public String getFsf() {
			return fsf;
		}
		public void setFsf(String fsf) {
			this.fsf = fsf;
		}
		public String getHyf() {
			return hyf;
		}
		public void setHyf(String hyf) {
			this.hyf = hyf;
		}
		public String getJcf() {
			return jcf;
		}
		public void setJcf(String jcf) {
			this.jcf = jcf;
		}
		public String getZlf() {
			return zlf;
		}
		public void setZlf(String zlf) {
			this.zlf = zlf;
		}
		public String getXyf() {
			return xyf;
		}
		public void setXyf(String xyf) {
			this.xyf = xyf;
		}
		public String getZchyf() {
			return zchyf;
		}
		public void setZchyf(String zchyf) {
			this.zchyf = zchyf;
		}
		public String getZcyf() {
			return zcyf;
		}
		public void setZcyf(String zcyf) {
			this.zcyf = zcyf;
		}
		public String getYqk() {
			return yqk;
		}
		public void setYqk(String yqk) {
			this.yqk = yqk;
		}
		public String getQtf() {
			return qtf;
		}
		public void setQtf(String qtf) {
			this.qtf = qtf;
		}
		
}
