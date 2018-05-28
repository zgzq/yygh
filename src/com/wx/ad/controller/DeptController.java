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
import com.wx.ad.dbo.UsrDept;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

@Menu("科室信息管理")
@RequestMapping("/sys/dept")
@Controller
public class DeptController extends AuthController {

	@Action("科室信息管理")
	@RequestMapping("/list")
	public String index() {
		return "admin/dept/list";
	}


	@Action("科室信息管理")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<UsrDept> gridData() {
		String realname = param("deptname", "");

		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (realname.length() > 0) {
			filter.append(" and dept_name like ? ");
			params.add(realname + "%");
		}
		
		return PageFactory.newPage(UsrDept.class, filter.toString(),"",
				params.toArray());
	}

	@Action("科室信息管理")
	@ResponseBody
	@RequestMapping("/saveGrid")
	public AjaxMsg gridSave(final @RequestParam("json") String json) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				GridSaveModel model = JSON.parseObject(json,
						GridSaveModel.class);

				List<UsrDept> insert = model.inserts(UsrDept.class);

				List<UsrDept> delete = model.deletes(UsrDept.class);

				List<UsrDept> update = model.updates(UsrDept.class);

				for (UsrDept comp : delete) {
					comp.delete();
				}

				for (UsrDept comp : update) {
					comp.update();
				}

				for (UsrDept comp : insert) {
					comp.setId(comp.newId()+"");
					comp.insert();
				}

				return AjaxMsg.ok();
			}
		});

	}
	
}
