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

@Menu("中美重点科室")
@RequestMapping("/charts/zmdept")
@Controller
public class ChartsZmdeptController  extends AuthController {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(ChartsZmdeptController.class);
	
	
	//中美重点科室
	@Action("中美重点科室")
	@RequestMapping("/list")
	public String zmdept(Model m) {
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
		return "charts/zmdept";
	}
	
	
	@Action("中美重点科室")
	@ResponseBody
	@RequestMapping("/getZmdept")
	public JSONObject getZmdept(
			@RequestParam String year,
			@RequestParam String month,
			@RequestParam String day
			) {

		Integer iyear=Integer.parseInt(year); 
		Integer imonth=Integer.parseInt(month);
		String sday=day.length()==1?"0"+day+"号":day+"号";
		
		
		//收入
		List<KpiValueWithDate> listSr=KpiValueWithDate.INSTANCE.query("year=? and month=? and day=? and kpiid=344  ",iyear,imonth ,sday);
		//人数
		List<KpiValueWithDate> listRs=KpiValueWithDate.INSTANCE.query("year=? and month=? and day=? and kpiid=355  ",iyear,imonth ,sday);
		
		
		JSONArray ja1=new JSONArray();
		JSONArray ja2=new JSONArray();
		JSONArray ja3=new JSONArray();
		for (int i = 0; i < listSr.size(); i++) {
			ja1.add(listSr.get(i).getDeptname());
			
			JSONObject json=new JSONObject();
			json.put("value", listSr.get(i).getKpivalue());
			json.put("name", listSr.get(i).getDeptname());
			
			ja2.add(json);
		}
		for (int i = 0; i < listRs.size(); i++) {
			JSONObject json=new JSONObject();
			json.put("value", listRs.get(i).getKpivalue());
			json.put("name", listRs.get(i).getDeptname());
			
			ja3.add(json);
		}
		
		JSONObject json=new JSONObject();
		json.put("data1", ja1);
		json.put("data2", ja2);
		json.put("data3", ja3);
		
		return json;
	}

}
