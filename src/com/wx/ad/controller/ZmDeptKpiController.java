package com.wx.ad.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import my.ann.Action;
import my.ann.Menu;
import my.dao.pool.DBManager;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.ad.bean.GridColumn;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.dbo.KpiValueMonthV;
import com.wx.ad.dbo.ValueRange;
import com.wx.ad.dbo.ZmDeptValue;
import com.wx.ad.util.CalendarDateOP;
import com.wx.ad.util.ExcelExpUtils;
import com.wx.ad.util.ExcelExpUtils.Visitor;
import com.wx.ad.web.AuthController;

@Menu("中美医院重点科室")
@RequestMapping("/kpi/zmdept")
@Controller
public class ZmDeptKpiController extends AuthController {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(ZmDeptKpiController.class);

	@Action("中美医院重点科室")
	@RequestMapping("/list")
	public String index(Model m) {
		List<KpiValueMonthV> kpiValueMonthV=KpiValueMonthV.INSTANCE.query("planid=? and orgid=?", 13,1);
		List<String> listMonth=new ArrayList<String>();
		for (KpiValueMonthV kvc : kpiValueMonthV) {
			listMonth.add(kvc.getMonth());
		}
		m.addAttribute("month", listMonth);
		return "admin/zmdeptrpt/list";
	}


	@Action("中美医院重点科室")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<ZmDeptValue> gridData() {
		String planid="13";
		String orgid="1";
		String sdate = param("sdate", "");
		String edate = param("edate", "");
		//日期转换为yyyymmdd
		sdate=sdate.replaceAll("-", "");
		edate=edate.replaceAll("-", "");
		//生成数据
		genValue(sdate, edate, planid,orgid);
		//取出科室数据，拼接model
		List<ZmDeptValue> list=new ArrayList<ZmDeptValue>();
		List<ValueRange> xhk=getDeptvalue(sdate, edate, planid,"xhk");
		List<ValueRange> xnk=getDeptvalue(sdate, edate, planid,"xnk");
		List<ValueRange> fk=getDeptvalue(sdate, edate, planid,"fk");
		List<ValueRange> gk=getDeptvalue(sdate, edate, planid,"gk");
		List<ValueRange> zlk=getDeptvalue(sdate, edate, planid,"zlk");
		List<ValueRange> ek=getDeptvalue(sdate, edate, planid,"ek");
		List<ValueRange> ck=getDeptvalue(sdate, edate, planid,"ck");
		for(int i=0;i<xhk.size();i++){
			ValueRange dict=xhk.get(i);
			ZmDeptValue zv=new ZmDeptValue();
			zv.setDictname(dict.getDictname());
			zv.setXhk(dict.getKpivalue());
			zv.setFk(fk.get(i).getKpivalue());
			zv.setXnk(xnk.get(i).getKpivalue());
			zv.setGk(gk.get(i).getKpivalue());
			zv.setZlk(zlk.get(i).getKpivalue());
			zv.setEk(ek.get(i).getKpivalue());
			zv.setCk(ck.get(i).getKpivalue());
			list.add(zv);
		}
		GridDataModel<ZmDeptValue> gd=new GridDataModel<ZmDeptValue>();
		gd.setRows(list);
		return gd;
	}
	
	
	@Action("中美医院重点科室")
	@ResponseBody
	@RequestMapping("/gridColumn")
	public JSONArray gridColumn() {
		JSONArray jsonArray=new JSONArray();
		jsonArray.add((JSONObject) JSONObject.toJSON(new GridColumn("指标名称", "dictname", "200", "center")));
		jsonArray.add((JSONObject) JSONObject.toJSON(new GridColumn("消化科", "xhk", "100", "center")));
		jsonArray.add((JSONObject) JSONObject.toJSON(new GridColumn("心内科", "xnk", "100", "center")));
		jsonArray.add((JSONObject) JSONObject.toJSON(new GridColumn("妇科", "fk", "100", "center")));
		jsonArray.add((JSONObject) JSONObject.toJSON(new GridColumn("骨科", "gk", "100", "center")));
		jsonArray.add((JSONObject) JSONObject.toJSON(new GridColumn("肿瘤科", "zlk", "100", "center")));
		jsonArray.add((JSONObject) JSONObject.toJSON(new GridColumn("儿科", "ek", "100", "center")));
		jsonArray.add((JSONObject) JSONObject.toJSON(new GridColumn("产科", "ck", "100", "center")));
		return jsonArray;
	}
	
	
	private void genValue(String sdate,String edate ,String planid,String orgid){
		// 调用存储过程\\
		Connection con = null;
		try {
			con = DBManager.getConnection();
			CallableStatement callstatement = con
					.prepareCall("call kpicalpk.p_kpi_range(?,?,?,?,?)");//
					//.prepareCall("call zmkpipk.p_getkpivaue(?,?,?,?,?)");//
			/*callstatement.setString(1, sdate);
			callstatement.setString(2, edate); 
			callstatement.setString(3, "2");
			callstatement.setString(4, planid);
			callstatement.setString(5, getLoginUer().getUserId());*/
			callstatement.setString(1, sdate);
			callstatement.setString(2, edate);
			callstatement.setString(3, getLoginUer().getUserId());
			callstatement.setString(4, planid);
			callstatement.setString(5, orgid);
			callstatement.execute();// 执行
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {

//			try {
//				DBManager.commitAll();
//				if (con != null) {
//					con.close();
//					DBManager.closeAllConnection();
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}
	}
	
	private List<ValueRange> getDeptvalue(String sdate,String edate,String planid,String deptcode){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and planid=? and psdate=? and pedate=? and userid=? and deptcode=? order by korder");
		params.add(planid);
		params.add(sdate);
		params.add(edate);
		params.add(getLoginUer().getUserId());
		params.add(deptcode);
		List<ValueRange> list=ValueRange.INSTANCE.query(filter.toString(), params.toArray());
		return list;
	}

	@Action("中美医院重点科室")
	@ResponseBody
	@RequestMapping("/download")
	public String download() {
		String json=param("json","");
		String month=param("month", "");
		String sdate = month+"01";
		String edate = CalendarDateOP.getLastDayOfMonth(month);
		GridDataModel model = JSON.parseObject(json, GridDataModel.class);
		final List<ZmDeptValue> list=JSON.parseArray(model.getRows().toString(), ZmDeptValue.class);
		//final List<ValueRange> list=JSON.parseArray(json, ValueRange.class);
		String filename="中美重点科室指标"+sdate+"-"+edate;
		ExcelExpUtils utils = new ExcelExpUtils(getResponse(), filename+".xls");
		String titles = "指标名称,消化科,心内科,妇科,骨科,肿瘤科,儿科,产科";
		String column = "dictname,xhk,xnk,fk,gk,zlk,ek,ck";
		utils.export(filename,titles.split(","), column.split(","), new Visitor() {
			
			@Override
			public String getValue(int j,String name) {
				if(name.equals("dictname")){
					return list.get(j).getDictname();
				}else if(name.equals("xhk")){
					return list.get(j).getXhk();
				}else if(name.equals("xnk")){
					return list.get(j).getXnk();
				}else if(name.equals("fk")){
					return list.get(j).getFk();
				}else if(name.equals("gk")){
					return list.get(j).getGk();
				}else if(name.equals("zlk")){
					return list.get(j).getZlk();
				}else if(name.equals("ek")){
					return list.get(j).getEk();
				}else if(name.equals("ck")){
					return list.get(j).getCk();
				}
				return "";
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
