package com.wx.ad.controller.rptym;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import my.ann.Action;
import my.ann.Menu;
import my.dao.utils.Record;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.ad.bean.GridColumn;
import com.wx.ad.bean.GridDataCross;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.controller.RptController;
import com.wx.ad.dbo.KpiValueCross;
import com.wx.ad.dbo.KpiValueCrossColnameV;
import com.wx.ad.dbo.KpiValueCrossMonthV;
import com.wx.ad.dbo.KpiValueCrossRownameV;
import com.wx.ad.util.CalendarDateOP;
import com.wx.ad.util.ExcelExpUtils;
import com.wx.ad.web.AuthController;

import com.wx.ad.util.ExcelExpUtils;
import com.wx.ad.util.ExcelExpUtils.Visitor;
import com.wx.ad.web.AuthController;


@Menu("ym5门诊收入统计(开单)")
@RequestMapping("/kpi/rptym5")
@Controller
public class RptYm5Controller extends AuthController {
	private final String RPTNAME="ym5门诊收入统计(开单)";
	private final String ROWNAME="科室";
	private int orgid=2;
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(RptYm1Controller.class);

	@Action("ym5门诊收入统计(开单)")
	@RequestMapping("/list")
	public String index(Model m) {
		List<KpiValueCrossMonthV> kpiValueCrossMonthV=KpiValueCrossMonthV.INSTANCE.query("rptname=? and userid=? and orgid=?", RPTNAME,"-1",orgid);
		List<String> listMonth=new ArrayList<String>();
		for (KpiValueCrossMonthV kvc : kpiValueCrossMonthV) {
			listMonth.add(kvc.getMonth());
		}
		m.addAttribute("month", listMonth);
		return "admin/rptym/rptym5/list";
	}

	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<Record> gridData() {
		String month=param("month", "");
		List<Record> list=RptController.gridData(month, RPTNAME,"-1", orgid);
		GridDataModel<Record> gd=new GridDataModel<Record>();
		gd.setRows(list);
		return gd;
	}
	
	
	/**
	 * 生成cloumn
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/gridColumn")
	public JSONArray gridColumn() {
		String month=param("month", "");
		JSONArray jsonArray=new JSONArray();
		jsonArray=RptController.gridColumn(month,RPTNAME,ROWNAME,"-1", orgid);
		return jsonArray;
	}

	@ResponseBody
	@RequestMapping("/download")
	public String download() {
		String json=param("json","");
		String month=param("month", "");
		String sdate = month+"01";
		String edate = CalendarDateOP.getLastDayOfMonth(month);
		GridDataModel model = JSON.parseObject(json, GridDataModel.class);
		final List<Record> list=JSON.parseArray(model.getRows().toString(), Record.class);
		String filename=RPTNAME+sdate+"-"+edate;
		ExcelExpUtils utils = new ExcelExpUtils(getResponse(), filename + ".xls");
		List<String> listTitles=new ArrayList<String>();
		List<String> listColumns=new ArrayList<String>();
		listTitles.add(ROWNAME);
		listColumns.add("rowname");
		List<KpiValueCrossColnameV> kpiValueCrossColnameV=RptController.getColname( RPTNAME, sdate, edate,"-1", orgid);
		for (KpiValueCrossColnameV kvc : kpiValueCrossColnameV) {
			listTitles.add(kvc.getColname());
			listColumns.add(kvc.getColname());
		}
		String[] titles = (String[]) listTitles.toArray(new String[0]);
		String[] columns = (String[]) listColumns.toArray(new String[0]);
		utils.export(filename,titles, columns, new Visitor() {
			@Override
			public String getValue(int j,String name) {
				return list.get(j).getString(name);
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
