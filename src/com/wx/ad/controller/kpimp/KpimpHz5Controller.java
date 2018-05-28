package com.wx.ad.controller.kpimp;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.KpiDateV;
import com.wx.ad.dbo.KpiValueMpDay;
import com.wx.ad.dbo.KpiValueMpHz3;
import com.wx.ad.dbo.KpiValueMpHz4;
import com.wx.ad.dbo.KpiValueMpRef;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

import my.ann.Action;
import my.ann.Menu;
import my.dao.pool.DBManager;
import my.web.AjaxMsg;
import my.web.BaseController.CallBack;
@Menu("上市医院经营数据汇总(每月)")
@RequestMapping("/kpimp/hz5")
@Controller
public class KpimpHz5Controller extends AuthController  {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(KpimpHz5Controller.class);

	@Action("上市医院经营数据汇总(每月)")
	@RequestMapping("/list")
	public String index() {
		return "admin/kpimp/hz5";
	}


	
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<KpiValueMpHz3> gridData() {
		String yearmonth = param("yearmonth", "");
		String userid=getLoginUer().getUserId();
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		//生成数据
		KpimpUtil.genValueHz5( yearmonth, userid);
		//查询数据
		KpiDateV kpiDateV1=KpiDateV.INSTANCE.queryOne(" dt in (select min(dt) from kpi_date_v where yearmonth=? ) ", yearmonth);
		String psdate=kpiDateV1.getDt().toString();
		KpiDateV kpiDateV2=KpiDateV.INSTANCE.queryOne(" dt in (select max(dt) from kpi_date_v where yearmonth=? ) ", yearmonth);
		String pedate=kpiDateV2.getDt().toString();
		filter.append(" and psdate = ? and pedate=? and userid=? ");
		params.add(psdate);
		params.add(pedate);
		params.add(userid);
		return PageFactory.newPage(KpiValueMpHz3.class, filter.toString()," order by seq ",
				params.toArray());
	}
	
	@ResponseBody
	@RequestMapping("/getHz5")
	public JSONObject getHz5() {
		String yearmonthname=param("yearmonthname", "");
		String yearmonth=param("yearmonth", "");
		String kpiname=param("kpiname", "");
		String userid=getLoginUer().getUserId();
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		double totle;
		String curDay;
		Date dt=new Date();
	    SimpleDateFormat matter1=new SimpleDateFormat("yyyyMMdd");
	    curDay= matter1.format(dt);
	    KpiDateV kpiDateV_day=KpiDateV.INSTANCE.queryOne(" dt=?  ", curDay);
	    Long day=kpiDateV_day.getDay();
	    String yearmonth_curr=kpiDateV_day.getYearmonth();
		//查询数据
		KpiDateV kpiDateV1=KpiDateV.INSTANCE.queryOne(" dt in (select min(dt) from kpi_date_v where yearmonth=? ) ", yearmonth);
		String psdate=kpiDateV1.getDt().toString();
		KpiDateV kpiDateV2=KpiDateV.INSTANCE.queryOne(" dt in (select max(dt) from kpi_date_v where yearmonth=? ) ", yearmonth);
		String pedate=kpiDateV2.getDt().toString();
		
		Double currentPercent;
		if (kpiDateV2.getDt()-kpiDateV1.getDt()==0) {
			currentPercent=100.0;
		}else if(yearmonth_curr.equals(yearmonth)){
			currentPercent=(day*100.0/(kpiDateV2.getDt()-kpiDateV1.getDt()));
		}else{
			currentPercent=100.0;
		}
		
		df.format(currentPercent);
		
		
		JSONArray jaorgname=new JSONArray();
		JSONArray japercent=new JSONArray();
		KpiValueMpHz3 kpiValueMpHz3=KpiValueMpHz3.INSTANCE.queryOne("and psdate = ? and pedate=? and userid=? and kpiname='"+kpiname+"' ", psdate,pedate,userid);
		
		jaorgname.add("目标");
		japercent.add(100);
		jaorgname.add("集团总进度");
		totle=((kpiValueMpHz3.getOrgid1perval().doubleValue() + kpiValueMpHz3.getOrgid2perval().doubleValue() + kpiValueMpHz3.getOrgid3perval().doubleValue() + 
				kpiValueMpHz3.getOrgid4perval().doubleValue() + kpiValueMpHz3.getOrgid5perval().doubleValue() + kpiValueMpHz3.getOrgid7perval().doubleValue())/6) ;
		japercent.add(df.format(totle));
		jaorgname.add("京东中美医院");
		japercent.add(kpiValueMpHz3.getOrgid1perval());
		jaorgname.add("京东誉美医院");
		japercent.add(kpiValueMpHz3.getOrgid2perval());
		jaorgname.add("中美东城医院");
		japercent.add(kpiValueMpHz3.getOrgid3perval());
		jaorgname.add("河南整形医院");
		japercent.add(kpiValueMpHz3.getOrgid4perval());
		jaorgname.add("河南誉美医院");
		japercent.add(kpiValueMpHz3.getOrgid5perval());
		jaorgname.add("株洲同济医院");
		japercent.add(kpiValueMpHz3.getOrgid7perval());

		
		
		JSONObject json=new JSONObject();
		json.put("yearmonthname",yearmonthname);
		json.put("orgname",jaorgname);
		json.put("percent",japercent);
		json.put("currentPercent",currentPercent);
		return json;
	}
	
}
