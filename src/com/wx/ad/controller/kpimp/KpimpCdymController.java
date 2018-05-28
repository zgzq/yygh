package com.wx.ad.controller.kpimp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.KpiValueMpDay;
import com.wx.ad.dbo.KpiValueMpHz1;
import com.wx.ad.dbo.KpiValueMpMonth;
import com.wx.ad.dbo.KpiValueMpRef;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

import my.ann.Action;
import my.ann.Menu;
import my.dao.pool.DBManager;
import my.web.AjaxMsg;
import my.web.BaseController.CallBack;
@Menu("成都誉美医院")
@RequestMapping("/kpimp/cdym")
@Controller
public class KpimpCdymController extends AuthController  {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(KpimpCdymController.class);

	@Action("成都誉美医院")
	@RequestMapping("/list")
	public String index() {
		return "admin/kpimp/cdym";
	}

	@Action("成都誉美医院")
	@ResponseBody
	@RequestMapping("/gridData1")
	public GridDataModel<KpiValueMpDay> gridData1() {
		String orgid = param("orgid", "");
		String yearmonth = param("yearmonth", "");
		String userid=getLoginUer().getUserId();
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		//生成数据
		KpimpUtil.genValue(orgid, yearmonth, userid);
		//查询数据
		filter.append(" and orgid = ? and yearmonth = ? and userid=? ");
		params.add(orgid);
		params.add(yearmonth);
		params.add(userid);
		return PageFactory.newPage(KpiValueMpDay.class, filter.toString()," order by seq ",
				params.toArray());
	}
	
	@Action("成都誉美医院")
	@ResponseBody
	@RequestMapping("/gridData2")
	public GridDataModel<KpiValueMpMonth> gridData2() {
		String orgid = param("orgid", "");
		String year = param("year", "");
		String userid="-1";
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		//生成数据
		KpimpUtil.genValue(orgid, year, userid);
		//查询数据
		filter.append(" and orgid = ? and year = ? and userid=? ");
		params.add(orgid);
		params.add(year);
		params.add(userid);
		return PageFactory.newPage(KpiValueMpMonth.class, filter.toString()," order by seq , kpitype",
				params.toArray());
	}
}
