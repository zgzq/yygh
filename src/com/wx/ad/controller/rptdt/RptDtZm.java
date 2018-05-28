package com.wx.ad.controller.rptdt;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.dbo.KpiValueCrossMonthV;
import com.wx.ad.dbo.KpiValueDt;
import com.wx.ad.dbo.KpiValueDtMonthV;
import com.wx.ad.util.CalendarDateOP;
import com.wx.ad.util.ExcelExpUtils;
import com.wx.ad.util.ExcelExpUtils.Visitor;
import com.wx.ad.web.AuthController;

import my.ann.Action;
import my.ann.Menu;

@Menu("(动态)中美")
@RequestMapping("/kpi/rptdtzm")
@Controller
public class RptDtZm extends AuthController{
	private int orgid=1;
	
	@Action("(动态)中美")
	@RequestMapping("/listzm")
	public String index(Model m) {
		List<KpiValueDtMonthV> kpiValueDtMonthV=KpiValueDtMonthV.INSTANCE.query("userid=? and orgid=?", "-1",orgid);
		List<String> listMonth=new ArrayList<String>();
		for (KpiValueDtMonthV kvc : kpiValueDtMonthV) {
			listMonth.add(kvc.getMonth());
		}
		m.addAttribute("month", listMonth);
		return "admin/rptdt/listzm";
	}
	
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<KpiValueDt> gridData() {
		String month=param("month", "");
		String psdate = month+"01";
		String pedate = CalendarDateOP.getLastDayOfMonth(month);
		List<KpiValueDt> list=new ArrayList<KpiValueDt>();
		list=KpiValueDt.INSTANCE.query("psdate=? and pedate=? and userid=? and orgid=? order by seq ", psdate,pedate,"-1",orgid);
		GridDataModel<KpiValueDt> gd=new GridDataModel<KpiValueDt>();
		gd.setRows(list);
		return gd;
	}
	
	
	@Action("(动态)中美")
	@ResponseBody
	@RequestMapping("/download")
	public String download() {
		String json=param("json","");
		String month=param("month", "");
		String sdate = month+"01";
		String edate = CalendarDateOP.getLastDayOfMonth(month);
		GridDataModel model = JSON.parseObject(json, GridDataModel.class);
		final List<KpiValueDt> list=JSON.parseArray(model.getRows().toString(), KpiValueDt.class);
		String filename="(动态)中美"+sdate+"-"+edate;
		ExcelExpUtils utils = new ExcelExpUtils(getResponse(), filename+".xls");
		
		String titles = "科室,期末有位,期初院数,期内院数,他科入院,总,合计,治愈,好转,未愈,死亡,其,转往科数,期末院数,出院占总日,实际放床数,平均放位,平均床作,实际用床数,出院者均院数,治(%),好(%),病(%),病床用(%),病床转(次),空床(日),加床(日)";
		String column = "ks,qmyw,qcys,qnys,tkry,z,hj,zy,hz,wy,sw,q,zwks,qmys,cyzzr,sjfcs,pjfw,pjcz,sjycs,cyzjys,zhi,hao,bing,bcy,bcz,kc,jc";
		utils.export(filename,titles.split(","), column.split(","), new Visitor() {
			
			@Override
			public String getValue(int j,String name) {
				return 	name.equals("ks")?list.get(j).getKs():
						name.equals("qmyw")?list.get(j).getQmyw():
						name.equals("qcys")?list.get(j).getQcys():
						name.equals("qnys")?list.get(j).getQnys():
						name.equals("tkry")?list.get(j).getTkry():
						name.equals("z")?list.get(j).getZ():
						name.equals("hj")?list.get(j).getHj():
						name.equals("zy")?list.get(j).getZy():
						name.equals("hz")?list.get(j).getHz():
						name.equals("wy")?list.get(j).getWy():
						name.equals("sw")?list.get(j).getSw():
						name.equals("q")?list.get(j).getQ():
						name.equals("zwks")?list.get(j).getZwks():
						name.equals("qmys")?list.get(j).getQmys():
						name.equals("cyzzr")?list.get(j).getCyzzr():
						name.equals("sjfcs")?list.get(j).getSjfcs():
						name.equals("pjfw")?list.get(j).getPjfw():
						name.equals("pjcz")?list.get(j).getPjcz():
						name.equals("sjycs")?list.get(j).getSjycs():
						name.equals("cyzjys")?list.get(j).getCyzjys():
						name.equals("zhi")?list.get(j).getZhi():
						name.equals("hao")?list.get(j).getHao():
						name.equals("bing")?list.get(j).getBing():
						name.equals("bcy")?list.get(j).getBcy():
						name.equals("bcz")?list.get(j).getBcz():
						name.equals("kc")?list.get(j).getKc():
						name.equals("jc")?list.get(j).getJc():"";
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
