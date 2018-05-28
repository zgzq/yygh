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
import com.wx.ad.dbo.KpiValueDcDept2;
import com.wx.ad.dbo.ValueRange;
import com.wx.ad.util.ExcelExpUtils;
import com.wx.ad.util.ExcelExpUtils.Visitor;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

@Menu("中美东城重点科室")
@RequestMapping("/kpi/dcdept")
@Controller
public class DcDept2KpiController extends AuthController {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(DcDept2KpiController.class);

	@Action("中美东城重点科室")
	@RequestMapping("/list")
	public String index() {
		return "admin/dcdeptrpt/list";
	}


	@Action("中美东城重点科室")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<KpiValueDcDept2> gridData() {
		
		String orgid="3";
		String sdate = param("sdate", "");
		String edate = param("edate", "");
		//日期转换为yyyymmdd
		sdate=sdate.replaceAll("-", "");
		edate=edate.replaceAll("-", "");
		//生成数据
		genValue(sdate, edate,orgid);
		
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and psdate=? and pedate=? and userid=?");
		params.add(sdate);
		params.add(edate);
		params.add(getLoginUer().getUserId());
		return PageFactory.newPage(KpiValueDcDept2.class, filter.toString()," order by 科室编号 ",
				params.toArray());
	}
	
	private void genValue(String sdate,String edate ,String orgid){
		// 调用存储过程\\
		Connection con = null;
		try {
			con = DBManager.getConnection();
			CallableStatement callstatement = con
					.prepareCall("call kpicalpk.P_KPI_DC_DEPT_2(?,?,?)");//
			callstatement.setString(1, sdate);
			callstatement.setString(2, edate);
			callstatement.setString(3, getLoginUer().getUserId());
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
	
	
	@Action("中美东城重点科室")
	@ResponseBody
	@RequestMapping("/download")
	public String download() {
		String json=param("json","");
		String sdate=param("sdate","");
		String edate=param("edate","");
		sdate=sdate.replaceAll("-", "");
		edate=edate.replaceAll("-", "");
		GridDataModel model = JSON.parseObject(json, GridDataModel.class);
		final List<KpiValueDcDept2> list=JSON.parseArray(model.getRows().toString(), KpiValueDcDept2.class);
		String filename="中美东城综合指标"+sdate+"-"+edate;
		ExcelExpUtils utils = new ExcelExpUtils(getResponse(), filename+".xls");
		String titles = "科室编号, 科室名称, 门诊量, 门诊收入, 入院量, 出院量, 在院量, 住院收入, 总收入";
		String column = "科室编号, 科室名称, 门诊量, 门诊收入, 入院量, 出院量, 在院量, 住院收入, 总收入";
		utils.export(filename,titles.split(","), column.split(","), new Visitor() {
			
			@Override
			public String getValue(int j,String name) {
				if(name.equals("科室编号")){
					return list.get(j).get科室编号().toString();
				}else if(name.equals("科室名称")){
					return list.get(j).get科室名称();
				}else if(name.equals("科室名称")){
					return list.get(j).get科室名称();
				}else if(name.equals("门诊量")){
					return list.get(j).get门诊量().toString();
				}else if(name.equals("门诊收入")){
					return list.get(j).get门诊收入().toString();
				}else if(name.equals("入院量")){
					return list.get(j).get入院量().toString();
				}else if(name.equals("出院量")){
					return list.get(j).get出院量().toString();
				}else if(name.equals("在院量")){
					return list.get(j).get在院量().toString();
				}else if(name.equals("住院收入")){
					return list.get(j).get住院收入().toString();
				}else if(name.equals("总收入")){
					return list.get(j).get总收入().toString();
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
