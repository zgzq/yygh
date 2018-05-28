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
import com.wx.cp.model.KpiChartsDataTb;

@Menu("门诊量(同比)")
@RequestMapping("/charts/mzl_tb")
@Controller
public class ChartsMzlTbController   extends AuthController {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(ChartsMzlTbController.class);
	
	//门诊量(同比)
	@Action("门诊量(同比)")
	@RequestMapping("/list")
	public String mzl(Model m) {
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
		return "charts/mzl_tb";
	}
	
	@Action("门诊量(同比)")
	@ResponseBody
	@RequestMapping("/getMzlTb")
	public JSONObject getMzl(
			@RequestParam String dt,
			@RequestParam String orgid,
			@RequestParam String orgname) {
		
		DecimalFormat   df   =new   DecimalFormat("#.00");  
		
		Integer iorgid=Integer.parseInt(orgid);
		String year1="2015";
		String year2="2014";
		
		JSONObject json=new JSONObject();//返回的json
		JSONArray ja=new JSONArray();//纵轴
		JSONArray ja1=new JSONArray();//横轴数据1--data1
		JSONArray ja2=new JSONArray();//横轴数据2--data2
		
		List<KpiChartsDataTb> list1=KpiChartsDataTb.INSTANCE.query(" type='mzl' and year=? and dt=? and orgid=? order by seq ",year1,dt,iorgid);
		List<KpiChartsDataTb> list2=KpiChartsDataTb.INSTANCE.query(" type='mzl' and year=? and dt=? and orgid=? order by seq ",year2,dt,iorgid);
		if (dt.equals("年")){
//			ja.add(year1+"年");
//			ja.add(year2+"年");
		}else{
			for (int i = 0; i < list1.size(); i++) {
				ja.add(list1.get(i).getCategory());
			}
		}
		for (int i = 0; i < list1.size(); i++) {
			Integer data=list1.get(i).getData().intValue();
			ja1.add(data);//保留两位小数
		}
		for (int i = 0; i < list2.size(); i++) {
			Integer data=list2.get(i).getData().intValue();
			ja2.add(data);//保留两位小数
		}
		json.put("date", ja);
		json.put("data1", ja1);
		json.put("data2", ja2);
		json.put("orgname", orgname);
		return json;
	}
	
	
}
