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
import com.wx.ad.dbo.KpiValueWithDate;
import com.wx.ad.dbo.Role;
import com.wx.ad.dbo.UsrOrg;
import com.wx.ad.dbo.ValueRange;
import com.wx.ad.util.ExcelExpUtils;
import com.wx.ad.util.ExcelExpUtils.Visitor;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;
import com.wx.cp.model.KpiChartsDataTb;

@Menu("收入|门诊量|住院量|(环比)")
@RequestMapping("/charts/hb3")
@Controller
public class Charts3in1HbController  extends AuthController  {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(Charts3in1HbController.class);
	
	//3 in 1 (环比)
	@Action("收入|门诊量|住院量|(环比)")
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
		return "charts/hb3";
	}
	
	@Action("收入|门诊量|住院量|(环比)")
	@ResponseBody
	@RequestMapping("/gethb3")
	public JSONObject getSr(
			@RequestParam String year,
			@RequestParam String month,
			@RequestParam String orgid,
			@RequestParam String orgname) {
		
		DecimalFormat   df   =new   DecimalFormat("#.00");  
		Integer iyear=Integer.parseInt(year);
		Integer imonth=Integer.parseInt(month);
		Integer iorgid=Integer.parseInt(orgid);
		int kpiid=-1;
		/**
		 * 日收入
		 */
		if(iorgid==1){//中美医院---
			kpiid=308;
		}else if(iorgid==2){//誉美医院---
			kpiid=373;
		}else if(iorgid==3){//中美东城医院---
			kpiid=81;
		}else if(iorgid==4){//河南整形医院---
			kpiid=133;
		}else if(iorgid==5){//河南誉美医院
			kpiid=552;
		}else if(iorgid==6){//CD医院
			kpiid=-1;
		}else if(iorgid==7){//株洲同济医院----
			kpiid=251;
		}else if(iorgid==8){//郑州京美医院
			kpiid=-1;
		}else if(iorgid==9){//HM医院
			kpiid=448;
		}else{
			kpiid=-1;
		}
		
		
		List<KpiValueWithDate> list=KpiValueWithDate.INSTANCE.query("year=? and month=? and kpiid=? order by day",iyear,imonth ,kpiid);
		JSONArray ja1=new JSONArray();
		JSONArray ja2=new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			String kpivalue=list.get(i).getKpivalue();
			Double dkpivalue=Double.parseDouble(kpivalue)/10000;
			kpivalue=df.format(dkpivalue);//保留两位小数
			ja1.add(list.get(i).getDay());
			ja2.add(kpivalue);
		}
		JSONObject json=new JSONObject();
		json.put("day", ja1);
		json.put("data1_sr", ja2);
		json.put("orgname", orgname);
		/**
		 * 门诊量
		 */
		
		if(iorgid==1){//中美医院---
			kpiid=330;
		}else if(iorgid==2){//誉美医院---
			kpiid=396;
		}else if(iorgid==3){//中美东城医院---
			kpiid=115;
		}else if(iorgid==4){//河南整形医院---
			kpiid=-1;
		}else if(iorgid==5){//河南誉美医院
			kpiid=205;
		}else if(iorgid==6){//CD医院
			kpiid=-1;
		}else if(iorgid==7){//株洲同济医院----
			kpiid=272;
		}else if(iorgid==8){//郑州京美医院
			kpiid=-1;
		}else if(iorgid==9){//HM医院
			kpiid=-1;
		}else{
			kpiid=-1;
		}
		
		List<KpiValueWithDate> list1=KpiValueWithDate.INSTANCE.query("year=? and month=? and kpiid=? order by day",iyear,imonth ,kpiid);
		JSONArray ja3=new JSONArray();
		for (int i = 0; i < list1.size(); i++) {
			String kpivalue=list1.get(i).getKpivalue();
			ja3.add(kpivalue);
		}

		json.put("data1_mzl", ja3);
		
		/**
		 * 住院量
		 */
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
			kpiidRy=-1;
			kpiidCy=-1;
		}else if(iorgid==9){//HM医院
			kpiidRy=-1;
			kpiidCy=-1;
		}else{
			kpiidRy=-1;
			kpiidCy=-1;
		}
		
		List<KpiValueWithDate> listRy=KpiValueWithDate.INSTANCE.query("year=? and month=? and kpiid=?  order by day",iyear,imonth ,kpiidRy);
		List<KpiValueWithDate> listCy=KpiValueWithDate.INSTANCE.query("year=? and month=? and kpiid=?  order by day",iyear,imonth ,kpiidCy);
		JSONArray jaRy=new JSONArray();
		JSONArray jaCy=new JSONArray();
		for (int i = 0; i < listRy.size(); i++) {
			jaRy.add(listRy.get(i).getKpivalue());
		}
		for (int i = 0; i < listCy.size(); i++) {
			jaCy.add(listCy.get(i).getKpivalue());
		}
		json.put("data1_ryl", jaRy);
		json.put("data2_cyl", jaCy);
		
		
		
		return json;
	}
	
}
