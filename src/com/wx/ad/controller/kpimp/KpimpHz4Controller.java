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
import com.wx.ad.dbo.KpiValueMpHz4;
import com.wx.ad.dbo.KpiValueMpRef;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

import my.ann.Action;
import my.ann.Menu;
import my.dao.pool.DBManager;
import my.web.AjaxMsg;
import my.web.BaseController.CallBack;
@Menu("非上市医院经营数据汇总(日期)")
@RequestMapping("/kpimp/hz4")
@Controller
public class KpimpHz4Controller extends AuthController  {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(KpimpHz4Controller.class);

	@Action("非上市医院经营数据汇总(日期)")
	@RequestMapping("/list")
	public String index() {
		return "admin/kpimp/hz4";
	}

	@Action("非上市医院经营数据汇总(日期)")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<KpiValueMpHz4> gridData() {
		String psdate = param("psdate", "").replace("-", "");
		String pedate = param("pedate", "").replace("-", "");
		String userid=getLoginUer().getUserId();
		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		//生成数据
		KpimpUtil.genValueHz4( psdate,pedate, userid);
		//查询数据
		filter.append(" and psdate = ? and pedate=? and userid=? ");
		params.add(psdate);
		params.add(pedate);
		params.add(userid);
		return PageFactory.newPage(KpiValueMpHz4.class, filter.toString()," order by seq ",
				params.toArray());
	}
	

	
}
