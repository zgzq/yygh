package com.wx.ad.controller;

import java.util.ArrayList;
import java.util.List;

import my.ann.Action;
import my.ann.Menu;
import my.web.AjaxMsg;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.KpiDict;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

@Menu("指标项目管理")
@RequestMapping("/sys/kpidict")
@Controller
public class KpiDictController extends AuthController {

	@Action("指标项目管理")
	@RequestMapping("/list")
	public String index() {
		return "admin/kpidict/list";
	}


	@Action("指标项目管理")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<KpiDict> gridData() {
		String dictname = param("dictname", "");

		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (dictname.length() > 0) {
			filter.append(" and dictname like ? ");
			params.add(dictname + "%");
		}
		
		return PageFactory.newPage(KpiDict.class, filter.toString()," order by dictcode",
				params.toArray());
	}

	@Action("指标项目管理")
	@ResponseBody
	@RequestMapping("/saveGrid")
	public AjaxMsg gridSave(final @RequestParam("json") String json) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				GridSaveModel model = JSON.parseObject(json,
						GridSaveModel.class);

				List<KpiDict> insert = model.inserts(KpiDict.class);

				List<KpiDict> delete = model.deletes(KpiDict.class);

				List<KpiDict> update = model.updates(KpiDict.class);

				for (KpiDict comp : delete) {
					comp.delete();
				}

				for (KpiDict comp : update) {
					comp.update();
				}

				for (KpiDict comp : insert) {
					comp.setId(comp.newId());
					comp.insert();
				}

				return AjaxMsg.ok();
			}
		});

	}
}
