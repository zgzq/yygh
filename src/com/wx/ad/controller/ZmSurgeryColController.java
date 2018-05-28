package com.wx.ad.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.Deptart;
import com.wx.ad.dbo.GoalInfo;
import com.wx.ad.dbo.Target;
import com.wx.ad.dbo.ZbsjkInfo1;
import com.wx.ad.dbo.ZmSurgeryCol;
import com.wx.ad.dbo.ZmSurgeryDetail;
import com.wx.ad.util.PageFactory;
import com.wx.ad.util.PageFactoryEasyUI;
import com.wx.ad.util.Test4;
import com.wx.ad.web.AuthController;

import jxl.Sheet;
import jxl.Workbook;
import my.ann.Action;
import my.ann.Menu;
import my.dao.pool.DBManager;
import my.web.AjaxMsg;
import my.web.BaseController;
import my.web.BaseController.CallBack;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Menu("中美医院手术患者明细表")
@RequestMapping("/hos/surgeryCol/")
@Controller
public class ZmSurgeryColController extends BaseController {
	@RequestMapping("listCol")
	public String index() {
		return "admin/surgery/listCol";
	}

	@Action("中美医院手术患者明细表")
	@ResponseBody
	@RequestMapping("gridData1_col")
	public GridDataModel<ZmSurgeryCol> gridData1_Col() {
		// String department = param("deptname", "");
		
		String years = param("years", "");
		String month = param("month", "");
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (years.length() > 0) {
			filter.append(" and year = ? ");
			params.add(years);
		}
		if (month.length() > 0) {
			filter.append(" and month = ? ");
			params.add(month);
		}
		return PageFactoryEasyUI.newPage(ZmSurgeryCol.class, filter.toString(), "order by id", params.toArray());
	}

	@ResponseBody
	@RequestMapping("geneData_col")
	public JSONObject geneDataCol(HttpServletRequest request, HttpSession session) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		if (year.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择年度");
			return JSONObject.fromObject(result);
		}
		if (month.equals("")) {
			result.put("success", false);
			result.put("msg", "请选择月份");
			return JSONObject.fromObject(result);
		}
		Test4 t4 = new Test4();
		int recode = t4.callProcCol(year,month);
		if(recode == 0){
			result.put("success", true);
			result.put("msg", "生成完毕");
		}else{
			result.put("success", false);
			result.put("msg", "生成失败，请重试");
		}
		return JSONObject.fromObject(result);
	}
	
	
	@Action("中美医院手术患者明细表")
	@ResponseBody
	@RequestMapping("gridJson")
	public JSONArray getValue() {
		JSONArray result = new JSONArray();
		PreparedStatement pStatement = null;
		ResultSet resultSet = null;
		Connection conn = null;
		String sql = "select * from ZM_DEPTART order by departid";
		try {
			conn = DBManager.getConnection();
			pStatement = conn.prepareStatement(sql);
			resultSet = pStatement.executeQuery();
			while (resultSet.next()) {
				result.add(buildPerson("" + resultSet.getInt("departid"), resultSet.getString("department")));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pStatement != null) {
					pStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;

	}

	private JSONObject buildPerson(String id, String name) {
		JSONObject result = new JSONObject();
		result.put("value", id);
		result.put("text", name);
		return result;
	}


}
