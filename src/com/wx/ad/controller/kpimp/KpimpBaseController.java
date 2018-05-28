package com.wx.ad.controller.kpimp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import my.ann.Action;
import my.ann.Menu;
import my.dao.pool.DBManager;
import my.poi.ExcelUtils;
import my.web.AjaxMsg;
import my.web.BaseController.CallBack;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.KpiValueMpRef;
import com.wx.ad.dbo.SysUser;
import com.wx.ad.dbo.ValueRange;
import com.wx.ad.util.ExcelExpUtils;
import com.wx.ad.util.ExcelExpUtils.Visitor;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;
import com.wx.cp.wx.WxCp;

import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpUser;

@Menu("目标进度设置")
@RequestMapping("/kpimp/base")
@Controller
public class KpimpBaseController extends AuthController {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(KpimpBaseController.class);

	@Action("目标进度设置")
	@RequestMapping("/list")
	public String index() {
		return "admin/kpimp/base";
	}

	@Action("目标进度设置")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<KpiValueMpRef> gridData() {
		String orgid = param("orgid", "");
		String year = param("year", "");

		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		filter.append(" and orgid = ? and year = ? ");
		params.add(orgid);
		params.add(year);
		
		return PageFactory.newPage(KpiValueMpRef.class, filter.toString(),"",
				params.toArray());
	}
	
	@Action("目标进度设置")
	@ResponseBody
	@RequestMapping("/saveGrid")
	public AjaxMsg gridSave(final @RequestParam("json") String json) {
		final String orgid = param("orgid", "");
		final String year = param("year", "");
		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
				List<KpiValueMpRef> insert = model.inserts(KpiValueMpRef.class);
				List<KpiValueMpRef> delete = model.deletes(KpiValueMpRef.class);
				List<KpiValueMpRef> update = model.updates(KpiValueMpRef.class);
				for (KpiValueMpRef comp : delete) {
					comp.delete();
				}

				for (KpiValueMpRef comp : update) {
					comp.update();
				}

				for (KpiValueMpRef comp : insert) {
					comp.setOrgid(Integer.parseInt(orgid));
					comp.setYear(Integer.parseInt(year));
					comp.insert();
				}

				return AjaxMsg.ok();
			}
		});

	}
	
}
