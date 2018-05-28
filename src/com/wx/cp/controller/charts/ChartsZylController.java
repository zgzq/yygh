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
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.KpiValueWithDate;
import com.wx.ad.dbo.Role;
import com.wx.ad.dbo.UsrOrg;
import com.wx.ad.dbo.ValueRange;
import com.wx.ad.util.ExcelExpUtils;
import com.wx.ad.util.ExcelExpUtils.Visitor;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

@Menu("住院量")
@RequestMapping("/charts/zyl")
@Controller
public class ChartsZylController  extends AuthController {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(ChartsZylController.class);
	
	//住院量（包含 入院量和 出院量 ）
	@Action("住院量")
	@RequestMapping("/list")
	public String zyl(Model m) {
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
		return "charts/zyl";
	}
	
	@Action("住院量")
	@ResponseBody
	@RequestMapping("/getZyl")
	public JSONObject getZyl(
			@RequestParam String yearmonth,
			@RequestParam String orgid,
			@RequestParam String orgname) {
		Integer iyear=Integer.parseInt(yearmonth.substring(0, 4));
		Integer imonth=Integer.parseInt(yearmonth.substring(5, 7));
		Integer iorgid=Integer.parseInt(orgid);
		int kpiidRy=-1;
		int kpiidCy=-1;
		if(iorgid==1){//中美医院---
			kpiidRy=336;
			kpiidCy=337;
		}else if(iorgid==2){//誉美医院---
			kpiidRy=399;
			kpiidCy=402;
		}else if(iorgid==3){//中美东城医院---
			kpiidRy=118;
			kpiidCy=119;
		}else if(iorgid==4){//河南整形医院---
			kpiidRy=-1;
			kpiidCy=-1;
		}else if(iorgid==5){//河南誉美医院
			kpiidRy=208;
			kpiidCy=211;
		}else if(iorgid==6){//CD医院
			kpiidRy=-1;
			kpiidCy=-1;
		}else if(iorgid==7){//株洲同济医院----
			kpiidRy=275;
			kpiidCy=278;
		}else if(iorgid==8){//郑州京美医院
			kpiidRy=576;
			kpiidCy=577;
		}else if(iorgid==9){//HM医院
			kpiidRy=508;
			kpiidCy=511;
		}else{
			kpiidRy=-1;
			kpiidCy=-1;
		}
		
		List<KpiValueWithDate> listRy=KpiValueWithDate.INSTANCE.query("year=? and month=? and kpiid=?  order by day",iyear,imonth ,kpiidRy);
		List<KpiValueWithDate> listCy=KpiValueWithDate.INSTANCE.query("year=? and month=? and kpiid=?  order by day",iyear,imonth ,kpiidCy);
		JSONArray ja1=new JSONArray();
		JSONArray jaRy=new JSONArray();
		JSONArray jaCy=new JSONArray();
		for (int i = 0; i < listRy.size(); i++) {
			ja1.add(listRy.get(i).getDay());
			jaRy.add(listRy.get(i).getKpivalue());
		}
		for (int i = 0; i < listCy.size(); i++) {
			jaCy.add(listCy.get(i).getKpivalue());
		}
		JSONObject json=new JSONObject();
		json.put("day", ja1);
		json.put("data1", jaRy);
		json.put("data2", jaCy);
		json.put("orgname", orgname);
		
		return json;
	}
	
	

}
