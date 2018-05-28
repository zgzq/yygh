package com.wx.ad.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import my.ann.Action;
import my.ann.Menu;
import my.dao.pool.DBManager;
import my.poi.ExcelUtils;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.ValueRange;
import com.wx.ad.util.ExcelExpUtils;
import com.wx.ad.util.ExcelExpUtils.Visitor;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

@Menu("河南整形综合")
@RequestMapping("/kpi/hnzx")
@Controller
public class HnzxKpiController extends AuthController {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(HnzxKpiController.class);

	@Action("河南整形综合")
	@RequestMapping("/list")
	public String index() {
		return "admin/hnzxrpt/list";
	}


	@Action("河南整形综合")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<ValueRange> gridData() {
		String planid="74";
		String orgid="4";
		String userid="-999";
		String sdate = param("sdate", "");
		String edate = param("edate", "");
		//日期转换为yyyymmdd
		sdate=sdate.replaceAll("-", "");
		edate=edate.replaceAll("-", "");
		//生成数据
		genValue(sdate, edate, planid,orgid,userid);
		
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and planid=? and psdate=? and pedate=? and userid=?");
		params.add(planid);
		params.add(sdate);
		params.add(edate);
		params.add(userid);
		return PageFactory.newPage(ValueRange.class, filter.toString()," order by korder ",
				params.toArray());
	}
	
	private void genValue(String sdate,String edate ,String planid,String orgid,String userid){
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
			callstatement.setString(3, userid);
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
	
	
	@Action("河南整形综合")
	@ResponseBody
	@RequestMapping("/download")
	public String download() {
		String json=param("json","");
		String sdate=param("sdate","");
		String edate=param("edate","");
		sdate=sdate.replaceAll("-", "");
		edate=edate.replaceAll("-", "");
		GridDataModel model = JSON.parseObject(json, GridDataModel.class);
		final List<ValueRange> list=JSON.parseArray(model.getRows().toString(), ValueRange.class);
		//final List<ValueRange> list=JSON.parseArray(json, ValueRange.class);
		String filename="河南整形综合指标"+sdate+"-"+edate;
		ExcelExpUtils utils = new ExcelExpUtils(getResponse(), filename+".xls");
		String titles = "指标名称,数值";
		String column = "dictname,kpivalue";
		utils.export(filename,titles.split(","), column.split(","), new Visitor() {
			
			@Override
			public String getValue(int j,String name) {
				if(name.equals("dictname")){
					return list.get(j).getDictname();
				}else if(name.equals("kpivalue")){
					return list.get(j).getKpivalue();
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
