package com.wx.ad.controller.rpthb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.dbo.KpiValueHb;
import com.wx.ad.dbo.KpiValueHbMonthV;
import com.wx.ad.util.CalendarDateOP;
import com.wx.ad.util.ExcelExpUtils;
import com.wx.ad.util.ExcelExpUtils.Visitor;
import com.wx.ad.web.AuthController;

import my.ann.Action;
import my.ann.Menu;
@Menu("(合并)誉美")
@RequestMapping("/kpi/rpthbym")
@Controller
public class RptHbYm  extends AuthController{
	private int orgid=2;
	
	@Action("(合并)誉美")
	@RequestMapping("/listym")
	public String index(Model m) {
		List<KpiValueHbMonthV> kpiValueHbMonthV=KpiValueHbMonthV.INSTANCE.query("userid=? and orgid=?", "-1",orgid);
		List<String> listMonth=new ArrayList<String>();
		for (KpiValueHbMonthV kvc : kpiValueHbMonthV) {
			listMonth.add(kvc.getMonth());
		}
		m.addAttribute("month", listMonth);
		return "admin/rpthb/listym";
	}
	
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<KpiValueHb> gridData() {
		String month=param("month", "");
		String psdate = month+"01";
		String pedate = CalendarDateOP.getLastDayOfMonth(month);
		List<KpiValueHb> list=new ArrayList<KpiValueHb>();
		list=KpiValueHb.INSTANCE.query("psdate=? and pedate=? and userid=? and orgid=? order by seq ", psdate,pedate,"-1",orgid);
		GridDataModel<KpiValueHb> gd=new GridDataModel<KpiValueHb>();
		gd.setRows(list);
		return gd;
	}
	
	@Action("(合并)誉美")
	@ResponseBody
	@RequestMapping("/download")
	public String download() {
		String json=param("json","");
		String month=param("month", "");
		String sdate = month+"01";
		String edate = CalendarDateOP.getLastDayOfMonth(month);
		GridDataModel model = JSON.parseObject(json, GridDataModel.class);
		final List<KpiValueHb> list=JSON.parseArray(model.getRows().toString(), KpiValueHb.class);
		String filename="(合并)誉美"+sdate+"-"+edate;
		ExcelExpUtils utils = new ExcelExpUtils(getResponse(), filename+".xls");
		

		String titles = "门诊科室,合计,初诊,复诊,专家,住院科室,原有数,入院数,转入数,出院数,转出数,现有数,实际用,开放数,病床用率,加床,空床";
		String column = "mzks,hj,cz,fz,zj,zyks,yys,rys,zrs,cys,zcs,xys,sjy,kfs,bcyl,jc,kc";
		utils.export(filename,titles.split(","), column.split(","), new Visitor() {
			
			@Override
			public String getValue(int j,String name) {
				return 	name.equals("mzks")?list.get(j).getMzks():
						name.equals("hj")?list.get(j).getHj():
						name.equals("cz")?list.get(j).getCz():
						name.equals("fz")?list.get(j).getFz():	
						name.equals("jz")?list.get(j).getJz():
						name.equals("zj")?list.get(j).getZj():	
						name.equals("zyks")?list.get(j).getZyks():	
						name.equals("yys")?list.get(j).getYys():	
						name.equals("rys")?list.get(j).getRys():	
						name.equals("zrs")?list.get(j).getZrs():	
						name.equals("cys")?list.get(j).getCys():	
						name.equals("zcs")?list.get(j).getZcs():
						name.equals("xys")?list.get(j).getXys():	
						name.equals("sjy")?list.get(j).getSjy():	
						name.equals("kfs")?list.get(j).getKfs():	
						name.equals("bcyl")?list.get(j).getBcyl():	
						name.equals("jc")?list.get(j).getJc():	
						name.equals("kc")?list.get(j).getKc():"";
			}
			
			@Override
			public int getSize() {
				if (list==null){
					return 0;
				}
				return list.size();
			}
		});
		return null;
	}	
}
