package com.wx.cp.controller.charts;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.ann.Action;
import my.ann.Menu;
import my.dao.pool.DBManager;
import my.dao.utils.QueryHelper;
import my.poi.ExcelUtils;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.controller.kpimp.KpimpUtil;
import com.wx.ad.dbo.KpiDateV;
import com.wx.ad.dbo.KpiValueMpHz3;
import com.wx.ad.dbo.KpiValueMpHz4;
import com.wx.ad.dbo.KpiValueWithDate;
import com.wx.ad.dbo.Role;
import com.wx.ad.dbo.UsrOrg;
import com.wx.ad.dbo.ValueRange;
import com.wx.ad.util.ExcelExpUtils;
import com.wx.ad.util.ExcelExpUtils.Visitor;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;
import com.wx.cp.model.KpiChartsDataTb;

@Menu("经营目标进度")
@RequestMapping("/charts/manageplan")
@Controller
public class ChartsManagePlanController  extends AuthController  {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(ChartsManagePlanController.class);


	@Action("经营目标进度")
	@RequestMapping("/list") 
	public String sr(Model m) {
		String orgname=param("orgname", "");
		String pdate=param("pdate", "");
		String userid=param("userid", "");
		String ss=param("ss", "");
		List<UsrOrg> orgs=UsrOrg.INSTANCE.query("id<>0");
		m.addAttribute("orgs", orgs);
		m.addAttribute("pdate", pdate);
		m.addAttribute("userid", userid);
		m.addAttribute("orgname", orgname);
		m.addAttribute("ss", ss);
		return "charts/manageplan";
	}
	
	@Action("经营目标进度")
	@ResponseBody
	@RequestMapping("/getdata")
	public JSONObject getdata() {
		String yearmonthname=param("yearmonthname", "");
		String yearmonth=param("yearmonth", "");
		String userid=param("userid", "");
		String ss=param("ss", "");
		JSONObject json=new JSONObject();//返回的json

		JSONArray jaorgname=new JSONArray();
		JSONArray japercent_sr=new JSONArray();
		JSONArray japercent_mzl=new JSONArray();
		JSONArray japercent_zyl=new JSONArray();
		//查询数据
		KpiDateV kpiDateV1=KpiDateV.INSTANCE.queryOne(" dt in (select min(dt) from kpi_date_v where yearmonth=? ) ", yearmonth);
		String psdate=kpiDateV1.getDt().toString();
		KpiDateV kpiDateV2=KpiDateV.INSTANCE.queryOne(" dt in (select max(dt) from kpi_date_v where yearmonth=? ) ", yearmonth);
		String pedate=kpiDateV2.getDt().toString();
		
		if (ss.equals("1")){ /*上市医院*/
			//生成数据
			KpimpUtil.genValueHz5( yearmonth, userid);
			
			KpiValueMpHz3 kpiValueMpHz3_sr=KpiValueMpHz3.INSTANCE.queryOne("and psdate = ? and pedate=? and userid=? and "
					+ "kpiname='收入(万元)' ", psdate,pedate,userid);
			japercent_sr.add(kpiValueMpHz3_sr.getOrgid1perval());
			japercent_sr.add(kpiValueMpHz3_sr.getOrgid2perval());
			japercent_sr.add(kpiValueMpHz3_sr.getOrgid3perval());
			japercent_sr.add(kpiValueMpHz3_sr.getOrgid4perval());
			japercent_sr.add(kpiValueMpHz3_sr.getOrgid5perval());
			japercent_sr.add(kpiValueMpHz3_sr.getOrgid7perval());
			
			KpiValueMpHz3 kpiValueMpHz3_mzl=KpiValueMpHz3.INSTANCE.queryOne("and psdate = ? and pedate=? and userid=? and "
					+ "kpiname='门诊量(人次)' ", psdate,pedate,userid);
			japercent_mzl.add(kpiValueMpHz3_mzl.getOrgid1perval());
			japercent_mzl.add(kpiValueMpHz3_mzl.getOrgid2perval());
			japercent_mzl.add(kpiValueMpHz3_mzl.getOrgid3perval());
			japercent_mzl.add(kpiValueMpHz3_mzl.getOrgid4perval());
			japercent_mzl.add(kpiValueMpHz3_mzl.getOrgid5perval());
			japercent_mzl.add(kpiValueMpHz3_mzl.getOrgid7perval());
			
			KpiValueMpHz3 kpiValueMpHz3_zyl=KpiValueMpHz3.INSTANCE.queryOne("and psdate = ? and pedate=? and userid=? and "
					+ "kpiname='住院量(人次)' ", psdate,pedate,userid);
			japercent_zyl.add(kpiValueMpHz3_zyl.getOrgid1perval());
			japercent_zyl.add(kpiValueMpHz3_zyl.getOrgid2perval());
			japercent_zyl.add(kpiValueMpHz3_zyl.getOrgid3perval());
			japercent_zyl.add(kpiValueMpHz3_zyl.getOrgid4perval());
			japercent_zyl.add(kpiValueMpHz3_zyl.getOrgid5perval());
			japercent_zyl.add(kpiValueMpHz3_zyl.getOrgid7perval());
			
			jaorgname.add("京东中美");
			jaorgname.add("京东誉美");
			jaorgname.add("中美东城");
			jaorgname.add("河南整形");
			jaorgname.add("河南誉美");
			jaorgname.add("株洲同济");
			
			json.put("yearmonthname",yearmonthname);
			json.put("orgname",jaorgname);
			json.put("percent_sr",japercent_sr);
			json.put("percent_mzl",japercent_mzl);
			json.put("percent_zyl",japercent_zyl);
			return json;
		}else if(ss.equals("0")){/*非上市医院*/
			//生成数据
			KpimpUtil.genValueHz6( yearmonth, userid);
			
			KpiValueMpHz4 kpiValueMpHz4_sr=KpiValueMpHz4.INSTANCE.queryOne("and psdate = ? and pedate=? and userid=? and "
					+ "kpiname='收入(万元)' ", psdate,pedate,userid);
			japercent_sr.add(kpiValueMpHz4_sr.getOrgid6perval());
			japercent_sr.add(kpiValueMpHz4_sr.getOrgid8perval());
			japercent_sr.add(kpiValueMpHz4_sr.getOrgid9perval());
			
			KpiValueMpHz4 kpiValueMpHz4_mzl=KpiValueMpHz4.INSTANCE.queryOne("and psdate = ? and pedate=? and userid=? and "
					+ "kpiname='门诊量(人次)' ", psdate,pedate,userid);
			japercent_mzl.add(kpiValueMpHz4_mzl.getOrgid6perval());
			japercent_mzl.add(kpiValueMpHz4_mzl.getOrgid8perval());
			japercent_mzl.add(kpiValueMpHz4_mzl.getOrgid9perval());
			
			KpiValueMpHz4 kpiValueMpHz4_zyl=KpiValueMpHz4.INSTANCE.queryOne("and psdate = ? and pedate=? and userid=? and "
					+ "kpiname='住院量(人次)' ", psdate,pedate,userid);
			japercent_zyl.add(kpiValueMpHz4_zyl.getOrgid6perval());
			japercent_zyl.add(kpiValueMpHz4_zyl.getOrgid8perval());
			japercent_zyl.add(kpiValueMpHz4_zyl.getOrgid9perval());
			
			jaorgname.add("成都誉美");
			jaorgname.add("郑州京美");
			jaorgname.add("常德惠民");
			
			json.put("yearmonthname",yearmonthname);
			json.put("orgname",jaorgname);
			json.put("percent_sr",japercent_sr);
			json.put("percent_mzl",japercent_mzl);
			json.put("percent_zyl",japercent_zyl);
			
			return json;
		}else{
			return null;
		}
			
	}
	
}
