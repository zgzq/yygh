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
import my.util.Param;

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

@Menu("日收入")
@RequestMapping("/charts/sr")
@Controller
public class ChartsRsrController  extends AuthController{
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(ChartsRsrController.class);
	
	//日收入
	@Action("日收入")
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
		return "charts/sr";
	}
	
	@Action("日收入")
	@ResponseBody
	@RequestMapping("/getSr")
	public JSONObject getSr(
			@RequestParam String yearmonth,
			@RequestParam String orgid,
			@RequestParam String orgname) {
		DecimalFormat   df   =new   DecimalFormat("#.00");  
		Integer iyear=Integer.parseInt(yearmonth.substring(0, 4));
		Integer imonth=Integer.parseInt(yearmonth.substring(5, 7));
		Integer iorgid=Integer.parseInt(orgid);
		int kpiid=-1;
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
		}else if(iorgid==6){//成都誉美医院
			kpiid=-1;
		}else if(iorgid==7){//株洲同济医院----
			kpiid=251;
		}else if(iorgid==8){//郑州京美医院
			kpiid=553;
		}else if(iorgid==9){//常德惠民医院
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
		json.put("data1", ja2);
		json.put("orgname", orgname);
		
		return json;
	}
	
}
