package com.wx.ad.controller;

import java.util.ArrayList;
import java.util.List;

import me.chanjar.weixin.cp.api.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpUser;
import my.ann.Action;
import my.ann.Menu;
import my.web.AjaxMsg;
import my.web.BaseController.CallBack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wx.ad.bean.GridDataModel;
import com.wx.ad.bean.GridSaveModel;
import com.wx.ad.dbo.SysMenu;
import com.wx.ad.dbo.SysUser;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;
import com.wx.cp.wx.WxCp;

@Menu("菜单管理")
@RequestMapping("/sys/menu")
@Controller
public class MenuController extends AuthController {

	@Action("菜单管理")
	@RequestMapping("/list")
	public String index() {
		return "admin/menu/menu";
	}


	@Action("菜单管理")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<SysMenu> gridData() {
		String text = param("text", "");

		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		filter.append(" and 1=1");
		if (text.length() > 0) {
			filter.append(" and text like ? ");
			params.add(text + "%");
		}
		
		return PageFactory.newPage(SysMenu.class, filter.toString()," order by id ",
				params.toArray());
	}

	@Action("菜单管理")
	@ResponseBody
	@RequestMapping("/saveGrid")
	public AjaxMsg gridSave(final @RequestParam("json") String json) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				GridSaveModel model = JSON.parseObject(json,
						GridSaveModel.class);

				List<SysMenu> insert = model.inserts(SysMenu.class);

				List<SysMenu> delete = model.deletes(SysMenu.class);

				List<SysMenu> update = model.updates(SysMenu.class);

				for (SysMenu comp : delete) {
					comp.delete();
					
				}

				for (SysMenu comp : update) {
					comp.update();
				}

				for (SysMenu comp : insert) {
					//comp.setId(comp.newId()+"");
					comp.insert();
				}

				return AjaxMsg.ok();
			}
		});

	}
	
}
