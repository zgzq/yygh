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
import com.wx.ad.dbo.IndSettlement;
import com.wx.ad.util.PageFactory;
import com.wx.ad.web.AuthController;

@Menu("医院收入利润")
@RequestMapping("/sgwh/yysrlr")
@Controller
public class SettlementController extends AuthController {

	@Action("医院收入利润")
	@RequestMapping("/list")
	public String index() {
		return "admin/yysrlr/list";
	}


	@Action("医院收入利润")
	@ResponseBody
	@RequestMapping("/gridData")
	public GridDataModel<IndSettlement> gridData() {
		String orgquery = param("orgquery", "");
		String sdate = param("sdate", "");
		String edate = param("edate", "");

		StringBuffer filter = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		
		//增加控制，只能查询本人录的数据
		String userid=getLoginUer().getUserId();
		if(!userid.equals("-1")){
			filter.append(" and CREATE_USER_ID = ? ");
			params.add(userid);
		}
		if (orgquery.length() > 0) {
			filter.append(" and org_id = ?");
			params.add(orgquery);
		}
		if (sdate.length() > 0) {
			sdate=sdate.replaceAll("-", "");
			filter.append(" and VALUE_DATE >= ?");
			params.add(sdate);
		}
		if (edate.length() > 0) {
			edate=edate.replaceAll("-", "");
			filter.append(" and VALUE_DATE <= ?");
			params.add(edate);
		}
		
		return PageFactory.newPage(IndSettlement.class, filter.toString(),"",
				params.toArray());
	}

	@Action("医院收入利润")
	@ResponseBody
	@RequestMapping("/saveGrid")
	public AjaxMsg gridSave(final @RequestParam("json") String json) {

		return run(new CallBack() {

			@Override
			public AjaxMsg call() throws Exception {
				GridSaveModel model = JSON.parseObject(json,
						GridSaveModel.class);

				List<IndSettlement> insert = model.inserts(IndSettlement.class);

				List<IndSettlement> delete = model.deletes(IndSettlement.class);

				List<IndSettlement> update = model.updates(IndSettlement.class);

				for (IndSettlement comp : delete) {
					comp.delete();
				}

				for (IndSettlement comp : update) {
					//处理科室保存编码
					//处理日期为yyyymmdd
					String vdate=comp.getValue_date();
					if(vdate.length()>0){
						comp.setValue_date(vdate.replaceAll("-", ""));
					}
					comp.setModify_user_id(getLoginUer().getUserId());
					comp.setModify_user_name(getLoginUer().getUserName());
					
					comp.update();
				}

				for (IndSettlement comp : insert) {
					//comp.setId(comp.newId()+"");
					//处理日期为yyyymmdd
					String vdate=comp.getValue_date();
					if(vdate.length()>0){
						comp.setValue_date(vdate.replaceAll("-", ""));
					}
					comp.setCreate_user_id(getLoginUer().getUserId());
					comp.setCreate_user_name(getLoginUer().getUserName());
					comp.insert();
				}

				return AjaxMsg.ok();
			}
		});

	}
	
	
}
