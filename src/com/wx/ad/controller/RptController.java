package com.wx.ad.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.ad.bean.GridColumn;
import com.wx.ad.bean.GridDataCross;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.dbo.KpiValueCross;
import com.wx.ad.dbo.KpiValueCrossColnameV;
import com.wx.ad.dbo.KpiValueCrossRownameV;
import com.wx.ad.util.CalendarDateOP;
import com.wx.ad.util.ExcelExpUtils;
import com.wx.ad.util.ExcelExpUtils.Visitor;
import com.wx.ad.web.AuthController;

import my.dao.utils.Record;


public class RptController {
	public static List<Record> gridData(String month,String RPTNAME,String userid,int orgid){
		String sdate = month+"01";
		String edate = CalendarDateOP.getLastDayOfMonth(month);
		//生成数据
		//genValue();
		//取出数据，拼接model
		List<Record> list=new ArrayList<Record>(); 
		List<KpiValueCrossColnameV> listValueCol=getColname( RPTNAME, sdate, edate,userid,orgid);
		List<KpiValueCrossRownameV> listValueRow=getRowname( RPTNAME, sdate, edate,userid,orgid);
		//
		for (KpiValueCrossRownameV kpiValueCrossRownameV : listValueRow) {
			Record record=new Record();
			GridDataCross gridDataCross=new GridDataCross();
			gridDataCross.setRowname(kpiValueCrossRownameV.getRowname());
			gridDataCross.setColnames(new LinkedHashMap<String,String>());
			for (int i = 0; i < listValueCol.size(); i++) {
				KpiValueCross kpiValueCross =getValueCross(RPTNAME, sdate, edate, 
						kpiValueCrossRownameV.getRowname(),listValueCol.get(i).getColname(),userid,orgid);
				if(kpiValueCross.getValue()==null){
					gridDataCross.getColnames().put(listValueCol.get(i).getColname(), "");
				}else{
					gridDataCross.getColnames().put(listValueCol.get(i).getColname(), kpiValueCross.getValue());
				}
				
				
			}
			//转换成record
			if(kpiValueCrossRownameV.getRowname() == null){
				record.put("rowname", "");
			}else{
				record.put("rowname", kpiValueCrossRownameV.getRowname());
			}
			record.putAll(gridDataCross.getColnames());
			list.add(record);
		}
		return list;
	}
	public static List<Record> gridDataCan(String month,String RPTNAME,String userid,int orgid){
		String sdate = month+"01";
		String edate = CalendarDateOP.getLastDayOfMonth(month);
		//生成数据
		//genValue();
		//取出数据，拼接model
		List<Record> list=new ArrayList<Record>(); 
		List<KpiValueCrossColnameV> listValueCol=getColname( RPTNAME, sdate, edate,userid,orgid);
		List<KpiValueCrossRownameV> listValueRow=getRownameCan( RPTNAME, sdate, edate,userid,orgid);
		//
		for (KpiValueCrossRownameV kpiValueCrossRownameV : listValueRow) {
			Record record=new Record();
			GridDataCross gridDataCross=new GridDataCross();
			gridDataCross.setRowname(kpiValueCrossRownameV.getRowname());
			gridDataCross.setColnames(new LinkedHashMap<String,String>());
			for (int i = 0; i < listValueCol.size(); i++) {
				KpiValueCross kpiValueCross =getValueCross(RPTNAME, sdate, edate, 
						kpiValueCrossRownameV.getRowname(),listValueCol.get(i).getColname(),userid,orgid);
				if(kpiValueCross.getValue()==null){
					gridDataCross.getColnames().put(listValueCol.get(i).getColname(), "");
				}else{
					gridDataCross.getColnames().put(listValueCol.get(i).getColname(), kpiValueCross.getValue());
				}
			}
			//转换成record
			record.put("rowname", kpiValueCrossRownameV.getRowname());
			record.putAll(gridDataCross.getColnames());
			list.add(record);
		}
		return list;
	}
	public static List<Record> gridDataCan1(String month,String RPTNAME,String userid,int orgid){
		String sdate = month+"01";
		String edate = CalendarDateOP.getLastDayOfMonth(month);
		//生成数据
		//genValue();
		//取出数据，拼接model
		List<Record> list=new ArrayList<Record>(); 
		List<KpiValueCrossColnameV> listValueCol=getColname( RPTNAME, sdate, edate,userid,orgid);
		List<KpiValueCrossRownameV> listValueRow=getRownameCan1( RPTNAME, sdate, edate,userid,orgid);
		//
		for (KpiValueCrossRownameV kpiValueCrossRownameV : listValueRow) {
			Record record=new Record();
			GridDataCross gridDataCross=new GridDataCross();
			gridDataCross.setRowname(kpiValueCrossRownameV.getRowname());
			gridDataCross.setColnames(new LinkedHashMap<String,String>());
			for (int i = 0; i < listValueCol.size(); i++) {
				KpiValueCross kpiValueCross =getValueCross(RPTNAME, sdate, edate, 
						kpiValueCrossRownameV.getRowname(),listValueCol.get(i).getColname(),userid,orgid);
				if(kpiValueCross.getValue()==null){
					gridDataCross.getColnames().put(listValueCol.get(i).getColname(), "");
				}else{
					gridDataCross.getColnames().put(listValueCol.get(i).getColname(), kpiValueCross.getValue());
				}
			}
			//转换成record
			record.put("rowname", kpiValueCrossRownameV.getRowname());
			record.putAll(gridDataCross.getColnames());
			list.add(record);
		}
		return list;
	}
	public static JSONArray gridColumn(String month,String RPTNAME,String ROWNAME,String userid,int orgid) {
		String sdate = month+"01";
		String edate = CalendarDateOP.getLastDayOfMonth(month);
		//取出数据，拼接model
		List<KpiValueCrossColnameV> list=new ArrayList<KpiValueCrossColnameV>();
		list=getColname( RPTNAME, sdate, edate, userid, orgid);
		JSONArray jsonArray=new JSONArray();
		//默认是rowname是ROWNAME
		jsonArray.add(new GridColumn(ROWNAME,"rowname","80","center"));
		for (KpiValueCrossColnameV kpiValueCrossColnameV : list) {
			
			jsonArray.add((JSONObject) JSONObject.toJSON(new GridColumn(
					kpiValueCrossColnameV.getColname(),kpiValueCrossColnameV.getColname(), "80", "center")));
		}
		return jsonArray;
	}
	
	//获取列名
	public static List<KpiValueCrossColnameV> getColname(String rptname,String sdate,String edate,String userid,int orgid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and rptname=? and psdate=? and pedate=? and userid=? and orgid=? ");
		params.add(rptname);
		params.add(sdate);
		params.add(edate);
		params.add(userid);
		params.add(orgid);
		List<KpiValueCrossColnameV> list=KpiValueCrossColnameV.INSTANCE.query(filter.toString(), params.toArray());
		return list;
	}

	//获取行名
	public static List<KpiValueCrossRownameV> getRowname(String rptname,String sdate,String edate,String userid,int orgid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and rptname=? and psdate=? and pedate=? and userid=? and orgid=? ");
		params.add(rptname);
		params.add(sdate);
		params.add(edate);
		params.add(userid);
		params.add(orgid);
		List<KpiValueCrossRownameV> list=KpiValueCrossRownameV.INSTANCE.query(filter.toString(), params.toArray());
		return list;
	}
	//获取行名(肿瘤)
	public static List<KpiValueCrossRownameV> getRownameCan(String rptname,String sdate,String edate,String userid,int orgid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and rptname=? and psdate=? and pedate=? and userid=? and orgid=? and rowname = ?");
		params.add(rptname);
		params.add(sdate);
		params.add(edate);
		params.add(userid);
		params.add(orgid);
		params.add("肿瘤科门诊");
		List<KpiValueCrossRownameV> list=KpiValueCrossRownameV.INSTANCE.query(filter.toString(), params.toArray());
		return list;
	}
	//获取行名(肿瘤)
	public static List<KpiValueCrossRownameV> getRownameCan1(String rptname,String sdate,String edate,String userid,int orgid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and rptname=? and psdate=? and pedate=? and userid=? and orgid=? and rowname = ?");
		params.add(rptname);
		params.add(sdate);
		params.add(edate);
		params.add(userid);
		params.add(orgid);
		params.add("肿瘤科");
		List<KpiValueCrossRownameV> list=KpiValueCrossRownameV.INSTANCE.query(filter.toString(), params.toArray());
		return list;
	}
	//获取值
	public static KpiValueCross getValueCross(String rptname,String sdate,String edate,String rowname,String colname,String userid,int orgid){
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if(rowname == null){
			filter.append(" and rptname=? and psdate=? and pedate=? and userid=? and orgid=? and rowname is null and colname=?  ");
			params.add(rptname);
			params.add(sdate);
			params.add(edate);
			params.add(userid);
			params.add(orgid);
//			params.add(rowname);
			params.add(colname);
			KpiValueCross kpiValueCross=KpiValueCross.INSTANCE.queryOne(filter.toString(), params.toArray());
			return kpiValueCross;
		}else{
			
			filter.append(" and rptname=? and psdate=? and pedate=? and userid=? and orgid=? and rowname=? and colname=?  ");
			params.add(rptname);
			params.add(sdate);
			params.add(edate);
			params.add(userid);
			params.add(orgid);
			params.add(rowname);
			params.add(colname);
			KpiValueCross kpiValueCross=KpiValueCross.INSTANCE.queryOne(filter.toString(), params.toArray());
			return kpiValueCross;
		}
	}

	

}
