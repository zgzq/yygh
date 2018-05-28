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

@Menu("收入|门诊量|住院量|(同比)")
@RequestMapping("/charts/tb3")
@Controller
public class Charts3in1TbController  extends AuthController {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(Charts3in1TbController.class);
	
	//3 in 1 (同比)
	@Action("收入|门诊量|住院量|(同比)")
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
		return "charts/tb3";
	}
	
	@Action("收入|门诊量|住院量|(同比)")
	@ResponseBody
	@RequestMapping("/gettb3")
	public JSONObject getSr(
			@RequestParam String dt,
			@RequestParam String orgid,
			@RequestParam String orgname) {
		
		int kpiid=-1;
		DecimalFormat   df   =new   DecimalFormat("#.00");  
		Integer iorgid=Integer.parseInt(orgid);
		String year1="2015";
		String year2="2014";
		JSONObject json=new JSONObject();//返回的json
		
		
		/**
		 * 日收入
		 */
		
		JSONArray ja_sr=new JSONArray();//纵轴
		JSONArray ja1_sr=new JSONArray();//横轴数据1--data1
		JSONArray ja2_sr=new JSONArray();//横轴数据2--data2
		
		List<KpiChartsDataTb> list1_sr=KpiChartsDataTb.INSTANCE.query(" type='sr' and year=? and dt=? and orgid=? order by seq ",year1,dt,iorgid);
		List<KpiChartsDataTb> list2_sr=KpiChartsDataTb.INSTANCE.query(" type='sr' and year=? and dt=? and orgid=? order by seq ",year2,dt,iorgid);
		if (dt.equals("年")){
//			ja_sr.add(year1+"年");
//			ja_sr.add(year2+"年");
		}else{
			for (int i = 0; i < list1_sr.size(); i++) {
				ja_sr.add(list1_sr.get(i).getCategory());
			}
		}
		for (int i = 0; i < list1_sr.size(); i++) {
			Double data=list1_sr.get(i).getData().doubleValue()/10000;
			ja1_sr.add(df.format(data));//保留两位小数
		}
		for (int i = 0; i < list2_sr.size(); i++) {
			Double data=list2_sr.get(i).getData().doubleValue()/10000;
			ja2_sr.add(df.format(data));//保留两位小数
		}
		json.put("date", ja_sr);
		json.put("data1_sr", ja1_sr);
		json.put("data2_sr", ja2_sr);
		json.put("orgname", orgname);
		/**
		 * 门诊量
		 */
		JSONArray ja_mzl=new JSONArray();//纵轴
		JSONArray ja1_mzl=new JSONArray();//横轴数据1--data1
		JSONArray ja2_mzl=new JSONArray();//横轴数据2--data2
		List<KpiChartsDataTb> list1_mzl=KpiChartsDataTb.INSTANCE.query(" type='mzl' and year=? and dt=? and orgid=? order by seq ",year1,dt,iorgid);
		List<KpiChartsDataTb> list2_mzl=KpiChartsDataTb.INSTANCE.query(" type='mzl' and year=? and dt=? and orgid=? order by seq ",year2,dt,iorgid);
		if (dt.equals("年")){
//			ja.add(year1+"年");
//			ja.add(year2+"年");
		}else{
			for (int i = 0; i < list1_mzl.size(); i++) {
				ja_mzl.add(list1_mzl.get(i).getCategory());
			}
		}
		for (int i = 0; i < list1_mzl.size(); i++) {
			Integer data=list1_mzl.get(i).getData().intValue();
			ja1_mzl.add(data);//保留两位小数
		}
		for (int i = 0; i < list2_mzl.size(); i++) {
			Integer data=list2_mzl.get(i).getData().intValue();
			ja2_mzl.add(data);//保留两位小数
		}
		
		json.put("data1_mzl", ja1_mzl);
		json.put("data2_mzl", ja2_mzl);


		/**
		 * 住院量
		 */
		JSONArray ja1_ryl=new JSONArray();//横轴数据1--data1
		JSONArray ja1_cyl=new JSONArray();//横轴数据1--data1
		JSONArray ja2_ryl=new JSONArray();//横轴数据2--data2
		JSONArray ja2_cyl=new JSONArray();//横轴数据2--data2
		
		
		List<KpiChartsDataTb> list1_ryl=KpiChartsDataTb.INSTANCE.query(" type='ryl' and year=? and dt=? and orgid=? order by seq ",year1,dt,iorgid);
		List<KpiChartsDataTb> list1_cyl=KpiChartsDataTb.INSTANCE.query(" type='cyl' and year=? and dt=? and orgid=? order by seq ",year1,dt,iorgid);
		List<KpiChartsDataTb> list2_ryl=KpiChartsDataTb.INSTANCE.query(" type='ryl' and year=? and dt=? and orgid=? order by seq ",year2,dt,iorgid);
		List<KpiChartsDataTb> list2_cyl=KpiChartsDataTb.INSTANCE.query(" type='cyl' and year=? and dt=? and orgid=? order by seq ",year2,dt,iorgid);
		if (dt.equals("年")){
//			ja.add(year1+"年");
//			ja.add(year2+"年");
		}else{
			for (int i = 0; i < list1_ryl.size(); i++) {
//				ja.add(list1_ryl.get(i).getCategory());
			}
		}
		
		for (int i = 0; i < list1_ryl.size(); i++) {
			Integer data=list1_ryl.get(i).getData().intValue();
			ja1_ryl.add(data);
		}
		for (int i = 0; i < list1_cyl.size(); i++) {
			Integer data=list1_cyl.get(i).getData().intValue();
			ja1_cyl.add(data);
		}
		for (int i = 0; i < list2_ryl.size(); i++) {
			Integer data=list2_ryl.get(i).getData().intValue();
			ja2_ryl.add(data);
		}
		for (int i = 0; i < list2_cyl.size(); i++) {
			Integer data=list2_cyl.get(i).getData().intValue();
			ja2_cyl.add(data);
		}
//		json.put("date", ja);
		json.put("data1_ryl", ja1_ryl);
		json.put("data1_cyl", ja1_cyl);
		json.put("data2_ryl", ja2_ryl);
		json.put("data2_cyl", ja2_cyl);
		
		
		return json;
	}
	
}
