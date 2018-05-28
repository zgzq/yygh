package com.wx.ad.controller.kpimp;

import java.io.UnsupportedEncodingException;
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
import com.wx.ad.dbo.KpiValueMpHz4;
import com.wx.ad.dbo.KpiValueMpRef;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

import my.ann.Action;
import my.ann.Menu;
import my.dao.pool.DBManager;
import my.web.AjaxMsg;
import my.web.BaseController.CallBack;
@Menu("非上市医院经营数据汇总(每月)")
@RequestMapping("/kpimp/hz6")
@Controller
public class KpimpHz6Controller extends AuthController  {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(KpimpHz6Controller.class);

	@Action("非上市医院经营数据汇总(每月)")
	@RequestMapping("/list")
	public String index() {
		return "admin/kpimp/hz6";
	}


	
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<KpiValueMpHz4> gridData() {
		String year=param("year", "");
		String month=param("month", "");
		String yearmonth = year+month;
		String userid=getLoginUer().getUserId();
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		//生成数据
		KpimpUtil.genValueHz6( yearmonth, userid);
		//查询数据
		KpiDateV kpiDateV1=KpiDateV.INSTANCE.queryOne(" dt in (select min(dt) from kpi_date_v where yearmonth=? ) ", yearmonth);
		String psdate=kpiDateV1.getDt().toString();
		KpiDateV kpiDateV2=KpiDateV.INSTANCE.queryOne(" dt in (select max(dt) from kpi_date_v where yearmonth=? ) ", yearmonth);
		String pedate=kpiDateV2.getDt().toString();
		filter.append(" and psdate = ? and pedate=? and userid=? ");
		params.add(psdate);
		params.add(pedate);
		params.add(userid);
		return PageFactory.newPage(KpiValueMpHz4.class, filter.toString()," order by seq ",
				params.toArray());
	}
	
	@ResponseBody
	@RequestMapping("/getHz6")
	public JSONObject getHz6() {
		String yearmonthname=param("yearmonthname", "");
		String year=param("year", "");
		String month=param("month", "");
		String yearmonth = year+month;
		String kpiname=param("kpiname", "");
		
		String kpiname_str = "";
		String yearmonthname_str = "";
		try {
			byte[] b=kpiname.getBytes("ISO-8859-1");//用tomcat的格式（iso-8859-1）方式去读。
			kpiname_str = new String(b,"utf-8");
			byte[] b1=yearmonthname.getBytes("ISO-8859-1");//用tomcat的格式（iso-8859-1）方式去读。
			yearmonthname_str = new String(b1,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		KpiValueMpHz4 kpiValueMpHz4=KpiValueMpHz4.INSTANCE.queryOne("and psdate = ? and pedate=? and userid=? and kpiname='"+kpiname_str+"' ", psdate,pedate,userid);
		jaorgname.add("目标");
		japercent.add(100);
		jaorgname.add("集团总进度");
		totle=((kpiValueMpHz4.getOrgid6perval().doubleValue() + kpiValueMpHz4.getOrgid8perval().doubleValue() + kpiValueMpHz4.getOrgid9perval().doubleValue())/3) ;
		japercent.add(df.format(totle));
		
		jaorgname.add("成都誉美医院");
		japercent.add(kpiValueMpHz4.getOrgid6perval());
		jaorgname.add("郑州京美医院");
		japercent.add(kpiValueMpHz4.getOrgid8perval());
		jaorgname.add("常德惠民医院");
		japercent.add(kpiValueMpHz4.getOrgid9perval());
		
		JSONObject json=new JSONObject();
		json.put("yearmonthname",yearmonthname_str);
		json.put("orgname",jaorgname);
		json.put("percent",japercent);
		json.put("currentPercent",currentPercent);
		return json;
	}
	
}
