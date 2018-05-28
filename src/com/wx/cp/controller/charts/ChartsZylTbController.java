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

@Menu("住院量(同比)")
@RequestMapping("/charts/zyl_tb")
@Controller
public class ChartsZylTbController  extends AuthController {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(ChartsZylTbController.class);
	
	//住院量(同比)（包含 入院量和 出院量 ）
	@Action("住院量(同比)")
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
		return "charts/zyl_tb";
	}
	
	@Action("住院量(同比)")
	@ResponseBody
	@RequestMapping("/getZylTb")
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
				ja.add(list1_ryl.get(i).getCategory());
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
		json.put("date", ja);
		json.put("data1_ryl", ja1_ryl);
		json.put("data1_cyl", ja1_cyl);
		json.put("data2_ryl", ja2_ryl);
		json.put("data2_cyl", ja2_cyl);
		json.put("orgname", orgname);
		return json;
	}
	
	

}
