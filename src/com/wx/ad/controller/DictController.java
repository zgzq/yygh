package com.wx.ad.controller;

import java.util.List;

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
import com.wx.ad.dbo.Dict;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

@Menu("字典维护")
@RequestMapping("/sys/dict")
@Controller
public class DictController extends AuthController {

	@Action("字典维护")
	@RequestMapping("/list")
	public String index() {
		return "admin/dict/dict";
	}
	
	@Action("字典维护")
	@ResponseBody
	@RequestMapping("/treeData")
	public List<Dict> treeData() {
		return Dict.INSTANCE.query("");
	}
	
	

	@Action("字典维护")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<Dict> gridData() {
		String pid = param("pid", "0");
		String filter = "pid = ? ";
		
		return PageFactory.newPage(Dict.class, filter," order by value", pid);
	}

	@Action("字典维护")
	@ResponseBody
	@RequestMapping("/saveGrid")
	public AjaxMsg gridSave(final @RequestParam("json") String json) {
		 
		return run(new CallBack() {
			
			@Override
			public AjaxMsg call() throws Exception {
				GridSaveModel model = JSON.parseObject(json, GridSaveModel.class);
				
				List<Dict> insert = model.inserts(Dict.class);
				
				List<Dict> delete = model.deletes(Dict.class);
				
				List<Dict> update=model.updates(Dict.class);
				
				for(Dict dict:delete){
					dict.delete();
				}
				
				for(Dict dict:update){
					dict.update();
				}
				
				for(Dict dict:insert){
					dict.setId(String.valueOf(dict.newId()));
					dict.insert();
				}
				return AjaxMsg.ok();
			}
		});
	
	}
}
